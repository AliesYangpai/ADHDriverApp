package com.adhdriver.work.presenter.common;

import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.dao.IBaseDao;
import com.adhdriver.work.dao.impl.IOssConfigDaoImpl;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.OssConfig;
import com.adhdriver.work.entity.driver.temp.RegConfirm;
import com.adhdriver.work.entity.driver.token.TokenInfo;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.logic.LogicAccout;
import com.adhdriver.work.logic.LogicExam;
import com.adhdriver.work.logic.LogicRegister;
import com.adhdriver.work.logic.LogicVehicle;
import com.adhdriver.work.method.IDriverAbout;
import com.adhdriver.work.method.IOssConfig;
import com.adhdriver.work.method.IRegister;
import com.adhdriver.work.method.impl.IDriverAboutImpl;
import com.adhdriver.work.method.impl.IOssConfigImpl;
import com.adhdriver.work.method.impl.IRegisterImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.ILoginView;
import com.adhdriver.work.utils.SpUtil;
import com.adhdriver.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created by Administrator on 2017/12/8.
 * 类描 登陆界面的login
 * 版本
 */

public class PresenterLogin extends BasePresenter<ILoginView> {


    private IDriverAbout iDriverAbout;
    private IOssConfig iOssConfig;
    private IRegister iRegister;


    private ILoginView iLoginView;


    private IBaseDao<OssConfig> iOssConfigDao;


    private VertifyNotNull vertifyNotNull;
    private ParseSerilizable parseSerilizable;


    private LogicAccout logicAccout;
    private LogicRegister logicRegister;
    private LogicVehicle logicVehicle;
    private LogicExam logicExam;

    public PresenterLogin(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        this.iDriverAbout = new IDriverAboutImpl();
        this.iOssConfig = new IOssConfigImpl();
        this.iRegister = new IRegisterImpl();

        this.vertifyNotNull = new VertifyNotNull();
        this.parseSerilizable = new ParseSerilizable();
        this.logicAccout = new LogicAccout();
        this.iOssConfigDao = new IOssConfigDaoImpl();


        this.logicRegister = new LogicRegister();
        this.logicVehicle = new LogicVehicle();
        this.logicExam = new LogicExam();
    }


