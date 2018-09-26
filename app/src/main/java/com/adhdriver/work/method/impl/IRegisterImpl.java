package com.adhdriver.work.method.impl;

import com.adhdriver.work.http.CallServer;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.http.HttpSingleResponseListener;
import com.adhdriver.work.http.RequestPacking;
import com.adhdriver.work.http.requestparam.RegisterParam;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IRegister;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by Administrator on 2017/12/11.
 * 类描述
 * 版本
 */

public class IRegisterImpl implements IRegister {

    private RegisterParam registerParam;


    public IRegisterImpl() {
        registerParam = new RegisterParam();
    }

    @Override
    public void doGetRegStatue(String url, String accessToken, final OnDataBackListener onDataBackListener) {

        Request<String> request = RequestPacking.getInstance().getRequestParamSetTokenForJson(
                url,
                RequestMethod.GET,
                accessToken, null);

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
    public void doCompleteUser(String url,
                               String accessToken,
                               String userName,
                               String user_identity_card_no,
                               String pathFront,
                               String pathBack, String inviteCode, final OnDataBackListener onDataBackListener) {

        String jsonParam = registerParam.getCompleteUserParam(userName,
                user_identity_card_no,
                pathFront,
                pathBack,
                inviteCode);


        Request<String> request = RequestPacking.getInstance().getRequestParamSetTokenForJson(url, RequestMethod.POST, accessToken, jsonParam);


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
    public void doGetPhoneCode(String url, String phone, String options, final OnDataBackListener onDataBackListener) {
        String param = registerParam.getConfrimCodeParam(phone, options);

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

        String jsonString = registerParam.getConfirmVertifyCodeParam(phone, pass_code);

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

    @Override
    public void doRegister(String url, String phone, String pass, String code, String defaultUserName, final OnDataBackListener onDataBackListener) {


        String jsonString = registerParam.getRegisteParams(phone, pass,code,defaultUserName);

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

    @Override
    public void doRegGetToken(String url, String user_phone, String user_password, final OnDataBackListener onDataBackListener) {

        String jsonParam = registerParam.getRegTokenParam(user_phone, user_password);
        Request<String> request = RequestPacking.getInstance().getRequestParamForJsonNoToken(
                url,
                RequestMethod.POST,
                jsonParam);



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
