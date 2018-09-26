package com.adhdriver.work.utils;


import com.adhdriver.work.constant.ConstHz;
import com.adhdriver.work.constant.ConstParams;
import com.adhdriver.work.constant.ConstTag;

import org.feezu.liuli.timeselector.Utils.TextUtil;

/**
 * Created by Administrator on 2016/10/6 0006.
 * 类描述
 * 版本
 */
public class StringUtil {





    /**
     * 获取String arry
     *
     * @param string
     * @param position
     * @return
     */
    public static String[] getStringArry(String string, String position) {


        String[] arry = string.split(position);

        return arry;
    }




    /**
     * 根据orderType设置订单类型名称
     * @param orderType
     * @return
     */
    public static String getOrderTypeHz(String orderType) {

        String type = ConstHz.SAME_CITY;


        switch (orderType) {

            case ConstParams.Orders.SAME_CITY:// "同城";
                type = ConstHz.SAME_CITY;
                break;

            case ConstParams.Orders.PART_LOAD:// "零担";
                type = ConstHz.PART_LOAD;
                break;

            case ConstParams.Orders.FULL_LOAD:// "整车";
                type = ConstHz.FULL_LOAD;
                break;

            case ConstParams.Orders.OVER_OFFICE://"营业部"
                type = ConstHz.OVER_OFFICE;
        }


        return type;


    }


    /**
     * 获取收款方式的文字
     * @param payWay
     * @return
     */
    public static String getPayWayByPayer(String payWay) {

        String payWayName =  ConstHz.SHIPPER;


        switch (payWay) {

            case ConstParams.PaySide.RECIVER:


                payWayName = ConstHz.RECEIVER;

                break;

            case ConstParams.PaySide.SHIPPER:

                payWayName = ConstHz.SHIPPER;
                break;

        }
        return payWayName;
    }




    /**
     * kg转化为吨
     * @param value
     * @return
     */
    public static String valueKgToTun(String value) {


        String str= "";
        if(!TextUtil.isEmpty(value)) {

            float v = Float.valueOf(value) / 1000;

            str = String.valueOf(v);

        }

        return str;


    }


    /**
     * 加密电话号码中中间几位
     * @param phone
     * @return
     */
    public static String encodePartOfPhone(String phone) {

        StringBuilder sb = new StringBuilder(phone);

        if (!TextUtil.isEmpty(phone)) {

            sb.replace(3, 4, "*");
            sb.replace(4, 5, "*");
            sb.replace(5, 6, "*");

            sb.replace(6, 7, "*");


        }

        return sb.toString();

    }


    /**
     * 替换城市名称
     *
     * @param cityName
     * @return
     */
    public static String replaceTheLongCity(String cityName, String countryName) {

        String name = cityName;


        if (null != cityName && null != countryName) {

            if (cityName.length() > 7 || countryName.length() > 6) {

                name = "...";

            }

        }
        return name;
    }



    /**
     * 进度条5s倒计时
     * @param process
     * @return
     */
    public static String getCountback(int timeCount,int process) {

        int targetCount = timeCount * process / 100;


        return String.valueOf(targetCount);


    }


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

            case ConstTag.VechileCatogoryTag.BOX:// "箱车";
                type = ConstHz.BOX;
                break;

            case ConstTag.VechileCatogoryTag.SLAB:// "平板";
                type = ConstHz.SLAB;
                break;

            case ConstTag.VechileCatogoryTag.GAOLAN:// "高栏";
                type = ConstHz.GAOLAN;
                break;
        }


        return type;


    }

}

