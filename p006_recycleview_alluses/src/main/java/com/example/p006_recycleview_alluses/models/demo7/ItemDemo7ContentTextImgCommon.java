package com.example.p006_recycleview_alluses.models.demo7;

import java.io.Serializable;

/**
 * Created by shining on 2018/3/26.
 *  private static final String JSON_FROM_SERVICE =
 "[\n" +
 "    {\n" +
 "        \"content\":{\n" +
 "            \"text\":\"A simple text Weibo: JSON_FROM_SERVICE.\",\n" +
 "            \"content_type\":\"simple_text\"\n" +
 "        },\n" +
 "        \"createTime\":\"Just now\",\n" +
 "        \"user\":{\n" +
 "            \"avatar\":2130903040,\n" +
 "            \"name\":\"drakeet\"\n" +
 "        }\n" +
 "    },\n" +
 "    {\n" +
 "        \"content\":{\n" +
 "            \"resId\":2130837591,\n" +
 "            \"content_type\":\"simple_image\"\n" +
 "        },\n" +
 "        \"createTime\":\"Just now(JSON_FROM_SERVICE)\",\n" +
 "        \"user\":{\n" +
 "            \"avatar\":2130903040,\n" +
 "            \"name\":\"drakeet\"\n" +
 "        }\n" +
 "    }\n" +
 "]";
 */

public abstract class ItemDemo7ContentTextImgCommon implements Serializable {
    private static final long serialVersionUID = 1L;
    private String content_type;

    public ItemDemo7ContentTextImgCommon() {
    }

    public ItemDemo7ContentTextImgCommon(String content_type) {
        this.content_type = content_type;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }
}
