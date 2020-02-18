package com.example.programmer.ordertasksjava.orderd_task;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;



public class OrderdIntentService extends IntentService {
int y=20;

    public OrderdIntentService() {
        super("OrderdIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
       Notification notification= NotificationUtils.getInstance(getApplicationContext())
                .getNotification("intent service","");
       startForeground(1,notification);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
String action=intent.getAction();
OrderdTask.executeTask(getApplicationContext(),action,intent.getExtras());

    }


}
