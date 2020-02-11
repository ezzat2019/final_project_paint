package com.example.programmer.ordertasksjava;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.example.programmer.ordertasksjava.alarm.AlramReciver;
import com.example.programmer.ordertasksjava.alarm.MyWorker;
import com.example.programmer.ordertasksjava.orderd_task.OrderdIntentService;
import com.example.programmer.ordertasksjava.orderd_task.OrderdTask;
import com.example.programmer.ordertasksjava.recivers.MyReceiver;
import com.example.programmer.ordertasksjava.recivers.MyViewModel;
import com.example.programmer.ordertasksjava.services.MyIntentService;
import com.example.programmer.ordertasksjava.services.MyService;
import com.example.programmer.ordertasksjava.threads.FirstThread;
import com.example.programmer.ordertasksjava.threads.seThread;
import com.example.programmer.ordertasksjava.threads.thrThread;

import java.time.Duration;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    MyReceiver myReceiver;
    private TextView mResultTextView;
    private Handler mHandler = new Handler();
    public static MyViewModel viewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        startActivity(new Intent(getApplicationContext(),Main2Activity.class));
        finish();


    }

    private void orderedIntent() {
        Intent intent=new Intent(getApplicationContext(), OrderdIntentService.class);
        intent.setAction(OrderdTask.ACTION_SHARE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent);
        }
        else
        {
            startService(intent);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void worker() {
WorkManager.getInstance().cancelAllWork();
        Constraints constraints=new Constraints.Builder()
                .setRequiresDeviceIdle(false)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        Data data=new Data.Builder()
                .putString("name","ezzat")
                .build();
        PeriodicWorkRequest oneTimeWorkRequest=
               new PeriodicWorkRequest.Builder(MyWorker.class, 10,TimeUnit.SECONDS)
                .setConstraints(constraints)
                .setInputData(data)
                       .addTag("wezz")
                .build();

        WorkManager.getInstance().enqueueUniquePeriodicWork("wezz",
                ExistingPeriodicWorkPolicy.REPLACE,oneTimeWorkRequest);

        WorkManager.getInstance().getWorkInfosByTagLiveData("wezz")
                .observe(this, new Observer<List<WorkInfo>>() {
                    @Override
                    public void onChanged(List<WorkInfo> workInfos) {
                        Log.d("eeeeee55555555", workInfos.get(0).getOutputData().getString("name")+"");
                    }
                });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void services() {
        mResultTextView=findViewById(R.id.txt);

//        Intent intent=new Intent(this, MyService.class);
//        intent.putExtra("num1",3);
//        intent.putExtra("num2",5);
//
//     startForegroundService(intent);

        ResultFronService resultFronService=new ResultFronService(null);
        Intent intent = new Intent(MainActivity.this, MyIntentService.class);
        intent.putExtra("ezz", "yarab");
        intent.putExtra("n3",resultFronService);

        startForegroundService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);


    }

    private void broadcast() {
        myReceiver = new MyReceiver();

        viewModel = ViewModelProviders.of(this).get(MyViewModel.class);


        viewModel.getLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("final", integer + "");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
      /*  Intent intent=new Intent(MainActivity.this,MyReceiver.class);

        sendBroadcast(intent);
        IntentFilter intentFilter=new IntentFilter();

        intentFilter.addAction("android.intent.action.AIRPLANE_MODE");
        registerReceiver(myReceiver,intentFilter);*/
    }


@RequiresApi(api = Build.VERSION_CODES.Q)
private void alarm()
{

    IntentFilter intentFilter=new IntentFilter();
    intentFilter.addAction("android.intent.action.AIRPLANE_MODE");
    registerReceiver(new AlramReciver(),intentFilter);


// repet based on time run of mobile
//        PendingIntent pendingIntent=PendingIntent
//                .getBroadcast(getApplicationContext(),1,new Intent(this,AlramReciver.class)
//                ,PendingIntent.FLAG_UPDATE_CURRENT);
//        int type= AlarmManager.ELAPSED_REALTIME;
//        long time=SystemClock.elapsedRealtime()+AlarmManager.INTERVAL_HALF_DAY;
//        AlarmManager manager= (AlarmManager) getSystemService(ALARM_SERVICE);
//        manager.set(type,time,pendingIntent);
    PendingIntent pendingIntent=PendingIntent
            .getBroadcast(getApplicationContext(),1,new Intent(this,AlramReciver.class)
                    ,PendingIntent.FLAG_UPDATE_CURRENT);
    int type= AlarmManager.RTC;

Log.d("eeeeee","test");
    Calendar calendar=Calendar.getInstance();
    calendar.set(Calendar.YEAR,2020);
    calendar.set(Calendar.MONTH,1);
    calendar.set(Calendar.DAY_OF_MONTH,31);
    calendar.set(Calendar.HOUR_OF_DAY,5);
    calendar.set(Calendar.MINUTE,34);
    AlarmManager manager= (AlarmManager) getSystemService(ALARM_SERVICE);
    long x=System.currentTimeMillis()+5000;
    manager.set(type,x,pendingIntent);

}
    @Override
    protected void onPause() {
        super.onPause();
        // unregisterReceiver(myReceiver);
    }

    private void threadandexecuters() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.execute(new FirstThread());
        executorService.execute(new seThread());
        Future<Integer> x = executorService.submit(new thrThread());

        try {
            Log.d("ezzatfsdsuttttttt", x.get() + "");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        AsyncTask<String, Void, Integer> asyncTask = new AsyncTask<String, Void, Integer>() {
            @Override
            protected Integer doInBackground(String... strings) {
                SystemClock.sleep(10000);

                return strings.length;
            }
        };

        try {
            Log.d("ezzat", asyncTask.executeOnExecutor(Executors.newSingleThreadExecutor(), "ezzat"
                    , "ali").get() + "");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void click(View view) {


    }

    private class ResultFronService extends ResultReceiver
    {

        public ResultFronService(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            if (resultCode==20&&resultData!=null)
            {
                mResultTextView.setText(resultData.getString("ezzz"));
            }
        }
    }


}
