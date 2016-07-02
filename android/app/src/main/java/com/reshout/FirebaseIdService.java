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

import polanski.option.Option;

public class FirebaseIdService extends FirebaseInstanceIdService {

    private static final String TAG = FirebaseIdService.class.getSimpleName();

    @NonNull
    private final OkHttpClient mClient = new OkHttpClient();

    public FirebaseIdService() {
        Log.d(TAG, "Starting");
    }

    @Override
    public void onTokenRefresh() {
        Log.d(TAG, "Refreshing");
        Option.ofObj(FirebaseInstanceId.getInstance().getToken())
              .ifSome(this::initialize)
              .ifNone(() -> Log.e(TAG, "Token is null"));

    }

    private void initialize(final String refreshedToken) {
        String url
                = "https://forces-assemble.herokuapp.com/api/v1/users/tomek_is_awesome/notification-token";
        String json = "{ \"notification-token\": \"" + refreshedToken + "\" }";

        String subscribeUrl
                = "https://forces-assemble.herokuapp.com/api/v1/channels/tomek_is_awesome_channel/subscribers";

        String subscribeJson = "{ \"user-id\": \"tomek_is_awesome\" }";
        try {
            put(url, json);
            post(subscribeUrl, subscribeJson);
        } catch (IOException e) {
            Log.e(TAG, "Error " + e);
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

    private String post(String url, String json) throws IOException {
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = mClient.newCall(request).execute();
        return response.body().string();
    }
}
