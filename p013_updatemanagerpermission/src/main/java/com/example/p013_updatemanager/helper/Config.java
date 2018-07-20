package com.example.p013_updatemanager.helper;

import android.os.Environment;

import com.blankj.utilcode.util.Utils;

import java.io.File;

public class Config {
    public static final String FILE_SEP = System.getProperty("file.separator");
//    public static final String TEST_APK_PATH = "http://p.gdown.baidu.com/e1ab5af129ff62cba3836250ea3447eb08fd6c60f1fd9c963ae7847c8102c98c10a29889b721f49dcb3c945064dd2e9483aed1b7852354134e43779405e8baf93307c409488d014f8b565660f1d0606392ea7dc77c31ff80e8cc78971579434d78d61eac9e1379c3296efa6fe61f1077558a3e7e8db84983959d6ba6167d0b7a10037f1e31675a27c3379d13c6611b348739684a047bea914fb5d8fa68981f747f2c8c76783bdb7ed91752dd5a730c41409e6e530a24872e926a7e6678711e4c50fc22bc8741fc33c1250aa4f4faf971fecb50b13fa99f475555672eff782d2ad027f08cf25e8daac4522a25937b515fd1c39f6feeb5364f4d552b5f059b71c8f0135f840c72a47d57f8bb9db6125545c6a666a7b9e2ec26b8420f96f1a4d67c";
    public static final String TEMP_PKG = "com.baidu.netdisk";// com.baidu.netdisk  com.blankj.testinstall
    public static final String TEMP_APKNAME = "geek_install.apk";// com.baidu.netdisk  com.blankj.testinstall
    public static final String CACHE_PATH;
    public static final String TEMP_APK_PATH;

    public static final String LOCAL_PKG = "com.blankj.testinstall";
    public static final String LOCAL_APKNAME = "test_install.apk";
    public static final String LOCAL_APK_PATH;

    static {
        File cacheDir = Utils.getApp().getExternalCacheDir();
        if (cacheDir != null) {
            CACHE_PATH = cacheDir.getAbsolutePath();
        } else {
            CACHE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        // /storage/emulated/0/Android/data/com.example.p013_updatemanager/cache/test_install.apk
        TEMP_APK_PATH = CACHE_PATH + FILE_SEP + TEMP_APKNAME;
        LOCAL_APK_PATH = CACHE_PATH + FILE_SEP + LOCAL_APKNAME;
    }
}
