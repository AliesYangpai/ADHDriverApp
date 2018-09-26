package com.adhdriver.work.method;

import com.adhdriver.work.listener.OnDataBackListener;

/**
 * Created by Administrator on 2017/12/11.
 * 类描述  注册方法
 * 版本
 */

public interface IRegister {

    /**
     * 获取当前司机的注册状态
     * @param url
     * @param accessToken
     * @param onDataBackListener
     */
    void doGetRegStatue(String url, String accessToken, OnDataBackListener onDataBackListener);


    /**
     * 注册用户信息
     * @param url
     * @param accessToken
     * @param userName
     * @param user_identity_card_no
     * @param pathFront
     * @param pathBack
     * @param inviteCode
     */
    void doCompleteUser(String url,
                        String accessToken,
                        String userName,
                        String user_identity_card_no,
                        String pathFront,
                        String pathBack,
                        String inviteCode, OnDataBackListener onDataBackListener);


    /**
     * 获取短信验证码
     * @param url
     * @param phone
     * @param options
     * @param onDataBackListener
     */
    void doGetPhoneCode(String url,
                        String phone,
                        String options,
                        OnDataBackListener onDataBackListener );


    /**
     * 验证验证码
     * @param url
     * @param phone
     * @param pass_code
     * @param onDataBackListener
     */
    void doConfirmVertifyCode(String url,
                              String phone,
                              String pass_code,
                              OnDataBackListener onDataBackListener);


    void doRegister(String url,
                    String phone,
                    String pass,
                    String code,
                    String defaultUserName,
                    OnDataBackListener onDataBackListener);


    /**
     * 登陆时获取token
     * @param url
     * @param user_phone
     * @param user_password
     * @param onDataBackListener
     */
    void doRegGetToken(String url,
                       String user_phone,
                       String user_password,
                       OnDataBackListener onDataBackListener);
}
