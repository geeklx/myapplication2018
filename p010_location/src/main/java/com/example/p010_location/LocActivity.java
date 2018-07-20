package com.example.p010_location;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.example.p010_location.adapter.LocListenerAdapter;
import com.example.p010_location.bean.LocationBean;
import com.example.p010_location.listener.LocListener;
import com.example.p010_location.util.LocUtil;
import com.example.shining.libutils.utilslib.base.CheckPermissionsActivity;

public class LocActivity extends CheckPermissionsActivity {

    private TextView tv1;
    private MapView mapView;
    private LocationBean bean = new LocationBean();
    private MyLocationStyle myLocationStyle;
    private Marker marker;
    private AMap aMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc);
        findview(savedInstanceState);
        onclick();
        donetwork();

    }

    private void donetwork() {
        LocUtil.getLocation(LocActivity.this, new LocListener() {

            @Override
            public void success(LocationBean model) {
                bean = model;
                setPos(model.getLatitude(),model.getLongitude(),model.getAddress());

            }

            @Override
            public void fail(int msg) {

            }
        });
    }

    private void onclick() {
        findViewById(R.id.tv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (marker!=null){
                    marker.destroy();
                    marker=null;
                }
                //定位
                LocUtil.getLocation(LocActivity.this, new LocListenerAdapter() {
                    @Override
                    public void success(LocationBean model) {
                        bean = model;
                        setPos(model.getLatitude(),model.getLongitude(),model.getAddress());
                    }
                });
            }
        });
        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
//                CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(location.getLatitude(),location.getLongitude()),18,30,0));
//                tv1.setText(location.getLatitude()+"     "+location.getLongitude());
            }
        });
        aMap.setOnMapClickListener(new AMap.OnMapClickListener() {
            private RegeocodeAddress model;
            @Override
            public void onMapClick(LatLng latLng) {
                //根据经纬度获取model
                GeocodeSearch geocodeSearch = new GeocodeSearch(LocActivity.this);
                geocodeSearch.setOnGeocodeSearchListener(geocodeSearchListener);
                RegeocodeQuery regeocodeQuery=new RegeocodeQuery(new LatLonPoint(latLng.latitude,latLng.longitude),1000,GeocodeSearch.AMAP);
                geocodeSearch.getFromLocationAsyn(regeocodeQuery);
//                Animation animation = new RotateAnimation(marker.getRotateAngle(),marker.getRotateAngle()+180,0,0,0);
//                long duration = 1000L;
//                animation.setDuration(duration);
//                animation.setInterpolator(new LinearInterpolator());
//
//                marker.setAnimation(animation);
//                marker.startAnimation();

            }
        });

    }

    private void findview(Bundle savedInstanceState) {
        tv1 = findViewById(R.id.tv1);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        aMap = mapView.getMap();
        //设置希望展示的地图缩放级别
        CameraUpdate mCameraUpdate = CameraUpdateFactory.zoomTo(16);
        aMap.moveCamera(mCameraUpdate);
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(5000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));// 设置圆形的填充颜色
        myLocationStyle.showMyLocation(true);
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);//LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER
//        myLocationStyle.myLocationIcon();
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(false);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
    }

    private GeocodeSearch.OnGeocodeSearchListener geocodeSearchListener = new GeocodeSearch.OnGeocodeSearchListener() {
        @Override
        public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
            double a = regeocodeResult.getRegeocodeQuery().getPoint().getLatitude();
            double b = regeocodeResult.getRegeocodeQuery().getPoint().getLongitude();
            String c = regeocodeResult.getRegeocodeAddress().getFormatAddress();
            //绘制单个marker
            if (marker!=null){
                marker.destroy();
                marker=null;
            }
            marker = aMap.addMarker(new MarkerOptions()
                    .position(new LatLng(a,b))
                    .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                            .decodeResource(getResources(), R.drawable.marker)))
                    .title("定位：")
                    .snippet(c)
                    .draggable(true));
            marker.showInfoWindow();

            setPos(a+"",b+"",c);
        }

        @Override
        public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

        }
    };

    private void setPos(String la,String lo,String add){
        tv1.setText(la+"     "+lo+"     "+add);
        //参数依次是：视角调整区域的中心点坐标、希望调整到的缩放级别、俯仰角0°~45°（垂直与地图时为0）、偏航角 0~360° (正北方为0)
//        CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(Double.valueOf(la),Double.valueOf(lo)),18,30,0));
//        aMap.moveCamera(mCameraUpdate);

        //带动画
        changeCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(Double.valueOf(la),Double.valueOf(lo)),18,30,0)), null);
//        aMap.clear();
//        aMap.addMarker(new MarkerOptions().position(new LatLng(Double.valueOf(la),Double.valueOf(lo)))
//                .icon(BitmapDescriptorFactory
//                        .defaultMarker(BitmapDescriptorFactory.HUE_RED)));
    }


    /**
     * 根据动画按钮状态，调用函数animateCamera或moveCamera来改变可视区域
     */
    private void changeCamera(CameraUpdate update, AMap.CancelableCallback callback) {
//        boolean animated = ((CompoundButton) findViewById(R.id.animate))
//                .isChecked();
        if (true) {
            aMap.animateCamera(update, 1000, callback);
        } else {
            aMap.moveCamera(update);
        }
    }


    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

}
