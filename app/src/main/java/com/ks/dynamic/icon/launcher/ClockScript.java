package com.ks.dynamic.icon.launcher;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.format.Time;
import android.view.View;

/**
 * Created by Admin on 2017/9/21 0021 16:02.
 * Author: kang
 * Email: kangsafe@163.com
 */

 /* 实现桌面时钟Icon动态显示。
         * 2015.4.1
         * @author gholl
        *
        */
public class ClockScript extends IconScript {
    Rect mRect = null;
    /**
     * 效果展示目标View
     */
    private View mView;
    /**
     * 通知系统更新视图现成
     */
    private ClockThread mClockThread = null;
    /**
     * 当前是否显示在屏幕上
     */
    private boolean mIsShowInScreen = false;

    public ClockScript() {
        super();
    }

    public void run(View view) {
        mView = view;
        mRect = getBounds();
        if (mClockThread == null) {
            mClockThread = new ClockThread();
            mClockThread.start();
        }
    }

    @Override
    public void onPause() {
        mClockThread.pauseRun();
        super.onPause();
    }

    @Override
    public void onResume() {
        mClockThread.resumeRun();
        super.onResume();
    }

    @Override
    public void onStop() {
        mClockThread.stopRun();
        super.onStop();
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        mIsShowInScreen = true;
//      mPaint.setColor(Color.WHITE);
//      canvas.drawCircle(mRect.centerX(), mRect.centerY(), mRect.height()/2, mPaint);
//      mPaint.setColor(Color.BLACK);
//      canvas.drawLine(mRect.centerX(), 0, mRect.centerX(), 10, mPaint);
//      canvas.drawLine(mRect.centerX(), mRect.height(), mRect.centerX(), mRect.height()-10, mPaint);
//      canvas.drawLine(mRect.left, mRect.centerY(), mRect.left + 10, mRect.centerY(), mPaint);
//      canvas.drawLine(mRect.right, mRect.centerY(), mRect.right-10, mRect.centerY(), mPaint);
        drawIndicator(canvas, mRect.centerX(), mRect.centerY(), mPaint);
//      mPaint.setColor(Color.MAGENTA);
        canvas.drawCircle(mRect.centerX(), mRect.centerY(), 3, mPaint);

        if (mClockThread.wait) {
            mClockThread.resumeRun();
        }
    }

    /**
     * 画指针
     *
     * @param canvas
     * @param centerX
     * @param centerY
     * @param p
     */
    private void drawIndicator(Canvas canvas, int centerX, int centerY, Paint p) {

        Time t = new Time();
        t.setToNow();
        p.setStrokeWidth(2);
        p.setColor(Color.BLACK);
        //时针
        canvas.drawLine(centerX, centerY,
                (int) (centerX + (mRect.width() / 2 - 35) * Math.cos((t.hour + (float) t.minute / 60) * (Math.PI / 6) - Math.PI / 2)),
                (int) (centerY + (mRect.width() / 2 - 35) * Math.sin((t.hour + (float) t.minute / 60) * (Math.PI / 6) - Math.PI / 2)), p);
        canvas.drawLine(centerX, centerY,
                (int) (centerX + (mRect.width() / 2 - 50) * Math.cos((t.hour + (float) t.minute / 60) * (Math.PI / 6) + Math.PI / 2)),
                (int) (centerY + (mRect.width() / 2 - 50) * Math.sin((t.hour + (float) t.minute / 60) * (Math.PI / 6) + Math.PI / 2)), p);
//      p.setColor(Color.GREEN);
        //分针
        canvas.drawLine(centerX, centerY,
                (int) (centerX + (mRect.width() / 2 - 27) * Math.cos(t.minute * (Math.PI / 30) - Math.PI / 2)),
                (int) (centerY + (mRect.width() / 2 - 27) * Math.sin(t.minute * (Math.PI / 30) - Math.PI / 2)), p);
        canvas.drawLine(centerX, centerY,
                (int) (centerX + (mRect.width() / 2 - 48) * Math.cos(t.minute * (Math.PI / 30) + Math.PI / 2)),
                (int) (centerY + (mRect.width() / 2 - 48) * Math.sin(t.minute * (Math.PI / 30) + Math.PI / 2)), p);
        p.setColor(Color.RED);
        //秒针
        canvas.drawLine(centerX, centerY,
                (int) (centerX + (mRect.width() / 2 - 24) * Math.cos(t.second * (Math.PI / 30) - Math.PI / 2)),
                (int) (centerY + (mRect.width() / 2 - 24) * Math.sin(t.second * (Math.PI / 30) - Math.PI / 2)), p);
        p.setStrokeWidth(3);
        canvas.drawLine(centerX, centerY,
                (int) (centerX + (mRect.width() / 2 - 45) * Math.cos(t.second * (Math.PI / 30) + Math.PI / 2)),
                (int) (centerY + (mRect.width() / 2 - 45) * Math.sin(t.second * (Math.PI / 30) + Math.PI / 2)), p);

    }

    class ClockThread extends Thread {
        int times = 0;
        boolean running = true;

        public boolean wait = false;

        public void stopRun() {
            running = false;
            synchronized (this) {
                this.notify();
            }
        }

        ;

        public void pauseRun() {
            this.wait = true;
            synchronized (this) {
                this.notify();
            }
        }

        public void resumeRun() {
            this.wait = false;
            synchronized (this) {
                this.notify();
            }
        }

        public void run() {
            while (running) {
                synchronized (mView) {
                    mView.postInvalidate();
                }

                if (!mIsShowInScreen) {
                    pauseRun();
                }
                mIsShowInScreen = false;
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    System.out.println(e);
                }

                synchronized (this) {
                    if (wait) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

}
