package com.adhdriver.work.ui.iview.driver;

import com.adhdriver.work.entity.driver.wallet.Wallet;
import com.adhdriver.work.ui.iview.IBaseView;

/**
 * Created by Administrator on 2018/1/11.
 * 类描述 提现界面的view
 * 版本
 */

public interface IDepositWithDrawView extends IBaseView {


    void onDataBackFail(int code, String errorMsg);

    void doVertifyErrorForCashNull();

    void doVertifyErrorForCash0();

    void doVertifyErrorForCashLessThanZeroPointOne();



    void doVertifyErrorForCashLessThanOne();
    /**
     * 金额验证完成
     */
    void doChcekAmountSuccess(String amoutContent);


    void onDataBackSuccessForDepositWithDraw();


    void showDataOnUi(Wallet wallet);
}
