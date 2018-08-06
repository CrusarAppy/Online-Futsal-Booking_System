package com.example.crusarappy.OnlineFutsalBookingSystem;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneActivity extends AppCompatActivity {

    EditText sendNUmber, receivedNumber;
    Button getCode,receivedCode;
    FirebaseAuth auth;
    String codeSent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);


        sendNUmber=  findViewById(R.id.editTextPhone);
        receivedNumber =findViewById(R.id.editText4);

        auth =FirebaseAuth.getInstance();

        getCode= findViewById(R.id.verificationCode);
        getCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendVerificationCode();

            }
        });


        receivedCode=findViewById(R.id.verify);
        receivedCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifySignInCode();
            }
        });
    }

    private void verifySignInCode(){
        String code = sendNUmber.getText().toString();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent,code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getApplicationContext(),
                                "Registered successful",Toast.LENGTH_SHORT).show();
    
                            FirebaseUser user = task.getResult().getUser();
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                           
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(getApplicationContext(),
                                "verification failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
    

    private void sendVerificationCode (){
        String phone =sendNUmber.getText().toString();

        if (phone.isEmpty()){
            sendNUmber.setError("Phone Number is required");
            sendNUmber.requestFocus();
            return;
        }
        if (phone.length()< 10){
            sendNUmber.setError("enter a valid phone");
            sendNUmber.requestFocus();
            return;

        }

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                PhoneActivity.this, // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks

    }

     PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
         @Override
         public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

         }

         @Override
         public void onVerificationFailed(FirebaseException e) {

         }

         @Override
         public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
             super.onCodeSent(s, forceResendingToken);

             codeSent =s;

         }
     };

}

