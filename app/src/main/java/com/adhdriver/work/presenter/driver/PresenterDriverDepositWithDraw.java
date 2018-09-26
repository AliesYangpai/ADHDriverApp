package com.adhdriver.work.presenter.driver;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.driver.pay.aliAbout.AliAuthInfo;
import com.adhdriver.work.entity.driver.wallet.AliAuthRespose;
import com.adhdriver.work.entity.driver.wallet.Wallet;
import com.adhdriver.work.entity.driver.wallet.WxAuthInfo;
import com.adhdriver.work.function.FunctionCash;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.logic.LogicMoney;
import com.adhdriver.work.method.IMoney;
import com.adhdriver.work.method.impl.IMoneyImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IDepositWithDrawView;
import com.adhdriver.work.verification.VertifyNotNull;

/**
 * Created by Administrator on 2018/1/11.
 * 类描述  提现界面的pRESENTEr
 * 版本
 */

public class PresenterDriverDepositWithDraw extends BasePresenter<IDepositWithDrawView> {


    private IDepositWithDrawView iDepositWithDrawView;
    private IMoney iMoney;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;
    private LogicMoney logicMoney;

    private FunctionCash functionCash;


    public PresenterDriverDepositWithDraw(IDepositWithDrawView iDepositWithDrawView) {
        this.iDepositWithDrawView = iDepositWithDrawView;
        this.iMoney = new IMoneyImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
        this.logicMoney = new LogicMoney();
        this.functionCash = new FunctionCash();
    }


    public void doCheckDepositMoney(String amount) {

        if (logicMoney.isEmptyCash(amount)) {

            iDepositWithDrawView.doVertifyErrorForCashNull();
            return;
        }

        if (logicMoney.isPointStart(amount)) {
            iDepositWithDrawView.doVertifyErrorForCashNull();
            return;
        }

        if (logicMoney.is0(amount)) {

            iDepositWithDrawView.doVertifyErrorForCash0();
            return;
        }

        if (logicMoney.isSmallThan1(amount)) {

            iDepositWithDrawView.doVertifyErrorForCashLessThanOne();
            return;
        }


        String amountContent = functionCash.getZeroFrontPoint(amount);




        iDepositWithDrawView.doChcekAmountSuccess(amountContent);

    }


    public void doShowData(Wallet wallet,String currentChannel) {
        if (vertifyNotNull.isNotNullObj(wallet)) {



            switch (currentChannel) {

                case ConstLocalData.WX:


                    WxAuthInfo wx_pay_auth_info = wallet.getWx_pay_auth_info();
                    if (vertifyNotNull.isNotNullObj(wx_pay_auth_info)) {
                        if(vertifyNotNull.isNotNullObj(wx_pay_auth_info)) {
                            iDepositWithDrawView.showDataOnUi(wallet);
                        }
                    }

                    break;

                case ConstLocalData.ALIPAY:

                    AliAuthInfo alipay_auth_info = wallet.getAlipay_auth_info();
                    if (vertifyNotNull.isNotNullObj(alipay_auth_info)) {
                        AliAuthRespose alipay_user_userinfo_share_response = alipay_auth_info.getAlipay_user_userinfo_share_response();
                        if(vertifyNotNull.isNotNullObj(alipay_user_userinfo_share_response)) {
                            iDepositWithDrawView.showDataOnUi(wallet);
                        }
                    }


                    break;
            }


        }

    }


    public void doDepositWithDraw(
            String url,
            String amount,
            String payment_channel_id,
            String os_type,
            String device_id,
            String open_id,
            String pay_password) {


        iMoney.doBalanceDeposit(
                url,
                amount,
                payment_channel_id,
                os_type,
                device_id,
                open_id,
                pay_password, new OnDataBackListener() {
                    @Override
                    public void onStart() {
                        iDepositWithDrawView.showLoadingDialog();
                    }

                    @Override
                    public void onSuccess(String data) {


                        iDepositWithDrawView.onDataBackSuccessForDepositWithDraw();

                    }

                    @Override
                    public void onFail(int code, String data) {
                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                        if (vertifyNotNull.isNotNullObj(errorEntity)) {
                            iDepositWithDrawView.onDataBackFail(code, errorEntity.getError_message());
                        } else {

                            iDepositWithDrawView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                        }

                    }

                    @Override
                    public void onFinish() {

                        iDepositWithDrawView.dismissLoadingDialog();
                    }
                });


    }

}
