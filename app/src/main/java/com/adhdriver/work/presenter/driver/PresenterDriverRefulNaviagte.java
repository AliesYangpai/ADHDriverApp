package com.adhdriver.work.presenter.driver;

import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IRefuelNavigateView;

/**
 * Created by Administrator on 2018/1/25.
 * 类描述
 * 版本
 */

public class PresenterDriverRefulNaviagte extends BasePresenter<IRefuelNavigateView> {

    private IRefuelNavigateView iRefuelNavigateView;

    public PresenterDriverRefulNaviagte(IRefuelNavigateView iRefuelNavigateView) {
        this.iRefuelNavigateView = iRefuelNavigateView;
    }

    
}
