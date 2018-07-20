package com.example.shining.libutils.utilslib.data;

import java.text.DecimalFormat;

/**
 * Created by jack_D on 2016/4/7.
 */
public class DataFormatUtil {
    public static String formatPrice2(double price){
        DecimalFormat df   = new DecimalFormat("######0.00");

        return df.format(price);
    }
}
