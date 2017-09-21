package com.ks.dynamic.icon.launcher;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * Created by Admin on 2017/9/21 0021 13:47.
 * Author: kang
 * Email: kangsafe@163.com
 */

public class LauncherUtils {
    private PackageManager mPackageManager;
    //默认组件
    private Context mContext;

    public LauncherUtils(WeakReference<Context> contextWeakReference) {
        this.mContext = contextWeakReference.get();
        //获取到包管理类实例
        mPackageManager = mContext.getPackageManager();
    }

    /**
     * 启动组件
     *
     * @param componentName 组件名
     */
    private void enableComponent(ComponentName componentName) {
        //此方法用以启用和禁用组件，会覆盖Androidmanifest文件下定义的属性
        mPackageManager.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    /**
     * 禁用组件
     *
     * @param componentName 组件名
     */
    private void disableComponent(ComponentName componentName) {
        mPackageManager.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }

    public void updateLauncher(int day) {
        Log.i("DAY", day + "");
        int num = day % 5;
        if (num == 0) {
            enableComponent(new ComponentName(mContext, MainActivity.class));
        } else {
            disableComponent(new ComponentName(mContext, MainActivity.class));
        }
        //如果没有，则显示默认图标
        for (int i = 1; i <= 5; i++) {
            ComponentName componentName = new ComponentName(mContext, "com.ks.dynamic.icon.launcher.MainActivity" + i);
            if (num == i) {
                enableComponent(componentName);
            } else {
                disableComponent(componentName);
            }
        }
    }
}
