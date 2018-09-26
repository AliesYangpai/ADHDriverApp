package com.adhdriver.work.presenter.driver;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstTag;
import com.adhdriver.work.entity.driver.AppMessage;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IMessageDetailView;
import com.adhdriver.work.verification.VertifyNotNull;

/**
 * Created by Administrator on 2017/11/28.
 * 类描述
 * 版本
 */

public class PresenterDriverMessageDetail extends BasePresenter<IMessageDetailView> {


    private IMessageDetailView iMessageDetailView;
    private VertifyNotNull vertifyNotNull;


    public PresenterDriverMessageDetail(IMessageDetailView iMessageDetailView) {
        this.iMessageDetailView = iMessageDetailView;
        this.vertifyNotNull = new VertifyNotNull();
    }

    /**
     * 设置数据到界面上
     * @param appMessage
     */
    public void doSetDataToUi(AppMessage appMessage) {

        if(vertifyNotNull.isNotNullObj(appMessage)) {

            String messageType = appMessage.getMessage_type();


            switch (messageType) {

                case ConstTag.MessageTag.Activity: //活动公告

                    doSetActivityDataToUi(appMessage);

                    break;


                case ConstTag.MessageTag.CustomerService://客服消息

                    doSetCustomerServiceDataToUi(appMessage);
                    break;

                case ConstTag.MessageTag.Feedback://回复消息

                    doSetFeedbackDataToUi(appMessage);

                    break;

                case ConstTag.MessageTag.System://系统消息

                    boolean pass = appMessage.is_pass();

                    if(pass) {
                        doSetVehicleAccessDataToUi(appMessage);
                    }else {

                        doSetVehicleDenyDataToUi(appMessage);
                    }

                    break;

                case ConstTag.MessageTag.Withdraw://提现消息

                    doSetWithDrawDataToUi(appMessage);
                    break;
            }

        }

    }



    /**
     * 活动消息
     *
     * @param appMessage
     */
    public void doSetActivityDataToUi(AppMessage appMessage) {


        if (vertifyNotNull.isNotNullObj(appMessage)) {

            iMessageDetailView.doSetActivityDataToUi(appMessage);

        }
    }






    /**
     * 客服消息
     *
     * @param appMessage
     */
    public void doSetCustomerServiceDataToUi(AppMessage appMessage) {


        if (vertifyNotNull.isNotNullObj(appMessage)) {

            iMessageDetailView.doSetCustomerServiceDataToUi(appMessage);
        }

    }

    /**
     * 反馈消息
     * @param appMessage
     */
    public void doSetFeedbackDataToUi(AppMessage appMessage) {


        if (vertifyNotNull.isNotNullObj(appMessage)) {

            iMessageDetailView.doSetFeedbackDataToUi(appMessage);
        }

    }


    /**
     * 车辆审核通过消息
     * @param appMessage
     */
    public void doSetVehicleAccessDataToUi(AppMessage appMessage) {


        if (vertifyNotNull.isNotNullObj(appMessage)) {

            iMessageDetailView.doSetVehicleAccessDataToUi(appMessage);
        }

    }

    /**
     * 车辆审核拒绝消息
     * @param appMessage
     */
    public void doSetVehicleDenyDataToUi(AppMessage appMessage) {


        if (vertifyNotNull.isNotNullObj(appMessage)) {

            iMessageDetailView.doSetVehicleDenyDataToUi(appMessage);
        }

    }

    /**
     * 提现消息
     * @param appMessage
     */
    public void doSetWithDrawDataToUi(AppMessage appMessage) {


        if (vertifyNotNull.isNotNullObj(appMessage)) {

            iMessageDetailView.doSetWithDrawDataToUi(appMessage);
        }

    }
}
