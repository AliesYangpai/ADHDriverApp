package com.adhdriver.work.presenter.driver;

import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.driver.AppMessage;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IMessage;
import com.adhdriver.work.method.impl.IMessageImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IMessageView;
import com.adhdriver.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created by Administrator on 2017/11/27.
 * 类描述
 * 版本
 */

public class PresenterDriverMessage extends BasePresenter<IMessageView> {

    private IMessage iMessage;
    private IMessageView iMessageView;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;

    public PresenterDriverMessage(IMessageView iMessageView) {
        this.iMessageView = iMessageView;
        this.iMessage = new IMessageImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
    }


    /**
     * 获取消息列表
     *
     * @param url
     * @param size
     * @param index
     */
    public void doGetMessages(String url, int size, int index) {

        iMessage.doGetMessages(url, size, index, new OnDataBackListener() {
            @Override
            public void onStart() {
                iMessageView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {
                List<AppMessage> list = parseSerilizable.getParseToList(data, AppMessage.class);

                if (vertifyNotNull.isNotNullListSize(list)) {

                    iMessageView.onDataBackSuccessForMessages(list);

                }
            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if (null != errorEntity) {
                    iMessageView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iMessageView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);

                }

            }

            @Override
            public void onFinish() {
                iMessageView.dismissLoadingDialog();
            }
        });

    }


    /**
     * 获取消息列表，下拉刷新
     *
     * @param url
     * @param size
     * @param index
     */
    public void doGetMessagesInFresh(String url, int size, int index) {

        iMessage.doGetMessagesInFresh(url, size, index, new OnDataBackListener() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(String data) {
                List<AppMessage> list = parseSerilizable.getParseToList(data, AppMessage.class);
                iMessageView.onDataBackSuccessForMessagesInFresh(list);
            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if (null != errorEntity) {
                    iMessageView.onDataBackFailInFresh(code, errorEntity.getError_message());
                } else {
                    iMessageView.onDataBackFailInFresh(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);

                }

            }

            @Override
            public void onFinish() {
            }
        });


    }

    /**
     * 获取消息列表，上拉加载
     *
     * @param url
     * @param size
     * @param index
     */
    public void doGetMessagesInLoadMore(String url, int size, int index) {




        iMessage.doGetMessagesInLoadMore(url, size, index, new OnDataBackListener() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(String data) {
                List<AppMessage> list = parseSerilizable.getParseToList(data, AppMessage.class);

                iMessageView.onDataBackSuccessForMessagesInLoadMore(list);


            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if (null != errorEntity) {
                    iMessageView.onDataBackFailInLoadMore(code, errorEntity.getError_message());
                } else {
                    iMessageView.onDataBackFailInLoadMore(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);

                }

            }

            @Override
            public void onFinish() {
            }
        });


    }
}
