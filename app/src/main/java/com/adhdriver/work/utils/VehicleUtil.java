package com.adhdriver.work.utils;

import com.adhdriver.work.constant.ConstHz;
import com.adhdriver.work.constant.ConstLocalData;

import org.feezu.liuli.timeselector.Utils.TextUtil;

/**
 * Created by Administrator on 2017/11/20.
 * 类描述  车辆工具类
 * 版本
 */

public class VehicleUtil {



    /**
     * 根据车辆类型mark 获取车辆类型文字描述
     * @param vehicleType
     * @return
     */
    public static String getVehicleTypeDescriptionHz(String vehicleType) {




        String type = "";


        if(TextUtil.isEmpty(vehicleType)) {

            return type;

        }


        switch (vehicleType) {

            case ConstLocalData.BOX:// "箱车";
                type = ConstHz.BOX;
                break;

            case ConstLocalData.SLAB:// "平板";
                type = ConstHz.SLAB;
                break;

            case ConstLocalData.GAOLAN:// "高栏";
                type = ConstHz.GAOLAN;
                break;
        }


        return type;


    }

}
