package com.adhdriver.work.presenter.driver;

import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.dao.impl.IClearDataDao;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.driver.token.TokenInfo;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IDriverAbout;
import com.adhdriver.work.method.IVisitorAbout;
import com.adhdriver.work.method.impl.IDriverAboutImpl;
import com.adhdriver.work.method.impl.IVisitorAboutImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IChangeLoginPassView;
import com.adhdriver.work.utils.SpUtil;
import com.adhdriver.work.verification.VertifyNotNull;
import com.adhdriver.work.verification.VertifyRule;

import org.w3c.dom.ProcessingInstruction;

/**
 * Created by Administrator on 2017/11/24.
 * 类描述
 * 版本
 */

public class PresenterDriverLoginPassChange extends BasePresenter<IChangeLoginPassView> {

    private IChangeLoginPassView iChangeLoginPassView;
    private IDriverAbout iDriverAbout;
    private IVisitorAbout iVisitorAbout;
    private ParseSerilizable parseSerilizable;
    private IClearDataDao iClearDataDao;


    private VertifyNotNull vertifyNotNull;
    private VertifyRule vertifyRule;


    public PresenterDriverLoginPassChange(IChangeLoginPassView iChangeLoginPassView) {
        this.iChangeLoginPassView = iChangeLoginPassView;
        this.iDriverAbout = new IDriverAboutImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.iClearDataDao = new IClearDataDao();
        this.vertifyNotNull = new VertifyNotNull();
        this.vertifyRule = new VertifyRule();
        this.iVisitorAbout = new IVisitorAboutImpl();

    }




    public void doChangeLoginPass(String url, String pass, String newPass,String newPassAgain) {




        if(vertifyNotNull.isNullString(pass)) {

            iChangeLoginPassView.doVertifyErrorNullOldPass();

            return;
        }

        if(vertifyNotNull.isNullString(newPass)) {

            iChangeLoginPassView.doVertifyErrorNullNewPass();

            return;

        }

        if(!vertifyRule.isLegalPass(newPass)) {


            iChangeLoginPassView.doVertifyErrorUnLegalPass();

            return;
        }

        if(vertifyNotNull.isNullString(newPassAgain)) {

            iChangeLoginPassView.doVertifyErrorNullNewPassAgain();

            return;

        }

        if(!newPass.equals(newPassAgain)) {

            iChangeLoginPassView.doVertifyErrorNewPassNotSameToNewPassAgain();

            return;
        }






        /**
         * 验证.......
         */

        iDriverAbout.doChangeLoginPass(url, pass, newPass, new OnDataBackListener() {
            @Override
            public void onStart() {
                iChangeLoginPassView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                iChangeLoginPassView.onDataBackSuccessForChangeLoginPass();

//                iClearDataDao.doClearAllTable();
//                iClearDataDao.doClearSp();

            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if(null != errorEntity) {

                    iChangeLoginPassView.onDataBackFail(code,errorEntity.getError_message());

                }else {

                    iChangeLoginPassView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }
                iChangeLoginPassView.dismissLoadingDialog();


            }

            @Override
            public void onFinish() {

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



                    }

                    @Override
                    public void onSuccess(String data) {



                        iChangeLoginPassView.onDataBackSuccessForClosePush();

                    }

                    @Override
                    public void onFail(int code, String data) {


                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                        if (vertifyNotNull.isNotNullObj(errorEntity)) {

                            iChangeLoginPassView.onDataBackFail(code, errorEntity.getError_message());
                        } else {

                            iChangeLoginPassView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                        }

                        iChangeLoginPassView.dismissLoadingDialog();
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

                    iChangeLoginPassView.onDataBackSuccessForGetVisitorToken();

                } else {

                    iChangeLoginPassView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    iChangeLoginPassView.onDataBackFail(code, errorEntity.getError_message());

                } else {

                    iChangeLoginPassView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);

                }


            }

            @Override
            public void onFinish() {
                iChangeLoginPassView.dismissLoadingDialog();
            }
        });

    }

    public void doClearAll() {

        iClearDataDao.doClearAllTable();

        iClearDataDao.doClearSp();

    }
}
