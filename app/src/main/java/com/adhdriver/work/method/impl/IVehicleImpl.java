package com.adhdriver.work.method.impl;

import com.adhdriver.work.constant.ConstTag;
import com.adhdriver.work.entity.driver.temp.TempRegDriverVehicle;
import com.adhdriver.work.entity.driver.vehicle.DriverVehicle;
import com.adhdriver.work.http.CallServer;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.http.HttpSingleResponseListener;
import com.adhdriver.work.http.RequestPacking;
import com.adhdriver.work.http.requestparam.VehicleParam;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IVehicle;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by Administrator on 2017/12/19.
 * 类描述
 * 版本
 */

public class IVehicleImpl implements IVehicle {

    private VehicleParam vehicleParam;


    public IVehicleImpl() {
        this.vehicleParam = new VehicleParam();
    }

    /**
     * 获取车辆类型
     *
     * @param url
     * @param accessToken
     * @param onDataBackListener
     */
    @Override
    public void doGetVehicleTypes(String url, String accessToken, final OnDataBackListener onDataBackListener) {

        Request<String> request = RequestPacking.getInstance().getRequestParamSetTokenForJson(url, RequestMethod.GET, accessToken, null);
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
    public void doGetVehicleCategories(String url, String accessToken, final OnDataBackListener onDataBackListener) {


        Request<String> request = RequestPacking.getInstance().getRequestParamSetTokenForJson(url,RequestMethod.GET,accessToken,null);

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
    public void doCompleteVehicleInfo(String url, String accessToken, TempRegDriverVehicle tempRegDriverVehicle,final OnDataBackListener onDataBackListener) {

        String jsonParam = vehicleParam.getRegVehicleAndDriverParam(tempRegDriverVehicle);

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
    public void doGetVehicles(String url, int size, int index, final OnDataBackListener onDataBackListener) {

        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url,RequestMethod.GET,null);

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
    public void doGetVehiclesInFresh(String url, int size, int index, final OnDataBackListener onDataBackListener) {

        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url,RequestMethod.GET,null);

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
    public void doGetVehiclesInLoadMore(String url, int size, int index, final OnDataBackListener onDataBackListener) {


        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url,RequestMethod.GET,null);

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
    public void doGetVehicleDetail(String url, final OnDataBackListener onDataBackListener) {


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
    public void doDelVehicle(String url, final OnDataBackListener onDataBackListener) {



        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url,RequestMethod.DELETE,null);

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
    public void doAddVehicle(String url, TempRegDriverVehicle tempRegDriverVehicle, final OnDataBackListener onDataBackListener) {

        String param =   vehicleParam.getAddVehicleParam(tempRegDriverVehicle);

        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url,RequestMethod.POST,param);

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
    public void doGetVehicleTypes(String url, final OnDataBackListener onDataBackListener) {
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
    public void doGetVehicleCategories(String url, final OnDataBackListener onDataBackListener) {


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
    public void doUpdateVehicle(String url, DriverVehicle driverVehicle, final OnDataBackListener onDataBackListener) {
        String param = vehicleParam.getEditVehicleParam(driverVehicle, ConstTag.CarPassStatusTag.PENDING);

        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url, RequestMethod.PUT, param);
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
     * 切换车辆
     * @param url
     * @param onDataBackListener
     */

    @Override
    public void doSwitchVehicle(String url, final OnDataBackListener onDataBackListener) {


        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url, RequestMethod.PUT, null);
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
    public void doUpLoadNewVehicleLocation(String url, double longitude, double latitude, String zone_code, String driver_vehicle_id, String vehicle_category_name, final OnDataBackListener onDataBackListener) {
        String param = vehicleParam.getCoordinateParam(longitude, latitude, zone_code, driver_vehicle_id, vehicle_category_name);
        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url, RequestMethod.POST, param);

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
