package com.ks.dynamic.icon.launcher;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.graphics.Bitmap;
import android.os.UserHandle;

import java.util.HashMap;

/**
 * Created by Admin on 2017/9/21 0021 15:57.
 * Author: kang
 * Email: kangsafe@163.com
 */
public class IconCache {
    private static class CacheEntry {
        public Bitmap icon;
        public CharSequence title;
        public CharSequence contentDescription;
        public IconScript script;//icon的脚本
    }

    CacheEntry entry;
    private IconCache mCache;
    private LauncherApps mLauncherApps;

    public IconScript getScript(Intent intent, UserHandle user) {
        synchronized (mCache) {
            ComponentName component = intent.getComponent();
            if (component == null) {
                return null;
            }
            LauncherActivityInfo launcherActInfo = mLauncherApps.resolveActivity(intent, user);
            CacheEntry entry = cacheLocked(component, launcherActInfo, null, user, true, -1);
            return entry.script;
        }
    }

    private CacheEntry cacheLocked(ComponentName componentName, LauncherActivityInfo info,
                                   HashMap<Object, CharSequence> labelCache, UserHandle user,
                                   boolean usePackageIcon, int unreadNum) {
        if (componentName.getPackageName().equals("com.android.deskclock"))
            entry.script = new ClockScript();
        if (componentName.getPackageName().equals("com.android.calendar"))
            entry.script = new CalendarScript();

        return entry;
    }
}