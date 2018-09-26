package com.adhdriver.work.method.impl;

import com.adhdriver.work.entity.driver.thirdauth.AuthParam;
import com.adhdriver.work.http.CallServer;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.http.HttpSingleResponseListener;
import com.adhdriver.work.http.RequestPacking;
import com.adhdriver.work.http.requestparam.ThirdAuthParam;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IThirdAuthAbout;
import com.google.gson.JsonElement;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by Administrator on 2017/11/29.
 * 类描述
 * 版本
 */

public class IThirdAuthAboutImpl implements IThirdAuthAbout {


    private ThirdAuthParam thirdAuthParam;


    public IThirdAuthAboutImpl() {
        this.thirdAuthParam = new ThirdAuthParam();
    }

    @Override
    public void doGetBinds(String url, final OnDataBackListener onDataBackListener) {


        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
                url,
                RequestMethod.GET,
                null);

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
    public void doValidAuthForQQ(String url, String code, String auth_type, String unionid, String openid, String access_token, final OnDataBackListener onDataBackListener) {


        String jsonString = thirdAuthParam.getAuthValidParamForQQ(code, auth_type, unionid, openid, access_token);


        Request<String> request = RequestPacking.getInstance().getRequestParamForJsonNoToken(url, RequestMethod.POST, jsonString);

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
    public void doValidAuthForWx(String url, String code, String auth_type, final OnDataBackListener onDataBackListener) {


        String jsonString = thirdAuthParam.getAuthValidParamForWx(code, auth_type);

        Request<String> request = RequestPacking.getInstance().getRequestParamForJsonNoToken(url, RequestMethod.POST, jsonString);

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
    public void doBindForWx(String url, String auth_type, AuthParam authParam, final OnDataBackListener onDataBackListener) {


        String jsonString = thirdAuthParam.getAuthBindParamForWx(auth_type, authParam);

        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url, RequestMethod.POST, jsonString);

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
    public void doBindForQQ(String url, String auth_type, AuthParam authParam, final OnDataBackListener onDataBackListener) {


        String jsonString = thirdAuthParam.getAuthBindParamForQQ(auth_type, authParam);

        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url, RequestMethod.POST, jsonString);

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
