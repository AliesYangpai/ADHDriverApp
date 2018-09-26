package com.adhdriver.work.logic;

import com.adhdriver.work.R;

/**
 * Created by Administrator on 2017/12/22.
 * 类描述   图片相关逻辑
 * 版本
 */

public class LogicPhoto {

    public LogicPhoto() {
    }


    /**
     * 注册 时上传车辆信息，判断是否是 交强险
     * @param id
     * @return
     */
    public boolean isVehicleInsurancePolicyInRegVehicle(int id) {
        return id == R.id.iv_upload_line3_left;
    }



    /**
     * 添加车辆 时上传车辆信息，判断是否是 交强险
     * @param id
     * @return
     */
    public boolean isVehicleInsurancePolicyInAddVehicle(int id) {
        return id == R.id.iv_upload_line2_left;
    }



}
