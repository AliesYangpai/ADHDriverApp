package com.adhdriver.work.entity.driver.pay.aliAbout;

import com.adhdriver.work.entity.driver.wallet.AliAuthRespose;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/7.
 * 类描述   第一层 授权信息
 * 版本
 */

public class AliAuthInfo implements Serializable {


    private AliAuthRespose alipay_user_userinfo_share_response;
    private String sign;


    public AliAuthInfo() {
    }


    public AliAuthRespose getAlipay_user_userinfo_share_response() {
        return alipay_user_userinfo_share_response;
    }

    public void setAlipay_user_userinfo_share_response(AliAuthRespose alipay_user_userinfo_share_response) {
        this.alipay_user_userinfo_share_response = alipay_user_userinfo_share_response;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "AliAuthInfo{" +
                "alipay_user_userinfo_share_response=" + alipay_user_userinfo_share_response +
                ", sign='" + sign + '\'' +
                '}';
    }
}
