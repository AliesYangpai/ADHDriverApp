package com.adhdriver.work.ui.iview;

import com.adhdriver.work.entity.driver.token.TokenInfo;

/**
 * Created by Alie on 2017/11/16.
 * 类描述
 * 版本
 */

public interface IWelcomView extends IBaseView{


    void doGoToMain();

    void onDataBackFail(int code, String errorMsg);

    /**
     * 游客登陆成功返回
     * @param tokenInfo
     */
    void onDataBackSuccessForVisitorLogon(TokenInfo tokenInfo);

}
