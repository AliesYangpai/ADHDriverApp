package com.adhdriver.work.logic;

import android.text.TextUtils;

import com.adhdriver.work.entity.driver.pay.aliAbout.AliAuthInfo;
import com.adhdriver.work.entity.driver.wallet.AliAuthRespose;
import com.adhdriver.work.entity.driver.wallet.Wallet;
import com.adhdriver.work.entity.driver.wallet.WxAuthInfo;

/**
 * Created by Administrator on 2018/1/10.
 * 类描述  钱包的逻辑判断类
 * 版本
 */

public class LogicWallet {


    /**
     * 已经进行支付宝授权
     * @param wallet
     * @return
     */
    public boolean isHasAuthAli(Wallet wallet) {

        boolean result = false;

        AliAuthInfo alipay_auth_info = wallet.getAlipay_auth_info();
        if(null != alipay_auth_info) {
            AliAuthRespose alipay_user_userinfo_share_response = alipay_auth_info.getAlipay_user_userinfo_share_response();
            if(null != alipay_user_userinfo_share_response) {
                result = true;
            }
        }

        return result;
    }


    /**
     * 已经进行支付宝授权
     * @param wallet
     * @return
     */
    public boolean isHasAuthWx(Wallet wallet) {

        boolean result = false;

        WxAuthInfo wx_pay_auth_info = wallet.getWx_pay_auth_info();


        if(null != wx_pay_auth_info) {
            result = true;
        }
        return result;
    }



    /**
     * 余额大于等于1元时才可提现
     * @param balance
     * @return
     */
    public boolean isHasBalance(String balance) {

        boolean result = false;
        if (!TextUtils.isEmpty(balance)) {
            float changebalance = Float.valueOf(balance);
            if (changebalance >= 1) {
                result = true;
            }
        }

        return result;

    }

    /**
     * 是否设置了提现密码
     * @param isHas_pay_pwd
     * @return
     */
    public boolean isHasSetDepositPass(boolean isHas_pay_pwd) {

        return isHas_pay_pwd;

    }


}
