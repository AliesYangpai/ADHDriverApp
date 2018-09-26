package com.adhdriver.work.entity.driver.thirdauth;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/11/28.
 * 类描述
 * 版本
 */

public class AuthParam implements Serializable{

//    expires_in=7200
//    openid=oES9tw6bLje2oBQfihFEpQ7Oguko
//            refresh_token=4_qiYHmGAihKEuUHbp75f2g_rbSpjcXqAJJp7jVAXzEZZwiBq_MpFhUPAxr26iSUi4KExXvBFJao8Ve3F3F9tJrU2q4sxcWcYYwVrMAxmSE70
//            scope=snsapi_userinfo
//    unionid=ozphN09TsX5OKqqV1hqPI77-ibfM

    private String expires_in;
    private String openid;
    private String refresh_token;
    private String scope;
    private String unionid;
    private String access_token;


    public AuthParam() {
    }


    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    @Override
    public String toString() {
        return "AuthParam{" +
                "expires_in='" + expires_in + '\'' +
                ", openid='" + openid + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                ", scope='" + scope + '\'' +
                ", unionid='" + unionid + '\'' +
                '}';
    }
}
