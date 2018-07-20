package com.haier.cellarette.p011_lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

import com.haier.cellarette.p011_lifecycle.observer2.LifeCycleObserver2;

public class LifeCycleObserver implements LifecycleObserver {

    private static final String TAG = LifeCycleObserver2.class.getClass().getName();

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void resume(){
        Log.d(TAG, "LifeCycleObserver.resume" + this.getClass().toString());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void change(){
        Log.d(TAG, "LifeCycleObserver.change" + this.getClass().toString());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void destory(){
        Log.d(TAG, "LifeCycleObserver.destory" + this.getClass().toString());
    }

}
