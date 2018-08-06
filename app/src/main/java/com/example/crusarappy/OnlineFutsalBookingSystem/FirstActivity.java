package com.example.crusarappy.OnlineFutsalBookingSystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class FirstActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private ProgressDialog progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        auth = FirebaseAuth.getInstance();
        progressBar = new ProgressDialog(this);
        inputEmail = (EditText) findViewById(R.id.EmailPhone);
        inputPassword = (EditText) findViewById(R.id.password);

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(FirstActivity.this, WelcomeHome.class));
            finish();
        }


        Button next = (Button) findViewById(R.id.signUp);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(i);
                finish();
            }
        });

        Button welcome =(Button) findViewById(R.id.loginUser);
        welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length()<6) {
                    Toast.makeText(getApplicationContext(), "Enter more than 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setMessage("Authenticating User");
                progressBar.show();

                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(FirstActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        inputPassword.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(FirstActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(FirstActivity.this, WelcomeHome.class);
                                    startActivity(intent);
                                    finish();
                                }
                                progressBar.dismiss();
                            }
                        });
            }
        });
    }


}


