package com.adhdriver.work.logic;

import org.feezu.liuli.timeselector.Utils.TextUtil;

/**
 * Created by Administrator on 2018/1/2.
 * 类描述  一键加油的实体类
 * 版本
 */

public class LogicRefuel {


    public boolean isNullStartLat(String startLat) {

        return TextUtil.isEmpty(startLat);

    }



    public boolean isNullStartLnt(String startLnt) {

        return TextUtil.isEmpty(startLnt);

    }

    public boolean isNullGasStationLat(String endLat) {
        return TextUtil.isEmpty(endLat);
    }


    public boolean isNullGasStationLng(String endLnt) {

        return TextUtil.isEmpty(endLnt);
    }

}
