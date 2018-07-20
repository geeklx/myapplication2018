package com.example.p006_recycleview_alluses.models.demo4;

import java.io.Serializable;

/**
 * Created by shining on 2018/3/16.
 */

public class ItemDemo4 implements Serializable {
    private static final long serialVersionUID = 1L;

    private int content1;
    public boolean enselected;

    public ItemDemo4() {
    }

    public ItemDemo4(int content1, boolean enselected) {
        this.content1 = content1;
        this.enselected = enselected;
    }

    public int getContent1() {
        return content1;
    }

    public void setContent1(int content1) {
        this.content1 = content1;
    }

    public boolean isEnselected() {
        return enselected;
    }

    public void setEnselected(boolean enselected) {
        this.enselected = enselected;
    }
}
