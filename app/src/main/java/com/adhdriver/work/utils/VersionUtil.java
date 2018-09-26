package com.adhdriver.work.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

import com.adhdriver.work.AiDaiHuoApplication;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * Created by Administrator on 2017/5/6.
 * 类描述  版本信息工具类
 * 版本
 */

public class VersionUtil {


    /**
     *
     * 获取版本名称
     */
    public static String getVersionName() {

        String versionName = "";

        try {
            PackageManager manager = AiDaiHuoApplication.getInstance().getPackageManager();
            PackageInfo info = manager.getPackageInfo(AiDaiHuoApplication.getInstance().getPackageName(), 0);
            versionName = info.versionName;

        } catch (Exception e) {
            e.printStackTrace();

        }

        return versionName;
    }

    /**
     * 获取版本号
     * @return
     */
    public static int getVersionCode() {

        int versionCode = 0;

        try {
            PackageManager manager = AiDaiHuoApplication.getInstance().getPackageManager();
            PackageInfo info = manager.getPackageInfo(AiDaiHuoApplication.getInstance().getPackageName(), 0);
            versionCode = info.versionCode;

        } catch (Exception e) {
            e.printStackTrace();

        }

        return versionCode;

    }


    /**
     * 获取包名
     * @return
     */
    public static String getPackageName() {

        String packageName = "";

        try {
            PackageManager manager = AiDaiHuoApplication.getInstance().getPackageManager();
            PackageInfo info = manager.getPackageInfo(AiDaiHuoApplication.getInstance().getPackageName(), 0);
            packageName = info.packageName;

        } catch (Exception e) {
            e.printStackTrace();

        }

        return packageName;

    }


    /**
     * 获取手机系统版本
     */

    public static String getPhoneSystemVersion() {

        String systemVerson = "";

        systemVerson = android.os.Build.VERSION.RELEASE;

        return systemVerson;

    }

    /**
     * 获取手机品牌名称
     *
     * @return
     */
    public static String getPhoneBrand() {

        String brand = android.os.Build.BRAND;

        return brand;

    }




    /**
     * 获取手机型号
     *
     * @return
     */
    public static String getPhoneType() {

        String phoneType = android.os.Build.MODEL;

        return phoneType;

    }



    /**
     * 获取手机唯一标识符
     */

    public static String getTheIMEI() {

        String imei = "";

        TelephonyManager TelephonyMgr = (TelephonyManager) AiDaiHuoApplication.getInstance().getSystemService(TELEPHONY_SERVICE);

        imei = TelephonyMgr.getDeviceId();


        return imei;
    }




}
