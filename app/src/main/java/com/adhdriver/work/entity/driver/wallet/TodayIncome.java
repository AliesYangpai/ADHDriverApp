package com.adhdriver.work.entity.driver.wallet;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/23.
 * 类描述
 * 版本
 */

public class TodayIncome implements Serializable{


    private int driver_id;
    private int order_count;
    private double income;


    public TodayIncome() {
    }


    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public int getOrder_count() {
        return order_count;
    }

    public void setOrder_count(int order_count) {
        this.order_count = order_count;
    }


    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    @Override
    public String toString() {
        return "TodayIncome{" +
                "driver_id=" + driver_id +
                ", order_count=" + order_count +
                ", income=" + income +
                '}';
    }
}
