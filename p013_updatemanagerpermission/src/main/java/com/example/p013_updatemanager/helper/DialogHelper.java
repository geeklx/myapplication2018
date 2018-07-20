package com.example.p013_updatemanager.helper;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.PermissionUtils.OnRationaleListener.ShouldRequest;
import com.example.p013_updatemanager.R;

public class DialogHelper {

    public static void showRationaleDialog(final ShouldRequest shouldRequest) {
        Activity topActivity = ActivityUtils.getTopActivity();
        if (topActivity == null) return;
        new AlertDialog.Builder(topActivity)
                .setTitle(android.R.string.dialog_alert_title)
                .setMessage("安装应用需要打开未知来源权限" + "\n\n" + "请点击|" + "设置|" + "更多设置|" + "系统安全|" + "安装未知应用|" + "-允许酒知道安装")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        shouldRequest.again(true);
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        shouldRequest.again(false);
                    }
                })
                .setCancelable(false)
                .create()
                .show();

    }

    public static void showOpenAppSettingDialog() {
        Activity topActivity = ActivityUtils.getTopActivity();
        if (topActivity == null) return;
        new AlertDialog.Builder(topActivity)
                .setTitle(android.R.string.dialog_alert_title)
                .setMessage("当前应用缺少必要权限" + "\n\n" + "请点击|" + "设置|" + "权限" + "-打开所需权限")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PermissionUtils.launchAppDetailsSettings();
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }

    public static void showKeyboardDialog() {
        Activity topActivity = ActivityUtils.getTopActivity();
        if (topActivity == null) return;
        final View dialogView = LayoutInflater.from(topActivity).inflate(R.layout.dialog_keyboard, null);
        final EditText etInput = dialogView.findViewById(R.id.et_input);
        final AlertDialog dialog = new AlertDialog.Builder(topActivity).setView(dialogView).create();
        dialog.setCanceledOnTouchOutside(false);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_hide_soft_input:
                        KeyboardUtils.hideSoftInput(etInput);
                        break;
                    case R.id.btn_show_soft_input:
                        KeyboardUtils.showSoftInput(etInput);
                        break;
                    case R.id.btn_toggle_soft_input:
                        KeyboardUtils.toggleSoftInput();
                        break;
                    case R.id.btn_close_dialog:
                        KeyboardUtils.hideSoftInput(etInput);
                        dialog.dismiss();
                        break;
                }
            }
        };
        dialogView.findViewById(R.id.btn_hide_soft_input).setOnClickListener(listener);
        dialogView.findViewById(R.id.btn_show_soft_input).setOnClickListener(listener);
        dialogView.findViewById(R.id.btn_toggle_soft_input).setOnClickListener(listener);
        dialogView.findViewById(R.id.btn_close_dialog).setOnClickListener(listener);
        dialog.show();
    }
}
