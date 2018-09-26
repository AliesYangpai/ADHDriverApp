package com.adhdriver.work.method;

import com.adhdriver.work.listener.OnDataBackListener;

/**
 * Created by Administrator on 2018/1/26.
 * 类描述
 * 版本
 */

public interface IVertifyCode {






    /**
     * 获取短信验证码
     * @param url
     * @param phone
     * @param options
     * @param onDataBackListener
     */
    void doSendVertifyCode(String url,
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
    void doConfirmVertifyCode(String url, String phone, String pass_code, OnDataBackListener onDataBackListener);




}
