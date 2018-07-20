package com.example.libanimation.loading;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.libanimation.R;


public class SimpleLoadingDialog {
    private Context mContext;
    private Dialog mDialog;
    private View mDialogContentView;
    private ImageView iv;
    private AnimationDrawable ad;


    public SimpleLoadingDialog(Context context) {
        this.mContext = context;
        init();
    }

    private void init() {
        mDialog = new Dialog(mContext, R.style.custom_dialog);
        mDialogContentView = LayoutInflater.from(mContext).inflate(R.layout.activity_loading_com, null);
        iv = (ImageView) mDialogContentView.findViewById(R.id.loadView_img);
        ad = (AnimationDrawable) iv.getBackground();

        mDialog.setContentView(mDialogContentView);
    }

    public void setBackground(int color) {
        GradientDrawable gradientDrawable = (GradientDrawable) mDialogContentView.getBackground();
        gradientDrawable.setColor(color);
    }

    public void setLoadingText(CharSequence charSequence) {
//        mLoadingView.setLoadingText(charSequence);
    }

    public void show() {
        mDialog.show();
        ad.start();

    }

    public void dismiss() {
        mDialog.dismiss();
        ad.stop();
//        new Handler().postDelayed(new Runnable() {
//            public void run() {
//                ad.stop();
//            }
//        }, 5 * 1000);
    }

    public void onDestroy() {
        mDialog.dismiss();
        ad.stop();
    }

    public Dialog getDialog() {
        return mDialog;
    }

    public void setCanceledOnTouchOutside(boolean cancel) {
        mDialog.setCanceledOnTouchOutside(cancel);
    }
}