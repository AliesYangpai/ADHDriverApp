package com.adhdriver.work.http.requestparam;

import com.google.gson.JsonObject;

/**
 * Created by Administrator on 2017/11/24.
 * 类描述
 * 版本
 */

public class FeedBackParam extends BaseParam{


    public FeedBackParam() {
    }


    /**
     * 意见反馈参数
     * @param source  driverApp
     * @param content  意见说明
     * @param phone    电话号码
     * @param os_version  系统版本
     * @param model     手机信息
     * @param os_type  Android
     * @return
     */
    public String getFeedBackParam(String source,String content,String phone,String os_version,String model,String os_type) {

        String jsonString = "";
        JsonObject jsonObject = getJsonObject();

        JsonObject jb = getJsonObject();

        jsonObject.addProperty("source", source);
        jsonObject.addProperty("content", content);
        jsonObject.addProperty("phone", phone);

        jb.addProperty("os_version", os_version);
        jb.addProperty("model", model);
        jb.addProperty("os_type", os_type);
        jsonObject.add("data", jb);


        jsonString = jsonObject.toString();


        return jsonString;

    }
}
