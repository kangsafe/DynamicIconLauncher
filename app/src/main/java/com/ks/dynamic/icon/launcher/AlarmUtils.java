package com.ks.dynamic.icon.launcher;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Admin on 2017/7/4 0004 11:46.
 * Author: kang
 * Email: kangsafe@163.com
 */

public class AlarmUtils {
    //闹钟同步
    public static final String ACTION_ALARM_SET = "com.ks.dynamic.icon.launcher.action.alarm.set";

    /**
     * 取消闹钟
     */
    public static void cancelAlarm(Context context, int seconds) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(ACTION_ALARM_SET);
        alarmManager.cancel(PendingIntent.getBroadcast(context, seconds, intent, 0));
    }

    /**
     * 设置闹钟
     *
     * @param context
     */
    public static void setAlarm(Context context, long seconds) {
        Date date = new Date(seconds);
        Calendar now = Calendar.getInstance();
        Calendar targetTime = (Calendar) now.clone();
        targetTime.set(Calendar.MONTH, date.getMonth());
        targetTime.set(Calendar.YEAR, date.getYear());
        targetTime.set(Calendar.HOUR_OF_DAY, date.getHours());
        targetTime.set(Calendar.MINUTE, date.getMinutes());
        targetTime.set(Calendar.SECOND, 0);
        targetTime.set(Calendar.MILLISECOND, 0);
        Intent intent = new Intent(ACTION_ALARM_SET);
        setAlarmOnce(context, AlarmManager.RTC_WAKEUP, targetTime.getTimeInMillis(), 0, intent);
    }

    /**
     * @param context
     * @param type
     * @param triggerAtMillis
     * @param intervalMillis
     * @param requestCode
     */
    private static void setAlarm(Context context, int type, long triggerAtMillis, long intervalMillis, int requestCode, Intent intent) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(type, triggerAtMillis, intervalMillis,
                PendingIntent.getBroadcast(context, requestCode, intent, 0));
    }

    /**
     * @param context
     * @param type
     * @param triggerAtMillis
     * @param requestCode
     */
    private static void setAlarmOnce(Context context, int type, long triggerAtMillis, int requestCode, Intent intent) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(type, triggerAtMillis,
                PendingIntent.getBroadcast(context, requestCode, intent, 0));
    }
}
