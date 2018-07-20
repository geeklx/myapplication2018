package com.example.p008_timertask;

import com.example.shining.libutils.utilslib.app.MyLogUtil;

import java.util.Observable;

public class MyObservable extends Observable {

    public void setData(String arg){
        setChanged();
        notifyObservers(arg);
    }

    public void getData(Object obj){
        MyLogUtil.d(""+obj);
    }


}
