package com.reshout;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;

import polanski.option.Option;
import polanski.option.function.Func1;

public class FirebaseIdService extends FirebaseInstanceIdService {

    private static final String TAG = FirebaseIdService.class.getSimpleName();

    private static final String REGISTRATION_REQUEST
            = "https://forces-assemble.herokuapp.com/api/v1/users/tomek_is_awesome/notification-token";

    private static final String CHANNEL_REGISTRATION_REQUEST
            = "https://forces-assemble.herokuapp.com/api/v1/channels/tomek_is_awesome_channel/subscribers";

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

    private void initialize(@NonNull final String refreshedToken) {
        try {
            put(REGISTRATION_REQUEST, getNotificationRequestBody(refreshedToken));
            post(CHANNEL_REGISTRATION_REQUEST, getChannelRequestBody());
            Log.d(TAG, "Registration successful");
        } catch (IOException e) {
            Log.e(TAG, "Error " + e);
        }
    }

    @NonNull
    private static String getNotificationRequestBody(@NonNull String refreshedToken) {
        return "{ \"notification-token\": \"" + refreshedToken + "\" }";
    }

    @NonNull
    private static String getChannelRequestBody() {
        return "{ \"user-id\": \"tomek_is_awesome\" }";
    }

    private void put(String url, String json) throws IOException {
        request(url, json, builder -> builder::put);
    }

    private void post(String url, String json) throws IOException {
        request(url, json, builder -> builder::post);
    }

    private void request(String url, String json,
                         Func1<Request.Builder, Func1<RequestBody, Request.Builder>> addFields)
            throws IOException {
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = addFields.call(new Request.Builder()).call(body).url(url).build();
        mClient.newCall(request).execute();
    }

}
