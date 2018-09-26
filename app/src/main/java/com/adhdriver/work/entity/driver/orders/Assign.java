package com.adhdriver.work.entity.driver.orders;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/13.
 * 类描述 车辆订单类型（我的指派、我的抢单、我的竞价）
 * 版本
 */

public class Assign  implements Serializable {



//        "driver_id": 0,
//                "method": "Grab",
//                "create_time": "2017-09-06T09:31:19.438Z",
//                "plate_number": "string",
//                "user_name": "string",
//                "phone": "string"







    private String driver_id;
    private String method; // 我的指派、我的抢单、我的竞价
    private String driver_vehicle_id;//
    private String plate_number;//车牌号
    private String user_name;//用户名
    private String phone;//司机电话



    public Assign() {
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

    public String getDriver_vehicle_id() {
        return driver_vehicle_id;
    }

    public void setDriver_vehicle_id(String driver_vehicle_id) {
        this.driver_vehicle_id = driver_vehicle_id;
    }

    @Override
    public String toString() {
        return "Assign{" +
                "driver_id='" + driver_id + '\'' +
                ", method='" + method + '\'' +
                ", driver_vehicle_id='" + driver_vehicle_id + '\'' +
                ", plate_number='" + plate_number + '\'' +
                ", user_name='" + user_name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
