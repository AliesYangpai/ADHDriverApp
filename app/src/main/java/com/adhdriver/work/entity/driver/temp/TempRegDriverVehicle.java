package com.adhdriver.work.entity.driver.temp;

/**
 * Created by Administrator on 2017/12/19.
 * 类描述  提交车辆、司机信息的缓存类
 * 版本
 */

public class TempRegDriverVehicle {


    private String vehicle_id;
    private String vehicle_no;
    private String vehicle_type;
    private String driver_license; //驾驶证
    private String vehicle_license; //行驶证
    private String driver_photo_path;//司机照片
    private String vehicle_photo_front_path;//车前照片
    private String vehicle_photo_back_path;//车后照片
    private String car_insurance_policy;//车辆保险单
    private String business_insurance;//商业险单，选填



    private String category_name;//['Box', 'Slab', 'Goalan'] 车辆外形
    private String vehicle_dead_weight;//车辆载重量 ,
    private String vehicle_stere;//车辆载货立方


    public TempRegDriverVehicle() {
    }

    public String getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(String vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getVehicle_no() {
        return vehicle_no;
    }

    public void setVehicle_no(String vehicle_no) {
        this.vehicle_no = vehicle_no;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getDriver_license() {
        return driver_license;
    }

    public void setDriver_license(String driver_license) {
        this.driver_license = driver_license;
    }

    public String getVehicle_license() {
        return vehicle_license;
    }

    public void setVehicle_license(String vehicle_license) {
        this.vehicle_license = vehicle_license;
    }

    public String getDriver_photo_path() {
        return driver_photo_path;
    }

    public void setDriver_photo_path(String driver_photo_path) {
        this.driver_photo_path = driver_photo_path;
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

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getVehicle_dead_weight() {
        return vehicle_dead_weight;
    }

    public void setVehicle_dead_weight(String vehicle_dead_weight) {
        this.vehicle_dead_weight = vehicle_dead_weight;
    }

    public String getVehicle_stere() {
        return vehicle_stere;
    }

    public void setVehicle_stere(String vehicle_stere) {
        this.vehicle_stere = vehicle_stere;
    }


    @Override
    public String toString() {
        return "TempRegDriverVehicle{" +
                "vehicle_id='" + vehicle_id + '\'' +
                ", vehicle_no='" + vehicle_no + '\'' +
                ", vehicle_type='" + vehicle_type + '\'' +
                ", driver_license='" + driver_license + '\'' +
                ", vehicle_license='" + vehicle_license + '\'' +
                ", driver_photo_path='" + driver_photo_path + '\'' +
                ", vehicle_photo_front_path='" + vehicle_photo_front_path + '\'' +
                ", vehicle_photo_back_path='" + vehicle_photo_back_path + '\'' +
                ", car_insurance_policy='" + car_insurance_policy + '\'' +
                ", business_insurance='" + business_insurance + '\'' +
                ", category_name='" + category_name + '\'' +
                ", vehicle_dead_weight='" + vehicle_dead_weight + '\'' +
                ", vehicle_stere='" + vehicle_stere + '\'' +
                '}';
    }
}
