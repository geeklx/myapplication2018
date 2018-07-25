package com.example.p021_welcome.demo1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.p021_welcome.R;
import com.example.p021_welcome.base.DataImgUtil;
import com.huanhailiuxin.coolviewpager.CoolViewPager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemoAct1 extends AppCompatActivity {

    private List<View> items1;
    private List<View> items2;
    private CoolViewPager vp;
    BaseWelcomeAdapter1 adapter1;
    BaseWelcomeAdapter2 adapter2;
    private DataImgUtil dataImgUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main1);
        getSupportActionBar().hide();
        dataImgUtil = new DataImgUtil();
        initViews();
    }

    private void initViews() {
        items1 = new ArrayList<>();
        items1 = dataImgUtil.getListDrawable(this, DataImgUtil.drawableList1);
        items2 = new ArrayList<>();
        items2 = dataImgUtil.getListDrawable(this, DataImgUtil.drawableList2);
        adapter1 = new BaseWelcomeAdapter1(items1);
        adapter2 = new BaseWelcomeAdapter2(items2);
        //
        vp = findViewById(R.id.vp);
        vp.setScrollMode(CoolViewPager.ScrollMode.HORIZONTAL);
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

    private Map<String, Bitmap> imgs = new HashMap<String, Bitmap>();

    public View createImageView(Context context, int imgResId) {
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new CoolViewPager.LayoutParams());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if (imgs.get("" + imgResId) != null) {
            imageView.setImageBitmap(imgs.get("" + imgResId));
        } else {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), imgResId, options);
            imgs.put("" + imgResId, bitmap);
            imageView.setImageBitmap(bitmap);
        }
        return imageView;
    }
}
