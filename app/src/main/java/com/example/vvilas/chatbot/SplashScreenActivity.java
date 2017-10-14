package com.example.vvilas.chatbot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by vvilas on 14/10/2017.
 */

public class SplashScreenActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrarMain();
            }
        }, 2000);
    }

    private void mostrarMain() {
        Intent intent = new Intent(SplashScreenActivity.this,
                MainActivity.class);
        startActivity(intent);
        finish();
    }

}