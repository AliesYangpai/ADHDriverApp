package com.adhdriver.work.entity.driver;


import com.adhdriver.work.entity.driver.vehicle.LogonVehicle;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/27.
 * 类描述 司机表
 * 版本
 */

public class Driver extends DataSupport implements Serializable {





//      "driver": {
//        "driver_id": 0,
//                "vehicle_no": "string",
//                "user_id": 0,
//                "user_name": "string",
//                "vehicle_type": 0,
//                "driver_license": "string",
//                "vehicle_license": "string",
//                "driver_photo_path": "string",
//                "vehicle_photo_front_path": "string",
//                "vehicle_photo_back_path": "string",
//                "car_insurance_policy": "string",
//                "income": 0,
//                "driver_status": "string",
//                "passed": true,
//                "passed_time": "2017-06-06T06:20:02.427Z",
//                "create_time": "2017-06-06T06:20:02.427Z",
//                "certify_organization_id": 0,
//                "organization_name": "string",
//                "business_insurance": "string",
//                "car_pass": true,
//                "phone": "string",
//                "identity_card_no": "string",
//                "unique_code": "string",
//                "business_name": "string",
//                "vehicle_type_name": "string",
//                "vehicle_length": 0,
//                "vehicle_weight": 0,
//                "vehicle_width": 0,
//                "vehicle_height": 0,
//                "vehicle_stere": 0,
//                "validity": "string",
//                "assign": true
//    }

    private int id; //自增id
    private String driver_id;//司机信息Id
    private String vehicle_no;//    车牌号 ,
    private String user_id;//    用户ID ,
    private String user_name;//    用户名 ,
    private String vehicle_type;//    车型 ,
    private String driver_license;//    驾驶证图片地址 ,
    private String vehicle_license;//    行驶证图片地址 ,
    private String driver_photo_path;//    司机照片地址 ,
    private String vehicle_photo_front_path;//    车辆照片(前方)地址 ,
    private String vehicle_photo_back_path;//    车辆照片(背后)地址 ,
    private String car_insurance_policy;//    车辆保险单地址 ,
    private String income;//    收益 ,
    private String driver_status;//    司机状态 ,
    private String passed;//    考试通过 ,
    private String passed_time;//    通过时间 ,
    private String create_time;//  创建时间 ,
    private String certify_organization_id;//    发证组织 ,
    private String organization_name;//    发证组织名称 ,
    private String business_insurance;
    private String car_pass;
    private String phone;
    private String identity_card_no;//    身份证号 ,
    private String unique_code;//    认证码 ,
    private String business_name;//    隶属企业中心 ,
    private String vehicle_type_name;//    车辆类型 ,
    private String vehicle_length;//    车辆长度 ,
    private String vehicle_weight;//    载重量 ,
    private String vehicle_width;//    车辆长度 ,
    private String vehicle_height;//车辆高度
    private String vehicle_stere;//载货体积
    private String validity;//司机审核状态 ,
    private String assign;//接收指派

    private float credit_points;//司机评分
    private LogonVehicle logon_vehicle;//当前登陆的车辆信息



    public Driver() {
    }


    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getVehicle_no() {
        return vehicle_no;
    }

    public void setVehicle_no(String vehicle_no) {
        this.vehicle_no = vehicle_no;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getDriver_status() {
        return driver_status;
    }

    public void setDriver_status(String driver_status) {
        this.driver_status = driver_status;
    }

    public String getPassed() {
        return passed;
    }

    public void setPassed(String passed) {
        this.passed = passed;
    }

    public String getPassed_time() {
        return passed_time;
    }

    public void setPassed_time(String passed_time) {
        this.passed_time = passed_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getCertify_organization_id() {
        return certify_organization_id;
    }

    public void setCertify_organization_id(String certify_organization_id) {
        this.certify_organization_id = certify_organization_id;
    }

    public String getOrganization_name() {
        return organization_name;
    }

    public void setOrganization_name(String organization_name) {
        this.organization_name = organization_name;
    }

    public String getBusiness_insurance() {
        return business_insurance;
    }

    public void setBusiness_insurance(String business_insurance) {
        this.business_insurance = business_insurance;
    }

    public String getCar_pass() {
        return car_pass;
    }

    public void setCar_pass(String car_pass) {
        this.car_pass = car_pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdentity_card_no() {
        return identity_card_no;
    }

    public void setIdentity_card_no(String identity_card_no) {
        this.identity_card_no = identity_card_no;
    }

    public String getUnique_code() {
        return unique_code;
    }

    public void setUnique_code(String unique_code) {
        this.unique_code = unique_code;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
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

    public String getVehicle_weight() {
        return vehicle_weight;
    }

    public void setVehicle_weight(String vehicle_weight) {
        this.vehicle_weight = vehicle_weight;
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

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getAssign() {
        return assign;
    }

    public void setAssign(String assign) {
        this.assign = assign;
    }


    public LogonVehicle getLogon_vehicle() {
        return logon_vehicle;
    }

    public void setLogon_vehicle(LogonVehicle logon_vehicle) {
        this.logon_vehicle = logon_vehicle;
    }


    public float getCredit_points() {
        return credit_points;
    }

    public void setCredit_points(float credit_points) {
        this.credit_points = credit_points;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", driver_id='" + driver_id + '\'' +
                ", vehicle_no='" + vehicle_no + '\'' +
                ", user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", vehicle_type='" + vehicle_type + '\'' +
                ", driver_license='" + driver_license + '\'' +
                ", vehicle_license='" + vehicle_license + '\'' +
                ", driver_photo_path='" + driver_photo_path + '\'' +
                ", vehicle_photo_front_path='" + vehicle_photo_front_path + '\'' +
                ", vehicle_photo_back_path='" + vehicle_photo_back_path + '\'' +
                ", car_insurance_policy='" + car_insurance_policy + '\'' +
                ", income='" + income + '\'' +
                ", driver_status='" + driver_status + '\'' +
                ", passed='" + passed + '\'' +
                ", passed_time='" + passed_time + '\'' +
                ", create_time='" + create_time + '\'' +
                ", certify_organization_id='" + certify_organization_id + '\'' +
                ", organization_name='" + organization_name + '\'' +
                ", business_insurance='" + business_insurance + '\'' +
                ", car_pass='" + car_pass + '\'' +
                ", phone='" + phone + '\'' +
                ", identity_card_no='" + identity_card_no + '\'' +
                ", unique_code='" + unique_code + '\'' +
                ", business_name='" + business_name + '\'' +
                ", vehicle_type_name='" + vehicle_type_name + '\'' +
                ", vehicle_length='" + vehicle_length + '\'' +
                ", vehicle_weight='" + vehicle_weight + '\'' +
                ", vehicle_width='" + vehicle_width + '\'' +
                ", vehicle_height='" + vehicle_height + '\'' +
                ", vehicle_stere='" + vehicle_stere + '\'' +
                ", validity='" + validity + '\'' +
                ", assign='" + assign + '\'' +
                ", credit_points=" + credit_points +
                ", logon_vehicle=" + logon_vehicle +
                '}';
    }
}
