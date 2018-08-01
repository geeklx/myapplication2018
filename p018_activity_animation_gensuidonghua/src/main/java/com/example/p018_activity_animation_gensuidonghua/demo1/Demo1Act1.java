package com.example.p018_activity_animation_gensuidonghua.demo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.p018_activity_animation_gensuidonghua.R;

public class Demo1Act1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_demo12);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);// 可以
        overridePendingTransition(R.anim.open_main, R.anim.close_next);// 可以


    }
}
