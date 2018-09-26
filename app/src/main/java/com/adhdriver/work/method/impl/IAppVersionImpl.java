package com.adhdriver.work.method.impl;

import com.adhdriver.work.constant.ConstParams;
import com.adhdriver.work.constant.ConstSign;
import com.adhdriver.work.http.CallServer;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.http.HttpSingleResponseListener;
import com.adhdriver.work.http.RequestPacking;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IAppVersion;
import com.adhdriver.work.utils.VersionUtil;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by Administrator on 2017/11/23.
 * 类描述
 * 版本
 */

public class IAppVersionImpl implements IAppVersion {


    @Override
    public void doGetAppInfo(String url, String os_type, String os_version, String model, String device_id, final OnDataBackListener onDataBackListener) {


        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url,
                RequestMethod.GET,
                null);

        request.add("os_type", os_type);
        request.add("os_version", os_version);
        request.add("model", model);
        request.add("device_id", device_id);


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
