package com.adhdriver.work.entity.business;


import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/27.
 * 类描述 企业表
 * 版本
 */

public class Business extends DataSupport implements Serializable {





//     "business": {
//        "business_id": 0,
//                "business_name": "string",
//                "user_id": 0,
//                "business_address": "string",
//                "business_license": "string",
//                "business_door_head": "string",
//                "business_unique_code": "string",
//                "business_income": 0,
//                "corporation": "string",
//                "corporation_identity": "string",
//                "hand_identify_path": "string",
//                "front_identify_path": "string",
//                "reverse_identify_path": "string",
//                "create_time": "2017-08-31T06:39:50.610Z"
//    }







    private int id;//自增id
    private String business_id;//企业id
    private String business_name;//企业名称
    private String user_id;//用户id
    private String business_address;//企业地址
    private String business_license;//企业营业执照照片地址
    private String business_door_head;//企业门口照片
    private String business_unique_code;   //唯一码

    private String business_income ;//企业收入
    private String corporation;//法人姓名
    private String corporation_identity;//法人身份证号码
    private String hand_identify_path;//法人手持身份证
    private String front_identify_path; //法人身份证正面
    private String reverse_identify_path;//法人身份证反面







    public Business() {
    }



    public String getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getBusiness_address() {
        return business_address;
    }

    public void setBusiness_address(String business_address) {
        this.business_address = business_address;
    }

    public String getBusiness_license() {
        return business_license;
    }

    public void setBusiness_license(String business_license) {
        this.business_license = business_license;
    }

    public String getBusiness_door_head() {
        return business_door_head;
    }

    public void setBusiness_door_head(String business_door_head) {
        this.business_door_head = business_door_head;
    }

    public String getBusiness_unique_code() {
        return business_unique_code;
    }

    public void setBusiness_unique_code(String business_unique_code) {
        this.business_unique_code = business_unique_code;
    }

    public String getBusiness_income() {
        return business_income;
    }

    public void setBusiness_income(String business_income) {
        this.business_income = business_income;
    }

    public String getCorporation() {
        return corporation;
    }

    public void setCorporation(String corporation) {
        this.corporation = corporation;
    }

    public String getCorporation_identity() {
        return corporation_identity;
    }

    public void setCorporation_identity(String corporation_identity) {
        this.corporation_identity = corporation_identity;
    }

    public String getHand_identify_path() {
        return hand_identify_path;
    }

    public void setHand_identify_path(String hand_identify_path) {
        this.hand_identify_path = hand_identify_path;
    }

    public String getFront_identify_path() {
        return front_identify_path;
    }

    public void setFront_identify_path(String front_identify_path) {
        this.front_identify_path = front_identify_path;
    }

    public String getReverse_identify_path() {
        return reverse_identify_path;
    }

    public void setReverse_identify_path(String reverse_identify_path) {
        this.reverse_identify_path = reverse_identify_path;
    }

    @Override
    public String toString() {
        return "Business{" +
                "business_id='" + business_id + '\'' +
                ", business_name='" + business_name + '\'' +
                ", user_id='" + user_id + '\'' +
                ", business_address='" + business_address + '\'' +
                ", business_license='" + business_license + '\'' +
                ", business_door_head='" + business_door_head + '\'' +
                ", business_unique_code='" + business_unique_code + '\'' +
                ", business_income='" + business_income + '\'' +
                ", corporation='" + corporation + '\'' +
                ", corporation_identity='" + corporation_identity + '\'' +
                ", hand_identify_path='" + hand_identify_path + '\'' +
                ", front_identify_path='" + front_identify_path + '\'' +
                ", reverse_identify_path='" + reverse_identify_path + '\'' +
                '}';
    }
}
