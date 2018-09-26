package com.adhdriver.work.method.impl;

import com.adhdriver.work.http.CallServer;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.http.HttpSingleResponseListener;
import com.adhdriver.work.http.RequestPacking;
import com.adhdriver.work.http.requestparam.ExamParam;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IExam;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.nohttp.tools.NetUtil;

/**
 * Created by Administrator on 2018/1/18.
 * 类描述
 * 版本
 */

public class IExamImpl implements IExam {


    private ExamParam examParam;

    public IExamImpl() {
        this.examParam = new ExamParam();
    }

    @Override
    public void doGetEameQuestions(String url, String accessToken, int size, int index, final OnDataBackListener onDataBackListener) {






        Request<String> request = RequestPacking.getInstance().getRequestParamSetTokenForJson(
                url,
                RequestMethod.GET,
                accessToken,
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
    public void doGetEameAnswers(String url, String accessToken, final OnDataBackListener onDataBackListener) {



        Request<String> request = RequestPacking.getInstance().getRequestParamSetTokenForJson(
                url,
                RequestMethod.GET,
                accessToken,
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
    public void doGetEameMark(String url, String accessToken, String driver_id, int mark, int paper_id, int correct_number, int error_number, final OnDataBackListener onDataBackListener) {

       String param =  examParam.getExamMarkParam(driver_id, mark, paper_id, correct_number, error_number);

        Request<String> request = RequestPacking.getInstance().getRequestParamSetTokenForJson(
                url,
                RequestMethod.POST,
                accessToken,
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
}
