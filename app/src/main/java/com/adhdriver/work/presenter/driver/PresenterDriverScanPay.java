package com.adhdriver.work.presenter.driver;

import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IScanPayView;

/**
 * Created by Administrator on 2018/1/26.
 * 类描述
 * 版本
 */

public class PresenterDriverScanPay extends BasePresenter<IScanPayView> {

    private IScanPayView iScanPayView;

    public PresenterDriverScanPay(IScanPayView iScanPayView) {
        this.iScanPayView = iScanPayView;
    }
}
