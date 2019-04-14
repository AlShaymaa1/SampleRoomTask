package com.sampletask.presentation;

import android.app.Application;
import android.content.Context;

import com.sampletask.presentation.core.ActivitiesLifecycleCallbacks;
import com.sampletask.usecases.domain.database.RoomModule;

public class MyApplication extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();

        ActivitiesLifecycleCallbacks activitiesLifecycleCallbacks = new ActivitiesLifecycleCallbacks();
        registerActivityLifecycleCallbacks(activitiesLifecycleCallbacks);
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }

}