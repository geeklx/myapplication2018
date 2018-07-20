package com.example.p010_location.util;

import android.content.Context;

import com.example.p010_location.bean.LocationBean;
import com.example.p010_location.listener.LocListener;
import com.example.p010_location.manager.LocManager;

public class LocUtil {

    private static LocationBean bean = new LocationBean();

    public LocUtil(){

    }

    public static volatile LocUtil mInstance;

    public static LocUtil getInstance() {
        if (mInstance == null) {
            synchronized (LocUtil.class) {
                if (mInstance == null) {
                    mInstance = new LocUtil();
                }
            }
        }
        return mInstance;
    }

    public static LocationBean getLocationBean(Context context){
        if (context==null){
            throw new IllegalArgumentException("context can not null.");
        }else {
            getLocation(context,null);
        }
        return bean;
    };

    public static void getLocation(Context context, final LocListener listener){
        LocManager loc = new LocManager();
        loc.init(context);
        loc.setListener(new LocListener() {

            @Override
            public void success(LocationBean model) {
                bean = model;
                if (listener!=null){
                    listener.success(model);
                }
            }

            @Override
            public void fail(int msg) {
                if (listener!=null){
                    listener.fail(msg);
                }
            }
        });
        if (!loc.enStarting()){
            loc.onStart();
        }

    }

}
