package com.example.p006_recycleview_alluses.models.demo7;

/**
 * Created by shining on 2018/3/26.
 */

public class ItemDemo7ContentText extends ItemDemo7ContentTextImgCommon {
    public static final String TYPE = "simple_text";
    private String text;

    public ItemDemo7ContentText(String text) {
        super(TYPE);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
