package com.example.shining.libutils.utilslib.etc;


import android.os.Handler;
import android.os.SystemClock;

public class TimeManager {

    private Runnable mTicker;
    private Handler mHandler;
    private boolean mTickerStopped = false;

    public void onAttachedToWindow() {
        mTickerStopped = false;
        mHandler = new Handler();

        mTicker = new Runnable() {
            public void run() {
                if (mTickerStopped) return;
                long time = System.currentTimeMillis();
                if (mListener != null) { mListener.onTimeChange(time);}

                long now = SystemClock.uptimeMillis(); // 25395838
                long next = now + (60000 - now % 60000);
                mHandler.postAtTime(mTicker, next);
            }
        };
        mTicker.run();
    }

    public void onDetachedFromWindow() {
        mTickerStopped = true;
    }

    private TimeChangeListener mListener;

    public void listen(TimeChangeListener li) {
        mListener = li;
    }

    public interface TimeChangeListener {
        void onTimeChange(long time);
    }
}
