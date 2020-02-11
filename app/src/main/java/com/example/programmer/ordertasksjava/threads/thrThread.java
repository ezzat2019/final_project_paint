package com.example.programmer.ordertasksjava.threads;

import android.os.SystemClock;
import android.util.Log;

import java.util.concurrent.Callable;

public class thrThread implements Callable<Integer> {


    @Override
    public Integer call() throws Exception {
        for (int i=0 ;i<30;i++)
        {
            Log.d("ezzat",i+" thr");
            SystemClock.sleep(1000);
        }
        return 9;
    }
}
