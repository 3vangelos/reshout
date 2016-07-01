package com.reshout;

import com.auth0.lock.react.LockReactPackage;
import com.facebook.react.ReactActivity;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends ReactActivity {

    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "ReShout";
    }

    /**
     * Returns whether dev mode should be enabled.
     * This enables e.g. the dev menu.
     */
    @Override
    protected boolean getUseDeveloperSupport() {
        return BuildConfig.DEBUG;
    }

    /**
     * A list of packages used by the app. If the app uses additional views
     * or modules besides the default ones, add more packages here.
     */
    @Override
    protected List<ReactPackage> getPackages() {
        return Arrays.<ReactPackage>asList(
                new MainReactPackage(),
                new LockReactPackage()
        );
    }

    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);

        sendNotification();
    }

    private void sendNotification() {
        NotificationManager notificationManager = ((NotificationManager) getBaseContext()
                .getSystemService(Context.NOTIFICATION_SERVICE));
        Notification notification =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.com_auth0_ic_email)
                        .setContentTitle(getMainComponentName())
                        .setContentText("Hello!")
                        .setContentIntent(createIntent())
                        .build();

        notificationManager.notify(getMainComponentName(), 1, notification);
    }

    private PendingIntent createIntent() {
        return PendingIntent.getActivity(
                this,
                0,
                new Intent(this, MainActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
