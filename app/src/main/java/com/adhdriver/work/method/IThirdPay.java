package com.adhdriver.work.method;

import com.adhdriver.work.constant.ConstTag;
import com.adhdriver.work.listener.OnDataBackListener;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Created by Administrator on 2018/1/10.
 * 类描述    支付宝的相关接口，包含获取信息，验签。等等
 * 版本
 */

public interface IThirdPay {


    /**
     * ====================================== ===================支付宝=================== ======================================
     */

    /**
     * 支付宝授权
     *
     * @param url
     * @param channel_id
     * @param device_id
     * @param os_type
     * @param device_type
     * @param parameters
     * @param onDataBackListener
     */
    void doAliPreAppAuthLogin(String url,
                              String channel_id,
                              String device_id,
                              String os_type,
                              String device_type,
                              JsonObject parameters, OnDataBackListener onDataBackListener);//获取预授权登陆信息


    /**
     * 支付宝
     * 获取支付宝信息
     */
    void doAliGetAuthLoginInfo(String url,
                               String channel_id,
                               String pay_password,
                               String device_id,
                               String os_type,
                               String device_type,
                               String auth_code,
                               JsonObject parameters, OnDataBackListener onDataBackListener);





    /**
     * 支付宝
     * 获取支付宝信息
     */
    void doWxGetAuthLoginInfo(String url,
                               String channel_id,
                               String pay_password,
                               String device_id,
                               String os_type,
                               String device_type,
                               String auth_code,
                               JsonObject parameters, OnDataBackListener onDataBackListener);



    /**
     * 支付宝
     * 执行阿里支付验签 (发生在调用支付接口之后)
     * 【注意 这个是支付验证，而不是 扫码支付验证】
     *
     * @param url
     *
     *  @param resultInfo;
     *   @param app_type
     * @param os_type
     * @param payer
     */
    void doAliCheckAliPayResultInfo(String url,
                                    String resultInfo,
                                    String app_type,
                                    String os_type,
                                    String payer, OnDataBackListener onDataBackListener);


    /**
     * ====================================== ===================微信=================== ======================================
     */

    /**
     * 微信验签
     *
     * @param url
     */
    void doWxCheckWxPayResultInfo(String url,
                                  String app_type,
                                  String os_type,
                                  String payment_record_id,
                                  String payer, OnDataBackListener onDataBackListener);


}
