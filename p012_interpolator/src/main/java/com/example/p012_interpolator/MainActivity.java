package com.example.p012_interpolator;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

/**
 * android:duration:动画执行的时间，以毫秒为单位
 * android:fillEnabled:true|false,true:动画结束时还原到开始动画前的状态
 * android:fillBefore:如果fillEnabled的值为true，它的值才有意义，否则没有意义默认值是true，视图会停留在动画开始的状态
 * <p>
 * android:fillAfter:设置的是在这个动画结束后是否保留这个动画的最后一帧的效果填充后面的动画,它的设置不受fillEnabled的影响
 * android:repeatMode:reverse|restart,重复类型,reverse:表示倒序回访,restart:表示重新放一遍，这个属性必须与repeatCount联合使用,因为它的前提是重复,即重复播放时的播放类型。
 * android:repeatCount:动画重复的次数(注意是重复的次数)，可以是你想循环播放的次数，也是可以是infinite:表示无限循环
 * android:interpolator:设定的插值器，它主要用来为动画设置一些特殊的效果，比方说：加速运动、减速运动、动画结束的时候弹 起等等
 */
public class MainActivity extends AppCompatActivity {

    private TextView tv_cart_anim;
    private View targetParent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_cart_anim = findViewById(R.id.tv_cart_anim);
        targetParent = (View) tv_cart_anim.getParent();
        findViewById(R.id.tv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1 下->上 2 回弹一下 3淡隐淡出
                tv_cart_anim.setVisibility(View.VISIBLE);
                animation1(tv_cart_anim);
//                animation3(tv_cart_anim);
            }
        });
    }

    private void animation1(final View animView) {
        final float startY = animView.getY();
        final float engY = targetParent.getHeight() - animView.getY() - animView.getHeight() - targetParent.getPaddingBottom();
        final float engX = targetParent.getWidth() - animView.getX() - animView.getWidth() - targetParent.getPaddingLeft();
        float[] yyy = new float[]{-600.f, -20.f};
        final long[] time = new long[]{1000, 500, 300};
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, yyy[0]);
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(translateAnimation);
        set.setDuration(time[0]);
        set.setFillAfter(true);
        set.setAnimationListener(new AnimationAdapter() {
            @Override
            public void onAnimationEnd(Animation animation) {
                animView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        stepTwo(container, animView, yOffset, time);
                        animation3(animView);
                    }
                }, time[1]);
            }
        });
        animView.startAnimation(set);

    }

    private class AnimationAdapter implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    /**
     * 使用Animation的子类AlphaAnimation
     */
    private void animation3(View textview) {
        textview.clearAnimation();
        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0f);
        alphaAnimation.setDuration(1000);
        textview.startAnimation(alphaAnimation);
        textview.setVisibility(View.GONE);
    }

    public void tvTimer(final View view, int setint) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, setint);
        valueAnimator
                .addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        ((TextView) view).setText("$ "
                                + (Integer) animation.getAnimatedValue());
                    }
                });
        valueAnimator.setDuration(3000);
        valueAnimator.start();

    }

}
