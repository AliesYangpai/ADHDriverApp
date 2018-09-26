package com.adhdriver.work.presenter.driver;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.dao.IBaseDao;
import com.adhdriver.work.dao.impl.IOssConfigDaoImpl;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.OssConfig;
import com.adhdriver.work.entity.driver.pay.PayChannelInfo;
import com.adhdriver.work.entity.driver.pay.aliAbout.AuthResult;
import com.adhdriver.work.entity.driver.pay.aliAbout.AliAuthInfo;
import com.adhdriver.work.entity.driver.pay.aliAbout.PayNecessaryInfo;
import com.adhdriver.work.entity.driver.wallet.Wallet;
import com.adhdriver.work.entity.driver.wallet.WxAuthInfo;
import com.adhdriver.work.function.FunctionPayChannel;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.logic.LogicMoney;
import com.adhdriver.work.logic.LogicWallet;
import com.adhdriver.work.method.IMoney;
import com.adhdriver.work.method.IThirdPay;
import com.adhdriver.work.method.IWallet;
import com.adhdriver.work.method.impl.IMoneyImpl;
import com.adhdriver.work.method.impl.IThirdPayImpl;
import com.adhdriver.work.method.impl.IWalletImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IWalletView;
import com.adhdriver.work.verification.VertifyNotNull;
import com.google.gson.JsonObject;


import java.util.List;


/**
 * Created by Administrator on 2018/1/10.
 * 类描述
 * 版本
 */

public class PresenterDriverWallet extends BasePresenter<IWalletView> {


    private ParseSerilizable parseSerilizable;


    private IWalletView iWalletView;
    private IWallet iWallet;
    private IMoney iMoney;
    private IThirdPay iThirdPay;


    private VertifyNotNull vertifyNotNull;
    private LogicWallet logicWallet;
    private LogicMoney logicMoney;


    private FunctionPayChannel functionPayChannel;

    private IBaseDao<OssConfig> iOssDao;

    public PresenterDriverWallet(IWalletView iWalletView) {
        this.iWalletView = iWalletView;
        this.parseSerilizable = new ParseSerilizable();

        this.iWallet = new IWalletImpl();
        this.iMoney = new IMoneyImpl();
        this.iThirdPay = new IThirdPayImpl();

        this.vertifyNotNull = new VertifyNotNull();
        this.logicWallet = new LogicWallet();
        this.functionPayChannel = new FunctionPayChannel();
        this.iOssDao = new IOssConfigDaoImpl();
        this.logicMoney = new LogicMoney();
    }


