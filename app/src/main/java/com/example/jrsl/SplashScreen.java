package com.example.jrsl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {
    SharedPreferences sharedPref;

    private ProgressBar splashProgressBar;
    private Timer timer;
    private int i = 0;

    public static final String accessKey = "access";
    public static final String accessPrefs = "AccessPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //loading preferences
        sharedPref = getSharedPreferences(accessPrefs, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        //Add default user and products into db if first access
        if(sharedPref.getInt(accessKey,1)!=0) {
            DatabaseHandler db = new DatabaseHandler(this);
            db.addDefaultUser();
            db.addAllProducts();
            db.addDefaultCartItems();
            db.addDefaultOrders();
            Log.d(null, "Added DB Stuff");

            editor.putInt(accessKey, 0);
            editor.commit();
        }

        splashProgressBar = findViewById(R.id.progressBarSplashScreen);
        timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (i<100) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                    splashProgressBar.setProgress(i);
                    i++;
                } else {
                    timer.cancel();
                    Intent intent = new Intent(SplashScreen.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 0, 30);

    }
}