package com.example.p022_jincheng_baohuo.demo2.services;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.p022_jincheng_baohuo.R;


/**
 * 正常的系统前台进程，会在系统通知栏显示一个Notification通知图标
 * 白色保活
 * 白色保活手段非常简单，就是调用系统api启动一个前台的Service进程，
 * 这样会在系统的通知栏生成一个Notification，用来让用户知道有这样一个app在运行着，
 * 哪怕当前的app退到了后台。如下方的LBE和QQ音乐这样：
 *
 * @author clock
 * @since 2016-04-12
 */
public class WhiteService extends Service {

    private final static String TAG = WhiteService.class.getSimpleName();

    private final static int FOREGROUND_ID = 1000;

    public IBinder onBinder(Intent intent) {
        return new MsgBinder();
    }

    public class MsgBinder extends Binder {
        public WhiteService getService() {
            return WhiteService.this;
        }
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "WhiteService->onCreate");
        super.onCreate();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "WhiteService->onStartCommand");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "chat";
            String channelName = "聊天消息";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(channelId, channelName, importance);

            channelId = "subscribe";
            channelName = "订阅消息";
            importance = NotificationManager.IMPORTANCE_DEFAULT;
            createNotificationChannel(channelId, channelName, importance);
        }
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent activityIntent = new Intent("hs.act.MainActivity2");
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 10086, activityIntent, PendingIntent.FLAG_UPDATE_CURRENT);// FLAG_ONE_SHOT
        Notification notification = new NotificationCompat.Builder(this, "chat")
                .setContentTitle("进程保活")
                .setContentText("我是运行中的APP的通知栏~")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();
//        manager.notify(1, notification); // 点击删除
        startForeground(FOREGROUND_ID, notification);// 点击不可删除
        return super.onStartCommand(intent, flags, startId);
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void del_notification(String channelId) {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationChannel mChannel = manager.getNotificationChannel(channelId);
        manager.deleteNotificationChannel(String.valueOf(mChannel));
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "WhiteService->onDestroy");
        super.onDestroy();
    }
}