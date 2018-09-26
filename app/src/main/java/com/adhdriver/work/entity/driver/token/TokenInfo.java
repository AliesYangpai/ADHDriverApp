package com.adhdriver.work.entity.driver.token;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/28.
 * 类描述  1.3版本之后（不包含1.3版本）登陆的token返回的改变
 * 版本
 */

public class TokenInfo implements Serializable{


    private String description;
    private String access_token;
    private String expires_in;

    private int processing_order_counts;
    private boolean is_joined_enterprise;


    public TokenInfo() {
    }


    public int getProcessing_order_counts() {
        return processing_order_counts;
    }

    public void setProcessing_order_counts(int processing_order_counts) {
        this.processing_order_counts = processing_order_counts;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public boolean is_joined_enterprise() {
        return is_joined_enterprise;
    }

    public void setIs_joined_enterprise(boolean is_joined_enterprise) {
        this.is_joined_enterprise = is_joined_enterprise;
    }


    @Override
    public String toString() {
        return "TokenAbout{" +
                "description='" + description + '\'' +
                ", access_token='" + access_token + '\'' +
                ", expires_in='" + expires_in + '\'' +
                ", processing_order_counts=" + processing_order_counts +
                ", is_joined_enterprise=" + is_joined_enterprise +
                '}';
    }
}
