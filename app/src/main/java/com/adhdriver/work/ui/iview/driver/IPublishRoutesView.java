package com.adhdriver.work.ui.iview.driver;

import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.entity.driver.temp.PublishRoute;
import com.adhdriver.work.ui.iview.IBaseView;

import java.util.List;

/**
 * Created by Administrator on 2017/12/7.
 * 类描述  顺风车发布列表的接口view
 * 版本
 */

public interface IPublishRoutesView extends IBaseView{

    void onDataBackFail(int code, String errorMsg);

    void onDataBackFailInFresh(int code, String errorMsg);

    void onDataBackFailInLoadMore(int code, String errorMsg);



    void onDataBackSuccessForPublishRoutes(List<PublishRoute> list);

    void onDataBackSuccessForPublishRoutesInFresh(List<PublishRoute> list);

    void onDataBackSuccessForPublishRoutesInLoadMore(List<PublishRoute> list);

    void onDataBackSuccessForCancelThisRoute();

    void doShowCancelThisRouteAlertDialog(String hitchhike_on);
}
