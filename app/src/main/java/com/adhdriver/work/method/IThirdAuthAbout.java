package com.adhdriver.work.method;

import com.adhdriver.work.entity.driver.thirdauth.AuthParam;
import com.adhdriver.work.listener.OnDataBackListener;
import com.google.gson.JsonElement;

/**
 * Created by Administrator on 2017/11/29.
 * 类描述  绑定授权的相关方法
 * 版本
 */

public interface IThirdAuthAbout {


    /**
     * 获取授权列表
     * @param url
     * @param onDataBackListener
     */
    void doGetBinds(String url, OnDataBackListener onDataBackListener);


    /**
     * 验证QQ是否授权
     * @param url
     * @param code
     * @param auth_type
     * @param unionid
     * @param openid
     * @param access_token
     * @param onDataBackListener
     */
    void doValidAuthForQQ(String url,
                          String code,
                          String auth_type,
                          String unionid,
                          String openid,
                          String access_token,
                          OnDataBackListener onDataBackListener);



    /**
     * 验证wx是否授权
     * @param url
     * @param code
     * @param auth_type
     * @param onDataBackListener
     */
    void doValidAuthForWx(String url,
                          String code,
                          String auth_type,
                          OnDataBackListener onDataBackListener);


    /**
     * 执行微信绑定
     * @param url
     * @param auth_type
     * @param authParam
     * @param onDataBackListener
     */
    void doBindForWx(String url,
                     String auth_type,
                     AuthParam authParam,
                     OnDataBackListener onDataBackListener);





    /**
     * 执行QQ绑定
     * @param url
     * @param auth_type
     * @param authParam
     * @param onDataBackListener
     */
    void doBindForQQ(String url,
                     String auth_type,
                     AuthParam authParam,
                     OnDataBackListener onDataBackListener);




}
