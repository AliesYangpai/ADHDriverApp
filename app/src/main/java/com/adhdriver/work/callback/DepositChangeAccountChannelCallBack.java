package com.adhdriver.work.callback;

import com.adhdriver.work.entity.driver.pay.PayChannelInfo;

/**
 * Created by Administrator on 2018/1/11.
 * 类描述  变更提现渠道的account
 * 版本
 */

public interface DepositChangeAccountChannelCallBack {


    void doWxChangeDepositAccountCallBack(PayChannelInfo payChannelInfo);

    void doAliChangeDepositAccountCallBack(PayChannelInfo payChannelInfo);
}
