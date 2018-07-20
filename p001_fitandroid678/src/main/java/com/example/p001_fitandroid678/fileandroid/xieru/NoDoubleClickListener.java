package com.example.p001_fitandroid678.fileandroid.xieru;

import android.view.View;

import java.util.Calendar;

/**
 * Created by BJColor on 2018/1/24.
 */

public abstract class NoDoubleClickListener implements View.OnClickListener {

    public long MIN_CLICK_DELAY_TIME = 1000;
    private long lastClickTime = 0;
    private long no_doule_time;

//    /**
//     * 设置秒数
//     */
//
//    public NoDoubleClickListener(final int i) {
//        if (i != 0) MIN_CLICK_DELAY_TIME = i * 1000;
//    }
//
//    /**
//     * 设置秒数
//     */
//
//    public NoDoubleClickListener(final double i) {
//        if (i != 0) MIN_CLICK_DELAY_TIME = (int) (i * 1000);
//    }

    /**
     * 设置秒数
     */

    public NoDoubleClickListener(final long i) {
        if (i != 0) {
           MIN_CLICK_DELAY_TIME = i;
        }

    }

    /**
     * 默认1秒
     */
    public NoDoubleClickListener() {
        no_doule_time = MIN_CLICK_DELAY_TIME;
    }

    protected abstract void onSingleClick(View v);
    protected abstract void onDoubleClick(View v);

    @Override
    public void onClick(View v) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            onDoubleClick(v);
        }else {
            onSingleClick(v);
        }
//        Object tag = v.getTag(v.getId());
//        long beforeTimemiles = tag != null ? (long) tag : 0;
//        long timeInMillis = Calendar.getInstance().getTimeInMillis();
//        v.setTag(v.getId(), timeInMillis);
//        if(timeInMillis - beforeTimemiles < no_doule_time){
//            onSingleClick(v);
//        }else {
//            onDoubleClick(v);
//        }
    }

    public static boolean isDoubleClick(View v, long no_doule_time) {
        Object tag = v.getTag(v.getId());
        long beforeTimemiles = tag != null ? (long) tag : 0;
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        v.setTag(v.getId(), timeInMillis);
        return timeInMillis - beforeTimemiles < no_doule_time;
    }


}
