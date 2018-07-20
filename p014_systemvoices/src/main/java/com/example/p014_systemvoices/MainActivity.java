package com.example.p014_systemvoices;

import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.example.p014_systemvoices.utils.RingControl;
import com.example.p014_systemvoices.utils.RingUtil;
import com.example.p014_systemvoices.utils.SwitchButton;

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
public class MainActivity extends AppCompatActivity {

    private FrameLayout mFlRingBg;
    private SeekBar mNoticeSeekBar;    //通知音效进度条
    private SeekBar mMediaSeekBar;     //多媒体音效进度条
    private SeekBar mAlarmSeekBar;     //闹铃音效进度条
    private SwitchButton toggle_quiet; //静音
    private RadioGroup mRgRingEffect;  //音效选择
    private ImageView mIvExit;         //退出图标

    private RingControl mRingActivityControl;
    private List<SeekBar> mSoundArray = new ArrayList<SeekBar>();// 公用函数集成 各音量调节seekbars 与 静音时switchbutton 的组合
    private int[] mIdRings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        findview();
        onclick();
        donetwork();

    }

    private void donetwork() {
//        mRingActivityControl.update_ui();
        mRingActivityControl.ringStartListenSystemVol();
    }

    private void onclick() {
        //设置背景按键事件监听
        mFlRingBg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        //设置退出按键事件监听
        mIvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //设置静音开关的监听
        toggle_quiet.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    //设置关闭静音
                    RingUtil.getInstance(MainActivity.this).ring_close_mute(mRingActivityControl.audioManager, mSoundArray);
                } else {
                    //设置打开静音
                    RingUtil.getInstance(MainActivity.this).ring_open_mute(mRingActivityControl.audioManager, mSoundArray);
                }
            }
        });

        //设置音效选择的监听
        mRgRingEffect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int idRadiobutton) {
                for (int i = 0; i < mIdRings.length; i++) {
                    if (mIdRings[i] == idRadiobutton) {
                        RingUtil.getInstance(MainActivity.this).ring_put_sp_sound_effect(i + 1);// 设置播放音效sp
                    }
                }
                RingUtil.getInstance(MainActivity.this).play_which_raw( AudioManager.STREAM_MUSIC);

            }
        });


    }


    private void findview() {
        mFlRingBg = findViewById(R.id.tv_ring_bg);
        mIvExit = findViewById(R.id.iv_ring_exit);
        toggle_quiet = findViewById(R.id.sb_mute_mode);
        mNoticeSeekBar = findViewById(R.id.sb_vol_notification);
        mMediaSeekBar = findViewById(R.id.sb_vol_media);
        mAlarmSeekBar = findViewById(R.id.sb_vol_alarm);
        mRgRingEffect = findViewById(R.id.rg_ring_effect);
        // 初始化控件的注册
        mRingActivityControl = new RingControl(this);
        get_seekbar();
        mRingActivityControl.ringRegisterVolSeekbar(AudioManager.STREAM_NOTIFICATION, mSoundArray.get(0));
        mRingActivityControl.ringRegisterVolSeekbar(AudioManager.STREAM_MUSIC, mSoundArray.get(1));
        mRingActivityControl.ringRegisterVolSeekbar(AudioManager.STREAM_ALARM, mSoundArray.get(2));
        mRingActivityControl.ringRegisterMuteSwitchbutton(toggle_quiet);
        mRingActivityControl.ringRegisterRingEffect(mRgRingEffect, mIdRings);

        mRingActivityControl.ring_set_ui_init_state();
        mRingActivityControl.ring_set_seekbar_listener(mSoundArray);
    }

    private void get_seekbar() {
        mSoundArray.add(mNoticeSeekBar);
        mSoundArray.add(mMediaSeekBar);
        mSoundArray.add(mAlarmSeekBar);
        mIdRings = new int[]{R.id.rb_ring1, R.id.rb_ring2, R.id.rb_ring3};
    }

    @Override
    protected void onResume() {
        super.onResume();
        donetwork();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //公共控制类的使用
        mRingActivityControl.ringFinishListenSystemVol();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //公共控制类的使用
        mRingActivityControl.ringFinishListenSystemVol();
    }
}
