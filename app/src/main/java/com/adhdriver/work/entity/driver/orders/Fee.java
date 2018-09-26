package com.adhdriver.work.entity.driver.orders;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/5/11.
 * 类描述  价格描述
 * 版本
 */

public class Fee  implements Serializable {

    private String description;//费用描述。 ,
    private String quoted; //总费用。 ,
    private String discount; //折扣。 ,
    private String total;//合计

    private List<FeeDetail> details;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuoted() {
        return quoted;
    }

    public void setQuoted(String quoted) {
        this.quoted = quoted;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<FeeDetail> getDetails() {
        return details;
    }

    public void setDetails(List<FeeDetail> details) {
        this.details = details;
    }


    @Override
    public String toString() {
        return "Fee{" +
                "description='" + description + '\'' +
                ", quoted='" + quoted + '\'' +
                ", discount='" + discount + '\'' +
                ", total='" + total + '\'' +
                ", details=" + details +
                '}';
    }
}
