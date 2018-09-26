package com.adhdriver.work.http.requestparam;

import com.google.gson.JsonObject;

/**
 * Created by Administrator on 2017/11/23.
 * 类描述  订单相关的请求参数
 * 版本
 */

public class OrderParam extends BaseParam{


    /**
     * 参与竞价
     * @param price
     * @return
     */
    public String getBiddingParam(String price) {

        JsonObject jsonObject = getJsonObject();

        jsonObject.addProperty("price", price);

        return jsonObject.toString();

    }


    /**
     * 出发时，的经纬度
     * @param longitude
     * @param latitude
     * @return
     */
    public String  getDepartCoordinateParam(double longitude,double latitude){

        JsonObject jsonObject = getJsonObject();

        jsonObject.addProperty("longitude", longitude);
        jsonObject.addProperty("latitude", latitude);

        return jsonObject.toString();
    }


    public String  getArriveNextCoordinateParam(double longitude,double latitude){

        JsonObject jsonObject = getJsonObject();

        jsonObject.addProperty("longitude", longitude);
        jsonObject.addProperty("latitude", latitude);

        return jsonObject.toString();
    }


    /**
     * 输入收货人的验证码 来完成这个订单
     *
     * @param
     * @return
     */
    public  String getReciverCodeParam(String reciverCode) {


        String jsonString = "";
        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty("pass_code", reciverCode);
        jsonString = jsonObject.toString();
        return jsonString;

    }


}

