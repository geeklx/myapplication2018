package com.example.p016_glide47_quan.liebiao;

import java.io.Serializable;

/**
 * Created by geek on 2016/2/25.
 */
public class Biaoge_listBean implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String text_content1;
    private String text_content2;
    private boolean enchoose;

    public Biaoge_listBean() {
    }

    public Biaoge_listBean(String text_content1, String text_content2, boolean enchoose) {
        this.text_content1 = text_content1;
        this.text_content2 = text_content2;
        this.enchoose = enchoose;
    }

    public String getText_content1() {
        return text_content1;
    }

    public void setText_content1(String text_content1) {
        this.text_content1 = text_content1;
    }

    public String getText_content2() {
        return text_content2;
    }

    public void setText_content2(String text_content2) {
        this.text_content2 = text_content2;
    }

    public boolean isEnchoose() {
        return enchoose;
    }

    public void setEnchoose(boolean enchoose) {
        this.enchoose = enchoose;
    }
}
