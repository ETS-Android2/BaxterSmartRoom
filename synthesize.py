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

def synthesize(users, dlib, names, n_care_areas=3, n_patients=25, avgPumpsPerPatient=4):
    pumpID = []
    drug = []

    for i in range(n_patients):
        if np.random.randint(0,2) > 0:
            nPumpsPerPatient = round(int(avgPumpsPerPatient) / int(np.random.randint(1,5)))
        else:
            nPumpsPerPatient = round(int(avgPumpsPerPatient) * int(np.random.randint(1,5)))

        for j in range(nPumpsPerPatient):
            drug_i = np.random.randint(0, len(dlib.drug) - 1) #index for which drug to pick

            #append pumps.csv
            pumpID.append(18696786 + i)
            drug.append(dlib.drug[drug_i])
            currentRate.append(currentRate)

    return None


synthesize(users, dlib, names)

emr = []
pumps = []
