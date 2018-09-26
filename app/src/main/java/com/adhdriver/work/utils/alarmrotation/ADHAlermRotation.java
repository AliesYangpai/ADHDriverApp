package com.adhdriver.work.utils.alarmrotation;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.adhdriver.work.constant.ConstLog;
import com.adhdriver.work.receiver.AlarmBroadCastReciver;
import com.adhdriver.work.utils.VersionUtil;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2018/1/2.
 * 类描述 轮训广播类
 * 版本
 */

public class ADHAlermRotation {

    private AlarmManager alarmManager;


    private WeakReference<Context> weakReference;
    private int alermCount;

    public ADHAlermRotation(Context context) {

        weakReference = new WeakReference<Context>(context);

        alarmManager = (AlarmManager)weakReference.get().getSystemService(Context.ALARM_SERVICE);
    }


    /**
     * 开始轮训广播
     */
    public void doAlermStart() {

        Intent i = new Intent(weakReference.get(), AlarmBroadCastReciver.class);
        i.setAction("greate");
        PendingIntent pi = PendingIntent.getBroadcast(weakReference.get(), alermCount++, i, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 1000, 6000, pi);
        Log.i(ConstLog.SERVICE_TAG, "=DRIVER=====Alerm闹钟开始发送===="
                + "  手机信息：" + VersionUtil.getPhoneBrand() + "_" + VersionUtil.getPhoneType() + "_" + VersionUtil.getPhoneSystemVersion() + " 当前apk版本：" + VersionUtil.getVersionName());

    }

    /**
     * 关闭轮训广播
     */
    public void doAlermStop() {

        if (null == alarmManager) {
            alarmManager = (AlarmManager)weakReference.get().getSystemService(Context.ALARM_SERVICE);
        }

        Intent i = new Intent(weakReference.get(), AlarmBroadCastReciver.class);
        PendingIntent pi = PendingIntent.getBroadcast(weakReference.get(), alermCount++, i, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pi);
        alermCount = 0;
        alarmManager = null;
        Log.i(ConstLog.SERVICE_TAG, "=DRIVER=====Alerm闹钟取消===="
                + "  手机信息：" + VersionUtil.getPhoneBrand() + "_" + VersionUtil.getPhoneType() + "_" + VersionUtil.getPhoneSystemVersion() + " 当前apk版本：" + VersionUtil.getVersionName());




    }
}
