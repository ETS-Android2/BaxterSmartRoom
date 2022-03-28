package com.example.baxter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class PatientActivity1 extends AppCompatActivity {
    RelativeLayout pt1, pt2, pt3, pt4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient1);
        pt1 = findViewById(R.id.pt1);
        pt2 = findViewById(R.id.pt2);
        pt3 = findViewById(R.id.pt3);
        pt4 = findViewById(R.id.pt4);
        pt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientActivity1.this, PumpActivity1.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
    }
}