package com.haier.cellarette.p011_lifecycle.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.haier.cellarette.p011_lifecycle.utils.ConstantsUtil;
import com.haier.cellarette.p011_lifecycle.utils.LoginUtil;


public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (!LoginUtil.get().onActivityResult(requestCode, resultCode, data)) {
            onActivityResult2(requestCode, resultCode, data);
        }else {
            if (LoginUtil.get().isUserLogin()) {
                onUserLogined(LoginUtil.get().userId());
            } else {
                //这种情况不会存在bufen
                onUserLoginCanceled();
            }
        }

    }

    protected void onUserLogined(String userId) {}

    protected void onUserLoginCanceled() {}

    protected void onActivityResult2(int requestCode, int resultCode, Intent data) {
    }
}
