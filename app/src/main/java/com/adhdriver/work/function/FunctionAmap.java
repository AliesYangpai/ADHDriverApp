package com.adhdriver.work.function;

import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;

/**
 * Created by Administrator on 2018/1/2.
 * 类描述   计算次数的方法用于amap
 * 版本
 */

public class FunctionAmap {


    /**
     * 每15上传一次
     * @param timeCount
     * @return
     */
    public long calculateTimeSpan(long timeCount) {
        return (timeCount * 5) % 15;
    }


    public long calculateUdpHeartTimeSpan(long timeCount) {


        return timeCount % 10;
    }


    public float calculateDistanceBetweenDriverLocationAndStartPlace(LatLng var0 , LatLng var1) {

       return AMapUtils.calculateLineDistance(var0,var1);

    }
}
