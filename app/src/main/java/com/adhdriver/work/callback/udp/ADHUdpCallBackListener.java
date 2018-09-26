package com.adhdriver.work.callback.udp;

/**
 * Created by Administrator on 2018/1/22.
 * 类描述   UDP的callBack方法
 * 版本
 */

public interface ADHUdpCallBackListener {

    
    
    void onConnect();

    void onSendData(String param);


    void onHeart(String param);

    void onClose();


    void onReceiveData(String data);

    void onReConnect();

}
