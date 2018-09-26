package com.adhdriver.work.presenter.driver;

import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.driver.token.TokenInfo;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IDriverAbout;
import com.adhdriver.work.method.IRegister;
import com.adhdriver.work.method.impl.IDriverAboutImpl;
import com.adhdriver.work.method.impl.IRegisterImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IRegDriverPhonePassView;
import com.adhdriver.work.utils.SpUtil;
import com.adhdriver.work.verification.VertifyNotNull;
import com.adhdriver.work.verification.VertifyRule;

/**
 * Created by Administrator on 2018/1/17.
 * 类描述
 * 版本
 */

public class PresenterDriverRegPhonePass extends BasePresenter<IRegDriverPhonePassView> {

    private IRegDriverPhonePassView iRegDriverPhonePassView;
    private IRegister iRegister;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;
    private VertifyRule vertifyRule;



    public PresenterDriverRegPhonePass(IRegDriverPhonePassView iRegDriverPhonePassView) {
        this.iRegDriverPhonePassView = iRegDriverPhonePassView;
        this.iRegister = new IRegisterImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
        this.vertifyRule = new VertifyRule();
    }


    public void doGetPhoneCode(String url, String phone, String options) {


        if(vertifyNotNull.isNullString(phone)) {

            iRegDriverPhonePassView.doVertifyErrorForNullPhone();
            return;
        }

        if(!vertifyRule.isLegalPhone(phone)) {

            iRegDriverPhonePassView.doVertifyErrorForUnlegalPhone();
            return;
        }

        iRegister.doGetPhoneCode(url, phone, options, new OnDataBackListener() {
            @Override
            public void onStart() {
                iRegDriverPhonePassView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                iRegDriverPhonePassView.onDataBackSuccessForGetPhoneCode();

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iRegDriverPhonePassView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iRegDriverPhonePassView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {

                iRegDriverPhonePassView.dismissLoadingDialog();
            }
        });
    }


    public void doVertifyPhoneCode(String url, final String phone, final String pass_code, final String pass) {


        if(vertifyNotNull.isNullString(phone)) {
            iRegDriverPhonePassView.doVertifyErrorForNullPhone();
            return;
        }

        if(vertifyNotNull.isNullString(pass_code)) {

            iRegDriverPhonePassView.doVertifyErrorForNullPhoneCode();
            return;
        }

        if(vertifyNotNull.isNullString(pass)) {

            iRegDriverPhonePassView.doVertifyErrorForNullPass();
            return;
        }

        if(!vertifyRule.isLegalPhone(phone)) {

            iRegDriverPhonePassView.doVertifyErrorForUnlegalPhone();
            return;
        }

        if(!vertifyRule.isLegalPass(pass)) {
            iRegDriverPhonePassView.doVertifyErrorForUnlegalPass();
            return;
        }


        iRegister.doConfirmVertifyCode(url, phone, pass_code, new OnDataBackListener() {
            @Override
            public void onStart() {
                iRegDriverPhonePassView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                iRegDriverPhonePassView.onDataBackSuccessForVeryifyPhoneCode(phone,pass_code,pass);

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iRegDriverPhonePassView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iRegDriverPhonePassView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

                iRegDriverPhonePassView.dismissLoadingDialog();
            }

            @Override
            public void onFinish() {

            }
        });

    }

    public void doRegister(String url,
                           final String phone,
                           final String pass,
                           String code,
                           String defaultUserName) {

        iRegister.doRegister(url, phone, pass, code, defaultUserName, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {


                boolean isSaved = SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_SGIN_ACCOUNT, phone);


                if (isSaved) {
                    iRegDriverPhonePassView.onDataBackSuccessForReg(phone,pass);
                }




            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iRegDriverPhonePassView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iRegDriverPhonePassView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

                iRegDriverPhonePassView.dismissLoadingDialog();
            }

            @Override
            public void onFinish() {

            }
        });
    }


    public void doRegGetToken(String url,
                              String user_phone,
                              String user_password) {

        iRegister.doRegGetToken(url, user_phone, user_password, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {
                TokenInfo tokenInfo = parseSerilizable.getParseToObj(data, TokenInfo.class);
                if(vertifyNotNull.isNotNullObj(tokenInfo)) {

                    iRegDriverPhonePassView.onDataBackSuccessForGetToken(tokenInfo);
                }else {

                    iRegDriverPhonePassView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data,ErrorEntity.class);
                if(vertifyNotNull.isNotNullObj(errorEntity)) {
                    iRegDriverPhonePassView.onDataBackFail(code,errorEntity.getError_message());
                }else {
                    iRegDriverPhonePassView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {

            }
        });

    }
}
