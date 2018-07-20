package com.example.p008_timertask;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.shining.libutils.utilslib.app.MyLogUtil;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


/**
 * //time为Date类型：在指定时间执行一次。
 * timer.schedule(task, time);
 * //firstTime为Date类型,period为long，表示从firstTime时刻开始，每隔period毫秒执行一次。
 * timer.schedule(task, firstTime,period);
 * //delay 为long类型：从现在起过delay毫秒执行一次。
 * timer.schedule(task, delay);
 * //delay为long,period为long：从现在起过delay毫秒以后，每隔period毫秒执行一次。
 * timer.schedule(task, delay,period);
 *
 */
public class TimerServices extends Service {
    private static final String TAG = TimerServices.class.getSimpleName();
    private MyObservable myObservable;
    private Timer mTimer;
    private TimerTask mTimerTask;
    private long timer_daojishi;
    private int hour=0;
    private int minute=1;
    private int seconds=50;
    private long count = hour * 60 * 60 + minute * 60 + seconds;
    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MsgBinder();
    }

    public class MsgBinder extends Binder{
        public TimerServices getService(){
            return TimerServices.this;
        }
        public MyObservable getMyObservable(){
            return myObservable;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent!=null&& TextUtils.isEmpty(intent.getAction())){
            String action = intent.getAction();
            MyLogUtil.d(TAG, "alarm set repeat " + Calendar.getInstance().getTime().toString() + "     " + action);
            if ("com.action".equals(action)){
                //业务逻辑bufen

            }
        }
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myObservable = new MyObservable();
        startimer();
    }

    private void startimer() {
        timer_daojishi = count;
        if (mTimer==null){
            mTimer = new Timer();
        }
        if (mTimerTask==null){
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    if (timer_daojishi>0){
                        setCount(timer_daojishi);
                        myObservable.setData(timer_daojishi+"");
                        timer_daojishi--;
                    }else {
                        timerfinish();
                        closetimer();
                    }
                }
            };
            if (mTimer!=null&&mTimerTask!=null){
                mTimer.schedule(mTimerTask,0,1000);
            }
        }

    }

    private void closetimer() {
        if (mTimer!=null){
            mTimer.cancel();
            mTimer =null;
        }
        if (mTimerTask!=null){
            mTimerTask.cancel();
            mTimerTask=null;
        }
    }

    private void timerfinish() {
        setCount(0);
        //业务逻辑 弹窗跳转bufen

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        closetimer();

    }
}
