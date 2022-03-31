package com.example.baxter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Pump1 extends AppCompatActivity {
    String patient, user, key, ptindex;
    Button homebutton, backbutton;
    RelativeLayout Layout1;
    TextView drugg, ratee, startVoll, IDD, alarmtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pump1);
        homebutton = findViewById(R.id.HomeButton);
        backbutton = findViewById(R.id.BackButton);
        drugg = findViewById(R.id.drug);
        ratee = findViewById(R.id.rate);
        startVoll = findViewById(R.id.startVolume);
        IDD = findViewById(R.id.pumpID);
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
                intent.putExtra("ptindex", ptindex);
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
        String user = grabdata.getStringExtra("user");
        ptindex = grabdata.getStringExtra("ptindex");
        String alarm = grabdata.getStringExtra("alarm");
        String key = grabdata.getStringExtra("key");
        String alarm_severity = grabdata.getStringExtra("alarm_severity");
        String drug = grabdata.getStringExtra("drug");
        String rate = grabdata.getStringExtra("rate");
        String startVol = grabdata.getStringExtra("startVol");
        String ID = grabdata.getStringExtra("ID");
        String alarm_text = grabdata.getStringExtra("alarm_text");
        int colors8 = Integer.parseInt(alarm_severity);
        if (colors8 == 1) {
            Layout1.setBackgroundResource(R.color.yellow);
        } else if (colors8 ==2){
            Layout1.setBackgroundResource(R.color.orange);
        } else if (colors8 == 3){
            Layout1.setBackgroundResource(R.color.red);
        } else {
            Layout1.setBackgroundResource(R.color.green);
        }
        drugg.setText(drug);
        ratee.setText(rate);
        startVoll.setText(startVol);
        IDD.setText(ID);
        alarmtext.setText(alarm_text);
    }
}