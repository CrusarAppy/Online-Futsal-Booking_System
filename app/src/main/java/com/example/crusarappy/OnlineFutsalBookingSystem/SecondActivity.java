package com.example.crusarappy.OnlineFutsalBookingSystem;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.support.annotation.NonNull;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class SecondActivity extends AppCompatActivity {
    private EditText inputEmail, inputPassword, inputName, inputContactNumber, inputAddress;
    private ProgressDialog progressBar;
    private FirebaseAuth auth;
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        inputName = findViewById(R.id.fullName);
        inputAddress = findViewById(R.id.address);
        inputContactNumber = findViewById(R.id.phoneNumber);
        inputEmail =  findViewById(R.id.email);
        inputPassword =  findViewById(R.id.password);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        progressBar = new ProgressDialog(this);
        db = FirebaseFirestore.getInstance();

        // goto register to login
        Button prev = (Button) findViewById(R.id.loginDirection);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(SecondActivity.this, FirstActivity.class);
                startActivity(i);
                finish();
            }
        });

        //validation of the user input
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

                //create user with email and password
             auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(SecondActivity.this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful()) {
                Toast.makeText(SecondActivity.this, "Registered Successful! ", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(SecondActivity.this, WelcomeHome.class));
                finish();
                String uid = auth.getCurrentUser().getUid();
                addUserInfo(uid);
            }else {

                progressBar.dismiss();
                if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                    Toast.makeText(SecondActivity.this, "User with this email already exist.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(SecondActivity.this, "Registration failed! Please check your Network Settings.",
                        Toast.LENGTH_SHORT).show();
//
            }
        }
    });
}
        });
    }
        // add data to database
        public void addUserInfo(String uid){
            Map <String, Object> info = new HashMap<>();
            info.put("Fullname", inputName.getText().toString());
            info.put("Address", inputAddress.getText().toString());
            info.put("Mobile Number", inputContactNumber.getText().toString());

            db.collection("users").document(uid)
                    .set(info)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.e("appy","Vayo");
                        }
                    });

        }


}




