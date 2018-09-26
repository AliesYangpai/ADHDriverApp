package com.adhdriver.work.method;

import com.adhdriver.work.listener.OnDataBackListener;

/**
 * Created by Alie on 2017/11/6.
 * 类描述  driver相关
 * 版本
 */

public interface IDriverAbout {

    /**
     * 获取用户信息
     */
    void doGetUserInfo(String url, OnDataBackListener onDataBackListener);


    /**
     * 获取用户信息
     */
    void doGetUserInfoInFresh(String url, OnDataBackListener onDataBackListener);


    /**
     * 获取app版本信息
     *
     * @param onDataBackListener
     */
    void doGetAppInfo(String url, String os_type, String os_version, String model, String device_id, OnDataBackListener onDataBackListener);


    /**
     * 手动设置推送开
     *
     * @param onDataBackListener
     */
    void doOpenPush(String url,
                    String os_type,
                    String os_version,
                    String model,
                    String device_id,
                    String push_type,
                    String push_token,
                    boolean quiet_mode, OnDataBackListener onDataBackListener);


    /**
     * 手动设置推送关
     *
     * @param onDataBackListener
     */
    void doClosePush(String url,
                     String os_type,
                     String os_version,
                     String model,
                     String device_id,
                     String push_type,
                     String push_token,
                     boolean quiet_mode, OnDataBackListener onDataBackListener);


    /**
     * 获取我的钱包信息
     *
     * @param onDataBackListener
     */
    void doGetMyWalletInfo(String url, OnDataBackListener onDataBackListener);


    /**
     * 修改登陆密码
     * @param url
     * @param password
     * @param new_password
     * @param onDataBackListener
     */
    void doChangeLoginPass(String url, String password, String new_password, OnDataBackListener onDataBackListener);


    /**
     * 修改提现密码
     * @param url
     * @param oldPass
     * @param newPass
     * @param onDataBackListener
     */
    void doChangeDepositePass(String url, String oldPass, String newPass, OnDataBackListener onDataBackListener);


    /**
     * 获取提现验证码
     * @param url
     * @param phone
     * @param options
     * @param onDataBackListener
     */
    void doGetDepositeVertifyCode(String url, String phone, String options, OnDataBackListener onDataBackListener);


    /**
     * 验证验证码
     * @param url
     * @param phone
     * @param pass_code
     * @param onDataBackListener
     */
    void doConfirmVertifyCode(String url, String phone, String pass_code, OnDataBackListener onDataBackListener);


    /**
     * 重置支付密码
     * @param url
     * @param last_card_no
     * @param new_pay_password
     * @param pass_code
     * @param onDataBackListener
     */
    void doResetDepositPass(String url, String last_card_no, String new_pay_password, String pass_code, OnDataBackListener onDataBackListener);


    /**
     * 登陆方法
     * @param url
     * @param phone
     * @param password
     * @param onDataBackListener
     */
    void doLogin(String url, String phone, String password, OnDataBackListener onDataBackListener);


    /**
     * 获取信用分
     * @param url
     * @param onDataBackListener
     */
    void doGetCreditScore(String url, OnDataBackListener onDataBackListener);

    



    void doUploadAvatar(String url,OnDataBackListener onDataBackListener);


}
