package com.example.crusarappy.OnlineFutsalBookingSystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class CheckActivity extends AppCompatActivity {
    int [] IMAGES= {R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a};

    String [] NAMES = {
            "B Fit Sports (Futsal)","Champion Futsal & Paintball Pvt. Ltd.","G & S Futsal Ground","Ranipauwa Sports Center","Top Corner Futsal","Barahi Futsal Arena","Amarsingh Sports Center Pvt. Ltd."};

    String [] Address ={ "Address1","Address1","Address1","Address1","Address1","Address1","Address1"};

    String [] Contact ={"Contact1","Contact1","Contact1","Contact1","Contact1","Contact1","Contact1"};
    ListView a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        a = findViewById(R.id.buttonlist1);



    }
}
