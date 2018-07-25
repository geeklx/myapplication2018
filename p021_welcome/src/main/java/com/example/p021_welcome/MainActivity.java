package com.example.p021_welcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.p021_welcome.demo1.DemoAct1;
import com.example.p021_welcome.demo2.DemoAct2;
import com.example.p021_welcome.demo3.DemoAct3;
import com.example.p021_welcome.demo4.DemoAct4;
import com.example.p021_welcome.demo5.DemoAct5;
import com.example.p021_welcome.demo6.DemoAct6;
import com.example.p021_welcome.demo7.DemoAct7;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DemoAct1.class));
            }
        });
        findViewById(R.id.tv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DemoAct2.class));
            }
        });
        findViewById(R.id.tv3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DemoAct3.class));
            }
        });
        findViewById(R.id.tv4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DemoAct4.class));
            }
        });
        findViewById(R.id.tv5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DemoAct5.class));
            }
        });
        findViewById(R.id.tv6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DemoAct6.class));
            }
        });
        findViewById(R.id.tv7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DemoAct7.class));
            }
        });
    }
}
