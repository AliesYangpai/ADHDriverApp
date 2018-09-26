package com.adhdriver.work.presenter.driver;

import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IPushAlertView;

/**
 * Created by Administrator on 2018/1/3.
 * 类描述
 * 版本
 */

public class PresenterDriverPushAlert extends BasePresenter<IPushAlertView>{

    private IPushAlertView iPushAlertView;




    public PresenterDriverPushAlert(IPushAlertView iPushAlertView) {
        this.iPushAlertView = iPushAlertView;
    }


    public void doPlayAlertVoice() {

        iPushAlertView.doPlayAlertVoice();
    }

    public void doShowAlertDialog() {


        iPushAlertView.doShowAlertDialog();
    }


}
