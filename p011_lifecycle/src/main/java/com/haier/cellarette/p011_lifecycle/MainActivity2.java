package com.haier.cellarette.p011_lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.haier.cellarette.p011_lifecycle.observer2.InterfaceLifeCycleObserver;
import com.haier.cellarette.p011_lifecycle.observer2.LifeCycleObserver2;

/**
 * LifecycleOwner
 * 生命周期事件分发者。例如我们最熟悉的Activity/Fragment。它们在生命周期发生变化时发出相应的Event给LifecycleRegistry。
 * LifecycleObserver
 * 生命周期监听者。通过注解将处理函数与希望监听的Event绑定,当相应的Event发生时,LifecycleRegistry会通知相应的函数进行处理。
 * LifecycleRegistry
 * 控制中心。它负责控制state的转换、接受分发event事件。其实个人觉得Lifecycle组件与EventBus很类似？ 但以下代码表现了他们的不同:
 *
 */
public class MainActivity2 extends AppCompatActivity implements LifecycleOwner{

    private InterfaceLifeCycleObserver mObserver;
//    private LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);
//    @Override
//    public Lifecycle getLifecycle() {
//        return mLifecycleRegistry;
//    }
//
//    public interface LifecycleOwner {
//        @NonNull
//        Lifecycle getLifecycle();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mObserver = new LifeCycleObserver2();
        getLifecycle().addObserver(mObserver);

    }

    private void login(){
        if (mObserver!=null){
            mObserver.in_resume(this);

        }
    }

    private void loginout(){
        if (mObserver!=null){
            mObserver.in_destory(this);
        }
    }

    @Override
    protected void onDestroy() {
        Log.d("tag", "onDestroy" + this.getClass().toString());
        super.onDestroy();
    }
}
