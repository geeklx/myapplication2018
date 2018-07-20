package com.example.p001_fitandroid678.fileandroid.xieru;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 购物车缓存的工具类
 */

public class FlieCacheBeanManager {

    private static FlieCacheBeanManager sShoppingCartHistoryManager;

    private FlieCacheBeanManager() {

    }

    public static FlieCacheBeanManager getInstance() {
        if (sShoppingCartHistoryManager == null) {
            synchronized (FlieCacheBeanManager.class) {
                if (sShoppingCartHistoryManager == null) {
                    sShoppingCartHistoryManager = new FlieCacheBeanManager();
                }
            }
        }
        return sShoppingCartHistoryManager;
    }

    // 写入缓存HashMap
    public void AddHashMap(String txt_id, ArrayList<FileCacheBean> mGoodsList) {
        //写入缓存
        HashMap<String, FileCacheBean> hashMap = new HashMap<>();
        for (FileCacheBean bean : mGoodsList) {
            String goodsId = bean.getGoodsId();
            hashMap.put(goodsId + "", bean);
        }
        FlieCacheManager.getInstance().addHashMap(txt_id, hashMap);
    }

    /**
     * 读取缓存HashMap
     *
     * @param txt_id
     */
    @SuppressWarnings("unchecked")
    public ArrayList<FileCacheBean> getListBean(String txt_id) {
        ArrayList<FileCacheBean> listBean = new ArrayList<>();
        HashMap<String, FileCacheBean> hashMap = (HashMap<String, FileCacheBean>) FlieCacheManager.getInstance().getHashMap(txt_id);
        if (hashMap != null) {
            for (Map.Entry<String, FileCacheBean> entry : hashMap.entrySet()) {
                FileCacheBean bean = entry.getValue();
                if (bean != null) {
                    listBean.add(bean);
                }
            }
        }
        return listBean;
    }

    //删除缓存中的TXT 文件bufen
    public void deletetxt(String txt_id) {
        FlieCacheManager.getInstance().delete(txt_id);
    }

}
