package com.adhdriver.work.entity.driver.vehicle;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/10/9.
 * 类描述  当前登陆车辆实体类 目前仅仅使用 driver_vehicle_id 字段
 * 版本
 */

public class LogonVehicle extends DataSupport{

//          "vehicle_brand": "string",
//                  "vehicle_type_name": "string",
//                  "vehicle_length": 0,
//                  "vehicle_width": 0,
//                  "vehicle_height": 0,
//                  "quality_deposit": 0,
//                  "fuel_type": "string",
//                  "fuel_consumption": 0,
//                  "suitable_vehicle_miles": 0,
//                  "driver_vehicle_id": 0,
//                  "driver_id": 0,
//                  "vehicle_id": 0,
//                  "vehicle_status": "string",
//                  "vehicle_license": "string",
//                  "vehicle_photo_front_path": "string",
//                  "vehicle_photo_back_path": "string",
//                  "car_insurance_policy": "string",
//                  "business_insurance": "string",
//                  "plate_number": "string",
//                  "vehicle_dead_weight": 0,
//                  "vehicle_stere": 0,
//                  "create_time": "2017-10-09T10:51:02.216Z",
//                  "refuse_reason": "string",
//                  "is_joined_enterprise": true,
//                  "category_name": "string"
//}

    private String driver_vehicle_id;

    private String category_name;

    public LogonVehicle() {
    }

    public String getDriver_vehicle_id() {
        return driver_vehicle_id;
    }

    public void setDriver_vehicle_id(String driver_vehicle_id) {
        this.driver_vehicle_id = driver_vehicle_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    @Override
    public String toString() {
        return "LogonVehicle{" +
                "driver_vehicle_id='" + driver_vehicle_id + '\'' +
                ", category_name='" + category_name + '\'' +
                '}';
    }
}
