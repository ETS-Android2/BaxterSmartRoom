import numpy as np
from numpy import random as random
import pandas as pd
import json
import ast
from datetime import datetime
from datetime import timedelta
from firebase import firebase
from functions import get_index
from synthesize import synthesize


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
                            "pumpnumber" : str(pump_ind),
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
                        "roomnumber" : str(int(emr.room[i])),
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


def main(userPatientAssign=[32,20,11,8]):
    f = open('users.json')
    users = json.load(f)
    usersList = list(users.keys())
    dataList = []

    for i in range(len(userPatientAssign)):
        #synthesize data
        n_patients = userPatientAssign[i]
        success = synthesize(n_patients=n_patients)

        #generate dictionary and convert to string
        user = usersList[i]
        dataString = str(function(user, users[user]["name"]))

        #do string operations
        dataString = dataString[1:]
        dataString = dataString[:-1]
        dataList.append(dataString)

    combinedStrings = '{'
    for i in range(len(dataList)):
        if i != (len(dataList) - 1):
            combinedStrings = combinedStrings + dataList[i] + ', '
        else:
            combinedStrings = combinedStrings + dataList[i] + '}'

    data = ast.literal_eval(combinedStrings)

    return data

data = main()

with open('tmp.json', 'w') as fp:
    json.dump(data, fp)


#print(type(data))

fb = firebase.FirebaseApplication("https://baxtersmartroom-default-rtdb.firebaseio.com/", None)
result = fb.post("users/", data)
#result = fb.put("users/", 'Name', )
print(result)


#goal:
goal_data = {
    "nnguy22" : { #user
        "name" : "Tram Nguyen", #username
        "password" : 123456,
        "n_patients" : 11, #total number of patients in care area
        "careArea" : { #care area
            1 : { #room number
                "maxSeverity" : 0,
                "patientID" : 29847,
                "name" : "Tram Nguyen", #patient name
                "age" : 21,
                "sex" : "f",
                "n_pumps" : 3, #total number of pumps on patient
                "Pumps" : {
                    1: {
                        "pumpID" : 18696786,
                        "drug" : "naloxone_0.4mg_1mL",
                        "currentRate" : 0.1,
                        "startVolume" : 250,
                        "alarm" : 0,
                        "alarm_severity" : 0,
                        "alarm_text" : "No alarm",
                        "time_left" : "tbd", #new
                        "projected_end_time" : "tbd", #new
                        "percent_complete" : "tbd" #new
                    }
                }
            }
        }
    }
}



# ------------------------------------------------------------------------------
# testing
# ------------------------------------------------------------------------------
'''
for key in data["nnguy2"]["pediatrics"][1][29847]["pumps"]:
    print(type(key))
'''
