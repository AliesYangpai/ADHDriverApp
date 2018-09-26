package com.adhdriver.work.presenter.common;

import com.adhdriver.work.method.IUserAgreement;
import com.adhdriver.work.method.impl.IUserAgreementImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.IUserAgreementView;

/**
 * Created by Alie on 2017/11/17.
 * 类描述  用户协议的present
 * 版本
 */

public class PresenterUserAgreement extends BasePresenter<IUserAgreementView> {

    private IUserAgreementView iUserAgreementView;
    private IUserAgreement iUserAgreement;


    public PresenterUserAgreement(IUserAgreementView iUserAgreementView) {
        this.iUserAgreementView = iUserAgreementView;
        this.iUserAgreement = new IUserAgreementImpl();
    }


    public void doLoadAgreementUrl(String url) {

        iUserAgreementView.doLoadUserAgreementUrl(url);

    }
}
