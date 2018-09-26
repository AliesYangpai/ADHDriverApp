package com.adhdriver.work.http.requestparam;

import com.adhdriver.work.entity.driver.temp.PublishRoute;
import com.google.gson.JsonObject;

/**
 * Created by Administrator on 2017/11/24.
 * 类描述  登陆相关的参数
 * 版本
 */

public class LoginParam extends BaseParam {


    /**
     * 修改登陆密码
     *
     * @param password
     * @param new_password
     * @return
     */
    public String getChangeLoginPassParam(String password, String new_password) {

        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty("password", password);
        jsonObject.addProperty("new_password", new_password);


        return jsonObject.toString();

    }


    /**
     * 用户登陆
     *
     * @param username 电话号码
     * @param password
     * @return
     */
    public String getLogonParams(String username, String password) {

        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty("username", username);
        jsonObject.addProperty("password", password);



        return jsonObject.toString();


    }


}
