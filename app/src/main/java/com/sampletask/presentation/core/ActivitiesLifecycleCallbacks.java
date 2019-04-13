package com.sampletask.presentation.core;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.sampletask.presentation.MyApplication;
import com.sampletask.presentation.feature.listItems.AlarmBroadcastReceiver;

import java.util.HashSet;

import static android.content.Context.ALARM_SERVICE;

public class ActivitiesLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

    private HashSet<Activity> activityInstance = new HashSet<Activity>();
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        initAlert();
        activityInstance.add(activity);
        alarmManager.cancel(pendingIntent);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        activityInstance.remove(activity);
       if(activityInstance.isEmpty())
        startAlert();
    }


    private void startAlert() {
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + (5 * 1000), pendingIntent);
        Toast.makeText(MyApplication.getAppContext(), "Alarm set in " + 5 + " seconds", Toast.LENGTH_LONG).show();
    }

    private void initAlert() {
        Intent intent = new Intent(MyApplication.getAppContext(), AlarmBroadcastReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(
                MyApplication.getAppContext(), 234324243, intent, 0);
        alarmManager = (AlarmManager) MyApplication.getAppContext().getSystemService(ALARM_SERVICE);
    }

}
