package com.adhdriver.work.ui.activity.driver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstConfig;
import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.entity.driver.pay.aliAbout.AliAuthInfo;
import com.adhdriver.work.entity.driver.wallet.WxAuthInfo;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.presenter.driver.PresenterDriverDepositPassEnter;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.iview.driver.IDepositPassEnterView;
import com.adhdriver.work.utils.ToastUtil;
import com.adhdriver.work.utils.VersionUtil;
import com.google.gson.JsonObject;
import com.jungly.gridpasswordview.GridPasswordView;

import org.feezu.liuli.timeselector.Utils.TextUtil;

public class DepositPassEnterActivity extends BaseActivity<IDepositPassEnterView, PresenterDriverDepositPassEnter> implements
        IDepositPassEnterView,
        View.OnClickListener{


    private PresenterDriverDepositPassEnter presenterDriverDepositPassEnter;
    /**
     * title
     */

    private ImageView iv_common_back;
    private TextView tv_common_title;
    private GridPasswordView gpv_set_withdraw_pass;
    private TextView tv_withdraw_sure;


    /**
     * 数据相关
     * @param savedInstanceState
     */


    private String has_pass_but_not_bind;
    private String authCode;
    private String currentChannel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit_pass_enter);
    }

    @Override
    protected PresenterDriverDepositPassEnter creatPresenter() {
        if (null == presenterDriverDepositPassEnter) {
            presenterDriverDepositPassEnter = new PresenterDriverDepositPassEnter(this);
        }
        return presenterDriverDepositPassEnter;
    }

    @Override
    protected void initViews() {

        /**
         * title
         */

        iv_common_back = findADHViewById(R.id.iv_common_back);
        tv_common_title = findADHViewById(R.id.tv_common_title);
        tv_common_title.setText(this.getString(R.string.enter_withdraw_pass));
        gpv_set_withdraw_pass = findADHViewById(R.id.gpv_set_withdraw_pass);
        tv_withdraw_sure = findADHViewById(R.id.tv_withdraw_sure);

    }

    @Override
    protected void initListener() {

        iv_common_back.setOnClickListener(this);

        /**
         * 中间
         *
         */


        tv_withdraw_sure.setOnClickListener(this);
    }

    @Override
    protected void processIntent() {

        Intent intent = this.getIntent();
        if(null != intent) {
            Bundle bundle = intent.getExtras();
            if(null != bundle) {

                currentChannel = bundle.getString(ConstIntent.BundleKEY.PAY_CHANNEL_IN_REBIND, ConstIntent.BundleValue.DEFAULT_STRING);
                authCode = bundle.getString(ConstIntent.BundleKEY.AUTH_CODE_IN_REBIND,ConstIntent.BundleValue.DEFAULT_STRING);
                has_pass_but_not_bind = bundle.getString(ConstIntent.BundleKEY.HAS_PASS_BUT_NOT_THIS_CHANNEL_AUTH,ConstIntent.BundleValue.DEFAULT_STRING);

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

        ToastUtil.showMsg(getApplicationContext(),errorMsg);
    }

    @Override
    public void doVertifyErrorForPassNull() {


        ToastUtil.showMsg(getApplicationContext(),R.string.enter_withdraw_pass_please);
    }

    @Override
    public void onDataBackSuccessForRebindAndGetAliInfo() {







    }

    @Override
    public void onDataBackSuccessForGetWxInfo(WxAuthInfo wxAuthInfo) {


        Intent intent = new Intent();
        Bundle bundle = new Bundle();

        bundle.putSerializable("sss",wxAuthInfo);
        intent.putExtras(bundle);

        DepositPassEnterActivity.this.setResult(ConstIntent.ResponseCode.CONFIRM_PAY_PASS_FOR_WX,intent);
        DepositPassEnterActivity.this.finish();

    }

    @Override
    public void onDataBackSuccessForRebindAndGetAliInfo(AliAuthInfo aliAuthInfo) {
        if(!TextUtil.isEmpty(has_pass_but_not_bind)){


            if(has_pass_but_not_bind.equals(ConstLocalData.ALIPAY)) {


                Intent intent = new Intent();
                Bundle bundle = new Bundle();



                bundle.putSerializable("sss",aliAuthInfo);
                intent.putExtras(bundle);

                DepositPassEnterActivity.this.setResult(ConstIntent.ResponseCode.CONFIRM_PAY_PASS_ALI_NOT_BIND,intent);

                DepositPassEnterActivity.this.finish();

            }



        }else {
            DepositPassEnterActivity.this.setResult(ConstIntent.ResponseCode.CONFIRM_PAY_PASS);

            DepositPassEnterActivity.this.finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_common_back:
                dofinishItself();
                break;

            case R.id.tv_withdraw_sure:

               String pay_pass =  gpv_set_withdraw_pass.getPassWord();



                if(currentChannel.equals(ConstLocalData.ALIPAY)) {
                    presenterDriverDepositPassEnter.doAliGetAuthLoginInfo(
                            HttpConst.URL.AUTH_LOGIN_INFO,
                            currentChannel,
                            pay_pass,
                            VersionUtil.getTheIMEI(),
                            ConstConfig.OS_TYPE,
                            ConstConfig.DEVICE_TYPE,
                            authCode,
                            new JsonObject());



                }else {

                    presenterDriverDepositPassEnter.doWxAuthoLogin(
                            HttpConst.URL.AUTH_LOGIN_INFO,
                            ConstLocalData.WX,
                            pay_pass,
                            VersionUtil.getTheIMEI(),
                            ConstConfig.OS_TYPE,
                            ConstConfig.DEVICE_TYPE,
                            authCode,
                            new JsonObject());
                }




                break;
        }
    }
}
