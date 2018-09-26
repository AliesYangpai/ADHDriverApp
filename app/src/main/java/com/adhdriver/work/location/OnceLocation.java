package com.adhdriver.work.location;

import com.adhdriver.work.AiDaiHuoApplication;
import com.adhdriver.work.callback.OnceLocationCallBack;
import com.adhdriver.work.constant.ConstLocation;
import com.adhdriver.work.location.amap.AmapOption;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

/**
 * Created by Administrator on 2017/11/29.
 * 类描述  游客模式 获取经纬度（单次定位）
 * 版本
 */

public class OnceLocation implements AMapLocationListener {

    private AMapLocationClient locationClient = null;
    private OnceLocationCallBack onceLocationCallBack;

    private AmapOption amapOption;

    public OnceLocation() {

        amapOption = new AmapOption();
        locationClient = new AMapLocationClient(AiDaiHuoApplication.getInstance().getApplicationContext());
        locationClient.setLocationOption(amapOption.getDefaultOption());
        locationClient.setLocationListener(this);
    }


    public void doStart() {

        if(null != locationClient) {
            locationClient.startLocation();
        }
    }






    /**
     * 停止并销毁定位
     */
    public void destroylocation() {

        if (null != locationClient && locationClient.isStarted()) {

            locationClient.stopLocation();

            locationClient.onDestroy();

            locationClient = null;

        }


    }



    public void setOnceLocationCallBack(OnceLocationCallBack onceLocationCallBack) {
        this.onceLocationCallBack = onceLocationCallBack;
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {



        if(null != aMapLocation && null != onceLocationCallBack) {

            onceLocationCallBack.onceLocationInfoBack(aMapLocation);
        }


    }

}
