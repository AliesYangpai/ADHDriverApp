package com.adhdriver.work.entity.driver.orders;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/24.
 * 类描述   行程中 点击 界面跳转的OrderTagEntity
 * 版本
 */

public class OrderTagEntity implements Serializable{
    private String businessType;
    private int tag;

    public OrderTagEntity() {
    }

    public OrderTagEntity(String businessType, int tag) {
        this.businessType = businessType;
        this.tag = tag;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }


    @Override
    public String toString() {
        return "OrderTagEntity{" +
                "businessType='" + businessType + '\'' +
                ", tag=" + tag +
                '}';
    }
}
