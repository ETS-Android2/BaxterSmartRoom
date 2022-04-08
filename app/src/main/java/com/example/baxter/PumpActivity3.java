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
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PumpActivity3 extends SwipeActivity {
    RelativeLayout Pump1, Pump2, Pump3, Pump4, Pump5, Pump6, Pump7, Pump8;
    TextView Pump1Rate, Pump1drug, Pump1startVolume, Pump2Rate, Pump2drug, Pump2startVolume, Pump5Rate, Pump5drug, Pump5startVolume, Pump3Rate, Pump3drug, Pump3startVolume,
            Pump4Rate, Pump4drug, Pump4startVolume, Pump6Rate, Pump6drug, Pump6startVolume, Pump7Rate, Pump7drug, Pump7startVolume, Pump8Rate, Pump8drug, Pump8startVolume;

    Button backbutton, homebutton;
    String ptindex, patient, Pumpp, user, key, alarm1, alarm_severity1, alarm_text1, Pump1drugstr, Pump1Ratestr, Pump1startVolumestr, Pump1ID,
            alarm2, alarm_severity2, alarm_text2, Pump2drugstr, Pump2Ratestr, Pump2startVolumestr, Pump2ID, alarm3, alarm_severity3, alarm_text3, Pump3drugstr, Pump3Ratestr, Pump3startVolumestr, Pump3ID, alarm4, alarm_severity4, alarm_text4, Pump4drugstr, Pump4Ratestr, Pump4startVolumestr, Pump4ID, alarm5, alarm_severity5, alarm_text5, Pump5drugstr, Pump5Ratestr, Pump5startVolumestr, Pump5ID, alarm6, alarm_severity6, alarm_text6, Pump6drugstr, Pump6Ratestr, Pump6startVolumestr, Pump6ID, alarm7, alarm_severity7, alarm_text7, Pump7drugstr, Pump7Ratestr, Pump7startVolumestr, Pump7ID, alarm8, alarm_severity8, alarm_text8, Pump8drugstr, Pump8Ratestr, Pump8startVolumestr, Pump8ID;

    int Pumps, pumpthisact, Pumpsdisplayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pump_activity3);
        Pump1 = findViewById(R.id.Pump1);
        Pump2 = findViewById(R.id.Pump2);
        Pump3 = findViewById(R.id.Pump3);
        Pump4 = findViewById(R.id.Pump4);
        Pump5 = findViewById(R.id.Pump5);
        Pump6 = findViewById(R.id.Pump6);
        Pump7 = findViewById(R.id.Pump7);
        Pump8 = findViewById(R.id.Pump8);
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

        Pump1startVolume = findViewById(R.id.Pump1startVolume);
        Pump2startVolume = findViewById(R.id.Pump2startVolume);
        Pump3startVolume = findViewById(R.id.Pump3startVolume);
        Pump4startVolume = findViewById(R.id.Pump4startVolume);
        Pump5startVolume = findViewById(R.id.Pump5startVolume);
        Pump6startVolume = findViewById(R.id.Pump6startVolume);
        Pump7startVolume = findViewById(R.id.Pump7startVolume);
        Pump8startVolume = findViewById(R.id.Pump8startVolume);
        homebutton = findViewById(R.id.HomeButton);
        backbutton = findViewById(R.id.BackButton);
        Intent grabdata = getIntent();
        Pumpp = grabdata.getStringExtra("Pump");
        user = grabdata.getStringExtra("user");
        patient = grabdata.getStringExtra("patients");
        ptindex = grabdata.getStringExtra("ptindex");
        pumpthisact = Pumps - 24;
        Pumps = Integer.parseInt(Pumpp);
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity3.this, PumpActivity1.class);
                intent.putExtra("user", user);
                intent.putExtra("key", key);
                intent.putExtra("Pump", Pumpp);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity3.this, PumpActivity25.class);
                intent.putExtra("user", user);
                intent.putExtra("key", key);
                intent.putExtra("Pump", Pumpp);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
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
        DatabaseReference reference123 = FirebaseDatabase.getInstance().getReference().child("users")
                .child(key).child(user).child("careArea").child(ptindex);
        reference123.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String maxsev = snapshot.child("maxSeverity").getValue().toString();
                String maxalarm = snapshot.child("maxSevPumps").getValue().toString();
                Log.d("max alarm", maxalarm);
                Log.d("max sev", maxsev);
                int colors = Integer.parseInt(maxsev);
                if (colors == 1) {
                    AlertDialog alertDialog = new AlertDialog.Builder(PumpActivity3.this).create();
                    alertDialog.setTitle("Alarm");
                    alertDialog.setMessage("Check pump (s): " + maxalarm);
                    alertDialog.getWindow().setBackgroundDrawableResource(R.color.yellow);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.dismiss();
                        }
                    });
                    alertDialog.show();
                    Vibrator v = (Vibrator) PumpActivity3.this.getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(1000); // 5000 miliseconds = 5 seconds
                } else if (colors == 2) {
                    final MediaPlayer mp = MediaPlayer.create(PumpActivity3.this, R.raw.med);
                    mp.start();
                    AlertDialog alertDialog = new AlertDialog.Builder(PumpActivity3.this).create();
                    alertDialog.setTitle("Alarm");
                    alertDialog.setMessage("Check pump (s): " + maxalarm);
                    alertDialog.getWindow().setBackgroundDrawableResource(R.color.orange);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.dismiss();
                            mp.stop();
                        }
                    });
                    alertDialog.show();
                    Vibrator v= (Vibrator) PumpActivity3.this.getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(3000); // 5000 miliseconds = 5 seconds
                } else if (colors == 3) {
                    final MediaPlayer mp = MediaPlayer.create(PumpActivity3.this, R.raw.hi);
                    mp.start();
                    AlertDialog alertDialog = new AlertDialog.Builder(PumpActivity3.this).create();
                    alertDialog.setTitle("Alarm");
                    alertDialog.setMessage("Check pump (s): " + maxalarm);

                    alertDialog.getWindow().setBackgroundDrawableResource(R.color.red);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.dismiss();
                            mp.stop();
                        }
                    });
                    alertDialog.show();
                    Vibrator v = (Vibrator) PumpActivity3.this.getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(5000); // 5000 miliseconds = 5 seconds
                } else { }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users")
                .child(key).child(user).child("careArea").child(ptindex).child("Pumps");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (pumpthisact >= 1) {
                    Pump1drugstr = snapshot.child("25").child("drug").getValue().toString();
                    Pump1Ratestr = snapshot.child("25").child("currentRate").getValue().toString();
                    Pump1startVolumestr = snapshot.child("25").child("startVolume").getValue().toString();
                    Pump1Rate.setText(Pump1Ratestr);
                    Pump1drug.setText(Pump1drugstr);
                    Pump1startVolume.setText(Pump1startVolumestr);
                    //data to pass on
                    alarm1 = snapshot.child("25").child("alarm").getValue().toString();
                    alarm_severity1 = snapshot.child("25").child("alarm_severity").getValue().toString();
                    Pump1ID = snapshot.child("25").child("pumpID").getValue().toString();
                    alarm_text1 = snapshot.child("25").child("alarm_text").getValue().toString();
                    //setting colors
                    String color1 = snapshot.child("25").child("alarm_severity").getValue().toString();
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
                if (pumpthisact >= 2) {
                    Pump2drugstr = snapshot.child("26").child("drug").getValue(String.class);
                    Pump2Ratestr = snapshot.child("26").child("currentRate").getValue().toString();
                    ;
                    Pump2startVolumestr = snapshot.child("26").child("startVolume").getValue().toString();
                    Pump2Rate.setText(Pump2Ratestr);
                    Pump2drug.setText(Pump2drugstr);
                    Pump2startVolume.setText(Pump2startVolumestr);
                    alarm2 = snapshot.child("26").child("alarm").getValue().toString();
                    ;
                    alarm_severity2 = snapshot.child("26").child("alarm_severity").getValue().toString();
                    ;
                    Pump2ID = snapshot.child("26").child("pumpID").getValue().toString();
                    ;
                    alarm_text2 = snapshot.child("26").child("alarm_text").getValue(String.class);
                    String color2 = snapshot.child("26").child("alarm_severity").getValue().toString();
                    int colors2 = Integer.parseInt(color2);
                    if (colors2 == 1) {
                        Pump2.setBackgroundResource(R.color.yellow);
                    } else if (colors2 == 2) {
                        Pump2.setBackgroundResource(R.color.orange);
                    } else if (colors2 == 3) {
                        Pump2.setBackgroundResource(R.color.red);
                    } else {
                        Pump2.setBackgroundResource(R.color.green);
                    }
                    Pump2.setEnabled(true);
                    Pump2.setVisibility(View.VISIBLE);

                }
                if (pumpthisact >= 3) {
                    Pump3drugstr = snapshot.child("27").child("drug").getValue(String.class);
                    Pump3Ratestr = snapshot.child("27").child("currentRate").getValue().toString();
                    ;
                    Pump3startVolumestr = snapshot.child("27").child("startVolume").getValue().toString();
                    Pump3Rate.setText(Pump3Ratestr);
                    Pump3drug.setText(Pump3drugstr);
                    Pump3startVolume.setText(Pump3startVolumestr);
                    alarm3 = snapshot.child("27").child("alarm").getValue().toString();
                    ;
                    alarm_severity3 = snapshot.child("27").child("alarm_severity").getValue().toString();
                    ;
                    Pump3ID = snapshot.child("27").child("pumpID").getValue().toString();
                    ;
                    alarm_text3 = snapshot.child("27").child("alarm_text").getValue(String.class);
                    String color3 = snapshot.child("27").child("alarm_severity").getValue().toString();
                    int colors3 = Integer.parseInt(color3);
                    if (colors3 == 1) {
                        Pump3.setBackgroundResource(R.color.yellow);
                    } else if (colors3 == 2) {
                        Pump3.setBackgroundResource(R.color.orange);
                    } else if (colors3 == 3) {
                        Pump3.setBackgroundResource(R.color.red);
                    } else {
                        Pump3.setBackgroundResource(R.color.green);
                    }
                    Pump3.setEnabled(true);
                    Pump3.setVisibility(View.VISIBLE);
                }
                if (pumpthisact >= 4) {
                    String Pump4drugstr = snapshot.child("28").child("drug").getValue(String.class);
                    String Pump4Ratestr = snapshot.child("28").child("currentRate").getValue().toString();
                    String Pump4startVolumestr = snapshot.child("28").child("startVolume").getValue().toString();
                    Pump4Rate.setText(Pump4Ratestr);
                    Pump4drug.setText(Pump4drugstr);
                    Pump4startVolume.setText(Pump4startVolumestr);
                    alarm4 = snapshot.child("28").child("alarm").getValue().toString();
                    ;
                    alarm_severity4 = snapshot.child("28").child("alarm_severity").getValue().toString();
                    ;
                    Pump4ID = snapshot.child("28").child("pumpID").getValue().toString();
                    ;
                    alarm_text4 = snapshot.child("28").child("alarm_text").getValue(String.class);
                    String color4 = snapshot.child("28").child("alarm_severity").getValue().toString();
                    int colors4 = Integer.parseInt(color4);
                    if (colors4 == 1) {
                        Pump4.setBackgroundResource(R.color.yellow);
                    } else if (colors4 == 2) {
                        Pump4.setBackgroundResource(R.color.orange);
                    } else if (colors4 == 3) {
                        Pump4.setBackgroundResource(R.color.red);
                    } else {
                        Pump4.setBackgroundResource(R.color.green);
                    }
                    Pump4.setEnabled(true);
                    Pump4.setVisibility(View.VISIBLE);


                }
                if (pumpthisact >= 5) {
                    String color5 = snapshot.child("29").child("alarm_severity").getValue().toString();
                    int colors5 = Integer.parseInt(color5);
                    if (colors5 == 1) {
                        Pump5.setBackgroundResource(R.color.yellow);
                    } else if (colors5 == 2) {
                        Pump5.setBackgroundResource(R.color.orange);
                    } else if (colors5 == 3) {
                        Pump5.setBackgroundResource(R.color.red);
                    } else {
                        Pump5.setBackgroundResource(R.color.green);
                    }

                    Pump5.setEnabled(true);
                    Pump5.setVisibility(View.VISIBLE);
                    Pump5drugstr = snapshot.child("29").child("drug").getValue(String.class);
                    Pump5Ratestr = snapshot.child("29").child("currentRate").getValue().toString();
                    ;
                    Pump5startVolumestr = snapshot.child("29").child("startVolume").getValue().toString();
                    Pump5Rate.setText(Pump5Ratestr);
                    Pump5drug.setText(Pump5drugstr);
                    Pump5startVolume.setText(Pump5startVolumestr);
                    alarm5 = snapshot.child("29").child("alarm").getValue().toString();
                    ;
                    alarm_severity5 = snapshot.child("29").child("alarm_severity").getValue().toString();
                    ;
                    Pump5ID = snapshot.child("29").child("pumpID").getValue().toString();
                    ;
                    alarm_text5 = snapshot.child("29").child("alarm_text").getValue(String.class);
                }
                if (pumpthisact >= 6) {
                    String color6 = snapshot.child("30").child("alarm_severity").getValue().toString();
                    int colors6 = Integer.parseInt(color6);
                    if (colors6 == 1) {
                        Pump6.setBackgroundResource(R.color.yellow);
                    } else if (colors6 == 2) {
                        Pump6.setBackgroundResource(R.color.orange);
                    } else if (colors6 == 3) {
                        Pump6.setBackgroundResource(R.color.red);
                    } else {
                        Pump6.setBackgroundResource(R.color.green);
                    }
                    Pump6.setEnabled(true);
                    Pump6.setVisibility(View.VISIBLE);

                    Pump6drugstr = snapshot.child("30").child("drug").getValue(String.class);
                    Pump6Ratestr = snapshot.child("30").child("currentRate").getValue().toString();
                    ;
                    Pump6startVolumestr = snapshot.child("30").child("startVolume").getValue().toString();
                    Pump6Rate.setText(Pump6Ratestr);
                    Pump6drug.setText(Pump6drugstr);
                    Pump6startVolume.setText(Pump6startVolumestr);
                    alarm6 = snapshot.child("30").child("alarm").getValue().toString();
                    ;
                    alarm_severity6 = snapshot.child("30").child("alarm_severity").getValue().toString();
                    ;
                    Pump6ID = snapshot.child("30").child("pumpID").getValue().toString();
                    ;
                    alarm_text6 = snapshot.child("30").child("alarm_text").getValue(String.class);
                }
                if (pumpthisact >= 7) {
                    String color7 = snapshot.child("31").child("alarm_severity").getValue().toString();
                    int colors7 = Integer.parseInt(color7);
                    if (colors7 == 1) {
                        Pump7.setBackgroundResource(R.color.yellow);
                    } else if (colors7 == 2) {
                        Pump7.setBackgroundResource(R.color.orange);
                    } else if (colors7 == 3) {
                        Pump7.setBackgroundResource(R.color.red);
                    } else {
                        Pump7.setBackgroundResource(R.color.green);
                    }
                    Pump7drugstr = snapshot.child("31").child("drug").getValue(String.class);
                    Pump7Ratestr = snapshot.child("31").child("currentRate").getValue().toString();
                    ;
                    Pump7startVolumestr = snapshot.child("31").child("startVolume").getValue().toString();
                    Pump7Rate.setText(Pump7Ratestr);
                    Pump7drug.setText(Pump7drugstr);
                    Pump7startVolume.setText(Pump7startVolumestr);
                    alarm7 = snapshot.child("31").child("alarm").getValue().toString();
                    ;
                    alarm_severity7 = snapshot.child("31").child("alarm_severity").getValue().toString();
                    ;
                    Pump7ID = snapshot.child("31").child("pumpID").getValue().toString();
                    ;
                    alarm_text7 = snapshot.child("31").child("alarm_text").getValue(String.class);
                    Pump7.setEnabled(true);
                    Pump7.setVisibility(View.VISIBLE);

                }
                if (pumpthisact >= 8) {
                    String color8 = snapshot.child("32").child("alarm_severity").getValue().toString();
                    int colors8 = Integer.parseInt(color8);
                    if (colors8 == 1) {
                        Pump8.setBackgroundResource(R.color.yellow);
                    } else if (colors8 == 2) {
                        Pump8.setBackgroundResource(R.color.orange);
                    } else if (colors8 == 3) {
                        Pump8.setBackgroundResource(R.color.red);
                    } else {
                        Pump8.setBackgroundResource(R.color.green);
                    }
                    Pump8drugstr = snapshot.child("32").child("drug").getValue(String.class);
                    Pump8Ratestr = snapshot.child("32").child("currentRate").getValue().toString();
                    ;
                    Pump8startVolumestr = snapshot.child("32").child("startVolume").getValue().toString();
                    Pump8Rate.setText(Pump8Ratestr);
                    Pump8drug.setText(Pump8drugstr);
                    Pump8startVolume.setText(Pump8startVolumestr);
                    alarm8 = snapshot.child("32").child("alarm").getValue().toString();
                    ;
                    alarm_severity8 = snapshot.child("32").child("alarm_severity").getValue().toString();
                    ;
                    Pump8ID = snapshot.child("32").child("pumpID").getValue().toString();
                    ;
                    alarm_text8 = snapshot.child("32").child("alarm_text").getValue(String.class);
                    Pump8.setEnabled(true);
                    Pump8.setVisibility(View.VISIBLE);
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

            Intent intent = new Intent(PumpActivity3.this, PumpActivity25.class);
            intent.putExtra("user", user);
            intent.putExtra("key", key);
            intent.putExtra("Pump", Pumps);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();

    }

    @Override
    protected void onSwipeLeft() {

    }

    private void clickListen() {
        Pump1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity3.this, Pump1.class);
                intent.putExtra("alarm", alarm1);
                intent.putExtra("Pump", Pumpp);
                intent.putExtra("ptindex", ptindex);
                intent.putExtra("pindex", "25");
                intent.putExtra("alarm_severity", alarm_severity1);
                intent.putExtra("drug", Pump1drugstr);
                intent.putExtra("rate", Pump1Ratestr);
                intent.putExtra("startVolume", Pump1startVolumestr);
                intent.putExtra("ID", Pump1ID);
                intent.putExtra("alarm_text", alarm_text1);
                intent.putExtra("user", user);
                intent.putExtra("key", key);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        Pump2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity3.this, Pump1.class);
                intent.putExtra("alarm", alarm2);
                intent.putExtra("alarm_severity", alarm_severity2);
                intent.putExtra("drug", Pump2drugstr);
                intent.putExtra("ptindex", ptindex);
                intent.putExtra("Pump", Pumpp);
                intent.putExtra("rate", Pump2Ratestr);
                intent.putExtra("pindex", "26");
                intent.putExtra("startVolume", Pump2startVolumestr);
                intent.putExtra("ID", Pump2ID);
                intent.putExtra("alarm_text", alarm_text2);
                intent.putExtra("user", user);
                intent.putExtra("key", key);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        Pump3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity3.this, Pump1.class);
                intent.putExtra("alarm", alarm3);
                intent.putExtra("alarm_severity", alarm_severity3);
                intent.putExtra("drug", Pump3drugstr);
                intent.putExtra("rate", Pump3Ratestr);
                intent.putExtra("ptindex", ptindex);
                intent.putExtra("Pump", Pumpp);
                intent.putExtra("startVolume", Pump3startVolumestr);
                intent.putExtra("ID", Pump3ID);
                intent.putExtra("pindex", "27");
                intent.putExtra("alarm_text", alarm_text3);
                intent.putExtra("user", user);
                intent.putExtra("key", key);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        Pump4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity3.this, Pump1.class);
                intent.putExtra("alarm", alarm4);
                intent.putExtra("alarm_severity", alarm_severity4);
                intent.putExtra("drug", Pump4drugstr);
                intent.putExtra("ptindex", ptindex);
                intent.putExtra("Pump", Pumpp);
                intent.putExtra("pindex", "28");
                intent.putExtra("rate", Pump4Ratestr);
                intent.putExtra("startVolume", Pump4startVolumestr);
                intent.putExtra("ID", Pump4ID);
                intent.putExtra("alarm_text", alarm_text4);
                intent.putExtra("user", user);
                intent.putExtra("key", key);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        Pump5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity3.this, Pump1.class);
                intent.putExtra("alarm", alarm5);
                intent.putExtra("alarm_severity", alarm_severity5);
                intent.putExtra("drug", Pump5drugstr);
                intent.putExtra("ptindex", ptindex);
                intent.putExtra("Pump", Pumpp);
                intent.putExtra("rate", Pump5Ratestr);
                intent.putExtra("startVolume", Pump5startVolumestr);
                intent.putExtra("ID", Pump5ID);
                intent.putExtra("pindex", "29");
                intent.putExtra("alarm_text", alarm_text5);
                intent.putExtra("user", user);
                intent.putExtra("key", key);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        Pump6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity3.this, Pump1.class);
                intent.putExtra("alarm", alarm1);
                intent.putExtra("pindex", "30");
                intent.putExtra("alarm_severity", alarm_severity6);
                intent.putExtra("drug", Pump6drugstr);
                intent.putExtra("ptindex", ptindex);
                intent.putExtra("Pump", Pumpp);
                intent.putExtra("rate", Pump6Ratestr);
                intent.putExtra("startVolume", Pump6startVolumestr);
                intent.putExtra("ID", Pump6ID);
                intent.putExtra("alarm_text", alarm_text6);
                intent.putExtra("user", user);
                intent.putExtra("key", key);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        Pump7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity3.this, Pump1.class);
                intent.putExtra("alarm", alarm7);
                intent.putExtra("alarm_severity", alarm_severity7);
                intent.putExtra("drug", Pump7drugstr);
                intent.putExtra("rate", Pump7Ratestr);
                intent.putExtra("ptindex", ptindex);
                intent.putExtra("Pump", Pumpp);
                intent.putExtra("pindex", "31");
                intent.putExtra("startVolume", Pump7startVolumestr);
                intent.putExtra("ID", Pump7ID);
                intent.putExtra("alarm_text", alarm_text7);
                intent.putExtra("user", user);
                intent.putExtra("key", key);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        Pump8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity3.this, Pump1.class);
                intent.putExtra("alarm", alarm8);
                intent.putExtra("alarm_severity", alarm_severity8);
                intent.putExtra("drug", Pump8drugstr);
                intent.putExtra("rate", Pump8Ratestr);
                intent.putExtra("ptindex", ptindex);
                intent.putExtra("Pump", Pumpp);
                intent.putExtra("pindex", "32");
                intent.putExtra("startVolume", Pump8startVolumestr);
                intent.putExtra("ID", Pump8ID);
                intent.putExtra("alarm_text", alarm_text8);
                intent.putExtra("user", user);
                intent.putExtra("key", key);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PumpActivity3.this, PatientActivity1.class);
                intent.putExtra("patients", patient);
                intent.putExtra("user", user);
                intent.putExtra("key", key);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });


    }
}
