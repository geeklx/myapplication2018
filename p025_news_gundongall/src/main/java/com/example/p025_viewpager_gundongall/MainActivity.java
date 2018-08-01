package com.example.p025_viewpager_gundongall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.p025_viewpager_gundongall.demo1.Demo1Act;
import com.example.p025_viewpager_gundongall.demo3.Demo3Act;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void TV1(View view) {
        startActivity(new Intent(MainActivity.this, Demo1Act.class));
    }


    public void TV3(View view) {
        startActivity(new Intent(MainActivity.this, Demo3Act.class));
    }

}
