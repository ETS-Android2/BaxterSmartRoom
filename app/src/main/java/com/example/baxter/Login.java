package com.example.baxter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    Button login;
    EditText username, password;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
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
        usersRef.addListenerForSingleValueEvent(valueEventListener);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginStart();
            }
        });


    }

    private void loginStart() {
        if (validatePassword() && validateUsername()) {
            isUser();
        }
    }


    private Boolean validateUsername() {
        String val = username.getText().toString();
        if (val.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;
        } else {
            username.setError(null);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = password.getText().toString();
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }

    private void isUser() {
        String userEnteredUsername = username.getText().toString().trim();
        String userEnteredPassword = password.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot1) {
                for(DataSnapshot uniqueKeySnapshot : snapshot1.getChildren()){
                    //Loop 1 to go through all the child nodes of users
                    key = uniqueKeySnapshot.getKey();
                    Log.d("key", key);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error1) {

            }
        });

        /*String key2 = "-MzmWhp7WMEmmo8TOcq_";
        if (key == key2) {
          Log.d("key status", "It worked I got key");
        }  else {
            Log.d("key status", "Duck it failed");
            key = key2;
        }*/
        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("users").child(key);
        Query checkUser = reference2.orderByKey().equalTo(userEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String passwordFromDB = snapshot.child(userEnteredUsername).child("password").getValue(String.class);
                    if (passwordFromDB.equals(userEnteredPassword)) {
                        String name = snapshot.child(userEnteredUsername).child("name").getValue(String.class);
                        String patient = snapshot.child(userEnteredUsername).child("n_patients").getValue().toString();
                        Intent intent2 = new Intent(getApplicationContext(), PatientActivity1.class);
                        intent2.putExtra("name", name);
                        intent2.putExtra("patients", patient);
                        intent2.putExtra("user", userEnteredUsername);
                        intent2.putExtra("key", key);
                        Toast.makeText(Login.this, "Welcome " + name, Toast.LENGTH_LONG).show();
                        intent2.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent2);
                        finish();
                    } else {
                        password.setError("Wrong Password");
                        Toast.makeText(Login.this, "Wrong Password", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    username.setError("No such User exist");
                    Toast.makeText(Login.this, "No such User exist", Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}