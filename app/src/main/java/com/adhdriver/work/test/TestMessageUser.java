package com.adhdriver.work.test;

/**
 * Created by Administrator on 2018/1/15.
 * 类描述
 * 版本
 */

public class TestMessageUser {

    private String UserId ;
    private String Phone ;
    private String EquipmentNo ;
    private String ToUserId;
    private String ToUserIP ;
    private String ToUserPort ;
    private String ToEquipmentOnline ;
    private int Type ;
    private String Content ;
    private String CreateTime ;


    public TestMessageUser() {
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEquipmentNo() {
        return EquipmentNo;
    }

    public void setEquipmentNo(String equipmentNo) {
        EquipmentNo = equipmentNo;
    }

    public String getToUserId() {
        return ToUserId;
    }

    public void setToUserId(String toUserId) {
        ToUserId = toUserId;
    }

    public String getToUserIP() {
        return ToUserIP;
    }

    public void setToUserIP(String toUserIP) {
        ToUserIP = toUserIP;
    }

    public String getToUserPort() {
        return ToUserPort;
    }

    public void setToUserPort(String toUserPort) {
        ToUserPort = toUserPort;
    }

    public String getToEquipmentOnline() {
        return ToEquipmentOnline;
    }

    public void setToEquipmentOnline(String toEquipmentOnline) {
        ToEquipmentOnline = toEquipmentOnline;
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
        return "TestMessageUser{" +
                "UserId='" + UserId + '\'' +
                ", Phone='" + Phone + '\'' +
                ", EquipmentNo='" + EquipmentNo + '\'' +
                ", ToUserId='" + ToUserId + '\'' +
                ", ToUserIP='" + ToUserIP + '\'' +
                ", ToUserPort='" + ToUserPort + '\'' +
                ", ToEquipmentOnline='" + ToEquipmentOnline + '\'' +
                ", Type=" + Type +
                ", Content='" + Content + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                '}';
    }
}
