package com.example.baxter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientActivity3 extends SwipeActivity {
    RelativeLayout Patient1, Patient2, Patient3, Patient4, Patient5, Patient6, Patient7, Patient8;
    TextView pt1number,pt2number,pt3number,pt4number,pt5number,pt6number,pt7number,pt8number, pt1age,pt1name,pt1sex,pt2age,pt2name,pt2sex,pt5age,pt5name,pt5sex,pt3age,pt3name,pt3sex,
            pt4age,pt4name,pt4sex,pt6age,pt6name,pt6sex,pt7age,pt7name,pt7sex,pt8age,pt8name,pt8sex, pt1ID, pt2ID, pt3ID, pt4ID, pt5ID, pt6ID, pt7ID, pt8ID;
    String patient, user, key, pump1,pump2,pump3,pump4,pump5,pump6,pump7,pump8;
    Button backbutton, homebutton;
    int patients, patientthisact, patientsdisplayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient3);
        Patient1 = findViewById(R.id.Patient1);
        Patient2 = findViewById(R.id.Patient2);
        Patient3 = findViewById(R.id.Patient3);
        Patient4 = findViewById(R.id.Patient4);
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersRef = rootRef.child("users");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    key = ds.getKey();
                    Log.d("TAG", key);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        Patient5 = findViewById(R.id.Patient5);
        Patient6 = findViewById(R.id.Patient6);
        Patient7 = findViewById(R.id.Patient7);
        Patient8 = findViewById(R.id.Patient8);
        pt1number=findViewById(R.id.pt1number);
        pt2number=findViewById(R.id.pt2number);
        pt3number=findViewById(R.id.pt3number);
        pt4number=findViewById(R.id.pt4number);
        pt5number=findViewById(R.id.pt5number);
        pt6number=findViewById(R.id.pt6number);
        pt7number=findViewById(R.id.pt7number);
        pt8number=findViewById(R.id.pt8number);
        pt1ID=findViewById(R.id.pt1ID);
        pt2ID=findViewById(R.id.pt2ID);
        pt3ID=findViewById(R.id.pt3ID);
        pt4ID=findViewById(R.id.pt4ID);
        pt5ID=findViewById(R.id.pt5ID);
        pt6ID=findViewById(R.id.pt6ID);
        pt7ID=findViewById(R.id.pt7ID);
        pt8ID=findViewById(R.id.pt8ID);
        pt1age = findViewById(R.id.pt1age);
        pt2age = findViewById(R.id.pt2age);
        pt3age = findViewById(R.id.pt3age);
        pt4age = findViewById(R.id.pt4age);
        pt5age = findViewById(R.id.pt5age);
        pt6age = findViewById(R.id.pt6age);
        pt7age = findViewById(R.id.pt7age);
        pt8age = findViewById(R.id.pt8age);
        pt1name = findViewById(R.id.pt1name);
        pt2name = findViewById(R.id.pt2name);
        pt3name = findViewById(R.id.pt3name);
        pt4name = findViewById(R.id.pt4name);
        pt5name = findViewById(R.id.pt5name);
        pt6name = findViewById(R.id.pt6name);
        pt7name = findViewById(R.id.pt7name);
        pt8name = findViewById(R.id.pt8name);
        pt1sex = findViewById(R.id.pt1sex);
        pt2sex = findViewById(R.id.pt2sex);
        pt3sex = findViewById(R.id.pt3sex);
        pt4sex = findViewById(R.id.pt4sex);
        pt5sex = findViewById(R.id.pt5sex);
        pt6sex = findViewById(R.id.pt6sex);
        pt7sex = findViewById(R.id.pt7sex);
        pt8sex = findViewById(R.id.pt8sex);
        homebutton = findViewById(R.id.HomeButton);
        backbutton = findViewById(R.id.BackButton);
        Intent grabdata = getIntent();
        patient = grabdata.getStringExtra("patients");
        user = grabdata.getStringExtra("user");
        key = grabdata.getStringExtra("key");
        patients = Integer.parseInt(patient);
        patientthisact = patients-24;
        if ((double) (patients)/8<=3 ) {
            backbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(PatientActivity3.this, PatientActivity2.class);
                    intent.putExtra("user", user);
                    intent.putExtra("key", key);
                    intent.putExtra("patients", patient);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    finish();

                }
            });
        } else {
            backbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(PatientActivity3.this, PatientActivity25.class);
                    intent.putExtra("user", user);
                    intent.putExtra("key", key);
                    intent.putExtra("patients", patient);
                    patientthisact = 8;
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    finish();
                }
            });

        }
        homebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(PatientActivity3.this, PatientActivity1.class);
                    intent.putExtra("user", user);
                    intent.putExtra("key", key);
                    intent.putExtra("patients", patient);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    finish();
                }
            });
        Patient6.setEnabled(false);
        Patient6.setVisibility(View.INVISIBLE);
        Patient7.setEnabled(false);
        Patient7.setVisibility(View.INVISIBLE);
        Patient8.setEnabled(false);
        Patient8.setVisibility(View.INVISIBLE);
        Patient5.setEnabled(false);
        Patient5.setVisibility(View.INVISIBLE);
        Patient4.setEnabled(false);
        Patient4.setVisibility(View.INVISIBLE);
        Patient3.setEnabled(false);
        Patient3.setVisibility(View.INVISIBLE);
        Patient2.setEnabled(false);
        Patient2.setVisibility(View.INVISIBLE);
        Patient1.setEnabled(false);
        Patient1.setVisibility(View.INVISIBLE);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users")
                .child(key).child(user).child("careArea");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (patientthisact >= 1) {
                    String pt1IDstr = snapshot.child("25").child("patientID").getValue().toString();
                    pt1ID.setText("Patient ID: "+pt1IDstr);
                    String pt1namestr = snapshot.child("25").child("name").getValue().toString();
                    String pt1numberstr = snapshot.child("25").child("roomnumber").getValue().toString();
                    pt1number.setText(pt1numberstr);
                    String pt1agestr = snapshot.child("25").child("age").getValue().toString();
                    String pt1sexstr = snapshot.child("25").child("sex").getValue().toString();
                    String maxSeverity = snapshot.child("25").child("maxSeverity").getValue().toString();
                    int max = Integer.parseInt(maxSeverity);
                    if (max == 1) {
                        Patient1.setBackgroundResource(R.color.yellow);
                    } else if (max == 2) {
                        Patient1.setBackgroundResource(R.color.orange);
                    } else if (max == 3) {
                        Patient1.setBackgroundResource(R.color.red);
                    } else {
                        Patient1.setBackgroundResource(R.color.green);
                    }
                    pump1 = snapshot.child("25").child("n_pumps").getValue().toString();
                    pt1age.setText(pt1agestr);
                    pt1name.setText(pt1namestr);
                    pt1sex.setText(pt1sexstr);
                    Patient1.setEnabled(true);
                    Patient1.setVisibility(View.VISIBLE);
                }
                if (patientthisact >= 2) {
                    String pt1IDstr = snapshot.child("26").child("patientID").getValue().toString();
                    pt2ID.setText("Patient ID: "+pt1IDstr);
                    String pt2namestr = snapshot.child("26").child("name").getValue().toString();
                    ;
                    String pt2agestr = snapshot.child("26").child("age").getValue().toString();
                    String pt2sexstr = snapshot.child("26").child("sex").getValue().toString();
                    String pt2numberstr = snapshot.child("26").child("roomnumber").getValue().toString();
                    pt2number.setText(pt2numberstr);
                    pt2age.setText(pt2agestr);
                    pt2name.setText(pt2namestr);
                    pump2 = snapshot.child("26").child("n_pumps").getValue().toString();
                    pt2sex.setText(pt2sexstr);
                    String maxSeverity = snapshot.child("26").child("maxSeverity").getValue().toString();
                    int max = Integer.parseInt(maxSeverity);
                    if (max == 1) {
                        Patient2.setBackgroundResource(R.color.yellow);
                    } else if (max == 2) {
                        Patient2.setBackgroundResource(R.color.orange);
                    } else if (max == 3) {
                        Patient2.setBackgroundResource(R.color.red);
                    } else {
                        Patient2.setBackgroundResource(R.color.green);
                    }
                    Patient2.setEnabled(true);
                    Patient2.setVisibility(View.VISIBLE);
                }
                if (patientthisact >= 3) {
                    String pt1IDstr = snapshot.child("27").child("patientID").getValue().toString();
                    pt3ID.setText("Patient ID: "+pt1IDstr);
                    String pt3namestr = snapshot.child("27").child("name").getValue().toString();
                    ;
                    String pt3agestr = snapshot.child("27").child("age").getValue().toString();
                    String pt3sexstr = snapshot.child("27").child("sex").getValue().toString();
                    String pt3numberstr = snapshot.child("27").child("roomnumber").getValue().toString();
                    pt3number.setText(pt3numberstr);
                    pt3age.setText(pt3agestr);
                    pt3name.setText(pt3namestr);
                    pt3sex.setText(pt3sexstr);
                    pump3 = snapshot.child("27").child("n_pumps").getValue().toString();
                    String maxSeverity = snapshot.child("27").child("maxSeverity").getValue().toString();
                    int max = Integer.parseInt(maxSeverity);
                    if (max == 1) {
                        Patient3.setBackgroundResource(R.color.yellow);
                    } else if (max == 2) {
                        Patient3.setBackgroundResource(R.color.orange);
                    } else if (max == 3) {
                        Patient3.setBackgroundResource(R.color.red);
                    } else {
                        Patient3.setBackgroundResource(R.color.green);
                    }
                    Patient3.setEnabled(true);
                    Patient3.setVisibility(View.VISIBLE);

                }
                if (patientthisact >= 4) {
                    String pt1IDstr = snapshot.child("28").child("patientID").getValue().toString();
                    pt4ID.setText("Patient ID: "+pt1IDstr);
                    String pt4namestr = snapshot.child("28").child("name").getValue().toString();
                    ;
                    String pt4agestr = snapshot.child("28").child("age").getValue().toString();
                    String pt4sexstr = snapshot.child("28").child("sex").getValue().toString();
                    String pt4numberstr = snapshot.child("28").child("roomnumber").getValue().toString();
                    pt4number.setText(pt4numberstr);
                    pt4age.setText(pt4agestr);
                    pt4name.setText(pt4namestr);
                    pt4sex.setText(pt4sexstr);
                    pump4 = snapshot.child("28").child("n_pumps").getValue().toString();
                    String maxSeverity = snapshot.child("28").child("maxSeverity").getValue().toString();
                    int max = Integer.parseInt(maxSeverity);
                    if (max == 1) {
                        Patient4.setBackgroundResource(R.color.yellow);
                    } else if (max == 2) {
                        Patient4.setBackgroundResource(R.color.orange);
                    } else if (max == 3) {
                        Patient4.setBackgroundResource(R.color.red);
                    } else {
                        Patient4.setBackgroundResource(R.color.green);
                    }
                    Patient4.setEnabled(true);
                    Patient4.setVisibility(View.VISIBLE);
                }
                if (patientthisact >= 5) {
                    String pt1IDstr = snapshot.child("29").child("patientID").getValue().toString();
                    pt5ID.setText("Patient ID: "+pt1IDstr);
                    String pt5namestr = snapshot.child("29").child("name").getValue().toString();
                    ;
                    String pt5agestr = snapshot.child("29").child("age").getValue().toString();
                    String pt5sexstr = snapshot.child("29").child("sex").getValue().toString();
                    String pt5numberstr = snapshot.child("29").child("roomnumber").getValue().toString();
                    pt5number.setText(pt5numberstr);
                    pt5age.setText(pt5agestr);
                    pt5name.setText(pt5namestr);
                    pt5sex.setText(pt5sexstr);
                    pump5 = snapshot.child("29").child("n_pumps").getValue().toString();
                    Patient5.setEnabled(true);
                    Patient5.setVisibility(View.VISIBLE);
                    String maxSeverity = snapshot.child("29").child("maxSeverity").getValue().toString();
                    int max = Integer.parseInt(maxSeverity);
                    if (max == 1) {
                        Patient5.setBackgroundResource(R.color.yellow);
                    } else if (max == 2) {
                        Patient5.setBackgroundResource(R.color.orange);
                    } else if (max == 3) {
                        Patient5.setBackgroundResource(R.color.red);
                    } else {
                        Patient5.setBackgroundResource(R.color.green);
                    }
                }
                if (patientthisact >= 6) {
                    String pt1IDstr = snapshot.child("30").child("patientID").getValue().toString();
                    pt6ID.setText("Patient ID: "+pt1IDstr);
                    String pt6namestr = snapshot.child("30").child("name").getValue().toString();
                    ;
                    String pt6agestr = snapshot.child("30").child("age").getValue().toString();
                    String pt6sexstr = snapshot.child("30").child("sex").getValue().toString();
                    String pt6numberstr = snapshot.child("30").child("roomnumber").getValue().toString();
                    pt6number.setText(pt6numberstr);
                    pt6age.setText(pt6agestr);
                    pt6name.setText(pt6namestr);
                    pt6sex.setText(pt6sexstr);
                    pump6 = snapshot.child("30").child("n_pumps").getValue().toString();
                    Patient6.setEnabled(true);
                    Patient6.setVisibility(View.VISIBLE);
                    String maxSeverity = snapshot.child("30").child("maxSeverity").getValue().toString();
                    int max = Integer.parseInt(maxSeverity);
                    if (max == 1) {
                        Patient6.setBackgroundResource(R.color.yellow);
                    } else if (max == 2) {
                        Patient6.setBackgroundResource(R.color.orange);
                    } else if (max == 3) {
                        Patient6.setBackgroundResource(R.color.red);
                    } else {
                        Patient6.setBackgroundResource(R.color.green);
                    }
                }
                if (patientthisact >= 7) {
                    String pt1IDstr = snapshot.child("31").child("patientID").getValue().toString();
                    pt7ID.setText("Patient ID: "+pt1IDstr);
                    String pt7namestr = snapshot.child("31").child("name").getValue().toString();
                    ;
                    String pt7agestr = snapshot.child("31").child("age").getValue().toString();
                    String pt7sexstr = snapshot.child("31").child("sex").getValue().toString();
                    String pt7numberstr = snapshot.child("31").child("roomnumber").getValue().toString();
                    pt7number.setText(pt7numberstr);
                    pt7age.setText(pt7agestr);
                    pt7name.setText(pt7namestr);
                    pump7 = snapshot.child("31").child("n_pumps").getValue().toString();
                    pt7sex.setText(pt7sexstr);
                    Patient7.setEnabled(true);
                    Patient7.setVisibility(View.VISIBLE);
                    String maxSeverity = snapshot.child("31").child("maxSeverity").getValue().toString();
                    int max = Integer.parseInt(maxSeverity);
                    if (max == 1) {
                        Patient7.setBackgroundResource(R.color.yellow);
                    } else if (max == 2) {
                        Patient7.setBackgroundResource(R.color.orange);
                    } else if (max == 3) {
                        Patient7.setBackgroundResource(R.color.red);
                    } else {
                        Patient7.setBackgroundResource(R.color.green);
                    }

                }
                if (patientthisact == 8) {
                    String pt1IDstr = snapshot.child("32").child("patientID").getValue().toString();
                    pt8ID.setText("Patient ID: "+pt1IDstr);
                    String pt8namestr = snapshot.child("32").child("name").getValue().toString();
                    ;
                    String pt8agestr = snapshot.child("32").child("age").getValue().toString();
                    String pt8sexstr = snapshot.child("32").child("sex").getValue().toString();
                    String pt8numberstr = snapshot.child("32").child("roomnumber").getValue().toString();
                    pt8number.setText(pt8numberstr);
                    pt8age.setText(pt8agestr);
                    pump8 = snapshot.child("32").child("n_pumps").getValue().toString();
                    Patient8.setEnabled(true);
                    Patient8.setVisibility(View.VISIBLE);
                    pt8name.setText(pt8namestr);
                    pt8sex.setText(pt8sexstr);
                    String maxSeverity = snapshot.child("32").child("maxSeverity").getValue().toString();
                    int max = Integer.parseInt(maxSeverity);
                    if (max == 1) {
                        Patient8.setBackgroundResource(R.color.yellow);
                    } else if (max == 2) {
                        Patient8.setBackgroundResource(R.color.orange);
                    } else if (max == 3) {
                        Patient8.setBackgroundResource(R.color.red);
                    } else {
                        Patient8.setBackgroundResource(R.color.green);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        clickListen();
    }

    @Override
    protected void onSwipeRight() {
        if ((double) (patients)/8<=3 ) {

            Intent intent = new Intent(PatientActivity3.this, PatientActivity2.class);
            intent.putExtra("user", user);
            intent.putExtra("key", key);
            intent.putExtra("patients", patient);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();

        } else if ((patients)/8<=4) {
            Intent intent = new Intent(PatientActivity3.this, PatientActivity25.class);
            intent.putExtra("user", user);
            intent.putExtra("key", key);
            intent.putExtra("patients", patient);
            patientthisact = 8;
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onSwipeLeft() {

    }

    private void clickListen() {
        Patient1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.example.baxter.PatientActivity3.this, PumpActivity1.class);
                intent.putExtra("user", user);
                intent.putExtra("ptindex", "25");
                intent.putExtra("key", key);
                intent.putExtra("Pump", pump1);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();

            }
        });
        Patient2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.example.baxter.PatientActivity3.this, PumpActivity1.class);
                intent.putExtra("user", user);
                intent.putExtra("key", key);
                intent.putExtra("Pump", pump2);
                intent.putExtra("ptindex", "26");
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();

            }
        });
        Patient3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.example.baxter.PatientActivity3.this, PumpActivity1.class);
                intent.putExtra("user", user);
                intent.putExtra("ptindex", "27");
                intent.putExtra("key", key);
                intent.putExtra("Pump", pump3);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();

            }
        });
        Patient4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.example.baxter.PatientActivity3.this, PumpActivity1.class);
                intent.putExtra("user", user);
                intent.putExtra("key", key);
                intent.putExtra("ptindex", "28");
                intent.putExtra("Pump", pump4);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();

            }
        });
        Patient5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(com.example.baxter.PatientActivity3.this, PumpActivity1.class);

                intent.putExtra("user", user);
                intent.putExtra("key", key);
                intent.putExtra("Pump", pump5);
                intent.putExtra("ptindex", "29");
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();

            }
        });
        Patient6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.example.baxter.PatientActivity3.this, PumpActivity1.class);

                intent.putExtra("user", user);
                intent.putExtra("key", key);
                intent.putExtra("Pump", pump6);
                intent.putExtra("ptindex", "30");
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();

            }
        });
        Patient7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.example.baxter.PatientActivity3.this, PumpActivity1.class);

                intent.putExtra("user", user);
                intent.putExtra("key", key);
                intent.putExtra("Pump", pump7);
                intent.putExtra("ptindex", "31");
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();

            }
        });
        Patient8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.example.baxter.PatientActivity3.this, PumpActivity1.class);
                intent.putExtra("user", user);
                intent.putExtra("key", key);
                intent.putExtra("ptindex", "32");
                intent.putExtra("Pump", pump8);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });
    }
}
