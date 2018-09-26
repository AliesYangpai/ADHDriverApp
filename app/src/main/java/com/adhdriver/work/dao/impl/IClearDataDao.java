package com.adhdriver.work.dao.impl;

import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.entity.OssConfig;
import com.adhdriver.work.entity.User;
import com.adhdriver.work.entity.business.Business;
import com.adhdriver.work.entity.driver.Driver;
import com.adhdriver.work.entity.driver.vehicle.LogonVehicle;
import com.adhdriver.work.entity.driver.vehicle.VehicleType;
import com.adhdriver.work.test.TestContent;
import com.adhdriver.work.utils.SpUtil;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/11/23.
 * 类描述   清楚数据的Dao
 * 版本
 */

public class IClearDataDao {


    /**
     * 删除单个本地表
     * @param mClass
     */
    public void doClearSingleTable(Class<?> mClass) {

        DataSupport.deleteAll(mClass);

    }


    /**
     * 删除全部的表
     */
    public void doClearAllTable() {

        DataSupport.deleteAll(User.class);
        DataSupport.deleteAll(Driver.class);
        DataSupport.deleteAll(LogonVehicle.class);
        DataSupport.deleteAll(OssConfig.class);


    }


    /**
     * 删除全部本地标记
     */
    public void doClearSp() {


        /**
         * 1.标记退出
         */
        SpUtil.getInstance().saveBooleanTosp(ConstSp.SP_KEY_IS_LOGIN_OR_NOT, ConstSp.SP_VALUE.IS_LOGIN_DEFAULT);



        /**
         * 3.司机id清空
         */
        SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_DRIVER_ID, ConstSp.SP_VALUE.DEFAULT_STRING);





        /**
         * 5.登陆角色清空
         */

        SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_LOGIN_ROLE,ConstSp.SP_VALUE.DEFAULT_STRING);


        /**
         * 6.司机登陆的当前车辆id清空
         */

        SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_LOGON_DRIVER_VEHICLE_ID,ConstSp.SP_VALUE.DEFAULT_STRING);



        /**
         * 7.司机当前车辆的 catogray信息清空
         */

        SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_LOGON_DRIVER_CATEGORY,ConstSp.SP_VALUE.DEFAULT_STRING);


        /**
         * 8.是否已经仅仅了 keepLocation上传经纬度
         */


        SpUtil.getInstance().saveBooleanTosp(ConstSp.SP_KEY_HAS_KEEP_LOCATION,false);




        /**
         * 9.还原是否已经上传了 省市区地址标记
         */

        SpUtil.getInstance().saveBooleanTosp(ConstSp.SP_KEY_HAS_AREA_OR_NOT,ConstSp.SP_VALUE.DEFAULT_BOOLEAN);


        /**
         * 10 默认为收车
         */
        SpUtil.getInstance().saveBooleanTosp(ConstSp.SP_KEY_VEHICLE_DIPATCH,ConstSp.SP_VALUE.DEFAULT_BOOLEAN);

        if(TestContent.forTest) {




            SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_LOTITUDE_forTest,ConstSp.SP_VALUE.DEFAULT_STRING);
            SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_LATITUDE_forTest,ConstSp.SP_VALUE.DEFAULT_STRING);
            SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_ad_code_forTest,ConstSp.SP_VALUE.DEFAULT_STRING);
            SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_CUREENTPLACE_forTest,ConstSp.SP_VALUE.DEFAULT_STRING);

        }


    }



}
