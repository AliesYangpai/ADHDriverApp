package com.adhdriver.work.method.impl;

import com.adhdriver.work.http.CallServer;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.http.HttpSingleResponseListener;
import com.adhdriver.work.http.RequestPacking;
import com.adhdriver.work.http.requestparam.FeedBackParam;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IFeedBack;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by Administrator on 2017/11/24.
 * 类描述
 * 版本
 */

public class IFeedBackImpl implements IFeedBack {

    private FeedBackParam feedBackParam;

    public IFeedBackImpl() {
        this.feedBackParam = new FeedBackParam();
    }

    @Override
    public void doFeedBack(String url, String source, String content, String phone, String os_version, String model, String os_type, final OnDataBackListener onDataBackListener) {


        String jsonString = feedBackParam.getFeedBackParam(source, content, phone, os_version, model, os_type);

        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
                HttpConst.URL.FEEDBACKS,
                RequestMethod.POST,
                jsonString);


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
                onDataBackListener.onFail(response.responseCode(), response.get());
            }

            @Override
            protected void OnHttpFinish(int what) {

                onDataBackListener.onFinish();

            }
        });


    }
}
