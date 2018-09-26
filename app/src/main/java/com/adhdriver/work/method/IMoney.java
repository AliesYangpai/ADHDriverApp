package com.adhdriver.work.method;

import com.adhdriver.work.listener.OnDataBackListener;

/**
 * Created by Administrator on 2018/1/10.
 * 类描述  跟钱相关的接口，包括充值 和 提现等等 设置提现密码等等
 * 版本
 */

public interface IMoney {




    /**
     * 押金充值
     * @param url
     * @param order_type
     * @param amount
     * @param channel_id
     * @param os_type
     * @param device_type
     * @param device_id
     * @param payer
     * @param onDataBackListener
     */
    void doDepositRecharge(String url,
                           String order_type,
                           String amount,
                           String channel_id,
                           String os_type,
                           String device_type,
                           String device_id,
                           String payer, OnDataBackListener onDataBackListener);


    /**
     * 获取支付渠道
     *
     * @param url
     * @param order_type
     * @param os_type
     * @param device_type
     * @param onDataBackListener
     */
    void doGetPaymentsChannels(String url,
                               String order_type,
                               String os_type,
                               String device_type, OnDataBackListener onDataBackListener);


    /**
     * 零钱充值
     * @param url
     * @param order_type
     * @param amount
     * @param channel_id
     * @param os_type
     * @param device_type
     * @param device_id
     * @param payer
     * @param onDataBackListener
     */
    void doBalanceRecharge(String url,
                           String order_type,
                           String amount,
                           String channel_id,
                           String os_type,
                           String device_type,
                           String device_id,
                           String payer, OnDataBackListener onDataBackListener);


    /**
     * 设置提现密码
     * @param url
     * @param pass
     * @param onDataBackListener
     */
    void doSetDepositPass(String url,String pass,OnDataBackListener onDataBackListener);




    /**
     * 零钱提现
     * @param url
     * @param amount
     * @param payment_channel_id
     * @param os_type
     * @param device_id
     * @param open_id
     * @param pay_password
     * @param onDataBackListener
     */
    void doBalanceDeposit(String url,
                          String amount,
                          String payment_channel_id,
                          String os_type,
                          String device_id,
                          String open_id,
                          String pay_password,OnDataBackListener onDataBackListener) ;



}
