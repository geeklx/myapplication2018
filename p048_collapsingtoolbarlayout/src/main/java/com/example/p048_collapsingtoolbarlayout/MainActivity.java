package com.example.p048_collapsingtoolbarlayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.p048_collapsingtoolbarlayout.demo1.MainActivity1;
import com.example.p048_collapsingtoolbarlayout.demo2.MainActivity2;
import com.example.p048_collapsingtoolbarlayout.demo3.MainActivity3;
import com.example.p048_collapsingtoolbarlayout.demo4.MainActivity4;
import com.example.p048_collapsingtoolbarlayout.demo5.MainActivity5;
import com.example.p048_collapsingtoolbarlayout.demo6.MainActivity6;
import com.example.p048_collapsingtoolbarlayout.demo7.MainActivity7;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void BTN1(View view) {
        startActivity(new Intent(this, MainActivity1.class));
    }
    public void BTN2(View view) {
        startActivity(new Intent(this, MainActivity2.class));
    }
    public void BTN3(View view) {
        startActivity(new Intent(this, MainActivity3.class));
    }
    public void BTN4(View view) {
        startActivity(new Intent(this, MainActivity4.class));
    }
    public void BTN5(View view) {
        startActivity(new Intent(this, MainActivity5.class));
    }
    public void BTN6(View view) {
        startActivity(new Intent(this, MainActivity6.class));
    }
    public void BTN7(View view) {
        startActivity(new Intent(this, MainActivity7.class));
    }
    public void BTN8(View view) {
        startActivity(new Intent(this, MainActivity7.class));
    }




}
