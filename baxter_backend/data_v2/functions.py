def get_index(i, data, user, care_area, emr_room):
    if data[user][care_area][emr_room] != 1:
        room_n = emr_room
        n_other_pumps = 0 #number of pumps counted so far that are not in this room
        for n in range(room_n - 1):
            n_other_pumps += len(data[user][care_area][n+1]["pumps"])
        pump_ind = i + 1 - n_other_pumps #room dependent pump index
    else:
        pump_ind = i + 1

    return pump_ind
