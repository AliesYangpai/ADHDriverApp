package com.adhdriver.work.callback;

import com.adhdriver.work.entity.driver.pay.PayChannelInfo;

/**
 * Created by Administrator on 2018/1/11.
 * 类描述   提现渠道选择 微信/支付宝
 * 版本
 */

public interface DepositChannelCallBack {


    void doWxDepositCallBack(PayChannelInfo payChannelInfo);

    void doAliDepositCallBack(PayChannelInfo payChannelInfo);
}
