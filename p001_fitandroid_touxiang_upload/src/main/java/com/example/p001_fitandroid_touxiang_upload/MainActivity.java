package com.example.p001_fitandroid_touxiang_upload;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.p001_fitandroid_touxiang_upload.view.CircleImageView;
import com.example.p001_fitandroid_touxiang_upload.view.GetFileUtil;
import com.zhy.base.fileprovider.FileProvider7;

import java.io.File;


/**
 * 主界面
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;
    //头像1
    private CircleImageView headImage1;
    //头像2
    private ImageView headImage2;
    //调用照相机返回图片文件
    private File tempFile;
    // 1: qq, 2: weixin
    private int type;

    private GetFileUtil getFileUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        headImage1 = (CircleImageView) findViewById(R.id.head_image1);
        headImage2 = (ImageView) findViewById(R.id.head_image2);
        RelativeLayout qqLayout = (RelativeLayout) findViewById(R.id.qqLayout);
        RelativeLayout weixinLayout = (RelativeLayout) findViewById(R.id.weixinLayout);
        qqLayout.setOnClickListener(this);
        weixinLayout.setOnClickListener(this);
        if (get_bitmap_chushihua(1) != null) {
            headImage1.setImageBitmap(get_bitmap_chushihua(1));
        }
        if (get_bitmap_chushihua(2) != null) {
            headImage2.setImageBitmap(get_bitmap_chushihua(2));
        }

    }

    private Bitmap set_filename_chushihua(int types) {
        getFileUtil = new GetFileUtil();
        getFileUtil.file_img = new File(GetFileUtil.file_img_url, "geek_img" + types + GetFileUtil.file_name_geshi);
        Bitmap bitMap = BitmapFactory.decodeFile(GetFileUtil.file_img_url +
                GetFileUtil.FILE_SEP + "geek_img" + types + GetFileUtil.file_name_geshi);
        return bitMap;
    }

    private Bitmap get_bitmap_chushihua(int types) {
        Bitmap bitMap = BitmapFactory.decodeFile(GetFileUtil.get_file_url() +
                GetFileUtil.FILE_SEP + "geek_img" + types + GetFileUtil.file_name_geshi);
        return bitMap;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qqLayout:
                type = 1;
                set_filename_chushihua(type);
                uploadHeadImage();
                break;
            case R.id.weixinLayout:
                type = 2;
                set_filename_chushihua(type);
                uploadHeadImage();
                break;
        }
    }


    /**
     * 上传头像
     */
    private void uploadHeadImage() {
        View mMenuView = LayoutInflater.from(this).inflate(R.layout.layout_popupwindow, null);
        TextView btnCarema = mMenuView.findViewById(R.id.btn_camera);
        TextView btnPhoto = mMenuView.findViewById(R.id.btn_photo);
        TextView btnCancel = mMenuView.findViewById(R.id.btn_cancel);
        final PopupWindow popupWindow = new PopupWindow(mMenuView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(false);
        popupWindow.setAnimationStyle(R.style.AnimBottom);
//        ColorDrawable dw = new ColorDrawable(0xb0000000);
//        popupWindow.setBackgroundDrawable(dw);
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
//                hideInputMethod(context);
                return true;
            }
        });

        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
        //popupWindow在弹窗的时候背景半透明
//        final WindowManager.LayoutParams params = getWindow().getAttributes();
//        params.alpha = 0.5f;
//        getWindow().setAttributes(params);
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                params.alpha = 1.0f;
//                getWindow().setAttributes(params);
//            }
//        });


        btnCarema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //权限判断
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, GetFileUtil.needPermissions, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    gotoCamera();
                }
                popupWindow.dismiss();
            }
        });
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //权限判断
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, GetFileUtil.needPermissions, READ_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    gotoPhoto();
                }
                popupWindow.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    /**
     * 外部存储权限申请返回
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                gotoCamera();
            }
        } else if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                gotoPhoto();
            }
        }
    }

    /**
     * 跳转到相册
     */
    private void gotoPhoto() {
        Log.d("evan", "*****************打开图库********************");
        //跳转到调用系统图库
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
    }


    /**
     * 跳转到照相机
     */
    private void gotoCamera() {
        Log.d("evan", "*****************打开相机********************");
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            Uri fileUri1 = FileProvider7.getUriForFile(this, getFileUtil.file_img);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri1);
            startActivityForResult(takePictureIntent, REQUEST_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
                    gotoClipActivity(Uri.fromFile(getFileUtil.file_img));
                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    gotoClipActivity(uri);
                }
                break;
            case REQUEST_CROP_PHOTO:  //剪切图片返回
                if (resultCode == RESULT_OK) {
                    final Uri uri = intent.getData();
                    if (uri == null) {
                        return;
                    }
                    String cropImagePath = GetFileUtil.getRealFilePathFromUri(getApplicationContext(), uri);
                    Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
                    if (type == 1) {
                        headImage1.setImageBitmap(bitMap);
                    } else {
                        headImage2.setImageBitmap(bitMap);
                    }
                    //此处后面可以将bitMap转为二进制上传后台网络
                    //......

                }
                break;
        }
    }


    /**
     * 打开截图界面
     */
    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, ClipImageActivity.class);
        intent.putExtra("type", type);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }


}
