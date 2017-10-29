package com.bmpl.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Notification.Builder notification = new Notification.Builder(this);
        notification.setContentTitle("Notification");
        notification.setContentText("This is my notification");
        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setDefaults(Notification.DEFAULT_ALL);
        //notification.setBadgeIconType(R.mipmap.ic_launcher_round);
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification.build());
    }
}
