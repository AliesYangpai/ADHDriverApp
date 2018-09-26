package com.adhdriver.work.presenter.driver;

import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.dao.impl.IClearDataDao;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.driver.token.TokenInfo;
import com.adhdriver.work.entity.driver.wallet.Wallet;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IDriverAbout;
import com.adhdriver.work.method.IVisitorAbout;
import com.adhdriver.work.method.impl.IDriverAboutImpl;
import com.adhdriver.work.method.impl.IVisitorAboutImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.ISettingDriverView;
import com.adhdriver.work.utils.SpUtil;
import com.adhdriver.work.verification.VertifyNotNull;

/**
 * Created by Administrator on 2017/11/23.
 * 类描述
 * 版本
 */

public class PresenterDriverSetting extends BasePresenter<ISettingDriverView> {


    private ISettingDriverView iSettingDriverView;
    private IDriverAbout iDriverAbout;
    private IVisitorAbout iVisitorAbout;
    private IClearDataDao iClearDataDao;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;

    public PresenterDriverSetting(ISettingDriverView iSettingDriverView) {
        this.iSettingDriverView = iSettingDriverView;
        this.iDriverAbout = new IDriverAboutImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.iClearDataDao = new IClearDataDao();
        this.vertifyNotNull = new VertifyNotNull();
        this.iVisitorAbout = new IVisitorAboutImpl();
    }


    /**
     * 获取我的钱包信息
     *
     * @param url
     */
    public void doGetMyWalletInfo(String url) {


        iDriverAbout.doGetMyWalletInfo(url, new OnDataBackListener() {
            @Override
            public void onStart() {

                iSettingDriverView.showLoadingDialog();

            }

            @Override
            public void onSuccess(String data) {


                Wallet wallet = parseSerilizable.getParseToObj(data, Wallet.class);
                if (vertifyNotNull.isNotNullObj(wallet)) {

                    boolean has_pay_pwd = wallet.isHas_pay_pwd();

                    if (has_pay_pwd) {

                        iSettingDriverView.onDataBackSuccessForWalletInfoHasDepositPass();
                    } else {
                        iSettingDriverView.onDataBackSuccessForWalletInfoNoDepositPass();
                    }
                }
            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    iSettingDriverView.onDataBackFail(code, errorEntity.getError_message());
                } else {

                    iSettingDriverView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {

                iSettingDriverView.dismissLoadingDialog();
            }
        });

    }






    /**
     * 关闭推送
     *
     * @param url
     * @param os_type
     * @param os_version
     * @param model
     * @param device_id
     * @param push_type
     * @param push_token
     * @param quiet_mode
     */
    public void doLogout(String url,
                            String os_type,
                            String os_version,
                            String model,
                            String device_id,
                            String push_type,
                            String push_token,
                            boolean quiet_mode) {


        iDriverAbout.doClosePush(url,
                                 os_type,
                                 os_version,
                                 model,
                                 device_id,
                                 push_type,
                                 push_token,
                                 quiet_mode, new OnDataBackListener() {
                    @Override
                    public void onStart() {

                        iSettingDriverView.showLoadingDialog();

                    }

                    @Override
                    public void onSuccess(String data) {



                        iSettingDriverView.onDataBackSuccessForClosePush();

                    }

                    @Override
                    public void onFail(int code, String data) {


                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                        if (vertifyNotNull.isNotNullObj(errorEntity)) {

                            iSettingDriverView.onDataBackFail(code, errorEntity.getError_message());
                        } else {

                            iSettingDriverView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                        }

                        iSettingDriverView.dismissLoadingDialog();

                    }

                    @Override
                    public void onFinish() {


                    }
                });

    }


    public void doInitVisitorLogon(String url) {

        iVisitorAbout.doVisitorLogon(url, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {


                TokenInfo tokenInfo = parseSerilizable.getParseToObj(data, TokenInfo.class);

                if (vertifyNotNull.isNotNullObj(tokenInfo)) {

                    String access_token = tokenInfo.getAccess_token();

                    SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_TOKEN, access_token); //这个是 visitor的token
                    SpUtil.getInstance().saveBooleanTosp(ConstSp.SP_KEY_VISITOR, ConstSp.SP_VALUE.DEFAULT_TRUE_VISITOR);

                    iSettingDriverView.onDataBackSuccessForGetVisitorToken();

                } else {

                    iSettingDriverView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    iSettingDriverView.onDataBackFail(code, errorEntity.getError_message());

                } else {

                    iSettingDriverView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);

                }


            }

            @Override
            public void onFinish() {

                iSettingDriverView.dismissLoadingDialog();
            }
        });

    }

    public void doClearAll() {

        iClearDataDao.doClearAllTable();

        iClearDataDao.doClearSp();

    }




}
