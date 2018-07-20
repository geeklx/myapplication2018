package com.example.p001_fitandroid678.fileandroid;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.RequiresApi;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.Utils;
import com.zhy.base.fileprovider.FileProvider7;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FitAndroidAssetsToCacheUtil {

    public static final String FILE_SEP = System.getProperty("file.separator");
    /**
     * 需要进行检测的权限数组
     */
    public static String[] needPermissions = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.REQUEST_INSTALL_PACKAGES,
            Manifest.permission.CAMERA
    };

    public static final int REQUEST_CODE_INSTALL_APK = 10086;
    public static final int REQUEST_CODE_TAKE_PHOTO = 0x110;

    public static final int REQ_PERMISSION_CODE_SDCARD = 0X111;
    public static final int REQ_PERMISSION_CODE_TAKE_PHOTO = 0X112;


    public static String get_file_url() {
        String file_apk_url;
        File file_apks = Utils.getApp().getExternalCacheDir();
        if (file_apks != null) {
            file_apk_url = file_apks.getAbsolutePath();
        } else {
            file_apk_url = Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return file_apk_url;
    }

    /**
     * 把assets/XX路径下的文件复制到cache中 获取返回file路径
     *
     * @param file_name
     * @param file_assets_lujing
     * @return
     */
    public static File getAssetsHuanCunLujing(String file_name, String file_assets_lujing) {
        readAssertResource(file_name, file_assets_lujing);// 刷新缓存区
        File file_url = new File(get_file_url() + FILE_SEP + file_assets_lujing, file_name);
        return file_url;
    }

    /**
     * 获取assets根路径bufen
     *
     * @param strAssertFileName
     */
    public static void readAssertResource(String strAssertFileName) {
        File apk = new File(get_file_url(), strAssertFileName);
        if (apk.exists()) {
            return;
        }
        AssetManager assetManager = Utils.getApp().getAssets();
        try {
            InputStream ims = assetManager.open(strAssertFileName);
            FileOutputStream fos = new FileOutputStream(new File(get_file_url(), strAssertFileName));
            byte[] buffer = new byte[1024];
            int byteCount = 0;
            while ((byteCount = ims.read(buffer)) != -1) {//循环从输入流读取 buffer字节
                fos.write(buffer, 0, byteCount);//将读取的输入流写入到输出流
            }
            fos.flush();//刷新缓冲区
            ims.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取assets下级路径文件 复制到缓存区  readAssertResource("demo.mp3", "jsonbean/")
     *
     * @param strAssertFileName
     * @param assets_url
     */
    public static void readAssertResource(String strAssertFileName, String assets_url) {
        String all_assets_path = get_file_url() + FILE_SEP + assets_url;
        FileUtils.createOrExistsFile(all_assets_path + strAssertFileName);
        AssetManager assetManager = Utils.getApp().getAssets();
        try {
            InputStream ims = assetManager.open(assets_url + strAssertFileName);
            FileOutputStream fos = new FileOutputStream(new File(all_assets_path, strAssertFileName));
            byte[] buffer = new byte[1024];
            int byteCount = 0;
            while ((byteCount = ims.read(buffer)) != -1) {//循环从输入流读取 buffer字节
                fos.write(buffer, 0, byteCount);//将读取的输入流写入到输出流
            }
            fos.flush();//刷新缓冲区
            ims.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 弹窗 安装应用需要打开未知来源权限
     *
     * @param activity
     * @param file
     */
    public static void open_anquan(final Activity activity, File file) {
//        String apkPath = getExternalCacheDir().getAbsolutePath();
//        File apk = new File(apkPath, "apk.apk");
        if (!file.exists()) {
            return;
        }
        boolean haveInstallPermission;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //先获取是否有安装未知来源应用的权限
            haveInstallPermission = activity.getPackageManager().canRequestPackageInstalls();
            if (!haveInstallPermission) {//没有权限
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("安装应用需要打开未知来源权限，请去设置中开启权限");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            startInstallPermissionSettingActivity(activity);
                        }
                    }
                });
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();
                return;
            }
        }
        //有权限，开始安装应用程序
        installApk(activity, file);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void startInstallPermissionSettingActivity(Activity activity) {
        Uri packageURI = Uri.parse("package:" + Utils.getApp().getPackageName());
        //注意这个是8.0新API
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
        activity.startActivityForResult(intent, REQUEST_CODE_INSTALL_APK);
    }

    /**
     * android 8.0 权限拍照bufen
     *
     * @param file
     */
    public static void takePhotoNoCompress(Activity activity, File file) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(Utils.getApp().getPackageManager()) != null) {
            Uri fileUri = FileProvider7.getUriForFile(Utils.getApp(), file);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            activity.startActivityForResult(takePictureIntent, REQUEST_CODE_TAKE_PHOTO);
        }
    }

    /**
     * android 8.0 权限寻找路径安装文件bufen
     *
     * @param file
     */
    public static void installApk(Activity activity, File file) {
        // 需要自己修改安装包路径
//        File file = new File(Environment.getExternalStorageDirectory(), "app-debug.apk");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        FileProvider7.setIntentDataAndType(Utils.getApp(), intent, "application/vnd.android.package-archive", file, true);
        activity.startActivity(intent);
    }




}
