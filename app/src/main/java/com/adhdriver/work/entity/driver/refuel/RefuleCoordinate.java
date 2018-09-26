package com.adhdriver.work.entity.driver.refuel;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/11.
 * 类描述
 * 版本
 */

public class RefuleCoordinate implements Serializable {

    private String startLat;
    private String startLng;
    private String endLat;
    private String endLng;


    public RefuleCoordinate() {
    }

    public RefuleCoordinate(String startLat, String startLng, String endLat, String endLng) {
        this.startLat = startLat;
        this.startLng = startLng;
        this.endLat = endLat;
        this.endLng = endLng;
    }

    public String getStartLat() {
        return startLat;
    }

    public void setStartLat(String startLat) {
        this.startLat = startLat;
    }

    public String getStartLng() {
        return startLng;
    }

    public void setStartLng(String startLng) {
        this.startLng = startLng;
    }

    public String getEndLat() {
        return endLat;
    }

    public void setEndLat(String endLat) {
        this.endLat = endLat;
    }

    public String getEndLng() {
        return endLng;
    }

    public void setEndLng(String endLng) {
        this.endLng = endLng;
    }

    @Override
    public String toString() {
        return "RefuleCoordinate{" +
                "startLat='" + startLat + '\'' +
                ", startLng='" + startLng + '\'' +
                ", endLat='" + endLat + '\'' +
                ", endLng='" + endLng + '\'' +
                '}';
    }
}
