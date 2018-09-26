package com.adhdriver.work.presenter.driver;

import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.dao.impl.IClearDataDao;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.UdpEntity;
import com.adhdriver.work.entity.driver.token.TokenInfo;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IDriverAbout;
import com.adhdriver.work.method.IVisitorAbout;
import com.adhdriver.work.method.impl.IDriverAboutImpl;
import com.adhdriver.work.method.impl.IVisitorAboutImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.iview.driver.IKickOutView;
import com.adhdriver.work.utils.SpUtil;
import com.adhdriver.work.verification.VertifyNotNull;

/**
 * Created by Administrator on 2018/1/22.
 * 类描述
 * 版本
 */

public class PresenterDriverKickOut extends BasePresenter<IKickOutView> {

    private IKickOutView iKickOutView;
    private IVisitorAbout iVisitorAbout;
    private IDriverAbout iDriverAbout;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;

    private IClearDataDao iClearDataDao;

    public PresenterDriverKickOut(IKickOutView iKickOutView) {
        this.iKickOutView = iKickOutView;
        this.iDriverAbout = new IDriverAboutImpl();
        this.iVisitorAbout = new IVisitorAboutImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
        this.iClearDataDao = new IClearDataDao();

    }



    public void doShowKickOutAlert(UdpEntity udpEntity){

        iKickOutView.doShowKickOutAlertDialog(udpEntity);
    }

    public void doDestroyAlertDialog(){

        iKickOutView.doDestroyAlertDialog();
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



                    }

                    @Override
                    public void onSuccess(String data) {



                        iKickOutView.onDataBackSuccessForClosePush();

                    }

                    @Override
                    public void onFail(int code, String data) {


                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                        if (vertifyNotNull.isNotNullObj(errorEntity)) {

                            iKickOutView.onDataBackFail(code, errorEntity.getError_message());
                        } else {

                            iKickOutView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                        }


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

                    iKickOutView.onDataBackSuccessForGetVisitorToken();

                } else {

                    iKickOutView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    iKickOutView.onDataBackFail(code, errorEntity.getError_message());

                } else {

                    iKickOutView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);

                }


            }

            @Override
            public void onFinish() {

            }
        });

    }

    public void doClearAll() {

        iClearDataDao.doClearAllTable();

        iClearDataDao.doClearSp();

    }



}
