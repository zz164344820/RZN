package com.rzn.commonbaselib.listener;

import android.view.View;

import java.util.Calendar;

public abstract class NoDoubleClickListener implements View.OnClickListener {

    public static final String MIN_CLICK_DELAY_TIME_2000s = "2000";
    public static final String MIN_CLICK_DELAY_TIME_1000s = "1000";
    private long lastClickTime = 0;
    private long currentDelayTime = 0;

    @Override
    public void onClick(View v) {

        currentDelayTime = Integer.valueOf(NoDoubleClickListener.MIN_CLICK_DELAY_TIME_1000s);

        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > currentDelayTime) {
            lastClickTime = currentTime;
            onNoDoubleClick(v);
        }
    }

    protected abstract void onNoDoubleClick(View v);


}