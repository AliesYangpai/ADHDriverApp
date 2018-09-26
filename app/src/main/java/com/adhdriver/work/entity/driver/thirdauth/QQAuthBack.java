package com.adhdriver.work.entity.driver.thirdauth;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/31.
 * 类描述  qq授权返回
 * 版本
 */

public class QQAuthBack implements Serializable{
//    {"ret":0,
//            "openid":"0F38526561099544514B513B0FD45B47",
//            "access_token":"7EA4FB0B1D6BBA737EAE498B11806458",
//            "pay_token":"B763FE34DF4E0DDD741164CEF094CD8F",
//            "expires_in":7776000,
//            "pf":"desktop_m_qq-10000144-android-2002-",
//            "pfkey":"8e49dcabc2c1c6c83f0b50df19a247c3",
//            "msg":"",
//            "login_cost":174,
//            "query_authority_cost":2094,
//            "authority_cost":0}


//    {
//        "ret":0,
//            "pay_token":"xxxxxxxxxxxxxxxx",
//            "pf":"openmobile_android",
//            "expires_in":"7776000",
//            "openid":"xxxxxxxxxxxxxxxxxxx",
//            "pfkey":"xxxxxxxxxxxxxxxxxxx",
//            "msg":"sucess",
//            "access_token":"xxxxxxxxxxxxxxxxxxxxx"
//    }


    private int ret;
    private String openid;
    private String access_token;
    private String expires_in;


    public QQAuthBack() {
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
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


    @Override
    public String toString() {
        return "QQAuthBack{" +
                "ret=" + ret +
                ", openid='" + openid + '\'' +
                ", access_token='" + access_token + '\'' +
                ", expires_in='" + expires_in + '\'' +
                '}';
    }
}
