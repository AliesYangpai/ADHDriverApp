package com.adhdriver.work.method.impl;

import com.adhdriver.work.http.CallServer;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.http.HttpSingleResponseListener;
import com.adhdriver.work.http.RequestPacking;
import com.adhdriver.work.http.requestparam.ThirdPayParam;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IThirdPay;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by Administrator on 2018/1/10.
 * 类描述
 * 版本
 */

public class IThirdPayImpl implements IThirdPay {


    private ThirdPayParam thirdPayParam;

    public IThirdPayImpl() {
        this.thirdPayParam = new ThirdPayParam();
    }

    @Override
    public void doAliPreAppAuthLogin(String url,
                                     String channel_id,
                                     String device_id,
                                     String os_type,
                                     String device_type,
                                     JsonObject parameters, final OnDataBackListener onDataBackListener) {




        String jsonParam = thirdPayParam.getAliPreAppAuthLoginParam(channel_id, device_id, os_type, device_type, parameters);


        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url, RequestMethod.POST,jsonParam);

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
    public void doAliGetAuthLoginInfo(String url,
                                      String channel_id,
                                      String pay_password,
                                      String device_id,
                                      String os_type,
                                      String device_type,
                                      String auth_code,
                                      JsonObject parameters, final OnDataBackListener onDataBackListener) {




        String jsonParam = thirdPayParam.doAliGetAuthLoginInfoParam(
                channel_id,
                pay_password,
                device_id,
                os_type,
                device_type,
                auth_code,
                parameters);


        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url, RequestMethod.POST,jsonParam);

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
    public void doWxGetAuthLoginInfo(String url, String channel_id, String pay_password, String device_id, String os_type, String device_type, String auth_code, JsonObject parameters, final OnDataBackListener onDataBackListener) {



        parameters.addProperty("auth_code",auth_code);

        String jsonParam = thirdPayParam.doWxGetAuthLoginInfoParam(
                channel_id,
                pay_password,
                device_id,
                os_type,
                device_type,
                auth_code,
                parameters);


        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url, RequestMethod.POST,jsonParam);

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
    public void doAliCheckAliPayResultInfo(String url,
                                           String resultInfo,
                                           String app_type,
                                           String os_type,
                                           String payer, final OnDataBackListener onDataBackListener) {



        String jsonParam = thirdPayParam.getAliCheckAliPayResult(
                resultInfo,
                app_type,
                os_type,
                payer);


        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url, RequestMethod.POST,jsonParam);

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
    public void doWxCheckWxPayResultInfo(String url,
                                         String app_type,
                                         String os_type,
                                         String payment_record_id,
                                         String payer, final OnDataBackListener onDataBackListener) {


        String jsonParam = thirdPayParam.getWxCheckWxPayResult(
                app_type,
                os_type,
                payment_record_id,
                payer);


        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url, RequestMethod.POST,jsonParam);

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
