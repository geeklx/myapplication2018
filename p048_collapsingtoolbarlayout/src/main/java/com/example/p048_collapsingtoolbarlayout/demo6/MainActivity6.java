package com.example.p048_collapsingtoolbarlayout.demo6;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

import com.example.p048_collapsingtoolbarlayout.R;

import java.lang.reflect.Field;

public class MainActivity6 extends AppCompatActivity {
    //刷新bufen
    private SwipeRefreshLayout mSwipeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_main6);
        refresh();
    }


    private void refresh() {
        mSwipeLayout = findViewById(R.id.refresh);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeLayout.setRefreshing(false);
                    }
                }, 5000);
            }
        });
        mSwipeLayout.setColorSchemeColors(getResources().getIntArray(R.array.swipeRefreshColors));
//        mSwipeLayout.setColorScheme(getResources().getColor(android.R.color.holo_blue_bright),
//                getResources().getColor(android.R.color.holo_green_light),
//                getResources().getColor(android.R.color.holo_orange_light),
//                getResources().getColor(android.R.color.holo_red_light));
        ViewTreeObserver vto = mSwipeLayout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                final DisplayMetrics metrics = getResources()
                        .getDisplayMetrics();
                Float mDistanceToTriggerSync = Math.min(
                        ((View) mSwipeLayout.getParent()).getHeight() * 0.6f,
                        150 * metrics.density);
                try {
                    Field field = SwipeRefreshLayout.class
                            .getDeclaredField("mDistanceToTriggerSync");
                    field.setAccessible(true);
                    field.setFloat(mSwipeLayout, mDistanceToTriggerSync);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ViewTreeObserver obs = mSwipeLayout.getViewTreeObserver();
                obs.removeOnGlobalLayoutListener(this);
            }
        });
    }
}