    /**
     * 司机登陆
     *
     * @param url
     * @param username
     * @param password
     */
    public void doLogon(String url, String username, String password) {

        if (vertifyNotNull.isNullString(username)) {

            iLoginView.doVertifyErrorNullUsername();
            return;
        }

        if (!logicAccout.isLegalPhone(username)) {
            iLoginView.doVertifyErrorUnlegalUsername();
            return;
        }


        if (vertifyNotNull.isNullString(password)) {

            iLoginView.doVertifyErrorNullPassword();
            return;
        }


        if (!logicAccout.isLegalPass(password)) {

            iLoginView.doVertifyErrorPasswordBetween8_16();
            return;
        }


        iDriverAbout.doLogin(url, username, password, new OnDataBackListener() {
            @Override
            public void onStart() {

                iLoginView.showLoadingDialog();

            }

            @Override
            public void onSuccess(String data) {


                if (logicAccout.isJsonObjStart(data)) {


                    TokenInfo tokenInfo = parseSerilizable.getParseToObj(data, TokenInfo.class);

                    if (vertifyNotNull.isNotNullObj(tokenInfo)) {
                        iLoginView.onDataBackSuccessForLoginJsonObject(tokenInfo.getAccess_token());
                    } else {
                        iLoginView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                    }

                } else if (logicAccout.isJsonArrayStart(data)) {

                    List<TokenInfo> tokenInfos = parseSerilizable.getParseToNoItemsList(data, TokenInfo.class);

                    if (vertifyNotNull.isNotNullListSize(tokenInfos)) {
                        iLoginView.onDataBackSuccessForLoginJsonArray(tokenInfos);
                    } else {
                        iLoginView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                    }

                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iLoginView.onDataBackFail(code, errorEntity.getError_message());
                } else {

                    iLoginView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);

                }

            }

            @Override
            public void onFinish() {
                iLoginView.dismissLoadingDialog();
            }
        });


    }


    /**
     * 获取oss配置信息
     *
     * @param url
     * @param accessToken
     */
    public void doGetOssConfigInfo(String url, final String accessToken) {

        iOssConfig.doGetOssConfigInfo(url, accessToken, new OnDataBackListener() {
            @Override
            public void onStart() {
                iLoginView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                OssConfig ossConfig = parseSerilizable.getParseToObj(data, OssConfig.class);

                if (vertifyNotNull.isNotNullObj(ossConfig)) {

                    OssConfig first = iOssConfigDao.findFirst(OssConfig.class);

                    boolean result = false;
                    if (vertifyNotNull.isNotNullObj(first)) {

                        iOssConfigDao.deleteAll(OssConfig.class);
                        result = iOssConfigDao.save(ossConfig);
                    } else {
                        result = iOssConfigDao.save(ossConfig);
                    }

                    if (result) {
                        iLoginView.onDataBackSuccessForOssConfig(accessToken);
                    }

                } else {

                    iLoginView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iLoginView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iLoginView.onDataBackFail(code, errorEntity.getError_message());
                }
            }

            @Override
            public void onFinish() {
                iLoginView.dismissLoadingDialog();
            }
        });

    }


    /**
     * 获取当前的司机注册状态
     * @param url
     * @param accessToken
     */
    public void doCheckRegStatues(String url, final String accessToken) {

        iRegister.doGetRegStatue(url, accessToken, new OnDataBackListener() {
            @Override
            public void onStart() {
                iLoginView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {


                RegConfirm regConfirm = parseSerilizable.getParseToObj(data, RegConfirm.class);

                if (vertifyNotNull.isNotNullObj(regConfirm)) {


                    String identity_card_no = regConfirm.getIdentity_card_no();
                    String vehicle_brand = regConfirm.getVehicle_brand();
                    boolean passed = regConfirm.isPassed();//考试是否通过
                    String certification = regConfirm.getCertification(); //用户身证验证是否通过sssssssssss
                    String validity = regConfirm.getValidity();//司机是否通过审核（合伙人验证）
                    String driverId = regConfirm.getDriver_id();
                    long certify_organization_id = regConfirm.getCertify_organization_id(); //城市运营中心 id 用于标记 最终审核是否通过
                    String car_pass = regConfirm.getCar_pass();

                    SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_DRIVER_ID, driverId);

                    if (logicRegister.isNoIdentifyNo(identity_card_no)) {

                        iLoginView.onDataBackSuccessForSetIdentifyInfo(accessToken);
                        return;
                    }

                    if (logicVehicle.isNoVehicleInfo(vehicle_brand)) {

                        iLoginView.onDataBackSuccessForSetVehicleInfo(accessToken);
                        return;
                    }


                    if (logicRegister.isCertificationAuthorizing(certification)) {

                        iLoginView.onDataBackSuccessForIndentifyInfoAuthorizing();
                        return;
                    }


                    if (logicRegister.isCertificationRejected(certification)) {

                        iLoginView.onDataBackSuccessForIndentifyInfoAuthorReject(accessToken);
                        return;
                    }

                    if (logicRegister.isCertificationApproved(certification)) {


                        if (logicVehicle.isCarPassApproved(car_pass)) {


                            if (logicExam.isExamPass(passed)) {

                                iLoginView.onDataBackSuccessForAllAcessGoToMain(accessToken);
                            } else {

                                iLoginView.onDataBackSuccessForDoLearnAndExam(accessToken);
                            }

                        } else if (logicVehicle.isCarPassRejected(car_pass)) {


                            iLoginView.onDataBackSuccessForVehicleReject(accessToken);

                        } else {

                            iLoginView.onDataBackSuccessForForVehicleAuthorizing();
                        }

                    }


                } else {

                    iLoginView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }


            }


            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iLoginView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iLoginView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);

                }
            }

            @Override
            public void onFinish() {

                iLoginView.dismissLoadingDialog();
            }
        });

    }
}
