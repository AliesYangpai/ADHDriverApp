package com.adhdriver.work.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.constant.ConstLog;
import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.function.FunctionUDP;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.location.KeepLocation;
import com.adhdriver.work.utils.SpUtil;
import com.adhdriver.work.utils.VersionUtil;
import com.adhdriver.work.utils.alarmrotation.ADHAlermRotation;
import com.adhdriver.work.utils.weaklock.ADHWeakLock;

import cn.jesse.nativelogger.NLogger;

public class ServiceWatch1 extends Service {


    private ADHWeakLock adhWeakLock;
    private ADHAlermRotation adhAlermRotation;


    public ServiceWatch1() {
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




        Log.i("Service_onCreate", "============onCreate====ServiceWatch1");

        adhWeakLock = new ADHWeakLock();
        adhAlermRotation = new ADHAlermRotation(this);
        adhWeakLock.doAcquireWakeLock();

        KeepLocation.getInstance().starLocation();



        Log.i(ConstLog.SERVICE_TAG, "===========onCreate========守护1号");
        adhAlermRotation.doAlermStart();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();


        boolean result = SpUtil.getInstance().getBoolenValue(ConstSp.SP_KEY_IS_LOGIN_OR_NOT, ConstSp.SP_VALUE.IS_LOGIN_DEFAULT);
        if (result) {

            Intent intent = new Intent();
            intent.setAction(ConstIntent.IntentAction.AWAKE_WATCH_1);
            sendBroadcast(intent);


        } else {

            if (null != adhWeakLock) {
                adhWeakLock.doReleaseWakeLock();
                adhWeakLock = null;
            }

            if(null != adhAlermRotation) {
                adhAlermRotation.doAlermStop();
                adhAlermRotation = null;
            }

            KeepLocation.getInstance().destroylocation();
//            KeepLocation.getInstance().getAdhUdpHelper().doSocketClose();
            Log.i(ConstLog.SERVICE_TAG, "==================ServiceWatch1中定位销毁已执行");
        }
        Log.i(ConstLog.SERVICE_TAG, "===onDestroy========守护1号销毁了");


        Log.i("Service_onCreate", "============onDestroy====ServiceWatch1");
    }


    /**
     * 开启alarm轮训服务监听服务
     */





}
