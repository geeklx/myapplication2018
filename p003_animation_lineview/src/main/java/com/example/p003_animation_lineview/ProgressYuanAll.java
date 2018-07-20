package com.example.p003_animation_lineview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;

public class ProgressYuanAll extends View {
    public static final int STATE_PLAYING = 1;//正在播放
    public static final int STATE_PAUSE = 2;//暂停
    public static final int STATE_STOP = 3;//停止
    public int state;

    private float angle;//记录RotateAnimation中受插值器数值影响的角度
    private float angle2;//主要用来记录暂停时停留的角度，即View初始旋转角度
    private int viewWidth;
    private int viewHeight;
    private MusicAnim musicAnim;

    public ProgressYuanAll(Context context) {
        super(context);
        init();
    }

    public ProgressYuanAll(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressYuanAll(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        state = STATE_STOP;
        angle = 0;
        angle2 = 0;
        viewWidth = 0;
        viewHeight = 0;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = getMeasuredWidth();
        viewHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.rotate(angle2, viewWidth / 2, viewHeight / 2);
        super.onDraw(canvas);
    }

    public class MusicAnim extends RotateAnimation {
        public MusicAnim(float fromDegrees, float toDegrees, float pivotX, float pivotY) {
            super(fromDegrees, toDegrees, pivotX, pivotY);
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            angle = interpolatedTime * 360;
        }
    }

    public void playMusic() {
        if (state == STATE_PLAYING) {
            angle2 = (angle2 + angle) % 360;//可以取余也可以不取，看实际的需求
            musicAnim.cancel();
            state = STATE_PAUSE;
            invalidate();
        } else {
            musicAnim = new MusicAnim(0, 360, viewWidth / 2, viewHeight / 2);
            musicAnim.setDuration(3000);
            musicAnim.setInterpolator(new LinearInterpolator());//动画时间线性渐变
            musicAnim.setRepeatCount(ObjectAnimator.INFINITE);
            startAnimation(musicAnim);
            state = STATE_PLAYING;
        }
    }

    public void stopMusic() {
        angle2 = 0;
        clearAnimation();
        state = STATE_STOP;
        invalidate();
    }
}