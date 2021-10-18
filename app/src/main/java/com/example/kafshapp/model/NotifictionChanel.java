package com.example.kafshapp.model;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;

public class NotifictionChanel extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel notifictionChanel=new NotificationChannel("myapp","kafshapp",NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(notifictionChanel);
        }

    }
}
