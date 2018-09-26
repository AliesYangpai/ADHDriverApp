package com.adhdriver.work.method;

import com.adhdriver.work.entity.driver.temp.TempRegDriverVehicle;
import com.adhdriver.work.entity.driver.vehicle.DriverVehicle;
import com.adhdriver.work.listener.OnDataBackListener;

/**
 * Created by Administrator on 2017/12/19.
 * 类描述 车辆相关接口
 * 版本
 */

public interface IVehicle {

    /**
     * 获取车辆类型列表  需要token
     * @param url
     * @param onDataBackListener
     */
    void  doGetVehicleTypes(String url, String accessToken, OnDataBackListener onDataBackListener);


    /**
     * 获取指定车辆的catogry
     * @param url
     * @param accessToken
     * @param onDataBackListener
     */
    void doGetVehicleCategories(String url, String accessToken, OnDataBackListener onDataBackListener);


    /**
     * 提交车辆信息
     * @param url
     * @param accessToken
     * @param onDataBackListener
     */
    void doCompleteVehicleInfo(String url, String accessToken, TempRegDriverVehicle tempRegDriverVehicle, OnDataBackListener onDataBackListener);


    /**
     * 获取我的车辆列表
     * @param url
     */
    void doGetVehicles(String url, int size, int index, OnDataBackListener onDataBackListener);



    /**
     * 下拉差量更新
     * @param url
     */
    void doGetVehiclesInFresh(String url, int size, int index, OnDataBackListener onDataBackListener);

    /**
     * 上拉加载更多
     * @param url
     */
    void doGetVehiclesInLoadMore(String url, int size, int index, OnDataBackListener onDataBackListener);


    /**
     * 获取车辆详情信息
     * @param url
     * @param onDataBackListener
     */
    void doGetVehicleDetail(String url, OnDataBackListener onDataBackListener);


    /**
     * 删除车辆
     * @param url
     * @param onDataBackListener
     */
    void doDelVehicle(String url, OnDataBackListener onDataBackListener);




    /**
     * 添加车辆的接口
     * @param url
     * @param tempRegDriverVehicle
     * @param onDataBackListener
     */
    void doAddVehicle(String url,
                      TempRegDriverVehicle tempRegDriverVehicle,OnDataBackListener onDataBackListener);


    /**
     * 获取车辆类型列表  不要token
     * @param url
     * @param onDataBackListener
     */
    void  doGetVehicleTypes(String url,  OnDataBackListener onDataBackListener);


    /**
     * 获取指定车辆的catogry
     * @param url
     * @param
     * @param onDataBackListener
     */
    void doGetVehicleCategories(String url ,OnDataBackListener onDataBackListener);


    /**
     * 编辑更新车量
     * @param url
     * @param driverVehicle
     * @param onDataBackListener
     */
    void doUpdateVehicle(
            String url,
            DriverVehicle driverVehicle,
            OnDataBackListener onDataBackListener);


    /**
     * 切换车辆成功
     * @param url
     * @param onDataBackListener
     */
    void doSwitchVehicle(String url,OnDataBackListener onDataBackListener);


    /**
     * 上传新车的经纬度
     * @param url
     * @param longitude
     * @param latitude
     * @param zone_code
     * @param driver_vehicle_id
     * @param vehicle_category_name
     * @param onDataBackListener
     */
    void doUpLoadNewVehicleLocation(
            String url,
            double longitude,
            double latitude,
            String zone_code,
            String driver_vehicle_id,
            String vehicle_category_name,OnDataBackListener onDataBackListener);

}
