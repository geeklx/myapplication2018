package com.example.p002_screen_light.fragments;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.p002_screen_light.R;
import com.example.p002_screen_light.utils.SetUtil;
import com.example.shining.libutils.utilslib.base.BaseIndexNetFragment;
import com.example.shining.libutils.utilslib.etc.ToastUtil;
import com.h6ah4i.android.widget.verticalseekbar.VerticalSeekBar;


/**
 * Created by shining on 2017/8/14.
 * 亮度页面
 */

public class Demo1Fragment2 extends BaseIndexNetFragment implements View.OnClickListener {

    private static final int REQUEST_CODE_WRITE_SETTINGS = 0x110;
    private static final int REQ_PERMISSION_CODE_SDCARD = 0X111;
    private static final int REQ_PERMISSION_CODE_TAKE_PHOTO = 0X112;

    private VerticalSeekBar lightSeek;//亮度
    private ImageView lightAdd;//亮度增加
    private ImageView lightDecrease;//亮度减少
    private TextView lightNum;

    @Override
    public void call(Object value) {
        String ids = (String) value;
        ToastUtil.showToastShort(ids);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo1_fragment2;
    }

    @Override
    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {
        super.setup(rootView, savedInstanceState);
        findview(rootView);
        onclickListener();
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.WRITE_SETTINGS, Manifest.permission.WAKE_LOCK},
                REQUEST_CODE_WRITE_SETTINGS);
        doNetWork();
    }

    private void doNetWork() {
        lightSeek.setMax(100);
        //如果亮度为自动，则变为不自动
        if (SetUtil.isAutoBrightness(getActivity().getContentResolver())) {
            SetUtil.stopAutoBrightness(getActivity());
        }
        //获取当前亮度并设置
        int light = SetUtil.getScreenBrightness(getActivity());
        lightSeek.setProgress(light);
        lightNum.setText(lightSeek.getProgress() + "");
    }

    private void onclickListener() {
        lightAdd.setOnClickListener(this);
        lightDecrease.setOnClickListener(this);
        lightSeek.setOnSeekBarChangeListener(lightSeekListener);
    }

    /**
     * <p>Discription:[亮度滚动监听]</p>
     */
    VerticalSeekBar.OnSeekBarChangeListener lightSeekListener = new VerticalSeekBar.OnSeekBarChangeListener() {

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            lightNum.setText(progress + "");
            SetUtil.setScreenBrightness(getActivity(), progress);
            SetUtil.saveBrightness(getActivity().getContentResolver(), progress);
        }
    };

    private void findview(View rootView) {
        lightNum = (TextView) rootView.findViewById(R.id.light_num);
        lightSeek = (VerticalSeekBar) rootView.findViewById(R.id.light_seek);
        lightAdd = (ImageView) rootView.findViewById(R.id.light_add);
        lightDecrease = (ImageView) rootView.findViewById(R.id.light_derease);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.light_add://亮度增加
                int progress = lightSeek.getProgress();
                if (progress < 100) {
                    decOrAddLight(true);
                }
                break;
            case R.id.light_derease://亮度减少
                if (SetUtil.getScreenBrightness(getActivity()) > 0) {
                    decOrAddLight(false);
                }
                break;
            default:
                break;
        }
    }

    /**
     * <p>
     * 亮度减少或者增加
     * </p>
     *
     * @param isAdd true增加亮度
     */
    private void decOrAddLight(boolean isAdd) {
        int progress = lightSeek.getProgress();
        if (isAdd) {
            progress++;
        } else {
            progress--;
        }
        lightSeek.setProgress(progress);
        lightNum.setText(progress + "");
        SetUtil.saveBrightness(getActivity().getContentResolver(), progress);
    }
}
