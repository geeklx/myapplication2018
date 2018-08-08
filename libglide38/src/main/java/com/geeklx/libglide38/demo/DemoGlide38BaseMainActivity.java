package com.geeklx.libglide38.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.geeklx.libglide38.R;
import com.geeklx.libglide38.base.ImageWatcher;
import com.geeklx.libglide38.base.MessagePicturesLayout;
import com.geeklx.libglide38.base.util.Utils;
import com.geeklx.libglide38.demo.domain.Data;

import java.util.Arrays;
import java.util.List;


public class DemoGlide38BaseMainActivity extends Activity implements MessagePicturesLayout.Callback, ImageWatcher.OnPictureLongPressListener, View.OnClickListener {
    private ImageWatcher vImageWatcher;
    private MessagePicturesLayout lPictures;
    private TextView mTvTitle;
    private ImageView mIvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_glide38_base);
        findView();
        setListener();
        doNetWork();
    }

    private void findView() {
        mTvTitle = findViewById(R.id.title);
        mIvBack = findViewById(R.id.ic_back);
        // 一般来讲， ImageWatcher 需要占据全屏的位置
        vImageWatcher = (ImageWatcher) findViewById(R.id.iw_image_watcher);
        lPictures = (MessagePicturesLayout) findViewById(R.id.mpl_pictures);
    }

    private void setListener() {
        mIvBack.setOnClickListener(this);
        lPictures.setCallback(this);
        // 长按图片的回调，你可以显示一个框继续提供一些复制，发送等功能
        vImageWatcher.setOnPictureLongPressListener(this);
    }

    private void doNetWork() {
        Intent intent = getIntent();
        int imageIndex;
        boolean isTranslucentStatus = false;

        mTvTitle.setText(intent.getStringExtra("title"));
        imageIndex = intent.getIntExtra("imageIndex", 0);

        // 如果是透明状态栏，你需要给ImageWatcher标记 一个偏移值，以修正点击ImageView查看的启动动画的Y轴起点的不正确
        vImageWatcher.setTranslucentStatus(!isTranslucentStatus ? Utils.calcStatusBarHeight(this) : 0);
        // 配置error图标
        vImageWatcher.setErrorImageRes(R.drawable.error_picture);
        lPictures.set(Data.get().get(imageIndex).getPictureThumbList(), Data.get().get(imageIndex).getPictureList());
    }

    @Override
    public void onThumbPictureClick(ImageView i, List<ImageView> imageGroupList, List<String> urlList) {
        vImageWatcher.show(i, imageGroupList, urlList);
    }

    @Override
    public void onPictureLongPress(ImageView v, String url, int pos) {
        Toast.makeText(v.getContext().getApplicationContext(), "长按了第" + (pos + 1) + "张图片", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if (vImageWatcher.handleBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.ic_back) {
            onBackPressed();
        }
    }
}
