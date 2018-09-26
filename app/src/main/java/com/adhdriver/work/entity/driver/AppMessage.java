package com.adhdriver.work.entity.driver;

import java.io.Serializable;

/**
 * Created by Alie on 2017/11/24.
 * 类描述  我的消息 是新题
 * 版本
 */

public class AppMessage implements Serializable{



//    {
//        "app_message_id": 0,
//            "user_id": 0,
//            "title": "string",
//            "message": "string",
//            "create_time": "2017-11-28T00:52:26.415Z",
//            "message_type": "string",
//            "seen": true,
//            "app_type": "string",
//            "is_pass": true
//    }


    private int app_message_id;
    private int user_id;
    private String title;
    private String message;
    private String create_time;
    private String message_type;
    private boolean seen;
    private String app_type;
    private boolean is_pass;


    public AppMessage() {
    }

    public int getApp_message_id() {
        return app_message_id;
    }

    public void setApp_message_id(int app_message_id) {
        this.app_message_id = app_message_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getMessage_type() {
        return message_type;
    }

    public void setMessage_type(String message_type) {
        this.message_type = message_type;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public String getApp_type() {
        return app_type;
    }

    public void setApp_type(String app_type) {
        this.app_type = app_type;
    }

    public boolean is_pass() {
        return is_pass;
    }

    public void setIs_pass(boolean is_pass) {
        this.is_pass = is_pass;
    }


    @Override
    public String toString() {
        return "AppMessage{" +
                "app_message_id=" + app_message_id +
                ", user_id=" + user_id +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", create_time='" + create_time + '\'' +
                ", message_type='" + message_type + '\'' +
                ", seen=" + seen +
                ", app_type='" + app_type + '\'' +
                ", is_pass=" + is_pass +
                '}';
    }
}
