package com.reshout;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import android.util.Log;

public class FirebaseMessageService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(final RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("FirebaseMessageService", "Received " + remoteMessage.getTo());
    }
}
