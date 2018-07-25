package com.example.p021_welcome.demo7;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.p021_welcome.R;
import com.example.p021_welcome.base.BaseWelcomeAdapter;
import com.huanhailiuxin.coolviewpager.CoolViewPager;

import java.util.ArrayList;
import java.util.List;

public class DemoAct7 extends AppCompatActivity {

    private static String[] stringList1 = new String[]{"http://img3.16fan.com/live/origin/201807/25/BD2Bd532974ae.jpg",
            "http://img3.16fan.com/live/origin/201807/25/435Aeeafe3c44.jpg",
            "http://img3.16fan.com/live/origin/201807/25/8EC015f2e409a.jpg",
            "http://img3.16fan.com/live/origin/201807/25/5CD03577288cc.jpg"
    };
    private static String[] stringList2 = new String[]{"http://img3.16fan.com/live/origin/201807/23/50740d1408f4f.jpg",
            "http://img3.16fan.com/live/origin/201807/23/BD32bea2bb4cf.jpg",
            "http://img3.16fan.com/live/origin/201807/23/FBA62f794fafd.jpg",
            "http://img3.16fan.com/live/origin/201807/23/7F382fed29e9d.jpg"
    };
    private List<View> items1;
    private List<View> items2;
    private CoolViewPager vp;
    private BaseWelcomeAdapter adapter1;
    private BaseWelcomeAdapter adapter2;

    private List<View> getList(String... stringList) {
        List<View> items = new ArrayList<View>();
        for (int i = 0; i < stringList.length; i++) {
            ImageView imageView = new ImageView(DemoAct7.this);
            imageView.setLayoutParams(new CoolViewPager.LayoutParams());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);// FIT_XY
            Glide.with(DemoAct7.this).load(stringList[i]).into(imageView);
            items.add(imageView);
        }
        return items;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main7);
        getSupportActionBar().hide();
        initViews();
    }

    private void initViews() {
        items1 = new ArrayList<View>();
        items2 = new ArrayList<View>();
        items1 = getList(stringList1);
        items2 = getList(stringList2);
        adapter1 = new BaseWelcomeAdapter(items1);
        adapter2 = new BaseWelcomeAdapter(items2);
        //
        vp = findViewById(R.id.vp);
        vp.setScrollMode(CoolViewPager.ScrollMode.HORIZONTAL);
        vp.setAutoScroll(true, 3000);
        vp.setScrollDuration(true, 2000);
        vp.setAdapter(adapter1);

    }


    private int index = 0;

    public void buttonClick(View view) {
        if (index % 2 == 0) {
            vp.setScrollMode(CoolViewPager.ScrollMode.VERTICAL);
            vp.setAdapter(adapter2);
            index = 1;
        } else {
            vp.setScrollMode(CoolViewPager.ScrollMode.HORIZONTAL);
            vp.setAdapter(adapter1);
            index = 0;
        }
    }


}
