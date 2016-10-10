package com.supagorn.mylibrary.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by iabellah on 2016-10-10.
 */

public class MyBroadcastReceiver {
    // receive message
    public static void registerMessageReceiver (Context context, OnIntentFilterListener onIntentFilterListener, final OnRegisterMessageListener onRegisterMessageListener) {
        BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // Get extra data included in the Intent
                onRegisterMessageListener.onReceive(context, intent);
            }
        };

        IntentFilter intentFilter = new IntentFilter();
        onIntentFilterListener.addFilter(intentFilter);
        LocalBroadcastManager.getInstance(context).registerReceiver(mMessageReceiver,
                intentFilter);
    }

    // send message without extras
    public static void sendMessage(Context context, String message) {
        Intent intent = new Intent(message);
        // You can also include some extra data.
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    // send extras and message
    public static void sendMessage(Context context, String message, OnSendMessageListener onSendMessageListener) {
        Intent intent = new Intent(message);
        onSendMessageListener.onPutExtras(intent);
        // You can also include some extra data.
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    // add intentFilter to registerReceiver
    public interface OnIntentFilterListener {
        void addFilter(IntentFilter intentFilter);
    }

    // for put extras
    public interface OnSendMessageListener {
        void onPutExtras(Intent intent);
    }

    // support multiple message
    public interface OnRegisterMessageListener {
        void onReceive(Context context, Intent intent);
    }

}
