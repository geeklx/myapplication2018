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

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.p013_updatemanager.R;
import com.example.p013_updatemanager.download.handlerthread.DownloadHandlerThread;


/**
 * Description:
 * <br> HandlerThread 示例程序
 * <p>
 * <br> Created by shixinzhang on 17/6/7.
 * <p>
 * <br> Email: shixinzhang2016@gmail.com
 * <p>
 * <a  href="https://about.me/shixinzhang">About me</a>
 */

public class HandlerThreadActivity extends AppCompatActivity implements Handler.Callback {

    private TextView mTvStartMsg;
    private TextView mTvFinishMsg;
    private Button mBtnStartDownload;

    private Handler mUIHandler;
    private DownloadHandlerThread mDownloadThread;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_handlerthread);
        findview();
        onclick();
        donetwork();
    }

    private void donetwork() {
        mUIHandler = new Handler(this);
        mDownloadThread = new DownloadHandlerThread("下载线程");
        mDownloadThread.setUIHandler(mUIHandler);
        mDownloadThread.setDownloadUrls("http://pan.baidu.com/s/1qYc3EDQ", "http://bbs.005.tv/thread-589833-1-1.html", "http://list.youku.com/show/id_zc51e1d547a5b11e2a19e.html?");

    }

    private void onclick() {
        mBtnStartDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDownloadThread.start();
                mBtnStartDownload.setText("正在下载");
                mBtnStartDownload.setEnabled(false);
            }
        });
    }

    private void findview() {
        mTvStartMsg = findViewById(R.id.tv_start_msg);
        mTvFinishMsg = findViewById(R.id.tv_finish_msg);
        mBtnStartDownload = findViewById(R.id.btn_start_download);
    }

    @Override
    public boolean handleMessage(final Message msg) {
        switch (msg.what) {
            case DownloadHandlerThread.TYPE_FINISHED:
                mTvFinishMsg.setText(mTvFinishMsg.getText().toString() + "\n " + msg.obj);
                break;
            case DownloadHandlerThread.TYPE_START:
                mTvStartMsg.setText(mTvStartMsg.getText().toString() + "\n " + msg.obj);
                break;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDownloadThread.quitSafely();
    }
}