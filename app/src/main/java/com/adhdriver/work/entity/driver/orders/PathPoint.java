package com.adhdriver.work.entity.driver.orders;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/24.
 * 类描述
 * 版本
 */

public class PathPoint implements Serializable{

    private Office office;
    private Province province;
    private City city;
    private County county;

    private String street_address;
    private String fullname;
    private String phone;

    private Coordinate coordinate;

    private String arrival_time;


    public PathPoint() {
    }


    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
    }

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }


    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }


    @Override
    public String toString() {
        return "PathPoint{" +
                "office=" + office +
                ", province=" + province +
                ", city=" + city +
                ", county=" + county +
                ", street_address='" + street_address + '\'' +
                ", fullname='" + fullname + '\'' +
                ", phone='" + phone + '\'' +
                ", coordinate=" + coordinate +
                ", arrival_time='" + arrival_time + '\'' +
                '}';
    }
}
