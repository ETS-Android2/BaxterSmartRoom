package com.example.baxter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PumpActivity1 extends SwipeActivity {
    RelativeLayout Pump1, Pump2, Pump3, Pump4, Pump5, Pump6, Pump7, Pump8;
    TextView Pump1rate,Pump1drug,Pump1startVolume,Pump2rate,Pump2drug,Pump2startVolume,Pump5rate,Pump5drug,Pump5startVolume,Pump3rate,Pump3drug,Pump3startVolume,
            Pump4rate,Pump4drug,Pump4startVolume,Pump6rate,Pump6drug,Pump6startVolume,Pump7rate,Pump7drug,Pump7startVolume,Pump8rate,Pump8drug,Pump8startVolume;

    Button fwdbutton, homebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pump_activity1);

        Pump1 = findViewById(R.id.Pump1);
        Pump2 = findViewById(R.id.Pump2);
        Pump3 = findViewById(R.id.Pump3);
        Pump4 = findViewById(R.id.Pump4);
        Pump5 = findViewById(R.id.Pump5);
        Pump6 = findViewById(R.id.Pump6);
        Pump7 = findViewById(R.id.Pump7);
        Pump8 = findViewById(R.id.Pump8);
        fwdbutton = findViewById(R.id.ForwardButton);
        homebutton = findViewById(R.id.HomeButton);
        Intent grabdata = getIntent();
        String user = grabdata.getStringExtra("user");
        String Pump = grabdata.getStringExtra("Pump");
        String key = grabdata.getStringExtra("key");
        int Pumps = Integer.parseInt(Pump);
        if (Pumps <= 8) {
            fwdbutton.setEnabled(false);
            fwdbutton.setVisibility(View.GONE);
        }

        Pump1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity1.this, Pump1.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        Pump2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity1.this, Pump2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        Pump3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity1.this, Pump3.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        Pump4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity1.this, Pump4.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        Pump5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity1.this, Pump5.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        Pump6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity1.this, Pump6.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        Pump7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity1.this, Pump7.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        Pump8.setOnClickListener(new View.OnClickListener() {
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
                Intent intent = new Intent(PumpActivity1.this, PumpActivity1.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users")
                .child(key).child(user).child("careArea").child("1").child("pumps");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //setting text
                String Pump1drugstr = snapshot.child("1").child("drug").getValue(String.class);
                String Pump1ratestr = snapshot.child("1").child("currentRate").getValue(String.class);
                String Pump1startVolumestr = snapshot.child("1").child("startVolume").getValue(String.class);
                Pump1rate.setText(Pump1ratestr);
                Pump1drug.setText(Pump1drugstr);
                Pump1startVolume.setText(Pump1startVolumestr);

                String Pump2drugstr = snapshot.child("2").child("drug").getValue(String.class);
                String Pump2ratestr = snapshot.child("2").child("currentRate").getValue(String.class);
                String Pump2startVolumestr = snapshot.child("2").child("startVolume").getValue(String.class);
                Pump2rate.setText(Pump2ratestr);
                Pump2drug.setText(Pump2drugstr);
                Pump2startVolume.setText(Pump2startVolumestr);

                String Pump3drugstr = snapshot.child("3").child("drug").getValue(String.class);
                String Pump3ratestr = snapshot.child("3").child("currentRate").getValue(String.class);
                String Pump3startVolumestr = snapshot.child("3").child("startVolume").getValue(String.class);
                Pump3rate.setText(Pump3ratestr);
                Pump3drug.setText(Pump3drugstr);
                Pump3startVolume.setText(Pump3startVolumestr);

                String Pump4drugstr = snapshot.child("4").child("drug").getValue(String.class);
                String Pump4ratestr = snapshot.child("4").child("currentRate").getValue(String.class);
                String Pump4startVolumestr = snapshot.child("4").child("startVolume").getValue(String.class);
                Pump4rate.setText(Pump4ratestr);
                Pump4drug.setText(Pump4drugstr);
                Pump4startVolume.setText(Pump4startVolumestr);

                String Pump5drugstr = snapshot.child("5").child("drug").getValue(String.class);
                String Pump5ratestr = snapshot.child("5").child("currentRate").getValue(String.class);
                String Pump5startVolumestr = snapshot.child("5").child("startVolume").getValue(String.class);
                Pump5rate.setText(Pump5ratestr);
                Pump5drug.setText(Pump5drugstr);
                Pump5startVolume.setText(Pump5startVolumestr);

                String Pump6drugstr = snapshot.child("6").child("drug").getValue(String.class);
                String Pump6ratestr = snapshot.child("6").child("currentRate").getValue(String.class);
                String Pump6startVolumestr = snapshot.child("6").child("startVolume").getValue(String.class);
                Pump6rate.setText(Pump6ratestr);
                Pump6drug.setText(Pump6drugstr);
                Pump6startVolume.setText(Pump6startVolumestr);

                String Pump7drugstr = snapshot.child("7").child("drug").getValue(String.class);
                String Pump7ratestr = snapshot.child("7").child("currentRate").getValue(String.class);
                String Pump7startVolumestr = snapshot.child("7").child("startVolume").getValue(String.class);
                Pump7rate.setText(Pump7ratestr);
                Pump7drug.setText(Pump7drugstr);
                Pump7startVolume.setText(Pump7startVolumestr);

                String Pump8drugstr = snapshot.child("8").child("drug").getValue(String.class);
                String Pump8ratestr = snapshot.child("8").child("currentRate").getValue(String.class);
                String Pump8startVolumestr = snapshot.child("8").child("startVolume").getValue(String.class);
                Pump8rate.setText(Pump8ratestr);
                Pump8drug.setText(Pump8drugstr);
                Pump8startVolume.setText(Pump8startVolumestr);

                //setting colors
                String color1 = snapshot.child("1").child("alarm_severity").getValue(String.class);
                int colors1 = Integer.parseInt(color1);
                if (colors1 == 1) {
                    Pump1.setBackgroundResource(R.color.yellow);
                } else if (colors1 ==2){
                    Pump1.setBackgroundResource(R.color.orange);
                } else if (colors1 == 3){
                    Pump1.setBackgroundResource(R.color.red);
                } else {
                    Pump1.setBackgroundResource(R.color.green);
                }

                String color2 = snapshot.child("2").child("alarm_severity").getValue(String.class);
                int colors2 = Integer.parseInt(color2);
                if (colors2 == 1) {
                    Pump2.setBackgroundResource(R.color.yellow);
                } else if (colors2 ==2){
                    Pump2.setBackgroundResource(R.color.orange);
                } else if (colors2 == 3){
                    Pump2.setBackgroundResource(R.color.red);
                } else {
                    Pump2.setBackgroundResource(R.color.green);
                }

                String color3 = snapshot.child("3").child("alarm_severity").getValue(String.class);
                int colors3 = Integer.parseInt(color3);
                if (colors3 == 1) {
                    Pump3.setBackgroundResource(R.color.yellow);
                } else if (colors3 ==2){
                    Pump3.setBackgroundResource(R.color.orange);
                } else if (colors3 == 3){
                    Pump3.setBackgroundResource(R.color.red);
                } else {
                    Pump3.setBackgroundResource(R.color.green);
                }

                String color4 = snapshot.child("4").child("alarm_severity").getValue(String.class);
                int colors4 = Integer.parseInt(color4);
                if (colors4 == 1) {
                    Pump4.setBackgroundResource(R.color.yellow);
                } else if (colors4 ==2){
                    Pump4.setBackgroundResource(R.color.orange);
                } else if (colors4 == 3){
                    Pump4.setBackgroundResource(R.color.red);
                } else {
                    Pump4.setBackgroundResource(R.color.green);
                }

                String color5 = snapshot.child("5").child("alarm_severity").getValue(String.class);
                int colors5 = Integer.parseInt(color5);
                if (colors5 == 1) {
                    Pump5.setBackgroundResource(R.color.yellow);
                } else if (colors5 ==2){
                    Pump5.setBackgroundResource(R.color.orange);
                } else if (colors5 == 3){
                    Pump5.setBackgroundResource(R.color.red);
                } else {
                    Pump5.setBackgroundResource(R.color.green);
                }

                String color6 = snapshot.child("6").child("alarm_severity").getValue(String.class);
                int colors6 = Integer.parseInt(color6);
                if (colors6 == 1) {
                    Pump6.setBackgroundResource(R.color.yellow);
                } else if (colors6 ==2){
                    Pump6.setBackgroundResource(R.color.orange);
                } else if (colors6 == 3){
                    Pump6.setBackgroundResource(R.color.red);
                } else {
                    Pump6.setBackgroundResource(R.color.green);
                }

                String color7 = snapshot.child("7").child("alarm_severity").getValue(String.class);
                int colors7 = Integer.parseInt(color7);
                if (colors7 == 1) {
                    Pump7.setBackgroundResource(R.color.yellow);
                } else if (colors7 ==2){
                    Pump7.setBackgroundResource(R.color.orange);
                } else if (colors7 == 3){
                    Pump7.setBackgroundResource(R.color.red);
                } else {
                    Pump7.setBackgroundResource(R.color.green);
                }

                String color8 = snapshot.child("8").child("alarm_severity").getValue(String.class);
                int colors8 = Integer.parseInt(color8);
                if (colors8 == 1) {
                    Pump8.setBackgroundResource(R.color.yellow);
                } else if (colors8 ==2){
                    Pump8.setBackgroundResource(R.color.orange);
                } else if (colors8 == 3){
                    Pump8.setBackgroundResource(R.color.red);
                } else {
                    Pump8.setBackgroundResource(R.color.green);
                }
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
