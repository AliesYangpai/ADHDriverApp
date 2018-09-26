package com.adhdriver.work.ui.iview.driver;

import com.adhdriver.work.entity.driver.orders.AdditionService;
import com.adhdriver.work.entity.driver.orders.Order;

import java.util.List;

/**
 * Created by Administrator on 2018/1/24.
 * 类描述
 * 版本
 */

public interface IOrderTakeDetailFinishView {

    /**
     * 订单相关
     * 同城
     * @param order
     */
    void onDataBackSuccessForSameCityOrderInfo(Order order);

    void onDataBackSuccessForSameCityDriveDivide(String driverDivide);
    void onDataBackSuccessForSameCityAdditionService(List<AdditionService> additionServices);




    /**
     * 营业部
     * 订单相关
     * @param order
     */
    void onDataBackSuccessForOverOfficeGetOrderInfo(Order order);
    void onDataBackSuccessForOverOfficeDriveDivide(String driverDivide);
    void onDataBackSuccessForOverOfficeAdditionService(List<AdditionService> additionServices);







    /**
     * 整车
     * 订单相关
     * @param order
     */
    void onDataBackSuccessForFullLoadGetOrderInfo(Order order);
    void onDataBackSuccessForFullLoadDriveDivide(String driverDivide);
    void onDataBackSuccessForFullLoadAdditionService(List<AdditionService> additionServices);





    /**
     * 调整view高度
     */
    void doAdjustViewForAdditionService();

    /**
     * 隐藏additionService
     */
    void doHideViewWithOutAdditionService();


    void doSetPathPointToUi(String[] arry);
}
