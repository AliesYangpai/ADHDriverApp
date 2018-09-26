package com.adhdriver.work.entity.driver.orders;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/11.
 * 类描述  经纬度
 * 版本
 */

public class Coordinate implements Serializable {

    private String longitude; //经度
    private String latitude;  //纬度


    public Coordinate() {
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }


    @Override
    public String toString() {
        return "Coordinate{" +
                "longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }
}
