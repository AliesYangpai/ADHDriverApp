package com.adhdriver.work.logic;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstSign;
import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.constant.ConstTag;
import com.adhdriver.work.entity.driver.vehicle.DriverVehicle;
import com.adhdriver.work.entity.driver.vehicle.VehicleCategory;
import com.adhdriver.work.utils.SpUtil;
import com.adhdriver.work.utils.ToastUtil;

import org.feezu.liuli.timeselector.Utils.TextUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/12/11.
 * 类描述  车辆审核的逻辑
 * 版本
 */

public class LogicVehicle {


    /**
     * 没有填写车辆信息
     *
     * @param vehicleBrand
     * @return
     */
    public boolean isNoVehicleInfo(String vehicleBrand) {

        return TextUtil.isEmpty(vehicleBrand);

    }

    /**
     * 车辆认证认证通过
     *
     * @param certification
     * @return
     */
    public boolean isCarPassApproved(String certification) {

        return certification.equals(ConstTag.CarPassStatusTag.APPROVED);
    }

    /**
     * 车辆认证认证中
     *
     * @param certification
     * @return
     */
    public boolean isCarPassPending(String certification) {

        return certification.equals(ConstTag.CarPassStatusTag.PENDING);
    }

    /**
     * 车辆认证认证拒绝
     *
     * @param certification
     * @return
     */
    public boolean isCarPassRejected(String certification) {

        return certification.equals(ConstTag.CarPassStatusTag.REJECTED);
    }


    /**
     * 未填写车辆品牌
     *
     * @param brand
     * @return
     */
    public boolean isNullVehicleBrand(String brand) {

        return TextUtil.isEmpty(brand);
    }


    /**
     * 未填车辆类型
     * 这个在前面判断
     *
     * @param categoryName
     * @return
     */
    public boolean isNullVehicleBrandWithListAndCatogray(List<VehicleCategory> vehicleCategories, VehicleCategory vehicleCategory, String categoryName) {
        boolean result = false;
        if (null != vehicleCategories && null != vehicleCategory) {
            if (TextUtil.isEmpty(categoryName)) {
                result = true;

            }
        }
        return result;
    }


    /**
     * 未填写车辆类型
     *
     * @param vehicleCategories
     * @param vehicleCategory
     * @return
     */
    public boolean isNullVehicleBrandWithList(List<VehicleCategory> vehicleCategories, VehicleCategory vehicleCategory) {
        return null != vehicleCategories && null == vehicleCategory;

    }


    /**
     * 最大载重为null
     *
     * @param capacity
     * @return
     */
    public boolean isNullCapacity(String capacity) {
        return TextUtil.isEmpty(capacity);
    }


    /**
     * 最大载重载重量为0
     *
     * @param capacity
     * @return
     */
    public boolean is0ForCapacityHasPoint(String capacity) {

        boolean result = false;

        if (capacity.contains(ConstSign.POINT)) {
            Float aFloat = Float.valueOf(capacity);
            if (aFloat == 0) {
                result = true;
            }
        }
        return result;
    }


    /**
     * 最大载重量为0
     *
     * @param capacity
     * @return
     */
    public boolean is0ForCapacityNoPoint(String capacity) {

        boolean result = false;

        Integer integer = Integer.valueOf(capacity);

        if (integer == 0) {

            result = true;
        }
        return result;
    }


    /**
     * 最大体积为null
     *
     * @param volume
     * @return
     */
    public boolean isNullVolume(String volume) {
        return TextUtil.isEmpty(volume);
    }


    /**
     * 最大体积为0
     *
     * @param volume
     * @return
     */
    public boolean is0ForVolumeHasPoint(String volume) {

        boolean result = false;

        if (volume.contains(ConstSign.POINT)) {
            Float aFloat = Float.valueOf(volume);
            if (aFloat == 0) {
                result = true;
            }
        }
        return result;
    }


    /**
     * 最大载重量为0
     *
     * @param volume
     * @return
     */
    public boolean is0ForVolumeNoPoint(String volume) {

        boolean result = false;
        Integer integer = Integer.valueOf(volume);
        if (integer == 0) {
            result = true;
        }
        return result;
    }



    /**
     * 请上传驾驶证
     * @param vehicleNo
     * @return
     */
    public boolean isNullVehicleNo(String vehicleNo) {

        return TextUtil.isEmpty(vehicleNo);
    }



    /**
     * 请上传驾驶证
     * @param driverLicence
     * @return
     */
    public boolean isNullDriverLicence(String driverLicence) {

        return TextUtil.isEmpty(driverLicence);
    }




    /**
     * 请上传行驶证
     * @param vechleListence
     * @return
     */
    public boolean isNullVechleListence(String vechleListence) {

        return TextUtil.isEmpty(vechleListence);
    }

    /**
     * 请上传车头照片
     * @param vehiclePhotoFrontPath
     * @return
     */
    public boolean isNullVehiclePhotoFrontPath(String vehiclePhotoFrontPath) {

        return TextUtil.isEmpty(vehiclePhotoFrontPath);
    }

    /**
     * 请上传车尾照片
     * @param vehiclePhotoBackPath
     * @return
     */
    public boolean isNullVehiclePhotoBackPath(String vehiclePhotoBackPath) {

        return TextUtil.isEmpty(vehiclePhotoBackPath);
    }

    /**
     * 请上传交强保险单
     * @param vehicleInsurancePolicy
     * @return
     */
    public boolean isNullVehicleInsurancePolicy(String vehicleInsurancePolicy) {

        return TextUtil.isEmpty(vehicleInsurancePolicy);
    }

    /**
     * 请上传司机照片
     * @param driverPhotoPath
     * @return
     */
    public boolean isNullDriverPhotoPath(String driverPhotoPath) {

        return TextUtil.isEmpty(driverPhotoPath);
    }


    /**
     * 判断有无车辆类别
     * @param catogroy
     * @return
     */
    public boolean isNullCatogroy(String catogroy){
        return TextUtil.isEmpty(catogroy);
    }


    /**
     * 判断是否是当前登陆的车辆
     * @param driverVehicleId
     * @return
     */
    public boolean isCurrentLogonVehicle(int driverVehicleId) {

        String stringValue = SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_LOGON_DRIVER_VEHICLE_ID, ConstSp.SP_VALUE.DEFAULT_STRING);

        return stringValue.equals(String.valueOf(driverVehicleId));
    }


    /**
     * 审核通过
     * @return
     */
    public boolean isVertifyPass(String statu) {

        return statu.equals(ConstTag.CarPassStatusTag.APPROVED);


    }






}
