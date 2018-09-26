package com.adhdriver.work.presenter.driver;

import android.text.TextUtils;

import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.logic.LogicMoney;
import com.adhdriver.work.method.IMoney;
import com.adhdriver.work.method.impl.IMoneyImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.ISettingDepositPassView;
import com.adhdriver.work.verification.VertifyNotNull;

/**
 * Created by Administrator on 2018/1/11.
 * 类描述
 * 版本
 */

public class PresenterDriverSettingDepositPass extends BasePresenter<ISettingDepositPassView> {

    private ISettingDepositPassView iSettingDepositPassView;
    private IMoney iMoney;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;
    private LogicMoney logicMoney;

    public PresenterDriverSettingDepositPass(ISettingDepositPassView iSettingDepositPassView) {
        this.iSettingDepositPassView = iSettingDepositPassView;
        this.iMoney = new IMoneyImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
        this.logicMoney = new LogicMoney();
    }









    public void doSetDepositPass(String url,String pass,String passAgain){


        if(logicMoney.isEmptyDepositPass(pass)){
            iSettingDepositPassView.doVertifyErrorForPassNull();
            return;
        }


        if(logicMoney.isPassLessThan6(pass)) {
            iSettingDepositPassView.doVertifyErrorForPassLessThan6();
            return;
        }

        if(logicMoney.isEmptyPassAgain(passAgain)){

            iSettingDepositPassView.doVertifyErrorForPassAgainNull();
            return;
        }

        if(logicMoney.isPassNotSame(pass,passAgain)){

            iSettingDepositPassView.doVertifyErrorForPassNotSame();
            return;
        }

        iMoney.doSetDepositPass(url, pass, new OnDataBackListener() {
            @Override
            public void onStart() {
                iSettingDepositPassView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {
                iSettingDepositPassView.onDataBackSuccessForSettingDepositPass();
            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity =parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if(vertifyNotNull.isNotNullObj(errorEntity)) {
                    iSettingDepositPassView.onDataBackFail(code,errorEntity.getError_message());
                }else {
                    iSettingDepositPassView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {

                iSettingDepositPassView.dismissLoadingDialog();

            }
        });


    }

}
