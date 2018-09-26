package com.adhdriver.work.entity;

import com.adhdriver.work.entity.business.Business;
import com.adhdriver.work.entity.driver.Driver;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/18.
 * 类描述  用户表 这里包含了 user信息、driver信息、还有officeix
 * 版本
 */

public class User extends DataSupport implements Serializable {






    private int id;//自增id
    private int user_id;//用户id
    private String phone;//用户电话
    private String fullname;// 用户名称
    private String create_time;// 创建时间
    private String last_login_time; //上次登陆时间
    private String currently_login_time; //当前登陆时间
    private String currently_ip;//当前登陆ip
    private String integrals;//用户积分
    private String avatar;       //用户头像
    private String validity;      //审核状态 = ['Waiting', 'Successful', 'Ongoing', 'Failed'],
    private String certification;  //认证状态（主要用于实名认证） = ['Pending', 'Authorizing', 'Approved', 'Rejected'],
    private String unique_code;  //唯一码/邀请码
    private String apply_refuse;  //认证拒绝原因

    private Driver driver;

    private Business business;


    private String province_id; //省Id
    private String community_id; //市id
    private String county_id;   //区域Id


    public User() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(String last_login_time) {
        this.last_login_time = last_login_time;
    }

    public String getCurrently_login_time() {
        return currently_login_time;
    }

    public void setCurrently_login_time(String currently_login_time) {
        this.currently_login_time = currently_login_time;
    }

    public String getCurrently_ip() {
        return currently_ip;
    }

    public void setCurrently_ip(String currently_ip) {
        this.currently_ip = currently_ip;
    }

    public String getIntegrals() {
        return integrals;
    }

    public void setIntegrals(String integrals) {
        this.integrals = integrals;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getUnique_code() {
        return unique_code;
    }

    public void setUnique_code(String unique_code) {
        this.unique_code = unique_code;
    }

    public String getApply_refuse() {
        return apply_refuse;
    }

    public void setApply_refuse(String apply_refuse) {
        this.apply_refuse = apply_refuse;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }


    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }


    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    public String getCommunity_id() {
        return community_id;
    }

    public void setCommunity_id(String community_id) {
        this.community_id = community_id;
    }

    public String getCounty_id() {
        return county_id;
    }

    public void setCounty_id(String county_id) {
        this.county_id = county_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", phone='" + phone + '\'' +
                ", fullname='" + fullname + '\'' +
                ", create_time='" + create_time + '\'' +
                ", last_login_time='" + last_login_time + '\'' +
                ", currently_login_time='" + currently_login_time + '\'' +
                ", currently_ip='" + currently_ip + '\'' +
                ", integrals='" + integrals + '\'' +
                ", avatar='" + avatar + '\'' +
                ", validity='" + validity + '\'' +
                ", certification='" + certification + '\'' +
                ", unique_code='" + unique_code + '\'' +
                ", apply_refuse='" + apply_refuse + '\'' +
                ", driver=" + driver +
                ", business=" + business +
                ", province_id='" + province_id + '\'' +
                ", community_id='" + community_id + '\'' +
                ", county_id='" + county_id + '\'' +
                '}';
    }
}
