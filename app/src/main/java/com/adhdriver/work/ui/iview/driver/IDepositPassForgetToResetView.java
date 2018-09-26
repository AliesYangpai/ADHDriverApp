package com.adhdriver.work.ui.iview.driver;

import com.adhdriver.work.ui.iview.IBaseView;

/**
 * Created by Administrator on 2017/11/24.
 * 类描述  重置提现密码的Iview
 * 版本
 */

public interface IDepositPassForgetToResetView extends IBaseView {



    void onDataBackFail(int code, String errorMsg);




    /**
     * 验证验证码成功的返回
     */
    void onDataBackSuccessForResetDepositPass();


    /**
     * 输入身份证号码后四位
     */
    void doVertifyErrorNullIDLast4();


    void doVertifyErrorIDCompleteLast4();


    void doVertifyErrorNullDepositPass();

    void doVertifyErrorUnLegalDepositPass();

    void doVertifyErrorEnterDepositPassAgain();

    void doVertifyErrorNewPassNotSameToNewPassAgain();










}
