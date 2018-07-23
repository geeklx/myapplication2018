package com.example.p018_activity_animation_gensuidonghua;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mjun.mtransition.ITransitPrepareListener;
import com.mjun.mtransition.MTransition;
import com.mjun.mtransition.MTransitionManager;
import com.mjun.mtransition.MTransitionView;
import com.mjun.mtransition.MTranstionUtil;
import com.mjun.mtransition.TransitListenerAdapter;

public class MainActivityB extends AppCompatActivity {

    private RelativeLayout rl1;
    private LinearLayout ll1;
    private ImageView tv1;
    private ImageView tv2;
    private ImageView tv3;
    private ImageView tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainb);
        rl1 = findViewById(R.id.rl1);
        ll1 = findViewById(R.id.ll1);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        final MTransition transition = MTransitionManager.getInstance().getTransition("geek");
        transition.toPage().setContainer(rl1, new ITransitPrepareListener() {
            @Override
            public void onPrepare(MTransitionView container) {
                container.alpha(0f, 1f);
                MTransitionView icon1 = transition.toPage().addTransitionView("icon1", tv1);
                MTransitionView icon2 = transition.toPage().addTransitionView("icon2", tv2);
                MTransitionView icon3 = transition.toPage().addTransitionView("icon3", tv3);
                MTransitionView icon4 = transition.toPage().addTransitionView("icon4", tv4);
                transition.fromPage().getTransitionView("icon1").above(icon1).transitTo(icon1, true);
                transition.fromPage().getTransitionView("icon2").above(icon2).transitTo(icon2, true);
                transition.fromPage().getTransitionView("icon3").above(icon3).transitTo(icon3, true);
                transition.fromPage().getTransitionView("icon4").above(icon4).transitTo(icon4, true);
            }
        });

        transition.setDuration(1500);
        transition.start();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MTransitionManager.getInstance().destoryTransition("geek");
    }

    @Override
    public void finish() {
        super.finish();
        MTranstionUtil.removeActivityAnimation(MainActivityB.this);
    }

    @Override
    public void onBackPressed() {
        final MTransition transition = MTransitionManager.getInstance().getTransition("geek");
        transition.reverse();
        transition.setOnTransitListener(new TransitListenerAdapter() {
            @Override
            public void onTransitEnd(MTransition transition, boolean reverse) {
                if (reverse) {
                    finish();
                    MTranstionUtil.removeActivityAnimation(MainActivityB.this);
                }
            }
        });
    }
}
