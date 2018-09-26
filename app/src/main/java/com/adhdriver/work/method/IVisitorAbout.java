package com.adhdriver.work.method;

import com.adhdriver.work.listener.OnDataBackListener;

/**
 * Created by Administrator on 2017/12/8.
 * 类描述  游客模式相关方法
 * 版本
 */

public interface IVisitorAbout {


    /**
     * 游客模式获取logon
     * @param url
     * @param onDataBackListener
     */
    void doVisitorLogon(String url, OnDataBackListener onDataBackListener);


    /**
     * 获取App版本信息
     * @param url
     * @param os_type
     * @param os_version
     * @param model
     * @param device_id
     * @param onDataBackListener
     */
    void doGetAppInfo(String url, String os_type, String os_version, String model, String device_id, OnDataBackListener onDataBackListener);


    /**
     * 上传游客模式经纬度
     * @param url
     * @param longitude
     * @param latitude
     * @param zone_code
     * @param onDataBackListener
     */
    void doVisitorSave(String url, double longitude, double latitude, String zone_code, OnDataBackListener onDataBackListener);



}
