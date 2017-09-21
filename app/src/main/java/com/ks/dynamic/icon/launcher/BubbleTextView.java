package com.ks.dynamic.icon.launcher;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Admin on 2017/9/21 0021 16:01.
 * Author: kang
 * Email: kangsafe@163.com
 */

public class BubbleTextView extends android.support.v7.widget.AppCompatTextView {
    IconScript mScript;

    public BubbleTextView(Context context) {
        super(context);
    }

    public BubbleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BubbleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void applyFromShortcutInfo(ShortcutInfo info, IconCache iconCache,
                                      boolean setDefaultPadding, boolean promiseStateChanged) {
        mScript = info.getScript(iconCache);
    }

    @Override
    public void setCompoundDrawables(Drawable left, Drawable top,
                                     Drawable right, Drawable bottom) {
        if (top != null) {
            if (mScript != null) {
                top = mScript;
                mScript.setBounds(top.getBounds());
                if (!mScript.isRuning)
                    mScript.run(this);
            }
        }

        super.setCompoundDrawables(left, top, right, bottom);
    }
}
