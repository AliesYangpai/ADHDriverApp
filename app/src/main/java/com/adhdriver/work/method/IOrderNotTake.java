package com.adhdriver.work.method;

import com.adhdriver.work.listener.OnDataBackListener;

/**
 * Created by Administrator on 2017/12/27.
 * 类描述  没有接单时的方法
 * 版本
 */

public interface IOrderNotTake {



    /**
     * 获取订单详情
     * @param url
     * @param onDataBackListener
     */
    void doGetOrderDetail(String url,OnDataBackListener onDataBackListener);


    /**
     * ===============================跨城===========================================
     */

    /**
     * 服务端获取宽城订单
     * @param url
     * @param size
     * @param index
     */
    void doGetAcrossCityOrders(String url,  int size, int index, OnDataBackListener onDataBackListener);

    /**
     * 下拉差量更新
     * @param url
     * @param size
     * @param index
     */
    void doGetAcrossCityOrdersInFresh(String url, int size, int index, OnDataBackListener onDataBackListener);


    /**
     * 上拉加载更多
     * @param url
     * @param size
     * @param index
     */
    void doGetAcrossCityOrdersInLoadMore(String url,  int size, int index, OnDataBackListener onDataBackListener);





    /**
     * ===================================全部列表==========================================================
     */


    /**
     * 服务端获取全部订单
     * @param url
     * @param
     * @param size
     * @param index
     */
    void doGetAllOrders(String url,int size, int index, OnDataBackListener onDataBackListener);

    /**
     * 下拉差量更新
     * @param url
     * @param
     * @param size
     * @param index
     */
    void doGetAllOrdersInFresh(String url, int size, int index, OnDataBackListener onDataBackListener);


    /**
     * 上拉加载更多
     * @param url
     * @param
     * @param size
     * @param index
     */
    void doGetAllOrdersInLoadMore(String url,  int size, int index, OnDataBackListener onDataBackListener);



}
