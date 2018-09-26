package com.adhdriver.work.http.requestparam;

import com.google.gson.JsonObject;

/**
 * Created by Administrator on 2017/12/1.
 * 类描述 发布顺风车
 * 版本
 */

public class PublishRouteParam extends BaseParam{


    /**
     * 获取司
     * @param hitchhike_no
     * @param driver_id
     * @param depart_time
     * @param depart_zone_code
     * @param depart_address
     * @param depart_coordinate_x
     * @param depart_coordinate_y
     * @param destination_zone_code
     * @param destination_address
     * @param destination_coordinate_x
     * @param destination_coordinate_y
     * @return
     */
    public String getPublishRouteParam(
            String hitchhike_no,
            String driver_id,
            String depart_time,
            String depart_zone_code,
            String depart_address,
            String depart_coordinate_x,
            String depart_coordinate_y,
            String destination_zone_code,
            String destination_address,
            String destination_coordinate_x,
            String destination_coordinate_y ) {

        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty("hitchhike_no", hitchhike_no);   //车牌号
        jsonObject.addProperty("driver_id", driver_id);        //司机id
        jsonObject.addProperty("depart_time", depart_time);    //// 出发时间

        jsonObject.addProperty("depart_zone_code", depart_zone_code); //出发地城市编码
        jsonObject.addProperty("depart_address", depart_address); //出发地详细信息
        jsonObject.addProperty("depart_coordinate_x",depart_coordinate_x); //出发地纬度
        jsonObject.addProperty("depart_coordinate_y",depart_coordinate_y);  //出发地经度


        jsonObject.addProperty("destination_zone_code", destination_zone_code);// 目的地城市编码
        jsonObject.addProperty("destination_address",destination_address);    // 目的详细地址
        jsonObject.addProperty("destination_coordinate_x",destination_coordinate_x); //目的地纬度
        jsonObject.addProperty("destination_coordinate_y",destination_coordinate_y);  // 目的地经度


        return jsonObject.toString();

    }


}
