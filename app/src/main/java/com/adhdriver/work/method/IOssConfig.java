package com.adhdriver.work.method;

import com.adhdriver.work.listener.OnDataBackListener;

/**
 * Created by Administrator on 2017/12/11.
 * 类描述  阿里云图片服务相关
 * 版本
 */

public interface IOssConfig {

    void doGetOssConfigInfo(String url, String token, OnDataBackListener onDataBackListener);

}
