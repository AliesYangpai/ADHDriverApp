package com.adhdriver.work.entity.driver.orders;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/22.
 */

public class PackageDetail implements Serializable {
    /**
     * 集包内订单编号
     */
    public String order_no;
    /**
     * 重量
     */
    public double weight;
    /**
     * 创建时间
     */
    public String create_time;
    /**
     * 上车确认
     */
    public boolean confirmed;
    /**
     * 拆包确认
     */
    public boolean unpacked;

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public boolean isUnpacked() {
        return unpacked;
    }

    public void setUnpacked(boolean unpacked) {
        this.unpacked = unpacked;
    }

    @Override
    public String toString() {
        return "PackageDetail{" +
                "order_no='" + order_no + '\'' +
                ", weight=" + weight +
                ", create_time='" + create_time + '\'' +
                ", confirmed=" + confirmed +
                ", unpacked=" + unpacked +
                '}';
    }
}
