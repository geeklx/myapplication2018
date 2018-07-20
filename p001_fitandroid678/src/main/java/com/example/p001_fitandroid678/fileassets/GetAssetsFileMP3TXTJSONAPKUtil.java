package com.example.p001_fitandroid678.fileassets;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import com.blankj.utilcode.util.Utils;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GetAssetsFileMP3TXTJSONAPKUtil implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    private static volatile GetAssetsFileMP3TXTJSONAPKUtil instance;
    private static Context mContext;

    private GetAssetsFileMP3TXTJSONAPKUtil(Context context) {
        this.mContext = context;
    }

    public static GetAssetsFileMP3TXTJSONAPKUtil getInstance(Context context) {
        if (instance == null) {
            synchronized (GetAssetsFileMP3TXTJSONAPKUtil.class) {
                instance = new GetAssetsFileMP3TXTJSONAPKUtil(context);
            }
        }
        return instance;
    }

    public MediaPlayer mediaPlayer;

    /**
     * 获取assets/xx目录下的json文件
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = context.getAssets();
        //使用IO流读取json文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }


    /**
     * 将Json字符串转换为对象
     *
     * @param json
     * @param type
     * @return
     */
    public static <T> T JsonToObject(String json, Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }

    /**
     * 将对象转换为字符串
     *
     * @param type
     * @return
     */
    public static String ObjectToJson(Object type) {
        Gson gson = new Gson();
        return gson.toJson(type);
    }

    /**
     * 获取assets下json文件的路径
     *
     * @param assets_file_name
     * @return
     */
    public static String get_assets_content(String assets_file_name) throws IOException {
        return new String(InputStreamToByte(getAssetsInput(assets_file_name)));
    }

    /**
     * 传入assets下文件名字获取InputStream
     *
     * @param assets_file_name
     * @return
     */
    public static InputStream getAssetsInput(String assets_file_name) throws IOException {
        AssetManager assset = Utils.getApp().getAssets();
        InputStream inputStream = null;
        inputStream = assset.open(assets_file_name);
        return inputStream;
    }

    public static byte[] InputStreamToByte(InputStream is) throws IOException {
        ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
        int ch;
        while ((ch = is.read()) != -1) {
            bytestream.write(ch);
        }
        byte imgdata[] = bytestream.toByteArray();
        bytestream.close();
        return imgdata;

    }

    /**
     * 获取assets/xx路径下mp3 获取raw路径下MP3
     * assets:   "mp3/demo2.mp3"
     * raw:      Uri.parse("android.resource://" + mContext.getPackageName() + "/" +uri)
     *
     * @param context
     * @param assets_or_raw
     * @param uri_or_assetsurl
     */
    public void playMusic(Context context, boolean assets_or_raw, String uri_or_assetsurl) {
        Log.e("playMusic:", "playMusic");
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }
        try {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.reset();
//                initMediaPlayer();
            }
            if (assets_or_raw) {
                mediaPlayer.setDataSource(context, Uri.parse(uri_or_assetsurl));
            } else {
                AssetManager assetManager = context.getAssets();
                AssetFileDescriptor fileDescriptor = assetManager.openFd(uri_or_assetsurl);
                mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());// 设置数据源
            }
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            Log.e("playMusic:error:", e.toString());
            e.printStackTrace();
        }

//        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnCompletionListener(this);

    }

    public void MusicDestory() {
        if (null != mediaPlayer) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        Log.e("playMusic:", "start");
//        mediaPlayer.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.e("playMusic:", "onCompletion");
        MusicDestory();
    }

}
