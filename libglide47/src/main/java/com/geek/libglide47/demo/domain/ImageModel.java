package com.geek.libglide47.demo.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class ImageModel implements Serializable {

    private String title;
    private ArrayList<String> images;

    public ImageModel(String title, ArrayList<String> images) {
        this.title = title;
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }
}
