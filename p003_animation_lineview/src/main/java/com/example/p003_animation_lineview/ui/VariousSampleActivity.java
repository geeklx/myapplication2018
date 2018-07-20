package com.example.p003_animation_lineview.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;

import com.example.libanimation.dagougou.PixelPath;
import com.example.libanimation.dagougou.SimpleLineView;
import com.example.libanimation.dagougou.painter.CirclePainter;
import com.example.libanimation.dagougou.painter.CircleProgressPainter;
import com.example.libanimation.dagougou.painter.Painter;
import com.example.libanimation.dagougou.painter.RealCirclePainter;
import com.example.libanimation.dagougou.painter.SegProgressPainter;
import com.example.libanimation.dagougou.painter.SegmentPainter;
import com.example.p003_animation_lineview.R;


/**
 * @author yuxingdong
 */
public class VariousSampleActivity extends AppCompatActivity {

    private Painter mHookPainter, mSquarePainter,
            mTrianglePainter, mReverseTrianglePainter,
            mLeftChaPainter, mRightChaPainter,
            mCloseStarPainter, mOpenStarPainter,
            mHookProgressPainter, mSquareProgressPainter,
            mLeftChaProgressPainter, mRightChaProgressPainter;

    private CirclePainter mCiclePainter, mDoublemCiclePainter, mCicleProgressPainter;

    /**
     * 这里的路径点通过获取PS中五角星图片的像素点的x和y值计算得到
     * 例如: 图片像素为200 * 200, x = 100, y = 16, 则当前点为
     * 200*(y-1)+x = 200 * 15 + 100 = 3100
     * 这里的点大约取了一下，并非绝对精确的值
     */
    private static final int[] CLOSE_FIVE_STAR = {3100, 36046, 15987, 15813, 36154};
    private static final int[] OPEN_FIVE_STAR =
            {3100, 15878, 15813, 23667, 36046, 28100, 36154, 23731, 15987, 15920};

    private SimpleLineView mView1, mView2, mView3, mView4, mView5, mView6;

    private SimpleLineView mView7;
    private Painter mHookPainter7;
    private CirclePainter mCiclePainter7;
    private SimpleLineView mView8;
    private Painter mLeftChaPainter8;
    private Painter mRightChaPainter8;
    private Painter mSquarePainter8;

    {
        // 钩
        PixelPath hookPath = new PixelPath(10, 10, new int[]{43, 65, 38});
        // 三角形
        PixelPath trianglePath =
                new PixelPath(400, 400, new int[]{15800, 112063, 112337});
        // 倒三角
        PixelPath reverseTrianglePath =
                new PixelPath(400, 400, new int[]{47263, 143400, 47537});
        // 圆形
        PixelPath circlePath =
                new PixelPath(10, 10, new int[]{1, 100});
        // 同时画两个圆
        // 这里设置了两组像素点作为两个圆的Rect
        PixelPath doubleCirclePath =
                new PixelPath(400, 400, new int[]{1605, 12833, 146366, 158795});
        // 矩形
        PixelPath squarePath = new PixelPath(2, 2, new int[]{1, 2, 4, 3});
        // 左上 -> 右下
        PixelPath leftChaPath = new PixelPath(10, 10, new int[]{34, 67});
        // 右上 -> 左下
        PixelPath rightChaPath = new PixelPath(10, 10, new int[]{37, 64});
        // 连线五角星
        PixelPath closeStarPath = new PixelPath(200, 200, CLOSE_FIVE_STAR);
        // 空心五角星
        PixelPath openStarPath = new PixelPath(200, 200, OPEN_FIVE_STAR);

        // 钩
        mHookPainter = new SegmentPainter(hookPath, 1000, false);
        mHookProgressPainter = new SegProgressPainter(mHookPainter, new float[]{0.6f, 1});

        // 方形
        mSquarePainter = new SegmentPainter(squarePath, 1000, true);
        mSquareProgressPainter = new SegProgressPainter(mSquarePainter, new float[]{0.6f, 1});

        // 圆形
        mCiclePainter = new RealCirclePainter(circlePath, 1000,
                -120, 360, false);
        mDoublemCiclePainter = new DoubleCirclePainter(doubleCirclePath,
                1000, 90, 360, false);

        // 由于原图片外层原与图片边框相切, 默认画笔设置了宽度，因此这里需要设padding，
        // 否则圆线条与view相切部分不完整。
        mDoublemCiclePainter.setPadding(6);
        mCicleProgressPainter = new CircleProgressPainter(mCiclePainter, new float[]{0, 0.4f});

        // 三角形
        mTrianglePainter = new SegmentPainter(trianglePath, 800, true);
        // 倒三角
        mReverseTrianglePainter = new SegmentPainter(reverseTrianglePath, 800, true);

        // 这里的叉分了两笔画
        mLeftChaPainter = new SegmentPainter(leftChaPath, 300, false);
        mRightChaPainter = new SegmentPainter(rightChaPath, 300, false);
        mLeftChaProgressPainter = new SegProgressPainter(mLeftChaPainter, new float[]{0f, 0.3f});
        mRightChaProgressPainter = new SegProgressPainter(mRightChaPainter, new float[]{0.3f, 0.6f});

        // 五角星
        mCloseStarPainter = new SegmentPainter(closeStarPath, 2000, true);
        mOpenStarPainter = new SegmentPainter(openStarPath, 2000, true);
    }

