package com.adhdriver.work.method.impl;

import com.adhdriver.work.http.CallServer;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.http.HttpSingleResponseListener;
import com.adhdriver.work.http.RequestPacking;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IOrderNotTake;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by Administrator on 2017/12/27.
 * 类描述  跨城未接单
 * 版本
 */

public class IOrderNotTakeImpl implements IOrderNotTake {



    @Override
    public void doGetAcrossCityOrders(String url, int size, int index, final OnDataBackListener onDataBackListener) {

        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url, RequestMethod.GET, null);

        request.add("size", size);                           //加载刷新
        request.add("index", index);       //加载刷新


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
    public void doGetAcrossCityOrdersInFresh(String url, int size, int index, final OnDataBackListener onDataBackListener) {


        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url, RequestMethod.GET, null);

        request.add("size", size);                           //加载刷新
        request.add("index", index);       //加载刷新


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
    public void doGetAcrossCityOrdersInLoadMore(String url,  int size, int index, final OnDataBackListener onDataBackListener) {


        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url, RequestMethod.GET, null);

        request.add("size", size);                           //加载刷新
        request.add("index", index);       //加载刷新


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
    public void doGetOrderDetail(String url, final OnDataBackListener onDataBackListener) {



        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url, RequestMethod.GET, null);



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
    public void doGetAllOrders(String url, int size, int index, final OnDataBackListener onDataBackListener) {

        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url, RequestMethod.GET, null);

        request.add("size", size);                           //加载刷新
        request.add("index", index);       //加载刷新


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
    public void doGetAllOrdersInFresh(String url, int size, int index, final OnDataBackListener onDataBackListener) {


        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url, RequestMethod.GET, null);

        request.add("size", size);                           //加载刷新
        request.add("index", index);       //加载刷新


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
    public void doGetAllOrdersInLoadMore(String url, int size, int index, final OnDataBackListener onDataBackListener) {


        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url, RequestMethod.GET, null);

        request.add("size", size);                           //加载刷新
        request.add("index", index);       //加载刷新


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
