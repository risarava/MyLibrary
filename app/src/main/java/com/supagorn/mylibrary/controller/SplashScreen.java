package com.supagorn.mylibrary.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.supagorn.mylibrary.MainActivity;
import com.supagorn.mylibrary.R;

/**
 * Created by iabellah on 2016-04-25.
 */
public class SplashScreen extends Activity {
    private static int splashInterval = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splashscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent i;
                    i = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(i);
                //jeda selesai Splashscreen
                finish();
            }
        }, splashInterval);
    }
}
