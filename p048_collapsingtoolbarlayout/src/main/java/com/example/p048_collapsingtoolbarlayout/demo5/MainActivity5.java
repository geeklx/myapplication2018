package com.example.p048_collapsingtoolbarlayout.demo5;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.p048_collapsingtoolbarlayout.R;

public class MainActivity5 extends AppCompatActivity {

    private Toolbar toolbar;
    // 抽屉菜单对象
    private ActionBarDrawerToggle drawerbar;
    private DrawerLayout drawerLayout;
    private RelativeLayout main_left_drawer_layout, main_right_drawer_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        findview();
        onclick();
        donetwork();


    }

    private void donetwork() {

    }

    private void onclick() {
        //设置左侧NavigationIcon点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(main_left_drawer_layout)) {
                    drawerLayout.closeDrawer(main_left_drawer_layout);
                } else {
                    drawerLayout.openDrawer(main_left_drawer_layout);
                }
            }
        });
    }

    private void findview() {
        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.toolbar);
        main_left_drawer_layout = findViewById(R.id.main_left_drawer_layout);
        main_right_drawer_layout = findViewById(R.id.main_right_drawer_layout);

        // toolbar
        setSupportActionBar(toolbar);
        // 下面两句系统默认图标
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
//        toolbar.setNavigationIcon(R.mipmap.ic_notifications);// 自定义图标

        // DrawerLayout
        //设置菜单内容之外其他区域的背景色
//        drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED); //关闭手势滑动
//        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED); //打开手势滑动
        drawerbar = new ActionBarDrawerToggle(MainActivity5.this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            //菜单打开
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Toast.makeText(MainActivity5.this, "菜单打开", Toast.LENGTH_SHORT).show();
            }

            // 菜单关闭
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Toast.makeText(MainActivity5.this, "菜单关闭", Toast.LENGTH_SHORT).show();
            }
        };

        drawerLayout.addDrawerListener(drawerbar);
        drawerbar.syncState();
    }

    //左边菜单开关事件
    public void openLeftLayout(View view) {
        if (drawerLayout.isDrawerOpen(main_left_drawer_layout)) {
            drawerLayout.closeDrawer(main_left_drawer_layout);
        } else {
            drawerLayout.openDrawer(main_left_drawer_layout);
        }
    }

    // 右边菜单开关事件
    public void openRightLayout(View view) {
        if (drawerLayout.isDrawerOpen(main_right_drawer_layout)) {
            drawerLayout.closeDrawer(main_right_drawer_layout);
        } else {
            drawerLayout.openDrawer(main_right_drawer_layout);
        }
    }

}
