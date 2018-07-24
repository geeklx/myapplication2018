package com.example.p001_fitandroid_touxiang_upload.view;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.blankj.utilcode.util.Utils;

import java.io.File;

/**
 * 文件工具类
 */
public class GetFileUtil {

//    public String file_img_name;
    public File file_img;
    public static final String file_img_url = get_file_url();
    public static final String file_name_geshi = ".jpg";
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
     * 根据Uri返回文件绝对路径
     * 兼容了file:///开头的 和 content://开头的情况
     */
    public static String getRealFilePathFromUri(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_FILE.equalsIgnoreCase(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equalsIgnoreCase(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /**
     * 检查文件是否存在
     */
    public static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }
}
