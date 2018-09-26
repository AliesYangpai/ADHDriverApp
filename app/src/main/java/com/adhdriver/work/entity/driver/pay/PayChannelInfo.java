package com.adhdriver.work.entity.driver.pay;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/25.
 * 类描述  支付渠道info
 * 版本
 */

public class PayChannelInfo implements Serializable{

    private String  channel_id;
    private String  name;
    private String  description;
    private String  logo;
    private String  tips;
    private String  balance;
    private String  limit;


    public PayChannelInfo() {
    }


    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }
}
