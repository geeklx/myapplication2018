package com.example.p013_updatemanager.helper;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import java.io.File;

public final class FileApkUtil {
    private static final String LINE_SEP = System.getProperty("line.separator");

    private FileApkUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 获取指定路径下的apk的信息
     *
     * @param context
     * @param lujing
     * @return
     */
    public static ApplicationInfo getApkInfo(Context context, String lujing) {
        ApplicationInfo appInfo = null;
        PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageArchiveInfo(lujing, PackageManager.GET_ACTIVITIES);
        if (info != null) {
            appInfo = info.applicationInfo;
            String appName = pm.getApplicationLabel(appInfo).toString();
            String packageName = appInfo.packageName;  //得到安装包名称
            String version = info.versionName;       //得到版本信息
            Drawable icon = pm.getApplicationIcon(appInfo);//得到图标信息
        }
        return appInfo;
    }

    /**
     * Return the file by path.
     *
     * @param filePath The path of file.
     * @return the file
     */
    public static File getFileByPath(final String filePath) {
        return isSpace(filePath) ? null : new File(filePath);
    }

    /**
     * Return whether the file exists.
     *
     * @param filePath The path of file.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isFileExists(final String filePath) {
        return isFileExists(getFileByPath(filePath));
    }

    /**
     * Return whether the file exists.
     *
     * @param file The file.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isFileExists(final File file) {
        return file != null && file.exists();
    }


    private static boolean isSpace(final String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
