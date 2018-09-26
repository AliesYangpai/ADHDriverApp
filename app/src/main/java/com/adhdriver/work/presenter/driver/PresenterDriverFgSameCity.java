package com.adhdriver.work.presenter.driver;

import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.dao.IBaseDao;
import com.adhdriver.work.dao.impl.IOssConfigDaoImpl;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.OssConfig;
import com.adhdriver.work.entity.driver.wallet.TodayIncome;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IOssConfig;
import com.adhdriver.work.method.IWallet;
import com.adhdriver.work.method.impl.IOssConfigImpl;
import com.adhdriver.work.method.impl.IWalletImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IFgOrderNotTakeSameCityView;
import com.adhdriver.work.verification.VertifyNotNull;

/**
 * Created by Administrator on 2017/12/27.
 * 类描述
 * 版本
 */

public class PresenterDriverFgSameCity extends BasePresenter<IFgOrderNotTakeSameCityView> {

    private IWallet iWallet;
    private IFgOrderNotTakeSameCityView iFgOrderNotTakeSameCityView;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;


    public PresenterDriverFgSameCity(IFgOrderNotTakeSameCityView iFgOrderNotTakeSameCityView) {
        this.iFgOrderNotTakeSameCityView = iFgOrderNotTakeSameCityView;
        this.iWallet = new IWalletImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();

    }



    /**
     * 获取每日收益
     * @param url
     */

    public void doGetDailyInCome(String url) {


        iWallet.doGetDailyInCome(url, new OnDataBackListener() {
            @Override
            public void onStart() {
                iFgOrderNotTakeSameCityView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {


                TodayIncome todayIncome = parseSerilizable.getParseToObj(data, TodayIncome.class);

                if(vertifyNotNull.isNotNullObj(todayIncome)) {
                    iFgOrderNotTakeSameCityView.onDataBackSuccessForDailyInCome(todayIncome);
                }else {

                    iFgOrderNotTakeSameCityView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }


            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity =  parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if(vertifyNotNull.isNotNullObj(errorEntity)){
                    iFgOrderNotTakeSameCityView.onDataBackFail(code,errorEntity.getError_message());
                }else {
                    iFgOrderNotTakeSameCityView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);

                }
            }

            @Override
            public void onFinish() {
                iFgOrderNotTakeSameCityView.dismissLoadingDialog();
            }
        });

    }


    /**
     * 刷新获取fresh
     * @param url
     */
    public void doGetDailyInComeInFresh(String url){


        iWallet.doGetDailyInCome(url, new OnDataBackListener() {
            @Override
            public void onStart() {



            }

            @Override
            public void onSuccess(String data) {

                TodayIncome todayIncome = parseSerilizable.getParseToObj(data, TodayIncome.class);

                if(vertifyNotNull.isNotNullObj(todayIncome)) {
                    iFgOrderNotTakeSameCityView.onDataBackSuccessForDailyInComeInFresh(todayIncome);
                }else {

                    iFgOrderNotTakeSameCityView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }


            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity =  parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if(vertifyNotNull.isNotNullObj(errorEntity)){
                    iFgOrderNotTakeSameCityView.onDataBackFail(code,errorEntity.getError_message());
                }else {
                    iFgOrderNotTakeSameCityView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);

                }
            }

            @Override
            public void onFinish() {


                iFgOrderNotTakeSameCityView.dismissFreshLoading();
            }
        });
    }
}
