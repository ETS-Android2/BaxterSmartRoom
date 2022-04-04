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

m_names = ['John','Aidan','Ben','Feng','Nick','Jack','James','Adam','Neil','Xi']
f_names = ['Amanda','Carol','Ella','Olivia','Emma','Kate','Hope','Eleanor','Mei']
l_names = ['Monroe','Adams','Hansen','Li','Turing','Turner','Smith','Bellin','Zhao']
names = [m_names,f_names,l_names]

#emr.to_csv('emr.csv', index=False)

# ------------------------------------------------------------------------------
# define synthesize()
# ------------------------------------------------------------------------------

def synthesize(users, alib, dlib, names, p_of_alarm = 0.2, n_care_areas=1, n_patients=25, avgPumpsPerPatient=4):
    '''
    p_of alarm must be a value between 0 and 1
    '''
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
            currentRate.append(round(dlib.maxRate[drug_i] * np.random.randint(10,22) / 20, ndigits=4)) # 1 in 11 chance that pump has a fucked up current rate to start with. this is rounded to the ten-thousandths
            startVolume.append(possible_volumes[np.random.randint(0, len(possible_volumes))])
            if currentRate[-1] > dlib.maxRate[drug_i]:
                alarm.append(999)
            else:
                p_of_alarm = round(p_of_alarm, ndigits=2)
                if int(np.random.randint(0, 100)) <= 100 * p_of_alarm - 1: #implementation of alarm probability. default is 1 in 5 chance of alarms
                    alarm_i = np.random.randint(0, len(alib.code) - 1) #index for which alarm to pick
                    alarm.append(alib.code[alarm_i])
                else:
                    alarm.append(0)

            #append emr.csv
            room.append(i+1)

            if counter == 0: #randomly generate if counter is zero because counter resets at the start of each patient iteration
                if np.random.randint(0,2) > 0:
                    Sex = 'm'
                    maleNameIndex = int(np.random.randint(0,len(names[0])))
                    lastNameIndex = np.random.randint(0,len(names[2]))
                    fullName = names[0][maleNameIndex] + ' ' + names[2][lastNameIndex]
                    PatientID = int(np.random.randint(20000, 60000))
                    Age = int(np.random.randint(10, 90))
                else:
                    Sex = 'f'
                    femaleNameIndex = int(np.random.randint(0,len(names[1])))
                    lastNameIndex = int(np.random.randint(0,len(names[2])))
                    fullName = names[1][femaleNameIndex] + ' ' + names[2][lastNameIndex]
                    PatientID = int(np.random.randint(20000, 60000))
                    Age = int(np.random.randint(10, 90))

                sex.append(Sex)
                patient.append(fullName)
                patientID.append(PatientID)
                age.append(Age)

                counter += 1

            else: #SHOULD NOT randomly generate again

                sex.append(Sex)
                patient.append(fullName)
                patientID.append(PatientID)
                age.append(Age)

    # --------------------------------------------------------------------------
    # synthesize the CSV
    # --------------------------------------------------------------------------

    #make dictionary for pandas dataframe (pumps.csv)
    pumps_dict = {"pumpID" : pumpID, "drug" : drug, "currentRate" : currentRate,
        "startVolume" : startVolume, "alarm" : alarm}

    #make dictionary for pandas dataframe (emr.csv)
    emr_dict = {"room" : room, "patient" : patient, "patientID" : patientID,
        "pumpID" : pumpID, "sex" : sex, "age" : age}

    #convert dictionaries to dataframe
    pumps_df = pd.DataFrame(pumps_dict)
    emr_df = pd.DataFrame(emr_dict)

    #convert dataframe to CSV
    pumps_df.to_csv('pumps_AUTO.csv', index=False)
    emr_df.to_csv('emr_AUTO.csv', index=False)

    return None


synthesize(users, alib, dlib, names, n_patients=7)
