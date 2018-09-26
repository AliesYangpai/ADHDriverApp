package com.adhdriver.work.http.requestparam;

import android.util.Log;

import com.adhdriver.work.http.HttpConst;
import com.google.gson.JsonObject;

/**
 * Created by Administrator on 2017/12/12.
 * 类描述  注册相关的请求参数Param
 * 版本
 */

public class RegisterParam extends BaseParam {


    /**
     * 获取完成用户信息的params
     *
     * @param userName
     * @param user_identity_card_no
     * @param pathFront
     * @param pathBack
     * @param inviteCode
     * @return
     */
    public String getCompleteUserParam(String userName, String user_identity_card_no, String pathFront, String pathBack, String inviteCode) {

        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty("user_name", userName);
        jsonObject.addProperty("identity_card_no", user_identity_card_no);
        jsonObject.addProperty("identity_card_path", pathFront);
        jsonObject.addProperty("identity_card_back_path", pathBack);
        jsonObject.addProperty("unique_code", inviteCode);
        return jsonObject.toString();

    }



    /**
     * 获取手机验证码
     *
     * @param phone
     * @return
     */
    public  String getConfrimCodeParam(String phone,String options) {

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


    /**
     * 用户注册
     *
     * @param phone
     * @param password
     * @param pass_code //验证码
     * @return
     */
    public  String getRegisteParams(String phone, String password, String pass_code,String defaultName) {


        JsonObject jsonObject = getJsonObject();

        jsonObject.addProperty("phone", phone);
        jsonObject.addProperty("password", password);
        jsonObject.addProperty("pass_code", pass_code);
        jsonObject.addProperty("user_name",defaultName);




        return jsonObject.toString();


    }


    /**
     * 获取注册时获取token
     * @param username
     * @param password
     * @return
     */
    public String  getRegTokenParam(String username,String password) {

        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty("username", username);
        jsonObject.addProperty("password", password);
        return jsonObject.toString();

    }

}
