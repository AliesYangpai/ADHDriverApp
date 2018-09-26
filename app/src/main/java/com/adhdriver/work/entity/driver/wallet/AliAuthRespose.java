package com.adhdriver.work.entity.driver.wallet;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/7.
 * 类描述  第二层核心授权信息
 * 版本
 */

public class AliAuthRespose implements Serializable{

    private String alipay_user_id;
    private String nick_name;
    private String avatar;


    public AliAuthRespose() {
    }


    public String getAlipay_user_id() {
        return alipay_user_id;
    }

    public void setAlipay_user_id(String alipay_user_id) {
        this.alipay_user_id = alipay_user_id;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "AliAuthRespose{" +
                "alipay_user_id='" + alipay_user_id + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
