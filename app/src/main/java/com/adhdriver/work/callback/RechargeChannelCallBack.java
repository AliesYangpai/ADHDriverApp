package com.adhdriver.work.callback;

import com.adhdriver.work.entity.driver.pay.PayChannelInfo;

/**
 * Created by Administrator on 2018/1/11.
 * 类描述  充值用于弹出 输入价格框的dialog,用于微信 和 支付宝的充值
 * 版本
 */

public interface RechargeChannelCallBack {





    void doRechargeChannelCallBack(String channelId);


}
