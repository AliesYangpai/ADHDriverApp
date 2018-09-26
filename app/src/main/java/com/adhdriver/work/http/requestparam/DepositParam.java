package com.adhdriver.work.http.requestparam;

import com.google.gson.JsonObject;

/**
 * Created by Administrator on 2017/11/24.
 * 类描述  提现相关的请求参数类
 * 版本
 */

public class DepositParam extends BaseParam {

    /**
     * 修改提现密码
     * @param password
     * @param new_password
     * @return
     */
    public String getResetDepositPassParam(String password, String new_password) {

        JsonObject jsonObject = getJsonObject();

        jsonObject.addProperty("password", password);
        jsonObject.addProperty("new_password", new_password);

        return jsonObject.toString();

    }


    /**
     * 获取提现验证码【忘记提现密码时】
     * @param phone
     * @param options
     * @return
     */
    public String getDepositVertifyCode( String phone, String options) {

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
    public String getConfirmVertifyCode( String phone, String pass_code) {

        JsonObject jsonObject = getJsonObject();

        jsonObject.addProperty("phone", phone);
        jsonObject.addProperty("pass_code", pass_code);

        return jsonObject.toString();
    }


    /**
     * 重置提现密码【忘记提现密码时】
     * @param last_card_no
     * @param new_pay_password
     * @param pass_code
     * @return
     */
    public String getResetDepositPass( String last_card_no, String new_pay_password,String pass_code) {

        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty("last_card_no", last_card_no);
        jsonObject.addProperty("new_pay_password", new_pay_password);
        jsonObject.addProperty("pass_code", pass_code);

        return jsonObject.toString();
    }

}
