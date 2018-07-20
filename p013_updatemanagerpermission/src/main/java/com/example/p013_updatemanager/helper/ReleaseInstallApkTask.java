package com.example.p013_updatemanager.helper;

import com.blankj.utilcode.util.ResourceUtils;
import com.blankj.utilcode.util.ThreadUtils;

public class ReleaseInstallApkTask extends ThreadUtils.SimpleTask<Void> {

    private OnReleasedListener mListener;
    private String apk_temp_path;

    public ReleaseInstallApkTask(final OnReleasedListener listener,String apk_temp_path) {
        mListener = listener;
        this.apk_temp_path = apk_temp_path;
    }

    @Override
    public Void doInBackground() {
        ResourceUtils.copyFileFromAssets("test_install", apk_temp_path);
        return null;
    }

    @Override
    public void onSuccess(Void result) {
        if (mListener != null) {
            mListener.onReleased(apk_temp_path);
        }
    }

    public void execute() {
        ThreadUtils.executeByIo(this);
    }
}
