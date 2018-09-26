package com.adhdriver.work.ui.iview.driver;

import com.adhdriver.work.ui.iview.IBaseView;

/**
 * Created by Administrator on 2017/11/24.
 * 类描述 忘记提现密码的获取验证码iview
 * 版本
 */

public interface IDepositPassForgetToGetCodeView extends IBaseView{



    void onDataBackFail(int code, String errorMsg);


    /**
     * 获取用户电话号码
     * @param phone
     */
    void doGetUserPhoneToUi(String phone);


    /**
     * 获取验证码成功的返回
     */
    void onDataBackSuccessForGetCode();


    /**
     * 验证验证码成功的返回
     */
    void onDataBackSuccessForConfirmDepositVertifyCode();


    /**
     * 验证码是null
     */
    void doVertifyErrorNullVertifyCode();


    /**
     * 未补全验证码
     */
    void doVertifyErrorUnCompletVertifyCode();






}
