package com.example.programmer.ordertasksjava.orderd_task;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

import com.example.programmer.ordertasksjava.R;

public class NotificationUtils {
    private static final String NOT1ID = "n1";
    private  volatile static  NotificationUtils ourInstance=null ;
    private Context context;

    public static NotificationUtils getInstance(Context context) {
        if (ourInstance==null)
        {
            synchronized (NotificationUtils.class)
            {
                if (ourInstance==null)
                {
                    ourInstance=new NotificationUtils(context);
                }
            }
        }
        return ourInstance;
    }

    private NotificationUtils(Context context) {
        this.context=context;
    }

    Notification getNotification(String title, String text)
    {
        Notification notification;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel=new NotificationChannel(NOT1ID,"not1", NotificationManager.IMPORTANCE_LOW);
            NotificationManager manager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);

           notification=new Notification.Builder(context,NOT1ID)
                    .setContentText(text)
                    .setContentTitle(title)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .build();



        }else {
            notification=new Notification.Builder(context)
                    .setContentText(text)
                    .setContentTitle(title)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .build();
        }
        return notification;

    }
}
