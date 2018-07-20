package com.example.p013_updatemanager;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.example.p013_updatemanager.download.ApkIntentServiceActivity;
import com.example.p013_updatemanager.download.HandlerThreadActivity;
import com.example.p013_updatemanager.download.ImgIntentServiceActivity;
import com.example.p013_updatemanager.helper.Config;
import com.example.p013_updatemanager.helper.DialogHelper;
import com.example.p013_updatemanager.helper.OnReleasedListener;
import com.example.p013_updatemanager.helper.PermissionHelper;
import com.example.p013_updatemanager.helper.ReleaseInstallApkTask;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvAboutPermission;
    private String permissions;

    @Override
    protected void onResume() {
        super.onResume();
        updateAboutPermission();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.init(this);

        tvAboutPermission = findViewById(R.id.tvAboutPermission);
        setting_text();
        findViewById(R.id.tv00).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HandlerThreadActivity.class));
            }
        });
        findViewById(R.id.tv0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ImgIntentServiceActivity.class));
            }
        });
        findViewById(R.id.tv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ApkIntentServiceActivity.class));
            }
        });
        findViewById(R.id.tv11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //本地安装
                if (AppUtils.isAppInstalled(Config.LOCAL_PKG)) {
                    ToastUtils.showShort("此应用已安装");
                } else {
                    PermissionHelper.requestStorage(new PermissionHelper.OnPermissionGrantedListener() {
                        @Override
                        public void onPermissionGranted() {
                            if (!FileUtils.isFileExists(Config.LOCAL_APK_PATH)) {
                                new ReleaseInstallApkTask(listener, Config.LOCAL_APK_PATH).execute();
                            } else {
                                listener.onReleased(Config.LOCAL_APK_PATH);
                                LogUtils.d("test apk existed.");
                            }
                        }
                    });
                }
            }
        });
        findViewById(R.id.tv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //静默下载
//                if (AppUtils.isAppInstalled(Config.TEST_PKG)) {
//                    ToastUtils.showShort("正在下载...");
//                } else {
//                    if (AppUtils.installAppSilent(Config.TEST_APK_PATH)) {
//                        ToastUtils.showShort("安装成功");
//                    } else {
//                        ToastUtils.showShort("安装失败");
//                    }
//                }
                // 卸载应用
                if (AppUtils.isAppInstalled(Config.TEMP_PKG)) {
                    AppUtils.uninstallApp(Config.TEMP_PKG);
                } else {
                    ToastUtils.showShort("请先安装应用");
                }
            }
        });
        findViewById(R.id.tv3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogHelper.showOpenAppSettingDialog();
            }
        });
        findViewById(R.id.tv4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionUtils.permission(PermissionConstants.CALENDAR)
                        .rationale(new PermissionUtils.OnRationaleListener() {
                            @Override
                            public void rationale(final ShouldRequest shouldRequest) {
                                DialogHelper.showRationaleDialog(shouldRequest);
                            }
                        })
                        .callback(new PermissionUtils.FullCallback() {
                            @Override
                            public void onGranted(List<String> permissionsGranted) {
                                updateAboutPermission();
                                LogUtils.d(permissionsGranted);
                                //跳转逻辑
                                ToastUtils.showLong("已经授权，进行业务逻辑操作跳转");
                            }

                            @Override
                            public void onDenied(List<String> permissionsDeniedForever,
                                                 List<String> permissionsDenied) {
                                if (!permissionsDeniedForever.isEmpty()) {
                                    DialogHelper.showOpenAppSettingDialog();
                                }
                                LogUtils.d(permissionsDeniedForever, permissionsDenied);
                            }
                        })
                        .theme(new PermissionUtils.ThemeCallback() {
                            @Override
                            public void onActivityCreate(Activity activity) {
                                ScreenUtils.setFullScreen(activity);
                            }
                        })
                        .request();
            }
        });
        findViewById(R.id.tv5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionUtils.permission(PermissionConstants.MICROPHONE)
                        .rationale(new PermissionUtils.OnRationaleListener() {
                            @Override
                            public void rationale(final ShouldRequest shouldRequest) {
                                DialogHelper.showRationaleDialog(shouldRequest);
                            }
                        })
                        .callback(new PermissionUtils.FullCallback() {
                            @Override
                            public void onGranted(List<String> permissionsGranted) {
                                updateAboutPermission();
                                LogUtils.d(permissionsGranted);
                                //跳转逻辑
                                ToastUtils.showLong("已经授权，进行业务逻辑操作跳转");
                            }

                            @Override
                            public void onDenied(List<String> permissionsDeniedForever,
                                                 List<String> permissionsDenied) {
                                if (!permissionsDeniedForever.isEmpty()) {
                                    DialogHelper.showOpenAppSettingDialog();
                                }
                                LogUtils.d(permissionsDeniedForever, permissionsDenied);
                            }
                        })
                        .request();
            }
        });
    }

    private final OnReleasedListener listener = new OnReleasedListener() {

        @Override
        public void onReleased(String local_temp_path) {
            AppUtils.installApp(local_temp_path);
        }
    };

    private void setting_text() {
        StringBuilder sb = new StringBuilder();
        for (String s : PermissionUtils.getPermissions()) {
            sb.append(s.substring(s.lastIndexOf('.') + 1)).append("\n");
        }
        permissions = sb.toString();
    }

    private void updateAboutPermission() {
        tvAboutPermission.setText(new SpanUtils()
                .append(permissions).setBold()
                .appendLine("读取日历: " + PermissionUtils.isGranted(Manifest.permission.READ_CALENDAR))
                .appendLine("读取语音: " + PermissionUtils.isGranted(Manifest.permission.RECORD_AUDIO))
                .create());
    }


}
