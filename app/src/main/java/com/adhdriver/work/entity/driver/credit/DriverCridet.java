package com.adhdriver.work.entity.driver.credit;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 * 类描述
 * 版本
 */

public class DriverCridet implements Serializable {


    private float credit_points; //信用分
    private float total_target;  //总指标数
    private int driver_id;
    private int received_orders; //接单数

    private List<RatingDetial> rating_details;//

    public DriverCridet() {
    }

    public float getCredit_points() {
        return credit_points;
    }

    public void setCredit_points(float credit_points) {
        this.credit_points = credit_points;
    }

    public float getTotal_target() {
        return total_target;
    }

    public void setTotal_target(float total_target) {
        this.total_target = total_target;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public int getReceived_orders() {
        return received_orders;
    }

    public void setReceived_orders(int received_orders) {
        this.received_orders = received_orders;
    }


    public List<RatingDetial> getRating_details() {
        return rating_details;
    }

    public void setRating_details(List<RatingDetial> rating_details) {
        this.rating_details = rating_details;
    }


    @Override
    public String toString() {
        return "DriverCridet{" +
                "credit_points=" + credit_points +
                ", total_target=" + total_target +
                ", driver_id=" + driver_id +
                ", received_orders=" + received_orders +
                ", rating_details=" + rating_details +
                '}';
    }
}
