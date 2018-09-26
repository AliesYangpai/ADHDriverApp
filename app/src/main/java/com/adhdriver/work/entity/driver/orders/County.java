package com.adhdriver.work.entity.driver.orders;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/13.
 * 类描述  区县信息
 * 版本
 */

public class County implements Serializable {
    private String zone_id; //区县id
    private String name;    //区县名称
    private String standard_code; //区县邮编
    private boolean is_downtown;//是否为市区

    public County() {
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

    public boolean is_downtown() {
        return is_downtown;
    }

    public void setIs_downtown(boolean is_downtown) {
        this.is_downtown = is_downtown;
    }

    @Override
    public String toString() {
        return "County{" +
                "zone_id='" + zone_id + '\'' +
                ", name='" + name + '\'' +
                ", standard_code='" + standard_code + '\'' +
                ", is_downtown=" + is_downtown +
                '}';
    }
}
