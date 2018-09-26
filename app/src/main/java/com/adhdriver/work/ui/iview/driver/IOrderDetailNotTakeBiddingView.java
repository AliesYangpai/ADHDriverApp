package com.adhdriver.work.ui.iview.driver;

import com.adhdriver.work.entity.driver.orders.AdditionService;
import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.ui.iview.IBaseView;

import java.util.List;

/**
 * Created by Administrator on 2018/1/5.
 * 类描述  未接单时，竞价的详情界面
 * 版本
 */

public interface IOrderDetailNotTakeBiddingView extends IBaseView{


    void onDataBackFail(int code, String errorMsg);

    void onDataBackSuccessForOrderDetial(Order order);

    void onDataBackSuccessForBiddingSuccess();


    /**
     * 订单相关
     * @param order
     */
    void onDataBackSuccessForGetOrderInfoFullLoad(Order order);

    /**
     * 货物相关
     * @param
     */
    void onDataBackSuccessForFullLoadGoodsWeight(String weight);

    /**
     * 额外服务
     * @param additionServices
     */
    void onDataBackSuccessForFullLoadAdditionService(List<AdditionService> additionServices);

    /**
     * 调整view高度
     */
    void doAdjustViewForAdditionService();

    /**
     * 隐藏additionService
     */
    void doHideViewWithOutAdditionService();


}
