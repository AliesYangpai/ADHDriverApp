package com.adhdriver.work.callback;

import com.amap.api.location.AMapLocation;

/**
 * Created by Administrator on 2017/11/29.
 * 类描述  单次定位的接口返回
 * 版本
 */

public interface OnceLocationCallBack {

    void onceLocationInfoBack(AMapLocation aMapLocation);

}
