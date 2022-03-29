package com.example.baxter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PumpActivity1 extends SwipeActivity {
    RelativeLayout Patient1, Patient2, Patient3, Patient4, Patient5, Patient6, Patient7, Patient8;
    TextView pt1age,pt1name,pt1sex,pt2age,pt2name,pt2sex,pt5age,pt5name,pt5sex,pt3age,pt3name,pt3sex,
            pt4age,pt4name,pt4sex,pt6age,pt6name,pt6sex,pt7age,pt7name,pt7sex,pt8age,pt8name,pt8sex;

    Button fwdbutton, backbutton, homebutton;
    RelativeLayout pump1, pump2, pump3, pump4,
            pump5, pump6, pump7, pump8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pump_activity1);

        pump1 = findViewById(R.id.pump1);
        pump2 = findViewById(R.id.pump2);
        pump3 = findViewById(R.id.pump3);
        pump4 = findViewById(R.id.pump4);
        pump5 = findViewById(R.id.pump5);
        pump6 = findViewById(R.id.pump6);
        pump7 = findViewById(R.id.pump7);
        pump8 = findViewById(R.id.pump8);
        fwdbutton = findViewById(R.id.ForwardButton);
        homebutton = findViewById(R.id.HomeButton);
        Intent grabdata = getIntent();
        String name = grabdata.getStringExtra("name");
        String user = grabdata.getStringExtra("user");
        String pump = grabdata.getStringExtra("pump");
        String room = grabdata.getStringExtra("room");
        int pumps = Integer.parseInt(pump);
        if (pumps <= 8) {
            fwdbutton.setEnabled(false);
            fwdbutton.setVisibility(View.GONE);
        }

        pump1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity1.this, Pump1.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        pump2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity1.this, Pump2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        pump3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity1.this, Pump3.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        pump4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity1.this, Pump4.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        pump5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity1.this, Pump5.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        pump6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity1.this, Pump6.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        pump7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity1.this, Pump7.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        pump8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity1.this, Pump8.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        fwdbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity1.this, PumpActivity2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity1.this, PatientActivity1.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user")
                .child(name).child("pediatrics");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String pt1namestr = snapshot.child("1").child("name").getValue(String.class);
                String pt1agestr = snapshot.child("1").child("age").getValue(String.class);
                String pt1sexstr = snapshot.child("1").child("sex").getValue(String.class);
                pt1age.setText(pt1agestr);
                pt1name.setText(pt1namestr);
                pt1sex.setText(pt1sexstr);

                String pt2namestr = snapshot.child("2").child("name").getValue(String.class);
                String pt2agestr = snapshot.child("2").child("age").getValue(String.class);
                String pt2sexstr = snapshot.child("2").child("sex").getValue(String.class);
                pt2age.setText(pt2agestr);
                pt2name.setText(pt2namestr);
                pt2sex.setText(pt2sexstr);

                String pt3namestr = snapshot.child("3").child("name").getValue(String.class);
                String pt3agestr = snapshot.child("3").child("age").getValue(String.class);
                String pt3sexstr = snapshot.child("3").child("sex").getValue(String.class);
                pt3age.setText(pt3agestr);
                pt3name.setText(pt3namestr);
                pt3sex.setText(pt3sexstr);

                String pt4namestr = snapshot.child("4").child("name").getValue(String.class);
                String pt4agestr = snapshot.child("4").child("age").getValue(String.class);
                String pt4sexstr = snapshot.child("4").child("sex").getValue(String.class);
                pt4age.setText(pt4agestr);
                pt4name.setText(pt4namestr);
                pt4sex.setText(pt4sexstr);

                String pt5namestr = snapshot.child("5").child("name").getValue(String.class);
                String pt5agestr = snapshot.child("5").child("age").getValue(String.class);
                String pt5sexstr = snapshot.child("5").child("sex").getValue(String.class);
                pt5age.setText(pt5agestr);
                pt5name.setText(pt5namestr);
                pt5sex.setText(pt5sexstr);

                String pt6namestr = snapshot.child("6").child("name").getValue(String.class);
                String pt6agestr = snapshot.child("6").child("age").getValue(String.class);
                String pt6sexstr = snapshot.child("6").child("sex").getValue(String.class);
                pt6age.setText(pt6agestr);
                pt6name.setText(pt6namestr);
                pt6sex.setText(pt6sexstr);

                String pt7namestr = snapshot.child("7").child("name").getValue(String.class);
                String pt7agestr = snapshot.child("7").child("age").getValue(String.class);
                String pt7sexstr = snapshot.child("7").child("sex").getValue(String.class);
                pt7age.setText(pt7agestr);
                pt7name.setText(pt7namestr);
                pt7sex.setText(pt7sexstr);

                String pt8namestr = snapshot.child("8").child("name").getValue(String.class);
                String pt8agestr = snapshot.child("8").child("age").getValue(String.class);
                String pt8sexstr = snapshot.child("8").child("sex").getValue(String.class);
                pt8age.setText(pt8agestr);
                pt8name.setText(pt8namestr);
                pt8sex.setText(pt8sexstr);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onSwipeRight() {

    }

    @Override
    protected void onSwipeLeft() {
        Intent intent = new Intent(PumpActivity1.this, PumpActivity2.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);

    }
}
