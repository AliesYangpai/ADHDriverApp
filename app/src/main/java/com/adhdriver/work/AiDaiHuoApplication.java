package com.adhdriver.work;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import android.util.Log;

import com.adhdriver.work.constant.ConstAliYun;
import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.utils.ImgUtil;
import com.adhdriver.work.utils.SpUtil;


import com.adhdriver.work.utils.VersionUtil;
import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.alibaba.sdk.android.push.notification.BasicCustomPushNotification;
import com.alibaba.sdk.android.push.notification.CustomNotificationBuilder;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.umeng.analytics.MobclickAgent;
import com.wenming.library.LogReport;
import com.wenming.library.save.imp.CrashWriter;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;


import org.litepal.LitePalApplication;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.jesse.nativelogger.NLogger;
import cn.jesse.nativelogger.formatter.SimpleFormatter;
import cn.jesse.nativelogger.logger.LoggerLevel;
import cn.jesse.nativelogger.util.CrashWatcher;
import cn.sharesdk.framework.ShareSDK;

/**
 * Created by Administrator on 2017/4/8 0008.
 * 类描述
 * 版本
 */
public class AiDaiHuoApplication extends LitePalApplication implements CommonCallback,Application.ActivityLifecycleCallbacks {


    public static final String TAG = AiDaiHuoApplication.class.getSimpleName();

    private static AiDaiHuoApplication mInstance;

    @Override
    public void onCreate() {

        super.onCreate();

        mInstance = this;

        initNoHttp();

        initImageLoader(this);

        initShareSDK();

//        initUmengPush();


        initAliYunPush(mInstance);

        initUmengAnalyze();


        registerActivityLifecycleCallbacks(mInstance);

//        initLogUtil();


        initNativeLogUtil();


        registBroadCast();


        Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandler);

    }


    private void registBroadCast() {



    }


    /**
     * 打印记录日志ey
     */
    private void initLogUtil() {


        LogReport.getInstance()
                .setCacheSize(10 * 1024 * 1024)//支持设置缓存大小，超出后清空
                .setLogDir(getApplicationContext(),"sdcard/" + this.getString(this.getApplicationInfo().labelRes) + "/")//定义路径为：sdcard/[app name]/
                .setWifiOnly(true)//设置只在Wifi状态下上传，设置为false为Wifi和移动网络都上传
                .setLogSaver(new CrashWriter(getApplicationContext()))//支持自定义保存崩溃信息的样式
                //.setEncryption(new AESEncode()) //支持日志到AES加密或者DES加密，默认不开启
                .init(getApplicationContext());


    }


    private void initNativeLogUtil() {





        NLogger.getInstance()
                .builder()
                .tag("ADH_ARIVER")
                .loggerLevel(LoggerLevel.INFO)
                .fileLogger(true)
                .fileDirectory(getApplicationContext().getFilesDir().getPath() + "/logs/AdhLog.txt")
                .fileFormatter(new SimpleFormatter())
                .expiredPeriod(3)
                .catchException(true, new CrashWatcher.UncaughtExceptionListener() {
                    @Override
                    public void uncaughtException(Thread thread, Throwable ex) {
                        NLogger.e("uncaughtException", ex);
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                })
                .build();
    }


    /**
     * 初始化友盟分析工具
     */
    private void initUmengAnalyze() {


        //普通统计场景类型
        MobclickAgent.setScenarioType(mInstance, MobclickAgent.EScenarioType.E_UM_NORMAL);

        /**
         * 禁止默认的页面统计方式，这样将不会再自动统计Activity。
         * 该方法主要是用于项目结构 并非是只有activity组成，而是由activity、fragment等组成
         */
//        MobclickAgent.openActivityDurationTrack(false);


//        MobclickAgent.setDebugMode( true );

    }










    /**
     * 初始化分享
     */

    private void  initShareSDK() {
        ShareSDK.initSDK(mInstance,"0a169860502c55427731614f87a3c763");

    }















    private CloudPushService pushService;
    /**
     * 初始化云推送通道
     * 阿里云推送
     * @param applicationContext
     */
    private void initAliYunPush(Context applicationContext) {
        PushServiceFactory.init(applicationContext);
        pushService = PushServiceFactory.getCloudPushService();
        pushService.register(applicationContext, this);
        pushService.setNotificationLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));//通知栏图标
        pushService.setNotificationSmallIcon(R.mipmap.ic_launcher); //设置状态栏图标


