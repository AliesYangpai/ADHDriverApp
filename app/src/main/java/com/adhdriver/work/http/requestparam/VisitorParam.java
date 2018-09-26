package com.adhdriver.work.http.requestparam;

import com.google.gson.JsonObject;

/**
 * Created by Administrator on 2018/1/2.
 * 类描述
 * 版本
 */

public class VisitorParam extends BaseParam{


    /**
     * 获取游客的经纬度
     * @param longitude
     * @param latitude
     * @param zone_code
     * @return
     */
    public String getVisitorCoordinate(double longitude, double latitude,String zone_code) {

        JsonObject jsonObject = getJsonObject();


        jsonObject.addProperty("longitude", longitude);
        jsonObject.addProperty("latitude", latitude);
        jsonObject.addProperty("zone_code", zone_code);

        return jsonObject.toString();

    }

}
