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
    TextView drugg, ratee, startVolumel, IDD, alarmtext;
    Spinner dynamicspinner;
    int selected, ptindex, pindex;
    String[] paths = {"Select Pump","Pump 1", "Pump 2", "Pump 3", "Pump 4", "Pump 5", "Pump 6", "Pump 7", "Pump 8"};


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
        alarmtext = findViewById(R.id.alarmtext);
        Layout1 = findViewById(R.id.Layout1);
        data();
        /*
        dynamicspinner =(Spinner) findViewById(R.id.dropdown_menu);
        ArrayAdapter<String>adapter = new ArrayAdapter (this,
                android.R.layout.simple_spinner_item,paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dynamicspinner.setAdapter(adapter);

        dynamicspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.e("item", (String) parent.getItemAtPosition(position));
                switch (position) {
                    case 1:
                        selected = 1;
                        // Whatever you want to happen when the first item gets selected
                        break;
                    case 0:
                        selected = 0;
                        // Whatever you want to happen when the second item gets selected
                        break;
                    case 2:
                        selected = 2;
                        // Whatever you want to happen when the thrid item gets selected
                        break;
                    case 3:
                        selected = 3;
                        // Whatever you want to happen when the first item gets selected
                        break;
                    case 4:
                        selected = 4;
                        // Whatever you want to happen when the second item gets selected
                        break;
                    case 5:
                        selected = 5;
                        // Whatever you want to happen when the thrid item gets selected
                        break;
                    case 6:
                        selected = 6;
                        // Whatever you want to happen when the second item gets selected
                        break;
                    case 7:
                        selected = 7;
                        // Whatever you want to happen when the thrid item gets selected
                        break;
                    case 8:
                        selected = 8;
                        // Whatever you want to happen when the thrid item gets selected
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

*/
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
                .child(key).child(user).child("careArea").child(ptindexx).child("pumps").child(pindexx);
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
                drugg.setText(drugup);
                ratee.setText(rateup);
                startVolumel.setText(startVolumeup);
                IDD.setText(IDup);
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