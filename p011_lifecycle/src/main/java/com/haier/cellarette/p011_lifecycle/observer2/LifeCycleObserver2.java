package com.haier.cellarette.p011_lifecycle.observer2;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.util.Log;

public class LifeCycleObserver2 implements InterfaceLifeCycleObserver {

    private static final String TAG = LifeCycleObserver2.class.getClass().getName();

    @Override
    public void in_resume(@NonNull LifecycleOwner owner) {
        Log.d(TAG, "LifeCycleObserver2.in_resume" + this.getClass().toString());


    }

    @Override
    public void in_destory(@NonNull LifecycleOwner owner) {
        Log.d(TAG, "LifeCycleObserver2.in_destory" + this.getClass().toString());

    }

    @Override
    public void in_change(@NonNull LifecycleOwner owner, @NonNull Lifecycle.Event event) {
        Log.d(TAG, "LifeCycleObserver2.in_change" + this.getClass().toString());

    }
}
