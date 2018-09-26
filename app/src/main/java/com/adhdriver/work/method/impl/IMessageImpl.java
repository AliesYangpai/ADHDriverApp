package com.adhdriver.work.method.impl;

import com.adhdriver.work.R;
import com.adhdriver.work.http.CallServer;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.http.HttpSingleResponseListener;
import com.adhdriver.work.http.RequestPacking;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IMessage;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by Administrator on 2017/11/27.
 * 类描述
 * 版本
 */

public class IMessageImpl implements IMessage {



    @Override
    public void doGetMessages(String url, int size, int index, final OnDataBackListener onDataBackListener) {

        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url, RequestMethod.GET,null);
        request.add("size",size);
        request.add("index",index);

        CallServer.getInstance().add(HttpConst.HTTP_WHAT, request, new HttpSingleResponseListener<String>() {
            @Override
            protected void OnHttpStart(int what) {
                onDataBackListener.onStart();
            }

            @Override
            protected void OnHttpSuccessed(int what, Response<String> response) {
                onDataBackListener.onSuccess(response.get());
            }

            @Override
            protected void onHttpFailed(int what, Response<String> response) {


                onDataBackListener.onFail(response.responseCode(),response.get());
            }

            @Override
            protected void OnHttpFinish(int what) {

                onDataBackListener.onFinish();

            }
        });

    }

    @Override
    public void doGetMessagesInFresh(String url, int size, int index, final OnDataBackListener onDataBackListener) {


        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url, RequestMethod.GET,null);

        request.add("size",size);
        request.add("index",index);

        CallServer.getInstance().add(HttpConst.HTTP_WHAT, request, new HttpSingleResponseListener<String>() {
            @Override
            protected void OnHttpStart(int what) {
                onDataBackListener.onStart();
            }

            @Override
            protected void OnHttpSuccessed(int what, Response<String> response) {
                onDataBackListener.onSuccess(response.get());
            }

            @Override
            protected void onHttpFailed(int what, Response<String> response) {


                onDataBackListener.onFail(response.responseCode(),response.get());
            }

            @Override
            protected void OnHttpFinish(int what) {

                onDataBackListener.onFinish();

            }
        });

    }

    @Override
    public void doGetMessagesInLoadMore(String url, int size, int index, final OnDataBackListener onDataBackListener) {

        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url, RequestMethod.GET,null);

        request.add("size",size);
        request.add("index",index);

        CallServer.getInstance().add(HttpConst.HTTP_WHAT, request, new HttpSingleResponseListener<String>() {
            @Override
            protected void OnHttpStart(int what) {
                onDataBackListener.onStart();
            }

            @Override
            protected void OnHttpSuccessed(int what, Response<String> response) {
                onDataBackListener.onSuccess(response.get());
            }

            @Override
            protected void onHttpFailed(int what, Response<String> response) {


                onDataBackListener.onFail(response.responseCode(),response.get());
            }

            @Override
            protected void OnHttpFinish(int what) {

                onDataBackListener.onFinish();

            }
        });


    }
}
