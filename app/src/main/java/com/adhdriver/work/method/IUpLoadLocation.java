package com.adhdriver.work.method;

import com.adhdriver.work.listener.OnDataBackListener;

/**
 * Created by Administrator on 2018/1/2.
 * 类描述
 * 版本
 */

public interface IUpLoadLocation {


    void doUpLoadLocation(String url,
                          double longitude,
                          double latitude,
                          String adCode,
                          String driverVehcleId,
                          String logonDriverCategory,
                          OnDataBackListener onDataBackListener);


    void doUpLoadDriverArea(String url,OnDataBackListener onDataBackListener);
}
