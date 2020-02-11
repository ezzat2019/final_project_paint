package com.example.programmer.ordertasksjava.threads;

import android.os.SystemClock;
import android.util.Log;

import java.util.concurrent.Callable;

public class seThread implements Runnable {

    @Override
    public void run() {
        for (int i=0 ;i<30;i++)
        {
            Log.d("ezzat",i+" se");
            SystemClock.sleep(1000);
        }

    }
}
