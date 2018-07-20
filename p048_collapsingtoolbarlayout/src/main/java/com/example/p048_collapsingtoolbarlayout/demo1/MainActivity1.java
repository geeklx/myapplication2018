package com.example.p048_collapsingtoolbarlayout.demo1;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.p048_collapsingtoolbarlayout.R;

import java.lang.reflect.Method;

public class MainActivity1 extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main1);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        ImageView iv = findViewById(R.id.iv);
        toolbar = findViewById(R.id.toolbar);
        initbar();

        collapsingToolbarLayout.setTitle("CollapsingToolbarLayout");
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        iv.setImageResource(R.mipmap.img00);
    }

    private void initbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_notifications);
        //设置左侧NavigationIcon点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity1.this, "点击了左侧按钮", Toast.LENGTH_SHORT).show();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String msg = "";
                switch (item.getItemId()) {
                    case R.id.action_edit:
                        msg += "Click1 edit";
                        break;
                    case R.id.action_share:
                        msg += "Click2 share";
                        break;
                    case R.id.action_settings:
                        msg += "Click3 setting";
                        break;
                }

                if (!msg.equals("")) {
                    Toast.makeText(MainActivity1.this, msg, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @SuppressLint("RestrictedApi")
    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (menu != null) {
            if (menu.getClass() == MenuBuilder.class) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onPrepareOptionsPanel(view, menu);
    }


}