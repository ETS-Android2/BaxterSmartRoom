package com.example.baxter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Pump1 extends AppCompatActivity {
    String pindexx, patient, user, key, ptindexx, Pump, rate, colorss;
    Button homebutton, backbutton;
    RelativeLayout Layout1;
    TextView timeleft, endtime, volumeleft, drugg, ratee, startVolumel, IDD, alarmtext;
    Spinner dynamicspinner;
    int selected, ptindex, pindex;
    String[] paths = {"Select Pump","Pump 1", "Pump 2", "Pump 3", "Pump 4", "Pump 5", "Pump 6", "Pump 7", "Pump 8"};
ProgressBar pct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pump1);
        homebutton = findViewById(R.id.HomeButton);
        backbutton = findViewById(R.id.BackButton);
        drugg = findViewById(R.id.drug);
        ratee = findViewById(R.id.rate);
        startVolumel = findViewById(R.id.startVolume);
        IDD = findViewById(R.id.pumpID);
        timeleft = findViewById(R.id.timeleft);
        pct = findViewById(R.id.determinateBar);
        endtime = findViewById(R.id.endtime);
        volumeleft = findViewById(R.id.volumeleft);
        alarmtext = findViewById(R.id.alarmtext);
        Layout1 = findViewById(R.id.Layout1);
        data();
        clickListen();
    }

    private void clickListen() {
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Pump1.this, PumpActivity1.class);
                intent.putExtra("patients", patient);
                intent.putExtra("user", user);
                intent.putExtra("key", key);
                intent.putExtra("Pump", Pump);
                intent.putExtra("ptindex", ptindexx);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Pump1.this, PatientActivity1.class);
                intent.putExtra("patients", patient);
                intent.putExtra("user", user);
                intent.putExtra("key", key);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    private void data() {
        Intent grabdata = getIntent();
        user = grabdata.getStringExtra("user");
        ptindexx = grabdata.getStringExtra("ptindex");
        patient = grabdata.getStringExtra("patients");
        String alarm = grabdata.getStringExtra("alarm");
        key = grabdata.getStringExtra("key");
        String alarm_severity = grabdata.getStringExtra("alarm_severity");
        String drug = grabdata.getStringExtra("drug");
        pindexx = grabdata.getStringExtra("pindex");
        Log.d("pindex", pindexx);
        Pump = grabdata.getStringExtra("Pump");
        rate = grabdata.getStringExtra("rate");
        String startVolume = grabdata.getStringExtra("startVolume");
        String ID = grabdata.getStringExtra("ID");
        String alarm_text = grabdata.getStringExtra("alarm_text");
        int colors8 = Integer.parseInt(alarm_severity);
        pindex = Integer.parseInt(pindexx);
        ptindex = Integer.parseInt(ptindexx);
       if (colors8 == 1) {
            Layout1.setBackgroundResource(R.color.yellow);
        } else if (colors8 ==2){
            Layout1.setBackgroundResource(R.color.orange);
        } else if (colors8 == 3){
            Layout1.setBackgroundResource(R.color.red);
        } else {
            Layout1.setBackgroundResource(R.color.green);
        }
        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("users").child(key);
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                patient = snapshot.child(user).child("n_patients").getValue().toString();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
       DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users")
                .child(key).child(user).child("careArea").child(ptindexx).child("Pumps").child(pindexx);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                colorss= snapshot.child("alarm_severity").getValue().toString();
                int colors = Integer.parseInt(colorss);
                if (colors == 1) {
                    Layout1.setBackgroundResource(R.color.yellow);
                } else if (colors ==2){
                    Layout1.setBackgroundResource(R.color.orange);
                } else if (colors == 3){
                    Layout1.setBackgroundResource(R.color.red);
                } else {
                    Layout1.setBackgroundResource(R.color.green);
                }
                String drugup= snapshot.child("drug").getValue().toString();
                String rateup= snapshot.child("currentRate").getValue().toString();
                String startVolumeup= snapshot.child("startVolume").getValue().toString();
                String alarm_textup= snapshot.child("alarm_text").getValue().toString();
                String IDup= snapshot.child("pumpID").getValue().toString();
                String pct1= snapshot.child("percent_complete").getValue().toString();
                int percent = Integer.parseInt(pct1);
                pct.setProgress(percent);
                String endtime1= snapshot.child("projected_end_time").getValue().toString();
                String timeleft1= snapshot.child("time_left").getValue().toString();
                String volumeleft1= snapshot.child("volume_left").getValue().toString();
                endtime.setText("End Time: "+endtime1);
                timeleft.setText("Time Left: "+timeleft1);
                volumeleft.setText("Volume Left: "+volumeleft1);
                drugg.setText(drugup);
                ratee.setText("Current Rate: "+rateup);
                startVolumel.setText("Start Volume: "+startVolumeup);
                IDD.setText("Pump ID: "+IDup);
                alarmtext.setText(alarm_textup);
            }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        drugg.setText(drug);
        ratee.setText(rate);
        startVolumel.setText(startVolume);
        IDD.setText(ID);
        alarmtext.setText(alarm_text);

    }
}