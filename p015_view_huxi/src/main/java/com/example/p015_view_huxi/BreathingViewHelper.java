/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 drakeet
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.example.p015_view_huxi;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.view.View;

import java.util.Date;

/**
 * https://github.com/drakeet/BreathingViewHelper
 *
 * Created by drakeet on 6/12/15.
 */
public class BreathingViewHelper {

    private static AsyncTask<Void, Integer, Void> mAsyncTask;
    private static int mColor;
    private static boolean mCancelled;


    public static void setBreathingBackgroundColor(final int timer, final View view, final int color) {
        Date firstDate = new Date();
        final long firstTime = firstDate.getTime();
        mAsyncTask = new AsyncTask<Void, Integer, Void>() {
            int n = 1, t = timer;
            boolean increaseN;


            @Override protected Void doInBackground(Void... params) {
                while (!isCancelled() || !mCancelled) {
                    Date currentDate = new Date();
                    long diffTime = currentDate.getTime() - firstTime;
                    if (diffTime > n * t) {
                        increaseN = true;
                    }
                    if (increaseN) {
                        n++;
                        increaseN = false;
                    }
                    double y = getBreathingY(diffTime, n, t);
                    int alpha = (int) ((y * 0.618f + 0.382f) * 255);
                    int resultColor = setAlphaComponent(color, alpha);
                    mColor = resultColor;
                    publishProgress(resultColor);
                    try {
                        Thread.sleep(38);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }


            @Override protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                view.setBackgroundColor(values[0]);
            }
        };
        executeAsyncTask(mAsyncTask);
    }


    public static void stopBreathingBackgroundColor(View view) {
        if (mAsyncTask != null) { BreathingViewHelper.mAsyncTask.cancel(true); }
        else { mCancelled = true; }
        smoothToOrigin(view);
    }


    @SafeVarargs private static <Params, Progress, Result> void executeAsyncTask(
            AsyncTask<Params, Progress, Result> task, Params... params) {
        if (Build.VERSION.SDK_INT >= 11) {
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);
        }
        else {
            task.execute(params);
        }
    }


    private static double getBreathingY(long time, int n, int t) {
        float k = 1.0f / 3;
        float pi = 3.1415f;
        float x = time / 1000.0f;
        t = (int) (t / 1000.0f);
        if (x >= ((n - 1) * t) && x < ((n - (1 - k)) * t)) {
            double i = pi / (k * t) * ((x - (0.5f * k * t)) - (n - 1) * t);
            return 0.5f * Math.sin(i) + 0.5f;
        }
        else if (x >= ((n - (1 - k)) * t) && x < n * t) {
            double j = pi / ((1 - k) * t) * ((x - (0.5f * (3 - k) * t)) - (n - 1) * t);
            double one = 0.5f * Math.sin(j) + 0.5f;
            return one * one;
        }
        return 0;
    }


    private static double getSinY(long time) {
        return 0.5f * Math.sin(3 * time / 1000.0f) + 0.5;
    }


    private static void smoothToOrigin(final View view) {
        Date firstDate = new Date();
        final long firstTime = firstDate.getTime();
        executeAsyncTask(new AsyncTask<Void, Integer, Void>() {
                    int n = 1, t = 4000;
                    boolean increaseN;


                    @Override protected Void doInBackground(Void... params) {
                        while (!isCancelled()) {
                            Date currentDate = new Date();
                            long diffTime = currentDate.getTime() - firstTime;

                            double y = getCosY(diffTime);
                            int alpha = (int) (y * 255);
                            int resultColor = setAlphaComponent(mColor, alpha);
                            if (alpha < 0.038 * 255) {
                                publishProgress(0);
                                this.cancel(true);
                                return null;
                            }
                            publishProgress(resultColor, alpha);
                            try {
                                Thread.sleep(38);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        return null;
                    }


                    @Override protected void onProgressUpdate(Integer... values) {
                        super.onProgressUpdate(values);
                        view.setBackgroundColor(values[0]);
                    }
                });
    }


    public static void smoothToTransparentFromColor(final View view, final int color) {
        Date firstDate = new Date();
        final long firstTime = firstDate.getTime();
        executeAsyncTask(new AsyncTask<Void, Integer, Void>() {
                    int n = 1, t = 4000;
                    boolean increaseN;


                    @Override protected Void doInBackground(Void... params) {
                        while (!isCancelled()) {
                            Date currentDate = new Date();
                            long diffTime = currentDate.getTime() - firstTime;

                            double y = getCosY(diffTime);
                            int alpha = (int) (y * Color.alpha(color));
                            int resultColor = setAlphaComponent(color, alpha);
                            if (alpha < 0.038 * 255) {
                                publishProgress(0);
                                this.cancel(true);
                                return null;
                            }
                            publishProgress(resultColor, alpha);
                            try {
                                Thread.sleep(38);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        return null;
                    }


                    @Override protected void onProgressUpdate(Integer... values) {
                        super.onProgressUpdate(values);
                        view.setBackgroundColor(values[0]);
                    }
                });
    }


    private static double getCosY(long diffTime) {
        return 0.5f * Math.cos(3 * diffTime / 1000.0f) + 0.5;
    }


    public static int setAlphaComponent(int color, int alpha) {
        if (alpha < 0 || alpha > 255) {
            throw new IllegalArgumentException("alpha must be between 0 and 255.");
        }
        return (color & 0x00ffffff) | (alpha << 24);
    }
}