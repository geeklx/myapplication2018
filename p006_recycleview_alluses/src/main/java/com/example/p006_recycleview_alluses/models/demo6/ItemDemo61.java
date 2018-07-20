package com.example.p006_recycleview_alluses.models.demo6;

import java.io.Serializable;

/**
 * Created by shining on 2018/3/16.
 */

public class ItemDemo61 implements Serializable {
    private static final long serialVersionUID = 1L;

    private int content1;
    private String content2;

    public ItemDemo61() {
    }

    public ItemDemo61(int content1, String content2) {
        this.content1 = content1;
        this.content2 = content2;
    }

    public int getContent1() {
        return content1;
    }

    public void setContent1(int content1) {
        this.content1 = content1;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }
}
