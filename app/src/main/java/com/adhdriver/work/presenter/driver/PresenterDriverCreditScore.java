package com.adhdriver.work.presenter.driver;

import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.dao.IBaseDao;
import com.adhdriver.work.dao.impl.IUserDaoImpl;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.User;
import com.adhdriver.work.entity.driver.credit.DriverCridet;
import com.adhdriver.work.entity.driver.credit.RatingDetial;
import com.adhdriver.work.function.FunctionCredit;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IDriverAbout;
import com.adhdriver.work.method.impl.IDriverAboutImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.iview.driver.ICreditScoreView;
import com.adhdriver.work.verification.VertifyNotNull;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/1/8.
 * 类描述
 * 版本
 */

public class PresenterDriverCreditScore extends BasePresenter<ICreditScoreView> {


    private ICreditScoreView iCreditScoreView;
    private IDriverAbout iDriverAbout;

    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;


    private IBaseDao<User> iUserDao;

    private FunctionCredit functionCredit;

    public PresenterDriverCreditScore(ICreditScoreView iCreditScoreView) {
        this.iCreditScoreView = iCreditScoreView;
        this.iDriverAbout = new IDriverAboutImpl();
        this.iUserDao = new IUserDaoImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
        this.functionCredit = new FunctionCredit();
    }


    public void doGetUserInfoFromDb() {

        User user = iUserDao.findFirst(User.class);

        iCreditScoreView.onDataBackSuccessDbForUser(user);
    }

    /**
     * 获取信用分
     *
     * @param url
     */
    public void doGetCreditScore(String url) {

        iDriverAbout.doGetCreditScore(url, new OnDataBackListener() {
            @Override
            public void onStart() {
                iCreditScoreView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {


                DriverCridet driverCridet = parseSerilizable.getParseToObj(data, DriverCridet.class);
                if (vertifyNotNull.isNotNullObj(driverCridet)) {
                    iCreditScoreView.onDataBackSuccessForGetCreditScore(driverCridet);

                    List<RatingDetial> ratingDetials = driverCridet.getRating_details();

                    if(vertifyNotNull.isNotNullListSize(ratingDetials)){

                        HashMap<String,Float> floatHashMap = functionCredit.getRadaDriverData(ratingDetials);

                        iCreditScoreView.onDataBackSuccessForSetDataToRadaView(floatHashMap);

                    }


                } else {
                    iCreditScoreView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    iCreditScoreView.onDataBackFail(code, errorEntity.getError_code());
                } else {
                    iCreditScoreView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
                iCreditScoreView.dismissLoadingDialog();
            }
        });


    }
}
