package com.adhdriver.work.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.adhdriver.work.callback.NetWorkStateChangedCallBack;
import com.wenming.library.util.NetUtil;

/**
 * Created by Administrator on 2018/1/22.
 * 类描述
 * 版本
 */

public class NetWorkBroadCastReciver extends BroadcastReceiver {

    private NetWorkStateChangedCallBack netWorkStateChangedCallBack;

    public void setNetWorkStateChangedCallBack(NetWorkStateChangedCallBack netWorkStateChangedCallBack) {
        this.netWorkStateChangedCallBack = netWorkStateChangedCallBack;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        // 如果相等的话就说明网络状态发生了变化
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            int netWorkState = getNetWorkState(context);
            // 接口回调传过去状态的类型


            if(null != netWorkStateChangedCallBack) {

                netWorkStateChangedCallBack.onNetChange(netWorkState);
            }

        }

    }


    /**
     * 没有连接网络
     */
    private  final int NETWORK_NONE = -1;
    /**
     * 移动网络
     */
    private  final int NETWORK_MOBILE = 0;
    /**
     * 无线网络
     */
    private  final int NETWORK_WIFI = 1;

    public  int getNetWorkState(Context context) {
        // 得到连接管理器对象
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {

            if (activeNetworkInfo.getType() == (ConnectivityManager.TYPE_WIFI)) {
                return NETWORK_WIFI;
            } else if (activeNetworkInfo.getType() == (ConnectivityManager.TYPE_MOBILE)) {
                return NETWORK_MOBILE;
            }
        } else {
            return NETWORK_NONE;
        }
        return NETWORK_NONE;
    }



}
