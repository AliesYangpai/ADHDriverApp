package com.adhdriver.work.ui.iview.driver;

import com.adhdriver.work.entity.AppUpdate;
import com.adhdriver.work.ui.iview.IBaseView;

/**
 * Created by Administrator on 2017/11/24.
 * 类描述  更改密码的iview
 * 版本
 */

public interface IChangeLoginPassView extends IBaseView{


    void onDataBackFail(int code, String errorMsg);



    void doVertifyErrorNullOldPass();


    void doVertifyErrorNullNewPass();


    void doVertifyErrorUnLegalPass();

    void doVertifyErrorNullNewPassAgain();

    void doVertifyErrorNewPassNotSameToNewPassAgain();

    void onDataBackSuccessForChangeLoginPass();



    void onDataBackSuccessForClosePush();

    void onDataBackSuccessForGetVisitorToken();





}
