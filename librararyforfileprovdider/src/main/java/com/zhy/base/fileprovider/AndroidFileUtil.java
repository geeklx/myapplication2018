package com.zhy.base.fileprovider;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;

import java.io.File;
import java.util.List;

/**
 * Created by shining on 2018/3/1.
 */

public class AndroidFileUtil {

    public static Intent openFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) return null;
        /* 取得扩展名 */
        String end = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length()).toLowerCase();
        /* 依扩展名的类型决定MimeType */
        if (end.equals("m4a") || end.equals("mp3") || end.equals("mid") ||
                end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {
            return getAudioFileIntent(filePath);
        } else if (end.equals("3gp") || end.equals("mp4")) {
            return getAudioFileIntent(filePath);
        } else if (end.equals("jpg") || end.equals("gif") || end.equals("png") ||
                end.equals("jpeg") || end.equals("bmp")) {
            return getImageFileIntent(filePath);
        } else if (end.equals("apk")) {
            return getApkFileIntent(filePath);
        } else if (end.equals("ppt")) {
            return getPptFileIntent(filePath);
        } else if (end.equals("xls")) {
            return getExcelFileIntent(filePath);
        } else if (end.equals("doc")) {
            return getWordFileIntent(filePath);
        } else if (end.equals("pdf")) {
            return getPdfFileIntent(filePath);
        } else if (end.equals("chm")) {
            return getChmFileIntent(filePath);
        } else if (end.equals("txt")) {
            return getTextFileIntent(filePath, false);
        } else {
            return getAllIntent(filePath);
        }
    }

    public static Uri getUriForFile(File file) {
        Uri fileUri = null;
        if (Build.VERSION.SDK_INT >= 24) {
            fileUri = getUriForFile24(file);
        } else {
            fileUri = Uri.fromFile(file);
        }
        return fileUri;
    }

    public static Uri getUriForFile24(File file) {
        Uri fileUri = android.support.v4.content.FileProvider.getUriForFile(Appuri.get(),
                Appuri.get().getPackageName() + ".android7.fileprovider",
                file);
        return fileUri;
    }

    public static Intent setIntentDataAndType2(Intent intent,String type,File file,boolean writeAble) {
        if (Build.VERSION.SDK_INT >= 24) {
            intent.setDataAndType(getUriForFile(file), type);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            if (writeAble) {
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
        } else {
            intent.setDataAndType(Uri.fromFile(file), type);
        }
        return intent;
    }

    public static void setIntentDataAndType(Intent intent,String type,File file,boolean writeAble) {
        if (Build.VERSION.SDK_INT >= 24) {
            intent.setDataAndType(getUriForFile(file), type);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            if (writeAble) {
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
        } else {
            intent.setDataAndType(Uri.fromFile(file), type);
        }
    }

    public static void setIntentData(Intent intent,File file,boolean writeAble) {
        if (Build.VERSION.SDK_INT >= 24) {
            intent.setData(getUriForFile(file));
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            if (writeAble) {
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
        } else {
            intent.setData(Uri.fromFile(file));
        }
    }

    public static void grantPermissions(Context context, Intent intent, Uri uri, boolean writeAble) {

        int flag = Intent.FLAG_GRANT_READ_URI_PERMISSION;
        if (writeAble) {
            flag |= Intent.FLAG_GRANT_WRITE_URI_PERMISSION;
        }
        intent.addFlags(flag);
        List<ResolveInfo> resInfoList = context.getPackageManager()
                .queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo resolveInfo : resInfoList) {
            String packageName = resolveInfo.activityInfo.packageName;
            context.grantUriPermission(packageName, uri, flag);
        }
    }

    //Android获取一个用于打开APK文件的intent
    public static Intent getAllIntent(String param) {

        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
//        Uri uri = Uri.fromFile(new File(param));
//        intent.setDataAndType(getUriForFile(new File(param)), "*/*");
        setIntentDataAndType(intent,"*/*",new File(param),true);
        return intent;
    }

    //Android获取一个用于打开APK文件的intent
    public static Intent getApkFileIntent(String param) {

        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
//        Uri uri = Uri.fromFile(new File(param));
//        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        setIntentDataAndType(intent,"application/vnd.android.package-archive",new File(param),true);
        return intent;
    }

    //Android获取一个用于打开VIDEO文件的intent
    public static Intent getVideoFileIntent(String param) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
//        Uri uri = Uri.fromFile(new File(param));
//        intent.setDataAndType(uri, "video/*");
        setIntentDataAndType(intent,"video/*",new File(param),true);
        return intent;
    }

    //Android获取一个用于打开AUDIO文件的intent
    public static Intent getAudioFileIntent(String param) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
//        Uri uri = Uri.fromFile(new File(param));
//        intent.setDataAndType(uri, "audio/*");
        setIntentDataAndType(intent,"audio/*",new File(param),true);
        return intent;
    }

    //Android获取一个用于打开图片文件的intent
    public static Intent getImageFileIntent(String param) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        Uri uri = Uri.fromFile(new File(param));
//        intent.setDataAndType(uri, "image/*");
        setIntentDataAndType(intent,"image/*",new File(param),true);
        return intent;
    }

    //Android获取一个用于打开PPT文件的intent
    public static Intent getPptFileIntent(String param) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        Uri uri = Uri.fromFile(new File(param));
//        intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
        setIntentDataAndType(intent,"application/vnd.ms-powerpoint",new File(param),true);
        return intent;
    }

    //Android获取一个用于打开Excel文件的intent
    public static Intent getExcelFileIntent(String param) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        Uri uri = Uri.fromFile(new File(param));
//        intent.setDataAndType(uri, "application/vnd.ms-excel");
        setIntentDataAndType(intent,"application/vnd.ms-excel",new File(param),true);
        return intent;
    }

    //Android获取一个用于打开Word文件的intent
    public static Intent getWordFileIntent(String param) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        Uri uri = Uri.fromFile(new File(param));
//        intent.setDataAndType(uri, "application/msword");
        setIntentDataAndType(intent,"application/msword",new File(param),true);
        return intent;
    }

    //Android获取一个用于打开CHM文件的intent
    public static Intent getChmFileIntent(String param) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        Uri uri = Uri.fromFile(new File(param));
//        intent.setDataAndType(uri, "application/x-chm");
        setIntentDataAndType(intent,"application/x-chm",new File(param),true);
        return intent;
    }

    //Android获取一个用于打开PDF文件的intent
    public static Intent getPdfFileIntent(String param) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        Uri uri = Uri.fromFile(new File(param));
//        intent.setDataAndType(uri, "application/pdf");
        setIntentDataAndType(intent,"application/pdf",new File(param),true);
        return intent;
    }

    //Android获取一个用于打开文本文件的intent
    public static Intent getTextFileIntent(String param, boolean paramBoolean) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = null;
        if (paramBoolean) {
             uri = Uri.parse(param);
        } else {
            if (Build.VERSION.SDK_INT >= 24) {
                uri = getUriForFile24(new File(param));
            } else {
                uri = Uri.fromFile(new File(param));
            }
        }
        intent.setDataAndType(uri, "text/plain");
        if (Build.VERSION.SDK_INT >= 24) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } else {
        }
        return intent;
    }

    //Android获取一个用于打开Html文件的intent
    public static Intent getHtmlFileIntent(String param) {

        Uri uri = Uri.parse(param).buildUpon().encodedAuthority("com.android.htmlfileprovider").scheme("content").encodedPath(param).build();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(uri, "text/html");

        return intent;
    }
}
