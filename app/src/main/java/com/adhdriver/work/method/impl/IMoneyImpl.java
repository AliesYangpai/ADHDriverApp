package com.adhdriver.work.method.impl;

import com.adhdriver.work.constant.ConstTag;
import com.adhdriver.work.http.CallServer;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.http.HttpSingleResponseListener;
import com.adhdriver.work.http.RequestPacking;
import com.adhdriver.work.http.requestparam.MoneyParam;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IMoney;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by Administrator on 2018/1/10.
 * 类描述
 * 版本
 */

public class IMoneyImpl implements IMoney {


    private MoneyParam moneyParam;

    public IMoneyImpl() {
        this.moneyParam = new MoneyParam();
    }

    @Override
    public void doGetPaymentsChannels(String url,
                                      String order_type,
                                      String os_type,
                                      String device_type, final OnDataBackListener onDataBackListener) {

        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url,
                RequestMethod.GET,
                null);


        request.add("order_type", order_type);
        request.add("os_type", os_type);
        request.add("device_type", device_type);

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
    public void doBalanceRecharge(String url,
                                  String order_type,
                                  String amount,
                                  String channel_id,
                                  String os_type,
                                  String device_type,
                                  String device_id,
                                  String payer, final OnDataBackListener onDataBackListener) {



        String param = moneyParam.getBalanceRechargeParam(order_type,amount,channel_id,os_type,device_type,device_id,payer);





        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url,
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

                onDataBackListener.onFail(response.responseCode(),response.get());

            }
            @Override
            protected void OnHttpFinish(int what) {
                onDataBackListener.onFinish();
            }
        });




    }

    @Override
    public void doDepositRecharge(String url, String order_type, String amount, String channel_id, String os_type, String device_type, String device_id, String payer, final OnDataBackListener onDataBackListener) {



        String param = moneyParam.getDepositeRechargeParam(order_type,amount,channel_id,os_type,device_type,device_id,payer);


        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url,
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

                onDataBackListener.onFail(response.responseCode(),response.get());

            }
            @Override
            protected void OnHttpFinish(int what) {
                onDataBackListener.onFinish();
            }
        });
    }



    @Override
    public void doSetDepositPass(String url, String pass, final OnDataBackListener onDataBackListener) {

       String param = moneyParam.getSettingDepositPassParam(pass);

        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url,
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

                onDataBackListener.onFail(response.responseCode(),response.get());

            }
            @Override
            protected void OnHttpFinish(int what) {
                onDataBackListener.onFinish();
            }
        });

    }

    @Override
    public void doBalanceDeposit(String url, String amount, String payment_channel_id, String os_type, String device_id, String open_id, String pay_password, final OnDataBackListener onDataBackListener) {

        String param =  moneyParam.getBalanceDepositParam(
                amount,
                payment_channel_id,
                os_type,
                device_id,
                 open_id,
                pay_password);

        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url,
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

                onDataBackListener.onFail(response.responseCode(),response.get());

            }
            @Override
            protected void OnHttpFinish(int what) {
                onDataBackListener.onFinish();
            }
        });

    }
}
