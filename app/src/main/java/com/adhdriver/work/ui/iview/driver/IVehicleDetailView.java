package com.adhdriver.work.ui.iview.driver;

import com.adhdriver.work.entity.driver.vehicle.DriverVehicle;
import com.adhdriver.work.ui.iview.IBaseView;


/**
 * Created by Administrator on 2018/1/15.
 * 类描述  车辆详情界面
 * 版本
 */

public interface IVehicleDetailView extends IBaseView {



    void onDataBackFail(int code, String errorMsg);


    /**
     * 有catogory
     * @param catogory
     */
    void onDataBackSuccessForShowVehicleCatogory(String catogory);


    /**
     * 无catogory
     */
    void onDataBackSuccessForHideVehicleCatogory();


    /**
     * 审核中
     */
    void onDatBackSuccessForVehicleVertifyPending();

    /**
     * 审核通过
     *
     */
    void onDatBackSuccessForVehicleVertifyApproved();



    /**
     * 审核拒绝
     *
     */
    void onDatBackSuccessForVehicleVertifyRejected();


    void onDataBackSuccessForVehicleDetail(DriverVehicle driverVehicle);

    void onDataBackSuccessForDelete();

}
