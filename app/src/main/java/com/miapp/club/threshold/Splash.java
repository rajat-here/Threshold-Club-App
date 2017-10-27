package com.miapp.club.threshold;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.database.FirebaseDatabase;

public class Splash extends Activity {
    @Override
    public void onCreate(Bundle icil) {
        super.onCreate(icil);
        setContentView(R.layout.splashscreen);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        int secondsDelayed = 2;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(Splash.this, MainActivity.class));
                finish();
            }
        }, secondsDelayed * 1000);
    }
}