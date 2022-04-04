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

public class PatientActivity25 extends SwipeActivity {
    RelativeLayout Patient1, Patient2, Patient3, Patient4, Patient5, Patient6, Patient7, Patient8;
    TextView pt1age, pt1name, pt1sex, pt2age, pt2name, pt2sex, pt5age, pt5name, pt5sex, pt3age, pt3name, pt3sex,
            pt4age, pt4name, pt4sex, pt6age, pt6name, pt6sex, pt7age, pt7name, pt7sex, pt8age, pt8name, pt8sex;
    String patient, user, key, pump1, pump2, pump3, pump4, pump5, pump6, pump7, pump8;
    Button fwdbutton, backbutton, homebutton;
    int patients, patientthisact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient25);
        Patient1 = findViewById(R.id.Patient1);
        Patient2 = findViewById(R.id.Patient2);
        Patient3 = findViewById(R.id.Patient3);
        Patient4 = findViewById(R.id.Patient4);
        Patient5 = findViewById(R.id.Patient5);
        Patient6 = findViewById(R.id.Patient6);
        Patient7 = findViewById(R.id.Patient7);
        Patient8 = findViewById(R.id.Patient8);
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
        fwdbutton = findViewById(R.id.ForwardButton);
        homebutton = findViewById(R.id.HomeButton);
        backbutton = findViewById(R.id.BackButton);
        Intent grabdata = getIntent();
        patient = grabdata.getStringExtra("patients");
        user = grabdata.getStringExtra("user");
        key = grabdata.getStringExtra("key");
        patients = Integer.parseInt(patient);
        if ((double)(patients)/8<=3 ) {
            fwdbutton.setEnabled(false);
            fwdbutton.setVisibility(View.GONE);
            patientthisact = patients-16;
        } else if ((double)(patients)/8<=4){
            patientthisact = 8;
            fwdbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(PatientActivity25.this, PatientActivity3.class);
                    intent.putExtra("user", user);
                    intent.putExtra("key", key);
                    intent.putExtra("patients", patient);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    finish();
                }
            });

        }


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
                    String pt1namestr = snapshot.child("17").child("name").getValue().toString();
                    ;
                    String pt1agestr = snapshot.child("17").child("age").getValue().toString();
                    String pt1sexstr = snapshot.child("17").child("sex").getValue().toString();
                    String maxSeverity = snapshot.child("17").child("maxSeverity").getValue().toString();
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
                    pump1 = snapshot.child("17").child("n_pumps").getValue().toString();
                    pt1age.setText(pt1agestr);
                    pt1name.setText(pt1namestr);
                    pt1sex.setText(pt1sexstr);
                    Patient1.setEnabled(true);
                    Patient1.setVisibility(View.VISIBLE);
                }
                if (patientthisact >= 2) {
                    String pt2namestr = snapshot.child("18").child("name").getValue().toString();
                    ;
                    String pt2agestr = snapshot.child("18").child("age").getValue().toString();
                    String pt2sexstr = snapshot.child("18").child("sex").getValue().toString();
                    ;
                    pt2age.setText(pt2agestr);
                    pt2name.setText(pt2namestr);
                    pump2 = snapshot.child("18").child("n_pumps").getValue().toString();
                    pt2sex.setText(pt2sexstr);
                    String maxSeverity = snapshot.child("18").child("maxSeverity").getValue().toString();
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
                    String pt3namestr = snapshot.child("19").child("name").getValue().toString();
                    ;
                    String pt3agestr = snapshot.child("19").child("age").getValue().toString();
                    String pt3sexstr = snapshot.child("19").child("sex").getValue().toString();
                    ;
                    pt3age.setText(pt3agestr);
                    pt3name.setText(pt3namestr);
                    pt3sex.setText(pt3sexstr);
                    pump3 = snapshot.child("19").child("n_pumps").getValue().toString();
                    String maxSeverity = snapshot.child("19").child("maxSeverity").getValue().toString();
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
                    String pt4namestr = snapshot.child("20").child("name").getValue().toString();
                    ;
                    String pt4agestr = snapshot.child("20").child("age").getValue().toString();
                    String pt4sexstr = snapshot.child("20").child("sex").getValue().toString();
                    ;
                    pt4age.setText(pt4agestr);
                    pt4name.setText(pt4namestr);
                    pt4sex.setText(pt4sexstr);
                    pump4 = snapshot.child("20").child("n_pumps").getValue().toString();
                    String maxSeverity = snapshot.child("20").child("maxSeverity").getValue().toString();
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
                    String pt5namestr = snapshot.child("21").child("name").getValue().toString();
                    ;
                    String pt5agestr = snapshot.child("21").child("age").getValue().toString();
                    String pt5sexstr = snapshot.child("21").child("sex").getValue().toString();
                    ;
                    pt5age.setText(pt5agestr);
                    pt5name.setText(pt5namestr);
                    pt5sex.setText(pt5sexstr);
                    pump5 = snapshot.child("21").child("n_pumps").getValue().toString();
                    Patient5.setEnabled(true);
                    Patient5.setVisibility(View.VISIBLE);
                    String maxSeverity = snapshot.child("21").child("maxSeverity").getValue().toString();
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
                    String pt6namestr = snapshot.child("22").child("name").getValue().toString();
                    ;
                    String pt6agestr = snapshot.child("22").child("age").getValue().toString();
                    String pt6sexstr = snapshot.child("22").child("sex").getValue().toString();
                    ;
                    pt6age.setText(pt6agestr);
                    pt6name.setText(pt6namestr);
                    pt6sex.setText(pt6sexstr);
                    pump6 = snapshot.child("22").child("n_pumps").getValue().toString();
                    Patient6.setEnabled(true);
                    Patient6.setVisibility(View.VISIBLE);
                    String maxSeverity = snapshot.child("22").child("maxSeverity").getValue().toString();
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
                    String pt7namestr = snapshot.child("23").child("name").getValue().toString();
                    ;
                    String pt7agestr = snapshot.child("23").child("age").getValue().toString();
                    String pt7sexstr = snapshot.child("23").child("sex").getValue().toString();
                    ;
                    pt7age.setText(pt7agestr);
                    pt7name.setText(pt7namestr);
                    pump7 = snapshot.child("23").child("n_pumps").getValue().toString();
                    pt7sex.setText(pt7sexstr);
                    Patient7.setEnabled(true);
                    Patient7.setVisibility(View.VISIBLE);
                    String maxSeverity = snapshot.child("23").child("maxSeverity").getValue().toString();
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
                if (patientthisact >= 8) {
                    String pt8namestr = snapshot.child("24").child("name").getValue().toString();
                    ;
                    String pt8agestr = snapshot.child("24").child("age").getValue().toString();
                    String pt8sexstr = snapshot.child("24").child("sex").getValue().toString();
                    ;
                    pt8age.setText(pt8agestr);
                    pump8 = snapshot.child("24").child("n_pumps").getValue().toString();
                    Patient8.setEnabled(true);
                    Patient8.setVisibility(View.VISIBLE);
                    pt8name.setText(pt8namestr);
                    pt8sex.setText(pt8sexstr);
                    String maxSeverity = snapshot.child("24").child("maxSeverity").getValue().toString();
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
           Intent intent = new Intent(PatientActivity25.this, PatientActivity2.class);
                    intent.putExtra("user", user);
                    intent.putExtra("key", key);
                    intent.putExtra("patients", patient);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    finish();

    }

    @Override
    protected void onSwipeLeft() {
            Intent intent = new Intent(PatientActivity25.this, PatientActivity3.class);
            intent.putExtra("user", user);
            intent.putExtra("key", key);
            intent.putExtra("patients", patient);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();

    }


    private void clickListen() {
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientActivity25.this, PatientActivity2.class);
                intent.putExtra("user", user);
                intent.putExtra("key", key);
                intent.putExtra("patients", patient);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();

            }
        });
        Patient1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.example.baxter.PatientActivity25.this, PumpActivity1.class);
                intent.putExtra("user", user);
                intent.putExtra("ptindex", "17");
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
                Intent intent = new Intent(com.example.baxter.PatientActivity25.this, PumpActivity1.class);
                intent.putExtra("user", user);
                intent.putExtra("key", key);
                intent.putExtra("Pump", pump2);
                intent.putExtra("ptindex", "18");
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();

            }
        });
        Patient3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.example.baxter.PatientActivity25.this, PumpActivity1.class);
                intent.putExtra("user", user);
                intent.putExtra("ptindex", "19");
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
                Intent intent = new Intent(com.example.baxter.PatientActivity25.this, PumpActivity1.class);
                intent.putExtra("user", user);
                intent.putExtra("key", key);
                intent.putExtra("ptindex", "20");
                intent.putExtra("Pump", pump4);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();

            }
        });
        Patient5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(com.example.baxter.PatientActivity25.this, PumpActivity1.class);

                intent.putExtra("user", user);
                intent.putExtra("key", key);
                intent.putExtra("Pump", pump5);
                intent.putExtra("ptindex", "21");
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();

            }
        });
        Patient6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.example.baxter.PatientActivity25.this, PumpActivity1.class);

                intent.putExtra("user", user);
                intent.putExtra("key", key);
                intent.putExtra("Pump", pump6);
                intent.putExtra("ptindex", "22");
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();

            }
        });
        Patient7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.example.baxter.PatientActivity25.this, PumpActivity1.class);

                intent.putExtra("user", user);
                intent.putExtra("key", key);
                intent.putExtra("Pump", pump7);
                intent.putExtra("ptindex", "23");
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();

            }
        });
        Patient8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.example.baxter.PatientActivity25.this, PumpActivity1.class);
                intent.putExtra("user", user);
                intent.putExtra("key", key);
                intent.putExtra("ptindex", 8);
                intent.putExtra("Pump", pump8);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientActivity25.this, PatientActivity1.class);
                intent.putExtra("user", user);
                intent.putExtra("key", key);
                intent.putExtra("patients", patient);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });
    };
}
