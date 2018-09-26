package com.adhdriver.work.entity.driver.orders;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/10/20.
 * 类描述  一口价的订单fee 用于集包订单，当订单是 overOffice的 集包订单的一口价类型时，遍历取出价格的时候需要 使用此对象
 * 版本
 */

public class FixedPriceFee implements Serializable{



    private List<FeeDetail> details;

    public FixedPriceFee() {
    }


    public List<FeeDetail> getDetails() {
        return details;
    }

    public void setDetails(List<FeeDetail> details) {
        this.details = details;
    }


    @Override
    public String toString() {
        return "FixedPriceFee{" +
                "details=" + details +
                '}';
    }
}
