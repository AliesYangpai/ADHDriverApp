package com.adhdriver.work.entity.driver.orders;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/13.
 * 类描述  省对象
 * 版本
 */

public class Province implements Serializable {

    private String zone_id; //省id
    private String name;    //省名称
    private String standard_code; //省编码

    public Province() {
    }

    public String getZone_id() {
        return zone_id;
    }

    public void setZone_id(String zone_id) {
        this.zone_id = zone_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStandard_code() {
        return standard_code;
    }

    public void setStandard_code(String standard_code) {
        this.standard_code = standard_code;
    }


    @Override
    public String toString() {
        return "Province{" +
                "zone_id='" + zone_id + '\'' +
                ", name='" + name + '\'' +
                ", standard_code='" + standard_code + '\'' +
                '}';
    }
}