    public static void launch(Context context) {
        context.startActivity(new Intent(context, VariousSampleActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_various_sample);

        mView1 = findViewById(R.id.view1);
        mView2 = findViewById(R.id.view2);
        mView3 = findViewById(R.id.view3);
        mView4 = findViewById(R.id.view4);
        mView5 = findViewById(R.id.view5);
        mView6 = findViewById(R.id.view6);
        mView7 = findViewById(R.id.view7);
        mView8 = findViewById(R.id.view8);

        mView1.addPainter(mDoublemCiclePainter)
                .addPainter(mTrianglePainter)
                .addPainter(mReverseTrianglePainter);
        mView2.addPainter(mCiclePainter)
                .addPainter(mLeftChaPainter)
                .addPainter(mRightChaPainter);
        mView3.addPainter(mCloseStarPainter);
        mView4.addPainter(mOpenStarPainter);

        // 钩
        PixelPath hookPath7 = new PixelPath(10, 10, new int[]{43, 65, 38});
        mHookPainter7 = new SegmentPainter(hookPath7, 1000, false);
        // 圆形
        PixelPath circlePath7 = new PixelPath(10, 10, new int[]{1, 100});
        mCiclePainter7 = new RealCirclePainter(circlePath7, 1000,
                -120, 360, false);
        mView7.clear();
        mView7.addPainter(mCiclePainter7)
                .addPainter(mHookPainter7);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mView7.start();
            }
        }, 500);

        // 这里的叉分了两笔画
        // 左上 -> 右下
        PixelPath leftChaPath8 = new PixelPath(10, 10, new int[]{34, 67});
        // 右上 -> 左下
        PixelPath rightChaPath8 = new PixelPath(10, 10, new int[]{37, 64});
        mLeftChaPainter8 = new SegmentPainter(leftChaPath8, 300, false);
        mRightChaPainter8 = new SegmentPainter(rightChaPath8, 300, false);
        // 方形
        // 矩形
        PixelPath squarePath8 = new PixelPath(2, 2, new int[]{1, 2, 4, 3});
        mSquarePainter8 = new SegmentPainter(squarePath8, 1000, true);
        mView8.clear();
        mView8.addPainter(mLeftChaPainter8)
                .addPainter(mRightChaPainter8)
                .addPainter(mSquarePainter);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mView8.start();
            }
        }, 500);

        // TODO: 2018/2/16 若setProgress过快，上一步onDraw可能未完成，这种情况下画面会闪动。
        // 这里先放主线程处理了
        mView5.addPainter(mCicleProgressPainter).addPainter(mHookProgressPainter).onMain();
        mView6.addPainter(mLeftChaProgressPainter).addPainter(mRightChaProgressPainter)
                .addPainter(mSquareProgressPainter).onMain();

        ((SeekBar) findViewById(R.id.seek_bar))
                .setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        mView5.setProgress(progress);
                        mView6.setProgress(progress);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_start:
                mView1.start();
                mView2.start();
                mView3.start();
                mView4.start();
                break;
            case R.id.bt_continue:
                mView1.stick();
                mView2.stick();
                mView3.stick();
                mView4.stick();
                break;
            case R.id.bt_stop:
                mView1.stop();
                mView2.stop();
                mView3.stop();
                mView4.stop();
                break;
            default:
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        mView1.stop();
        mView2.stop();
        mView3.stop();
        mView4.stop();
        mView7.stop();
        mView8.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mView7.stop();
        mView8.stop();
    }
}
