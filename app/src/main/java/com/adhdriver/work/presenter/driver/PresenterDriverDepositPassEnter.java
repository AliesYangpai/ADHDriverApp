package com.adhdriver.work.presenter.driver;

import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.entity.ErrorEntity;

import com.adhdriver.work.entity.driver.pay.aliAbout.AliAuthInfo;
import com.adhdriver.work.entity.driver.wallet.WxAuthInfo;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.logic.LogicMoney;
import com.adhdriver.work.method.IThirdPay;
import com.adhdriver.work.method.impl.IThirdPayImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IDepositPassEnterView;
import com.adhdriver.work.verification.VertifyNotNull;
import com.google.gson.JsonObject;

/**
 * Created by Administrator on 2018/1/12.
 * 类描述
 * 版本
 */

public class PresenterDriverDepositPassEnter extends BasePresenter<IDepositPassEnterView> {


    private IDepositPassEnterView iDepositPassEnterView;
    private IThirdPay iThirdPay;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;
    private LogicMoney logicMoney;

    public PresenterDriverDepositPassEnter(IDepositPassEnterView iDepositPassEnterView) {
        this.iDepositPassEnterView = iDepositPassEnterView;
        this.iThirdPay = new IThirdPayImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
        this.logicMoney = new LogicMoney();
    }




    public void doAliGetAuthLoginInfo(String url,
                                      String channel_id,
                                      String pay_password,
                                      String device_id,
                                      String os_type,
                                      String device_type,
                                      String auth_code,
                                      JsonObject parameters) {


        if(logicMoney.isEmptyDepositPass(pay_password)) {

            iDepositPassEnterView.doVertifyErrorForPassNull();
            return;
        }


        iThirdPay.doAliGetAuthLoginInfo(
                url,
                channel_id,
                pay_password,
                device_id,
                os_type,
                device_type,
                auth_code,
                parameters, new OnDataBackListener() {
                    @Override
                    public void onStart() {
                        iDepositPassEnterView.showLoadingDialog();
                    }

                    @Override
                    public void onSuccess(String data) {





                        AliAuthInfo aliAuthInfo = parseSerilizable.specialGetAliAuthInfoBack(data);
                        if (vertifyNotNull.isNotNullObj(aliAuthInfo)) {



                            iDepositPassEnterView.onDataBackSuccessForRebindAndGetAliInfo(aliAuthInfo);
                        }



                    }

                    @Override
                    public void onFail(int code, String data) {

                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data,ErrorEntity.class);
                        if(vertifyNotNull.isNotNullObj(errorEntity)) {
                            iDepositPassEnterView.onDataBackFail(code,errorEntity.getError_message());
                        }else {
                            iDepositPassEnterView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                        }


                    }

                    @Override
                    public void onFinish() {

                        iDepositPassEnterView.dismissLoadingDialog();

                    }
                });
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
                        iDepositPassEnterView.showLoadingDialog();
                    }

                    @Override
                    public void onSuccess(String data) {

                        WxAuthInfo wxAuthInfo = parseSerilizable.getParseToObj(data, WxAuthInfo.class);

                        if(vertifyNotNull.isNotNullObj(wxAuthInfo)) {
                            iDepositPassEnterView.onDataBackSuccessForGetWxInfo(wxAuthInfo);
                        }
                    }

                    @Override
                    public void onFail(int code, String data) {

                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                        if (vertifyNotNull.isNotNullObj(errorEntity)) {
                            iDepositPassEnterView.onDataBackFail(code, errorEntity.getError_message());
                        } else {

                            iDepositPassEnterView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                        }
                    }

                    @Override
                    public void onFinish() {
                        iDepositPassEnterView.dismissLoadingDialog();
                    }
                });


    }

}
