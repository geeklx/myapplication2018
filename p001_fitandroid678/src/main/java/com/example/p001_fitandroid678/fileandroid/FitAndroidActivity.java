package com.example.p001_fitandroid678.fileandroid;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p001_fitandroid678.R;
import com.example.p001_fitandroid678.fileandroid.xieru.FileCacheBean;
import com.example.p001_fitandroid678.fileandroid.xieru.FlieCacheBeanManager;
import com.example.p001_fitandroid678.fileandroid.xieru.NoDoubleClickListener;
import com.zhy.base.fileprovider.FileProvider7;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class FitAndroidActivity extends AppCompatActivity {

    private File file_music;

    private TextView tv1;
    private Button btn1;
    private ImageView mIvPhoto;
    private String file_img_name;
    private File file_img;
    private String file_img_url;

    private String file_apk_name;
    private File file_apk;
    private String file_apk_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanxian);
        tv1 = findViewById(R.id.tv1);
        btn1 = findViewById(R.id.btn1);
        btn_del_txt();
        chushihua_hashmap();
        mIvPhoto = findViewById(R.id.id_iv);

        //打开缓存中的音乐bufen  MUSICMP3
        String file_mp3 = "demo.mp3";
        String file_assets_lujing = "mp3/";
        file_music = FitAndroidAssetsToCacheUtil.getAssetsHuanCunLujing(file_mp3, file_assets_lujing);// 刷新缓存区
        // 图片bufen  TAKEPHOTO
//        file_img_name = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.CHINA).format(new Date()) + ".png";
        file_img_name = "geek_img.png";
        file_img_url = FitAndroidAssetsToCacheUtil.get_file_url();
        file_img = new File(file_img_url, file_img_name);
        // 文件bufen  INSTALLAPK
        String file_apk_name = "demoapk.apk";
        String file_apk_assets_lujing = "apks/";
        FitAndroidAssetsToCacheUtil.readAssertResource(file_apk_name, file_apk_assets_lujing);// 刷新缓存区
        file_apk = FitAndroidAssetsToCacheUtil.getAssetsHuanCunLujing(file_apk_name, file_apk_assets_lujing);
    }

    //拍照bufen
    public void TAKEPHOTO(View view) {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, FitAndroidAssetsToCacheUtil.needPermissions, FitAndroidAssetsToCacheUtil.REQ_PERMISSION_CODE_TAKE_PHOTO);
        } else {
            FitAndroidAssetsToCacheUtil.takePhotoNoCompress(this, file_img);

        }
    }

    //打开缓存中的图片 bufen
    public void TAKEPHOTO2(View view) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        FileProvider7.setIntentDataAndType(this, intent, "image/*", file_img, true);
        startActivity(intent);
    }

    // 打开缓存中的MP3
    public void MUSICMP3(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        FileProvider7.setIntentDataAndType(this, intent, "audio/*", file_music, true);
        startActivity(intent);
    }

    //打开缓存中的APK 安装文件bufen
    public void INSTALLAPK(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, FitAndroidAssetsToCacheUtil.needPermissions, FitAndroidAssetsToCacheUtil.REQ_PERMISSION_CODE_SDCARD);
        } else {
            FitAndroidAssetsToCacheUtil.open_anquan(this, file_apk);

        }
    }

    private String TXT_ID = "12";// 以商品的id为txt的名字
    private ArrayList<FileCacheBean> mGoodsList;
    private int id = 100;// 0x100

    private void chushihua_hashmap() {
        mGoodsList = FlieCacheBeanManager.getInstance().getListBean(TXT_ID);
        String aaa = "";
        for (int i = 0; i < mGoodsList.size(); i++) {
            FileCacheBean bean = mGoodsList.get(i);
            aaa += bean.getGoodsId() + "   " + bean.getGoods() + "   " + bean.getCount() + "\n";
        }
        tv1.setText(aaa);
    }

    //删除缓存中的TXT 文件bufen
//    public void DELETETXT(final View view) {
//        FlieCacheBeanManager.getInstance().deletetxt(TXT_ID);
//        mGoodsList = new ArrayList<>();
//        chushihua_hashmap();
//    }

    //删除缓存中的TXT 文件bufen
    public void btn_del_txt() {

        btn1.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onSingleClick(View v) {

            }

            @Override
            protected void onDoubleClick(View v) {
                FlieCacheBeanManager.getInstance().deletetxt(TXT_ID);
                mGoodsList = new ArrayList<>();
                chushihua_hashmap();
            }
        });
    }

    //写入缓存中的TXT 文件bufen
    public void WRITETXT(View view) {
        // 假数据准备写入缓存
        int a = new Random().nextInt(100);
        for (int i = a; i < a + 1; i++) {
            mGoodsList.add(new FileCacheBean(i + 1, "小猪包套餐" + i, id++ + ""));
        }
        // 写入缓存bufen
        FlieCacheBeanManager.getInstance().AddHashMap(TXT_ID, mGoodsList);
        chushihua_hashmap();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 写入缓存bufen
        FlieCacheBeanManager.getInstance().AddHashMap(TXT_ID, mGoodsList);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // 安装文件bufen
        if (requestCode == FitAndroidAssetsToCacheUtil.REQ_PERMISSION_CODE_SDCARD) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                FitAndroidAssetsToCacheUtil.open_anquan(this, file_apk);
            } else {
                // Permission Denied
                Toast.makeText(FitAndroidActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        // 拍照bufen
        if (requestCode == FitAndroidAssetsToCacheUtil.REQ_PERMISSION_CODE_TAKE_PHOTO) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                FitAndroidAssetsToCacheUtil.takePhotoNoCompress(this, file_img);
            } else {
                // Permission Denied
                Toast.makeText(FitAndroidActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }

    }

    /**
     * 安全安装bufen
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 安装文件bufen
        if (requestCode == FitAndroidAssetsToCacheUtil.REQUEST_CODE_INSTALL_APK && resultCode == Activity.RESULT_OK) {
            //业务逻辑bufen
            FitAndroidAssetsToCacheUtil.installApk(this, file_apk);

        }
        // 拍照bufen
        if (resultCode == Activity.RESULT_OK && requestCode == FitAndroidAssetsToCacheUtil.REQUEST_CODE_TAKE_PHOTO) {
            //业务逻辑bufen 缓存中的图片
            mIvPhoto.setImageBitmap(BitmapFactory.decodeFile(file_img_url + System.getProperty("file.separator") + file_img_name));
        }

    }
}
