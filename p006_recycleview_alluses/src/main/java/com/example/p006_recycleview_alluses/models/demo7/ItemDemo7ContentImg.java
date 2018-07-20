package com.example.p006_recycleview_alluses.models.demo7;

import android.support.annotation.DrawableRes;

/**
 * Created by shining on 2018/3/26.
 */

public class ItemDemo7ContentImg extends ItemDemo7ContentTextImgCommon {
    public static final String TYPE = "simple_image";
    public @DrawableRes
    int resId;

    public ItemDemo7ContentImg(int resId) {
        super(TYPE);
        this.resId = resId;
    }

    public @DrawableRes
    int getResId() {
        return resId;
    }

    public void setResId(@DrawableRes int resId) {
        this.resId = resId;
    }
}
