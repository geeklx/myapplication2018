package com.example.p003_animation_lineview.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.p003_animation_lineview.MainActivity;
import com.example.p003_animation_lineview.ProgressYuan;
import com.example.p003_animation_lineview.R;
import com.example.p003_animation_lineview.progressutils.NumberProgressBar;
import com.white.progressview.CircleProgressView;
import com.yanzhikai.pictureprogressbar.PictureProgressBar;


/**
 * @Author: yuxingdong
 * @Time: 2018/2/16
 */

public class AddYuanxingActivity extends AppCompatActivity {

    private ProgressYuan progressYuan;
    private Button btn1;
    private Button btn2;

    private CircleProgressView circle_progress_normal;
    private NumberProgressBar number_progress;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, AddYuanxingActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_yuan);
        findview();
        progress1();
        progress2();
        progress3();

    }


    private void findview() {
        progressYuan = findViewById(R.id.view1);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        progressBar = findViewById(R.id.progress_horizontal);
        circle_progress_normal = findViewById(R.id.circle_progress_normal);
        number_progress = findViewById(R.id.number_progress);

    }



    private int mMaxProgress = 100;
    private int mProgress = 0;

    private void progress3() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mProgress < mMaxProgress) {
                    try {
                        mProgress++;
                        Message msg = mHandler.obtainMessage();
                        msg.what = 100;
                        msg.obj = mProgress;
                        mHandler.sendMessage(msg);
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 100:
                    int progress = (int) msg.obj;
                    number_progress.setProgress(progress);
                    if (100 == progress) {

                        Toast.makeText(AddYuanxingActivity.this,"下载完成，跳转到安装界面",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }

        }
    };


    private void progress2() {
        progressYuan.playMusic();
        int color = ContextCompat.getColor(this, R.color.blue);
        circle_progress_normal.setReachBarColor(color);
        circle_progress_normal.setNormalBarColor(adjustAlpha(color, 0.3f));
        circle_progress_normal.runProgressAnim(20000);
    }

    private int adjustAlpha(int color, float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

    public void btn1(View view) {
        progressYuan.playMusic();
        if (btn1.getText().equals("开始")) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    btn1.setText("暂停");
                }
            });

        }
        if (btn1.getText().equals("暂停")) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    btn1.setText("开始");

                }
            });

        }
    }

    public void btn2(View view) {
        progressYuan.stopMusic();
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_clear:

                break;

            default:
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private Thread thread;
    private ProgressBar progressBar;
    private boolean stateChange = true;

    private void progress1() {
        progressBar.setVisibility(View.VISIBLE);
//        setProgresss(70, "f25252");
        //动态进度条bufen
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (true) {
                    int currentValue = progressBar.getProgress();
                    int currentMaxValue = progressBar.getMax();
                    int currentSecondaryValue = progressBar.getSecondaryProgress();
                    if (stateChange == false) {
                        if (currentValue >= currentMaxValue) {
                            stateChange = true;
                        } else {
                            progressBar.setProgress(currentValue + 1);
                            progressBar.setSecondaryProgress(currentValue + 1);
                        }
                    } else {
                        if (currentValue <= 0) {
                            stateChange = false;
                        } else {
//                            progressBar.setProgress(currentValue - 1);
                            thread = null;
                        }
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    public void setProgresss(int progress, String colors) {
        int color = Integer.parseInt(colors, 16);//tag.getCoupon_color().substring(1)  FF5001  ratings.getTag_color().substring(1)
        color = 0xFF000000 + color;
//            viewHolder.pb.getProgressDrawable().setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
        LayerDrawable drawable = (LayerDrawable) progressBar.getProgressDrawable();
        drawable.getDrawable(1).setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
        progressBar.setProgress(progress);
        if (progress == 100) {
//            this.dismiss();
        }
    }


}
