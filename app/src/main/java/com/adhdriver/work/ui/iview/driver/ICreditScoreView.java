package com.adhdriver.work.ui.iview.driver;

import com.adhdriver.work.entity.User;
import com.adhdriver.work.entity.driver.credit.DriverCridet;
import com.adhdriver.work.ui.iview.IBaseView;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/1/8.
 * 类描述  获取信用分
 * 版本
 */

public interface ICreditScoreView extends IBaseView {


    void onDataBackFail(int code, String errorMsg);

    void onDataBackSuccessDbForUser(User user);

    void onDataBackSuccessForGetCreditScore(DriverCridet driverCridet);

    void onDataBackSuccessForSetDataToRadaView(HashMap<String, Float> valueHash);

}
