package com.ks.dynamic.icon.launcher;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Parcelable;
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
//        removeShortcut(mContext.getString(R.string.app_name));
//        if (num > 0) {
//            int resid = mContext.getResources().getIdentifier("icon_" + num, "mipmap", mContext.getPackageName());
//            createShortCut(resid, R.string.app_name);
//        } else {
//            createShortCut(R.mipmap.ic_launcher, R.string.app_name);
//        }
    }

    public void createShortCut(int iconResId,
                               int appnameResId) {
        try {
            // com.android.launcher.permission.INSTALL_SHORTCUT
            Intent shortcutintent = new Intent(
                    "com.android.launcher.action.INSTALL_SHORTCUT");
            // 不允许重复创建
            shortcutintent.putExtra("duplicate", false);
            // 需要现实的名称
            shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_NAME,
                    mContext.getString(appnameResId));
            // 快捷图片
            Parcelable icon = Intent.ShortcutIconResource.fromContext(
                    mContext, iconResId);
            shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
            shortcutintent.putExtra("nihao", iconResId);
            // 点击快捷图片，运行的程序主入口
            shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_INTENT,
                    new Intent(mContext, MainActivity.class));
            // 发送广播
            mContext.sendBroadcast(shortcutintent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final String ACTION_REMOVE_SHORTCUT = "com.android.launcher.action.UNINSTALL_SHORTCUT";

    private void removeShortcut(String name) {
        try {
            // remove shortcut的方法在小米系统上不管用，在三星上可以移除
            Intent intent = new Intent(ACTION_REMOVE_SHORTCUT);
            // 名字
            intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);

            // 设置关联程序
            Intent launcherIntent = new Intent(mContext,
                    MainActivity.class).setAction(Intent.ACTION_MAIN);
            intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, launcherIntent);
            // 发送广播
            mContext.sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
