package com.adhdriver.work.method.impl;

import com.adhdriver.work.constant.ConstParams;
import com.adhdriver.work.http.CallServer;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.http.HttpSingleResponseListener;
import com.adhdriver.work.http.RequestPacking;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IOrderTake;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.nohttp.tools.NetUtil;

/**
 * Created by Administrator on 2017/11/22.
 * 类描述  获取到的订单实现类
 * 版本
 */

public class IOrderTakeImpl implements IOrderTake {


    @Override
    public void doGetOrderTake(String url,  int size, int index, final OnDataBackListener onDataBackListener) {


        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
                url,
                RequestMethod.GET,
                null);

        request.add("size", size);
        request.add("index", index);


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
     * 下拉刷新
     * @param url
     * @param size
     * @param index
     * @param onDataBackListener
     */
    @Override
    public void doGetOrderTakeInFresh(String url,  int size, int index, final OnDataBackListener onDataBackListener) {


        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
                url,
                RequestMethod.GET,
                null);

        request.add("size", size);
        request.add("index", index);


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
     * 上拉加载
     * @param url
     * @param size
     * @param index
     * @param onDataBackListener
     */
    @Override
    public void doGetOrderTakeInLoadMore(String url,int size, int index, final OnDataBackListener onDataBackListener) {


        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
                url,
                RequestMethod.GET,
                null);

        request.add("size", size);
        request.add("index", index);


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
    public void doGetOrderInfo(String url, final OnDataBackListener onDataBackListener) {

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
}
