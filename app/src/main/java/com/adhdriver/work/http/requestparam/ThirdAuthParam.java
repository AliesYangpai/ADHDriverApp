package com.adhdriver.work.http.requestparam;

import com.adhdriver.work.entity.driver.thirdauth.AuthParam;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Created by Administrator on 2017/11/29.
 * 类描述  授权相关的参数
 * 版本
 */

public class ThirdAuthParam extends BaseParam{

    /**
     * QQ授权判断验证接口
     * @param code
     * @param auth_type
     * @return
     */
    public  String getAuthValidParamForQQ(String code,String auth_type,String unionid,String openid,String access_token ) {


        JsonObject jsonObject = getJsonObject();
        JsonObject jb = getJsonObject();
        jsonObject.addProperty("code", code);
        jsonObject.addProperty("auth_type", auth_type);
        jsonObject.addProperty("unionid",unionid);
        jb.addProperty("openid",openid);
        jb.addProperty("access_token",access_token);
        jsonObject.add("parameters",jb);
        return jsonObject.toString();
    }

    /**
     * WX授权验证接口
     * @param code
     * @param auth_type
     * @return
     */
    public  String getAuthValidParamForWx(String code,String auth_type) {


        JsonObject jsonObject =getJsonObject();
        jsonObject.addProperty("code", code);
        jsonObject.addProperty("auth_type", auth_type);
        return jsonObject.toString();


    }





    /**
     * 执行QQ绑定的参数
     * @param auth_type
     * @param authParam
     * @return
     */
    public String getAuthBindParamForQQ(String auth_type,AuthParam authParam) {

        JsonObject jsonObject =getJsonObject();
        jsonObject.addProperty("auth_type", auth_type);
        jsonObject.add("parameters", getJsonElement(authParam));
        return jsonObject.toString();
    }



    /**
     * 执行wx绑定的参数
     * @param auth_type
     * @param authParam
     * @return
     */
    public String getAuthBindParamForWx(String auth_type,AuthParam authParam) {




        JsonObject jsonObject =getJsonObject();
        jsonObject.addProperty("auth_type", auth_type);
        jsonObject.add("parameters", getJsonElement(authParam));
        return jsonObject.toString();
    }



}
