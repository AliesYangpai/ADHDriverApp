package com.adhdriver.work.presenter.driver;

import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IDriverAbout;
import com.adhdriver.work.method.impl.IDriverAboutImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IDepositPassChangeView;
import com.adhdriver.work.verification.VertifyNotNull;

/**
 * Created by Administrator on 2017/11/24.
 * 类描述   修改提现密码的activity
 * 版本
 */

public class PresenterDriverDepositPassChange extends BasePresenter<IDepositPassChangeView> {

    private IDepositPassChangeView iDepositPassChangeView;
    private IDriverAbout iDriverAbout;
    private VertifyNotNull vertifyNotNull;
    private ParseSerilizable parseSerilizable;

    public PresenterDriverDepositPassChange(IDepositPassChangeView iDepositPassChangeView) {
        this.iDepositPassChangeView = iDepositPassChangeView;
        this.iDriverAbout = new IDriverAboutImpl();
        this.vertifyNotNull = new VertifyNotNull();
        this.parseSerilizable = new ParseSerilizable();
    }


    /**
     * 更改提现密码
     *
     * @param url
     * @param oldPass
     * @param newPass
     * @param newPassAgain
     */
    public void doChangeDepositePass(String url, String oldPass, String newPass, String newPassAgain) {


        if (vertifyNotNull.isNullString(oldPass)) {

            iDepositPassChangeView.doVertifyErrorNullOldPass();
            return;
        }

        if (vertifyNotNull.isNullString(newPass)) {

            iDepositPassChangeView.doVertifyErrorNullNewPass();
            return;
        }

        if (newPass.length() < 6) {

            iDepositPassChangeView.doVertifyErrorUnLegalPass();

            return;
        }

        if (vertifyNotNull.isNullString(newPassAgain)) {

            iDepositPassChangeView.doVertifyErrorNullNewPassAgain();
            return;
        }

        if(!newPass.equals(newPassAgain)) {

            iDepositPassChangeView.doVertifyErrorNewPassNotSameToNewPassAgain();
            return;

        }


        iDriverAbout.doChangeDepositePass(url,
                oldPass,
                newPass, new OnDataBackListener() {
                    @Override
                    public void onStart() {
                        iDepositPassChangeView.showLoadingDialog();
                    }

                    @Override
                    public void onSuccess(String data) {

                        iDepositPassChangeView.onDataBackSuccessForChangeDepositPass();

                    }

                    @Override
                    public void onFail(int code, String data) {

                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                        if (vertifyNotNull.isNotNullObj(errorEntity)) {

                            iDepositPassChangeView.onDataBackFail(code, errorEntity.getError_message());
                        } else {

                            iDepositPassChangeView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);

                        }


                    }

                    @Override
                    public void onFinish() {
                        iDepositPassChangeView.dismissLoadingDialog();
                    }
                });
    }

}
