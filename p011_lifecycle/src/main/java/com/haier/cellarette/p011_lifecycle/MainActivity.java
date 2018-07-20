package com.haier.cellarette.p011_lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.shining.libutils.utilslib.app.MyLogUtil;
import com.haier.cellarette.p011_lifecycle.activity.BaseActivity;
import com.haier.cellarette.p011_lifecycle.utils.ConstantsUtil;
import com.haier.cellarette.p011_lifecycle.utils.LoginUtil;

/**
 * LifecycleOwner
 * 生命周期事件分发者。例如我们最熟悉的Activity/Fragment。它们在生命周期发生变化时发出相应的Event给LifecycleRegistry。
 * LifecycleObserver
 * 生命周期监听者。通过注解将处理函数与希望监听的Event绑定,当相应的Event发生时,LifecycleRegistry会通知相应的函数进行处理。
 * LifecycleRegistry
 * 控制中心。它负责控制state的转换、接受分发event事件。其实个人觉得Lifecycle组件与EventBus很类似？ 但以下代码表现了他们的不同:
 */
public class MainActivity extends BaseActivity {

    private TextView tv1;
    private TextView tv2;
    private TextView tv22;
    private TextView tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv22 = findViewById(R.id.tv22);
        tv3 = findViewById(R.id.tv3);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUtil.get().loginToDo(MainActivity.this, new Runnable() {
                    @Override
                    public void run() {
                        //登录成功 更新UI或者跳转
                        startActivity(new Intent(MainActivity.this, MainActivity3.class));
                    }
                });
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUtil.get().loginToDo(MainActivity.this, new Runnable() {
                    @Override
                    public void run() {
                        //登录成功 更新UI或者跳转
                        tv3.setText("登录成功~");
                    }
                });
            }
        });
        tv22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginUtil.isUserLogin()) {
                    LoginUtil.get().loginOutToDo(MainActivity.this);
                }
            }
        });
    }

    @Override
    protected void onActivityResult2(int requestCode, int resultCode, Intent data) {
        super.onActivityResult2(requestCode, resultCode, data);
        if (requestCode == ConstantsUtil.LOGINOUT_REQUEST_CODE) {
            if (resultCode == ConstantsUtil.LOGINOUT_RESULT_OK ) {
                //退出成功 更新UI或者跳转
                tv3.setText("退出成功~");
            }
        }
    }

    @Override
    protected void onUserLogined(String userId) {
        super.onUserLogined(userId);
        MyLogUtil.e("------geek---------" + userId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
