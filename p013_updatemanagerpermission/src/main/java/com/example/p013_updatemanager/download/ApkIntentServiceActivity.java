/*
 * Copyright (c) 2017. shixinzhang (shixinzhang2016@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.p013_updatemanager.download;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.p013_updatemanager.R;
import com.example.p013_updatemanager.download.intenservice.DownloadServiceApk;
import com.example.p013_updatemanager.helper.Config;
import com.example.p013_updatemanager.helper.OnReleasedListener;
import com.example.p013_updatemanager.helper.PermissionHelper;
import com.example.p013_updatemanager.helper.ReleaseInstallApkTask;

import java.util.Arrays;
import java.util.List;


/**
 * Description:
 * <br> IntentService 实例
 * <p>
 * <br> Created by shixinzhang on 17/6/9.
 * <p>
 * <br> Email: shixinzhang2016@gmail.com
 * <p>
 * <a  href="https://about.me/shixinzhang">About me</a>
 */

public class ApkIntentServiceActivity extends AppCompatActivity implements Handler.Callback {

    private Button mBtnDownload;
    private TextView tv1;

    private String[] strings = new String[]{"http://p.gdown.baidu.com/e1ab5af129ff62cba3836250ea3447eb08fd6c60f1fd9c963ae7847c8102c98c10a29889b721f49dcb3c945064dd2e9483aed1b7852354134e43779405e8baf93307c409488d014f8b565660f1d0606392ea7dc77c31ff80e8cc78971579434d78d61eac9e1379c3296efa6fe61f1077558a3e7e8db84983959d6ba6167d0b7a10037f1e31675a27c3379d13c6611b348739684a047bea914fb5d8fa68981f747f2c8c76783bdb7ed91752dd5a730c41409e6e530a24872e926a7e6678711e4c50fc22bc8741fc33c1250aa4f4faf971fecb50b13fa99f475555672eff782d2ad027f08cf25e8daac4522a25937b515fd1c39f6feeb5364f4d552b5f059b71c8f0135f840c72a47d57f8bb9db6125545c6a666a7b9e2ec26b8420f96f1a4d67c"};
    private List<String> urlList = Arrays.asList(strings);
    private int mFinishCount;   //完成的任务个数

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_apk);
        findview();
        onclick();
        donetwork();

    }

    private void donetwork() {
        DownloadServiceApk.setUIHandler(new Handler(this));
    }

    private void onclick() {
        mBtnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 判断是否已下载完毕 否则正常下载
                if (AppUtils.isAppInstalled(Config.TEMP_PKG)) {
                    ToastUtils.showShort("此应用已安装");
                } else {
                    PermissionHelper.requestStorage(new PermissionHelper.OnPermissionGrantedListener() {
                        @Override
                        public void onPermissionGranted() {
                            if (!FileUtils.isFileExists(Config.TEMP_APK_PATH)) {
                                Intent intent = new Intent("hs.act.DownloadServiceApk");
                                intent.setPackage(ApkIntentServiceActivity.this.getPackageName());
                                for (String url : urlList) {
                                    intent.putExtra(DownloadServiceApk.DOWNLOAD_URL, url);
                                    startService(intent);
                                }
                            } else {
                                listener.onReleased(Config.TEMP_APK_PATH);
                                LogUtils.d("test apk existed.");
                            }
                        }
                    });
                }
            }
        });
    }

    private final OnReleasedListener listener = new OnReleasedListener() {

        @Override
        public void onReleased(String local_temp_path) {
            AppUtils.installApp(local_temp_path);
        }
    };

    private void findview() {
        tv1 = findViewById(R.id.tv1);
        mBtnDownload = findViewById(R.id.btn_download);
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg != null) {
            switch (msg.what) {
                case DownloadServiceApk.WHAT_DOWNLOAD_FINISHED:
                    if ((boolean) msg.obj) {
                        ToastUtils.showLong("完成 " + (++mFinishCount) + "个任务");
                        if (AppUtils.isAppInstalled(Config.TEMP_PKG)) {
                            ToastUtils.showShort("此应用已安装");
                        } else {
                            PermissionHelper.requestStorage(new PermissionHelper.OnPermissionGrantedListener() {
                                @Override
                                public void onPermissionGranted() {
                                    if (!FileUtils.isFileExists(Config.TEMP_APK_PATH)) {
                                        new ReleaseInstallApkTask(listener, Config.TEMP_APK_PATH).execute();
                                    } else {
                                        listener.onReleased(Config.TEMP_APK_PATH);
                                        LogUtils.d("test apk existed.");
                                    }
                                }
                            });
                        }

                    }
                    break;
                case DownloadServiceApk.WHAT_DOWNLOAD_STARTED:

                    tv1.setText(tv1.getText() + (String) msg.obj);
                    break;
            }
        }
        return true;
    }
}