package com.adhdriver.work.method;

import com.adhdriver.work.listener.OnDataBackListener;

/**
 * Created by Administrator on 2017/12/22.
 * 类描述
 * 版本
 */

public interface IReadLearn {

    void doGetLearningMaterials(String url, String accessToken, int size, int index, OnDataBackListener onDataBackListener);


}
