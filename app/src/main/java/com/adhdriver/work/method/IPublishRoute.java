package com.adhdriver.work.method;

import com.adhdriver.work.listener.OnDataBackListener;

/**
 * Created by Administrator on 2017/12/1.
 * 类描述   发布顺风车方法
 * 版本
 */

public interface IPublishRoute {


//                jsonObject.put("hitchhike_no", publishRide.getHitchhike_no());   //车牌号
//            jsonObject.put("driver_id", publishRide.getDriver_id());        //司机id
//            jsonObject.put("depart_time", publishRide.getDepart_time());    //// 出发时间
//
//
//            jsonObject.put("depart_zone_code", publishRide.getDepart_zone_code()); //出发地城市编码
//            jsonObject.put("depart_address", publishRide.getDepart_address()); //出发地详细信息
//            jsonObject.put("depart_coordinate_x", publishRide.getDepart_coordinate_x()); //出发地纬度
//            jsonObject.put("depart_coordinate_y", publishRide.getDepart_coordinate_y());  //出发地经度
//
//
//
//            jsonObject.put("destination_zone_code", publishRide.getDestination_zone_code());// 目的地城市编码
//            jsonObject.put("destination_address", publishRide.getDestination_address());    // 目的详细地址
//            jsonObject.put("destination_coordinate_x", publishRide.getDestination_coordinate_x()); //目的地纬度
//            jsonObject.put("destination_coordinate_y", publishRide.getDestination_coordinate_y());  // 目的地经度


    /**
     * 发布顺风车
     *
     * @param url
     * @param hitchhike_no             //车牌号
     * @param driver_id                //司机id
     * @param depart_time              出发时间
     * @param depart_zone_code         出发地城市编码
     * @param depart_address           出发地详细信息
     * @param depart_coordinate_x      出发地纬度
     * @param depart_coordinate_y      出发地经度
     * @param destination_zone_code    目的地城市编码
     * @param destination_address      目的详细地址
     * @param destination_coordinate_x 目的地纬度
     * @param destination_coordinate_y 目的地经度
     * @param onDataBackListener
     */
    void doPublishRoute(String url,
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
                        String destination_coordinate_y,
                        OnDataBackListener onDataBackListener);


    /**
     * 获取路线发布列表
     *
     * @param url
     * @param size
     * @param index
     */
    void doGetPublishRoutes(String url, int size, int index, OnDataBackListener onDataBackListener);



    /**
     * 获取路线发布列表
     *
     * @param url
     * @param size
     * @param index
     */
    void doGetPublishRoutesInFresh(String url, int size, int index, OnDataBackListener onDataBackListener);

    /**
     * 获取路线发布列表
     *
     * @param url
     * @param size
     * @param index
     */
    void doGetPublishRoutesInLoadMore(String url, int size, int index, OnDataBackListener onDataBackListener);


    /**
     * 取消本次路线
     * @param url
     * @param onDataBackListener
     */
    void doCancelThisRoute(String url, OnDataBackListener onDataBackListener);
}
