package com.example.p006_recycleview_alluses.models.demo7.gson;

import android.support.annotation.NonNull;

import com.example.p006_recycleview_alluses.models.demo7.ItemDemo7;
import com.example.p006_recycleview_alluses.models.demo7.ItemDemo7ContentTextImgCommon;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public final class WeiboJsonParser {

    private WeiboJsonParser() {
    }

    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(ItemDemo7ContentTextImgCommon.class, new WeiboContentDeserializer())
            .create();

    public static List<ItemDemo7> fromJson(@NonNull String json) {
        return GSON.fromJson(json, new TypeToken<ArrayList<ItemDemo7>>() {
        }.getType());
    }
}
