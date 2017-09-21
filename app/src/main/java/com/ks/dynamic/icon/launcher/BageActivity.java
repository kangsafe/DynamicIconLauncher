package com.ks.dynamic.icon.launcher;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Random;

/**
 * Created by Admin on 2017/9/21 0021 16:31.
 * Author: kang
 * Email: kangsafe@163.com
 */
public class BageActivity extends AppCompatActivity {


    private Handler handler = new Handler();

    private Runnable runnable = new MyRunnable();

    public class MyRunnable implements Runnable {

        @Override
        public void run() {
            handler.postDelayed(runnable, 5000);
            BadgeUtil.setBadgeCount(getApplicationContext(), getCount(), R.mipmap.ic_launcher);
        }
    }

    private int getCount() {

        return new Random().nextInt(100);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = (Button) findViewById(R.id.start);
        Button stop = (Button) findViewById(R.id.stop);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (handler == null) {
                    handler = new Handler();
                }

                if (runnable == null) {
                    runnable = new MyRunnable();
                }

                handler.post(runnable);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (handler != null) {
                    BadgeUtil.resetBadgeCount(getApplicationContext(), R.mipmap.ic_launcher);
                    handler.removeCallbacksAndMessages(null);
                    runnable = null;
                    handler = null;
                }
            }
        });
    }
}