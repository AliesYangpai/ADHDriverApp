package com.adhdriver.work.ui.iview.driver;

import com.adhdriver.work.ui.iview.IBaseView;

/**
 * Created by Administrator on 2018/1/11.
 * 类描述  设置提现密码的ivew
 * 版本
 */

public interface ISettingDepositPassView extends IBaseView{


    void onDataBackFail(int code, String errorMsg);

    /**
     * 请输入提现密码
     */
    void doVertifyErrorForPassNull();

    /**
     * 密码小于 6为
     */
    void doVertifyErrorForPassLessThan6();


    /**
     * 请在输入一下
     */
    void doVertifyErrorForPassAgainNull();

    /**
     * 密码不一致
     */
    void doVertifyErrorForPassNotSame();


    /**
     * 密码设置成功
     */
    void onDataBackSuccessForSettingDepositPass();

}
