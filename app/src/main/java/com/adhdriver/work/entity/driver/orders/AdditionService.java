package com.adhdriver.work.entity.driver.orders;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/11.
 * 类描述  额外服务
 * 版本
 */

public class AdditionService implements Serializable {

    private String type; //额外服务
    private String price; //额外服务价格
    private String remark;//描述

    public AdditionService() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    @Override
    public String toString() {
        return "Addition_service{" +
                "type='" + type + '\'' +
                ", price='" + price + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
