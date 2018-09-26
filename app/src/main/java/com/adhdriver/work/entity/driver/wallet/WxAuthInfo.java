package com.adhdriver.work.entity.driver.wallet;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/7.
 * 类描述
 * 版本
 */

public class WxAuthInfo implements Serializable {



    private String city;
    private String nickname;
    private String openid;
    private String unionid;
    private String headimgurl;


    public WxAuthInfo() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }


    @Override
    public String toString() {
        return "WxAuthInfo{" +
                "city='" + city + '\'' +
                ", nickname='" + nickname + '\'' +
                ", openid='" + openid + '\'' +
                ", unionid='" + unionid + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                '}';
    }
}
