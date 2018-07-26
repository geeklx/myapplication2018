package com.example.p012_interpolator;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;


public class CarAct extends Activity {

    private TextView tv1;
    private CartAnim cartAnim;
    int i = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = findViewById(R.id.tv1);
        cartAnim = CartAnim.obtain(CarAct.this);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                cartAnim.start(i + "", tv1, new float[]{-200.f, -40.f}, new long[]{600, 500, 300});

            }
        });
    }

    @Override
    protected void onDestroy() {
        cartAnim.cancel();
        super.onDestroy();

    }
}
