package com.adhdriver.work.method.impl;

import com.adhdriver.work.constant.ConstParams;
import com.adhdriver.work.http.CallServer;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.http.HttpSingleResponseListener;
import com.adhdriver.work.http.RequestPacking;
import com.adhdriver.work.http.requestparam.PublishRouteParam;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IPublishRoute;
import com.adhdriver.work.utils.ToastUtil;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by Administrator on 2017/12/1.
 * 类描述
 * 版本
 */

public class IPublishRouteImpl implements IPublishRoute {


   private PublishRouteParam publishRouteParam;

    public IPublishRouteImpl() {

        publishRouteParam = new PublishRouteParam();
    }

    @Override
    public void doPublishRoute(String url,
                              String hitchhike_no,
                              String driver_id,
                              String depart_time,
                              String depart_zone_code,
                              String depart_address,
                              String depart_coordinate_x,
                              String depart_coordinate_y,
                              String destination_zone_code,
                              String destination_address,
                              String destination_coordinate_x,
                              String destination_coordinate_y, final OnDataBackListener onDataBackListener) {






       String jsonString =  publishRouteParam.getPublishRouteParam(
                 hitchhike_no,
                 driver_id,
                 depart_time,
                 depart_zone_code,
                 depart_address,
                 depart_coordinate_x,
                 depart_coordinate_y,
                 destination_zone_code,
                 destination_address,
                 destination_coordinate_x,
                 destination_coordinate_y);

        Request<String> request = RequestPacking.
                getInstance().
                getRequestParamForJson(url, RequestMethod.POST,jsonString);


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
    public void doGetPublishRoutes(String url, int size, int index, final OnDataBackListener onDataBackListener) {

        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url, RequestMethod.GET, null);

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
                onDataBackListener.onFail(response.responseCode(),response.get());
            }

            @Override
            protected void OnHttpFinish(int what) {
                onDataBackListener.onFinish();
            }
        });




    }

    @Override
    public void doGetPublishRoutesInFresh(String url, int size, int index, final OnDataBackListener onDataBackListener) {
        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url, RequestMethod.GET, null);

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
                onDataBackListener.onFail(response.responseCode(),response.get());
            }

            @Override
            protected void OnHttpFinish(int what) {
                onDataBackListener.onFinish();
            }
        });
    }

    @Override
    public void doGetPublishRoutesInLoadMore(String url, int size, int index, final OnDataBackListener onDataBackListener) {

        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url, RequestMethod.GET, null);

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
                onDataBackListener.onFail(response.responseCode(),response.get());
            }

            @Override
            protected void OnHttpFinish(int what) {
                onDataBackListener.onFinish();
            }
        });

    }

    @Override
    public void doCancelThisRoute(String url, final OnDataBackListener onDataBackListener) {
        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url, RequestMethod.POST, null);

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
