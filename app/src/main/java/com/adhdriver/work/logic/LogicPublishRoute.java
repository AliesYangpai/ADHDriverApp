package com.adhdriver.work.logic;

import com.amap.api.services.core.AMapException;

/**
 * Created by Alie on 2017/12/2.
 * 类描述
 * 版本
 */

public class LogicPublishRoute {


    /**
     * 地理编码回调成功
     * @param rCode
     * @return
     */
    public boolean isMapCallbackSuccess(int rCode) {
        return rCode == AMapException.CODE_AMAP_SUCCESS;
    }


    public boolean isAdCodeLenthBigThan5(String adCode) {

        return adCode.length() >=5 ;

    }

}
