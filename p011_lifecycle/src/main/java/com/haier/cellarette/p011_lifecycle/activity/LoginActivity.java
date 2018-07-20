package com.haier.cellarette.p011_lifecycle.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.example.shining.libutils.utilslib.app.App;
import com.example.shining.libutils.utilslib.data.SpUtils;
import com.example.shining.libutils.utilslib.etc.ToastUtil;
import com.haier.cellarette.p011_lifecycle.R;
import com.haier.cellarette.p011_lifecycle.observer2.InterfaceLifeCycleObserver;
import com.haier.cellarette.p011_lifecycle.utils.ConstantsUtil;


public class LoginActivity extends AppCompatActivity  {

    private Button btn_cancel;
    private Button btn_ok;

    private InterfaceLifeCycleObserver mObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findview();
        onclick();
        donetwork();

    }

    private void donetwork() {


    }

    private void onclick() {
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginCanceled();
            }
        });
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //请求服务器bufen
                SpUtils.getInstance(App.get()).put(ConstantsUtil.user_id,"0000000001");
                onLoginSuccess();
            }
        });
    }

    private void onLoginSuccess() {
        setResult(ConstantsUtil.LOGIN_RESULT_OK);
        finish();
    }

    private void onLoginCanceled() {
        setResult(ConstantsUtil.LOGIN_RESULT_CANCELED);
        finish();
    }

    private void findview() {
        btn_ok = findViewById(R.id.btn_ok);
        btn_cancel = findViewById(R.id.btn_cancel);
    }

    private boolean isPhone(String phone) {
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showToastShort("请输入您的手机号码！");
            return false;
        }

        if (!TextUtils.isDigitsOnly(phone)) {
            ToastUtil.showToastShort("手机号格式错误，仅支持纯数字！");
            return false;
        }

        if (phone.length() != 11) {
            ToastUtil.showToastShort("手机号格式错误，应为11位纯数字！");
            return false;
        }

        return true;
    }
}
