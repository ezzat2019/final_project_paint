package com.example.programmer.ordertasksjava.threads;

import android.os.SystemClock;
import android.util.Log;

import java.util.concurrent.Callable;

public class FirstThread implements Runnable {



    @Override
    public void run() {
        for (int i=0 ;i<30;i++)
        {
            Log.d("ezzat",i+" fr");
            SystemClock.sleep(1000);
        }

    }
}
