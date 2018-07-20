package com.example.p006_recycleview_alluses.models.demo2;

import java.io.Serializable;

/**
 * Created by shining on 2018/3/16.
 */

public class ItemDemo2 implements Serializable {
    private static final long serialVersionUID = 1L;

    private String content1;
    private int content2;

    public ItemDemo2() {
    }

    public ItemDemo2(String content1, int content2) {
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
