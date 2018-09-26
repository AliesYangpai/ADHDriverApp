package com.adhdriver.work.callback;

import com.adhdriver.work.entity.driver.orders.Order;

/**
 * Created by Administrator on 2018/1/25.
 * 类描述
 * 版本
 */

public interface OrderSameCityOperateCallBack {


    void onSameCityGetPhoneCode(Order order);

    void onSameCityArrive(Order order);


    void onSameCityArriveNext(Order order);

    void onSameCityDepart(Order order);


    void onSameCityGetPayChannel();
}
