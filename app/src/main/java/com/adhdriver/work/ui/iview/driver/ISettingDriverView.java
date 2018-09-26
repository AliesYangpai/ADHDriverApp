package com.adhdriver.work.ui.iview.driver;


import com.adhdriver.work.ui.iview.IBaseView;

/**
 * Created by Alie on 2017/11/6.
 * 类描述  司机settingiview
 * 版本
 */

public interface ISettingDriverView extends IBaseView{


    void onDataBackFail(int code, String errorMsg);


    /**
     * 有提现密码
     * @param
     */
    void onDataBackSuccessForWalletInfoHasDepositPass();

    void onDataBackSuccessForWalletInfoNoDepositPass();

    void onDataBackSuccessForClosePush();

    void onDataBackSuccessForGetVisitorToken();


}
