package com.example.p021_welcome.demo6;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.p021_welcome.R;
import com.example.p021_welcome.base.BaseWelcomeAdapter;
import com.example.p021_welcome.base.DataImgUtil;

import java.util.ArrayList;
import java.util.List;

public class DemoAct6 extends AppCompatActivity {

    private ViewPager vp;
    BaseWelcomeAdapter adapter;
    private DataImgUtil dataImgUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main6);
        getSupportActionBar().hide();
        dataImgUtil = new DataImgUtil();
        initViews();
    }

    private List<View> items;

    private void initViews() {
        items = new ArrayList<>();
        items = dataImgUtil.getListDrawable(this, DataImgUtil.drawableList1);
        adapter = new BaseWelcomeAdapter(items);
        //
        vp = findViewById(R.id.vp);
        vp.setAdapter(adapter);
    }

    public void buttonClick(View view) {
        items.clear();
        // 方法1
        items = dataImgUtil.getListDrawable(this, DataImgUtil.drawableList2);
        adapter.setContacts(items);
        vp.setAdapter(adapter);
        // 方法2
//        for (int i = 0; i < DataImgUtil.drawableList2.length; i++) {
//            items.add(dataImgUtil.createImageView(this, DataImgUtil.drawableList2[i]));
//        }
//        vp.setAdapter(adapter);
    }
}
