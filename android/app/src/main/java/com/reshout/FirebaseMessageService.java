package com.reshout;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class FirebaseMessageService extends FirebaseMessagingService {

    public FirebaseMessageService() {
        Log.d("FirebaseMessageService", "Starting");
    }

    @Override
    public void onMessageReceived(final RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("FirebaseMessageService", "Received " + remoteMessage.getData());
        sendNotification();
    }

    public void sendNotification() {
        NotificationManager notificationManager
                = ((NotificationManager) getBaseContext()
                .getSystemService(Context.NOTIFICATION_SERVICE));
        Notification notification =
                new NotificationCompat.Builder(getBaseContext())
                        .setSmallIcon(R.drawable.com_auth0_ic_email)
                        .setContentTitle("ReShout")
                        .setContentText("Hello!")
                        .setContentIntent(createIntent())
                        .build();

        notificationManager.notify("ReShout", 1, notification);
    }

    private PendingIntent createIntent() {
        return PendingIntent.getActivity(
                getBaseContext(),
                0,
                new Intent(getBaseContext(), MainActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
