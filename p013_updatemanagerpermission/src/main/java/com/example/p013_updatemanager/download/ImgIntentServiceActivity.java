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
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.p013_updatemanager.R;
import com.example.p013_updatemanager.download.intenservice.DownloadServiceImg;

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

public class ImgIntentServiceActivity extends AppCompatActivity implements Handler.Callback {

    private ImageView mIvDisplay;
    private Button mBtnDownload;
    private TextView mTvStatus;

    private List<String> urlList = Arrays.asList("https://ws1.sinaimg.cn/large/610dc034ly1fgepc1lpvfj20u011i0wv.jpg",
            "https://ws1.sinaimg.cn/large/d23c7564ly1fg6qckyqxkj20u00zmaf1.jpg",
            "https://ws1.sinaimg.cn/large/610dc034ly1fgchgnfn7dj20u00uvgnj.jpg");
    private int mFinishCount;   //完成的任务个数

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_img);
        findview();
        onclick();
        donetwork();

    }

    private void donetwork() {

        DownloadServiceImg.setUIHandler(new Handler(this));
    }

    private void onclick() {
        mBtnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("hs.act.DownloadServiceImg");
                intent.setPackage(ImgIntentServiceActivity.this.getPackageName());
                for (String url : urlList) {
                    intent.putExtra(DownloadServiceImg.DOWNLOAD_URL, url);
                    startService(intent);
                }
                mBtnDownload.setEnabled(false);
            }
        });
    }

    private void findview() {
        mIvDisplay = findViewById(R.id.iv_display);
        mBtnDownload = findViewById(R.id.btn_download);
        mTvStatus = findViewById(R.id.tv_status);
    }


    @Override
    public boolean handleMessage(final Message msg) {
        if (msg != null) {
            switch (msg.what) {
                case DownloadServiceImg.WHAT_DOWNLOAD_FINISHED:
                    mIvDisplay.setImageBitmap((Bitmap) msg.obj);
                    mBtnDownload.setText("完成 " + (++mFinishCount) + "个任务");
                    break;
                case DownloadServiceImg.WHAT_DOWNLOAD_STARTED:
                    mTvStatus.setText(mTvStatus.getText() + (String) msg.obj);
                    break;
            }
        }
        return true;
    }
}