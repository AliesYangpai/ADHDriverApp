package com.adhdriver.work.entity.driver.pay;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/13.
 * 类描述  获取二维码返回的 二维码实体类
 * 版本
 */

public class PayScanBack implements Serializable{

//    {
//        "channel_id": "string",
//            "token": "string",
//            "expire_time": "2017-10-13T05:02:33.386Z"
//    }


    private String channel_id;
    private String token;
    private String out_trade_no;

    public PayScanBack() {
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    @Override
    public String toString() {
        return "PayScanBack{" +
                "channel_id='" + channel_id + '\'' +
                ", token='" + token + '\'' +
                ", out_trade_no='" + out_trade_no + '\'' +
                '}';
    }
}
