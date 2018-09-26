package com.adhdriver.work.http.requestparam;

import com.adhdriver.work.entity.driver.temp.TempRegDriverVehicle;
import com.adhdriver.work.entity.driver.vehicle.DriverVehicle;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/12/19.
 * 类描述
 * 版本
 */

public class VehicleParam extends BaseParam {


    /**
     * 提交司机、车辆信息
     *
     * @return
     */

    public String getRegVehicleAndDriverParam(TempRegDriverVehicle tempRegDriverVehicle) {

        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty("vehicle_no", tempRegDriverVehicle.getVehicle_no());
        jsonObject.addProperty("vehicle_type", tempRegDriverVehicle.getVehicle_id());
        jsonObject.addProperty("driver_license", tempRegDriverVehicle.getDriver_license());
        jsonObject.addProperty("vehicle_license", tempRegDriverVehicle.getVehicle_license());
        jsonObject.addProperty("driver_photo_path", tempRegDriverVehicle.getDriver_photo_path());
        jsonObject.addProperty("vehicle_photo_front_path", tempRegDriverVehicle.getVehicle_photo_front_path());
        jsonObject.addProperty("vehicle_photo_back_path", tempRegDriverVehicle.getVehicle_photo_back_path());
        jsonObject.addProperty("car_insurance_policy", tempRegDriverVehicle.getCar_insurance_policy());
        jsonObject.addProperty("business_insurance", tempRegDriverVehicle.getBusiness_insurance());


        jsonObject.addProperty("category_name", tempRegDriverVehicle.getCategory_name());
        jsonObject.addProperty("vehicle_dead_weight", tempRegDriverVehicle.getVehicle_dead_weight());
        jsonObject.addProperty("vehicle_stere", tempRegDriverVehicle.getVehicle_stere());

        return jsonObject.toString();


    }


    /**
     * 添加新车辆的相关请求参数
     *
     * @param tempRegDriverVehicle
     * @return
     */
    public String getAddVehicleParam(TempRegDriverVehicle tempRegDriverVehicle) {

        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty("vehicle_id", tempRegDriverVehicle.getVehicle_id());
        jsonObject.addProperty("plate_number", tempRegDriverVehicle.getVehicle_no());
        jsonObject.addProperty("vehicle_license", tempRegDriverVehicle.getVehicle_license());
        jsonObject.addProperty("vehicle_photo_front_path", tempRegDriverVehicle.getVehicle_photo_front_path());
        jsonObject.addProperty("vehicle_photo_back_path", tempRegDriverVehicle.getVehicle_photo_back_path());
        jsonObject.addProperty("car_insurance_policy", tempRegDriverVehicle.getCar_insurance_policy());
        jsonObject.addProperty("business_insurance", tempRegDriverVehicle.getBusiness_insurance());
        jsonObject.addProperty("category_name", tempRegDriverVehicle.getCategory_name());
        jsonObject.addProperty("vehicle_dead_weight", tempRegDriverVehicle.getVehicle_dead_weight());
        jsonObject.addProperty("vehicle_stere", tempRegDriverVehicle.getVehicle_stere());

        return jsonObject.toString();

    }


    /**
     * 编辑新车辆的相关请求参数
     *
     * @param driverVehicle
     * @return
     */
    public String getEditVehicleParam(DriverVehicle driverVehicle, String vehicle_status) {

        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty("vehicle_license", driverVehicle.getVehicle_license());
        jsonObject.addProperty("vehicle_photo_front_path", driverVehicle.getVehicle_photo_front_path());
        jsonObject.addProperty("vehicle_photo_back_path", driverVehicle.getVehicle_photo_back_path());
        jsonObject.addProperty("car_insurance_policy", driverVehicle.getCar_insurance_policy());
        jsonObject.addProperty("business_insurance", driverVehicle.getBusiness_insurance());
        jsonObject.addProperty("category_name", driverVehicle.getCategory_name());
        jsonObject.addProperty("vehicle_dead_weight", driverVehicle.getVehicle_dead_weight());
        jsonObject.addProperty("vehicle_stere", driverVehicle.getVehicle_stere());
        jsonObject.addProperty("vehicle_status", vehicle_status);

        return jsonObject.toString();

    }


    /**
     * 上传司机经纬度参数
     *
     * @param longitude 经度
     * @param latitude  纬度
     * @return
     */
    public String getCoordinateParam(double longitude, double latitude, String zone_code, String driver_vehicle_id, String vehicle_category_name) {

        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty("longitude", longitude);
        jsonObject.addProperty("latitude", latitude);
        jsonObject.addProperty("zone_code", zone_code);
        jsonObject.addProperty("driver_vehicle_id", driver_vehicle_id);
        jsonObject.addProperty("vehicle_category_name", vehicle_category_name);


        return jsonObject.toString();

    }
}