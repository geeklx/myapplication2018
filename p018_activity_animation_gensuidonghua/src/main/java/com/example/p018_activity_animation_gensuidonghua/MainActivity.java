package com.example.p018_activity_animation_gensuidonghua;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mjun.mtransition.ITransitPrepareListener;
import com.mjun.mtransition.MTransition;
import com.mjun.mtransition.MTransitionManager;
import com.mjun.mtransition.MTransitionView;
import com.mjun.mtransition.MTranstionUtil;

public class MainActivity extends AppCompatActivity {

    private LinearLayout ll11;
    private LinearLayout ll1;
    private ImageView tv1;
    private ImageView tv2;
    private ImageView tv3;
    private ImageView tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll11 = findViewById(R.id.ll11);
        ll1 = findViewById(R.id.ll1);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        final MTransition transition = MTransitionManager.getInstance().createTransition("geek");
        transition.fromPage().setContainer(ll11, new ITransitPrepareListener() {
            @Override
            public void onPrepare(MTransitionView container) {
                // 传递需要做动画的View
                transition.fromPage().addTransitionView("icon1", tv1);
                transition.fromPage().addTransitionView("icon2", tv2);
                transition.fromPage().addTransitionView("icon3", tv3);
                transition.fromPage().addTransitionView("icon4", tv4);

            }
        });

    }

    public void BTN1(View view) {
        startActivity(new Intent(MainActivity.this, MainActivityB.class));
        MTranstionUtil.removeActivityAnimation(MainActivity.this);

    }
}
