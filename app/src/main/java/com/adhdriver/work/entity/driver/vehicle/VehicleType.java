package com.adhdriver.work.entity.driver.vehicle;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/27.
 * 类描述  车辆类型信息
 * 版本
 */

public class VehicleType extends DataSupport implements Serializable {






    //    {
//        "vehicle_type_id": 0,
//            "vehicle_brand": "string",
//            "vehicle_type_name": "string",
//            "vehicle_length": 0,
//            "vehicle_weight": 0,
//            "vehicle_height": 0,
//            "vehicle_width": 0,
//            "vehicle_stere": 0,
//            "quality_deposit": 0
//    }


    private int id; //自增id


    private String vehicle_type_id;//车型Id
    private String vehicle_brand;//车辆品牌
    private String vehicle_type_name;//车型名称
    private String vehicle_length;//车辆长度
    private String vehicle_weight;//车辆重量
    private String vehicle_height;//车辆高度
    private String vehicle_width;//车辆宽度
    private String vehicle_stere;//车辆载货立方
    private String quality_deposit;//质保金











    public VehicleType() {
    }


    public String getVehicle_type_id() {
        return vehicle_type_id;
    }

    public void setVehicle_type_id(String vehicle_type_id) {
        this.vehicle_type_id = vehicle_type_id;
    }

    public String getVehicle_brand() {
        return vehicle_brand;
    }

    public void setVehicle_brand(String vehicle_brand) {
        this.vehicle_brand = vehicle_brand;
    }

    public String getVehicle_type_name() {
        return vehicle_type_name;
    }

    public void setVehicle_type_name(String vehicle_type_name) {
        this.vehicle_type_name = vehicle_type_name;
    }

    public String getVehicle_length() {
        return vehicle_length;
    }

    public void setVehicle_length(String vehicle_length) {
        this.vehicle_length = vehicle_length;
    }

    public String getVehicle_width() {
        return vehicle_width;
    }

    public void setVehicle_width(String vehicle_width) {
        this.vehicle_width = vehicle_width;
    }

    public String getVehicle_height() {
        return vehicle_height;
    }

    public void setVehicle_height(String vehicle_height) {
        this.vehicle_height = vehicle_height;
    }

    public String getVehicle_weight() {
        return vehicle_weight;
    }

    public void setVehicle_weight(String vehicle_weight) {
        this.vehicle_weight = vehicle_weight;
    }

    public String getVehicle_stere() {
        return vehicle_stere;
    }

    public void setVehicle_stere(String vehicle_stere) {
        this.vehicle_stere = vehicle_stere;
    }


    public String getQuality_deposit() {
        return quality_deposit;
    }

    public void setQuality_deposit(String quality_deposit) {
        this.quality_deposit = quality_deposit;
    }


    @Override
    public String toString() {
        return "VehicleType{" +
                "vehicle_type_id='" + vehicle_type_id + '\'' +
                ", vehicle_brand='" + vehicle_brand + '\'' +
                ", vehicle_type_name='" + vehicle_type_name + '\'' +
                ", vehicle_length='" + vehicle_length + '\'' +
                ", vehicle_weight='" + vehicle_weight + '\'' +
                ", vehicle_height='" + vehicle_height + '\'' +
                ", vehicle_width='" + vehicle_width + '\'' +
                ", vehicle_stere='" + vehicle_stere + '\'' +
                ", quality_deposit='" + quality_deposit + '\'' +
                '}';
    }
}

