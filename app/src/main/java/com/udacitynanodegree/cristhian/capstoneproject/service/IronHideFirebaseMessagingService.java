package com.udacitynanodegree.cristhian.capstoneproject.service;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.udacitynanodegree.cristhian.capstoneproject.ui.activities.OrderReleasedActivity;
import com.udacitynanodegree.cristhian.capstoneproject.utils.NotificationUtils;

import java.util.Map;

public class IronHideFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "FCM Service";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(TAG, "¡Mensaje recibido!");
        Log.e(TAG, "From: " + remoteMessage.getFrom());
        displayNotification(remoteMessage.getNotification());


        Map<String, String> data = remoteMessage.getData();
        String price = data.get("price");
        Log.e(IronHideFirebaseMessagingService.class.getName(), "PRICE: ".concat(price));

        Log.e(IronHideFirebaseMessagingService.class.getName(), "FIREBASE MESSAGE: ".concat(remoteMessage.toString()));
        Log.e(IronHideFirebaseMessagingService.class.getName(), "NOTIFICATION DATA: ".concat(remoteMessage.getData().toString()));
    }


    private void displayNotification(RemoteMessage.Notification notification) {
//        startCashbackService();
        Intent intent = new Intent(this, OrderReleasedActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        NotificationUtils notificationUtils = new NotificationUtils(getBaseContext());
//        NotificationCompat.Builder notificationBuilder = notificationUtils.getNotification(notification, pendingIntent);
        NotificationCompat.Builder notificationBuilder = notificationUtils.getNotification(notification, null);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());
    }


    public void startCashbackService() {
        Intent intent = new Intent(this, ShowAlertService.class);
        startService(intent);
    }
}
