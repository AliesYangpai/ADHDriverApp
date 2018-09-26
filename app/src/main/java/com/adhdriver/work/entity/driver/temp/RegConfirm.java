package com.adhdriver.work.entity.driver.temp;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/9.
 * 类描述  该实体类用于 返回用户不完全注册的判断返回
 * 版本
 */

public class RegConfirm implements Serializable{


    /**
     * 1.用户身份证号
     * 2.车辆品牌
     * 3.考试结果
     * 4.身份证认证状态
     * 5.审核状态
     */


//    { "identity_card_no": "610124198901231532", "vehicle_brand": null, "passed": false, "certification": "0", "validity": "Successful" }


//    {
//        "identity_card_no": "string",
//            "user_name": "string",
//            "vehicle_brand": "string",
//            "passed": true,
//            "certification": "string",
//            "validity": "string",
//            "driver_id": 0,
//            "certify_organization_id": 0,
//            "vehicle_no": "string",
//            "car_pass": true
//    }


    private String identity_card_no;
    private String vehicle_brand;
    private boolean passed;//考试是否通过 1过、0
    private String certification; //用户身证验证是否通过
    private String validity;//司机是否通过审核
    private String driver_id;//司机id
    private long certify_organization_id;//城市运营中心id用于标记城市运营中新是否审核确认该司机
    private String car_pass;//车辆信息审核   "Pending";//审核中 "Approved";//审核通过"Rejected";//审核失败

    public RegConfirm() {
    }

    public String getIdentity_card_no() {
        return identity_card_no;
    }

    public void setIdentity_card_no(String identity_card_no) {
        this.identity_card_no = identity_card_no;
    }

    public String getVehicle_brand() {
        return vehicle_brand;
    }

    public void setVehicle_brand(String vehicle_brand) {
        this.vehicle_brand = vehicle_brand;
    }


    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }


    public long getCertify_organization_id() {
        return certify_organization_id;
    }

    public void setCertify_organization_id(long certify_organization_id) {
        this.certify_organization_id = certify_organization_id;
    }

    public String getCar_pass() {
        return car_pass;
    }

    public void setCar_pass(String car_pass) {
        this.car_pass = car_pass;
    }

    @Override
    public String toString() {
        return "RegTempConfirm{" +
                "identity_card_no='" + identity_card_no + '\'' +
                ", vehicle_brand='" + vehicle_brand + '\'' +
                ", passed=" + passed +
                ", certification='" + certification + '\'' +
                ", validity='" + validity + '\'' +
                ", driver_id='" + driver_id + '\'' +
                ", certify_organization_id=" + certify_organization_id +
                ", car_pass='" + car_pass + '\'' +
                '}';
    }
}
