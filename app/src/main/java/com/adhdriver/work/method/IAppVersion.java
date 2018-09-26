package com.adhdriver.work.method;


import com.adhdriver.work.listener.OnDataBackListener;

/**
 * Created by Administrator on 2017/11/20.
 * 类描述  版本相关的接口 方法
 * 版本
 */

public interface IAppVersion  {


    /**
     * 获取app版本信息
     *
     * @param onDataBackListener
     */
    void doGetAppInfo(String url, String os_type, String os_version, String model, String device_id, OnDataBackListener onDataBackListener);


}
