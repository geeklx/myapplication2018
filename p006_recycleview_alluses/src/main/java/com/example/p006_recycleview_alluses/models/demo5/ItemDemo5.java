package com.example.p006_recycleview_alluses.models.demo5;

import java.io.Serializable;

/**
 * Created by shining on 2018/3/16.
 */

public class ItemDemo5 implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int TYPE_1 = 1;
    public static final int TYPE_2 = 2;

    private String content1;
    private int content2;

    public ItemDemo5() {
    }

    public ItemDemo5(String content1, int content2) {
        this.content1 = content1;
        this.content2 = content2;
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public int getContent2() {
        return content2;
    }

    public void setContent2(int content2) {
        this.content2 = content2;
    }
}
