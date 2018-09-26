package com.adhdriver.work.entity.driver.temp;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/18.
 * 类描述
 * 版本  发布顺风车的实体类
 */

public class PublishRoute implements Serializable {


//                jsonObject.put("hitchhike_no", publishRide.getHitchhike_no());   //车牌号
//            jsonObject.put("driver_id", publishRide.getDriver_id());        //司机id
//            jsonObject.put("depart_time", publishRide.getDepart_time());    //// 出发时间
//
//
//            jsonObject.put("depart_zone_code", publishRide.getDepart_zone_code()); //出发地城市编码
//            jsonObject.put("depart_address", publishRide.getDepart_address()); //出发地详细信息
//            jsonObject.put("depart_coordinate_x", publishRide.getDepart_coordinate_x()); //出发地纬度
//            jsonObject.put("depart_coordinate_y", publishRide.getDepart_coordinate_y());  //出发地经度
//
//
//
//            jsonObject.put("destination_zone_code", publishRide.getDestination_zone_code());// 目的地城市编码
//            jsonObject.put("destination_address", publishRide.getDestination_address());    // 目的详细地址
//            jsonObject.put("destination_coordinate_x", publishRide.getDestination_coordinate_x()); //目的地纬度
//            jsonObject.put("destination_coordinate_y", publishRide.getDestination_coordinate_y());  // 目的地经度


    private String hitchhike_no;//顺风车编号
    private String driver_id;//司机id
    private String depart_time;//出发时间


    private String depart_zone_code;////出发地的城市编码
    private String depart_address;//出发地地址
    private String depart_coordinate_x;//出发地 纬度
    private String depart_coordinate_y;//出发地 经度

    private String destination_zone_code;//目的地的城市编码 【新加入】
    private String destination_address;//目的地地址
    private String destination_coordinate_x;//目的地 纬度
    private String destination_coordinate_y;//目的地 经度


    private String status;//发布状态 //Pending 等待匹配
    // Succeed 匹配成功
    // Failed 匹配失败
    // Canceled 已取消


    public PublishRoute() {
    }


    public String getHitchhike_no() {
        return hitchhike_no;
    }

    public void setHitchhike_no(String hitchhike_no) {
        this.hitchhike_no = hitchhike_no;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getDepart_time() {
        return depart_time;
    }

    public void setDepart_time(String depart_time) {
        this.depart_time = depart_time;
    }

    public String getDepart_zone_code() {
        return depart_zone_code;
    }

    public void setDepart_zone_code(String depart_zone_code) {
        this.depart_zone_code = depart_zone_code;
    }

    public String getDepart_address() {
        return depart_address;
    }

    public void setDepart_address(String depart_address) {
        this.depart_address = depart_address;
    }

    public String getDepart_coordinate_x() {
        return depart_coordinate_x;
    }

    public void setDepart_coordinate_x(String depart_coordinate_x) {
        this.depart_coordinate_x = depart_coordinate_x;
    }

    public String getDepart_coordinate_y() {
        return depart_coordinate_y;
    }

    public void setDepart_coordinate_y(String depart_coordinate_y) {
        this.depart_coordinate_y = depart_coordinate_y;
    }

    public String getDestination_zone_code() {
        return destination_zone_code;
    }

    public void setDestination_zone_code(String destination_zone_code) {
        this.destination_zone_code = destination_zone_code;
    }

    public String getDestination_address() {
        return destination_address;
    }

    public void setDestination_address(String destination_address) {
        this.destination_address = destination_address;
    }

    public String getDestination_coordinate_x() {
        return destination_coordinate_x;
    }

    public void setDestination_coordinate_x(String destination_coordinate_x) {
        this.destination_coordinate_x = destination_coordinate_x;
    }

    public String getDestination_coordinate_y() {
        return destination_coordinate_y;
    }

    public void setDestination_coordinate_y(String destination_coordinate_y) {
        this.destination_coordinate_y = destination_coordinate_y;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "PublishRoute{" +
                "hitchhike_no='" + hitchhike_no + '\'' +
                ", driver_id='" + driver_id + '\'' +
                ", depart_time='" + depart_time + '\'' +
                ", depart_zone_code='" + depart_zone_code + '\'' +
                ", depart_address='" + depart_address + '\'' +
                ", depart_coordinate_x='" + depart_coordinate_x + '\'' +
                ", depart_coordinate_y='" + depart_coordinate_y + '\'' +
                ", destination_zone_code='" + destination_zone_code + '\'' +
                ", destination_address='" + destination_address + '\'' +
                ", destination_coordinate_x='" + destination_coordinate_x + '\'' +
                ", destination_coordinate_y='" + destination_coordinate_y + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
