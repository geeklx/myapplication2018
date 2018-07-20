package com.haier.cellarette.p011_lifecycle.utils;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.example.shining.libutils.utilslib.app.App;
import com.example.shining.libutils.utilslib.data.SpUtils;

public class LoginUtil {

    private static LoginUtil sInstance;
    private static final Object lock = new Object();
    private Runnable mLastRunnable;

    public LoginUtil() {

    }

    public static LoginUtil get() {
        if (sInstance == null) {
            synchronized (lock) {
                sInstance = new LoginUtil();
            }
        }
        return sInstance;
    }

    /**
     * 用户uid
     *
     * @return
     */
    public String userId() {
        //设置userid 返回userid
        return SpUtils.getInstance(App.get()).get(ConstantsUtil.user_id, "").toString();
    }

    /**
     * 用户是否登录bufen
     *
     * @return
     */
    public static boolean isUserLogin() {
        if (!TextUtils.isEmpty(SpUtils.getInstance(App.get()).get(ConstantsUtil.user_id, "").toString())) {
            return true;
        } else {
            return false;
        }
    }

    public void loginToDo(Activity activity, Runnable runnable) {
        if (isUserLogin()) {
            if (runnable != null) {
                runnable.run();
            }
        }else {
            mLastRunnable = runnable;
            login(activity);
        }
    }

    public void login(Activity activity) {
        Intent intent = new Intent("hs.act.loginactivitys");
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivityForResult(intent, ConstantsUtil.LOGIN_REQUEST_CODE);
        }
    }

    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        Runnable runnable = mLastRunnable;
        mLastRunnable = null;
        if (requestCode == ConstantsUtil.LOGIN_REQUEST_CODE) {
            if (resultCode == ConstantsUtil.LOGIN_RESULT_OK && runnable != null) {
                runnable.run();
            }
            return true;
        }
        return false;
    }

    public void loginOutToDo(Activity activity) {
        Intent intent = new Intent("hs.act.loginoutactivitys");
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivityForResult(intent, ConstantsUtil.LOGINOUT_REQUEST_CODE);
        }
    }

}
