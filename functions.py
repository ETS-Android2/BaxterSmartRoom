def get_index(i, data, user, care_area, emr_room):
    if data[user][care_area][emr_room] != 1:
        room_n = emr_room
        n_other_pumps = 0 #number of pumps counted so far that are not in this room
        for n in range(room_n - 1):
            n_other_pumps += len(data[user][care_area][n+1]["Pumps"])
        pump_ind = i + 1 - n_other_pumps #room dependent pump index
    else:
        pump_ind = i + 1

    return pump_ind

def countX(lst, x):
    count = 0
    for ele in lst:
        if (ele == x):
            count = count + 1
    return count

def function(user, name):

    # --------------------------------------------------------------------------
    # load data
    # --------------------------------------------------------------------------

    emr = pd.read_csv('emr.csv')
    pumps = pd.read_csv('pumps.csv')
    dlib = pd.read_csv('drug_library.csv')
    alib = pd.read_csv('alarm_flagging.csv')

    # --------------------------------------------------------------------------
    # generate JSON formatted data for front-end
    # --------------------------------------------------------------------------

    data = dict() #pre-define the variable data as a dictionary
    for i in range(len(pumps.pumpID)):
        while True:
            if user in data:
                currentPosition = data[user]
                if "careArea" in currentPosition:
                    currentPosition = currentPosition["careArea"]
                    if int(emr.room[i]) in currentPosition:
                        currentPosition = currentPosition[int(emr.room[i])]
                        currentPosition = currentPosition["Pumps"]
                        pump_ind = get_index(i, data, user, "careArea", int(emr.room[i]))
                        if pump_ind in currentPosition:
                            currentPosition = currentPosition[pump_ind]
                            break
                        else:
                            #determine alarm severity
                            alarmIndex = list(alib.code).index(pumps.alarm[i])
                            if alib.clinicallyActionable[alarmIndex] == -1 and alib.highRisk[alarmIndex] == -1:
                                severity = 0
                            elif alib.clinicallyActionable[alarmIndex] == 0 and alib.highRisk[alarmIndex] == 0:
                                severity = 1
                            elif alib.clinicallyActionable[alarmIndex] == 1 and alib.highRisk[alarmIndex] == 0:
                                severity = 2
                            elif alib.clinicallyActionable[alarmIndex] == 1 and alib.highRisk[alarmIndex] == 1:
                                severity = 3

                            #set n_pumps to pump_ind, pump_ind on last iteration is the number of pumps
                            data[user]["careArea"][int(emr.room[i])]["n_pumps"] = pump_ind

                            #determine the max severity (if statement checks condition)
                            maxSeverity = data[user]["careArea"][int(emr.room[i])]["maxSeverity"]
                            if severity > maxSeverity:
                                data[user]["careArea"][int(emr.room[i])]["maxSeverity"] = severity

                            data[user]["careArea"][int(emr.room[i])]["Pumps"][pump_ind] = {
                            "pumpID" : int(pumps.pumpID[i]),
                            "drug" : pumps.drug[i],
                            "currentRate" : str(float(pumps.currentRate[i])) + " mg/min",
                            "startVolume" : str(int(pumps.startVolume[i])) + " mL",
                            "alarm" : int(pumps.alarm[i]),
                            "alarm_severity" : severity,
                            "alarm_text" : alib.description[alarmIndex],
                            "volume_left" : str(pumps.startVolume[i]) + " mL", #new
                            "time_left" : str(round(pumps.startVolume[i] * 0.62)) + " minutes",  #new
                            "projected_end_time" : (datetime.now() + timedelta(minutes=round(pumps.startVolume[i] * 0.62))).strftime("%H:%M:%S"), #new
                            "percent_complete" : 0 #new
                            }
                    else:
                        data[user]["careArea"][int(emr.room[i])] = {
                        "maxSeverity" : 0, #THIS WORKS. IT'S JUST PREALLOCATION
                        "maxSevPumps" : "",
                        "patientID" : int(emr.patientID[i]),
                        "name" : emr.patient[i],
                        "age" : int(emr.age[i]),
                        "sex" : emr.sex[i],
                        "n_pumps" : "tbd", #THIS WORKS. IT'S JUST PREALLOCATION
                        "Pumps" : {}
                        }
                else:
                    data[user]["careArea"] = {}
            else:
                data[user] = {
                "name" : name,
                "password" : "123456",
                "n_patients" : int(len(list(set(emr.patient)))) #total number of patients in care area
                }

    for i in range(data[user]["n_patients"]):
        nPumps = data[user]["careArea"][i+1]["n_pumps"]
        maxSeverity = data[user]["careArea"][i+1]["maxSeverity"]

        maxSevPumpsList = []
        for j in range(nPumps):
            if data[user]["careArea"][i+1]["Pumps"][j+1]["alarm_severity"] == maxSeverity:
                maxSevPumpsList.append(j+1)

        #convert list to string
        maxSevPumpsString = ""
        counter = 0
        for k in maxSevPumpsList:
            if counter > 0:
                maxSevPumpsString = maxSevPumpsString + ' ' + str(k)
            else:
                maxSevPumpsString = maxSevPumpsString + str(k)
                counter = 1

        data[user]["careArea"][i+1]["maxSevPumps"] = maxSevPumpsString

    return data
