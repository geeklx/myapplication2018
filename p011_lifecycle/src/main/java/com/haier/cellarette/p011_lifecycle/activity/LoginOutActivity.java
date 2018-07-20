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

import static com.haier.cellarette.p011_lifecycle.utils.ConstantsUtil.LOGIN_RESULT_CANCELED;
import static com.haier.cellarette.p011_lifecycle.utils.ConstantsUtil.LOGIN_RESULT_OK;
import static com.haier.cellarette.p011_lifecycle.utils.ConstantsUtil.user_id;


public class LoginOutActivity extends AppCompatActivity  {

    private Button btn_cancel;
    private Button btn_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginout);
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
                onLoginOutCanceled();
            }
        });
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //请求服务器bufen
                SpUtils.getInstance(App.get()).put(ConstantsUtil.user_id,"");
                onLoginOutSuccess();
            }
        });
    }

    private void onLoginOutSuccess() {
        setResult(ConstantsUtil.LOGINOUT_RESULT_OK);
        finish();
    }

    private void onLoginOutCanceled() {
        setResult(ConstantsUtil.LOGINOUT_RESULT_CANCELED);
        finish();
    }

    private void findview() {
        btn_ok = findViewById(R.id.btn_ok);
        btn_cancel = findViewById(R.id.btn_cancel);
    }

}
