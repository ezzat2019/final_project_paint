package com.example.programmer.ordertasksjava.alarm;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;


public class MyWorker extends Worker {


    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        String name;

        if (getInputData().getString("name").equals(""))
        {
          name="no";
        }
        else
        {
          name=getInputData().getString("name");

        }

        Log.d("eeeeeee",name);
        Intent intent=new Intent(getApplicationContext(),AlramReciver.class);
        getApplicationContext().sendBroadcast(intent);

        return Result.success(new Data.Builder().putString("name",name).build());
    }
}
