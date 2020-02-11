package com.example.programmer.ordertasksjava.alarm;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.RequiresApi;


public class AlarmIntentService extends IntentService {


    public AlarmIntentService() {
        super("AlarmIntentService");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();

        NotificationChannel channel=new NotificationChannel("ea","not1", NotificationManager.IMPORTANCE_HIGH);
        Notification notification=new Notification.Builder(this,"ea")
                .setContentTitle("")
                .setContentText("")
                .build();
        NotificationManager manager= (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
        startForeground(2,notification);

    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.d("eeeeeeeeed",intent.getStringExtra("eee"));

     for (int i=0;i<5;i++)
     {
         SystemClock.sleep(500);
         Log.d("eeeeeeeee",i+"");
     }


    }


}
