package com.adhdriver.work.method.impl;

import com.adhdriver.work.constant.ConstParams;
import com.adhdriver.work.constant.ConstSign;
import com.adhdriver.work.http.CallServer;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.http.HttpSingleResponseListener;
import com.adhdriver.work.http.RequestPacking;
import com.adhdriver.work.http.requestparam.DepositParam;
import com.adhdriver.work.http.requestparam.FeedBackParam;
import com.adhdriver.work.http.requestparam.LoginParam;
import com.adhdriver.work.http.requestparam.OrderParam;
import com.adhdriver.work.http.requestparam.PushParam;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IDriverAbout;
import com.adhdriver.work.utils.VersionUtil;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by Alie on 2017/11/6.
 * 类描述   主页的实现类
 * 版本
 */

public class IDriverAboutImpl implements IDriverAbout {


    private PushParam pushParam;
    private LoginParam loginParam;
    private DepositParam depositParam;

    public IDriverAboutImpl() {

        this.pushParam = new PushParam();
        this.loginParam = new LoginParam();
        this.depositParam = new DepositParam();
    }


    /**
     * 获取用户信息
     *
     * @param onDataBackListener
     */
    @Override
    public void doGetUserInfo(String url, final OnDataBackListener onDataBackListener) {

        Request<String> request = RequestPacking
                .getInstance()
                .getRequestParamForJson(url, RequestMethod.GET, null);

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
    public void doGetUserInfoInFresh(String url, final OnDataBackListener onDataBackListener) {


        Request<String> request = RequestPacking
                .getInstance()
                .getRequestParamForJson(url, RequestMethod.GET, null);

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


    /**
     * 打开推送
     *
     * @param os_type
     * @param os_version
     * @param model
     * @param device_id
     * @param push_type
     * @param push_token
     * @param quiet_mode
     * @param onDataBackListener
     */

    @Override
    public void doOpenPush(String url,
                           String os_type,
                           String os_version,
                           String model,
                           String device_id,
                           String push_type,
                           String push_token,
                           boolean quiet_mode, final OnDataBackListener onDataBackListener) {


        String param = pushParam.
                setPushOpenParams(os_type,
                        os_version,
                        model,
                        device_id,
                        push_type,
                        push_token,
                        quiet_mode);


        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
                url,
                RequestMethod.POST,
                param
        );


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


    /**
     * 关闭推送
     *
     * @param os_type
     * @param os_version
     * @param model
     * @param device_id
     * @param push_type
     * @param push_token
     * @param quiet_mode
     * @param onDataBackListener
     */

    @Override
    public void doClosePush(String url,
                            String os_type,
                            String os_version,
                            String model,
                            String device_id,
                            String push_type,
                            String push_token,
                            boolean quiet_mode, final OnDataBackListener onDataBackListener) {


        String param = pushParam.
                setPushOpenParams(os_type,
                        os_version,
                        model,
                        device_id,
                        push_type,
                        push_token,
                        quiet_mode);

        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
                url,
                RequestMethod.POST,
                param);


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


    /**
     * 获取钱包信息
     *
     * @param url
     * @param onDataBackListener
     */
    @Override
    public void doGetMyWalletInfo(String url, final OnDataBackListener onDataBackListener) {


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


    /**
     * 修改登陆密码
     *
     * @param url
     * @param password
     * @param new_password
     * @param onDataBackListener
     */
    @Override
    public void doChangeLoginPass(String url, String password, String new_password, final OnDataBackListener onDataBackListener) {


        String jsonString = loginParam.getChangeLoginPassParam(password, new_password);

        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
                HttpConst.URL.CHANGE_PASSWORD,
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
    public void doChangeDepositePass(String url, String oldPass, String newPass, final OnDataBackListener onDataBackListener) {

        String jsonString = depositParam.getResetDepositPassParam(oldPass, newPass);

        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
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


    /**
     *
     * @param url
     * @param phone
     * @param options
     * @param onDataBackListener
     */
    @Override
    public void doGetDepositeVertifyCode(String url, String phone, String options, final OnDataBackListener onDataBackListener) {



        String jsonString = depositParam.getDepositVertifyCode(phone, options);




        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
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


    /**
     * 验证验证码方法
     * @param url
     * @param phone
     * @param pass_code
     * @param onDataBackListener
     */

    @Override
    public void doConfirmVertifyCode(String url, String phone, String pass_code, final OnDataBackListener onDataBackListener) {


        String jsonString = depositParam.getConfirmVertifyCode(phone, pass_code);


        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
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
    public void doResetDepositPass(String url,String last_card_no, String new_pay_password, String pass_code, final OnDataBackListener onDataBackListener) {



        String jsonString = depositParam.getResetDepositPass(last_card_no, new_pay_password,pass_code);


        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
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
    public void doLogin(String url, String phone, String password, final OnDataBackListener onDataBackListener) {

        String logonJson = loginParam.getLogonParams(phone, password);

        Request<String> request = RequestPacking.getInstance().getRequestParamForJsonNoToken(url,RequestMethod.POST,logonJson);





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
    public void doGetCreditScore(String url, final OnDataBackListener onDataBackListener) {

        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url,RequestMethod.GET,null);

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
    public void doUploadAvatar(String url, final OnDataBackListener onDataBackListener) {


        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url,RequestMethod.PUT,null);

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
