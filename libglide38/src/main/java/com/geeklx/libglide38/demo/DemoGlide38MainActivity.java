package com.geeklx.libglide38.demo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.geeklx.libglide38.R;
import com.geeklx.libglide38.base.ImageWatcher;
import com.geeklx.libglide38.base.MessagePicturesLayout;
import com.geeklx.libglide38.base.SpaceItemDecoration;
import com.geeklx.libglide38.base.util.Utils;
import com.geeklx.libglide38.demo.adapter.MessageAdapter;
import com.geeklx.libglide38.demo.domain.Data;

import java.util.List;




public class DemoGlide38MainActivity extends Activity implements MessagePicturesLayout.Callback, ImageWatcher.OnPictureLongPressListener, View.OnClickListener {
    private ImageWatcher vImageWatcher;

    private RecyclerView vRecycler;
    private MessageAdapter adapter;
    private TextView mTvTitle;
    private ImageView mIvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        boolean isTranslucentStatus = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
            isTranslucentStatus = true;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_glide38);

        mTvTitle = findViewById(R.id.title);
        mTvTitle.setText(intent.getStringExtra("title"));
        mIvBack = findViewById(R.id.ic_back);
        mIvBack.setOnClickListener(this);

        vRecycler = (RecyclerView) findViewById(R.id.v_recycler);
        vRecycler.setLayoutManager(new LinearLayoutManager(this));
        vRecycler.addItemDecoration(new SpaceItemDecoration(this).setSpace(14).setSpaceColor(0xFFECECEC));
        vRecycler.setAdapter(adapter = new MessageAdapter(this).setPictureClickCallback(this));
        adapter.set(Data.get());

        // 一般来讲， ImageWatcher 需要占据全屏的位置
        vImageWatcher = (ImageWatcher) findViewById(R.id.v_image_watcher);
        // 如果是透明状态栏，你需要给ImageWatcher标记 一个偏移值，以修正点击ImageView查看的启动动画的Y轴起点的不正确
        vImageWatcher.setTranslucentStatus(!isTranslucentStatus ? Utils.calcStatusBarHeight(this) : 0);
        // 配置error图标
        vImageWatcher.setErrorImageRes(R.drawable.error_picture);
        // 长按图片的回调，你可以显示一个框继续提供一些复制，发送等功能
        vImageWatcher.setOnPictureLongPressListener(this);

        Utils.fitsSystemWindows(isTranslucentStatus, findViewById(R.id.v_fit));
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
