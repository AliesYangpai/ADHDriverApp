package com.adhdriver.work.ui.iview.driver;

import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.ui.iview.IBaseView;

import java.util.List;

/**
 * Created by Administrator on 2017/12/27.
 * 类描述  跨城fg界面
 * 版本
 */

public interface IFgOrderNotTakeAllView extends IBaseView {





    void onDataBackFail(int code, String errorMsg);

    void onDataBackFailInLoadMore(int code, String errorMsg);

    void dismissFreshLoading();


    void onDataBackSuccessForAllOrders(List<Order> list);

    void onDataBackSuccessForAllOrdersInFresh(List<Order> list);

    void onDataBackSuccessForAllOrdersInLoadMore(List<Order> list);

    void onDataBackSuccessForBiddingSuccess();

    void onDataBackSuccessForOrderDetial(Order order);




}
