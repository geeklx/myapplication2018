package com.example.p006_recycleview_alluses.models.demo6;

import java.io.Serializable;
import java.util.List;

/**
 * Created by shining on 2018/3/16.
 */

public class ItemDemo62 implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<ItemDemo621> content1;

    public ItemDemo62() {
    }

    public ItemDemo62(List<ItemDemo621> content1) {
        this.content1 = content1;
    }

    public List<ItemDemo621> getContent1() {
        return content1;
    }

    public void setContent1(List<ItemDemo621> content1) {
        this.content1 = content1;
    }
}
