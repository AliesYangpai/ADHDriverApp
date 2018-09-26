package com.adhdriver.work.entity.driver.temp;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/20.
 * 类描述  注册已完成返回
 * 版本
 */

public class RegDriverComplete implements Serializable{


    private String driver_id;

    public RegDriverComplete() {
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    @Override
    public String toString() {
        return "RegDriverComplete{" +
                "driver_id='" + driver_id + '\'' +
                '}';
    }
}
