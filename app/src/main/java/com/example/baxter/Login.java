package com.example.baxter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.baxter.ui.login.LoginActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.view.View;

public class Login extends AppCompatActivity {
    Button login;
    TextInputLayout username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        username.findViewById(R.id.username);
        password.findViewById(R.id.password);
    }

    private Boolean validateUsername() {
        String val = username.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w(4,20)\\z";
        if (val.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w(4,20)\\z";
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }
    public void loginUser(View view) {
        //validate login info
        if (validatePassword() && validateUsername()) {
            isUser();
        }
    }

    private void isUser() {
        String userEnteredUsername = username.getEditText().getText().toString().trim();
        String userEnteredPassword = password.getEditText().getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredPassword);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String passwordFromDB = snapshot.child(userEnteredUsername).child("password").getValue(String.class);
                    if (passwordFromDB.equals(userEnteredPassword)) {
                        String name = snapshot.child(userEnteredUsername).child("name").getValue(String.class);
                        String patient = snapshot.child(userEnteredUsername).child("patients").getValue(String.class);
                        Intent intent2 = new Intent(getApplicationContext(), PatientActivity1.class);
                        intent2.putExtra("name", name);
                        intent2.putExtra("patients", patient);
                        intent2.putExtra("user", userEnteredUsername);
                        Toast.makeText(Login.this, "Welcome" + name, Toast.LENGTH_LONG).show();
                        intent2.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent2);
                        finish();
                    } else {
                        password.setError("Wrong Password");
                    }
                } else {
                    username.setError("No such User exist");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}