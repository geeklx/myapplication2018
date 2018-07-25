package com.example.p021_welcome.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

import com.example.p021_welcome.R;
import com.huanhailiuxin.coolviewpager.CoolViewPager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataImgUtil {

    public static int[] drawableList1 = new int[]{R.drawable.i1,
            R.drawable.i2,
            R.drawable.i5,
            R.drawable.i1,
            R.drawable.i6
    };
    public static int[] drawableList2 = new int[]{R.drawable.img3,
            R.drawable.img5,
            R.drawable.img6,
            R.drawable.img7
    };

    public List<View> getListDrawable(Context context, int... drawableList) {
        List<View> items = new ArrayList<>();
        for (int i = 0; i < drawableList.length; i++) {
            items.add(createImageView(context, drawableList[i]));
        }
        return items;
    }

    private Map<String, Bitmap> imgs = new HashMap<String, Bitmap>();

    public View createImageView(Context context, int imgResId) {
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new CoolViewPager.LayoutParams());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);// FIT_XY
        if (imgs.get("" + imgResId) != null) {
            imageView.setImageBitmap(imgs.get("" + imgResId));
        } else {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), imgResId, options);
            imgs.put("" + imgResId, bitmap);
            imageView.setImageBitmap(bitmap);
        }
        return imageView;
    }


//
//    /**
//     * 得到本地或者网络上的bitmap url - 网络或者本地图片的绝对路径,比如:
//     *
//     * A.网络路径: url="http://blog.foreverlove.us/girl2.png" ;
//     *
//     * B.本地路径:url="file://mnt/sdcard/photo/image.png";
//     *
//     * C.支持的图片格式 ,png, jpg,bmp,gif等等
//     *
//     * @param url
//     * @return
//     */
//    public static Bitmap GetLocalOrNetBitmap(String url)
//    {
//        Bitmap bitmap = null;
//        InputStream in = null;
//        BufferedOutputStream out = null;
//        try
//        {
//            in = new BufferedInputStream(new URL(url).openStream(), Constant.IO_BUFFER_SIZE);
//            final ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
//            out = new BufferedOutputStream(dataStream, Constant.IO_BUFFER_SIZE);
//            copy(in, out);
//            out.flush();
//            byte[] data = dataStream.toByteArray();
//            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//            data = null;
//            return bitmap;
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//            return null;
//        }
//    }

}
