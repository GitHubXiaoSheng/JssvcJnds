package com.app.trafficclient.activity;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityArray {

    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void deleteActivity(){
        for (int i = 0; i < activities.size(); i++) {
            activities.get(i).finish();
        }

    }

}
