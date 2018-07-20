package com.example.p048_collapsingtoolbarlayout.demo4;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.p048_collapsingtoolbarlayout.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity4 extends AppCompatActivity {

    private DrawerLayout drawerlayout;
    private CollapsingToolbarLayoutState state;
    private AppBarLayout app_bar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView iv;
    private ImageView iv_icon;
    private ImageView iv_icon2;
    private Toolbar toolbar;
    private TabLayout tablayout;
    private ViewPager viewpager;
    private NavigationView nav_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_main4);
        findview();
        onclick();
        donetwork();


    }

    private void donetwork() {

    }

    private void onclick() {
        iv_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerlayout.isDrawerOpen(nav_view)) {
                    drawerlayout.closeDrawer(nav_view);
                } else {
                    drawerlayout.openDrawer(nav_view);
                }
            }
        });
        iv_icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity4.this, "点击了~", Toast.LENGTH_SHORT).show();
            }
        });
        app_bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //计算进度百分比
                float percent = Float.valueOf(Math.abs(verticalOffset)) / Float.valueOf(appBarLayout.getTotalScrollRange());
                //根据百分比做你想做的
                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                        collapsingToolbarLayout.setTitle("EXPANDED");//设置title为EXPANDED
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        collapsingToolbarLayout.setTitle("");//设置title不显示
                        iv_icon2.setVisibility(View.VISIBLE);//隐藏播放按钮
                        state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                    }
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        if (state == CollapsingToolbarLayoutState.COLLAPSED) {
                            iv_icon2.setVisibility(View.GONE);//由折叠变为中间状态时隐藏播放按钮
                        }
                        collapsingToolbarLayout.setTitle("INTERNEDIATE");//设置title为INTERNEDIATE
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
                    }
                }
            }
        });

    }

    private void findview() {
        drawerlayout = findViewById(R.id.drawerlayout);
        app_bar = findViewById(R.id.app_bar);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        iv = findViewById(R.id.iv);
        iv_icon = findViewById(R.id.iv_icon);
        iv_icon2 = findViewById(R.id.iv_icon2);
        toolbar = findViewById(R.id.toolbar);
        tablayout = findViewById(R.id.tablayout);
        viewpager = findViewById(R.id.viewpager);
        nav_view = findViewById(R.id.nav_view);
        iv.setImageResource(R.mipmap.img00);
        // toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        // CollapsingToolbarLayout
        collapsingToolbarLayout.setTitleEnabled(false);
        // 必须放在  setSupportActionBar(toolbar); 在 drawerlayout.addDrawerListener(toggle);之后才起作用
        toolbar.setNavigationIcon(null);
        // viewpager
        setupViewPager(viewpager);
        tablayout.setupWithViewPager(viewpager);

    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new TabFragment(), "Tab 1");
        adapter.addFrag(new TabFragment(), "Tab 2");
        adapter.addFrag(new TabFragment(), "Tab 3");
        adapter.addFrag(new TabFragment(), "Tab 4");

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}
