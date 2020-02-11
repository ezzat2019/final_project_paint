package com.example.programmer.ordertasksjava.orderd_task;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;

public class OrderdTask {
    public static final String ACTION_SHARE = "clear_all_notifications";
    public static final String ACTION_CLEAR_ALL_NOTIFICATION = "clear_all_notifications2";
    public static final String ACTION_BACKUP_DATABASE = "backup_database";
    public static final String ACTION_SYNC_ROOM_FIREBASE_DATABASE = "sync_room_firebase_database";
    public static final String ACTION_FETCH_API = "fetch_api";

    private OrderdTask(){}
    public static void executeTask(Context context, String action, Bundle extras) {
        if (ACTION_CLEAR_ALL_NOTIFICATION.equals(action)) {
            clearNotifications(context);
        } else if (ACTION_SHARE.equals(action)) {
            shareNotification(context, extras);
        } else if (ACTION_BACKUP_DATABASE.equals(action)) {

        } else if (ACTION_SYNC_ROOM_FIREBASE_DATABASE.equals(action)) {

        } else if (ACTION_FETCH_API.equals(action)) {
            fetchAPI();
        }

    }


    private static void clearNotifications(Context context) {

        NotificationManager manager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(2,NotificationUtils.getInstance(context).getNotification("orderd","clear"));
    }

    private static void shareNotification(Context context, Bundle extras) {


        NotificationManager manager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(3,        NotificationUtils.getInstance(context).getNotification("orderd","share"));
    }

    private static void fetchAPI() {

    }
}
