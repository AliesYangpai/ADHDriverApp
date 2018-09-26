package com.adhdriver.work.ui.iview.driver;

import com.adhdriver.work.ui.iview.IBaseView;

/**
 * Created by Administrator on 2017/11/24.
 * 类描述  更改提现密码
 * 版本
 */

public interface IDepositPassChangeView extends IBaseView{

    void onDataBackFail(int code, String errorMsg);



    void onDataBackSuccessForChangeDepositPass();


    void doVertifyErrorNullOldPass();


    void doVertifyErrorNullNewPass();


    void doVertifyErrorUnLegalPass();

    void doVertifyErrorNullNewPassAgain();

    void doVertifyErrorNewPassNotSameToNewPassAgain();



}
