package com.example.crusarappy.OnlineFutsalBookingSystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class WelcomeHome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth auth;
    private ProgressDialog progressBar;
    private FirebaseFirestore db;

    TextView v;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        auth = FirebaseAuth.getInstance();
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        String uid =currentFirebaseUser.getUid();
        Toast.makeText(WelcomeHome.this, ""+ uid, Toast.LENGTH_SHORT).show();


        progressBar = new ProgressDialog(this);

        v = findViewById(R.id.textView2);
        db=FirebaseFirestore.getInstance();

        DocumentReference docRef = db.collection("users").document(uid);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    Toast.makeText(WelcomeHome.this, ""+ document.exists(), Toast.LENGTH_SHORT).show();
                    if (document.exists()) {

                        String name = document.getString("Fullname");
                        String address = document.getString("Address");
                        String mob = document.getString("Mobile Number");
                        Toast.makeText(WelcomeHome.this, ""+name, Toast.LENGTH_SHORT).show();
                        v.setText("Fullname: "+ name +"\n" + "Address: " + address +"\n" + "Mobile Number : "+ mob );
                    } else {


                        Toast.makeText(WelcomeHome.this, "No documents", Toast.LENGTH_SHORT).show();


                           }
                } else {
                    Toast.makeText(WelcomeHome.this, "failed", Toast.LENGTH_SHORT).show();
                    task.getException();
                }
            }
        });

        Button manage =findViewById(R.id.manage1);
        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(WelcomeHome.this,AccountActivity.class);
                startActivity(i);
                finish();
            }
        });

        Button next = (Button) findViewById(R.id.logout);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //logout
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(WelcomeHome.this, FirstActivity.class);
                startActivity(i);
                finish();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.welcome_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.futsal_list) {
            Intent i = new Intent(WelcomeHome.this, futsal_list.class);
            startActivity(i);

        } else if (id == R.id.futsal_nearby) {
            Intent i = new Intent(WelcomeHome.this, futsalNearby.class);
            startActivity(i);


        } else if (id == R.id.tournaments) {
            Intent i = new Intent(WelcomeHome.this, Tournament.class);
            startActivity(i);


        } else if (id == R.id.About_us) {
            Intent i = new Intent(WelcomeHome.this, About_us.class);
            startActivity(i);


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
