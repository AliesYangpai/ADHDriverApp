package com.adhdriver.work.http.requestparam;

import com.google.gson.JsonObject;

/**
 * Created by Administrator on 2018/1/2.
 * 类描述
 * 版本
 */

public class LocationParam extends BaseParam {



    public String getCoordinate(double longitude, double latitude,String zone_code,String driver_vehicle_id,String vehicle_category_name) {


        JsonObject jsonObject = getJsonObject();

        jsonObject.addProperty("longitude", longitude);
        jsonObject.addProperty("latitude", latitude);
        jsonObject.addProperty("zone_code", zone_code);
        jsonObject.addProperty("driver_vehicle_id", driver_vehicle_id);
        jsonObject.addProperty("vehicle_category_name",vehicle_category_name);

        return jsonObject.toString();

    }

}
