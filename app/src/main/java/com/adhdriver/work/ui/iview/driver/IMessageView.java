package com.adhdriver.work.ui.iview.driver;

import com.adhdriver.work.entity.driver.AppMessage;
import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.ui.iview.IBaseView;

import java.util.List;

/**
 * Created by Administrator on 2017/11/27.
 * 类描述   massage的iView
 * 版本
 */

public interface IMessageView extends IBaseView {

    void onDataBackFail(int code, String errorMsg);

    void onDataBackFailInFresh(int code, String errorMsg);

    void onDataBackFailInLoadMore(int code, String errorMsg);



    void onDataBackSuccessForMessages(List<AppMessage> list);

    void onDataBackSuccessForMessagesInFresh(List<AppMessage> list);

    void onDataBackSuccessForMessagesInLoadMore(List<AppMessage> list);

}
