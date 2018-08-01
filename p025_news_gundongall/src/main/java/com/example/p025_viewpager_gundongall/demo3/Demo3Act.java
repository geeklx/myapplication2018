package com.example.p025_viewpager_gundongall.demo3;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.p025_viewpager_gundongall.ItemEntity;
import com.example.p025_viewpager_gundongall.R;
import com.example.p025_viewpager_gundongall.utils.FadeTransitionImageView;
import com.example.p025_viewpager_gundongall.utils.HorizontalTransitionLayout;
import com.example.p025_viewpager_gundongall.utils.PileLayout;
import com.example.p025_viewpager_gundongall.utils.VerticalTransitionLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Demo3Act extends AppCompatActivity {


    private View positionView;
    private PileLayout pileLayout;
    private List<ItemEntity> dataList;

    private int lastDisplay = -1;

    private ObjectAnimator transitionAnimator;

    private HorizontalTransitionLayout countryView, temperatureView;
    private VerticalTransitionLayout addressView, timeView;
    private FadeTransitionImageView bottomView;
    private Animator.AnimatorListener animatorListener;
    private TextView descriptionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main3);

        positionView = findViewById(R.id.positionView);
        countryView = (HorizontalTransitionLayout) findViewById(R.id.countryView);
        temperatureView = (HorizontalTransitionLayout) findViewById(R.id.temperatureView);
        pileLayout = (PileLayout) findViewById(R.id.pileLayout);
        addressView = (VerticalTransitionLayout) findViewById(R.id.addressView);
        descriptionView = (TextView) findViewById(R.id.descriptionView);
        timeView = (VerticalTransitionLayout) findViewById(R.id.timeView);
        bottomView = (FadeTransitionImageView) findViewById(R.id.bottomImageView);


        animatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                countryView.onAnimationEnds();
                temperatureView.onAnimationEnds();
                addressView.onAnimationEnds();
                bottomView.onAnimationEnds();
                timeView.onAnimationEnds();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        };

        // 3. PileLayout绑定Adapter
        initDataList();
        pileLayout.setAdapter(new PileLayout.Adapter() {
            @Override
            public int getLayoutId() {
                return R.layout.item_layout2;
            }

            @Override
            public void bindView(View view, int position) {
                ViewHolder viewHolder = (ViewHolder) view.getTag();
                if (viewHolder == null) {
                    viewHolder = new ViewHolder();
                    viewHolder.imageView = (ImageView) view.findViewById(R.id.imageView);
                    view.setTag(viewHolder);
                }

                Glide.with(Demo3Act.this).load(dataList.get(position).getCoverImageUrl()).into(viewHolder.imageView);
            }

            @Override
            public int getItemCount() {
                return dataList.size();
            }

            @Override
            public void displaying(int position) {
                descriptionView.setText(dataList.get(position).getDescription() + " Since the world is so beautiful, You have to believe me, and this index is " + position);
                if (lastDisplay < 0) {
                    initSecene(position);
                    lastDisplay = 0;
                } else if (lastDisplay != position) {
                    transitionSecene(position);
                    lastDisplay = position;
                }
            }

            @Override
            public void onItemClick(View view, int position) {
                super.onItemClick(view, position);
            }

            class ViewHolder {
                ImageView imageView;
            }
        });

    }

    private void initSecene(int position) {
        countryView.firstInit(dataList.get(position).getCountry());
        temperatureView.firstInit(dataList.get(position).getTemperature());
        addressView.firstInit(dataList.get(position).getAddress());
        bottomView.firstInit(dataList.get(position).getMapImageUrl());
        timeView.firstInit(dataList.get(position).getTime());
    }

    private void transitionSecene(int position) {
        if (transitionAnimator != null) {
            transitionAnimator.cancel();
        }

        countryView.saveNextPosition(position, dataList.get(position).getCountry() + "-" + position);
        temperatureView.saveNextPosition(position, dataList.get(position).getTemperature());
        addressView.saveNextPosition(position, dataList.get(position).getAddress());
        bottomView.saveNextPosition(position, dataList.get(position).getMapImageUrl());
        timeView.saveNextPosition(position, dataList.get(position).getTime());

        transitionAnimator = ObjectAnimator.ofFloat(this, "transitionAnimatorValue", 0.0f, 1.0f);
        transitionAnimator.setDuration(300);
//        transitionAnimator.setRepeatCount(ObjectAnimator.INFINITE);
//        transitionAnimator.setRepeatMode(ObjectAnimator.RESTART);
        transitionAnimator.start();
        transitionAnimator.addListener(animatorListener);

    }

    /**
     * 从asset读取文件json数据
     */
    private void initDataList() {
        dataList = new ArrayList<>();
        try {
            InputStream in = getAssets().open("preset.config");
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            String jsonStr = new String(buffer, "UTF-8");
            JSONObject jsonObject = new JSONObject(jsonStr);
            JSONArray jsonArray = jsonObject.optJSONArray("result");
            if (null != jsonArray) {
                int len = jsonArray.length();
                for (int j = 0; j < 3; j++) {
                    for (int i = 0; i < len; i++) {
                        JSONObject itemJsonObject = jsonArray.getJSONObject(i);
                        ItemEntity itemEntity = new ItemEntity(itemJsonObject);
                        dataList.add(itemEntity);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private float transitionAnimatorValue;
    /**
     * 属性动画
     */
    public void setTransitionAnimatorValue(float value) {
        this.transitionAnimatorValue = value;
        countryView.duringAnimation(value);
        temperatureView.duringAnimation(value);
        addressView.duringAnimation(value);
        bottomView.duringAnimation(value);
        timeView.duringAnimation(value);
    }

    public float getTransitionAnimatorValue() {
        return transitionAnimatorValue;
    }


}
