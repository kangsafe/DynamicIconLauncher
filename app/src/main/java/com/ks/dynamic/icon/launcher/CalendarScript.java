package com.ks.dynamic.icon.launcher;

/**
 * Created by Admin on 2017/9/21 0021 16:03.
 * Author: kang
 * Email: kangsafe@163.com
 */

import android.app.LauncherActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.LauncherApps;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.AppLaunchChecker;
import android.text.format.Time;
import android.view.View;

/**
 * 实现桌面日历Icon动态显示。
 * @author gholl
 *
 */
public class CalendarScript extends IconScript {
    private float mDensity = 1.5f;
    Time mTime = new Time();
    Context mContext;
    public CalendarScript() {
        super();
        mContext = MyApp.CONTEXT;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        mTime.setToNow();
        canvas.drawBitmap(getRes(mTime.monthDay), null, getBounds(), mPaint);
    }

    @Override
    public void run(final View view) {

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_DATE_CHANGED);
        filter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
        view.getContext().registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                view.postInvalidate();
            }
        }, filter);

        super.run(view);
    }

    private Bitmap getRes(int monthDay){
        int res = mContext.getResources().getIdentifier("ic_launcher_calendar_" + monthDay, "drawable", mContext.getPackageName());
        BitmapDrawable bitmapDrawable = (BitmapDrawable)mContext.getResources().getDrawable(res);
        return bitmapDrawable.getBitmap();
    }
}
