import numpy as np
import pandas as pd
import json
from firebase_admin import db
import firebase_admin
from firebase_admin import credentials
import time

if not firebase_admin._apps:
    cred = credentials.Certificate('serviceAccountKey.json')
    default_app = firebase_admin.initialize_app(cred, {'databaseURL': 'https://baxtersmartroom-default-rtdb.firebaseio.com'})

ref = db.reference('users/-N0q2mQHBTNqzjjGA3By/nnguy22/careArea/1/Pumps/1')
print(ref.get())

#reset everything
ref.child('percent_complete').set(0)
ref.child('time_left').set("30 minutes")
ref.child('volume_left').set("600 mL")

min = 0
tm = 30
for i in range(30):
    ref = db.reference('users/-N0q2mQHBTNqzjjGA3By/nnguy22/careArea/1/Pumps/1')
    print(ref.get())
    min = min + 1
    my_pctg = int(100*round(1-(tm - min)/30,5))
    if my_pctg > 100:
        my_pctg = 100
    ref.child('percent_complete').set(my_pctg)
    time.sleep(2)
    ref.child('time_left').set(str(tm - min) + " minutes")
    time.sleep(2)
    ref.child('volume_left').set(str(int(600 - 600*(1-(tm - min)/30))) + ' mL')
    time.sleep(2)

    time.sleep(15)
