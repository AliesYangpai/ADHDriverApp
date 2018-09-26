package com.adhdriver.work.entity.driver.vehicle;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/28.
 * 类描述  指定车辆的种类信息
 * 版本
 */

public class VehicleCategory implements Serializable{


//    {
//        "vehicle_category_id": 0,
//            "category_name": "string",
//            "vehicle_type_id": 0
//    }


    private String vehicle_category_id;
    private String category_display_name;//车辆种类描述名称
    private String category_name;
    private String vehicle_type_id;

    public VehicleCategory() {
    }

    public String getVehicle_category_id() {
        return vehicle_category_id;
    }

    public void setVehicle_category_id(String vehicle_category_id) {
        this.vehicle_category_id = vehicle_category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getVehicle_type_id() {
        return vehicle_type_id;
    }

    public void setVehicle_type_id(String vehicle_type_id) {
        this.vehicle_type_id = vehicle_type_id;
    }


    public String getCategory_display_name() {
        return category_display_name;
    }

    public void setCategory_display_name(String category_display_name) {
        this.category_display_name = category_display_name;
    }

    @Override
    public String toString() {
        return "VehicleCategory{" +
                "vehicle_category_id='" + vehicle_category_id + '\'' +
                ", category_display_name='" + category_display_name + '\'' +
                ", category_name='" + category_name + '\'' +
                ", vehicle_type_id='" + vehicle_type_id + '\'' +
                '}';
    }
}
