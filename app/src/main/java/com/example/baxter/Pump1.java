package com.example.baxter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Pump1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pump1);
        data();

    }

    private void data() {
        Intent grabdata = getIntent();
        String user = grabdata.getStringExtra("user");
        String alarm = grabdata.getStringExtra("alarm");
        String key = grabdata.getStringExtra("key");
        String alarm_severity = grabdata.getStringExtra("alarm_severity");
        String drug = grabdata.getStringExtra("drug");
        String rate = grabdata.getStringExtra("rate");
        String startVol = grabdata.getStringExtra("startVol");
        String ID = grabdata.getStringExtra("ID");
        String alarm_text = grabdata.getStringExtra("alarm_text");
    }
}