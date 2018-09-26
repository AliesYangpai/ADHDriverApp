package com.adhdriver.work.ui.iview.driver;

import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.entity.driver.token.TokenInfo;
import com.adhdriver.work.ui.iview.IBaseView;

/**
 * Created by Administrator on 2018/1/17.
 * 类描述  司机注册的电话号码 和密码的界面
 * 版本
 */

public interface IRegDriverPhonePassView extends IBaseView{

    void onDataBackFail(int code, String errorMsg);

    void doVertifyErrorForNullPhone();

    void doVertifyErrorForUnlegalPhone();


    void doVertifyErrorForNullPhoneCode();

    void doVertifyErrorForNullPass();

    void doVertifyErrorForUnlegalPass();

    void onDataBackSuccessForGetPhoneCode();

    void onDataBackSuccessForVeryifyPhoneCode(String phone,String pass_code,String pass);

    void onDataBackSuccessForReg(String phone,String pass);

    void onDataBackSuccessForGetToken(TokenInfo tokenInfo);

}
