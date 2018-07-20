package com.example.p013_updatemanager.download.intenservice;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;

import com.example.p013_updatemanager.helper.Config;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Description:
 * <br> 使用 IntentService 实现下载
 */

public class DownloadServiceApk extends IntentService {
    private static final String TAG = "DownloadServiceApk";
    public static final String DOWNLOAD_URL = "down_load_url";
    public static final int WHAT_DOWNLOAD_FINISHED = 1;
    public static final int WHAT_DOWNLOAD_STARTED = 2;
    private boolean iscancel = false;
    private boolean isfinish = false;

    public DownloadServiceApk() {
        super(TAG);
    }

    private static Handler mUIHandler;

    public static void setUIHandler(final Handler UIHandler) {
        mUIHandler = UIHandler;
    }

    /**
     * 这个方法运行在子线程
     *
     * @param intent
     */
    @Override
    protected void onHandleIntent(final Intent intent) {
        String url = intent.getStringExtra(DOWNLOAD_URL);
        if (!TextUtils.isEmpty(url)) {
            sendMessageToMainThread(WHAT_DOWNLOAD_STARTED, "\n " + SystemClock.currentThreadTimeMillis() + " 开始下载任务：\n" + url);
            try {
                boolean isloadingend = downloadUrlToApk(url);
                SystemClock.sleep(1000);    //延迟一秒发送消息
                if (isloadingend){
                    sendMessageToMainThread(WHAT_DOWNLOAD_FINISHED, isloadingend);
                    isfinish = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发送消息到主线程
     *
     * @param id
     * @param o
     */
    private void sendMessageToMainThread(final int id, final Object o) {
        if (mUIHandler != null) {
            mUIHandler.sendMessage(mUIHandler.obtainMessage(id, o));
        }
    }

    /**
     * 下载图片
     *
     * @param url
     * @return
     * @throws Exception
     */
    private Bitmap downloadUrlToBitmap(String url) throws Exception {
        HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
        BufferedInputStream in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 1024);
        Bitmap bitmap = BitmapFactory.decodeStream(in);
        urlConnection.disconnect();
        in.close();
        return bitmap;
    }

    /**
     * 下载文件
     *
     * @param url
     * @return
     * @throws Exception
     */
    private boolean downloadUrlToApk(String url) throws Exception {
        HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
        urlConnection.connect();
        int length = urlConnection.getContentLength(); // 获取文件大小
        InputStream is = urlConnection.getInputStream();// 创建输入流
        File file = new File(Config.CACHE_PATH + Config.FILE_SEP);
        if (!file.exists()) {
            file.mkdir();
        }
        File apkFile = new File(Config.TEMP_APK_PATH);
        FileOutputStream fos = new FileOutputStream(apkFile);
        int count = 0;
        byte buf[] = new byte[1024];// 缓存
        do {
            int numread = is.read(buf);
            count += numread;
            Log.e("--geek进度--", (int) (((float) count / length) * 100) + "");
            if (numread <= 0) {
                // 下载完成
                isfinish = true;
                break;
            }
            // 写入文件
            fos.write(buf, 0, numread);
        } while (!iscancel);
        urlConnection.disconnect();
        fos.close();
        is.close();
        return isfinish;
    }


}