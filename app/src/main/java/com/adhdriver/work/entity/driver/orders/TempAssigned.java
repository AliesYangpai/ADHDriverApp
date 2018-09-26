package com.adhdriver.work.entity.driver.orders;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/4.
 * 类描述 整车一口价订单的临时指派信息
 * 版本
 */

public class TempAssigned implements Serializable{



    private String driver_id;
    private String method; // 我的指派、我的抢单、我的竞价

    private String plate_number;//车牌号
    private String user_name;//用户名
    private String phone;//司机电话



    public TempAssigned() {
    }


    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPlate_number() {
        return plate_number;
    }

    public void setPlate_number(String plate_number) {
        this.plate_number = plate_number;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public String toString() {
        return "TempAssigned{" +
                "driver_id='" + driver_id + '\'' +
                ", method='" + method + '\'' +
                ", plate_number='" + plate_number + '\'' +
                ", user_name='" + user_name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
