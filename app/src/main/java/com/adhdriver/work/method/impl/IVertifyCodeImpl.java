package com.adhdriver.work.method.impl;

import com.adhdriver.work.http.CallServer;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.http.HttpSingleResponseListener;
import com.adhdriver.work.http.RequestPacking;
import com.adhdriver.work.http.requestparam.VertifyCodeParam;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IVertifyCode;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by Administrator on 2018/1/26.
 * 类描述
 * 版本
 */

public class IVertifyCodeImpl implements IVertifyCode {


    private VertifyCodeParam vertifyCodeParam;

    public IVertifyCodeImpl() {
        vertifyCodeParam = new VertifyCodeParam();
    }

    @Override
    public void doSendVertifyCode(String url, String phone, String options, final OnDataBackListener onDataBackListener) {


        String param = vertifyCodeParam.getSendVertifyCodeParam(phone, options);

        Request<String> request = RequestPacking.getInstance().getRequestParamForJsonNoToken(url, RequestMethod.POST, param);

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

    @Override
    public void doConfirmVertifyCode(String url, String phone, String pass_code, final OnDataBackListener onDataBackListener) {


        String jsonString = vertifyCodeParam.getConfirmVertifyCodeParam(phone, pass_code);

        Request<String> request = RequestPacking.getInstance().getRequestParamForJsonNoToken(
                url,
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
