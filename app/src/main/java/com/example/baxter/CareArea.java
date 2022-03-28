package com.example.baxter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CareArea extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private static final String[] paths = {"Care Area", "Pediatrics", "General Surgery", "Cardiovascular"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_area);

        spinner = findViewById(R.id.dropdown);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CareArea.this,
                android.R.layout.simple_spinner_item, paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                break;
            case 1:
                Intent intentc1 = new Intent(CareArea.this, PatientActivity1.class);
                intentc1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intentc1);
                break;
            case 2:
                Intent intentc2 = new Intent(CareArea.this, PatientActivity2.class);
                intentc2.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intentc2);
                // Whatever you want to happen when the third item gets selected
                break;
            case 3:
                Intent intentc3 = new Intent(CareArea.this, PatientActivity3.class);
                intentc3.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intentc3);
                // Whatever you want to happen when the third item gets selected
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }
}
