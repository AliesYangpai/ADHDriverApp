package com.adhdriver.work.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.constant.ConstLog;
import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.location.KeepLocation;
import com.adhdriver.work.utils.SpUtil;
import com.adhdriver.work.utils.VersionUtil;

import cn.jesse.nativelogger.NLogger;

public class ServiceWatch2 extends Service {





    public ServiceWatch2() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }




    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        flags = Service.START_STICKY;
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();


        KeepLocation.getInstance().starLocation();
        Log.i(ConstLog.SERVICE_TAG,"===========onCreate========守护2号");

        NLogger.i(ConstLog.SERVICE_TAG,"=DRIVER====onCreate========守护2号"
                +"  手机信息："+VersionUtil.getPhoneBrand() + "_" + VersionUtil.getPhoneType()+ "_" +VersionUtil.getPhoneSystemVersion()+" 当前apk版本："+ VersionUtil.getVersionName());

    }





    @Override
    public void onDestroy() {
        super.onDestroy();

        boolean result = SpUtil.getInstance().getBoolenValue(ConstSp.SP_KEY_IS_LOGIN_OR_NOT, ConstSp.SP_VALUE.IS_LOGIN_DEFAULT);

        if(result) {

            Intent intent = new Intent();
            intent.setAction(ConstIntent.IntentAction.AWAKE_WATCH_2);
            sendBroadcast(intent);

        }else {
            KeepLocation.getInstance().destroylocation();
            Log.i(ConstLog.SERVICE_TAG, "==================ServiceWatch2中定位销毁已执行");
        }


        Log.i(ConstLog.SERVICE_TAG,"===onDestroy========守护2号销毁了");


    }


}
