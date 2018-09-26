package com.adhdriver.work.method;

import com.adhdriver.work.listener.OnDataBackListener;

/**
 * Created by Alie on 2017/11/13.
 * 类描述  活动界面的方法接口
 * 版本
 */

public interface IFgActDriver {


    void doGetActList(OnDataBackListener onDataBackListener);



    void doLoadMore(OnDataBackListener onDataBackListener);

    void doFresh(OnDataBackListener onDataBackListener);

}
