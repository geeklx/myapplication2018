package com.example.p003_animation_lineview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.libanimation.loading.ShowLoadingUtil;
import com.example.p003_animation_lineview.ui.LaunchActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ShowLoadingUtil.showLoading(MainActivity.this, "加载中...");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, LaunchActivity.class));
            }
        }, 1000);
//        Intent intent = new Intent(android.provider.Settings.ACTION_PRIVACY_SETTINGS);
//        startActivity(intent);

    }
}
