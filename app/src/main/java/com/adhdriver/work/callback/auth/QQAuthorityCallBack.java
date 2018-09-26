package com.adhdriver.work.callback.auth;


import com.adhdriver.work.entity.driver.thirdauth.QQAuthBack;

/**
 * Created by Administrator on 2017/10/31.
 * 类描述  qq授权返回接口
 * 版本
 */

public interface QQAuthorityCallBack {


    void qqAuthoritySuccessAndNext(QQAuthBack qqAuthBack);

    void qqAuthorityFail();

    void qqAuthorityCancel();

}
