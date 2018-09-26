package com.adhdriver.work.listener;

/**
 * Created by Alie on 2017/11/3.
 * 类描述  请求数据返回公共接口
 * 版本
 */

public interface OnDataBackListener {


    void onStart();

    void onSuccess(String data);

    void onFail(int code, String data);

    void onFinish();
}
