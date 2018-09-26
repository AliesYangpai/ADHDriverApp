package com.adhdriver.work.http;


import android.util.Log;

import com.adhdriver.work.AiDaiHuoApplication;
import com.adhdriver.work.constant.ConstLog;
import com.adhdriver.work.utils.ToastUtil;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by Administrator on 2017/4/14 0014.
 * 类描述   请求的返回处理
 * 版本
 */
public abstract class HttpSingleResponseListener<T> implements OnResponseListener<T> {


    /**
     * 用于下拉刷新和上拉加载的单个请求封装
     */
    public HttpSingleResponseListener() {

    }


    @Override
    public void onStart(int what) {

        OnHttpStart(what);

    }

    @Override
    public void onSucceed(int what, Response<T> response) {



        String requestMethod = response.request().getRequestMethod().toString();

        Log.i(ConstLog.SERVER_BACK, "回调onSucceed："+requestMethod + " 请求成功 返回码:" + response.responseCode() + " 请求URL:" + response.request().url() + " 请求参数:" + response.request().getParamKeyValues().toString() + " 请求方法：" + response.request().getRequestMethod() + " 返回结果：" + response.toString());


        responseCodeInSuccess(what, response);

    }


    @Override
    public void onFailed(int what, Response<T> response) {


        Log.i(ConstLog.SERVER_BACK, "回调onFailed：请求失败 返回码：" + response.responseCode() + "请求URL:" + response.request().url() + " 请求方法：" + response.request().getRequestMethod() + " 返回结果：" + response.get());


        responseCodeInFail(what,response);


    }

    @Override
    public void onFinish(int what) {


        OnHttpFinish(what);

    }


    /**
     * 判断返回码范围，这里只需要判读 [200,300]
     *
     * @param what
     * @param response
     */
    private void responseCodeInSuccess(int what, Response<T> response) {

        int responseCode = response.responseCode();

        boolean isSuccess = successOrFail(responseCode);


        if (isSuccess) {


            OnHttpSuccessed(what, response);

        } else {

            onFailed(what, response);

        }
    }


    private void responseCodeInFail(int what, Response<T> response) {


        int responseCode = response.responseCode();

        switch (responseCode) {


            case HttpConst.CODE_0:

                ToastUtil.showMsg(AiDaiHuoApplication.getInstance(), "网络链接异常");
                onHttpFailed(what, response);

                break;

            case HttpConst.CODE_401:
                ToastUtil.showMsg(AiDaiHuoApplication.getInstance(), "未授权（Token过期等）");
                onHttpFailed(what, response);

            default:
                onHttpFailed(what, response);
                break;
        }


    }


    /**
     * 判断返回
     *
     * @param code
     * @return
     */
    private boolean successOrFail(int code) {


        boolean result = false;

        switch (code) {


            case HttpConst.CODE_200:
                result = true;
                break;

            case HttpConst.CODE_201:

                result = true;
                break;


            case HttpConst.CODE_204:
                result = true;
                break;


        }


        return result;

    }


    protected abstract void OnHttpStart(int what);

    protected abstract void OnHttpSuccessed(int what, Response<T> response);

    protected abstract void onHttpFailed(int what, Response<T> response);

    protected abstract void OnHttpFinish(int what);

}
