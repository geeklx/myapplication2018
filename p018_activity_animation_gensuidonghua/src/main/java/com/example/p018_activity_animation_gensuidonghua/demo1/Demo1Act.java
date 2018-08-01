package com.example.p018_activity_animation_gensuidonghua.demo1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.p018_activity_animation_gensuidonghua.R;

public class Demo1Act extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1);

    }

    public void BTN1(View view) {
        startActivity(new Intent(Demo1Act.this, Demo1Act1.class));
        //        overridePendingTransition(R.anim.close_main, R.anim.open_next);// 可以
//        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
//        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_left);
//        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);// 可以
//        overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);// 可以
//        overridePendingTransition(R.anim.out_from_right, R.anim.in_from_left);// 可以

//        overridePendingTransition(R.anim.out_from_left, R.anim.in_from_right);// 可以
//        overridePendingTransition(R.anim.alpha_action, R.anim.scale_action);// 可以
//        overridePendingTransition(R.anim.scale_rotate, R.anim.scale_translate);// 可以
//        overridePendingTransition(R.anim.hold, R.anim.fade);// 可以

    }

}
