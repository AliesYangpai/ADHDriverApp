package com.adhdriver.work.http.requestparam;


import com.google.gson.JsonObject;

/**
 * Created by Alie on 2017/11/14.
 * 类描述   推送类的相关参数
 * 版本
 */

public class PushParam extends BaseParam{





    public PushParam() {
    }


    /**
     * 打开推送
     * @param os_type  //推送类型 Android
     * @param os_version  操作系统版本
     * @param model       //手机型号
     * @param device_id   //唯一id
     * @param push_type  //推送类型 （Native，Umeng）
     * @param push_token  deviceToken
     * @param quiet_mode   //打开还是关闭
     * @return
     */
    public String setPushOpenParams(String os_type,
                                    String os_version,
                                    String model,
                                    String device_id,
                                    String push_type,
                                    String push_token,
                                    boolean quiet_mode) {

        String param = "";

        JsonObject jsonObject = getJsonObject();

        jsonObject.addProperty("os_type",os_type);
        jsonObject.addProperty("os_version",os_version);
        jsonObject.addProperty("model",model);
        jsonObject.addProperty("device_id",device_id);
        jsonObject.addProperty("push_type",push_type);
        jsonObject.addProperty("push_token",push_token);
        jsonObject.addProperty("quiet_mode",quiet_mode);


        param = jsonObject.toString();
        return param;

    }


    /**
     * 打开推送
     * @param os_type  //推送类型 Android
     * @param os_version  操作系统版本
     * @param model       //手机型号
     * @param device_id   //唯一id
     * @param push_type  //推送类型 （Native，Umeng）
     * @param push_token  deviceToken
     * @param quiet_mode   //打开还是关闭
     * @return
     */
    public String setPushCloseParams(String os_type,
                                    String os_version,
                                    String model,
                                    String device_id,
                                    String push_type,
                                    String push_token,
                                    boolean quiet_mode) {

        String param = "";

        JsonObject jsonObject = getJsonObject();

        jsonObject.addProperty("os_type",os_type);
        jsonObject.addProperty("os_version",os_version);
        jsonObject.addProperty("model",model);
        jsonObject.addProperty("device_id",device_id);
        jsonObject.addProperty("push_type",push_type);
        jsonObject.addProperty("push_token",push_token);
        jsonObject.addProperty("quiet_mode",quiet_mode);

        param = jsonObject.toString();
        return param;


    }
}
