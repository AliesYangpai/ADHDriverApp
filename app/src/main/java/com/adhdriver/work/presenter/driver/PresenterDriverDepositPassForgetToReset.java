package com.adhdriver.work.presenter.driver;

import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IDriverAbout;
import com.adhdriver.work.method.impl.IDriverAboutImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IDepositPassForgetToResetView;
import com.adhdriver.work.verification.VertifyNotNull;

/**
 * Created by Administrator on 2017/11/24.
 * 类描述  重置提现密码的presenter
 * 版本
 */

public class PresenterDriverDepositPassForgetToReset extends BasePresenter<IDepositPassForgetToResetView> {

    private IDepositPassForgetToResetView iDepositPassForgetToResetView;
    private IDriverAbout iDriverAbout;
    private VertifyNotNull vertifyNotNull;
    private ParseSerilizable parseSerilizable;


    public PresenterDriverDepositPassForgetToReset(IDepositPassForgetToResetView iDepositPassForgetToResetView) {
        this.iDepositPassForgetToResetView = iDepositPassForgetToResetView;
        this.iDriverAbout=  new IDriverAboutImpl();
        this.vertifyNotNull = new VertifyNotNull();
        this.parseSerilizable = new ParseSerilizable();
    }


    /**
     * 重置支付密码
     * @param url
     * @param last_card_no
     * @param newPass
     * @param passAgain
     * @param pass_code
     */
    public void doResetDepositPass(String url,String last_card_no, String newPass,String passAgain, String pass_code) {



        if(vertifyNotNull.isNullString(last_card_no)) {

            iDepositPassForgetToResetView.doVertifyErrorNullIDLast4();
            return;
        }

        if(last_card_no.length() < 4) {

            iDepositPassForgetToResetView.doVertifyErrorIDCompleteLast4();
            return;
        }

        if(vertifyNotNull.isNullString(newPass)) {

            iDepositPassForgetToResetView.doVertifyErrorNullDepositPass();
            return;
        }

        if(newPass.length() < 6) {
            iDepositPassForgetToResetView.doVertifyErrorUnLegalDepositPass();
            return;
        }



        if(vertifyNotNull.isNullString(passAgain)) {

            iDepositPassForgetToResetView.doVertifyErrorEnterDepositPassAgain();

            return;
        }


        if(!newPass.equals(passAgain)) {

            iDepositPassForgetToResetView.doVertifyErrorNewPassNotSameToNewPassAgain();
            return;
        }












        iDriverAbout.doResetDepositPass(url, last_card_no, newPass, pass_code, new OnDataBackListener() {
            @Override
            public void onStart() {
                iDepositPassForgetToResetView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                iDepositPassForgetToResetView.onDataBackSuccessForResetDepositPass();


            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity  = parseSerilizable.getParseToObj(data,ErrorEntity.class);

                if(vertifyNotNull.isNotNullObj(errorEntity)) {

                    iDepositPassForgetToResetView.onDataBackFail(code,errorEntity.getError_message());
                }else {


                    iDepositPassForgetToResetView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {

                iDepositPassForgetToResetView.dismissLoadingDialog();

            }
        });

    }

}
