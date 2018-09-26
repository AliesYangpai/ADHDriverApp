package com.adhdriver.work.ui.activity.driver;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstConfig;
import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.constant.ConstSign;
import com.adhdriver.work.entity.driver.pay.aliAbout.AliAuthInfo;
import com.adhdriver.work.entity.driver.wallet.AliAuthRespose;
import com.adhdriver.work.entity.driver.wallet.Wallet;
import com.adhdriver.work.entity.driver.wallet.WxAuthInfo;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.presenter.driver.PresenterDriverDepositWithDraw;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.iview.driver.IDepositWithDrawView;
import com.adhdriver.work.utils.ImgUtil;
import com.adhdriver.work.utils.ToastUtil;
import com.adhdriver.work.utils.VersionUtil;
import com.adhdriver.work.ui.widget.fragment.PayFragment;
import com.adhdriver.work.ui.widget.fragment.PayPwdView;


/**
 * 提现的activity
 */
public class DepositWithDrawActivity extends BaseActivity<IDepositWithDrawView, PresenterDriverDepositWithDraw> implements
        IDepositWithDrawView,
        View.OnClickListener,
        TextWatcher,
        PayPwdView.InputCallBack {


    private PresenterDriverDepositWithDraw presenterDriverDepositWithDraw;


    /**
     * title
     */

    private ImageView iv_common_back;
    private TextView tv_common_title;
    private ImageView iv_common_search;
    private RelativeLayout rl_withdraw_parent;


    /**
     * 中间：
     */
    private ImageView iv_channel_head;    //支付宝或者微信头像
    private TextView tv_channel_nick_name; //支付宝或者微信昵称

    private TextView tv_get_money_deposit_way;// 文字显示零钱提现or押金提现

    private EditText et_money;//需要输入的monny数额
    private TextView tv_money;//押金提现时，价格栏里显示的押金总额
    private TextView tv_change_balance;//零钱余额
    private TextView tv_cash_deposit_balance;//押金余额
    private TextView tv_start_dispose;//开始提现


    /**
     * 数据相关
     */
    private String currentChannel;
    private Wallet currentWallet;
    private int currentMoneyWay; //【押金提现】 【余额提现】


    /**
     * 控件相关
     *
     * @param savedInstanceState
     */

    private PayFragment payFragment;


    private String getAliOpenId() {

        String openId = "";

        if (null != currentWallet) {

            AliAuthInfo alipay_auth_info = currentWallet.getAlipay_auth_info();

            if (null != alipay_auth_info) {

                AliAuthRespose alipay_user_userinfo_share_response = alipay_auth_info.getAlipay_user_userinfo_share_response();


                if (null != alipay_user_userinfo_share_response) {

                    openId = alipay_user_userinfo_share_response.getAlipay_user_id();

                }
            }
        }


        return openId;

    }


    /**
     * 发起微信提现的时候调用
     * @return
     */
    private String getWxOpenId() {

        String openId = "";

        if (null != currentWallet) {

            WxAuthInfo wx_pay_auth_info = currentWallet.getWx_pay_auth_info();

            if (null != wx_pay_auth_info) {


                openId = wx_pay_auth_info.getOpenid();

            }
        }


        return openId;

    }

    private void updateBottomUiByMoneyCount(Editable s) {

        String str = String.valueOf(s);

        if (!TextUtils.isEmpty(str) && !str.equals(ConstSign.POINT)) {

            float somme = Float.valueOf(str);

            if (currentWallet != null && somme > Float.valueOf(currentWallet.getBalance())) {


                tv_start_dispose.setEnabled(false);

            } else {

                tv_start_dispose.setEnabled(true);

            }


        } else {

            tv_start_dispose.setEnabled(true);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit_with_draw);


        presenterDriverDepositWithDraw.doShowData(currentWallet, currentChannel);
    }

    @Override
    protected PresenterDriverDepositWithDraw creatPresenter() {
        if (null == presenterDriverDepositWithDraw) {
            presenterDriverDepositWithDraw = new PresenterDriverDepositWithDraw(this);
        }
        return presenterDriverDepositWithDraw;
    }

    @Override
    protected void initViews() {

        /**
         * title
         */

        iv_common_back = findADHViewById(R.id.iv_common_back);
        tv_common_title = findADHViewById(R.id.tv_common_title);
        iv_common_search = findADHViewById(R.id.iv_common_search);
        rl_withdraw_parent = findADHViewById(R.id.rl_withdraw_parent);
        iv_common_search.setVisibility(View.GONE);


        iv_channel_head = findADHViewById(R.id.iv_channel_head);    //支付宝或者微信头像
        tv_channel_nick_name = findADHViewById(R.id.tv_channel_nick_name); //支付宝或者微信昵称

        tv_get_money_deposit_way = findADHViewById(R.id.tv_get_money_deposit_way);// 文字显示零钱提现or押金提现

        et_money = findADHViewById(R.id.et_money);//需要输入的monny数额
        tv_money = findADHViewById(R.id.tv_money);////押金提现时，价格栏里显示的押金总额
        tv_change_balance = findADHViewById(R.id.tv_change_balance);//零钱余额
        tv_cash_deposit_balance = findADHViewById(R.id.tv_cash_deposit_balance);//押金余额
        tv_start_dispose = findADHViewById(R.id.tv_start_dispose);//开始提现


        switch (currentChannel) {

            case ConstLocalData.ALIPAY:

                tv_common_title.setText(this.getString(R.string.ali_withDraw));
                rl_withdraw_parent.setBackgroundResource(R.drawable.shape_qr_ali_bg);

                break;


            case ConstLocalData.WX:


                tv_common_title.setText(this.getString(R.string.wx_withdraw));
                rl_withdraw_parent.setBackgroundResource(R.drawable.shape_qr_wx_bg);
                break;
        }

        et_money.setVisibility(View.VISIBLE);
        tv_get_money_deposit_way.setText(this.getString(R.string.change_deposit));

    }

    @Override
    protected void initListener() {


        iv_common_back.setOnClickListener(this);

        tv_start_dispose.setOnClickListener(this);

        et_money.addTextChangedListener(this);

    }

    @Override
    protected void processIntent() {

        Intent intent = this.getIntent();

        if (null != intent) {

            Bundle extras = intent.getExtras();

            if (null != extras) {


                currentWallet = (Wallet) extras.getSerializable(ConstIntent.BundleKEY.WITHDRAW_WALLET);
                currentChannel = extras.getString(ConstIntent.BundleKEY.WITHDRAW_DEPOSITE_CHANNEL);

            }

        }

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
    public void doVertifyErrorForCashNull() {
        ToastUtil.showMsg(getApplicationContext(), R.string.enter_withdraw_money);
    }

    @Override
    public void doVertifyErrorForCash0() {

        ToastUtil.showMsg(getApplicationContext(), R.string.min_balance_not_0);
    }

    @Override
    public void doVertifyErrorForCashLessThanZeroPointOne() {


        ToastUtil.showMsg(getApplicationContext(), R.string.min_balance);

    }

    @Override
    public void doVertifyErrorForCashLessThanOne() {


        ToastUtil.showMsg(getApplicationContext(), R.string.min_balance_1_one);
    }

    @Override
    public void doChcekAmountSuccess(String amoutContent) {

        String content = this.getString(R.string.the_withdraw_balance) + ConstSign.COLON + ConstSign.SPACE + this.getString(R.string.currency_unit) + ConstSign.SPACE + amoutContent;


        Bundle bundle = new Bundle();
        bundle.putString(ConstIntent.BundleKEY.WITHDRAW_MONNEY_CONTENT, content);
        payFragment = new PayFragment();
        payFragment.setArguments(bundle);
        payFragment.setPaySuccessCallBack(this);
        payFragment.show(getSupportFragmentManager(), "Pay");

    }

    @Override
    public void onDataBackSuccessForDepositWithDraw() {

        if (null != payFragment) {

            payFragment.dismiss();
            payFragment = null;

        }


        DepositWithDrawActivity.this.setResult(ConstIntent.ResponseCode.DO_WITHDRAW_DISPOSIT);
        DepositWithDrawActivity.this.finish();
    }

    @Override
    public void showDataOnUi(Wallet wallet) {


        switch (currentChannel) {


            case ConstLocalData.ALIPAY:

                AliAuthRespose alipay_user_userinfo_share_response = wallet.getAlipay_auth_info().getAlipay_user_userinfo_share_response();
                String avatar = alipay_user_userinfo_share_response.getAvatar();
                String nick_name = alipay_user_userinfo_share_response.getNick_name();
                tv_change_balance.setText(this.getString(R.string.change_account) + this.getString(R.string.currency_unit) + currentWallet.getBalance());
                tv_cash_deposit_balance.setText(this.getString(R.string.cash_pledge_account) + this.getString(R.string.currency_unit) + currentWallet.getDeposit_amount());
                tv_channel_nick_name.setText(nick_name);
                ImgUtil.getInstance().getRadiusImgFromNetByUrl(avatar, iv_channel_head, R.drawable.img_head, 120);


                break;

            case ConstLocalData.WX:


                WxAuthInfo wx_pay_auth_info = wallet.getWx_pay_auth_info();

                String avatarWX = wx_pay_auth_info.getHeadimgurl();
                String nick_nameWX = wx_pay_auth_info.getNickname();
                tv_change_balance.setText(this.getString(R.string.change_account) + this.getString(R.string.currency_unit) + currentWallet.getBalance());
                tv_cash_deposit_balance.setText(this.getString(R.string.cash_pledge_account) + this.getString(R.string.currency_unit) + currentWallet.getDeposit_amount());
                tv_channel_nick_name.setText(nick_nameWX);
                ImgUtil.getInstance().getRadiusImgFromNetByUrl(avatarWX, iv_channel_head, R.drawable.img_head, 120);

                break;
        }


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.iv_common_back:
                dofinishItself();
                break;

            case R.id.tv_start_dispose:
                String amount = et_money.getText().toString().trim();
                presenterDriverDepositWithDraw.doCheckDepositMoney(amount);

                break;

        }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        updateBottomUiByMoneyCount(s);
    }

    @Override
    public void onInputFinish(String result) {


        String amout = et_money.getText().toString().toString();


        switch (currentChannel) {


            case ConstLocalData.ALIPAY:


                presenterDriverDepositWithDraw.doDepositWithDraw(
                        HttpConst.URL.CHANGE_TRANSFER,
                        amout,
                        currentChannel,
                        ConstConfig.OS_TYPE,
                        VersionUtil.getTheIMEI(),
                        getAliOpenId(),
                        result);
                break;

            case ConstLocalData.WX:


                presenterDriverDepositWithDraw.doDepositWithDraw(
                        HttpConst.URL.CHANGE_TRANSFER,
                        amout,
                        currentChannel,
                        ConstConfig.OS_TYPE,
                        VersionUtil.getTheIMEI(),
                        getWxOpenId(),
                        result);
                break;
        }


    }
}
