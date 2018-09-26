package com.adhdriver.work.callback;

import com.adhdriver.work.entity.driver.orders.Order;

/**
 * Created by Administrator on 2018/1/25.
 * 类描述
 * 版本
 */

public interface OrderOverOfficeOperateCallBack {


    void onOverOfficeDepart(Order order);

    void onOverOfficeArrive(Order order);
}
