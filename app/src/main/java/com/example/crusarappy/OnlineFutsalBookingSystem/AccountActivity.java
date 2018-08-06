package com.example.crusarappy.OnlineFutsalBookingSystem;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AccountActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    EditText namefield,phonefield, addressfield;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        final String uid =currentFirebaseUser.getUid();

        namefield= findViewById(R.id.editTextName);
        phonefield=findViewById(R.id.editTextPhone);
        addressfield=findViewById(R.id.editTextAddress);
        save = findViewById(R.id.save);


        DocumentReference docRef = db.collection("users").document(uid);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    Toast.makeText(AccountActivity.this, ""+ document.exists(), Toast.LENGTH_SHORT).show();
                    if (document.exists()) {

                        String name = document.getString("Fullname");
                        String address = document.getString("Address");
                        String mob = document.getString("Mobile Number");
                        namefield.setText( name);
                        addressfield.setText(address);
                        phonefield.setText(mob);
                        } else {


                        Toast.makeText(AccountActivity.this, "No documents", Toast.LENGTH_SHORT).show();


                    }
                } else {
                    Toast.makeText(AccountActivity.this, "failed", Toast.LENGTH_SHORT).show();
                    task.getException();
                }
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUserInfo(uid);
                Toast.makeText(AccountActivity.this, "Successfully updated", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void addUserInfo(String uid){
        Map<String, Object> info = new HashMap<>();
        info.put("Fullname", namefield.getText().toString());
        info.put("Address", addressfield.getText().toString());
        info.put("Mobile Number", phonefield.getText().toString());

        db.collection("users").document(uid)
                .update(info)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.e("appy","Vayo");
                    }
                });

    }
}
