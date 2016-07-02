package com.reshout;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;

import java.util.Map;

import polanski.option.function.Action0;

import static polanski.option.Option.ofObj;

public class FirebaseMessageService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(final RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Map<String, String> data = remoteMessage.getData();

        ofObj(data.get("title"))
                .lift(ofObj(data.get("body")), (t, b) -> (Action0) () -> sendNotification(t, b))
                .ifSome(Action0::call);
    }

    private void sendNotification(@NonNull final String title, @NonNull final String body) {
        NotificationManager notificationManager
                = ((NotificationManager) getBaseContext()
                .getSystemService(Context.NOTIFICATION_SERVICE));
        Notification notification =
                new NotificationCompat.Builder(getBaseContext())
                        .setSmallIcon(R.drawable.com_auth0_ic_email)
                        .setContentTitle(title)
                        .setContentText(body)
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
