package com.example.shining.libutils.utilslib.data;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by qibin on 2016/8/30.
 */

public class IoUtils {

    public static void closeGracefully(Closeable closeable) {
        if (closeable == null) { return;}
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
