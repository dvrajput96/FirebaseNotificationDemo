package com.example.pc.firebasenotificationdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by pc on 1/3/18.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FCM Service";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO: Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated.
        String longText = remoteMessage.getNotification().getBody();

        // Prepare intent which is triggered if the
        // notification is selected
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        // Build notification
        // Actions are just fake
        Notification noti = new Notification.Builder(this)
                .setContentTitle("From: " + remoteMessage.getFrom())
                .setContentText("Notification Message Body: " + remoteMessage.getNotification().getBody())
                .setSmallIcon(R.drawable.ic_email_black_24dp)
                .setContentIntent(pIntent)
                .setStyle(new Notification.BigTextStyle().bigText(longText))
                .setLights(Color.CYAN, 1, 1)
                .setSound(Uri.EMPTY)
                .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                .addAction(R.drawable.ic_email_black_24dp, "Call", pIntent)
                .addAction(R.drawable.ic_email_black_24dp, "More", pIntent)
                .addAction(R.drawable.ic_email_black_24dp, "And more", pIntent).build();

        //Added defaults
       /* int defaults = 0;
        defaults |= android.app.Notification.DEFAULT_SOUND;
        defaults |= android.app.Notification.DEFAULT_VIBRATE;
        noti.setDefaults(defaults);*/
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, noti);
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
    }
   /* public void createNotification() {
        String longText = "Hello my frds how are you i m fine thank you bla bla bla";

        // Prepare intent which is triggered if the
        // notification is selected
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        // Build notification
        // Actions are just fake
        Notification noti = new Notification.Builder(this)
                .setContentTitle("From: " + remoteMessage.getFrom())
                .setContentText("Subject")
                .setSmallIcon(R.drawable.ic_email_black_24dp)
                .setContentIntent(pIntent)
                .setStyle(new Notification.BigTextStyle().bigText(longText))
                .setLights(Color.CYAN, 1, 1)
                .setSound(Uri.EMPTY)
                .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                .addAction(R.drawable.ic_email_black_24dp, "Call", pIntent)
                .addAction(R.drawable.ic_email_black_24dp, "More", pIntent)
                .addAction(R.drawable.ic_email_black_24dp, "And more", pIntent).build();

        //Added defaults
       *//* int defaults = 0;
        defaults |= android.app.Notification.DEFAULT_SOUND;
        defaults |= android.app.Notification.DEFAULT_VIBRATE;
        noti.setDefaults(defaults);*//*
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, noti);

    }*/
}