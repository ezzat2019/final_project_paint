package com.example.programmer.ordertasksjava.recivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MyLocalReceiver extends BroadcastReceiver {

// todo ezzat
    // غير اللوجن ده وخليه رجيست
    //

    @Override
    public void onReceive(Context context, Intent intent) {

        // an Intent broadcast.
     int x=intent.getIntExtra("num",-1);
     Intent intent1=new Intent();
     intent.putExtra("val",x);
      LocalBroadcastManager.getInstance(context).sendBroadcast(intent1);


    }
}
