package com.example.crusarappy.OnlineFutsalBookingSystem;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.support.annotation.NonNull;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SecondActivity extends AppCompatActivity {
    private EditText inputEmail, inputPassword, inputName, inputContactNumber, inputAddress;
    private ProgressDialog progressBar;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        inputName = (EditText) findViewById(R.id.fullName);
        inputAddress = (EditText) findViewById(R.id.address);
        inputContactNumber = (EditText) findViewById(R.id.phoneNumber);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);


        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        progressBar = new ProgressDialog(this);


        Button prev = (Button) findViewById(R.id.loginDirection);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SecondActivity.this, FirstActivity.class);
                startActivity(i);
                finish();
            }
        });

        Button welcome =  findViewById(R.id.register);
        welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email))

                {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password))

                {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6)

                {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setMessage("Registering User");
                progressBar.show();
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SecondActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                               if(task.isSuccessful()) {
                                   Toast.makeText(SecondActivity.this, "Registered Successful! ", Toast.LENGTH_SHORT).show();

                                   startActivity(new Intent(SecondActivity.this, WelcomeHome.class));
                                   finish();
                               }else {
                                    Toast.makeText(SecondActivity.this, "Registration failed! Please check your Network Settings.",
                                            Toast.LENGTH_SHORT).show();
//
                                    }
                                progressBar.dismiss();
                            }
                        });

            }



        });
    }


//
}




