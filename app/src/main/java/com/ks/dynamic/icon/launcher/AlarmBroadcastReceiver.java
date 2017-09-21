package com.ks.dynamic.icon.launcher;

/**
 * Created by Admin on 2017/7/4 0004 10:56.
 * Author: kang
 * Email: kangsafe@163.com
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.Date;

import static com.ks.dynamic.icon.launcher.AlarmUtils.ACTION_ALARM_SET;

/**
 * 闹钟广播接收器 * Created by chuxin on 2016/5/4.
 */
public class AlarmBroadcastReceiver extends BroadcastReceiver {
    private final String TAG = getClass().getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.i(TAG, action);
        if (action.equals(ACTION_ALARM_SET) || action.equals("android.intent.action.BOOT_COMPLETED")) {
            Date date = new Date();
            int m = date.getMinutes();
            LauncherUtils u = new LauncherUtils(new WeakReference<Context>(context));
            u.updateLauncher(m);
        }
    }
}