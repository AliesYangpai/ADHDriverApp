package com.adhdriver.work.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/22.
 * 类描述
 * 版本
 */

public class UdpEntity implements Serializable {

    private String UserId ;
    private String Token ;
    private int Type ;
    private String Content ;
    private String CreateTime;

    public UdpEntity() {
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    @Override
    public String toString() {
        return "UdpEntity{" +
                "UserId='" + UserId + '\'' +
                ", Token='" + Token + '\'' +
                ", Type=" + Type +
                ", Content='" + Content + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                '}';
    }
}
