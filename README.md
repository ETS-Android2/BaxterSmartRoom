# BaxterSmartRoom
## Problem Statement
This is the code repository for the Baxter Smart Room team as part of the senior design project for the 2021-2022 at the University of Rochester. We have designed a prototype Android application that  adresses common infusion pump problems.

Here, we define the main problems of infusion pumps:
* Infusion pump induced alarm fatigue is a significant problem in the clinic [1]
* There is a lack of central monitoring systems for infusion pumps in the hospital care areas
* There is a lack of sufficient communication between the smart pumps and the electronic medical record (EMR)

## Design
We broke our engineering team into two parts: the back-end and front-end team. The back-end consists of three main sections: the data synthesis script, the back-end pipeline script, and the simulation script. The front-end consists of the Android application.

## Instructions
To run this infusion pump simulation, you must first synthesize the data. Go to the `PCapp` branch of this repository. The script `synthesize.py` has a fuction defined that synthesizes the files `emr.csv` and `pumps.csv`. This function can be called by the user to synthesize data at user-defined parameters (such as number of pumps per patient, probability of alarm, etc.).

We have already written a script that calls this function, processes the data, and uploads a JSON file to Google Firebase, a JSON file which the front-end of the app will use. This script is `combine_V2.py`. At this point in the instructions, run the script `combine_V2.py`.

<img width="400" alt="Screen Shot 2022-05-03 at 12 34 34 PM" src="https://user-images.githubusercontent.com/59581492/166498482-fc7805ab-7870-4d36-b605-da7bd08cf0e0.png">
Figure 1: Google Firebase as a server for our back-end data

Now, a JSON file has been generated and the Android app has data to query. Switch to the `fronend` branch of this repository. Download the repository and move it into your Android Studio directory. Launch Android Studio and change your directory to the folder you just downloaded. Send the Android application to your Android tablet or run a virtual Android device on your computer.

<img width="1043" alt="Screen Shot 2022-05-03 at 12 36 19 PM" src="https://user-images.githubusercontent.com/59581492/166499087-79329bb3-a1f9-487b-9262-bd9e31efce40.png">
Figure 2: Android app

In your Android device, launch the application called "Baxter Smart Room." You will see the app populated with your user-defined data synthesis. Now, switch to the `PCapp` branch of this repository and run the file `simulate_V2.py`.

This script, `simulate_V2.py`, adds time-dependence on the simulation. `combine_V2.py` is not time dependent. Running `simulate_V2.py` results in the infusion progressing overtime, and these changes are made on Firebase as part of the back-end. These changes can be observed on the front-end via the text displayed and the progress bar.

## References
[1] Hravnak, Marilyn, et al. "A call to alarms: Current state and future directions in the battle against alarm fatigue." Journal of electrocardiology 51.6 (2018): S44-S48.