//        pushService.setNotificationSoundFilePath(getSoundPath());



        BasicCustomPushNotification notification = new BasicCustomPushNotification();
        notification.setServerOptionFirst(true);
        notification.setBuildWhenAppInForeground(false);

        boolean res = CustomNotificationBuilder.getInstance().setCustomNotification(ConstAliYun.PUSH_APP_IN, notification);


        Log.i("aliyunPush", "init pushService===============" +"res:"+res);

        Log.i("aliyunPush", "init pushService===============" +"res:"+res+"    ===声音路径："+getSoundPath());

    }






    private String getSoundPath() {

        String path = "";
        int assignSoundId = getResources().getIdentifier("new_order_alert", "raw", VersionUtil.getPackageName());
         path = "android.resource://" + getPackageName() + "/" + assignSoundId;


        return path;

    }


    @Override
    public void onSuccess(String s) {
        Log.i("aliyunPush", "init cloudchannel success "+s+" "+pushService.getDeviceId());


        if(null!= pushService) {

            dealWithUmergToken(pushService.getDeviceId());


        }

    }

    @Override
    public void onFailed(String s, String s1) {
        Log.i("aliyunPush", "init cloudchannel failed -- errorcode:" + s + " -- errorMessage:" + s1);
    }











    /**
     * 初始化网路请求框架
     */
    private void initNoHttp() {

        Logger.setDebug(true); // 开启NoHttp调试模式。
        Logger.setTag("NoHttpMyTest"); // 设置NoHttp打印Log的TAG。
        /**
         * 1.超时配置
         * 2.配置缓存 （实际就是数据的本地存储）
         * 3.配置Cookie
         * 4.配置网络层 （这大概是http网络层的异常处理）
         *
         */
        NoHttp.Config config = new NoHttp.Config();
        config.setConnectTimeout(30 * 1000); // 全局连接超时时间，单位毫秒。
        config.setReadTimeout(30 * 1000); // 全局服务器响应超时时间，单位毫秒。
        config.setNetworkExecutor(new OkHttpNetworkExecutor()); //2.配置网络层

        NoHttp.initialize(mInstance, config);


    }


    /**
     * 初始化ImageLoader
     */
    private void initImageLoader(Context context) {


        String cachePath = this.getCacheDir().getAbsolutePath() + File.separator + "imageCache/";

        File cacheDir = new File(cachePath);

        ImageLoaderConfiguration config = new
                ImageLoaderConfiguration.Builder(
                context).threadPriority(Thread.NORM_PRIORITY - 2)// 设置当前线程优先级
                .denyCacheImageMultipleSizesInMemory() // 缓存显示不同 大小的同一张图片
                .diskCacheSize(50 * 1024 * 1024) // 本地Sd卡的缓存最大值
                .diskCache(new UnlimitedDiscCache(cacheDir))// sd卡缓存
                .memoryCache(new WeakMemoryCache()) // 内存缓存
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();

        ImgUtil.getInstance().getImageLoader().init(config);


    }

    public static AiDaiHuoApplication getInstance() {

        if (mInstance == null) {

            synchronized (AiDaiHuoApplication.class) {

                if (mInstance == null) {

                    mInstance = new AiDaiHuoApplication();

                }

            }

        }
        return mInstance;

    }


//    @Override
//    public void onSuccess(String s) {
//
//        Log.i("umerg", "device_token:"+s);
//
//
//        dealWithUmergToken(s);
//
//
//    }

    private void dealWithUmergToken(String s){

        if(!TextUtils.isEmpty(s)) {

            String token = SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_ALIYUN_PUSH_DEVICE_TOKEN,ConstSp.SP_VALUE.DEFAULT_STRING);

            if(TextUtils.isEmpty(token)) {

                SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_ALIYUN_PUSH_DEVICE_TOKEN, s);

            }else {


                if(!token.equals(s)) {


                    SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_ALIYUN_PUSH_DEVICE_TOKEN, s);

                }




            }


        }

    }


//    @Override
//    public void onFailure(String s, String s1) {
//        Log.i("umerg", "获取deviceToken异常：" + s + " s1" + s1);
//    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base); MultiDex.install(this);
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        // 每个Activity创建都会调用这个方法
        Log.i("AdhApplicationActivty", activity.getLocalClassName() + " onActivityCreated...");
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        // 每个Activity销毁都会调用这个方法
        Log.i("AdhApplicationActivty", activity.getLocalClassName() + " onActivityDestroyed...");
    }




    Thread.UncaughtExceptionHandler uncaughtExceptionHandler = new Thread.UncaughtExceptionHandler() {
        @Override
        public void uncaughtException(Thread t, Throwable throwable) {
            try {
                Log.e(TAG, Log.getStackTraceString(throwable));
                throwable.printStackTrace();
                // 指定输出日志的路径
                File errerLog = new File(getSdcardPackagePath() + "/adhErrorLog.log");
                FileWriter fw = new FileWriter(errerLog, true);
                fw.write("\n\n" + SimpleDateFormat.getInstance().format(new Date()) + "\n");
                PrintWriter pw = new PrintWriter(fw);
                throwable.printStackTrace(pw);
                fw.close();
                pw.close();

                // 捕获未捕获的异常,杀掉进程闪退
                catheUnhanldeCatch();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };



    /**
     * 杀死当前进程
     */
    private void catheUnhanldeCatch() {
        // 得到当前应用的进程ID
        int pid = android.os.Process.myPid();
        // 杀死进程 闪退
        android.os.Process.killProcess(pid);
    }



    private String getSdcardPackagePath() {
        // 判断sdcard是否存在
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            // 获取根目录
            return Environment.getExternalStorageDirectory().getPath();
        } else {
            return getFilesDir().getPath();
        }
    }





}
