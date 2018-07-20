package com.haier.cellarette.p011_lifecycle.observer2;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.NonNull;

public interface InterfaceLifeCycleObserver extends LifecycleObserver{
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void in_resume(@NonNull LifecycleOwner owner);

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void in_destory(@NonNull LifecycleOwner owner);

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    void in_change(@NonNull LifecycleOwner owner,@NonNull Lifecycle.Event event);

}
