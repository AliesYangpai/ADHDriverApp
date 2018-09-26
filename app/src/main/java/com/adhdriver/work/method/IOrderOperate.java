package com.adhdriver.work.method;

import com.adhdriver.work.listener.OnDataBackListener;

/**
 * Created by Administrator on 2018/1/5.
 * 类描述  订单操作方法，包括竞价、抢单、到达、取消订单等的呢
 * 版本
 */

public interface IOrderOperate {


    void doStartBidding(String url,String price,OnDataBackListener onDataBackListener);

    void doStartGrab(String url,OnDataBackListener onDataBackListener);


    void doDepartSameCity(String url ,double longitude,double latitude,OnDataBackListener onDataBackListener);
    void doDepartFullLoad(String url ,double longitude,double latitude,OnDataBackListener onDataBackListener);
    void doDepartOverOffice(String url ,OnDataBackListener onDataBackListener);




    void doArriveSameCityNext(String url ,double longitude,double latitude,OnDataBackListener onDataBackListener);
    void doArriveSameCity(String url,OnDataBackListener onDataBackListener);
    void doArriveFullLoad(String url,OnDataBackListener onDataBackListener);
    void doArriveOverOffice(String url,OnDataBackListener onDataBackListener);

    void doFinishOrder(String url,String pass_code,OnDataBackListener onDataBackListener);

}
