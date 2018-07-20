package com.example.p006_recycleview_alluses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class RecycleViewMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview_main);

        findViewById(R.id.tv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.demo1.act"));
            }
        });
        findViewById(R.id.tv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.demo2.act"));
            }
        });
        findViewById(R.id.tv3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.demo3.act"));
            }
        });
        findViewById(R.id.tv4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.demo4.act"));
            }
        });
        findViewById(R.id.tv5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.demo5.act"));
            }
        });
        findViewById(R.id.tv6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.demo6.act"));
            }
        });
        findViewById(R.id.tv7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.demo7.act"));
            }
        });
        findViewById(R.id.tv8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.demo8.act"));
            }
        });
    }
}
