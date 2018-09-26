package com.adhdriver.work.ui.iview.driver;

import android.widget.ListView;

import com.adhdriver.work.entity.driver.pay.PayChannelInfo;
import com.adhdriver.work.entity.driver.pay.aliAbout.AliAuthInfo;
import com.adhdriver.work.entity.driver.pay.aliAbout.PayNecessaryInfo;
import com.adhdriver.work.entity.driver.wallet.Wallet;
import com.adhdriver.work.entity.driver.wallet.WxAuthInfo;
import com.adhdriver.work.ui.iview.IBaseView;

import java.util.List;

/**
 * Created by Administrator on 2018/1/10.
 * 类描述
 * 版本
 */

public interface IWalletView extends IBaseView {


    void onDataBackFail(int code, String errorMsg);


    void dismissFreshLoading();


    void onDataBackSuccessForWalletToSetData(Wallet wallet);

    void onDataBackSuccessForShowChangeAccount();

    void onDataBackSuccessForHideChangeAccount();


    /**
     * 余额不足的客户端错误验证
     */
    void doVertifyErrorNoBalance();

    /**
     * 提示去设置提现密码
     */
    void doShowAlertToSetDepositPass();


    /**
     * 弹出提现dialog
     * @param payChannelInfoList
     */
    void doShowDepositDialog(List<PayChannelInfo> payChannelInfoList);


    /**
     * 弹出充值dialog
     * @param payChannelInfoList
     */
    void doShowRechargeDialog(List<PayChannelInfo> payChannelInfoList);



    /**
     * 弹出变更账户dialog
     * @param payChannelInfoList
     */
    void doShowChangeDepositAccountDialog(List<PayChannelInfo> payChannelInfoList);


    /**
     * 使用支付宝控件,调用获取支付宝授权信息
     * @param auth_info
     * @param
     * @param
     */
    void doRxAliAuthoration(String auth_info);



    /**
     * 使用支付宝控件,调用获取支付宝授权信息(绑定账户变更时候)
     * @param auth_info
     * @param
     * @param
     */
    void doRxAliAuthorationRebind(String auth_info,String channel_id);



    /**
     * 获取阿里授权成功，并获取到阿里用户信息（这之后进行界面跳转）
     * @param auth_info
     */
    void onDataBackSuccessForGetAliInfo(AliAuthInfo auth_info);




    /**
     * 获取微信授权成功，并获取到阿里用户信息（这之后进行界面跳转）
     * @param wxAuthInfo
     */
    void onDataBackSuccessForGetWxInfo(WxAuthInfo wxAuthInfo);

    /**
     * 跳转到提现界面进行提现
     */
    void doJumpDepositForAli();



    /**
     * 跳转到提现界面进行提现
     */
    void doJumpDepositForWx();

    /**
     * 弹出支付宝的输入充值金额的dialog
     * @param channelId
     */
    void doShowAliRechargeDialog(String channelId);




    /**
     * 使用支付宝控件,执行充值（支付操作）
     * @param payInfo
     * @param
     * @param
     */
    void doRxAliReCharge(String payInfo);


    /**
     * 使用微信api进行充值
     * @param payNecessaryInfo
     */
    void doWxReCharge(PayNecessaryInfo payNecessaryInfo);


    /**
     * Ali验签通过
     */
    void onDataBackSuccessForAliConfirm();




    /**
     * wx验签通过
     */
    void onDataBackSuccessForWxConfirm();


    /**
     * 微信验签失败 【由于wx验签的时候需要加上 paymentID 根据微信 sdk的特性无法回传回来，所以这里加入一个微信验签失败的iview 】
     * @param code
     * @param errorMsg
     */
    void onDataBackFailForWxConfirm(int code, String errorMsg);


    /**
     * 弹出微信的输入充值金额的dialog
     * @param channelId
     */
    void doShowWxRechargeDialog(String channelId);



    void doGetWxCodeInLoginWay();


}
