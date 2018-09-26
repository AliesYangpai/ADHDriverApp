package com.adhdriver.work.http.requestparam;

import com.adhdriver.work.constant.ConstParams;
import com.adhdriver.work.constant.ConstTag;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/10.
 * 类描述
 * 版本
 */

public class ThirdPayParam extends BaseParam {

    public ThirdPayParam() {
    }


    /**
     * ======================================支付宝==================================================================
     */


    /**
     * 支付宝
     * ali支付渠道获取授权令牌信息
     */
    public String getAliPreAppAuthLoginParam(String channel_id,
                                             String device_id,
                                             String os_type,
                                             String device_type,
                                             JsonObject parameters) {

        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty("channel_id", channel_id);
        jsonObject.addProperty("device_id", device_id);
        jsonObject.addProperty("os_type", os_type);
        jsonObject.addProperty("device_type", device_type);
        jsonObject.add("parameters", parameters);

        return jsonObject.toString();

    }


    /**
     * 支付宝
     * 根据支付渠道获取授权相关信息
     */
    public String doAliGetAuthLoginInfoParam(String channel_id,
                                             String pay_password,
                                             String device_id,
                                             String os_type,
                                             String device_type,
                                             String auth_code,
                                             JsonObject parameters) {

        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty("channel_id", channel_id);
        jsonObject.addProperty("pay_password", pay_password);
        jsonObject.addProperty("device_id", device_id);
        jsonObject.addProperty("os_type", os_type);
        jsonObject.addProperty("device_type", device_type);
        parameters.addProperty("auth_code", auth_code);
        jsonObject.add("parameters", parameters);

        return jsonObject.toString();

    }


    /**
     * 微信
     * 根据支付渠道获取授权相关信息
     */
    public String doWxGetAuthLoginInfoParam(String channel_id,
                                             String pay_password,
                                             String device_id,
                                             String os_type,
                                             String device_type,
                                             String auth_code,
                                             JsonObject parameters) {

        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty("channel_id", channel_id);
        jsonObject.addProperty("pay_password", pay_password);
        jsonObject.addProperty("device_id", device_id);
        jsonObject.addProperty("os_type", os_type);
        jsonObject.addProperty("device_type", device_type);
        jsonObject.add("parameters", parameters);

        return jsonObject.toString();

    }


    /**
     * 支付宝
     * 执行阿里支付验签 (发生在调用支付接口之后)
     * 【注意 这个是支付验证，而不是 扫码支付验证】
     */


    public String getAliCheckAliPayResult(String resultInfo,
                                          String app_type,
                                          String os_type,
                                          String payer) {
        JSONObject jsonObject = null;
        try {
            jsonObject = getORGJsonObJect(resultInfo);
            jsonObject.put("app_type",app_type);
            jsonObject.put("os_type",os_type);
            jsonObject.put("payer",payer);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }


    /**
     * ======================================微信==================================================================
     */


    public String getWxCheckWxPayResult(String app_type,
                                        String os_type,
                                        String payment_record_id,
                                        String payer) {


        String jsonString = "";
        JsonObject jsonObject = getJsonObject();

        jsonObject.addProperty("app_type", app_type);
        jsonObject.addProperty("os_type", os_type);
        jsonObject.addProperty("payment_record_id", payment_record_id);
        jsonObject.addProperty("payer", payer);

        jsonString = jsonObject.toString();

        return jsonString;

    }


}
