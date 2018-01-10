package com.udacitynanodegree.cristhian.capstoneproject.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;
import com.udacitynanodegree.cristhian.capstoneproject.BuildConfig;

public class NotificationUtils extends ContextWrapper {

    private NotificationManager notificationManager;
    private final String ANDROID_CHANNEL_ID = BuildConfig.APPLICATION_ID.concat(".android");
    private final String ANDROID_CHANNEL_NAME = "ANDROID CHANNEL";
    public static String ACTION_NOTIFY_ORDER_RELEASED = "ACTION_NOTIFY_ORDER_RELEASED";

    public NotificationUtils(Context base) {
        super(base);
        createChannel();
    }

    private void createChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel androidChannel = new NotificationChannel(ANDROID_CHANNEL_ID, ANDROID_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            androidChannel.enableLights(true);
            androidChannel.enableVibration(true);
            androidChannel.setLightColor(Color.GREEN);
            androidChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            getManager().createNotificationChannel(androidChannel);
        }
    }

    private NotificationManager getManager() {
        if (notificationManager == null) {
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notificationManager;
    }

    public NotificationCompat.Builder getNotification(RemoteMessage.Notification notification, PendingIntent pendingIntent) {
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder androidNotification;
        androidNotification = getBuilderByAndroidVersion();
        androidNotification.setSmallIcon(android.R.drawable.stat_notify_more)
                .setContentTitle(notification.getTitle())
                .setContentText(notification.getBody())
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
//                .setContentIntent(pendingIntent);
        ;
        return androidNotification;
    }

    @NonNull
    private NotificationCompat.Builder getBuilderByAndroidVersion() {
        NotificationCompat.Builder androidNotification;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            androidNotification = new NotificationCompat.Builder(getApplicationContext(), ANDROID_CHANNEL_ID);

        } else {
            androidNotification = new NotificationCompat.Builder(getApplicationContext());
        }
        return androidNotification;
    }
}
