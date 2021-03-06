package com.adhdriver.work.ui.iview.driver;

import com.adhdriver.work.entity.driver.vehicle.DriverVehicle;
import com.adhdriver.work.entity.driver.vehicle.VehicleCategory;
import com.adhdriver.work.entity.driver.vehicle.VehicleType;
import com.adhdriver.work.ui.iview.IBaseView;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;

import java.util.List;


/**
 * Created by Administrator on 2018/1/15.
 * 类描述  车辆详情界面
 * 版本
 */

public interface IVehicleAddView extends IBaseView {



    void onDataBackFail(int code, String errorMsg);

    void onDataBackSuccessForAddVehicle();



    void onDataBackSuccessForVehicleTypes(List<VehicleType> vehicleTypes);




    /**
     * 显示车辆类型布局
     */
    void doShowVehicleCatogoryLayout();


    /**
     * 将catogry加入到radioGroup中
     */
    void doSetVehicleCatogoriesToRG(List<VehicleCategory> vehicleCategories);

    /**
     * 清空选择
     */
    void doClearCatogroyCheck();


    /**
     * 请选择车辆品牌
     */
    void doVertifyErrorNullForVehicleBrand();




    /**
     * 请选择车辆类型
     */
    void doVertifyErrorNullForVehicleType();

    /**
     * 请输入最大载重
     */
    void doVertifyErrorNullForVehicleCapacity();



    /**
     * 最大载重不能为0
     */
    void doVertifyErrorNot0ForVehicleCapacity();

    /**
     * 请输入车辆体积
     */
    void doVertifyErrorNullForVehicleVolume();


    /**
     * 车辆体积不能为0
     */
    void doVertifyErrorNot0ForVehicleVolume();


    /**
     * 请收入车牌号
     */
    void doVertifyErrorNullForVehicleNum();


    /**
     * 请上传行驶证
     */
    void doVertifyErrorNullForVehicleLicence();


    /**
     * 请上传车头照片
     */
    void doVertifyErrorNullForVehiclePhotoFrontPath();

    /**
     * 请上传车尾照片
     */
    void doVertifyErrorNullForVehiclePhotoBackPath();

    /**
     * 请上传交强保险单
     */
    void doVertifyErrorNullForVehicleInsurancePolicy();



    /**
     * 权限检查
     */

    void doPermissionCheck();

    /**
     * 关闭权限时候的打开提醒
     */
    void doShowPermissionAlert();


    /**
     * 弹出控件显示
     */
    void doShowPhotoPopWindow();

    /**
     * 上传中....
     * @param request
     * @param currentSize
     * @param totalSize
     */
    void ossOnProgress(PutObjectRequest request, long currentSize, long totalSize);


    /**
     * 图片上传失败
     */
    void ossOnFailure();



    /**
     * 行驶证
     * @param url
     * @param drawableId
     */
    void ossOnSuccessVehicleLicensToUi(String url, int drawableId);


    /**
     * 车前照片
     * @param url
     * @param drawableId
     */
    void ossOnSuccessVehiclePhotoFrontToUi(String url, int drawableId);

    /**
     * 车尾照片
     * @param url
     * @param drawableId
     */
    void ossOnSuccessVehiclePhotoBackToUi(String url, int drawableId);

    /**
     * 交强保险
     * @param url
     * @param drawableId
     */
    void ossOnSuccessVehicleInsurancePolicyToUi(String url, int drawableId);


    /**
     * 商业保险单
     * @param url
     * @param drawableId
     */
    void ossOnSuccessBusinessInsuranceToUi(String url, int drawableId);



}
