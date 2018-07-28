package com.example.crusarappy.OnlineFutsalBookingSystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;


public class splashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();


        Thread myThread = new Thread() {

            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent intent = new Intent(splashActivity.this, FirstActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        myThread.start();
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//        setContentView(R.layout.activity_splash);
//        getSupportActionBar().hide();
//        LogoLauncher logoLauncher = new LogoLauncher();
//        logoLauncher.start();
//
//    }
//
//    private class LogoLauncher extends Thread{
//        public void run(){
//            try{
//                sleep(1000 * SLEEP_TIMER);
//            }catch(InterruptedException e){
//                e.printStackTrace();
//            }
//
//            Intent intent = new Intent(splashActivity.this, FirstActivity.class);
//            startActivity(intent);
//            splashActivity.this.finish();
//        }
    }
}
