package com.app.trafficclient;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {
    public static RequestQueue queue;
    public static Context appContext;
    public static List<String> stringList;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        queue= Volley.newRequestQueue(getApplicationContext());
        stringList = new ArrayList<>();
    }

}
