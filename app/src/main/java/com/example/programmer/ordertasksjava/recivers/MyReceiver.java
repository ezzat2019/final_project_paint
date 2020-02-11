package com.example.programmer.ordertasksjava.recivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.programmer.ordertasksjava.MainActivity;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //Log.d("ezzat",intent.getAction()+"");
        //int x=intent.getIntExtra("num",-1);
       // MainActivity.viewModel.addInt(x*2);

    }
}
