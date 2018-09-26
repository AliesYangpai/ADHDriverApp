package com.adhdriver.work.ui.iview.driver;

import com.adhdriver.work.entity.driver.thirdauth.AuthState;
import com.adhdriver.work.entity.driver.thirdauth.ValidAuthInApp;
import com.adhdriver.work.ui.iview.IBaseView;

import java.util.List;

/**
 * Created by Administrator on 2017/11/29.
 * 类描述  授权界面的IView
 * 版本
 */

public interface IThirdAuthView extends IBaseView{

    void onDataBackFail(int code, String errorMsg);

    void onDataBackSuccessForQQBind();

    void onDataBackSuccessForWxBind();

    void onDataBackSuccessForAllBind();

    void onDataBackSuccessForNoBind();


    void onDataBackSuccessForValidAuthQQ(ValidAuthInApp validAuthInApp);

    void onDataBackSuccessForValidAuthWx(ValidAuthInApp validAuthInApp);

    void onDataBackSuccessForBindAuthQQ(String back);

    void onDataBackSuccessForBindAuthWx(String back);

    void doShowQQHasBindAlert();

    void doShowWxHasBindAlert();

    void doGetQQAuthorization(); //获取QQopenId


    void doGetWxAuthorization(); //获取微信授权
}
