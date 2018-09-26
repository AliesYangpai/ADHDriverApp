package com.adhdriver.work.ui.iview.driver;

import com.adhdriver.work.entity.driver.wallet.TodayIncome;
import com.adhdriver.work.ui.iview.IBaseView;

/**
 * Created by Administrator on 2017/12/27.
 * 类描述  同城未接单业务
 * 版本
 */

public interface IFgOrderNotTakeSameCityView extends IBaseView{


    void onDataBackFail(int code, String errorMsg);




     void dismissFreshLoading();


    /**
     * 获取当日的收益
     */
    void onDataBackSuccessForDailyInCome(TodayIncome todayIncome);


    /**
     * 获取当日的收益
     */
    void onDataBackSuccessForDailyInComeInFresh(TodayIncome todayIncome);

}
