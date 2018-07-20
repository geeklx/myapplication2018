package com.example.p006_recycleview_alluses.models.demo7;

import android.support.annotation.DrawableRes;

import java.io.Serializable;

/**
 * Created by shining on 2018/3/26.
 */

public class ItemDemo7User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    public @DrawableRes
    int avatar;

    public ItemDemo7User() {
    }

    public ItemDemo7User(String name, int avatar) {
        this.name = name;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public @DrawableRes
    int getAvatar() {
        return avatar;
    }

    public void setAvatar(@DrawableRes int avatar) {
        this.avatar = avatar;
    }
}
