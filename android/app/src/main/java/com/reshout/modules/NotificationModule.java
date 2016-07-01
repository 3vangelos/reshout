package com.reshout.modules;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.reshout.MainActivity;
import com.reshout.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class NotificationModule extends ReactContextBaseJavaModule {

    @Override
    public String getName() {
        return "NotificationModule";
    }

    public NotificationModule(final ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @ReactMethod
    public void sendNotification() {
        NotificationManager notificationManager
                = ((NotificationManager) getReactApplicationContext()
                .getSystemService(Context.NOTIFICATION_SERVICE));
        Notification notification =
                new NotificationCompat.Builder(getReactApplicationContext())
                        .setSmallIcon(R.drawable.com_auth0_ic_email)
                        .setContentTitle("ReShout")
                        .setContentText("Hello!")
                        .setContentIntent(createIntent())
                        .build();

        notificationManager.notify("ReShout", 1, notification);
    }

    private PendingIntent createIntent() {
        return PendingIntent.getActivity(
                getReactApplicationContext(),
                0,
                new Intent(getReactApplicationContext(), MainActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
