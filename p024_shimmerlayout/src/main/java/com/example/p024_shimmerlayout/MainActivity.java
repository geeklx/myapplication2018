package com.example.p024_shimmerlayout;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.p024_shimmerlayout.utils.ShimmerLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ShimmerLayout shimmerLayout = findViewById(R.id.shimmer_layout);
        final RelativeLayout rl1 = findViewById(R.id.rl1);
        shimmerLayout.startShimmerAnimation();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                shimmerLayout.stopShimmerAnimation();
                shimmerLayout.setVisibility(View.GONE);
                rl1.setVisibility(View.VISIBLE);
            }
        }, 3000);
    }
}
