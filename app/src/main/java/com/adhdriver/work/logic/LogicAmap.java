package com.adhdriver.work.logic;

import com.adhdriver.work.AiDaiHuoApplication;
import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstLog;
import com.adhdriver.work.utils.ToastUtil;
import com.adhdriver.work.utils.VersionUtil;
import com.amap.api.location.AMapLocation;

import org.feezu.liuli.timeselector.Utils.TextUtil;

import cn.jesse.nativelogger.NLogger;

/**
 * Created by Administrator on 2018/1/2.
 * 类描述  高德地图逻辑类
 * 版本
 */

public class LogicAmap {


    public boolean isNotNullAmapLocation(AMapLocation aMapLocation) {

        return null != aMapLocation;

    }


    public boolean isNot0Lng(double lng) {

        return lng != 0;
    }

    public boolean is0Lat(double lat) {

        return lat != 0;
    }

    public boolean isNotNullForAdCode(String adCode) {
        return !TextUtil.isEmpty(adCode);
    }


    /**
     * 大于1000
     * @param meter
     * @return
     */
    public boolean isDistanceBiggerThan1000(float meter) {

        return meter >= 1000f;
    }

}
