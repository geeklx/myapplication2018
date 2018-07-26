package com.example.p012_interpolator;

import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;



public class CartAnim {

    private Activity mActivity;
    private TextView mAnimView;
    private boolean isAnimPlaying;

    public static CartAnim obtain(Activity activity) {
        return new CartAnim(activity);
    }

    private CartAnim(Activity activity) {
        mActivity = activity;
    }

    public void start(String text, final View startView, final float[] yOffset, final long[] times) {
        cancel();

        mAnimView = getCartAnimView(mActivity);
        mAnimView.setVisibility(View.INVISIBLE);
        mAnimView.setText(text);


        final FrameLayout container = (FrameLayout) mActivity.getWindow().getDecorView();
        container.addView(mAnimView);
        container.requestLayout();


        container.post(new Runnable() {
            @Override
            public void run() {
                int[] locations = new int[2];
                startView.getLocationInWindow(locations);
                FrameLayout.LayoutParams p = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                p.gravity =
                        p.topMargin = locations[1];
                p.leftMargin = locations[0] + startView.getMeasuredWidth() / 2 - mAnimView.getMeasuredWidth() / 2;

                mAnimView.setGravity(Gravity.CENTER);
                mAnimView.setLayoutParams(p);
                mAnimView.setVisibility(View.VISIBLE);
                mAnimView.requestLayout();

                stepOne(container, mAnimView, yOffset, times);
            }
        });
    }

    private void stepOne(final FrameLayout container, final View animView, final float[] yOffset, final long[] time) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, yOffset[0]);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.f, 1.f);

        AnimationSet set = new AnimationSet(true);
        set.addAnimation(translateAnimation);
        set.addAnimation(alphaAnimation);

        set.setDuration(time[0]);
        set.setFillAfter(true);
        set.setAnimationListener(new AnimationAdapter() {
            @Override
            public void onAnimationEnd(Animation animation) {
                animView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (!isAniming()) {
                            return;
                        }
                        stepTwo(container, animView, yOffset, time);
                    }
                }, time[1]);
            }
        });

        isAnimPlaying = true;
        animView.startAnimation(set);
    }

    private void stepTwo(final FrameLayout container, final View animView, final float[] yOffset, final long[] time) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, yOffset[0], yOffset[0] + yOffset[1]);

        AnimationSet set = new AnimationSet(true);
        set.addAnimation(translateAnimation);

        set.setDuration(time[2]);
        set.setFillAfter(true);
        set.setAnimationListener(new AnimationAdapter() {
            @Override
            public void onAnimationEnd(Animation animation) {
                if (!isAniming()) {
                    return;
                }
                Log.e("---geek-isAniming--",isAniming()+"");
                onFinished();

                animView.setVisibility(View.GONE);
                container.removeView(animView);

            }

        });

        animView.startAnimation(set);
    }

    private TextView getCartAnimView(Activity activity) {
        return (TextView) LayoutInflater.from(activity).inflate(R.layout.icon_cart_anim,
                (ViewGroup) activity.getWindow().getDecorView(), false);
    }

    public boolean isAniming() {
        return isAnimPlaying;
    }

    private boolean isPlayingAnimation() {
        return mAnimView != null
                && mAnimView.getAnimation() != null
                && mAnimView.getAnimation().hasStarted()
                && !mAnimView.getAnimation().hasEnded();
    }

    public void cancel() {
        if (isAniming()) {
            if (isPlayingAnimation()) {
                mAnimView.getAnimation().cancel();
            }
            onFinished();
        }
    }

    private void onFinished() {
        isAnimPlaying = false;
        if (mActivity == null || mAnimView == null) {
            return;
        }
        FrameLayout container = (FrameLayout) mActivity.getWindow().getDecorView();
        container.removeView(mAnimView);
        Log.e("---geek-isAnimPlaying--",isAnimPlaying+"");

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
}
