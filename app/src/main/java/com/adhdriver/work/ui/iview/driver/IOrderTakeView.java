package com.adhdriver.work.ui.iview.driver;

import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.ui.iview.IBaseView;

import java.util.List;

/**
 * Created by Administrator on 2018/1/8.
 * 类描述  行程界面的iView
 * 版本
 */

public interface IOrderTakeView extends IBaseView{




    void onDataBackFail(int code, String errorMsg);

    void onDataBackFailInLoadMore(int code, String errorMsg);

    void dismissFreshLoading();


    void onDataBackSuccessForOrderTakeOrders(List<Order> list);

    void onDataBackSuccessForOrderTakeInFresh(List<Order> list);

    void onDataBackSuccessForOrderTakeInLoadMore(List<Order> list);


    /**
     * 订单获取验证相关
     */


    void onDataBackSuccessForSameCityComplete(Order order);
    void onDataBackSuccessForSameCityCancel(Order order);
    void onDataBackSuccessForSameCityRefund(Order order);
    void onDataBackSuccessForSameCityNotDepart(Order order);
    void onDataBackSuccessForSameCityArriveAndHasPaid(Order order);
    void onDataBackSuccessForSameCityArriveAndNotPaid(Order order);
    void onDataBackSuccessForSameCityDepartAndNotArrive(Order order);
    void onDataBackSuccessForSameCityAutoDepart(Order order);
    void onDataBackSuccessForSameCityNotInAutoDepartArea(Order order);




    void onDataBackSuccessForFullLoadComplete(Order order);
    void onDataBackSuccessForFullLoadCancel(Order order);
    void onDataBackSuccessForFullLoadRefund(Order order);
    void onDataBackSuccessForFullLoadNotDepart(Order order);
    void onDataBackSuccessForFullLoadArriveAndHasPaid(Order order);
    void onDataBackSuccessForFullLoadArriveAndNotPaid(Order order);
    void onDataBackSuccessForFullLoadDepartAndNotArrive(Order order);
    void onDataBackSuccessForFullLoadAutoDepart(Order order);
    void onDataBackSuccessForFullLoadNotInAutoDepartArea(Order order);




    void onDataBackSuccessForOverOfficeComplete(Order order);
    void onDataBackSuccessForOverOfficeNotDepart(Order order);
    void onDataBackSuccessForOverOfficeDepartAndNotArrive(Order order);
    void onDataBackSuccessForOverOfficeArrive(Order order);
    void onDataBackSuccessForOverOfficeAutoDepart(Order order);
    void onDataBackSuccessForOverOfficeNotInAutoDepartArea(Order order);

}
