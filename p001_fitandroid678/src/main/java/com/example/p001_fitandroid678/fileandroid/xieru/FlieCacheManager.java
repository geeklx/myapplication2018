package com.example.p001_fitandroid678.fileandroid.xieru;

import android.os.Environment;
import android.support.annotation.NonNull;

import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * 购物车缓存的工具类
 */

public class FlieCacheManager {

    //    private static final String PATH = Environment.getExternalStorageDirectory().getPath() + File.separator + "goods";
    private static final String PATH = get_file_url() + File.separator + "geek001";

    public static String get_file_url() {
        String file_apk_url;
        File file_apks = Utils.getApp().getExternalCacheDir();
        if (file_apks != null) {
            file_apk_url = file_apks.getAbsolutePath();
        } else {
            file_apk_url = Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return file_apk_url;
    }

    private static final String FILE_FORMAT = ".txt";
    private static FlieCacheManager sShoppingCartHistoryManager;

    private FlieCacheManager() {

    }

    public static FlieCacheManager getInstance() {
        if (sShoppingCartHistoryManager == null) {
            synchronized (FlieCacheManager.class) {
                if (sShoppingCartHistoryManager == null) {
                    sShoppingCartHistoryManager = new FlieCacheManager();
                }
            }
        }
        return sShoppingCartHistoryManager;
    }

    /**
     * 添加缓存2
     *
     * @param storeId id = 文件名.txt
     */
    public FlieCacheManager addHashMap(String storeId, @NonNull HashMap<String, ? extends Object> storeGoodsBean) {
        File file = new File(PATH);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
//            fileOutputStream = new FileOutputStream(file.getAbsolutePath() + File.separator + storeId + FILE_FORMAT);
            fileOutputStream = new FileOutputStream(file + File.separator + storeId + FILE_FORMAT);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(storeGoodsBean);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * 得到缓存2
     *
     * @param storeId id = 文件名.txt
     */
    @SuppressWarnings("unchecked")
    public <T> HashMap<String, ? extends T> getHashMap(String storeId) {
        File file = new File(PATH);
        if (!file.exists()) {
            return null;
        }
        FileInputStream FileInputStream = null;
        ObjectInputStream objectInputStream = null;
        HashMap<String, ? extends T> storeGoodsBean = new HashMap<>();
        try {
            FileInputStream = new FileInputStream(file.getAbsolutePath() + File.separator + storeId + FILE_FORMAT);
            objectInputStream = new ObjectInputStream(FileInputStream);
            storeGoodsBean = (HashMap<String, ? extends T>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return storeGoodsBean;
    }

//    /**
//     * 获取总个数
//     *
//     * @param storeId id = 文件名.txt
//     * @return
//     */
//    public int getAllHashMap(String storeId) {
//        HashMap<String, Object> hashMap = getHashMap(storeId);
//        int allCount = 0;
//        if (hashMap != null) {
//            for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
//                Object value = entry.getValue();
//                if (value != null && !value.equals("")) {
//                    allCount++;
//                }
//            }
//        }
//        return allCount;
//    }

    /**
     * 删除
     *
     * @param storeId id = 文件名.txt
     */
    public FlieCacheManager delete(@NonNull String storeId) {
        File file = new File(PATH, storeId + FILE_FORMAT);
        if (file.exists()) {
            file.delete();
        }else {
            ToastUtils.showShort("文件不存在!");
        }
        return this;
    }


}
