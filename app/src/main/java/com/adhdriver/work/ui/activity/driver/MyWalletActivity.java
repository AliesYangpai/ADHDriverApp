package com.adhdriver.work.ui.activity.driver;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.DepositChangeAccountChannelCallBack;
import com.adhdriver.work.callback.DepositChannelCallBack;
import com.adhdriver.work.callback.DepositPassAlertSettingDialogCallBack;
import com.adhdriver.work.callback.RechargeAliEnterAmountCallBack;
import com.adhdriver.work.callback.RechargeChannelCallBack;
import com.adhdriver.work.callback.RechargeWxEnterAmountCallBack;
import com.adhdriver.work.constant.ConstConfig;
import com.adhdriver.work.constant.ConstEvent;
import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.constant.ConstParams;
import com.adhdriver.work.entity.EventEntity;
import com.adhdriver.work.entity.driver.pay.PayChannelInfo;
import com.adhdriver.work.entity.driver.pay.aliAbout.AliAuthInfo;
import com.adhdriver.work.entity.driver.pay.aliAbout.AuthResult;
import com.adhdriver.work.entity.driver.pay.aliAbout.PayNecessaryInfo;
import com.adhdriver.work.entity.driver.pay.aliAbout.PayResult;
import com.adhdriver.work.entity.driver.wallet.Wallet;
import com.adhdriver.work.entity.driver.wallet.WxAuthInfo;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.presenter.driver.PresenterDriverWallet;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.iview.driver.IWalletView;
import com.adhdriver.work.utils.ToastUtil;
import com.adhdriver.work.utils.VersionUtil;
import com.adhdriver.work.ui.widget.dialog.ChangeDepositAccountDialog;
import com.adhdriver.work.ui.widget.dialog.DepositMoneyChannelDialog;
import com.adhdriver.work.ui.widget.dialog.DepositPassSettingAlertDialog;
import com.adhdriver.work.ui.widget.dialog.DepositWithDrawSuccessDialog;
import com.adhdriver.work.ui.widget.dialog.RechargeAliEnterAmountDialog;
import com.adhdriver.work.ui.widget.dialog.RechargeMoneyChannelDialog;
import com.adhdriver.work.ui.widget.dialog.RechargeWxEnterAmountDialog;
import com.alipay.sdk.app.AuthTask;
import com.alipay.sdk.app.PayTask;
import com.google.gson.JsonObject;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MyWalletActivity extends BaseActivity<IWalletView, PresenterDriverWallet> implements
        View.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener,
        IWalletView,
        DepositPassAlertSettingDialogCallBack,
        DepositChannelCallBack,
        RechargeChannelCallBack,
        RechargeAliEnterAmountCallBack,
        RechargeWxEnterAmountCallBack,
        DepositChangeAccountChannelCallBack {


    private PresenterDriverWallet presenterDriverWallet;

    private ImageView iv_common_back;
    private TextView tv_common_title;
    private TextView tv_common_right;


    private SwipeRefreshLayout srefresh_layout; //
    private TextView tv_my_change;
    private RelativeLayout rl_change_deposit_account;//提现账户变更
    private RelativeLayout rl_recharge;//充值
    private RelativeLayout rl_deposit;//提现


    /**
     * dialog相关
     *
     * @param savedInstanceState
     */

    private ChangeDepositAccountDialog changeDepositAccountDialog;//变更提现账户的dialog
    private DepositMoneyChannelDialog depositMoneyChannelDialog;//提现
    private RechargeMoneyChannelDialog rechargeMoneyChannelDialog;//充值dialog
    private DepositPassSettingAlertDialog depositPassSettingAlertDialog;//提醒设置提现密码的dialog
    private DepositWithDrawSuccessDialog depositWithDrawSuccessDialog;//提现请求提交成功的dialog
    private RechargeAliEnterAmountDialog rechargeAliEnterAmountDialog; //支付宝的输入充值金额的dialog
    private RechargeWxEnterAmountDialog rechargeWxEnterAmountDialog;//微信输入充值金额的dialog
    /**
     * 数据相关
     */
    private Wallet currentWallet;
    private String payment_record_id;////标记微信的Payment_record_id用于微信查询 这个记得要还原


    /**
     * 判断授权返回是否成功
     *
     * @param authResult
     * @return
     */


    private boolean isRebind;

    private boolean checkAuthBack(AuthResult authResult) {

        boolean result = false;

        if (null != authResult) {
            String resultStatus = authResult.getResultStatus();
            // 判断resultStatus 为“9000”且result_code
            // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
            if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                // 获取alipay_open_id，调支付时作为参数extern_token 的value
                // 传入，则支付账户为该授权账户
                ToastUtil.showMsg(getApplicationContext(), "授权成功");
                Log.i("ssssswww", "授权成功返回：" + authResult.getAuthCode());

                result = true;

            } else {


                result = false;

                ToastUtil.showMsg(getApplicationContext(), "授权失败");

                Log.i("ssssswww", "授权失败：" + authResult.getAuthCode());
            }


        }


        return result;

    }


    /**
     * 处理支付宝支付结果返回并更新界面(就是支付宝充值返回后进行验签)
     *
     * @param stringStringMap
     */
    private void doAlipayCheckResultToUi(Map<String, String> stringStringMap) {


        PayResult payResult = new PayResult(stringStringMap);
        String resultInfo = payResult.getResult();// 同步返回需要验证的信息
        String resultStatus = payResult.getResultStatus();
        // 判断resultStatus 为9000则代表支付成功
        if (TextUtils.equals(resultStatus, "9000")) {
            // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。


            presenterDriverWallet.doAliCheckAliPayResultInfo(HttpConst.URL.CHECK_ALI_PAY_RESULT_INFORMATION,
                    payResult.getResult(),
                    ConstConfig.DRIVER_APP,
                    ConstConfig.OS_TYPE,
                    ConstParams.PaySide.RECIVER);


        } else {
            // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
            ToastUtil.showMsg(getApplicationContext(), "支付充值失败");

        }


    }


    /**
     * 判断是否安装支付宝客户端
     *
     * @param context
     * @return
     */
    public boolean checkAliPayInstalled(Context context) {
        Uri uri = Uri.parse(ConstLocalData.ALI_CLIENT_CHECK_URL);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        ComponentName componentName = intent.resolveActivity(context.getPackageManager());
        return componentName != null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
        EventBus.getDefault().register(this);
        presenterDriverWallet.doGetWalletInfo(HttpConst.URL.MY_WALLETE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {

            case ConstIntent.ResponseCode.SET_PAY_PASS:
                presenterDriverWallet.doGetWalletInfo(HttpConst.URL.MY_WALLETE);
                break;


            case ConstIntent.ResponseCode.DO_WITHDRAW_DISPOSIT:

                if (null == depositWithDrawSuccessDialog) {
                    depositWithDrawSuccessDialog = new DepositWithDrawSuccessDialog(this);
                }
                depositWithDrawSuccessDialog.setCancelable(false);
                depositWithDrawSuccessDialog.show();


                presenterDriverWallet.doGetWalletInfo(HttpConst.URL.MY_WALLETE);


                break;


            case ConstIntent.ResponseCode.CONFIRM_PAY_PASS:


                presenterDriverWallet.doGetWalletInfo(HttpConst.URL.MY_WALLETE);

                ToastUtil.showMsg(getApplicationContext(), "重新绑定完毕");

                break;


            case ConstIntent.ResponseCode.CONFIRM_PAY_PASS_FOR_WX:

                if (null != data) {

                    Bundle extras = data.getExtras();
                    if (null != extras) {

                        WxAuthInfo wxAuthInfo = (WxAuthInfo) extras.getSerializable("sss");

                        currentWallet.setWx_pay_auth_info(wxAuthInfo);

                        Intent intent = new Intent(MyWalletActivity.this, DepositWithDrawActivity.class);
                        Bundle bundleWx = new Bundle();
                        bundleWx.putSerializable(ConstIntent.BundleKEY.WITHDRAW_WALLET, currentWallet);
                        bundleWx.putString(ConstIntent.BundleKEY.WITHDRAW_DEPOSITE_CHANNEL, ConstLocalData.WX);
                        intent.putExtras(bundleWx);
                        startActivityForResult(intent, ConstIntent.RequestCode.WALLET_TO_WITHDRAW);
                    }
                }
                break;


            case ConstIntent.ResponseCode.CONFIRM_PAY_PASS_ALI_NOT_BIND:

                if (null != data) {

                    Bundle bundle = data.getExtras();

                    if(null != bundle) {
                        AliAuthInfo aliAuthInfo = (AliAuthInfo) bundle.getSerializable("sss");

                        /**
                         * 获取信息成功，跳转到提现界面
                         */

                        currentWallet.setAlipay_auth_info(aliAuthInfo);

                        Intent intent = new Intent(MyWalletActivity.this, DepositWithDrawActivity.class);
                        Bundle bundleAli = new Bundle();
                        bundleAli.putSerializable(ConstIntent.BundleKEY.WITHDRAW_WALLET, currentWallet);

                        bundleAli.putString(ConstIntent.BundleKEY.WITHDRAW_DEPOSITE_CHANNEL, ConstLocalData.ALIPAY);
                        intent.putExtras(bundleAli);
                        startActivityForResult(intent, ConstIntent.RequestCode.WALLET_TO_WITHDRAW);


                    }
                }


                break;

        }
    }


    /**
     * 处理微信充值返回结果
     *
     * @param eventEntity
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventInActivty(EventEntity eventEntity) {


        if (null != eventEntity) {


            switch (eventEntity.getNotifyTag()) {


                case ConstEvent.WX_PAY_SUCCESS_BACK:  //微信支付成功 返回


                    presenterDriverWallet.doWxCheckWxPayResultInfo(
                            HttpConst.URL.CHECK_WX_PAY_RESULT,
                            ConstConfig.DRIVER_APP,
                            ConstConfig.OS_TYPE,
                            payment_record_id,
                            ConstParams.PaySide.RECIVER);

                    break;


                case ConstEvent.WX_PAY_FAIL_BACK:  //微信支付失败 返回


                    ToastUtil.showMsg(getApplicationContext(), R.string.fail_to_wx_recharge);

                    break;

                case ConstEvent.WxAuthAbout.SUCCESS:
                    String respCode = eventEntity.getNotifyMsg();


                    if (!currentWallet.isHas_pay_pwd()) {


                        presenterDriverWallet.doWxAuthoLogin(
                                HttpConst.URL.AUTH_LOGIN_INFO,
                                ConstLocalData.WX,
                                ConstLocalData.FIRST_AUTN_PASS,
                                VersionUtil.getTheIMEI(),
                                ConstConfig.OS_TYPE,
                                ConstConfig.DEVICE_TYPE,
                                respCode,
                                new JsonObject());

                    } else {


                        Intent intent = new Intent(MyWalletActivity.this, DepositPassEnterActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString(ConstIntent.BundleKEY.PAY_CHANNEL_IN_REBIND, ConstLocalData.WX);
                        bundle.putString(ConstIntent.BundleKEY.AUTH_CODE_IN_REBIND, respCode);
                        intent.putExtras(bundle);
                        startActivityForResult(intent, ConstIntent.RequestCode.WALLET_TO_WITHDRAW);


                    }


                    break;

                case ConstEvent.WxAuthAbout.CANCEL:
                    break;

                case ConstEvent.WxAuthAbout.FAIL:


                    break;
            }

        }

    }

    @Override
    protected PresenterDriverWallet creatPresenter() {
        if (null == presenterDriverWallet) {
            presenterDriverWallet = new PresenterDriverWallet(this);
        }
        return presenterDriverWallet;
    }

    @Override
    protected void initViews() {

        iv_common_back = findADHViewById(R.id.iv_common_back);
        tv_common_title = findADHViewById(R.id.tv_common_title);
        tv_common_title.setText(getString(R.string.wallet));
        tv_common_right = findADHViewById(R.id.tv_common_right);
        tv_common_right.setText(getString(R.string.change_detial));


        srefresh_layout = findADHViewById(R.id.srefresh_layout);
        srefresh_layout.setColorSchemeColors(getSwipeRefreshColor());
        tv_my_change = findADHViewById(R.id.tv_my_change);
        rl_change_deposit_account = findADHViewById(R.id.rl_change_deposit_account);//提现账户变更
        rl_recharge = findADHViewById(R.id.rl_recharge);//充值
        rl_deposit = findADHViewById(R.id.rl_deposit);//提现

    }

    @Override
    protected void initListener() {

        iv_common_back.setOnClickListener(this);
        tv_common_right.setOnClickListener(this);

        srefresh_layout.setOnRefreshListener(this);
        rl_change_deposit_account.setOnClickListener(this);
        rl_recharge.setOnClickListener(this);
        rl_deposit.setOnClickListener(this);

    }

    @Override
    protected void processIntent() {

    }

    @Override
    public void showLoadingDialog() {
        showLoadDialog();
    }

    @Override
    public void dismissLoadingDialog() {
        dismissLoadDialog();
    }

    @Override
    public void onDataBackFail(int code, String errorMsg) {
        ToastUtil.showMsg(getApplicationContext(), errorMsg);
    }

    @Override
    public void dismissFreshLoading() {
        srefresh_layout.setRefreshing(false);
    }

    @Override
    public void onDataBackSuccessForWalletToSetData(Wallet wallet) {

        currentWallet = wallet;

        tv_my_change.setText(wallet.getBalance());
    }

    @Override
    public void onDataBackSuccessForShowChangeAccount() {
        rl_change_deposit_account.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDataBackSuccessForHideChangeAccount() {
        rl_change_deposit_account.setVisibility(View.GONE);
    }

    @Override
    public void doVertifyErrorNoBalance() {
        ToastUtil.showMsg(getApplicationContext(), R.string.can_not_withDraw);
    }

    @Override
    public void doShowAlertToSetDepositPass() {


        if (null == depositPassSettingAlertDialog) {
            depositPassSettingAlertDialog = new DepositPassSettingAlertDialog(this);
            depositPassSettingAlertDialog.setDepositPassAlertSettingDialogCallBack(this);
            depositPassSettingAlertDialog.setCancelable(false);
        }
        depositPassSettingAlertDialog.show();


    }

    @Override
    public void doShowDepositDialog(List<PayChannelInfo> payChannelInfoList) {


        if (depositMoneyChannelDialog == null) {
            depositMoneyChannelDialog = new DepositMoneyChannelDialog(this);
        }
        depositMoneyChannelDialog.setPayChannelInfos(payChannelInfoList);
        depositMoneyChannelDialog.setCancelable(false);
        depositMoneyChannelDialog.setDepositChannelCallBack(this);
        depositMoneyChannelDialog.show();
    }

    @Override
    public void doShowRechargeDialog(List<PayChannelInfo> payChannelInfoList) {

        if (rechargeMoneyChannelDialog == null) {
            rechargeMoneyChannelDialog = new RechargeMoneyChannelDialog(this);
        }
        rechargeMoneyChannelDialog.setRechargeChannelCallBack(this);
        rechargeMoneyChannelDialog.setPayChannelInfos(payChannelInfoList);
        rechargeMoneyChannelDialog.setCancelable(false);
        rechargeMoneyChannelDialog.show();

    }

    @Override
    public void doShowChangeDepositAccountDialog(List<PayChannelInfo> payChannelInfoList) {
        if (null == changeDepositAccountDialog) {


        }

        if (changeDepositAccountDialog == null) {
            changeDepositAccountDialog = new ChangeDepositAccountDialog(this);
        }
        changeDepositAccountDialog.setDepositChangeAccountChannelCallBack(this);
        changeDepositAccountDialog.setPayChannelInfos(payChannelInfoList);
        changeDepositAccountDialog.setCancelable(false);
        changeDepositAccountDialog.show();

    }


    /**
     * 使用支付宝控件,调用获取支付宝授权信息
     *
     * @param auth_info
     * @param
     * @param
     */
    @Override
    public void doRxAliAuthoration(String auth_info) {
        final String authInfo = auth_info;
        Log.i("ssssswww", auth_info);
        if (!TextUtils.isEmpty(auth_info)) {


            Observer<AuthResult> observer = new Observer<AuthResult>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    Log.i(" rxJavaxxxx", " onError ---> " + e);
                }

                @Override
                public void onNext(AuthResult authResult) {
                    Log.i(" rxJavaxxxx", " onNext ---> " + authResult.toString());

                    if (checkAuthBack(authResult)) {


                        rl_change_deposit_account.setVisibility(View.VISIBLE);


                        if (currentWallet.isHas_pay_pwd()) {


                            Intent intent = new Intent(MyWalletActivity.this, DepositPassEnterActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString(ConstIntent.BundleKEY.PAY_CHANNEL_IN_REBIND, ConstLocalData.ALIPAY);
                            bundle.putString(ConstIntent.BundleKEY.AUTH_CODE_IN_REBIND, authResult.getAuthCode());
                            bundle.putString(ConstIntent.BundleKEY.HAS_PASS_BUT_NOT_THIS_CHANNEL_AUTH, ConstLocalData.ALIPAY);
                            intent.putExtras(bundle);
                            startActivityForResult(intent, ConstIntent.RequestCode.WALLET_TO_WITHDRAW);


                        } else {

                            presenterDriverWallet.doGetAliPayInfo(
                                    HttpConst.URL.AUTH_LOGIN_INFO,
                                    ConstLocalData.ALIPAY,
                                    ConstLocalData.FIRST_AUTN_PASS,
                                    VersionUtil.getTheIMEI(),
                                    ConstConfig.OS_TYPE,
                                    ConstConfig.DEVICE_TYPE,
                                    authResult.getAuthCode(),
                                    new JsonObject(),
                                    currentWallet);
                        }


                    }
                }
            };


            Observable.create(new Observable.OnSubscribe<AuthResult>() {
                @Override
                public void call(Subscriber<? super AuthResult> subscriber) {
                    AuthTask authTask = new AuthTask(MyWalletActivity.this);
                    Map<String, String> map = authTask.authV2(authInfo, true);
                    AuthResult authResult = new AuthResult(map, true);
                    subscriber.onNext(authResult);
                    subscriber.onCompleted();
                }
            }).subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }
    }


    /**
     * 重新绑定的时候
     *
     * @param auth_info
     */
    @Override
    public void doRxAliAuthorationRebind(String auth_info, final String payChannelId) {

        final String authInfo = auth_info;
        Log.i("ssssswww", auth_info);
        if (!TextUtils.isEmpty(auth_info)) {


            Observer<AuthResult> observer = new Observer<AuthResult>() {
                @Override
                public void onCompleted() {


                }

                @Override
                public void onError(Throwable e) {
                    Log.i(" rxJavaxxxx", " onError ---> " + e);
                }

                @Override
                public void onNext(AuthResult authResult) {
                    Log.i(" rxJavaxxxx", " onNext ---> " + authResult.toString());

                    if (checkAuthBack(authResult)) {


                        Intent intent = new Intent(MyWalletActivity.this, DepositPassEnterActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString(ConstIntent.BundleKEY.PAY_CHANNEL_IN_REBIND, payChannelId);
                        bundle.putString(ConstIntent.BundleKEY.AUTH_CODE_IN_REBIND, authResult.getAuthCode());
                        intent.putExtras(bundle);
                        startActivityForResult(intent, ConstIntent.RequestCode.WALLET_TO_WITHDRAW);


                    }
                }
            };


            Observable.create(new Observable.OnSubscribe<AuthResult>() {
                @Override
                public void call(Subscriber<? super AuthResult> subscriber) {
                    AuthTask authTask = new AuthTask(MyWalletActivity.this);
                    Map<String, String> map = authTask.authV2(authInfo, true);
                    AuthResult authResult = new AuthResult(map, true);
                    subscriber.onNext(authResult);
                    subscriber.onCompleted();
                }
            }).subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }
    }


    /**
     * 初次授权获取授权信息成功返回 之后跳转到提现界面
     *
     * @param
     */
    @Override
    public void onDataBackSuccessForGetAliInfo(AliAuthInfo aliAuthInfo) {


        /**
         * 获取信息成功，跳转到提现界面
         */

        currentWallet.setAlipay_auth_info(aliAuthInfo);

        Intent intent = new Intent(MyWalletActivity.this, DepositWithDrawActivity.class);
        Bundle bundleAli = new Bundle();
        bundleAli.putSerializable(ConstIntent.BundleKEY.WITHDRAW_WALLET, currentWallet);

        bundleAli.putString(ConstIntent.BundleKEY.WITHDRAW_DEPOSITE_CHANNEL, ConstLocalData.ALIPAY);
        intent.putExtras(bundleAli);
        startActivityForResult(intent, ConstIntent.RequestCode.WALLET_TO_WITHDRAW);

    }

    @Override
    public void onDataBackSuccessForGetWxInfo(WxAuthInfo wxAuthInfo) {


        currentWallet.setWx_pay_auth_info(wxAuthInfo);
        Intent intent = new Intent(MyWalletActivity.this, DepositWithDrawActivity.class);
        Bundle bundleWx = new Bundle();
        bundleWx.putSerializable(ConstIntent.BundleKEY.WITHDRAW_WALLET, currentWallet);
        bundleWx.putString(ConstIntent.BundleKEY.WITHDRAW_DEPOSITE_CHANNEL, ConstLocalData.WX);
        intent.putExtras(bundleWx);
        startActivityForResult(intent, ConstIntent.RequestCode.WALLET_TO_WITHDRAW);


    }

    @Override
    public void doJumpDepositForAli() {


        Intent intentAli = new Intent(this, DepositWithDrawActivity.class);
        Bundle bundleAli = new Bundle();
        bundleAli.putSerializable(ConstIntent.BundleKEY.WITHDRAW_WALLET, currentWallet);
        bundleAli.putString(ConstIntent.BundleKEY.WITHDRAW_DEPOSITE_CHANNEL, ConstLocalData.ALIPAY);
        intentAli.putExtras(bundleAli);
        startActivityForResult(intentAli, ConstIntent.RequestCode.WALLET_TO_WITHDRAW);


    }

    @Override
    public void doJumpDepositForWx() {


        Intent intentWx = new Intent(this, DepositWithDrawActivity.class);
        Bundle bundleWx = new Bundle();
        bundleWx.putSerializable(ConstIntent.BundleKEY.WITHDRAW_WALLET, currentWallet);
        bundleWx.putString(ConstIntent.BundleKEY.WITHDRAW_DEPOSITE_CHANNEL, ConstLocalData.WX);
        intentWx.putExtras(bundleWx);
        startActivityForResult(intentWx, ConstIntent.RequestCode.WALLET_TO_WITHDRAW);

    }

    /**
     * 支付宝充值输入价格的dialog
     */

    @Override
    public void doShowAliRechargeDialog(String channelId) {
        if (null == rechargeAliEnterAmountDialog) {

            rechargeAliEnterAmountDialog = new RechargeAliEnterAmountDialog(this);
            rechargeAliEnterAmountDialog.setPayChannelTag(channelId);
            rechargeAliEnterAmountDialog.setRechargeAliEnterAmountCallBack(this);
        }
        rechargeAliEnterAmountDialog.show();
    }

    @Override
    public void doRxAliReCharge(final String payInfo) {

        Observable.create(new Observable.OnSubscribe<Map<String, String>>() {
            @Override
            public void call(Subscriber<? super Map<String, String>> subscriber) {
                PayTask alipay = new PayTask(MyWalletActivity.this);
                Map<String, String> result = alipay.payV2(payInfo, true);
                subscriber.onNext(result);
                subscriber.onCompleted();
            }

        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Map<String, String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Map<String, String> stringStringMap) {


                        doAlipayCheckResultToUi(stringStringMap);

                    }
                });
    }


    /**
     * 执行微信充值
     *
     * @param
     */
    @Override
    public void doWxReCharge(PayNecessaryInfo payNecessaryInfo) {
        try {
            JSONObject json = new JSONObject(payNecessaryInfo.getAction_arguments());

            PayReq req = new PayReq();
            req.appId = json.getString("appid");
            req.partnerId = json.getString("partnerid");
            req.prepayId = json.getString("prepayid");
            req.nonceStr = json.getString("noncestr");
            req.timeStamp = json.getString("timestamp");
            req.packageValue = json.getString("package");
            req.sign = json.getString("sign");

            IWXAPI wxapi = WXAPIFactory.createWXAPI(this, req.appId);


            wxapi.sendReq(req);

            payment_record_id = payNecessaryInfo.getPayment_record_id();//标记微信的Payment_record_id用于微信查询

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDataBackSuccessForAliConfirm() {
        presenterDriverWallet.doGetWalletInfo(HttpConst.URL.MY_WALLETE);
    }

    @Override
    public void onDataBackSuccessForWxConfirm() {

        presenterDriverWallet.doGetWalletInfo(HttpConst.URL.MY_WALLETE);

    }

    @Override
    public void onDataBackFailForWxConfirm(int code, String errorMsg) {
        dismissLoadDialog();
        ToastUtil.showMsg(getApplicationContext(), errorMsg);
    }


    /**
     * 微信充值输入价格的dialog
     */
    @Override
    public void doShowWxRechargeDialog(String channelId) {

        ToastUtil.showMsg(getApplicationContext(), "弹出微信充值的dialog");


        if (null == rechargeWxEnterAmountDialog) {

            rechargeWxEnterAmountDialog = new RechargeWxEnterAmountDialog(this);
            rechargeWxEnterAmountDialog.setPayChannelTag(channelId);
            rechargeWxEnterAmountDialog.setRechargeWxEnterAmountCallBack(this);
        }
        rechargeWxEnterAmountDialog.show();
    }

    @Override
    public void doGetWxCodeInLoginWay() {
        String uuid = UUID.randomUUID().toString();

        IWXAPI wxapi = WXAPIFactory.createWXAPI(this, ConstLocalData.WX_APPID, true);

        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = uuid;
        wxapi.sendReq(req);


    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.iv_common_back:

                dofinishItself();
                break;


            case R.id.tv_common_right:


                openActivity(MyWalletInfosActivity.class, null);
                break;

            case R.id.rl_change_deposit_account:


                presenterDriverWallet.doShowChangeDepositDialog(
                        HttpConst.URL.PAY_CHANNELS,
                        ConstLocalData.ORDER_TYPE,
                        ConstConfig.OS_TYPE,
                        ConstConfig.DEVICE_TYPE);

                break;

            case R.id.rl_recharge:

                presenterDriverWallet.doShowRechargeDialog(
                        HttpConst.URL.PAY_CHANNELS,
                        ConstLocalData.ORDER_TYPE,
                        ConstConfig.OS_TYPE,
                        ConstConfig.DEVICE_TYPE);

                break;

            case R.id.rl_deposit:


                presenterDriverWallet.doDealToShowDepositDialogOrNot(
                        currentWallet,
                        HttpConst.URL.PAY_CHANNELS,
                        ConstLocalData.ORDER_TYPE,
                        ConstConfig.OS_TYPE,
                        ConstConfig.DEVICE_TYPE);
                break;
        }
    }

    @Override
    public void onRefresh() {
        presenterDriverWallet.doGetWalletInfoInFresh(HttpConst.URL.MY_WALLETE);
    }

    @Override
    public void sureToSetDepositPass() {


        Intent intent = new Intent(this, DepositPassSettingActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(ConstIntent.BundleKEY.ABOUT_SET_PAYPASS, ConstIntent.BundleValue.SET_PAYPASS);
        intent.putExtras(bundle);
        startActivityForResult(intent, ConstIntent.RequestCode.WALLET_TO_WITHDRAW);
    }

    /**
     * 提现渠道选择
     * @param payChannelInfo
     */
    /**
     * 微信提现
     *
     * @param payChannelInfo
     */
    @Override
    public void doWxDepositCallBack(PayChannelInfo payChannelInfo) {


        presenterDriverWallet.doCommitWxDeposit(currentWallet);
    }


    /**
     * 支付宝提现
     *
     * @param payChannelInfo
     */
    @Override
    public void doAliDepositCallBack(PayChannelInfo payChannelInfo) {


        if (!checkAliPayInstalled(this)) {
            ToastUtil.showMsg(getApplicationContext(), R.string.please_load_ali_app);
            return;
        }

        presenterDriverWallet.doCommitAliDeposit(
                currentWallet,
                HttpConst.URL.AUTH_INFO,
                payChannelInfo.getChannel_id(),
                VersionUtil.getTheIMEI(),
                ConstConfig.OS_TYPE,
                ConstConfig.DEVICE_TYPE,
                new JsonObject());
    }

    @Override
    public void doRechargeChannelCallBack(String channelId) {

        presenterDriverWallet.doShowRechargeDilogByChannel(channelId);

    }


    @Override
    public void rechargeAliFinishEnterSureCallBack(String payChannel, String amount) {
        if (null != rechargeAliEnterAmountDialog && rechargeAliEnterAmountDialog.isShowing()) {
            rechargeAliEnterAmountDialog.dismiss();
            rechargeAliEnterAmountDialog = null;
        }

        presenterDriverWallet.doAliBalanceRecharge(
                HttpConst.URL.BALANCE_RECHARGE,
                ConstLocalData.BALANCE_RECHARGE,
                amount,
                payChannel,
                ConstConfig.OS_TYPE,
                ConstConfig.DEVICE_TYPE,
                VersionUtil.getTheIMEI(),
                ConstParams.PaySide.SHIPPER);

    }

    @Override
    public void rechargeAliCancelEnterCallBack() {
        if (null != rechargeAliEnterAmountDialog && rechargeAliEnterAmountDialog.isShowing()) {
            rechargeAliEnterAmountDialog.dismiss();
            rechargeAliEnterAmountDialog = null;
        }
    }

    @Override
    public void rechargeWxFinishEnterSureCallBack(String payChannel, String amount) {

        if (null != rechargeWxEnterAmountDialog && rechargeWxEnterAmountDialog.isShowing()) {
            rechargeWxEnterAmountDialog.dismiss();
            rechargeWxEnterAmountDialog = null;
        }


        presenterDriverWallet.doWxBalanceRecharge(
                HttpConst.URL.BALANCE_RECHARGE,
                ConstLocalData.BALANCE_RECHARGE,
                amount,
                payChannel,
                ConstConfig.OS_TYPE,
                ConstConfig.DEVICE_TYPE,
                VersionUtil.getTheIMEI(),
                ConstParams.PaySide.SHIPPER);

    }

    /**
     * 微信充值输入金额点击取消的回调接口
     */
    @Override
    public void rechargeWxCancelEnterCallBack() {

        if (null != rechargeWxEnterAmountDialog && rechargeWxEnterAmountDialog.isShowing()) {
            rechargeWxEnterAmountDialog.dismiss();
            rechargeWxEnterAmountDialog = null;
        }

    }

    @Override
    public void doWxChangeDepositAccountCallBack(PayChannelInfo payChannelInfo) {


        String uuid = UUID.randomUUID().toString();
        IWXAPI wxapi = WXAPIFactory.createWXAPI(this, ConstLocalData.WX_APPID, true);
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = uuid;
        wxapi.sendReq(req);


    }

    @Override
    public void doAliChangeDepositAccountCallBack(PayChannelInfo payChannelInfo) {

        if (!checkAliPayInstalled(this)) {
            ToastUtil.showMsg(getApplicationContext(), R.string.please_load_ali_app);
            return;
        }


        presenterDriverWallet.doAliPreRebindAppAuthLogin(
                HttpConst.URL.AUTH_INFO,
                payChannelInfo.getChannel_id(),
                VersionUtil.getTheIMEI(),
                ConstConfig.OS_TYPE,
                ConstConfig.DEVICE_TYPE,
                new JsonObject());


    }
}
