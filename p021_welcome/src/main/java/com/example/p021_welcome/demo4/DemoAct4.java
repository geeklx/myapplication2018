package com.example.p021_welcome.demo4;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
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

public class DemoAct4 extends AppCompatActivity {

    private List<View> items;
    private CoolViewPager vp;
    BaseWelcomeAdapter adapter;
    private DataImgUtil dataImgUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main4);
        getSupportActionBar().hide();
        dataImgUtil = new DataImgUtil();
        initViews();
    }

    private void initViews() {
        vp = findViewById(R.id.vp);
        initData();
    }

    private void initData() {
        items = new ArrayList<>();
        items = dataImgUtil.getListDrawable(this, DataImgUtil.drawableList1);
        adapter = new BaseWelcomeAdapter(items);
        vp.setAdapter(adapter);
    }

    private int currIndex = -1;
    private ViewPager.PageTransformer[] verticals = new ViewPager.PageTransformer[]{
            new com.huanhailiuxin.coolviewpager.transformer.VerticalAccordionTransformer(),
            new com.huanhailiuxin.coolviewpager.transformer.VerticalRotateTransformer(),
            new com.huanhailiuxin.coolviewpager.transformer.VerticalDepthPageTransformer(),
            new com.huanhailiuxin.coolviewpager.transformer.VerticalRotateDownTransformer(),
            new com.huanhailiuxin.coolviewpager.transformer.VerticalZoomInTransformer()
    };

    public void buttonClick(View view) {
        initData();
        currIndex = (++currIndex) % verticals.length;
        vp.setPageTransformer(false, verticals[currIndex]);
    }
}
