package com.supagorn.mylibrary.controller;

import android.app.Application;

import com.facebook.FacebookSdk;

// Setup Realm in your Application
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}
