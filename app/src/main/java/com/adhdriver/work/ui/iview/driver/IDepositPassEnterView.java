package com.adhdriver.work.ui.iview.driver;

import com.adhdriver.work.entity.driver.pay.aliAbout.AliAuthInfo;
import com.adhdriver.work.entity.driver.wallet.WxAuthInfo;
import com.adhdriver.work.ui.iview.IBaseView;

/**
 * Created by Administrator on 2018/1/11.
 * 类描述  输入验证提现密码的ivew
 * 版本
 */

public interface IDepositPassEnterView extends IBaseView{


    void onDataBackFail(int code, String errorMsg);

    /**
     * 请输入提现密码
     */
    void doVertifyErrorForPassNull();


    /**
     * 重新绑定并且获取授权信息成功
     */
    void onDataBackSuccessForRebindAndGetAliInfo();


    void onDataBackSuccessForGetWxInfo(WxAuthInfo wxAuthInfo);



    void onDataBackSuccessForRebindAndGetAliInfo( AliAuthInfo aliAuthInfo );

}
