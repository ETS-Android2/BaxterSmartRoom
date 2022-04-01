import numpy as np
import pandas as pd
from combine import function
import json

emr = pd.read_csv('emr.csv')
pumps = pd.read_csv('pumps.csv')
dlib = pd.read_csv('drug_library.csv')

time = list(range(60))

alerts = []

t1 = np.random.randint(40, high=60)
t2 = np.random.randint(40, high=60)

print('t1: ' + str(t1))
print('t2: ' + str(t2))


for i in time:
    pumps = pd.read_csv('pumps.csv')

    if i == t1:
        pumps.currentRate[6] = 10
    if i == t2:
        pumps.currentRate[2] = 100

    pumps.to_csv('pumps.csv', index=False)

    alerts, h_alerts = function()

    if len(alerts) != 0:
        print('alert at t = ' + str(i) + ' at pump(s) ' + str(alerts))

    delay()

alerts = []
pumps.currentRate[6] = 0.01
pumps.currentRate[2] = 0.01
pumps.to_csv('pumps.csv')
