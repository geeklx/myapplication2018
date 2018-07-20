package com.geek.libglide47.demo.commonutil;

import com.geek.libglide47.demo.domain.ImageModel;

import java.util.ArrayList;
import java.util.List;

public class ModelUtil {

    public static List<ImageModel> getImages() {
        List<ImageModel> list = new ArrayList<>();
        ArrayList<String> images = new ArrayList<>();
        images.add("http://img2.imgtn.bdimg.com/it/u=2850936076,2080165544&fm=206&gp=0.jpg");
        images.add("http://img3.imgtn.bdimg.com/it/u=524208507,12616758&fm=206&gp=0.jpg");
        images.add("http://img3.imgtn.bdimg.com/it/u=698582197,4250615262&fm=206&gp=0.jpg");
        images.add("http://img3.imgtn.bdimg.com/it/u=524208507,12616758&fm=206&gp=0.jpg");
        images.add("http://img5.imgtn.bdimg.com/it/u=3191365283,111438732&fm=21&gp=0.jpg");
        images.add("http://img5.imgtn.bdimg.com/it/u=482494496,1350922497&fm=206&gp=0.jpg");
        images.add("http://img4.imgtn.bdimg.com/it/u=2440866214,1867472386&fm=21&gp=0.jpg");
        images.add("http://img3.imgtn.bdimg.com/it/u=524208507,12616758&fm=206&gp=0.jpg");
        images.add("http://img1.imgtn.bdimg.com/it/u=1832737924,144748431&fm=21&gp=0.jpg");
        images.add("http://img5.imgtn.bdimg.com/it/u=2091366266,1524114981&fm=21&gp=0.jpg");
        images.add("http://img5.imgtn.bdimg.com/it/u=2091366266,1524114981&fm=21&gp=0.jpg");
        images.add("http://img5.imgtn.bdimg.com/it/u=1424970962,1243597989&fm=21&gp=0.jpg");
        list.add(new ImageModel("一共" + images.size() + "张图片", images));
        return list;
    }

}
