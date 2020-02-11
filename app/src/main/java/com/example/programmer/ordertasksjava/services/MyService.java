package com.example.programmer.ordertasksjava.services;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();


    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {

        ExecutorService service= Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                int n1=intent.getIntExtra("num1",0);
                int n2=intent.getIntExtra("num2",0);
                Log.d("nnnnnnnnnnn",(n1+n2)+"");

            }
        });



        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
     return null;
    }
}
