package com.example.p015_view_huxi;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView tv1;
    private LinearLayout ll1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll1 = findViewById(R.id.ll1);
        tv1 = findViewById(R.id.tv1);
        // start
        BreathingViewHelper.setBreathingBackgroundColor(8000,ll1, getResources().getColor(R.color.colorPrimary));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // stop
                BreathingViewHelper.stopBreathingBackgroundColor(ll1);
            }
        }, 20000);

    }
}
