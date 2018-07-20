package com.example.p002_screen_light.fragments;

import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.p002_screen_light.Demo1Activity;
import com.example.p002_screen_light.R;
import com.example.p002_screen_light.utils.LiangduUtil;
import com.example.p002_screen_light.utils.SetUtil;
import com.example.shining.libutils.utilslib.app.MyLogUtil;
import com.example.shining.libutils.utilslib.base.BaseIndexNetFragment;
import com.example.shining.libutils.utilslib.data.SpUtils;
import com.h6ah4i.android.widget.verticalseekbar.VerticalSeekBar;


/**
 * Created by shining on 2017/8/14.
 * 休眠页面
 */

public class Demo1Fragment1 extends BaseIndexNetFragment implements View.OnClickListener {

    public PowerManager pm;// 屏幕开关
    public PowerManager.WakeLock mWakeLock;

    //屏幕关闭时间
    public static final String SP_SCREEN_TIME = "screenTime";
    //屏幕常亮开关标识
    public static final String SP_ALWAYS_LIGHT = "alwaysLight";

    private VerticalSeekBar timeSeek;//屏幕延时
    private TextView volum_num_min;
    private ImageView lightDelayAdd;//亮度延时增加
    private ImageView lightDelayDecrease;//亮度延时减少
    private CheckBox lightCheck;//屏幕亮度
    private TextView lightCheckDescription;  //lightCheck的描述

    @Override
    public void call(Object value) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo1_fragment1;
    }

    @Override
    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {
        super.setup(rootView, savedInstanceState);
        rootView.findViewById(R.id.tv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendToFragment("demo1的fragment1页面");
            }
        });
        findview(rootView);
        onclickListener();
