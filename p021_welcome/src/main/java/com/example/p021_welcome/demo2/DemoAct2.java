package com.example.p021_welcome.demo2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.p021_welcome.R;
import com.example.p021_welcome.base.BaseWelcomeAdapter;
import com.example.p021_welcome.base.DataImgUtil;
import com.huanhailiuxin.coolviewpager.CoolViewPager;

import java.util.ArrayList;
import java.util.List;

public class DemoAct2 extends AppCompatActivity {

    private List<View> items;
    private CoolViewPager vp;
    private BaseWelcomeAdapter adapter;
    private DataImgUtil dataImgUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();
        dataImgUtil = new DataImgUtil();
        initViews();
    }

    private void initViews() {
        items = new ArrayList<>();
        items = dataImgUtil.getListDrawable(this, DataImgUtil.drawableList1);
        adapter = new BaseWelcomeAdapter(items);
        //
        vp = findViewById(R.id.vp);
        vp.setScrollMode(CoolViewPager.ScrollMode.HORIZONTAL);
        vp.setAdapter(adapter);
    }

    private int colorIndex = 0;
    private int[] colors = new int[]{Color.GREEN, Color.RED, Color.BLUE};

    public void buttonClick(View view) {
        colorIndex = (++colorIndex) % colors.length;// 好方法
        vp.setEdgeEffectColor(colors[colorIndex]);
    }
}
