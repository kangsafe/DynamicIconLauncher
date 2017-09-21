package com.ks.dynamic.icon.launcher;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Created by Admin on 2017/9/21 0021 16:01.
 * Author: kang
 * Email: kangsafe@163.com
 */
public class IconScript extends Drawable {
    public boolean isRuning = false;
    public FastBitmapDrawable mFastBitmapDrawable = null;
    protected Paint mPaint = new Paint();

    public IconScript(){
        mPaint.setAntiAlias(true);
        mPaint.setFilterBitmap(true);
    }

    public void draw(Canvas canvas){
        if(mFastBitmapDrawable != null){
            canvas.drawBitmap(mFastBitmapDrawable.getBitmap(), null, getBounds(),mPaint);//画底图
        }
    }

    /**
     * 运行脚本
     * @param view
     */
    public void run(View view){
        isRuning = true;
    }

    /**
     * 停止脚本
     * (未调用，暂留入口)
     */
    public void onStop(){
        isRuning = false;
    }

    /**
     * 暂停脚本
     * (未调用，暂留入口)
     */
    public void onPause(){
        isRuning = false;
    }

    /**
     * 恢复脚本
     * (未调用，暂留入口)
     */
    public void onResume(){
        isRuning = true;
    }

    @Override
    public int getOpacity() {
        // TODO Auto-generated method stub
        return PixelFormat.UNKNOWN;
    }

    @Override
    public void setAlpha(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setColorFilter(ColorFilter arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getIntrinsicWidth() {
        int width = getBounds().width();
        if (width == 0) {
            width = mFastBitmapDrawable.getBitmap().getWidth();
        }
        return width;
    }

    @Override
    public int getIntrinsicHeight() {
        int height = getBounds().height();
        if (height == 0) {
            height = mFastBitmapDrawable.getBitmap().getHeight();
        }
        return height;
    }

    @Override
    public int getMinimumWidth() {
        return getBounds().width();
    }

    @Override
    public int getMinimumHeight() {
        return getBounds().height();
    }

    @Override
    public void setFilterBitmap(boolean filterBitmap) {
        mPaint.setFilterBitmap(filterBitmap);
        mPaint.setAntiAlias(filterBitmap);
    }

    public void setFastBitmapDrawable(FastBitmapDrawable drawable){
        mFastBitmapDrawable = drawable;
    }

    public FastBitmapDrawable setFastBitmapDrawable(){
        return mFastBitmapDrawable;
    }
}
