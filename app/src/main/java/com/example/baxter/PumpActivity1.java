package com.example.baxter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PumpActivity1 extends SwipeActivity {
    RelativeLayout Pump1, Pump2, Pump3, Pump4, Pump5, Pump6, Pump7, Pump8;
    TextView  pt1number,pt2number,pt3number,pt4number,pt5number,pt6number,pt7number,pt8number, Pump1Rate ,Pump1drug,Pump1startVolume,Pump2Rate,Pump2drug,Pump2startVolume,Pump5Rate ,Pump5drug,Pump5startVolume,Pump3Rate ,Pump3drug,Pump3startVolume,
            Pump4Rate ,Pump4drug,Pump4startVolume,Pump6Rate ,Pump6drug,Pump6startVolume,Pump7Rate ,Pump7drug,Pump7startVolume,Pump8Rate ,Pump8drug,Pump8startVolume;
    ProgressBar Pump1pct, Pump2pct, Pump3pct, Pump4pct, Pump5pct, Pump6pct, Pump7pct, Pump8pct;
    Button fwdbutton, homebutton;
    String  pt1numberstr,pt2numberstr,pt3numberstr,pt4numberstr,pt5numberstr,pt6numberstr,pt7numberstr,pt8numberstr,
            ptindex, patient, Pumpp, user, key, alarm1, alarm_severity1, alarm_text1, Pump1drugstr, Pump1Ratestr, Pump1startVolumestr, Pump1ID,
            alarm2, alarm_severity2, alarm_text2, Pump2drugstr, Pump2Ratestr, Pump2startVolumestr, Pump2ID
            , alarm3, alarm_severity3, alarm_text3, Pump3drugstr, Pump3Ratestr, Pump3startVolumestr, Pump3ID
            , alarm4, alarm_severity4, alarm_text4, Pump4drugstr, Pump4Ratestr, Pump4startVolumestr, Pump4ID
            , alarm5, alarm_severity5, alarm_text5, Pump5drugstr, Pump5Ratestr, Pump5startVolumestr, Pump5ID
            , alarm6, alarm_severity6, alarm_text6, Pump6drugstr, Pump6Ratestr, Pump6startVolumestr, Pump6ID
            , alarm7, alarm_severity7, alarm_text7, Pump7drugstr, Pump7Ratestr, Pump7startVolumestr, Pump7ID
            , alarm8, alarm_severity8, alarm_text8, Pump8drugstr, Pump8Ratestr, Pump8startVolumestr, Pump8ID;
    int Pumps;
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
        pt1number=findViewById(R.id.pt1number);
        pt2number=findViewById(R.id.pt2number);
        pt3number=findViewById(R.id.pt3number);
        pt4number=findViewById(R.id.pt4number);
        pt5number=findViewById(R.id.pt5number);
        pt6number=findViewById(R.id.pt6number);
        pt7number=findViewById(R.id.pt7number);
        pt8number=findViewById(R.id.pt8number);
        Pump1Rate = findViewById(R.id.Pump1Rate);
        Pump2Rate = findViewById(R.id.Pump2Rate);
        Pump3Rate = findViewById(R.id.Pump3Rate);
        Pump4Rate = findViewById(R.id.Pump4Rate);
        Pump5Rate = findViewById(R.id.Pump5Rate);
        Pump6Rate = findViewById(R.id.Pump6Rate);
        Pump7Rate = findViewById(R.id.Pump7Rate);
        Pump8Rate = findViewById(R.id.Pump8Rate);

        Pump1drug = findViewById(R.id.Pump1drug);
        Pump2drug = findViewById(R.id.Pump2drug);
        Pump3drug = findViewById(R.id.Pump3drug);
        Pump4drug = findViewById(R.id.Pump4drug);
        Pump5drug = findViewById(R.id.Pump5drug);
        Pump6drug = findViewById(R.id.Pump6drug);
        Pump7drug = findViewById(R.id.Pump7drug);
        Pump8drug = findViewById(R.id.Pump8drug);

        Pump1pct = findViewById(R.id.pct1);
        Pump2pct = findViewById(R.id.pct2);
        Pump3pct = findViewById(R.id.pct3);
        Pump4pct = findViewById(R.id.pct4);
        Pump5pct = findViewById(R.id.pct5);
        Pump6pct = findViewById(R.id.pct6);
        Pump7pct = findViewById(R.id.pct7);
        Pump8pct = findViewById(R.id.pct8);
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
        Pump1startVolume = findViewById(R.id.Pump1startVolume);
        Pump2startVolume = findViewById(R.id.Pump2startVolume);
        Pump3startVolume = findViewById(R.id.Pump3startVolume);
        Pump4startVolume = findViewById(R.id.Pump4startVolume);
        Pump5startVolume = findViewById(R.id.Pump5startVolume);
        Pump6startVolume = findViewById(R.id.Pump6startVolume);
        Pump7startVolume = findViewById(R.id.Pump7startVolume);
        Pump8startVolume = findViewById(R.id.Pump8startVolume);
        
        fwdbutton = findViewById(R.id.ForwardButton);
        homebutton = findViewById(R.id.HomeButton);
        data();
        clickListen();
    }

    private void data() {
        Intent grabdata = getIntent();
        user = grabdata.getStringExtra("user");
        Pumpp = grabdata.getStringExtra("Pump");
        key = grabdata.getStringExtra("key");
        patient = grabdata.getStringExtra("patients");
        ptindex = grabdata.getStringExtra("ptindex");
        Pumps = Integer.parseInt(Pumpp);
        if (Pumps <= 8) {
            fwdbutton.setEnabled(false);
            fwdbutton.setVisibility(View.GONE);
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
        Pump6.setEnabled(false);
        Pump6.setVisibility(View.INVISIBLE);
        Pump7.setEnabled(false);
        Pump7.setVisibility(View.INVISIBLE);
        Pump8.setEnabled(false);
        Pump8.setVisibility(View.INVISIBLE);
        Pump5.setEnabled(false);
        Pump5.setVisibility(View.INVISIBLE);
        Pump4.setEnabled(false);
        Pump4.setVisibility(View.INVISIBLE);
        Pump3.setEnabled(false);
        Pump3.setVisibility(View.INVISIBLE);
        Pump2.setEnabled(false);
        Pump2.setVisibility(View.INVISIBLE);
        Pump1.setEnabled(false);
        Pump1.setVisibility(View.INVISIBLE);
        Log.d("key", key);
        Log.d("user", user);
        Log.d("ptindex", ptindex);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users")
                .child(key).child(user).child("careArea").child(ptindex);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String maxsev = snapshot.child("maxSeverity").getValue().toString();
                String maxalarm = snapshot.child("maxSevPumps").getValue().toString();
                Log.d("max alarm", maxalarm);
                Log.d("max sev", maxsev);
                int colors = Integer.parseInt(maxsev);
                if (colors == 1) {
                    AlertDialog alertDialog = new AlertDialog.Builder(PumpActivity1.this).create();
                    alertDialog.setTitle("Alarm");
                    alertDialog.setMessage("Check pump (s): "+ maxalarm);
                    alertDialog.getWindow().setBackgroundDrawableResource(R.color.yellow);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.dismiss();
                        }
                    });
                    alertDialog.show();
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(1000); // 5000 miliseconds = 5 seconds
                } else if (colors ==2){
                    final MediaPlayer mp = MediaPlayer.create(PumpActivity1.this, R.raw.med);
                    mp.start();
                    AlertDialog alertDialog = new AlertDialog.Builder(PumpActivity1.this).create();
                    alertDialog.setTitle("Alarm");
                    alertDialog.setMessage("Check pump (s): "+maxalarm);
                    alertDialog.getWindow().setBackgroundDrawableResource(R.color.orange);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.dismiss();
                            mp.stop();
                        }
                    });
                    alertDialog.show();
                    Vibrator v = (Vibrator) PumpActivity1.this.getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(3000); // 5000 miliseconds = 5 seconds
                } else if (colors == 3){
                    final MediaPlayer mp = MediaPlayer.create(PumpActivity1.this, R.raw.hi);
                    mp.start();
                    AlertDialog alertDialog = new AlertDialog.Builder(PumpActivity1.this).create();
                    alertDialog.setTitle("Alarm");
                    alertDialog.setMessage("Check pump (s): "+maxalarm);

                    alertDialog.getWindow().setBackgroundDrawableResource(R.color.red);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.dismiss();
                            mp.stop();
                        }
                    });
                    alertDialog.show();
                    Vibrator v = (Vibrator) PumpActivity1.this.getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(5000); // 5000 miliseconds = 5 seconds
                } else {
                }
                if (Pumps >= 1) {
                    String pct1 = snapshot.child("Pumps").child("1").child("percent_complete").getValue().toString();
                    int percent1 = Integer.parseInt(pct1);
                    Pump1pct.setProgress(percent1);
                    Pump1drugstr = snapshot.child("Pumps").child("1").child("drug").getValue().toString();
                    Pump1Ratestr = snapshot.child("Pumps").child("1").child("currentRate").getValue().toString();
                    Pump1startVolumestr = snapshot.child("Pumps").child("1").child("startVolume").getValue().toString();
                    Pump1Rate.setText(Pump1Ratestr);
                    Pump1drug.setText(Pump1drugstr);
                    pt1numberstr = snapshot.child("Pumps").child("1").child("pumpnumber").getValue().toString();
                    pt1number.setText(pt1numberstr);
                    Pump1startVolume.setText(Pump1startVolumestr);
                    //data to pass on
                    alarm1 = snapshot.child("Pumps").child("1").child("alarm").getValue().toString();
                    alarm_severity1 = snapshot.child("Pumps").child("1").child("alarm_severity").getValue().toString();
                    Pump1ID = snapshot.child("Pumps").child("1").child("pumpID").getValue().toString();
                    alarm_text1 = snapshot.child("Pumps").child("1").child("alarm_text").getValue().toString();
                    //setting colors
                    String color1 = snapshot.child("Pumps").child("1").child("alarm_severity").getValue().toString();
                    int colors1 = Integer.parseInt(color1);
                    if (colors1 == 1) {
                        Pump1.setBackgroundResource(R.color.yellow);
                    } else if (colors1 == 2) {
                        Pump1.setBackgroundResource(R.color.orange);
                    } else if (colors1 == 3) {
                        Pump1.setBackgroundResource(R.color.red);
                    } else {
                        Pump1.setBackgroundResource(R.color.green);
                    }
                    Pump1.setEnabled(true);
                    Pump1.setVisibility(View.VISIBLE);
                }
                if (Pumps >= 2) {
                    String pct2 = snapshot.child("Pumps").child("2").child("percent_complete").getValue().toString();
                    int percent2 = Integer.parseInt(pct2);
                    Pump2pct.setProgress(percent2);
                    Pump2drugstr = snapshot.child("Pumps").child("2").child("drug").getValue(String.class);
                    Pump2Ratestr = snapshot.child("Pumps").child("2").child("currentRate").getValue().toString();
                    Pump2startVolumestr = snapshot.child("Pumps").child("2").child("startVolume").getValue().toString();
                    Pump2Rate.setText(Pump2Ratestr);
                    Pump2drug.setText(Pump2drugstr);
                    pt2numberstr = snapshot.child("Pumps").child("2").child("pumpnumber").getValue().toString();
                    pt2number.setText(pt2numberstr);
                    Pump2startVolume.setText(Pump2startVolumestr);
                    alarm2 = snapshot.child("Pumps").child("2").child("alarm").getValue().toString();
                    alarm_severity2 = snapshot.child("Pumps").child("2").child("alarm_severity").getValue().toString();
                    Pump2ID = snapshot.child("Pumps").child("2").child("pumpID").getValue().toString();
                    alarm_text2 = snapshot.child("Pumps").child("2").child("alarm_text").getValue(String.class);
                    String color2 = snapshot.child("Pumps").child("2").child("alarm_severity").getValue().toString();
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
                    Pump2.setEnabled(true);
                    Pump2.setVisibility(View.VISIBLE);

                }
                if (Pumps >= 3){
                    String pct1 = snapshot.child("Pumps").child("3").child("percent_complete").getValue().toString();
                    int percent1 = Integer.parseInt(pct1);
                    Pump3pct.setProgress(percent1);
                    Pump3drugstr = snapshot.child("Pumps").child("3").child("drug").getValue(String.class);
                    Pump3Ratestr = snapshot.child("Pumps").child("3").child("currentRate").getValue().toString();
                    Pump3startVolumestr = snapshot.child("Pumps").child("3").child("startVolume").getValue().toString();
                    Pump3Rate.setText(Pump3Ratestr);
                    Pump3drug.setText(Pump3drugstr);
                    pt3numberstr = snapshot.child("Pumps").child("3").child("pumpnumber").getValue().toString();
                    pt3number.setText(pt3numberstr);
                    Pump3startVolume.setText(Pump3startVolumestr);
                    alarm3 = snapshot.child("Pumps").child("3").child("alarm").getValue().toString();
                    alarm_severity3 = snapshot.child("Pumps").child("3").child("alarm_severity").getValue().toString();;
                    Pump3ID = snapshot.child("Pumps").child("3").child("pumpID").getValue().toString();;
                    alarm_text3 = snapshot.child("Pumps").child("3").child("alarm_text").getValue(String.class);
                    String color3 = snapshot.child("Pumps").child("3").child("alarm_severity").getValue().toString();
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
                    Pump3.setEnabled(true);
                    Pump3.setVisibility(View.VISIBLE);
                }
                if (Pumps >= 4){
                    String pct1 = snapshot.child("Pumps").child("4").child("percent_complete").getValue().toString();
                    int percent1 = Integer.parseInt(pct1);
                    Pump4pct.setProgress(percent1);
                    String Pump4drugstr = snapshot.child("Pumps").child("4").child("drug").getValue(String.class);
                    String Pump4Ratestr = snapshot.child("Pumps").child("4").child("currentRate").getValue().toString();
                    String Pump4startVolumestr = snapshot.child("Pumps").child("4").child("startVolume").getValue().toString();
                    Pump4Rate.setText(Pump4Ratestr);
                    Pump4drug.setText(Pump4drugstr);
                    pt4numberstr = snapshot.child("Pumps").child("4").child("pumpnumber").getValue().toString();
                    pt4number.setText(pt4numberstr);
                    Pump4startVolume.setText(Pump4startVolumestr);
                    alarm4 = snapshot.child("Pumps").child("4").child("alarm").getValue().toString();;
                    alarm_severity4 = snapshot.child("Pumps").child("4").child("alarm_severity").getValue().toString();;
                    Pump4ID = snapshot.child("Pumps").child("4").child("pumpID").getValue().toString();;
                    alarm_text4 = snapshot.child("Pumps").child("4").child("alarm_text").getValue(String.class);
                    String color4 = snapshot.child("Pumps").child("4").child("alarm_severity").getValue().toString();
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
                    Pump4.setEnabled(true);
                    Pump4.setVisibility(View.VISIBLE);


                }
                if (Pumps >= 5){
                    String pct1 = snapshot.child("Pumps").child("5").child("percent_complete").getValue().toString();
                    int percent1 = Integer.parseInt(pct1);
                    Pump5pct.setProgress(percent1);
                    String color5 = snapshot.child("Pumps").child("5").child("alarm_severity").getValue().toString();
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

                    Pump5.setEnabled(true);
                    Pump5.setVisibility(View.VISIBLE);
                    Pump5drugstr = snapshot.child("Pumps").child("5").child("drug").getValue(String.class);
                    Pump5Ratestr = snapshot.child("Pumps").child("5").child("currentRate").getValue().toString();;
                    Pump5startVolumestr = snapshot.child("Pumps").child("5").child("startVolume").getValue().toString();
                    Pump5Rate.setText(Pump5Ratestr);
                    Pump5drug.setText(Pump5drugstr);
                    pt5numberstr = snapshot.child("Pumps").child("5").child("pumpnumber").getValue().toString();
                    pt5number.setText(pt5numberstr);
                    Pump5startVolume.setText(Pump5startVolumestr);
                    alarm5 = snapshot.child("Pumps").child("5").child("alarm").getValue().toString();;
                    alarm_severity5 = snapshot.child("Pumps").child("5").child("alarm_severity").getValue().toString();;
                    Pump5ID = snapshot.child("Pumps").child("5").child("pumpID").getValue().toString();;
                    alarm_text5 = snapshot.child("Pumps").child("5").child("alarm_text").getValue(String.class);
                }
                if (Pumps >= 6) {
                    String pct1 = snapshot.child("Pumps").child("6").child("percent_complete").getValue().toString();
                    int percent1 = Integer.parseInt(pct1);
                    Pump6pct.setProgress(percent1);
                    String color6 = snapshot.child("Pumps").child("6").child("alarm_severity").getValue().toString();
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
                    Pump6.setEnabled(true);
                    Pump6.setVisibility(View.VISIBLE);
                    pt6numberstr = snapshot.child("Pumps").child("6").child("pumpnumber").getValue().toString();
                    pt6number.setText(pt6numberstr);
                    Pump6drugstr = snapshot.child("Pumps").child("6").child("drug").getValue(String.class);
                    Pump6Ratestr = snapshot.child("Pumps").child("6").child("currentRate").getValue().toString();;
                    Pump6startVolumestr = snapshot.child("Pumps").child("6").child("startVolume").getValue().toString();
                    Pump6Rate.setText(Pump6Ratestr);
                    Pump6drug.setText(Pump6drugstr);
                    Pump6startVolume.setText(Pump6startVolumestr);
                    alarm6 = snapshot.child("Pumps").child("6").child("alarm").getValue().toString();;
                    alarm_severity6 = snapshot.child("Pumps").child("6").child("alarm_severity").getValue().toString();;
                    Pump6ID = snapshot.child("Pumps").child("6").child("pumpID").getValue().toString();;
                    alarm_text6 = snapshot.child("Pumps").child("6").child("alarm_text").getValue(String.class);
                }
                if (Pumps >= 7){
                    String pct1 = snapshot.child("Pumps").child("7").child("percent_complete").getValue().toString();
                    int percent1 = Integer.parseInt(pct1);
                    Pump7pct.setProgress(percent1);
                    String color7 = snapshot.child("Pumps").child("7").child("alarm_severity").getValue().toString();
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
                    Pump7drugstr = snapshot.child("Pumps").child("7").child("drug").getValue(String.class);
                    Pump7Ratestr = snapshot.child("Pumps").child("7").child("currentRate").getValue().toString();;
                    Pump7startVolumestr = snapshot.child("Pumps").child("7").child("startVolume").getValue().toString();
                    Pump7Rate.setText(Pump7Ratestr);
                    Pump7drug.setText(Pump7drugstr);
                    Pump7startVolume.setText(Pump7startVolumestr);
                    alarm7 = snapshot.child("Pumps").child("7").child("alarm").getValue().toString();;
                    alarm_severity7 = snapshot.child("Pumps").child("7").child("alarm_severity").getValue().toString();;
                    Pump7ID = snapshot.child("Pumps").child("7").child("pumpID").getValue().toString();;
                    alarm_text7 = snapshot.child("Pumps").child("7").child("alarm_text").getValue(String.class);
                    Pump7.setEnabled(true);
                    Pump7.setVisibility(View.VISIBLE);
                    pt7numberstr = snapshot.child("Pumps").child("7").child("pumpnumber").getValue().toString();
                    pt7number.setText(pt7numberstr);

                }
                if (Pumps >= 8) {
                    String pct1 = snapshot.child("Pumps").child("8").child("percent_complete").getValue().toString();
                    int percent1 = Integer.parseInt(pct1);
                    Pump8pct.setProgress(percent1);
                    String color8 = snapshot.child("Pumps").child("8").child("alarm_severity").getValue().toString();
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
                    Pump8drugstr = snapshot.child("Pumps").child("8").child("drug").getValue(String.class);
                    Pump8Ratestr = snapshot.child("Pumps").child("8").child("currentRate").getValue().toString();;
                    Pump8startVolumestr = snapshot.child("Pumps").child("8").child("startVolume").getValue().toString();
                    Pump8Rate.setText(Pump8Ratestr);
                    Pump8drug.setText(Pump8drugstr);
                    pt8numberstr = snapshot.child("Pumps").child("8").child("pumpnumber").getValue().toString();
                    pt8number.setText(pt3numberstr);
                    Pump8startVolume.setText(Pump8startVolumestr);
                    alarm8 = snapshot.child("Pumps").child("8").child("alarm").getValue().toString();;
                    alarm_severity8 = snapshot.child("Pumps").child("8").child("alarm_severity").getValue().toString();;
                    Pump8ID = snapshot.child("Pumps").child("8").child("pumpID").getValue().toString();;
                    alarm_text8 = snapshot.child("Pumps").child("8").child("alarm_text").getValue(String.class);
                    Pump8.setEnabled(true);
                    Pump8.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void clickListen() {
        Pump1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity1.this, Pump1.class);
                intent.putExtra("alarm", alarm1);
                intent.putExtra("Pump", Pumpp);
                intent.putExtra("ptindex", ptindex);
                intent.putExtra("pindex", "17");
                intent.putExtra("pumpnumber", pt1numberstr);
                intent.putExtra("alarm_severity", alarm_severity1);
                intent.putExtra("drug", Pump1drugstr);
                intent.putExtra("rate", Pump1Ratestr);
                intent.putExtra("startVolume", Pump1startVolumestr);
                intent.putExtra("ID", Pump1ID);
                intent.putExtra("alarm_text", alarm_text1);
                intent.putExtra("user",user);
                intent.putExtra("key",key);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        Pump2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity1.this, Pump1.class);
                intent.putExtra("alarm", alarm2);
                intent.putExtra("alarm_severity", alarm_severity2);
                intent.putExtra("drug", Pump2drugstr);
                intent.putExtra("ptindex", ptindex); intent.putExtra("Pump", Pumpp);
                intent.putExtra("rate", Pump2Ratestr);
                intent.putExtra("pumpnumber", pt2numberstr);
                intent.putExtra("pindex", "18");
                intent.putExtra("startVolume", Pump2startVolumestr);
                intent.putExtra("ID", Pump2ID);
                intent.putExtra("alarm_text", alarm_text2);
                intent.putExtra("user",user);
                intent.putExtra("key",key);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        Pump3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity1.this, Pump1.class);
                intent.putExtra("alarm", alarm3);
                intent.putExtra("alarm_severity", alarm_severity3);
                intent.putExtra("drug", Pump3drugstr);
                intent.putExtra("rate", Pump3Ratestr);

                intent.putExtra("pumpnumber", pt3numberstr);
                intent.putExtra("ptindex", ptindex); intent.putExtra("Pump", Pumpp);
                intent.putExtra("startVolume", Pump3startVolumestr);
                intent.putExtra("ID", Pump3ID);
                intent.putExtra("pindex", "19");
                intent.putExtra("alarm_text", alarm_text3);
                intent.putExtra("user",user);
                intent.putExtra("key",key);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        Pump4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity1.this, Pump1.class);
                intent.putExtra("alarm", alarm4);
                intent.putExtra("alarm_severity", alarm_severity4);
                intent.putExtra("drug", Pump4drugstr);
                intent.putExtra("ptindex", ptindex); intent.putExtra("Pump", Pumpp);
                intent.putExtra("pindex", "20");
                intent.putExtra("rate", Pump4Ratestr);
                intent.putExtra("startVolume", Pump4startVolumestr);
                intent.putExtra("ID", Pump4ID);
                intent.putExtra("pumpnumber", pt4numberstr);
                intent.putExtra("alarm_text", alarm_text4);
                intent.putExtra("user",user);
                intent.putExtra("key",key);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        Pump5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity1.this, Pump1.class);
                intent.putExtra("alarm", alarm5);
                intent.putExtra("alarm_severity", alarm_severity5);
                intent.putExtra("drug", Pump5drugstr);
                intent.putExtra("ptindex", ptindex); intent.putExtra("Pump", Pumpp);
                intent.putExtra("rate", Pump5Ratestr);
                intent.putExtra("pumpnumber", pt5numberstr);
                intent.putExtra("startVolume", Pump5startVolumestr);
                intent.putExtra("ID", Pump5ID);
                intent.putExtra("pindex", "21");
                intent.putExtra("alarm_text", alarm_text5);
                intent.putExtra("user",user);
                intent.putExtra("key",key);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        Pump6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity1.this, Pump1.class);
                intent.putExtra("alarm", alarm1);
                intent.putExtra("pindex", "22");
                intent.putExtra("alarm_severity", alarm_severity6);
                intent.putExtra("drug", Pump6drugstr);
                intent.putExtra("ptindex", ptindex); intent.putExtra("Pump", Pumpp);
                intent.putExtra("rate", Pump6Ratestr);
                intent.putExtra("startVolume", Pump6startVolumestr);
                intent.putExtra("ID", Pump6ID);
                intent.putExtra("pumpnumber", pt6numberstr);
                intent.putExtra("alarm_text", alarm_text6);
                intent.putExtra("user",user);
                intent.putExtra("key",key);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        Pump7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity1.this, Pump1.class);
                intent.putExtra("alarm", alarm7);
                intent.putExtra("alarm_severity", alarm_severity7);
                intent.putExtra("drug", Pump7drugstr);
                intent.putExtra("rate", Pump7Ratestr);
                intent.putExtra("pumpnumber", pt7numberstr);
                intent.putExtra("ptindex", ptindex); intent.putExtra("Pump", Pumpp);
                intent.putExtra("pindex", "23");
                intent.putExtra("startVolume", Pump7startVolumestr);
                intent.putExtra("ID", Pump7ID);
                intent.putExtra("alarm_text", alarm_text7);
                intent.putExtra("user",user);
                intent.putExtra("key",key);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        Pump8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity1.this, Pump1.class);
                intent.putExtra("alarm", alarm8);
                intent.putExtra("alarm_severity", alarm_severity8);
                intent.putExtra("drug", Pump8drugstr);
                intent.putExtra("rate", Pump8Ratestr);
                intent.putExtra("pumpnumber", pt8numberstr);
                intent.putExtra("ptindex", ptindex); intent.putExtra("Pump", Pumpp);
                intent.putExtra("pindex", "24");
                intent.putExtra("startVolume", Pump8startVolumestr);
                intent.putExtra("ID", Pump8ID);
                intent.putExtra("alarm_text", alarm_text8);
                intent.putExtra("user",user);
                intent.putExtra("key",key);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        fwdbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity1.this, PumpActivity2.class);
                intent.putExtra("Pump", Pumpp);
                intent.putExtra("user", user);
                intent.putExtra("key", key);
                intent.putExtra("patients", patient);
                intent.putExtra("ptindex", ptindex);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity1.this, PatientActivity1.class);
                intent.putExtra("patients", patient);
                intent.putExtra("user", user);
                intent.putExtra("key", key);
                intent.putExtra("patients", patient);
                intent.putExtra("ptindex", ptindex);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    protected void onSwipeRight() {

    }

    @Override
    protected void onSwipeLeft() {
        if (Pumps > 8) {
            Intent intent = new Intent(PumpActivity1.this, PumpActivity2.class);
            intent.putExtra("Pump", Pumpp);
            intent.putExtra("user", user);
            intent.putExtra("key", key);
            intent.putExtra("patients", patient);
            intent.putExtra("ptindex", ptindex);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }

    }
}
