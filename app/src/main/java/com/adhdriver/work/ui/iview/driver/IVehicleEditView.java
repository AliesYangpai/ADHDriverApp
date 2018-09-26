package com.adhdriver.work.ui.iview.driver;

import com.adhdriver.work.entity.driver.vehicle.DriverVehicle;
import com.adhdriver.work.entity.driver.vehicle.VehicleCategory;
import com.adhdriver.work.ui.iview.IBaseView;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;

import java.util.List;

/**
 * Created by Administrator on 2018/1/16.
 * 类描述  车辆编辑界面的iview
 * 版本
 */

public interface IVehicleEditView extends IBaseView{



    void onDataBackFail(int code, String errorMsg);


    void doSetDataToUi(DriverVehicle driverVehicle);


    /**
     * 隐藏车辆布局
     */
    void doHindVehicleCatograyLayout();


    /**
     * 显示车辆类型布局
     */
    void doShowVehicleCatogoryLayout();


    /**
     * 设置本地数据到 界面 平板 箱车 高栏
     * @param vehicleCategories
     */
    void doSetLocalDataToRbGroup(List<VehicleCategory> vehicleCategories);


    void doSetCurrentCategoryNameToRb(String category);





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


    /**
     * 更新当前车辆的信息
     */
    void doUpdateIsCurrent();


    /**
     * 更新车辆
     */
    void doUpdate();


    /**
     * 更新车辆成功
     */
    void onDataBackSuccessForUpdateSuccess();


    /**
     * 更新车辆成功
     */
    void onDataBackSuccessForUpdateCurrentSuccess();


    void onDataBackSuccessForClosePush();


    void onDataBackSuccessForGetVisitorToken();
}
