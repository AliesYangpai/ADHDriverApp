package com.adhdriver.work.method;


import com.adhdriver.work.listener.OnDataBackListener;

/**
 * Created by Administrator on 2017/11/20.
 * 类描述  我的钱包相关方法 方法
 * 版本
 */

public interface IWallet {


    /**
     * 获取每日收益
     *
     * @param onDataBackListener
     */
    void doGetDailyInCome(String url, OnDataBackListener onDataBackListener);


    /**
     * 获取钱包详情
     * @param url
     * @param onDataBackListener
     */
    void doGetWalletInfo(String url,OnDataBackListener onDataBackListener);




    /**
     * 获取零钱明细列表
     * @param url
     * @param size
     * @param index
     */
    void doGetWalletInfos(String url,int size, int index, OnDataBackListener onDataBackListener);

    /**
     * 下拉差量更新
     * @param url
     * @param size
     * @param index
     */
    void doGetWalletInfosInFresh(String url,  int size, int index, OnDataBackListener onDataBackListener);


    /**
     * 上拉加载更多
     * @param url
     * @param size
     * @param index
     */
    void doGetWalletInfosInLoadMore(String url, int size, int index, OnDataBackListener onDataBackListener);



}
