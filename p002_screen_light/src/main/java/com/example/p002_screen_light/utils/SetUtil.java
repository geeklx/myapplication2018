package com.example.p002_screen_light.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.media.AudioManager;
import android.net.Uri;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.view.WindowManager;

import com.example.shining.libutils.utilslib.app.MyLogUtil;

import static android.media.AudioManager.STREAM_MUSIC;
import static android.media.AudioManager.STREAM_SYSTEM;

public class SetUtil {

    /**
     * <p>Discription:[亮度是否为自动设置]</p>
     *
     * @param aContentResolver
     * @return
     * @author:[刘新华]
     * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
     */
    public static boolean isAutoBrightness(ContentResolver aContentResolver) {
        boolean automicBrightness = false;
        try {
            automicBrightness = Settings.System.getInt(aContentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE) == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
        } catch (SettingNotFoundException e) {
            e.printStackTrace();
        }
        return automicBrightness;
    }

    /**
     * <p>Discription:[停止自动亮度]</p>
     *
     * @param activity
     * @author:[刘新华]
     * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
     */
    public static void stopAutoBrightness(Activity activity) {
        Settings.System.putInt(activity.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
    }

    /**
     * <p>Discription:[获取屏幕亮度]</p>
     *
     * @param activity
     * @return
     * @author:[刘新华]
     * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
     */
    public static int getScreenBrightness(Activity activity) {
        int value = 0;
        ContentResolver cr = activity.getContentResolver();
        try {
            value = Settings.System.getInt(cr, Settings.System.SCREEN_BRIGHTNESS);
        } catch (SettingNotFoundException e) {

        }
        return value;
    }

    /**
     * <p>Discription:[设置屏幕亮度]</p>
     *
     * @param activity
     * @param value
     * @author:[刘新华]
     * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
     */
    public static void setScreenBrightness(Activity activity, int value) {
        WindowManager.LayoutParams params = activity.getWindow().getAttributes();
        params.screenBrightness = value / 100f;
        activity.getWindow().setAttributes(params);
    }

    /**
     * <p>Discription:[保存调节亮度]</p>
     *
     * @param resolver
     * @param brightness
     * @author:[刘新华]
     * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
     */
    public static void saveBrightness(ContentResolver resolver, int brightness) {
        Uri uri = Settings.System.getUriFor("screen_brightness");
        Settings.System.putInt(resolver, "screen_brightness", (brightness));
        // resolver.registerContentObserver(uri, true, myContentObserver);
        resolver.notifyChange(uri, null);
    }

    /**
     * 设置系统休眠时间
     */
    public static void setTimeOfSetting(Context context, int value) {
        Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, value);
    }

    /**
     * 获取系统休眠时间
     */
    public static int getTimeOfSetting(Context context) {
        int defTimeOut = Settings.System
                .getInt(context.getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, 120000);
        return defTimeOut;
    }

    /**
     * 设置系统不休眠
     */
    public static void setTimeNeverOff(Context context) {
        //        Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, -1);
        Settings.System.putInt(context.getContentResolver(),android.provider.Settings.System.SCREEN_OFF_TIMEOUT, -1);
//        Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, -1);
    }

    /**
     * 获取当前系统音量
     */
    public static int getVolume(Context context) {
        //音量控制,初始化定义
        AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        //当前音量
        int currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);//old STREAM_SYSTEM ,now STREAM_MUSIC
        MyLogUtil.e("ziuo"+"---当前音量---->" + currentVolume);

        return currentVolume;
    }

    /**
     * 获取系统最大音量
     */
    public static int getMaxVolume(Context context) {
        //音量控制,初始化定义
        AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        //最大音量
        int maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);//old STREAM_SYSTEM ,now STREAM_MUSIC
        MyLogUtil.e("ziuo"+"---最大音量---->" + maxVolume);
        return maxVolume;
    }

    /**
     * 增加系统音量
     */
    public static void upVolume(Context context) {
        AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mAudioManager.adjustStreamVolume(STREAM_MUSIC, AudioManager.ADJUST_RAISE,
                AudioManager.FLAG_PLAY_SOUND);
        // 通知音量最大为 8 ==> 系统音量 16

        mAudioManager.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_RAISE,
                AudioManager.FLAG_PLAY_SOUND);
/*        mAudioManager.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_RAISE,
                AudioManager.FLAG_PLAY_SOUND);*/

    }

    /**
     * 降低系统音量
     */
    public static void downVolume(Context context) {
        AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mAudioManager.adjustStreamVolume(STREAM_MUSIC, AudioManager.ADJUST_LOWER,
                AudioManager.FLAG_PLAY_SOUND);

        // 通知音量最大为 8 ==> 通知音量 16
        mAudioManager.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_LOWER,
                AudioManager.FLAG_PLAY_SOUND);
/*        mAudioManager.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_LOWER,
                AudioManager.FLAG_PLAY_SOUND);*/
    }

    /**
     * 静音
     *
     * @param context
     */
    public static void slienceVolume(Context context) {
        AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mAudioManager.getStreamVolume(STREAM_MUSIC);
        mAudioManager.setStreamVolume(STREAM_MUSIC, 0, 0);
        mAudioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, 0, 0);
        mAudioManager.setStreamVolume(AudioManager.STREAM_RING, 0, 0);
//        mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
    }

    /**
     * 恢复正常
     *
     * @param context
     * @param progress
     */
    public static void restoreVolume(Context context, int progress) {
        AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mAudioManager.getStreamVolume(STREAM_MUSIC);
        mAudioManager.setStreamVolume(STREAM_MUSIC, progress, 0);
        mAudioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, progress, 0);
        mAudioManager.setStreamVolume(AudioManager.STREAM_RING, progress, 0);
//        mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
    }

    public static void setVolume(Context context, int valume) {
        AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
/*        mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE,
                AudioManager.FLAG_PLAY_SOUND);
        mAudioManager.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_RAISE,
                AudioManager.FLAG_PLAY_SOUND);*/

        mAudioManager.setStreamVolume(STREAM_MUSIC,valume, 0);
        mAudioManager.setStreamVolume(STREAM_SYSTEM,valume/2, 0);

        MyLogUtil.e("ziuo"+"---设置音量---->" + valume+"  通知音量"+valume/2);

    }

}
