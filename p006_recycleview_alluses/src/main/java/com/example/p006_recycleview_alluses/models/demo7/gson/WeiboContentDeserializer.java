package com.example.p006_recycleview_alluses.models.demo7.gson;

import android.support.annotation.NonNull;

import com.example.p006_recycleview_alluses.models.demo7.ItemDemo7ContentImg;
import com.example.p006_recycleview_alluses.models.demo7.ItemDemo7ContentText;
import com.example.p006_recycleview_alluses.models.demo7.ItemDemo7ContentTextImgCommon;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;


public class WeiboContentDeserializer implements JsonDeserializer<ItemDemo7ContentTextImgCommon> {

  @Override
  public ItemDemo7ContentTextImgCommon deserialize(JsonElement json, Type type, JsonDeserializationContext context)
      throws JsonParseException {
    Gson gson = WeiboJsonParser.GSON;
    JsonObject jsonObject = (JsonObject) json;
    final String contentType = stringOrEmpty(jsonObject.get("content_type"));
    ItemDemo7ContentTextImgCommon content = null;

    if (ItemDemo7ContentText.TYPE.equals(contentType)) {
      content = gson.fromJson(json, ItemDemo7ContentText.class);
    } else if (ItemDemo7ContentImg.TYPE.equals(contentType)) {
      content = gson.fromJson(json, ItemDemo7ContentImg.class);
    }
    return content;
  }

  private @NonNull
  String stringOrEmpty(JsonElement jsonElement) {
    return jsonElement.isJsonNull() ? "" : jsonElement.getAsString();
  }
}
