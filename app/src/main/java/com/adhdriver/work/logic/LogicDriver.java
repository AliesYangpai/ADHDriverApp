package com.adhdriver.work.logic;

import org.feezu.liuli.timeselector.Utils.TextUtil;

/**
 * Created by Administrator on 2018/1/17.
 * 类描述
 * 版本
 */

public class LogicDriver {


    /**
     * 省市区
     * @param provinceId
     * @return
     */
    public boolean isNotNullProvice(String provinceId) {
        return !TextUtil.isEmpty(provinceId);
    }

    public boolean isNotNullConmmunity(String conmmunityId) {
        return !TextUtil.isEmpty(conmmunityId);
    }

    public boolean isNotNullCounty(String countyId) {
        return !TextUtil.isEmpty(countyId);
    }

}
