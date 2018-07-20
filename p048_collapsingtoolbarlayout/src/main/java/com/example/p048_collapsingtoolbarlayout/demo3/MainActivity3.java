package com.example.p048_collapsingtoolbarlayout.demo3;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.example.p048_collapsingtoolbarlayout.R;

public class MainActivity3 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        ImageView iv = findViewById(R.id.iv);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_drawer_home);
        collapsingToolbarLayout.setTitle("CollapsingToolbarLayout");
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        iv.setImageResource(R.mipmap.img00);
    }


}
