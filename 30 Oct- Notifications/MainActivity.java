package com.bmpl.notifications;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, NewActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1001, intent, PendingIntent.FLAG_ONE_SHOT);

//        NotificationChannel notificationChannel = new NotificationChannel("Notification", "Noti",1);
//        notificationChannel.enableLights(true);

        Notification.Builder notification = new Notification.Builder(this);

        notification.setContentTitle("Notification");
        notification.setContentText("This is my notification");
        notification.setSmallIcon(R.mipmap.ic_launcher);
        //notification.setStyle(new Notification.BigTextStyle().bigText(""));
        //notification.setStyle(new Notification.BigPictureStyle().bigLargeIcon());

        //oreo and highest version

        notification.setDefaults(Notification.DEFAULT_ALL);

        Notification.Action action = new Notification.Action(R.mipmap.ic_launcher_round, "Open", pendingIntent);
        notification.setActions(action);
        //notification.setContentIntent(pendingIntent);
        //nougat and higher
        notification.addAction(action);

        // till marshmallow
        //notification.addAction(R.mipmap.ic_launcher_round, "Open", pendingIntent);

        notification.setAutoCancel(true);

        //notification.setBadgeIconType(R.mipmap.ic_launcher_round);

        NotificationManager notificationManager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification.build());

//        Notification.Builder notification1 = new Notification.Builder(this);
//        notification1.setContentTitle("New Notification");
//        notification1.setContentText("This is my new notification");
//        notification1.setSmallIcon(R.mipmap.ic_launcher);
//        notification1.setDefaults(Notification.DEFAULT_ALL);
//
//        //notification.setBadgeIconType(R.mipmap.ic_launcher_round);
//        NotificationManager notificationManager1 = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
//        notificationManager1.notify(2, notification1.build());


    }
}
