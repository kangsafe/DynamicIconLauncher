package com.ks.dynamic.icon.launcher;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by Admin on 2017/9/21 0021 13:50.
 * Author: kang
 * Email: kangsafe@163.com
 */

public class MyApp extends Application {
    public static Context CONTEXT;

    @Override
    public void onCreate() {
        super.onCreate();
        setAlarms();
        CONTEXT = getApplicationContext();
    }

    private void setAlarms() {
        Intent intent = new Intent(AlarmUtils.ACTION_ALARM_SET);
        AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 60, alarmIntent);
    }
}
