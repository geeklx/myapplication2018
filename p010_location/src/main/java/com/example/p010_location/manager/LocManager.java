package com.example.p010_location.manager;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.p010_location.bean.LocationBean;
import com.example.p010_location.bean.LocationErrorCode;
import com.example.p010_location.listener.LocListener;
import com.example.p010_location.listener.LocManagerListener;

public class LocManager implements LocManagerListener {

    private LocListener listener;
    private LocServer server;

//    public static volatile LocManager mInstance;
//
//    public static  LocManager getInstance() {
//        if (mInstance == null) {
//            synchronized (LocManager.class) {
//                if (mInstance == null) {
//                    mInstance = new LocManager();
//                }
//            }
//        }
//        return mInstance;
//    }

    @Override
    public void init(Context context) {
        server = new LocServer(context,aMapLocationListener,getOption());
    }

    private AMapLocationListener aMapLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (null!=aMapLocation&&aMapLocation.getErrorCode()==0&&aMapLocation.getLatitude()!=0.0&&aMapLocation.getLongitude()!=0.0){
                if (listener!=null){
                    LocationBean bean = new LocationBean();
                    bean.setAdCode(aMapLocation.getAdCode());
                    bean.setAddress(aMapLocation.getAddress());
                    bean.setAreaInterestName(aMapLocation.getAoiName());
                    bean.setCity(aMapLocation.getCity());
                    bean.setCityCode(aMapLocation.getCityCode());
                    bean.setDistrict(aMapLocation.getDistrict());
                    bean.setLatitude(aMapLocation.getLatitude()+"");
                    bean.setLongitude(aMapLocation.getLongitude()+"");
                    bean.setPointInterestName(aMapLocation.getPoiName());
                    bean.setProvince(aMapLocation.getProvince());
                    bean.setStreet(aMapLocation.getStreet());
                    listener.success(bean);
                }
            }else {
                if (listener!=null){
                    listener.fail(LocationErrorCode.ERROR_UNKNOWN);
                }
            }
        }
    };

    private AMapLocationClientOption getOption(){
        return new AMapLocationClientOption().setOnceLocation(true);
    }

    @Override
    public void setListener(LocListener listener) {
        this.listener= listener;
    }

    @Override
    public void onStart() {
        server.start();
    }

    @Override
    public boolean enStarting() {
        return server.enStarting();
    }

    @Override
    public void onStop() {
        if (server!=null){
            server.stop();
        }
    }

    @Override
    public void onDestory() {
        if (server!=null){
            server.destory();
        }
    }
}
