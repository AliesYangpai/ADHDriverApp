package com.adhdriver.work.method.impl;

import com.adhdriver.work.http.CallServer;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.http.HttpSingleResponseListener;
import com.adhdriver.work.http.RequestPacking;
import com.adhdriver.work.http.requestparam.OrderParam;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IOrderOperate;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by Administrator on 2018/1/5.
 * 类描述  订单操作的实现类
 * 版本
 */

public class IOrderOperateImpl implements IOrderOperate {

    private OrderParam orderParam;

    public IOrderOperateImpl() {
        this.orderParam = new OrderParam();
    }


    @Override
    public void doStartBidding(String url, String price, final OnDataBackListener onDataBackListener) {


        String jsonString = orderParam.getBiddingParam(price);


        Request request = RequestPacking.getInstance()
                .getRequestParamForJson(url,
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
    public void doStartGrab(String url, final OnDataBackListener onDataBackListener) {


        Request request = RequestPacking.getInstance()
                .getRequestParamForJson(url,
                        RequestMethod.POST,
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
    public void doDepartSameCity(String url, double longitude, double latitude, final OnDataBackListener onDataBackListener) {


        String jsonString = orderParam.getDepartCoordinateParam(longitude, latitude);

        Request request = RequestPacking.getInstance()
                .getRequestParamForJson(url,
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
    public void doDepartFullLoad(String url, double longitude, double latitude, final OnDataBackListener onDataBackListener) {


        String jsonString = orderParam.getDepartCoordinateParam(longitude, latitude);

        Request request = RequestPacking.getInstance()
                .getRequestParamForJson(url,
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
    public void doDepartOverOffice(String url, final OnDataBackListener onDataBackListener) {
        Request request = RequestPacking.getInstance()
                .getRequestParamForJson(url,
                        RequestMethod.POST,
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
    public void doArriveSameCityNext(String url, double longitude, double latitude, final OnDataBackListener onDataBackListener) {


        String jsonString = orderParam.getArriveNextCoordinateParam(longitude, latitude);

        Request request = RequestPacking.getInstance()
                .getRequestParamForJson(url,
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
    public void doArriveSameCity(String url, final OnDataBackListener onDataBackListener) {
        Request request = RequestPacking.getInstance()
                .getRequestParamForJson(url,
                        RequestMethod.POST,
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
    public void doArriveFullLoad(String url, final OnDataBackListener onDataBackListener) {



        Request request = RequestPacking.getInstance()
                .getRequestParamForJson(url,
                        RequestMethod.POST,
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
    public void doArriveOverOffice(String url, final OnDataBackListener onDataBackListener) {

        Request request = RequestPacking.getInstance()
                .getRequestParamForJson(url,
                        RequestMethod.POST,
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
    public void doFinishOrder(String url, String pass_code, final OnDataBackListener onDataBackListener) {

       String  jsonParam =  orderParam.getReciverCodeParam(pass_code);

        Request request = RequestPacking.getInstance()
                .getRequestParamForJson(url,
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
