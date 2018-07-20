package com.example.p008_timertask;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements Observer {

    private boolean isBind =false;
    private TimerServices mTimerServices;
    private long content_num;
    private MyObservable myObservable;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = findViewById(R.id.tv1);
//        myObservable = new MyObservable();
//        myObservable.addObserver(this);
        startService(new Intent(this, TimerServices.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isBind){
             bindService(new Intent(this,TimerServices.class),conn, Context.BIND_ABOVE_CLIENT);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isBind){
            mTimerServices.setCount(0);
        }
        unbindService(conn);
        isBind = false;
    }

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mTimerServices = ((TimerServices.MsgBinder)service).getService();
            myObservable = ((TimerServices.MsgBinder)service).getMyObservable();
            myObservable.addObserver(MainActivity.this);
            isBind =true;
            //业务逻辑bufen
            content_num = mTimerServices.getCount();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBind = false;
        }
    };


    @Override
    public void update(Observable o, Object arg) {
        //更新页面bufen
        String timer = (String) arg;
        if (!TextUtils.isEmpty(timer)) {
            final long[] times = TimeUtil.compute(Long.valueOf(timer) * 1000);
            tv1.post(new Runnable() {
                @Override
                public void run() {
                    tv1.setText("时："+String.valueOf(times[1])+"分："+String.valueOf(times[2])+"秒："+String.valueOf(times[3]));
                }
            });
        }

    }
}
