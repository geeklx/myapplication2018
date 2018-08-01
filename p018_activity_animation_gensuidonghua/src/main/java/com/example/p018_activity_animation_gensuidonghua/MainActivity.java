package com.example.p018_activity_animation_gensuidonghua;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.p018_activity_animation_gensuidonghua.anim.MainActivityA;
import com.example.p018_activity_animation_gensuidonghua.demo1.Demo1Act;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = findViewById(R.id.tv1);
        List<String> detailList = new ArrayList<>();
        String imgStr = "a,b,c,d,e,f,g,h";
        String url = "";
        if (!TextUtils.isEmpty(imgStr)) {
            String[] str = imgStr.split(",");
            detailList = Arrays.asList(str);
        }
        for (int i = 0; i < detailList.size(); i++) {
            url += detailList.get(i)+" ";
        }
        tv1.setText(url);

    }

    public void BTN1(View view) {
        startActivity(new Intent(MainActivity.this, MainActivityA.class));
    }

    public void BTN2(View view) {
        startActivity(new Intent(MainActivity.this, Demo1Act.class));
    }
}
