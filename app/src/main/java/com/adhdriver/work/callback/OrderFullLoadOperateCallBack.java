package com.adhdriver.work.callback;

import com.adhdriver.work.entity.driver.orders.Order;

/**
 * Created by Administrator on 2018/1/25.
 * 类描述
 * 版本
 */

public interface OrderFullLoadOperateCallBack {


    void onFullLoadGetPhoneCode(Order order);

    void onFullLoadArrive(Order order);

    void onFullLoadDepart(Order order);


    void onFullLoadGetPayChannel();
}
