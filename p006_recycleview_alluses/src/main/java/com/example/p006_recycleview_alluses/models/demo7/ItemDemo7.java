package com.example.p006_recycleview_alluses.models.demo7;

import java.io.Serializable;

/**
 * Created by shining on 2018/3/26.
 *  "[\n" +
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
 "]"
 */

public class ItemDemo7 implements Serializable {
    private static final long serialVersionUID = 1L;
    private ItemDemo7ContentTextImgCommon content;
    private String createTime;
    private ItemDemo7User user;

    public ItemDemo7() {
    }

    public ItemDemo7(ItemDemo7ContentTextImgCommon content, String createTime, ItemDemo7User user) {
        this.content = content;
        this.createTime = createTime;
        this.user = user;
    }

    public ItemDemo7ContentTextImgCommon getContent() {
        return content;
    }

    public void setContent(ItemDemo7ContentTextImgCommon content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public ItemDemo7User getUser() {
        return user;
    }

    public void setUser(ItemDemo7User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "content: " + content.getClass().getSimpleName();
    }
}
