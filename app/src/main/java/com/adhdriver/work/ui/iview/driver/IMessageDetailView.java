package com.adhdriver.work.ui.iview.driver;

import com.adhdriver.work.entity.driver.AppMessage;

/**
 * Created by Administrator on 2017/11/28.
 * 类描述 消息详情界面
 * 版本
 */
public interface IMessageDetailView {


    void doSetActivityDataToUi(AppMessage appMessage);

    void doSetCustomerServiceDataToUi(AppMessage appMessage);

    void doSetFeedbackDataToUi(AppMessage appMessage);

    void doSetVehicleAccessDataToUi(AppMessage appMessage);

    void doSetVehicleDenyDataToUi(AppMessage appMessage);

    void doSetWithDrawDataToUi(AppMessage appMessage);
}
