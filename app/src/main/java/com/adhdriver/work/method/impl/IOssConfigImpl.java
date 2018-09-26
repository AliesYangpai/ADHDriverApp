package com.adhdriver.work.method.impl;

import com.adhdriver.work.http.CallServer;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.http.HttpSingleResponseListener;
import com.adhdriver.work.http.RequestPacking;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IOssConfig;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by Administrator on 2017/12/11.
 * 类描述
 * 版本
 */

public class IOssConfigImpl implements IOssConfig {


    @Override
    public void doGetOssConfigInfo(String url, String token, final OnDataBackListener onDataBackListener) {

        Request<String> request = RequestPacking.getInstance().getRequestParamSetTokenForJson(
                url,
                RequestMethod.GET,
                token, null);

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
