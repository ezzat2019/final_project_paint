package com.example.programmer.ordertasksjava.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

public class AlramReciver extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.d("eeeeeeeeeeeee","ezzat");

        Intent intent1=new Intent(context,AlarmIntentService.class);
        intent1.putExtra("eee","ezzat");
        context.startForegroundService(intent1);
    }
}
