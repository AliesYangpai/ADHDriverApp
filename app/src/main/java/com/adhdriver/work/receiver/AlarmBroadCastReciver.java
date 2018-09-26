package com.adhdriver.work.receiver;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.adhdriver.work.constant.ConstLog;
import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.service.ServiceWatch1;
import com.adhdriver.work.service.ServiceWatch2;
import com.adhdriver.work.utils.SpUtil;
import com.adhdriver.work.utils.VersionUtil;
import com.adhdriver.work.utils.weaklock.ADHWeakLock;

import java.util.List;

import cn.jesse.nativelogger.NLogger;

/**
 * Created by Administrator on 2017/9/22.
 * 类描述   轮训广播类，为了间隔检测 服务是否存在
 * 版本
 */

public class AlarmBroadCastReciver extends BroadcastReceiver {




    private ADHWeakLock adhWeakLock;


    public AlarmBroadCastReciver() {

        adhWeakLock = new ADHWeakLock();

    }

    @Override
    public void onReceive(Context context, Intent intent) {






        Log.i(ConstLog.SERVICE_TAG, "=DRIVER====Alerm==轮训的广播====onReceive回调了"
                + "  手机信息：" + VersionUtil.getPhoneBrand() + "_" + VersionUtil.getPhoneType() + "_" + VersionUtil.getPhoneSystemVersion() + " 当前apk版本：" + VersionUtil.getVersionName());

//        LogWriter.writeLog(ConstLog.SERVICE_TAG, "=DRIVER====Alerm==轮训的广播====onReceive回调了"
//                + "  手机信息：" + StringUtils.getPhoneBrand() + "_" + StringUtils.getPhoneType() + "_" + StringUtils.getPhoneSystemVersion() + " 当前apk版本：" + VersionUtil.getVersionName());

        NLogger.i(ConstLog.SERVICE_TAG, "=DRIVER====Alerm==轮训的广播====onReceive回调了"
                + "  手机信息：" + VersionUtil.getPhoneBrand() + "_" + VersionUtil.getPhoneType() + "_" + VersionUtil.getPhoneSystemVersion() + " 当前apk版本：" + VersionUtil.getVersionName());



        /**
         * 电源锁检测
         */
        doCheckWeakLock();


        doCheckWatchService(context);


    }


    /**
     * 判断Service服务是否存在
     *
     * @param serviceClass
     * @param context
     * @return
     */

    private boolean isServiceRunning(Class<?> serviceClass, Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager
                .getRunningServices(Integer.MAX_VALUE);
        if (serviceList == null || serviceList.size() == 0)
            return false;
        for (ActivityManager.RunningServiceInfo info : serviceList) {
            if (info.service.getClassName().equals(serviceClass.getName()))
                return true;
        }
        return false;
    }


    /**
     * 判断是否已退出
     * @return
     */
    private boolean isLogin() {


        boolean result = SpUtil.getInstance().getBoolenValue(ConstSp.SP_KEY_IS_LOGIN_OR_NOT, ConstSp.SP_VALUE.IS_LOGIN_DEFAULT);
        return result;
    }

    /**
     * 获取电源锁，保持该服务在屏幕熄灭时仍然获取CPU时，保持运行
     */








    /**
     * 整体检测电源锁
     */
    private void doCheckWeakLock() {


        if(isLogin()) {
            adhWeakLock.doAcquireWakeLock();
        }else {
            adhWeakLock.doReleaseWakeLock();
        }

    }


    /**
     * 服务检测
     */
    private void doCheckWatchService(Context context) {

        /**
         * 一号服务是否存在
         */
        if (!isServiceRunning(ServiceWatch1.class, context)) {


            if(isLogin()) {

                context.startService(new Intent(context, ServiceWatch1.class));
                Log.i(ConstLog.SERVICE_TAG, "=DRIVER====Alerm==轮训的广播已接受====守护1一号正在重启...."
                        + "  手机信息：" + VersionUtil.getPhoneBrand() + "_" + VersionUtil.getPhoneType() + "_" + VersionUtil.getPhoneSystemVersion() + " 当前apk版本：" + VersionUtil.getVersionName());


                NLogger.i(ConstLog.SERVICE_TAG, "=DRIVER====Alerm==轮训的广播已接受====守护1一号正在重启...."
                        + "  手机信息：" + VersionUtil.getPhoneBrand() + "_" + VersionUtil.getPhoneType() + "_" + VersionUtil.getPhoneSystemVersion() + " 当前apk版本：" + VersionUtil.getVersionName());

            }


        }


        /**
         * 二号服务是否存在
         */

        if (!isServiceRunning(ServiceWatch2.class, context)) {

            if(isLogin()) {

                context.startService(new Intent(context, ServiceWatch2.class));
                Log.i(ConstLog.SERVICE_TAG, "=DRIVER====Alerm==轮训的广播已接受====守护2一号正在重启...."
                        + "  手机信息：" + VersionUtil.getPhoneBrand() + "_" + VersionUtil.getPhoneType() + "_" + VersionUtil.getPhoneSystemVersion() + " 当前apk版本：" + VersionUtil.getVersionName());
//                LogWriter.writeLog(ConstLog.SERVICE_TAG, "=DRIVER====Alerm==轮训的广播已接受====守护2一号正在重启...."
//                        + "  手机信息：" + StringUtils.getPhoneBrand() + "_" + StringUtils.getPhoneType() + "_" + StringUtils.getPhoneSystemVersion() + " 当前apk版本：" + VersionUtil.getVersionName());


                NLogger.i(ConstLog.SERVICE_TAG, "=DRIVER====Alerm==轮训的广播已接受====守护2一号正在重启...."
                        + "  手机信息：" + VersionUtil.getPhoneBrand() + "_" + VersionUtil.getPhoneType() + "_" + VersionUtil.getPhoneSystemVersion() + " 当前apk版本：" + VersionUtil.getVersionName());

            }
        }
    }
}
