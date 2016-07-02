package com.reshout;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;

public class FirebaseIdService extends FirebaseInstanceIdService {

    @NonNull
    private final OkHttpClient mClient = new OkHttpClient();

    public FirebaseIdService() {
        Log.d("FirebaseService", "Starting");
    }

    @Override
    public void onTokenRefresh() {
        Log.d("FirebaseService", "Refreshing");
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        if (refreshedToken != null) {
            String url
                    = "https://forces-assemble.herokuapp.com/api/v1/users/tomek_is_awesome/notification-token";

            String json = "{ \"notification-token\": \"" + refreshedToken + "\" }";
            try {
                put(url, json);
            } catch (IOException e) {
                Log.e("FirebaseService", "Error " + e);
            }
        } else {
            Log.e("FirebaseService", "Token is null");
        }

    }

    private String put(String url, String json) throws IOException {
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();
        Response response = mClient.newCall(request).execute();
        return response.body().string();
    }
}
