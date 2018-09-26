package com.adhdriver.work.ui.iview.driver;

import com.adhdriver.work.entity.User;
import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.entity.driver.vehicle.DriverVehicle;
import com.adhdriver.work.ui.iview.IBaseView;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 * 类描述  获取车辆列表的iview
 * 版本
 */

public interface IVehiclesView extends IBaseView{


    void onDataBackFail(int code, String errorMsg);

    void onDataBackFailInLoadMore(int code, String errorMsg);

    void dismissFreshLoading();


    void onDataBackSuccessForVehicles(List<DriverVehicle> list);

    void onDataBackSuccessForVehiclesInFresh(List<DriverVehicle> list);

    void onDataBackSuccessForVehiclesInLoadMore(List<DriverVehicle> list);


    /**
     * 切换车辆成功并且一斤得到token
     */
    void onDataBackSuccessForSwitchAndGetToken();


    /**
     *
     */
    void onDataBackSuccessForUpLoadNewVehicleLocation();

    /**
     * 获取userInfo信息
     * @param
     */
    void onDataBackSuccessForGetUserInfo();

    void doShowSwitchAlertDialog(DriverVehicle driverVehicle);
}
