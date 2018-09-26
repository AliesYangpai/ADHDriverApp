package com.adhdriver.work.method;

import com.adhdriver.work.listener.OnDataBackListener;

/**
 * Created by Administrator on 2017/11/24.
 * 类描述  意见反馈方法
 * 版本
 */

public interface IFeedBack {


    /**
     * 意见反馈
     * @param url
     * @param source
     * @param content
     * @param phone
     * @param os_version
     * @param model
     * @param os_type
     * @param onDataBackListener
     */
    void doFeedBack(String url,
                    String source,
                    String content,
                    String phone,
                    String os_version,
                    String model,
                    String os_type, OnDataBackListener onDataBackListener);
}
