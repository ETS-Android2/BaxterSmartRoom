import numpy as np
import pandas as pd
from functions import function
import json

# ------------------------------------------------------------------------------
# load data
# ------------------------------------------------------------------------------

f = open('users.json')
users = json.load(f)

alib = pd.read_csv('alarm_flagging.csv')
dlib = pd.read_csv('drug_library.csv')

m_names = ['John','Aidan','Ben','Tim','Nick','Jack','James','Adam','Neil','Xu']
f_names = ['Amanda','Carol','Ella','Olivia','Emma','Kate','Hope','Eleanor','Xin']
l_names = ['Monroe','Adams','Gold','Doe','Turing','Adams','Stock','Bellin','Zhao']
names = [m_names,f_names,l_names]

#emr.to_csv('emr.csv', index=False)

# ------------------------------------------------------------------------------
# define synthesize()
# ------------------------------------------------------------------------------

def synthesize(users, dlib, names, n_care_areas=1, n_patients=3, avgPumpsPerPatient=2):
    # --------------------------------------------------------------------------
    # preallocate
    # --------------------------------------------------------------------------

    #preallocate pumps.csv lists
    pumpID = []
    drug = []
    currentRate = []
    startVolume = []
    alarm = []

    #preallocate emr.csv lists
    room = []
    patientID = []
    age = []
    sex = []
    patient = []

    # --------------------------------------------------------------------------
    # iterate through every pump and synthesize patient data
    # --------------------------------------------------------------------------

    for i in range(n_patients):
        if np.random.randint(0,2) > 0:
            nPumpsPerPatient = round(int(avgPumpsPerPatient) / int(np.random.randint(1,5)))
            if nPumpsPerPatient == 0:
                nPumpsPerPatient = 1
        else:
            nPumpsPerPatient = round(int(avgPumpsPerPatient) * int(np.random.randint(1,5)))

        counter = 0 #resets to zero everyime new patient begins

        for j in range(nPumpsPerPatient):
            drug_i = np.random.randint(0, len(dlib.drug) - 1) #index for which drug to pick
            possible_volumes = [50, 100, 250, 500, 1000] #units: mL

            #append pumps.csv
            pumpID.append(18696786 + i)
            drug.append(dlib.drug[drug_i])
            currentRate.append(dlib.maxRate[drug_i] * np.random.randint(1,12) / 10) # 1 in 11 chance that pump has a fucked up current rate to start with
            startVolume.append(possible_volumes[np.random.randint(0, len(possible_volumes))])
            if currentRate[-1] > dlib.maxRate[drug_i]:
                alarm.append(999)
            else:
                if int(np.random.randint(0,6)) == 5:
                    alarm_i = np.random.randint(0, len(alib.code) - 1) #index for which alarm to pick
                    alarm.append(alib.code[alarm_i])
                else:
                    alarm.append(0)

            #append emr.csv
            room.append(i+1)
            patientID.append(int(np.random.randint(20000, 60000)))
            age.append(int(np.random.randint(10, 90)))

            if np.random.randint(0,2) > 0:
                sex.append('m')
                maleNameIndex = int(np.random.randint(0,len(names[0])))
                lastNameIndex = np.random.randint(0,len(names[2]))
                patient.append(names[maleNameIndex] + ' ' + names[lastNameIndex])
            else:
                sex.append('f')
                femaleNameIndex = int(np.random.randint(0,len(names[1])))
                lastNameIndex = int(np.random.randint(0,len(names[2])))
                patient.append(names[femaleNameIndex] + ' ' + names[lastNameIndex])

    #make pandas dataframe for pumps.csv
    pumps_dict = {"pumpID" : pumpID, "drug" : drug, "currentRate" : currentRate,
        "startVolume" : startVolume, "alarm" : alarm}

    pumps_df = pd.DataFrame(pumps_dict)

    #make pandas dataframe for emr.csv
    emr_dict = {"room" : room, "patient" : patient, "patientID" : patientID,
        "pumpID" : pumpID, "sex" : sex, "age" : age}

    emr_df = pd.DataFrame(emr_dict)



    return None


synthesize(users, dlib, names)

emr = []
pumps = []
