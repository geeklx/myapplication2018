package com.example.p014_systemvoices.utils;

import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;
import android.provider.Settings;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统声音stream_type：
 * 1.设置闹钟音量                  AudioManager.STREAM_ALARM
 * 2.设置媒体（音乐）音量          AudioManager.STREAM_MUSIC
 * 3.设置通知音量                  AudioManager.STREAM_NOTIFICATION
 * 4.设置系统音量                  AudioManager.STREAM_SYSTEM
 * 5.设置手机铃声音量              AudioManager.STREAM_RING
 * 6.设置通话音量                  AudioManager.STREAM_VOICE_CALL
 * 7.设置拨号键音量                AudioManager.STREAM_DTMF
 */
public class RingControl {
    private static final String TAG = RingControl.class.getSimpleName();


    private Context context;
    public AudioManager audioManager;
    private SettingsContentObserver mObserver;//监听设置变化

    private List<SeekBar> mSoundArray = new ArrayList<>();// 公用函数集成 各音量调节seekbars 与 静音时switchbutton 的组合
    private SwitchButton mMute;
    private RadioGroup mRgRingEffect;
    private int mIdRings[];

    public RingControl(Context context) {
        this.context = context;
        mObserver = new SettingsContentObserver(new Handler());
    }

    class SettingsContentObserver extends ContentObserver {

        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public SettingsContentObserver(Handler handler) {
            super(handler);
            audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        }

        @Override
        public boolean deliverSelfNotifications() {
            return false;
        }

        @Override
        public void onChange(boolean selfChange) {
            update_ui();
        }
    }

    /**
     * 静音与音量调节状态的更新
     * 基于系统音量变化，实时更新音量界面
     */
    public void update_ui() {
        RingUtil.getInstance(context).ring_put_sp_current_vol(audioManager, -1);// 记录指定类型当前音量到当前声音SP  传-1 记录所有类型当前音量到当前声音SP
        int tag = RingUtil.getInstance(context).ring_get_sp_mute_state();

        if (tag == 0) { //设置为不静音
            set_mute_open_jingyin(false, false);
        } else if (tag == 1) {  //设置为静音
            if (RingUtil.getInstance(context).ring_has_sound(mSoundArray)) {
                mMute.setChecked(false);
            }
        }
        RingUtil.getInstance(context).getSoundVolume(audioManager);
    }

    /**
     * 静音与音量调节状态的初始化
     * 在设置静音与音量的监听之前，基于当前系统音量状态，设置音量界面和系统保持一致
     */
    public void ring_set_ui_init_state() {
        //初始化音效
        int ringN = RingUtil.getInstance(context).ring_get_sp_sound_effect();
        mRgRingEffect.check(mIdRings[ringN - 1]);
        //初始化静音和音量调节
        RingUtil.getInstance(context).ring_put_sp_current_vol(audioManager, -1);
        int tag = RingUtil.getInstance(context).ring_get_sp_mute_state();

        if (tag == 0) { //不静音
            mMute.setChecked(false);
            set_mute_open_jingyin(true, false);
        } else if (tag == 1) {  //静音
            if (RingUtil.getInstance(context).ring_has_sound(mSoundArray)) {
                RingUtil.getInstance(context).ring_put_sp_mute_state(0);
                mMute.setChecked(false);
                set_mute_open_jingyin(true, false);
            } else {
                mMute.setChecked(true);
                for (SeekBar seekbar : mSoundArray) {
                    int type = (int) seekbar.getTag();
                    seekbar.setMax(RingUtil.getInstance(context).get_zhiding_voices_MaxVolume(audioManager, type));
                    seekbar.setProgress(RingUtil.getInstance(context).ring_get_sp_mute_vol(type));
                    RingUtil.getInstance(context).ring_set_ui_seekbar(seekbar, true);
                }
            }
        } else if (tag == -1) {  //之前没有设置过静音标志位
            if (RingUtil.getInstance(context).ring_has_sound(mSoundArray)) {
                RingUtil.getInstance(context).ring_put_sp_mute_state(0);
                mMute.setChecked(false);
                set_mute_open_jingyin(true, false);
            } else {
                RingUtil.getInstance(context).ring_put_sp_mute_state(1);
                mMute.setChecked(true);
                for (SeekBar seekbar : mSoundArray) {
                    int type = (int) seekbar.getTag();
                    RingUtil.getInstance(context).ring_put_sp_mute_vol(type, 0);
                    seekbar.setMax(RingUtil.getInstance(context).get_zhiding_voices_MaxVolume(audioManager, type));
                    seekbar.setProgress(RingUtil.getInstance(context).ring_get_sp_mute_vol(type));
                    RingUtil.getInstance(context).ring_set_ui_seekbar(seekbar, true);
                }
            }
        }
        RingUtil.getInstance(context).getSoundVolume(audioManager);
    }

