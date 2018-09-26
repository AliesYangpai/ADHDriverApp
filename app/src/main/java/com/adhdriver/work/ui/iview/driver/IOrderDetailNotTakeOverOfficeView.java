package com.adhdriver.work.ui.iview.driver;

import com.adhdriver.work.entity.driver.orders.AdditionService;
import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.ui.iview.IBaseView;

import java.util.List;

/**
 * Created by Administrator on 2018/1/5.
 * 类描述  未接单时，获取营业部订单详情IvIEW
 * 版本
 */

public interface IOrderDetailNotTakeOverOfficeView extends IBaseView {


    void onDataBackFail(int code, String errorMsg);

    void onDataBackSuccessForOrderDetial(Order order);


    void onDataBackSuccessForGetOrderInfoOverOffice(Order order);

    void onDataBackSuccessForOverOfficeGoodsWeight(String weight);


    void onDataBackSuccessForOverPathPoints(String[] pathPoint);

    void onDataBackSuccessForOverOfficeAdditionService(List<AdditionService> additionServices);

    void doAdjustViewForAdditionService();

    void doHideViewWithOutAdditionService();

    void onDataBackSuccessForGrab();

}
