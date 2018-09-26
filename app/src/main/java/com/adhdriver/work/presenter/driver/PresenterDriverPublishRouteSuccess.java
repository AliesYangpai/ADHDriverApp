package com.adhdriver.work.presenter.driver;

import com.adhdriver.work.method.IPublishRoute;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IPublishRouteSuccessView;

/**
 * Created by Alie on 2017/12/3.
 * 类描述
 * 版本
 */

public class PresenterDriverPublishRouteSuccess extends BasePresenter<IPublishRouteSuccessView> {

    private IPublishRouteSuccessView iPublishRouteSuccessView;
    private IPublishRoute iPublishRoute;


    public PresenterDriverPublishRouteSuccess(IPublishRouteSuccessView iPublishRouteSuccessView) {
        this.iPublishRouteSuccessView = iPublishRouteSuccessView;
    }


    
}
