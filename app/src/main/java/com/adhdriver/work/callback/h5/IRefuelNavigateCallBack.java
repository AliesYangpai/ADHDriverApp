package com.adhdriver.work.callback.h5;

import android.webkit.JavascriptInterface;

/**
 * Created by Administrator on 2017/12/11.
 * 类描述
 * 版本
 */

public interface IRefuelNavigateCallBack {

    @JavascriptInterface
    void startNavigate(String startLat, String startLng, String endLat, String endLng);


    @JavascriptInterface
    void setExtraInfoHead(String key, String value);
}
