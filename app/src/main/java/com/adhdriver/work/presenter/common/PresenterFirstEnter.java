package com.adhdriver.work.presenter.common;

import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.method.IFirstEnter;
import com.adhdriver.work.method.IVisitorAbout;
import com.adhdriver.work.method.impl.IFirstEnterImpl;
import com.adhdriver.work.method.impl.IVisitorAboutImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.IFirstEnterView;
import com.adhdriver.work.utils.SpUtil;

/**
 * Created by Alie on 2017/11/16.
 * 类描述
 * 版本
 */

public class PresenterFirstEnter extends BasePresenter<IFirstEnterView> {

    private IFirstEnterView iFirstEnterView;
    private IFirstEnter iFirstEnter;


    public PresenterFirstEnter(IFirstEnterView iFirstEnterView) {
        this.iFirstEnterView = iFirstEnterView;
//        this.iFirstEnter = new IFirstEnterImpl();
    }


    /**
     * 权限判断
     */
    public void doPermissionCheck() {

        iFirstEnterView.doPermissionCheck();

    }

    public void goToWelCome() {

        iFirstEnterView.doGoToWelcome();
    }


    public void goToMain() {

        iFirstEnterView.doGoToMain();

    }


    public void doGoToMainOrWelCome() {


        boolean loadOrNot = SpUtil.getInstance().getBoolenValue(ConstSp.SP_KEY_LOAD_OR_NOT, false);


        if(loadOrNot) {

            iFirstEnterView.doGoToMain();

        }else {
            iFirstEnterView.doGoToWelcome();

        }

    }

    public void doShowPermissionDialog() {

        iFirstEnterView.doShowPermissionDialog();

    }




}
