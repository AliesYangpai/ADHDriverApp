package com.adhdriver.work.method;

import com.adhdriver.work.listener.OnDataBackListener;

/**
 * Created by Administrator on 2017/11/22.
 * 类描述 已接单
 * 版本
 */

public interface IOrderTake {


    void doGetOrderTake(String url,  int size, int index, OnDataBackListener onDataBackListener);

    void doGetOrderTakeInFresh(String url,int size, int index, OnDataBackListener onDataBackListener);

    void doGetOrderTakeInLoadMore(String url, int size, int index, OnDataBackListener onDataBackListener);

    void doGetOrderInfo(String url, OnDataBackListener onDataBackListener);


}
