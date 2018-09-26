package com.adhdriver.work.presenter.driver;

import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.dao.IBaseDao;
import com.adhdriver.work.dao.impl.IUserDaoImpl;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.User;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IDriverAbout;
import com.adhdriver.work.method.impl.IDriverAboutImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IDepositPassForgetToGetCodeView;
import com.adhdriver.work.verification.VertifyNotNull;

/**
 * Created by Administrator on 2017/11/24.
 * 类描述 忘记体现密码并获取code
 * 版本
 */

public class PresenterDriverDepositPassForgetToGetCode extends BasePresenter<IDepositPassForgetToGetCodeView> {

    private IDepositPassForgetToGetCodeView iDepositPassForgetToGetCodeView;
    private IDriverAbout iDriverAbout;
    private VertifyNotNull vertifyNotNull;
    private ParseSerilizable parseSerilizable;
    private IBaseDao<User> iUserDao;


    public PresenterDriverDepositPassForgetToGetCode(IDepositPassForgetToGetCodeView iDepositPassForgetToGetCodeView) {
        this.iDepositPassForgetToGetCodeView = iDepositPassForgetToGetCodeView;
        this.iDriverAbout = new IDriverAboutImpl();
        this.vertifyNotNull = new VertifyNotNull();
        this.parseSerilizable = new ParseSerilizable();
        this.iUserDao = new IUserDaoImpl();
    }


    public void doGetUserPhoneFromDb() {


        User user = iUserDao.findFirst(User.class);

        if (vertifyNotNull.isNotNullObj(user)) {

            String phone = user.getPhone();

            iDepositPassForgetToGetCodeView.doGetUserPhoneToUi(phone);

        }

    }


    /**
     * 获取验证码
     *
     * @param url
     * @param phone
     * @param options
     */
    public void doGetDepositeVertifyCode(String url, String phone, String options) {


        iDriverAbout.doGetDepositeVertifyCode(url, phone, options, new OnDataBackListener() {
            @Override
            public void onStart() {
                iDepositPassForgetToGetCodeView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    iDepositPassForgetToGetCodeView.onDataBackFail(code, errorEntity.getError_message());

                } else {


                    iDepositPassForgetToGetCodeView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }


            }

            @Override
            public void onFinish() {

                iDepositPassForgetToGetCodeView.dismissLoadingDialog();

            }
        });


    }


    /**
     * 验证验证码
     * @param url
     * @param phone
     * @param pass_code
     */
    public void doConfirmVertifyCode(String url, String phone, String pass_code) {


        if (vertifyNotNull.isNullString(pass_code)) {

            iDepositPassForgetToGetCodeView.doVertifyErrorNullVertifyCode();

            return;
        }

        if (pass_code.length() < 6) {

            iDepositPassForgetToGetCodeView.doVertifyErrorUnCompletVertifyCode();

            return;
        }


            iDriverAbout.doConfirmVertifyCode(url, phone, pass_code, new OnDataBackListener() {
                @Override
                public void onStart() {
                    iDepositPassForgetToGetCodeView.showLoadingDialog();
                }

                @Override
                public void onSuccess(String data) {


                    iDepositPassForgetToGetCodeView.onDataBackSuccessForConfirmDepositVertifyCode();

                }

                @Override
                public void onFail(int code, String data) {

                    ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                    if (vertifyNotNull.isNotNullObj(errorEntity)) {

                        iDepositPassForgetToGetCodeView.onDataBackFail(code, errorEntity.getError_message());

                    } else {

                        iDepositPassForgetToGetCodeView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);

                    }

                }

                @Override
                public void onFinish() {
                    iDepositPassForgetToGetCodeView.dismissLoadingDialog();
                }
            });


    }

}