    /**
     * 开始监听系统音量，进行相应的变化
     */
    public void ringStartListenSystemVol() {
        context.getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, mObserver);
    }

    /**
     * 结束对系统音量的监听
     */
    public void ringFinishListenSystemVol() {
        context.getContentResolver().unregisterContentObserver(mObserver);
    }


    /**
     * 注册声音调节的Seekbar（声音类型记录到Seekbar的Tag）
     *
     * @param type
     * @param seekbar
     */
    public void ringRegisterVolSeekbar(int type, SeekBar seekbar) {
        seekbar.setTag(type);
        mSoundArray.add(seekbar);
    }

    /**
     * 注册静音的SwitchButton
     *
     * @param switchbutton
     */
    public void ringRegisterMuteSwitchbutton(SwitchButton switchbutton) {
        mMute = switchbutton;
    }

    /**
     * 注册音效选择的RadioGroup（声音类型记录到Seekbar的Tag）
     *
     * @param radiogroup
     */
    public void ringRegisterRingEffect(RadioGroup radiogroup, int... idRings) {
        mRgRingEffect = radiogroup;
        mIdRings = idRings;
    }



    /**
     * 设置不静音状态seekbar跟随系统大小变化而变化 1.false false/true  2.true false/true
     *
     * @param is_change_ui
     * @param is_open
     */
    public void set_mute_open_jingyin(boolean is_change_ui, boolean is_open) {
        for (SeekBar seekbar : mSoundArray) {
            int type = (int) seekbar.getTag();
            seekbar.setMax(RingUtil.getInstance(context).get_zhiding_voices_MaxVolume(audioManager, type));// 获取当前指定声音的最大声音
            seekbar.setProgress(RingUtil.getInstance(context).ring_get_sp_current_vol(type));// 获取指定类型音量从当前声音SP
            if (is_change_ui) {
                RingUtil.getInstance(context).ring_set_ui_seekbar(seekbar, is_open);// 设置声音调节Seekbar的UI样式为可用 或不可用
            }
        }
    }

    /**
     * 设置音量调节Seekbar的监听
     *
     * @param seekbars
     */
    public void ring_set_seekbar_listener(List<SeekBar> seekbars) {
        for (SeekBar seekbar : seekbars) {
            //设置媒体seekbar监听
            seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean isUser) {
                    int type = (int) seekBar.getTag();  //获取当前Seekbar的类型
                    int tag = RingUtil.getInstance(context).ring_get_sp_mute_state();       //1：静音  0：非静音
                    if (tag == 0 && isUser) {                 //如果是用户操作，设置音量并更新当前音量的SP值
                        RingUtil.getInstance(context).set_zhiding_voices_volume(audioManager, type, i);
                        RingUtil.getInstance(context).ring_put_sp_current_vol(audioManager, type);
                    } else if (tag == 0 && !isUser) {         //如果不是用户操作，不做任何操作（仅更新UI）
                    } else if (tag == 1 && isUser)            //如果是用户操作，设置静音时的SP值
                        RingUtil.getInstance(context).ring_put_sp_mute_vol(type, i);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(final SeekBar seekBar) {
                    int type = (int) seekBar.getTag();
                    RingUtil.getInstance(context).play_which_raw(type);
                }
            });
        }
    }


}
