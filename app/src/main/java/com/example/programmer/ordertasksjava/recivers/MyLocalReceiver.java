package com.example.programmer.ordertasksjava.recivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MyLocalReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
     int x=intent.getIntExtra("num",-1);
     Intent intent1=new Intent();
     intent.putExtra("val",x);
      LocalBroadcastManager.getInstance(context).sendBroadcast(intent1);


    }
}
