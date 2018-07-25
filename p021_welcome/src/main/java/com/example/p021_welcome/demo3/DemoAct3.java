package com.example.p021_welcome.demo3;

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

public class DemoAct3 extends AppCompatActivity {

    private List<View> items;
    private CoolViewPager vp;
    BaseWelcomeAdapter adapter;
    private DataImgUtil dataImgUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN|WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main3);
        getSupportActionBar().hide();
        dataImgUtil = new DataImgUtil();
        initViews();
    }

    private void initViews() {
        items = new ArrayList<>();
        items = dataImgUtil.getListDrawable(this,DataImgUtil.drawableList1);
        adapter = new BaseWelcomeAdapter(items);
        //
        vp = findViewById(R.id.vp);
        vp.setScrollMode(CoolViewPager.ScrollMode.VERTICAL);
        vp.setAutoScroll(true,3000);
        vp.setScrollDuration(true,2000);
        vp.setAdapter(adapter);
    }

    int index = 0;
    public void buttonClick(View view) {
        if(index == 0){
            vp.toggleAutoScrollDirection();
            vp.setScrollDuration(true,1000);
            index = 1;
        }else{
            vp.toggleAutoScrollDirection();
            vp.setScrollDuration(true,500);
            index = 0;
        }
    }
}
