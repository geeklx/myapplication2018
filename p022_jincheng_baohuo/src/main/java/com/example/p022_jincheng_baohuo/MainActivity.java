package com.example.p022_jincheng_baohuo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.p022_jincheng_baohuo.demo1.MainActivity1;
import com.example.p022_jincheng_baohuo.demo2.MainActivity2;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void DEMO1(View view) {
        startActivity(new Intent(MainActivity.this, MainActivity1.class));
    }

    public void DEMO2(View view) {
        startActivity(new Intent(MainActivity.this, MainActivity2.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        android.os.Process.killProcess(android.os.Process.myPid());
//        System.exit(0);
    }
}
