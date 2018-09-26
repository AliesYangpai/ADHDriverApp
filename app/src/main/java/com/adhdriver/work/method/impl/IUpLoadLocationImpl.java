package com.adhdriver.work.method.impl;

import com.adhdriver.work.http.CallServer;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.http.HttpSingleResponseListener;
import com.adhdriver.work.http.RequestPacking;
import com.adhdriver.work.http.requestparam.LocationParam;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IUpLoadLocation;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by Administrator on 2018/1/2.
 * 类描述
 * 版本
 */

public class IUpLoadLocationImpl implements IUpLoadLocation {

    private LocationParam locationParam;


    public IUpLoadLocationImpl() {
        locationParam = new LocationParam();
    }

    @Override
    public void doUpLoadLocation(String url,
                                 double longitude,
                                 double latitude,
                                 String adCode,
                                 String driverVehcleId,
                                 String logonDriverCategory,
                                 final OnDataBackListener onDataBackListener) {

        String jsonParam = locationParam.getCoordinate(longitude,latitude,adCode,driverVehcleId,logonDriverCategory);


        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url, RequestMethod.POST,jsonParam);

        CallServer.getInstance().add(HttpConst.HTTP_WHAT, request, new HttpSingleResponseListener<String>() {
            @Override
            protected void OnHttpStart(int what) {

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

            }
        });


    }

    @Override
    public void doUpLoadDriverArea(String url, final OnDataBackListener onDataBackListener) {
        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url,RequestMethod.PUT,null);

        CallServer.getInstance().add(HttpConst.HTTP_WHAT, request, new HttpSingleResponseListener<String>() {
            @Override
            protected void OnHttpStart(int what) {

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

            }
        });
    }

}
