package com.example.shining.libutils.utilslib.base;

import android.app.Activity;
import android.view.View;

import com.example.shining.libutils.utilslib.app.App;
import com.example.shining.libutils.utilslib.app.AppManager;
import com.example.shining.libutils.utilslib.etc.IndexViewPager;


/**
 * Created by geek on 2016/8/12.
 */

public abstract class BaseIndexFragment extends BaseFragment {

    /**
     * 获取MainActivity的ViewPager
     * @return
     */
    public IndexViewPager findIndexViewPager() {
//        if (AppManager.getInstance().top() instanceof MainActivity){
//
//        }
//        if (getActivity() == null || !(getActivity() instanceof MainActivity)) { return null;}
        return null;
//        return ((Demo3Activity) getActivity()).getViewPager();
    }

    /**
     * 给Viewpager设置事件分发对象
     * @param view
     */
    public void setPagerTouchBindView(View view) {
        IndexViewPager pager = findIndexViewPager();
        if (pager != null) { pager.bind(view);}
    }

    public void removePagerTouchBindView(View view) {
        IndexViewPager pager = findIndexViewPager();
        if (pager != null) { pager.remove(view);}
    }


    public void call(Object value) {

    }
}
