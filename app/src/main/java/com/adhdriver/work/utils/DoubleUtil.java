package com.adhdriver.work.utils;



import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.constant.ConstParams;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2017/10/10.
 * 类描述
 * 版本
 */

public class DoubleUtil {


    public static Double getDecima6(double data) {


        String format = new DecimalFormat("#.000000").format(data);


        return Double.valueOf(format);


    }


    /**
     * 获取司机的分成比例
     * @param businessType
     * @return
     */
    public static double getProportionByBusinessType(String businessType) {

        double proportion = ConstLocalData.SAME_CITY_PROPORTION;

        switch (businessType) {

            case ConstParams.Orders.SAME_CITY:// "同城0.85";
                proportion = ConstLocalData.SAME_CITY_PROPORTION;
                break;

            case ConstParams.Orders.PART_LOAD:// "零担0.85";
                proportion = ConstLocalData.PART_LOAD_PROPORTION;
                break;

            case ConstParams.Orders.FULL_LOAD:// "整车0.95";
                proportion = ConstLocalData.FULL_LOAD_PROPORTION;
                break;
        }

        return proportion;
    }
}
