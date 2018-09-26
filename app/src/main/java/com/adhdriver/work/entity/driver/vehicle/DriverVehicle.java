package com.adhdriver.work.entity.driver.vehicle;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/28.
 * 类描述  司机名下车辆详情信息
 * 版本
 */

public class DriverVehicle implements Serializable {


//    {
//        "driver_vehicle_id": 0,
//            "driver_id": 0,
//            "vehicle_id": 0,
//            "vehicle_status": "string",
//            "vehicle_license": "string",
//            "vehicle_photo_front_path": "string",
//            "vehicle_photo_back_path": "string",
//            "car_insurance_policy": "string",
//            "business_insurance": "string",
//            "plate_number": "string",
//            "vehicle_brand": "string",
//            "vehicle_type_name": "string",
//            "vehicle_dead_weight": 0,
//            "vehicle_length": 0,
//            "vehicle_width": 0,
//            "vehicle_height": 0,
//            "vehicle_stere": 0,
//            "create_time": "2017-09-28T02:20:59.709Z",
//            "refuse_reason": "string",
//            "quality_deposit": 0,
//            "is_joined_enterprise": true,
//            "category_name": "string"
//    }

    private int driver_vehicle_id;  //
    private int driver_id;
    private String vehicle_id;
    private String vehicle_status;//车辆状态


    private String vehicle_license; //行驶证图片地址
    private String vehicle_photo_front_path;//车辆照片(前方)地址 ,
    private String vehicle_photo_back_path;//车辆照片(背后)地址 ,
    private String car_insurance_policy;//车辆保险单地址 ,
    private String business_insurance;//商业保险地址 ,


    private String plate_number; //车辆号
    private String vehicle_brand;//车辆品牌 ,

    private String vehicle_type_name; //车型名称 ,
    private String vehicle_dead_weight;//车辆自重

    private String vehicle_weight; //【已废弃】
    private String vehicle_length;
    private String vehicle_width;
    private String vehicle_height;


    private String vehicle_stere;//车辆载货立方


    private String create_time;
    private String refuse_reason; //审核被拒绝原因
    private String quality_deposit;//质保金


    private boolean is_joined_enterprise;//该车辆是否加入企业
    private String category_name;//['Box', 'Slab', 'Goalan']//车辆外形

    public DriverVehicle() {
    }

    public int getDriver_vehicle_id() {
        return driver_vehicle_id;
    }

    public void setDriver_vehicle_id(int driver_vehicle_id) {
        this.driver_vehicle_id = driver_vehicle_id;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public String getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(String vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getVehicle_status() {
        return vehicle_status;
    }

    public void setVehicle_status(String vehicle_status) {
        this.vehicle_status = vehicle_status;
    }

    public String getVehicle_license() {
        return vehicle_license;
    }

    public void setVehicle_license(String vehicle_license) {
        this.vehicle_license = vehicle_license;
    }

    public String getVehicle_photo_front_path() {
        return vehicle_photo_front_path;
    }

    public void setVehicle_photo_front_path(String vehicle_photo_front_path) {
        this.vehicle_photo_front_path = vehicle_photo_front_path;
    }

    public String getVehicle_photo_back_path() {
        return vehicle_photo_back_path;
    }

    public void setVehicle_photo_back_path(String vehicle_photo_back_path) {
        this.vehicle_photo_back_path = vehicle_photo_back_path;
    }

    public String getCar_insurance_policy() {
        return car_insurance_policy;
    }

    public void setCar_insurance_policy(String car_insurance_policy) {
        this.car_insurance_policy = car_insurance_policy;
    }

    public String getBusiness_insurance() {
        return business_insurance;
    }

    public void setBusiness_insurance(String business_insurance) {
        this.business_insurance = business_insurance;
    }

    public String getPlate_number() {
        return plate_number;
    }

    public void setPlate_number(String plate_number) {
        this.plate_number = plate_number;
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

    public String getVehicle_weight() {
        return vehicle_weight;
    }

    public void setVehicle_weight(String vehicle_weight) {
        this.vehicle_weight = vehicle_weight;
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

    public String getVehicle_stere() {
        return vehicle_stere;
    }

    public void setVehicle_stere(String vehicle_stere) {
        this.vehicle_stere = vehicle_stere;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getRefuse_reason() {
        return refuse_reason;
    }

    public void setRefuse_reason(String refuse_reason) {
        this.refuse_reason = refuse_reason;
    }


    public boolean is_joined_enterprise() {
        return is_joined_enterprise;
    }

    public void setIs_joined_enterprise(boolean is_joined_enterprise) {
        this.is_joined_enterprise = is_joined_enterprise;
    }


    public String getVehicle_dead_weight() {
        return vehicle_dead_weight;
    }

    public void setVehicle_dead_weight(String vehicle_dead_weight) {
        this.vehicle_dead_weight = vehicle_dead_weight;
    }

    public String getQuality_deposit() {
        return quality_deposit;
    }

    public void setQuality_deposit(String quality_deposit) {
        this.quality_deposit = quality_deposit;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }


    @Override
    public String toString() {
        return "DriverVehicle{" +
                "driver_vehicle_id=" + driver_vehicle_id +
                ", driver_id=" + driver_id +
                ", vehicle_id='" + vehicle_id + '\'' +
                ", vehicle_status='" + vehicle_status + '\'' +
                ", vehicle_license='" + vehicle_license + '\'' +
                ", vehicle_photo_front_path='" + vehicle_photo_front_path + '\'' +
                ", vehicle_photo_back_path='" + vehicle_photo_back_path + '\'' +
                ", car_insurance_policy='" + car_insurance_policy + '\'' +
                ", business_insurance='" + business_insurance + '\'' +
                ", plate_number='" + plate_number + '\'' +
                ", vehicle_brand='" + vehicle_brand + '\'' +
                ", vehicle_type_name='" + vehicle_type_name + '\'' +
                ", vehicle_dead_weight='" + vehicle_dead_weight + '\'' +
                ", vehicle_weight='" + vehicle_weight + '\'' +
                ", vehicle_length='" + vehicle_length + '\'' +
                ", vehicle_width='" + vehicle_width + '\'' +
                ", vehicle_height='" + vehicle_height + '\'' +
                ", vehicle_stere='" + vehicle_stere + '\'' +
                ", create_time='" + create_time + '\'' +
                ", refuse_reason='" + refuse_reason + '\'' +
                ", quality_deposit='" + quality_deposit + '\'' +
                ", is_joined_enterprise=" + is_joined_enterprise +
                ", category_name='" + category_name + '\'' +
                '}';
    }
}
