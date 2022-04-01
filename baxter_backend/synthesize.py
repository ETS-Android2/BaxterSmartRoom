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

def synthesize(users, dlib, names, n_care_areas, n_patients):
    avgPumpsPerPatient = 3 #let the average number of pumps per patient be 3
    for i in range(n_patients):
         t = 3 * int(np.random.randint(1,3))
    return None


synthesize(users, dlib, 3, 1000)

emr = []
pumps = []
