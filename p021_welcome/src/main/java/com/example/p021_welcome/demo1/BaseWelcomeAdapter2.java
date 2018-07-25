package com.example.p021_welcome.demo1;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class BaseWelcomeAdapter2 extends PagerAdapter {
        private List<View> views;

        public BaseWelcomeAdapter2(List<View> items) {
            this.views = items;
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            //必须要先判断,否则报错:java.lang.IllegalStateException: The specified child already has a parent
            //ViewGroup的addView（）方法不能添加一个已存在父控件的视图,所以在执行前需要判断要添加的View实例是不是存在父控件.
            //如果存在父控件,需要其父控件先将该View移除掉,再执行addView
            if (views.get(position).getParent() != null) {
                ((ViewGroup) views.get(position).getParent()).removeView(views.get(position));
            }
            container.addView(views.get(position));
            return views.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }