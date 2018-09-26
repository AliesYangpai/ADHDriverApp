package com.adhdriver.work.entity.driver.thirdauth;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/11/28.
 * 类描述  授权状态实体类
 * 版本
 */

public class AuthState implements Serializable{

//    {
//        "user_auth_id": 0,
//            "user_id": 0,
//            "auth_type": "Weixin",
//            "union_id": "string",
//            "parameters": {},
//        "auth_info": "string",
//            "token": {}
//    }

    private String user_auth_id;
    private String user_id;
    private String auth_type;
    private String union_id;
    private String parameters;
    private String auth_info;

    public AuthState() {
    }

    public String getUser_auth_id() {
        return user_auth_id;
    }

    public void setUser_auth_id(String user_auth_id) {
        this.user_auth_id = user_auth_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAuth_type() {
        return auth_type;
    }

    public void setAuth_type(String auth_type) {
        this.auth_type = auth_type;
    }

    public String getUnion_id() {
        return union_id;
    }

    public void setUnion_id(String union_id) {
        this.union_id = union_id;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getAuth_info() {
        return auth_info;
    }

    public void setAuth_info(String auth_info) {
        this.auth_info = auth_info;
    }

    @Override
    public String toString() {
        return "AuthState{" +
                "user_auth_id='" + user_auth_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", auth_type='" + auth_type + '\'' +
                ", union_id='" + union_id + '\'' +
                ", parameters='" + parameters + '\'' +
                ", auth_info='" + auth_info + '\'' +
                '}';
    }
}
