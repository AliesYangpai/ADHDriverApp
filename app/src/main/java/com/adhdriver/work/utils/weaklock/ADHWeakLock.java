package com.adhdriver.work.utils.weaklock;

import android.content.Context;
import android.os.PowerManager;
import android.util.Log;

import com.adhdriver.work.AiDaiHuoApplication;
import com.adhdriver.work.constant.ConstLog;
import com.adhdriver.work.service.ServiceWatch1;
import com.adhdriver.work.utils.VersionUtil;

import java.lang.ref.WeakReference;

import cn.jesse.nativelogger.NLogger;

/**
 * Created by Administrator on 2018/1/2.
 * 类描述  电源锁
 * 版本
 */

public class ADHWeakLock {

    private PowerManager.WakeLock wakeLock;


    public ADHWeakLock() {

    }


    /**
     * 获取电源锁
     */
    public void doAcquireWakeLock() {

        if (null == wakeLock) {

            PowerManager pm = (PowerManager) AiDaiHuoApplication.getInstance().getSystemService(Context.POWER_SERVICE);

            wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, ServiceWatch1.class.getName());


//            wakeLock = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK,"bright");

            if (null != wakeLock) {

                wakeLock.acquire();


                Log.i(ConstLog.SERVICE_TAG, "=DRIVER====KeepLocation中获取电源锁===="
                        + "  手机信息：" + VersionUtil.getPhoneBrand() + "_" + VersionUtil.getPhoneType() + "_" + VersionUtil.getPhoneSystemVersion() + " 当前apk版本：" + VersionUtil.getVersionName());

            }
        } else {

            if (!wakeLock.isHeld()) {
                wakeLock.acquire();
            }
        }

    }


    /**
     * 释放电源锁
     */
    public void doReleaseWakeLock() {

        if (null != wakeLock) {

            wakeLock.release();
            wakeLock = null;

            Log.i(ConstLog.SERVICE_TAG, "=DRIVER====KeepLocation中释放电源锁===="
                    + "  手机信息：" + VersionUtil.getPhoneBrand() + "_" + VersionUtil.getPhoneType() + "_" + VersionUtil.getPhoneSystemVersion() + " 当前apk版本：" + VersionUtil.getVersionName());
        }
    }

}
