package com.adhdriver.work.method;

import com.adhdriver.work.listener.OnDataBackListener;

/**
 * Created by Administrator on 2017/11/27.
 * 类描述  消息相关的接口
 * 版本
 */

public interface IMessage {


    void doGetMessages(String url, int size, int index, OnDataBackListener onDataBackListener);


    void doGetMessagesInFresh(String url, int size, int index, OnDataBackListener onDataBackListener);


    void doGetMessagesInLoadMore(String url, int size, int index, OnDataBackListener onDataBackListener);

}
