package com.example.shining.libutils.utilslib.app;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetAccessHelper {
    public static final int MAX_RETRY_COUNT = 3;
    public static final int PING_TIMEOUT = 5000;
    public static final String PING_ADDR = "www.baidu.com";

    private int mCurrentTryStep;

    public boolean canRetry() {
        boolean canRetry = mCurrentTryStep < MAX_RETRY_COUNT;
        if (canRetry) {
            MyLogUtil.d("NetAccessHelper", "retry.");
            mCurrentTryStep++;
        }
        return canRetry;
    }

    public void resetRetry() {
        mCurrentTryStep = 0;
    }

    public void networkLookup(Context ctx, final NetWorkLookup lookup) {
        if (lookup == null) { return;}
        if (!MobileUtils.isNetworkConnected(ctx)) {
            lookup.lookup(false);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean reachable = false;
                try {
                    InetAddress address = InetAddress.getByName(PING_ADDR);
                    reachable = address.isReachable(PING_TIMEOUT);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                final boolean res = reachable;
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        lookup.lookup(res);
                    }
                });
            }
        }).start();
    }

    public interface NetWorkLookup {
        void lookup(boolean reachable);
    }
}