    /**
     * 获取钱包信息
     *
     * @param url
     */
    public void doGetWalletInfo(String url) {

        iWallet.doGetWalletInfo(url, new OnDataBackListener() {
            @Override
            public void onStart() {
                iWalletView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                Wallet wallet = parseSerilizable.getParseToObj(data, Wallet.class);

                if (vertifyNotNull.isNotNullObj(wallet)) {

                    iWalletView.onDataBackSuccessForWalletToSetData(wallet);
                    if (logicWallet.isHasAuthAli(wallet)) {

                        iWalletView.onDataBackSuccessForShowChangeAccount();
                    } else {

                        iWalletView.onDataBackSuccessForHideChangeAccount();

                    }

                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iWalletView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iWalletView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {

                iWalletView.dismissLoadingDialog();
            }
        });

    }


    /**
     * 刷新获取钱包信息
     *
     * @param url
     */
    public void doGetWalletInfoInFresh(String url) {

        iWallet.doGetWalletInfo(url, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {

                Wallet wallet = parseSerilizable.getParseToObj(data, Wallet.class);

                if (vertifyNotNull.isNotNullObj(wallet)) {

                    iWalletView.onDataBackSuccessForWalletToSetData(wallet);


                    if (logicWallet.isHasAuthAli(wallet)) {

                        iWalletView.onDataBackSuccessForShowChangeAccount();
                    } else {

                        iWalletView.onDataBackSuccessForHideChangeAccount();

                    }

                }

            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iWalletView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iWalletView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {

                iWalletView.dismissFreshLoading();
            }
        });

    }


    /**
     * 提现dialog还是进行其他操作
     *
     * @param wallet
     * @param url
     * @param order_type
     * @param os_type
     * @param device_type
     */
    public void doDealToShowDepositDialogOrNot(Wallet wallet,
                                               String url,
                                               String order_type,
                                               String os_type,
                                               String device_type) {

        if (vertifyNotNull.isNullObj(wallet)) {
            return;
        }

        if (!logicWallet.isHasBalance(wallet.getBalance())) {

            iWalletView.doVertifyErrorNoBalance();

            return;
        }

        if (!logicWallet.isHasSetDepositPass(wallet.isHas_pay_pwd())) {


            iWalletView.doShowAlertToSetDepositPass();

            return;
        }

        iMoney.doGetPaymentsChannels(url, order_type, os_type, device_type, new OnDataBackListener() {
            @Override
            public void onStart() {
                iWalletView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                List<PayChannelInfo> list = parseSerilizable.getParseToNoItemsList(data, PayChannelInfo.class);

                if (vertifyNotNull.isNotNullListSize(list)) {

                    OssConfig ossConfig = iOssDao.findFirst(OssConfig.class);

                    if (vertifyNotNull.isNotNullObj(ossConfig)) {
                        list = functionPayChannel.getReSetChannelInfos(list, ossConfig);
                    }

                    iWalletView.doShowDepositDialog(list);

                } else {


                    iWalletView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iWalletView.onDataBackFail(code, errorEntity.getError_message());
                } else {

                    iWalletView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
                iWalletView.dismissLoadingDialog();
            }
        });


    }


    /**
     * 展示充值dialog
     *
     * @param url
     * @param order_type
     * @param os_type
     * @param device_type
     */
    public void doShowRechargeDialog(String url,
                                     String order_type,
                                     String os_type,
                                     String device_type) {


        iMoney.doGetPaymentsChannels(url, order_type, os_type, device_type, new OnDataBackListener() {
            @Override
            public void onStart() {
                iWalletView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                List<PayChannelInfo> list = parseSerilizable.getParseToNoItemsList(data, PayChannelInfo.class);

                if (vertifyNotNull.isNotNullListSize(list)) {

                    OssConfig ossConfig = iOssDao.findFirst(OssConfig.class);

                    if (vertifyNotNull.isNotNullObj(ossConfig)) {
                        list = functionPayChannel.getReSetChannelInfos(list, ossConfig);
                    }

                    iWalletView.doShowRechargeDialog(list);

                } else {


                    iWalletView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iWalletView.onDataBackFail(code, errorEntity.getError_message());
                } else {

                    iWalletView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
                iWalletView.dismissLoadingDialog();
            }
        });

    }


    /**
     * 支付宝提现
     *
     * @param wallet
     * @param channel_id
     * @param device_id
     * @param os_type
     * @param device_type
     */
    public void doCommitAliDeposit(Wallet wallet,
                                   String url,
                                   String channel_id,
                                   String device_id,
                                   String os_type,
                                   String device_type,
                                   JsonObject jsonObject) {


        if (logicWallet.isHasAuthAli(wallet)) {

            iWalletView.doJumpDepositForAli();

        } else {


            doAliPreAppAuthLogin(url, channel_id, device_id, os_type, device_type, jsonObject);


        }

    }


    /**
     * 支付宝提现
     *
     * @param wallet
     */
    public void doCommitWxDeposit(Wallet wallet) {


        if (logicWallet.isHasAuthWx(wallet)) {

            iWalletView.doJumpDepositForWx();

        } else {



            iWalletView.doGetWxCodeInLoginWay();
        }

    }


    public void doWxAuthoLogin(String url,
                               String channel_id,
                               String pass_word,
                               String device_id,
                               String os_type,
                               String device_type,
                               String auth_code,
                               JsonObject jsonObject) {



        iThirdPay.doWxGetAuthLoginInfo(
                url,
                channel_id,
                pass_word,
                device_id,
                os_type,
                device_type,
                auth_code,
                jsonObject, new OnDataBackListener() {
                    @Override
                    public void onStart() {
                        iWalletView.showLoadingDialog();
                    }

                    @Override
                    public void onSuccess(String data) {

                        WxAuthInfo wxAuthInfo = parseSerilizable.getParseToObj(data, WxAuthInfo.class);

                        if(vertifyNotNull.isNotNullObj(wxAuthInfo)) {
                            iWalletView.onDataBackSuccessForGetWxInfo(wxAuthInfo);
                        }
                    }

                    @Override
                    public void onFail(int code, String data) {

                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                        if (vertifyNotNull.isNotNullObj(errorEntity)) {
                            iWalletView.onDataBackFail(code, errorEntity.getError_message());
                        } else {

                            iWalletView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                        }
                    }

                    @Override
                    public void onFinish() {
                        iWalletView.dismissLoadingDialog();
                    }
                });


    }





    /**
     * 执行支付宝预授权【仅仅在presenterDriverWallet中调用】
     *
     * @param url
     * @param channel_id
     * @param device_id
     * @param os_type
     * @param device_type
     * @param jsonObject
     */
    private void doAliPreAppAuthLogin(String url,
                                      String channel_id,
                                      String device_id,
                                      String os_type,
                                      String device_type,
                                      JsonObject jsonObject) {


        iThirdPay.doAliPreAppAuthLogin(url, channel_id, device_id, os_type, device_type, jsonObject, new OnDataBackListener() {
            @Override
            public void onStart() {
                iWalletView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                String auth_info = parseSerilizable.getParseString(data, "auth_info");

                iWalletView.doRxAliAuthoration(auth_info);

            }

            @Override
            public void onFail(int code, String data) {


                iWalletView.dismissLoadingDialog();
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iWalletView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iWalletView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }


            }

            @Override
            public void onFinish() {

            }
        });
    }


    /**
     * 获取用户支付宝信息
     *
     * @param url
     * @param channel_id
     * @param pay_password
     * @param device_id
     * @param os_type
     * @param device_type
     * @param auth_code
     * @param jsonObject
     */
    public void doGetAliPayInfo(String url,
                                String channel_id,
                                String pay_password,
                                String device_id,
                                String os_type,
                                String device_type,
                                String auth_code,
                                JsonObject jsonObject,
                                final Wallet wallet) {

        iThirdPay.doAliGetAuthLoginInfo(
                url,
                channel_id,
                pay_password,
                device_id,
                os_type,
                device_type,
                auth_code,
                jsonObject, new OnDataBackListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(String data) {


                        AliAuthInfo aliAuthInfo = parseSerilizable.specialGetAliAuthInfoBack(data);
                        if (vertifyNotNull.isNotNullObj(aliAuthInfo) &&
                                vertifyNotNull.isNotNullObj(wallet)) {

                            iWalletView.onDataBackSuccessForGetAliInfo(aliAuthInfo);
                        }

                    }

                    @Override
                    public void onFail(int code, String data) {
                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                        if (vertifyNotNull.isNotNullObj(errorEntity)) {
                            iWalletView.onDataBackFail(code, errorEntity.getError_message());
                        } else {
                            iWalletView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                        }
                    }

                    @Override
                    public void onFinish() {

                        iWalletView.dismissLoadingDialog();

                    }
                });


    }


    /**
     * 支付宝充值请求
     *
     * @param url
     * @param order_type
     * @param amount
     * @param channel_id
     * @param os_type
     * @param device_type
     * @param device_id
     * @param payer
     */
    public void doAliBalanceRecharge(String url,
                                     String order_type,
                                     String amount,
                                     String channel_id,
                                     String os_type,
                                     String device_type,
                                     String device_id,
                                     String payer) {

        iMoney.doBalanceRecharge(
                url,
                order_type,
                amount,
                channel_id,
                os_type,
                device_type,
                device_id,
                payer, new OnDataBackListener() {
                    @Override
                    public void onStart() {
                        iWalletView.showLoadingDialog();
                    }

                    @Override
                    public void onSuccess(String data) {

                        PayNecessaryInfo payNecessaryInfo = parseSerilizable.getParseToObj(data, PayNecessaryInfo.class);

                        if (vertifyNotNull.isNotNullObj(payNecessaryInfo)) {

                            iWalletView.doRxAliReCharge(payNecessaryInfo.getAction_arguments());
                        }

                    }

                    @Override
                    public void onFail(int code, String data) {


                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                        if (vertifyNotNull.isNotNullObj(errorEntity)) {
                            iWalletView.onDataBackFail(code, errorEntity.getError_message());
                        } else {
                            iWalletView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                        }


                    }

                    @Override
                    public void onFinish() {
                        iWalletView.dismissLoadingDialog();
                    }
                });
    }


    /**
     * 微信充值请求
     *
     * @param url
     * @param order_type
     * @param amount
     * @param channel_id
     * @param os_type
     * @param device_type
     * @param device_id
     * @param payer
     */
    public void doWxBalanceRecharge(String url,
                                    String order_type,
                                    String amount,
                                    String channel_id,
                                    String os_type,
                                    String device_type,
                                    String device_id,
                                    String payer) {

        iMoney.doBalanceRecharge(
                url,
                order_type,
                amount,
                channel_id,
                os_type,
                device_type,
                device_id,
                payer, new OnDataBackListener() {
                    @Override
                    public void onStart() {
                        iWalletView.showLoadingDialog();
                    }

                    @Override
                    public void onSuccess(String data) {

                        PayNecessaryInfo payNecessaryInfo = parseSerilizable.getParseToObj(data, PayNecessaryInfo.class);

                        if (vertifyNotNull.isNotNullObj(payNecessaryInfo)) {

                            iWalletView.doWxReCharge(payNecessaryInfo);

                        }

                    }

                    @Override
                    public void onFail(int code, String data) {


                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                        if (vertifyNotNull.isNotNullObj(errorEntity)) {
                            iWalletView.onDataBackFail(code, errorEntity.getError_message());
                        } else {
                            iWalletView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                        }


                    }

                    @Override
                    public void onFinish() {
                        iWalletView.dismissLoadingDialog();
                    }
                });
    }


    /**
     * 根据channelId展示出输入充值价格的dialog
     *
     * @param channelId
     */
    public void doShowRechargeDilogByChannel(String channelId) {


        if (logicMoney.isAliChannel(channelId)) {

            iWalletView.doShowAliRechargeDialog(channelId);


        } else if (logicMoney.isWxChannel(channelId)) {

            iWalletView.doShowWxRechargeDialog(channelId);

        }


    }


    /**
     * 支付宝充值后的验签
     *
     * @param url
     * @param resultInfo
     * @param app_type
     * @param os_type
     * @param payer
     */
    public void doAliCheckAliPayResultInfo(String url,
                                           String resultInfo,
                                           String app_type,
                                           String os_type,
                                           String payer) {
        iThirdPay.doAliCheckAliPayResultInfo(
                url,
                resultInfo,
                app_type,
                os_type,
                payer, new OnDataBackListener() {
                    @Override
                    public void onStart() {
                        iWalletView.showLoadingDialog();
                    }

                    @Override
                    public void onSuccess(String data) {


                        if (vertifyNotNull.isNotNullString(data)) {

                            if (Boolean.valueOf(data)) {


                                iWalletView.onDataBackSuccessForAliConfirm();

                            } else {
                                iWalletView.onDataBackFail(ConstError.ERROR_ALI_CONFIREM_CODE, ConstError.ERROR_ALI_CONFIREM_MSG);

                            }
                        }

                    }

                    @Override
                    public void onFail(int code, String data) {

                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                        if (null != errorEntity) {
                            iWalletView.onDataBackFail(code, errorEntity.getError_message());
                        } else {
                            iWalletView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                        }


                    }

                    @Override
                    public void onFinish() {
                        iWalletView.dismissLoadingDialog();
                    }
                });
    }


    /**
     * 微信验签
     *
     * @param url
     */
    public void doWxCheckWxPayResultInfo(String url,
                                         String app_type,
                                         String os_type,
                                         String payment_record_id,
                                         String payer) {

        iThirdPay.doWxCheckWxPayResultInfo(
                url,
                app_type,
                os_type,
                payment_record_id,
                payer, new OnDataBackListener() {
                    @Override
                    public void onStart() {
                        iWalletView.showLoadingDialog();
                    }

                    @Override
                    public void onSuccess(String data) {


                        if (vertifyNotNull.isNotNullString(data)) {

                            if (Boolean.valueOf(data)) {

                                iWalletView.onDataBackSuccessForWxConfirm();

                            } else {
                                iWalletView.onDataBackFailForWxConfirm(ConstError.ERROR_WX_CONFIREM_CODE, ConstError.ERROR_WX_CONFIREM_MSG);

                            }
                        }

                    }

                    @Override
                    public void onFail(int code, String data) {

                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                        if (vertifyNotNull.isNotNullObj(errorEntity)) {
                            iWalletView.onDataBackFail(code, errorEntity.getError_message());
                        } else {
                            iWalletView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                        }
                        iWalletView.dismissLoadingDialog();
                    }

                    @Override
                    public void onFinish() {
                        ;
                    }
                });

    }


    /**
     * 展示充值dialog
     *
     * @param url
     * @param order_type
     * @param os_type
     * @param device_type
     */
    public void doShowChangeDepositDialog(String url,
                                          String order_type,
                                          String os_type,
                                          String device_type) {


        iMoney.doGetPaymentsChannels(url, order_type, os_type, device_type, new OnDataBackListener() {
            @Override
            public void onStart() {
                iWalletView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                List<PayChannelInfo> list = parseSerilizable.getParseToNoItemsList(data, PayChannelInfo.class);

                if (vertifyNotNull.isNotNullListSize(list)) {

                    OssConfig ossConfig = iOssDao.findFirst(OssConfig.class);

                    if (vertifyNotNull.isNotNullObj(ossConfig)) {
                        list = functionPayChannel.getReSetChannelInfos(list, ossConfig);
                    }

                    iWalletView.doShowChangeDepositAccountDialog(list);

                } else {


                    iWalletView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iWalletView.onDataBackFail(code, errorEntity.getError_message());
                } else {

                    iWalletView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
                iWalletView.dismissLoadingDialog();
            }
        });

    }


    /**
     * 账户变更的时候执行重新绑定
     * 执行重新绑定时支付宝预授权
     *
     * @param url
     * @param channel_id
     * @param device_id
     * @param os_type
     * @param device_type
     * @param jsonObject
     */
    public void doAliPreRebindAppAuthLogin(String url,
                                           final String channel_id,
                                           String device_id,
                                           String os_type,
                                           String device_type,
                                           JsonObject jsonObject) {


        iThirdPay.doAliPreAppAuthLogin(url, channel_id, device_id, os_type, device_type, jsonObject, new OnDataBackListener() {
            @Override
            public void onStart() {
                iWalletView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                String auth_info = parseSerilizable.getParseString(data, "auth_info");

                iWalletView.doRxAliAuthorationRebind(auth_info, channel_id);


            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iWalletView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iWalletView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }


            }

            @Override
            public void onFinish() {


                iWalletView.dismissLoadingDialog();
            }
        });
    }


    /**
     * 微信的重新绑定授权
     * @param url
     * @param channel_id
     * @param device_id
     * @param os_type
     * @param device_type
     * @param jsonObject
     */
    public void doWxRebindAuthLogin(String url,
                                           final String channel_id,
                                           String device_id,
                                           String os_type,
                                           String device_type,
                                           JsonObject jsonObject) {


        iThirdPay.doAliPreAppAuthLogin(url, channel_id, device_id, os_type, device_type, jsonObject, new OnDataBackListener() {
            @Override
            public void onStart() {
                iWalletView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                String auth_info = parseSerilizable.getParseString(data, "auth_info");

                iWalletView.doRxAliAuthorationRebind(auth_info, channel_id);


            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iWalletView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iWalletView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }


            }

            @Override
            public void onFinish() {


                iWalletView.dismissLoadingDialog();
            }
        });
    }



}
