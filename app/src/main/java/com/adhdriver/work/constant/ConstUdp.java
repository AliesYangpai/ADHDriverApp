package com.adhdriver.work.constant;


/**
 * Created by Administrator on 2018/1/22.
 * 类描述
 * 版本
 */

public class ConstUdp {

    public static final String HOST = "devapi.adaihuo.com";

    public static final int PORT = 53355;

    public static final int HANDLER_CONNECT_SUCCESS = 1001;     //链接socket
    public static final int HANDLER_SEND_DATA_SUCCESS = 1002;    //发送数据
    public static final int HANDLER_HEART_SUCCESS = 1003;       //心跳
    public static final int HANDLER_RECEIVE_DATA_SUCCESS = 1004; //接收到数据
    public static final int HANDLER_CLOSE_SUCCESS = 1005;     //关闭socket
    public static final int HANDLER_RECONNECT = 1006;     //重新连入socket


    public static final String BUNDLE_UDP_SEND = "send";
    public static final String BUNDLE_UDP_HEART = "heart";
    public static final String BUNDLE_UDP_RECEIVE = "receive";



    public static final int TYPE_SEND = 0; //发送时使用
    public static final int TYPE_KICK_OUT = 5; //被踢出
    public static final int TYPE_RECONNECT = 6; //重新连入

}
