package com.adhdriver.work.entity.driver.orders;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/8.
 * 类描述  分成对象
 * 版本
 */

public class Divided implements Serializable {


//    "divided": {
//        "driver_amount": 0,
//                "system_amount": 0,
//                "from_business_office_amount": 0,
//                "from_city_operation_center_amount": 0,
//                "to_business_office_amount": 0,
//                "to_city_operation_center_amount": 0
//    }


    private String driver_amount;//分成钱数【司机端需要】
    private String system_amount;
    private String from_business_office_amount;
    private String from_city_operation_center_amount;
    private String to_business_office_amount;
    private String to_city_operation_center_amount;


    public Divided() {
    }


    public String getDriver_amount() {
        return driver_amount;
    }

    public void setDriver_amount(String driver_amount) {
        this.driver_amount = driver_amount;
    }

    public String getSystem_amount() {
        return system_amount;
    }

    public void setSystem_amount(String system_amount) {
        this.system_amount = system_amount;
    }

    public String getFrom_business_office_amount() {
        return from_business_office_amount;
    }

    public void setFrom_business_office_amount(String from_business_office_amount) {
        this.from_business_office_amount = from_business_office_amount;
    }

    public String getFrom_city_operation_center_amount() {
        return from_city_operation_center_amount;
    }

    public void setFrom_city_operation_center_amount(String from_city_operation_center_amount) {
        this.from_city_operation_center_amount = from_city_operation_center_amount;
    }

    public String getTo_business_office_amount() {
        return to_business_office_amount;
    }

    public void setTo_business_office_amount(String to_business_office_amount) {
        this.to_business_office_amount = to_business_office_amount;
    }

    public String getTo_city_operation_center_amount() {
        return to_city_operation_center_amount;
    }

    public void setTo_city_operation_center_amount(String to_city_operation_center_amount) {
        this.to_city_operation_center_amount = to_city_operation_center_amount;
    }

    @Override
    public String toString() {
        return "Divided{" +
                "driver_amount='" + driver_amount + '\'' +
                ", system_amount='" + system_amount + '\'' +
                ", from_business_office_amount='" + from_business_office_amount + '\'' +
                ", from_city_operation_center_amount='" + from_city_operation_center_amount + '\'' +
                ", to_business_office_amount='" + to_business_office_amount + '\'' +
                ", to_city_operation_center_amount='" + to_city_operation_center_amount + '\'' +
                '}';
    }
}
