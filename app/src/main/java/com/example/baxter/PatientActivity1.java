package com.example.baxter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientActivity1 extends AppCompatActivity {
    RelativeLayout Patient1, Patient2, Patient3, Patient4, Patient5, Patient6, Patient7, Patient8;
    TextView pt1age,pt1name,pt1sex,pt2age,pt2name,pt2sex,pt5age,pt5name,pt5sex,pt3age,pt3name,pt3sex,
            pt4age,pt4name,pt4sex,pt6age,pt6name,pt6sex,pt7age,pt7name,pt7sex,pt8age,pt8name,pt8sex;
    String patient, user, key, pump1,pump2,pump3,pump4,pump5,pump6,pump7,pump8;
    Button fwdbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient1);
        Patient1 = findViewById(R.id.Patient1);
        Patient2 = findViewById(R.id.Patient2);
        Patient3 = findViewById(R.id.Patient3);
        Patient4 = findViewById(R.id.Patient4);
        Patient5 = findViewById(R.id.Patient5);
        Patient6 = findViewById(R.id.Patient6);
        Patient7 = findViewById(R.id.Patient7);
        Patient8 = findViewById(R.id.Patient8);
        pt1age=findViewById(R.id.pt1age);
        pt2age=findViewById(R.id.pt2age);
        pt3age=findViewById(R.id.pt3age);
        pt4age=findViewById(R.id.pt4age);
        pt5age=findViewById(R.id.pt5age);
        pt6age=findViewById(R.id.pt6age);
        pt7age=findViewById(R.id.pt7age);
        pt8age=findViewById(R.id.pt8age);
        pt1name=findViewById(R.id.pt1name);
        pt2name=findViewById(R.id.pt2name);
        pt3name=findViewById(R.id.pt3name);
        pt4name=findViewById(R.id.pt4name);
        pt5name=findViewById(R.id.pt5name);
        pt6name=findViewById(R.id.pt6name);
        pt7name=findViewById(R.id.pt7name);
        pt8name=findViewById(R.id.pt8name);
        pt1sex=findViewById(R.id.pt1sex);
        pt2sex=findViewById(R.id.pt2sex);
        pt3sex=findViewById(R.id.pt3sex);
        pt4sex=findViewById(R.id.pt4sex);
        pt5sex=findViewById(R.id.pt5sex);
        pt6sex=findViewById(R.id.pt6sex);
        pt7sex=findViewById(R.id.pt7sex);
        pt8sex=findViewById(R.id.pt8sex);
        fwdbutton = findViewById(R.id.ForwardButton);
        Intent grabdata = getIntent();
        patient = grabdata.getStringExtra("patients");
        user = grabdata.getStringExtra("user");
        key = grabdata.getStringExtra("key");
        int patients = Integer.parseInt(patient);
        if (patients <= 8) {
            fwdbutton.setEnabled(false);
            fwdbutton.setVisibility(View.GONE);
        }
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users")
                .child(key).child(user).child("careArea");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (patients >=1){
                    String pt1namestr = snapshot.child("1").child("name").getValue(String.class);
                    String pt1agestr = snapshot.child("1").child("age").getValue().toString();
                    String pt1sexstr = snapshot.child("1").child("sex").getValue(String.class);
                    pump1 = snapshot.child("1").child("n_pumps").getValue().toString();
                    pt1age.setText(pt1agestr);
                    pt1name.setText(pt1namestr);
                    pt1sex.setText(pt1sexstr);
                }
                if (patients >=2){
                    String pt2namestr = snapshot.child("2").child("name").getValue(String.class);
                    String pt2agestr = snapshot.child("2").child("age").getValue().toString();
                    String pt2sexstr = snapshot.child("2").child("sex").getValue(String.class);
                    pt2age.setText(pt2agestr);
                    pt2name.setText(pt2namestr);
                    pump2 = snapshot.child("2").child("n_pumps").getValue().toString();
                    pt2sex.setText(pt2sexstr);
                }
                if (patients >=3){
                    String pt3namestr = snapshot.child("3").child("name").getValue(String.class);
                    String pt3agestr = snapshot.child("3").child("age").getValue().toString();
                    String pt3sexstr = snapshot.child("3").child("sex").getValue(String.class);
                    pt3age.setText(pt3agestr);
                    pt3name.setText(pt3namestr);
                    pt3sex.setText(pt3sexstr);
                    pump3 = snapshot.child("3").child("n_pumps").getValue().toString();
                }
                if (patients >=4){
                    String pt4namestr = snapshot.child("4").child("name").getValue(String.class);
                    String pt4agestr = snapshot.child("4").child("age").getValue().toString();
                    String pt4sexstr = snapshot.child("4").child("sex").getValue(String.class);
                    pt4age.setText(pt4agestr);
                    pt4name.setText(pt4namestr);
                    pt4sex.setText(pt4sexstr);
                    pump4 = snapshot.child("4").child("n_pumps").getValue().toString();
                }
                if (patients >=5){
                    String pt5namestr = snapshot.child("5").child("name").getValue(String.class);
                    String pt5agestr = snapshot.child("5").child("age").getValue().toString();
                    String pt5sexstr = snapshot.child("5").child("sex").getValue(String.class);
                    pt5age.setText(pt5agestr);
                    pt5name.setText(pt5namestr);
                    pt5sex.setText(pt5sexstr);
                    pump5 = snapshot.child("5").child("n_pumps").getValue().toString();
                }
                if (patients >=6){
                    String pt6namestr = snapshot.child("6").child("name").getValue(String.class);
                    String pt6agestr = snapshot.child("6").child("age").getValue().toString();
                    String pt6sexstr = snapshot.child("6").child("sex").getValue(String.class);
                    pt6age.setText(pt6agestr);
                    pt6name.setText(pt6namestr);
                    pt6sex.setText(pt6sexstr);
                    pump6 = snapshot.child("6").child("n_pumps").getValue().toString();
                }
                if (patients >=7){
                    String pt7namestr = snapshot.child("7").child("name").getValue(String.class);
                    String pt7agestr = snapshot.child("7").child("age").getValue().toString();
                    String pt7sexstr = snapshot.child("7").child("sex").getValue(String.class);
                    pt7age.setText(pt7agestr);
                    pt7name.setText(pt7namestr);
                    pump7 = snapshot.child("7").child("n_pumps").getValue().toString();
                    pt7sex.setText(pt7sexstr);
                }
                if (patients ==8){
                String pt8namestr = snapshot.child("8").child("name").getValue(String.class);
                String pt8agestr = snapshot.child("8").child("age").getValue().toString();
                String pt8sexstr = snapshot.child("8").child("sex").getValue(String.class);
                pt8age.setText(pt8agestr);
                pump8 = snapshot.child("8").child("n_pumps").getValue().toString();
                pt8name.setText(pt8namestr);
                pt8sex.setText(pt8sexstr);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        clickListen();
    }

    private void clickListen() {
        fwdbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientActivity1.this, PatientActivity2.class);

                intent.putExtra("user",user);
                intent.putExtra("key",key);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();

            }
        });
        Patient1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientActivity1.this, PumpActivity1.class);
                intent.putExtra("user",user);
                intent.putExtra("ptindex","1");
                intent.putExtra("key",key);
                intent.putExtra("Pump",pump1);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();

            }
        });
        Patient2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientActivity1.this, PumpActivity1.class);
                intent.putExtra("user",user);
                intent.putExtra("key",key);
                intent.putExtra("Pump",pump2);
                intent.putExtra("ptindex","2");
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();

            }
        });
        Patient3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientActivity1.this, PumpActivity1.class);
                intent.putExtra("user",user);
                intent.putExtra("ptindex","3");
                intent.putExtra("key",key);
                intent.putExtra("Pump",pump3);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();

            }
        });
        Patient4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientActivity1.this, PumpActivity1.class);
                intent.putExtra("user",user);
                intent.putExtra("key",key);
                intent.putExtra("ptindex","4");
                intent.putExtra("Pump",pump4);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent); finish();

            }
        });
        Patient5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(PatientActivity1.this, PumpActivity1.class);

                intent.putExtra("user",user);
                intent.putExtra("key",key);
                intent.putExtra("Pump",pump5);
                intent.putExtra("ptindex","5");
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent); finish();

            }
        });
        Patient6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientActivity1.this, PumpActivity1.class);

                intent.putExtra("user", user);
                intent.putExtra("key", key);
                intent.putExtra("Pump", pump6);
                intent.putExtra("ptindex", "6");
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();

            }
        });
        Patient7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientActivity1.this, PumpActivity1.class);

                intent.putExtra("user",user);
                intent.putExtra("key",key);
                intent.putExtra("Pump",pump7);
                intent.putExtra("ptindex","7");
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent); finish();

            }
        });
        Patient8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientActivity1.this, PumpActivity1.class);

                intent.putExtra("user",user);
                intent.putExtra("key",key);
                intent.putExtra("ptindex",8);
                intent.putExtra("Pump",pump8);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();

            }
        });
    }
}