//        pm = (PowerManager) getActivity().getSystemService(POWER_SERVICE);
//        mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "geek");
//        mWakeLock.setReferenceCounted(false);
        doNetWork();
    }

    private void doNetWork() {
        //初始化休眠时间
        int time = (int) SpUtils.getInstance(getActivity()).get(SP_SCREEN_TIME, 25);
//        MyLogUtil.d(TAG, "initTimeSeek start,time:" + time);
        //根据刻度设置屏幕休眠时间
//        int tempMills = LiangduUtil.getMillsSecond(time);
//        if (lightCheck.isChecked()){
//            SetUtil.setTimeOfSetting(getActivity(), -1);
//        }else{
//            SetUtil.setTimeOfSetting(getActivity(), tempMills);
//        }

        String minStr = LiangduUtil.getMinuteStr(time);
        volum_num_min.setText(minStr);
        timeSeek.setProgress(time);
        MyLogUtil.d(TAG, "initTimeSeek end");

        //设置屏幕常亮的checkbox状态
        int value = (int) SpUtils.getInstance(getActivity()).get(SP_ALWAYS_LIGHT, 0);
        boolean isTrue = (value == 1);
        lightCheck.setChecked(isTrue);
        lightCheckDescription.setText(isTrue ? R.string.open : R.string.close);
    }

    private void onclickListener() {
        lightDelayAdd.setOnClickListener(this);
        lightDelayDecrease.setOnClickListener(this);
        /* 背光控制 */
        lightCheck.setOnCheckedChangeListener(lightCheckListener);
         /* 灭屏控制*/
        timeSeek.setOnSeekBarChangeListener(timeSeekListener);
    }

    /**
     * <p>Discription:[屏幕休眠时间滚动监听]</p>
     */
    VerticalSeekBar.OnSeekBarChangeListener timeSeekListener = new VerticalSeekBar.OnSeekBarChangeListener() {

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            //根据刻度设置屏幕休眠时间
            if (!lightCheck.isChecked()) {
                //根据滚动的位置判断停在那个刻度
                int progress = seekBar.getProgress();
                int time = LiangduUtil.getTime(progress);
                seekBar.setProgress(time);
                lightChange(time);
                MyLogUtil.d(TAG, "SP put end,time:" + time);
            } else {
                timeSeek.setEnabled(!lightCheck.isChecked());
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            String a = "";
            if (lightCheck.isChecked()) {
                timeSeek.setEnabled(!lightCheck.isChecked());
            }
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (!lightCheck.isChecked()) {
                int time = LiangduUtil.getTime(progress);
                String minuStr = LiangduUtil.getMinuteStr(time);
                seekBar.setProgress(time);
                volum_num_min.setText(minuStr);
                MyLogUtil.d(TAG, "onProgressChanged    time=" + time);
            } else {
                timeSeek.setEnabled(!lightCheck.isChecked());
            }
        }
    };

    /**
     * 屏幕常亮check
     */
    CompoundButton.OnCheckedChangeListener lightCheckListener = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            closeOrOpenAlwaysLight(isChecked);
            lightCheckDescription.setText(isChecked ? R.string.open : R.string.close);
            if (!isChecked) {
                int time = timeSeek.getProgress();
                lightChange(time);
            }
        }
    };

    /**
     * <p>
     * 监听器改变
     * </p>
     *
     * @param time
     */
    private void lightChange(int time) {
        boolean isElse = (time != 0) && (time != 25) && (time != 50) && (time != 75) && (time != 100);
        if (isElse) {
            SetUtil.setTimeOfSetting(getActivity(), 300000);
        } else {
            int mills = LiangduUtil.getMillsSecond(time);
            SetUtil.setTimeOfSetting(getActivity(), mills);
            String minuStr = LiangduUtil.getMinuteStr(time);
            volum_num_min.setText(minuStr);
            SpUtils.getInstance(getActivity()).put(SP_SCREEN_TIME, time);
        }
    }

    /**
     * 是否将屏幕置为常亮状态
     *
     * @param isOpen
     */
    public void closeOrOpenAlwaysLight(boolean isOpen) {
        if (isOpen) {
//            mWakeLock.acquire();
            SetUtil.setTimeOfSetting(getActivity(), Integer.MAX_VALUE);
//            SetUtil.setTimeNeverOff(getActivity());
        } else {
//            mWakeLock.release();
            int time = (int) SpUtils.getInstance(getActivity()).get(SP_SCREEN_TIME, 25);
            int mills = LiangduUtil.getMillsSecond(time);
            SetUtil.setTimeOfSetting(getActivity(), mills);
        }
        int flag = isOpen ? 1 : 0;
        SpUtils.getInstance(getActivity()).put(SP_ALWAYS_LIGHT, flag);
        //将人感打开 休眠时间可以修改
//        Sensor.setSensorState(!isOpen);
//        feelCheck.setChecked(!isOpen);
//        feelCheck.setEnabled(!isOpen);
//        feelCheckDescription.setText(isOpen ? R.string.close : R.string.open);
        timeSeek.setEnabled(!isOpen);
        if (!isOpen) {
            int time = (int) SpUtils.getInstance(getActivity()).get(SP_SCREEN_TIME, 25);
            timeSeek.setProgress(time);
        }
    }

    private void findview(View rootView) {
        timeSeek = (VerticalSeekBar) rootView.findViewById(R.id.time_seek);
        volum_num_min = (TextView) rootView.findViewById(R.id.volum_num_min);
        lightDelayAdd = (ImageView) rootView.findViewById(R.id.light_delay_add);
        lightDelayDecrease = (ImageView) rootView.findViewById(R.id.light_delay_decrease);
        lightCheck = (CheckBox) rootView.findViewById(R.id.light_check);
        lightCheckDescription = (TextView) rootView.findViewById(R.id.light_check_description);
    }

    /**
     * 页面传值操作部分
     *
     * @param id1
     */
    private void SendToFragment(String id1) {
        //举例
//        IndexFoodFragmentUpdateIds iff = new IndexFoodFragmentUpdateIds();
//        iff.setFood_definition_id(id1);
//        iff.setFood_name(id2);
        if (getActivity() != null && getActivity() instanceof Demo1Activity) {
            ((Demo1Activity) getActivity()).callFragment(id1, Demo1Fragment2.class.getName());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.light_delay_add://亮度延时增加
                decOrAddLightDelay(true);
                break;
            case R.id.light_delay_decrease://休眠延时减少
                decOrAddLightDelay(false);
                break;
            default:
                break;
        }
    }

    /**
     * <p>
     * 亮度延时减少或者增加
     * </p>
     *
     * @param isAdd
     */
    private void decOrAddLightDelay(boolean isAdd) {
        if (!lightCheck.isChecked()) {
            int time = 0;
            int progress = timeSeek.getProgress();
            if (isAdd) {
                time = LiangduUtil.getScrollTime(progress);
            } else {
                time = LiangduUtil.getDecTime(progress);
            }
            timeSeek.setProgress(time);
            boolean isElse = (time != 0) && (time != 25) && (time != 50) && (time != 75) && (time != 100);
            if (isElse) {
                SetUtil.setTimeOfSetting(getActivity(), 300000);
            } else {
                int mills = LiangduUtil.getMillsSecond(time);
                SetUtil.setTimeOfSetting(getActivity(), mills);
                String minuStr = LiangduUtil.getMinuteStr(time);
                volum_num_min.setText(minuStr);
            }
            SpUtils.getInstance(getActivity()).put(SP_SCREEN_TIME, time);
        } else {
            timeSeek.setEnabled(false);
        }
    }
}
