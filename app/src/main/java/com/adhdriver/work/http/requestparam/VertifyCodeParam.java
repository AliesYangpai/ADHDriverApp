package com.adhdriver.work.http.requestparam;

import com.google.gson.JsonObject;

/**
 * Created by Administrator on 2017/12/12.
 * 类描述  注册相关的请求参数Param
 * 版本
 */

public class VertifyCodeParam extends BaseParam {






    /**
     * 获取手机验证码
     *
     * @param phone
     * @return
     */
    public  String getSendVertifyCodeParam(String phone,String options) {

        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty("phone", phone);
        jsonObject.addProperty("options", options);

        return jsonObject.toString();
    }


    /**
     * 验证提现验证码【忘记提现密码时】
     * @param phone
     * @param pass_code
     * @return
     */
    public String getConfirmVertifyCodeParam( String phone, String pass_code) {

        JsonObject jsonObject = getJsonObject();

        jsonObject.addProperty("phone", phone);
        jsonObject.addProperty("pass_code", pass_code);

        return jsonObject.toString();
    }



}
