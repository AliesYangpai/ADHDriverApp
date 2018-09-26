package com.adhdriver.work.location;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;

import com.adhdriver.work.AiDaiHuoApplication;

import com.adhdriver.work.callback.NetWorkStateChangedCallBack;
import com.adhdriver.work.callback.udp.ADHUdpCallBackListener;
import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.constant.ConstLog;
import com.adhdriver.work.constant.ConstSp;

import com.adhdriver.work.constant.ConstUdp;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.UdpEntity;
import com.adhdriver.work.function.FunctionAmap;
import com.adhdriver.work.function.FunctionUDP;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.location.amap.AmapOption;
import com.adhdriver.work.logic.LogicAmap;
import com.adhdriver.work.method.IUpLoadLocation;
import com.adhdriver.work.method.impl.IUpLoadLocationImpl;
import com.adhdriver.work.receiver.NetWorkBroadCastReciver;
import com.adhdriver.work.udp.ADHUdpHelper;
import com.adhdriver.work.ui.activity.kick.KickOutActivity;
import com.adhdriver.work.utils.DoubleUtil;
import com.adhdriver.work.utils.SpUtil;
import com.adhdriver.work.utils.ToastUtil;
import com.adhdriver.work.utils.weaklock.ADHWeakLock;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import okhttp3.WebSocketListener;


/**
 * Created by Administrator on 2017/5/11.
 * 类描述   持续定位的类（单例）
 * 版本
 */

public class KeepLocation extends WebSocketListener implements
        AMapLocationListener,
        OnDataBackListener,
        ADHUdpCallBackListener ,
        NetWorkStateChangedCallBack {


    private static volatile KeepLocation mInstance;
    private AMapLocationClient locationClient = null;
    private long tempCount = 0;

    private long tempUdpCount = 0;


    /**
     * weakLock电源锁
     *
     * @return
     */
    private ADHWeakLock adhWeakLock;

    /**
     * 地图配置
     *
     * @return
     */

    private AmapOption amapOption;
    private LogicAmap logicAmap;
    private IUpLoadLocation iUpLoadLocation;
    private FunctionAmap functionAmap;

    private ParseSerilizable parseSerilizable;


    private ADHUdpHelper adhUdpHelper;

    private FunctionUDP functionUDP;

    private NetWorkBroadCastReciver netWorkBroadCastReciver;
    private IntentFilter filter;

    public ADHUdpHelper getAdhUdpHelper() {
        return adhUdpHelper;
    }

    public static KeepLocation getInstance() {

        if (null == mInstance) {
            synchronized (KeepLocation.class) {
                if (null == mInstance) {
                    mInstance = new KeepLocation();
                }
            }
        }
        return mInstance;
    }


    public KeepLocation() {

        amapOption = new AmapOption(); //获取定位监听
        adhWeakLock = new ADHWeakLock(); //获取电源锁
        logicAmap = new LogicAmap();   //创建逻辑类
        functionAmap = new FunctionAmap(); //计算类
        iUpLoadLocation = new IUpLoadLocationImpl(); //上传经纬度的方法类
        parseSerilizable = new ParseSerilizable();
        locationClient = new AMapLocationClient(AiDaiHuoApplication.getInstance().getApplicationContext());
        locationClient.setLocationOption(amapOption.getIntervalOption());
        locationClient.setLocationListener(this);

        adhUdpHelper = new ADHUdpHelper(ConstUdp.PORT,ConstUdp.HOST);
        adhUdpHelper.setAdhUdpCallBackListener(this);

        functionUDP = new FunctionUDP();


        netWorkBroadCastReciver = new NetWorkBroadCastReciver();

        netWorkBroadCastReciver.setNetWorkStateChangedCallBack(this);

        IntentFilter filter=new IntentFilter();
        //添加动作，监听网络

        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

        AiDaiHuoApplication.getInstance().registerReceiver(netWorkBroadCastReciver, filter);
    }



    /**
     * 默认收车
     * @return
     */
    public boolean isDipatch() {

        return SpUtil.getInstance().getBoolenValue(ConstSp.SP_KEY_VEHICLE_DIPATCH,ConstSp.SP_VALUE.DEFAULT_BOOLEAN);
    }


    /**
     * 司机上传经纬度
     *
     * @param
     * @param aMapLocation
     */
    private void doUpLoadLocation(AMapLocation aMapLocation) {


        /**
         * 上传经纬度相关
         */

        double longtitudeLocalSave = ConstLocalData.DEFAULT_BEIJING_LONGITUDE;
        double latitudeLocalSave = ConstLocalData.DEFAULT_BEIJING_LATITUDE;
        String areaCodeLocalSave = ConstLocalData.DEFAULT_BEIJING_ZONE_CODE;

        if (logicAmap.isNotNullAmapLocation(aMapLocation)) {

            if (logicAmap.is0Lat(aMapLocation.getLongitude()) &&
                    logicAmap.isNot0Lng(aMapLocation.getLatitude()) &&
                    logicAmap.isNotNullForAdCode(aMapLocation.getAdCode())) {


                longtitudeLocalSave = aMapLocation.getLongitude();
                latitudeLocalSave = aMapLocation.getLatitude();
                areaCodeLocalSave = aMapLocation.getAdCode();

                Log.i(ConstLog.SERVICE_TAG, "=========经度：" + aMapLocation.getLongitude() + " 纬度：" + aMapLocation.getLatitude() +
                        "=======处理后的经度：" + DoubleUtil.getDecima6(aMapLocation.getLongitude()) +
                        "=======处理后的纬度" + DoubleUtil.getDecima6(aMapLocation.getLatitude()) + " 定位code：" + aMapLocation.getErrorCode() + " tempCount = " + tempCount + " 城市code：" + aMapLocation.getCityCode() + " Ad_Code:" + aMapLocation.getAdCode());


                iUpLoadLocation.doUpLoadLocation(HttpConst.URL.HEART_LOCATION,
                        DoubleUtil.getDecima6(aMapLocation.getLongitude()),
                        DoubleUtil.getDecima6(aMapLocation.getLatitude()),
                        aMapLocation.getAdCode(),
                        SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_LOGON_DRIVER_VEHICLE_ID, ConstSp.SP_VALUE.DEFAULT_STRING),
                        SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_LOGON_DRIVER_CATEGORY, ConstSp.SP_VALUE.DEFAULT_STRING), this);


            } else {

                Log.i(ConstLog.SERVICE_TAG, "地理位置回传为null，上传北京的经纬度=========");

                iUpLoadLocation.doUpLoadLocation(HttpConst.URL.HEART_LOCATION,
                        DoubleUtil.getDecima6(ConstLocalData.DEFAULT_BEIJING_LONGITUDE),
                        DoubleUtil.getDecima6(ConstLocalData.DEFAULT_BEIJING_LATITUDE),
                        ConstLocalData.DEFAULT_BEIJING_ZONE_CODE,
                        SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_LOGON_DRIVER_VEHICLE_ID, ConstSp.SP_VALUE.DEFAULT_STRING),
                        SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_LOGON_DRIVER_CATEGORY, ConstSp.SP_VALUE.DEFAULT_STRING), this);

            }

        } else {
            Log.i(ConstLog.SERVICE_TAG, "地理位置回传为null，上传北京的经纬度=========");
            iUpLoadLocation.doUpLoadLocation(HttpConst.URL.HEART_LOCATION,
                    DoubleUtil.getDecima6(ConstLocalData.DEFAULT_BEIJING_LONGITUDE),
                    DoubleUtil.getDecima6(ConstLocalData.DEFAULT_BEIJING_LATITUDE),
                    ConstLocalData.DEFAULT_BEIJING_ZONE_CODE,
                    SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_LOGON_DRIVER_VEHICLE_ID, ConstSp.SP_VALUE.DEFAULT_STRING),
                    SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_LOGON_DRIVER_CATEGORY, ConstSp.SP_VALUE.DEFAULT_STRING), this);
        }


        SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_LONGITUDE_FOR_VEHICLE_CAHNEG, String.valueOf(longtitudeLocalSave));
        SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_LATITUDE_FOR_VEHICLE_CAHNEG, String.valueOf(latitudeLocalSave));
        SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_AREA_CODE_FOR_VEHICLE_CAHNEG, String.valueOf(areaCodeLocalSave));

    }


    /**
     * 司机上传区域编码
     *
     * @param aMapLocation
     */
    private void doUpLoadArea(AMapLocation aMapLocation) {


        if (logicAmap.isNotNullAmapLocation(aMapLocation) &&
                logicAmap.isNotNullForAdCode(aMapLocation.getAdCode())) {

            iUpLoadLocation.doUpLoadDriverArea(HttpConst.URL.DRIVER_REGION + HttpConst.SEPARATOR + aMapLocation.getAdCode(), this);

        }


    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {


        adhWeakLock.doAcquireWakeLock();



        adhUdpHelper.doSocketReceiveData();  //获取udp数据



       long timeUdpHeart =  functionAmap.calculateUdpHeartTimeSpan(tempUdpCount);


        if(timeUdpHeart == 0) {



            adhUdpHelper.doSocketHeart(SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_USER_ID,ConstSp.SP_VALUE.DEFAULT_STRING));
        }




        if(isDipatch()) {


            /**
             * 上传区域编码
             * 这里进行代码测试
             */
            if (!SpUtil.getInstance().getBoolenValue(ConstSp.SP_KEY_HAS_AREA_OR_NOT, ConstSp.SP_VALUE.DEFAULT_BOOLEAN)) {

                doUpLoadArea(aMapLocation);

            }


            long times = functionAmap.calculateTimeSpan(tempCount);

            /**
             * 上传经纬度相关
             */
            if (times == 0) {

                doUpLoadLocation(aMapLocation);

            }
            tempCount++;
        }



        tempUdpCount+=5;

    }


    public AMapLocation getLastPosition() {

        AMapLocation lastKnownLocation = null;

        if (null != locationClient) {

            lastKnownLocation = locationClient.getLastKnownLocation();
        }

        return lastKnownLocation;

    }


    /**
     * 停止并销毁定位
     */
    public void destroylocation() {

        if (null != locationClient && locationClient.isStarted()) {



            if(null != adhWeakLock) {

                adhWeakLock.doReleaseWakeLock();
                adhWeakLock = null;
            }

            locationClient.stopLocation();
            locationClient.onDestroy();
            adhUdpHelper.doSocketClose();
            locationClient = null;

            tempCount = 0;
            tempUdpCount = 0;
            AiDaiHuoApplication.getInstance().unregisterReceiver(netWorkBroadCastReciver);

            netWorkBroadCastReciver = null;
        }


    }


    /**
     * stop定位
     */


    public void stoplocation() {

        if (null != locationClient) {

            locationClient.stopLocation();

            locationClient.onDestroy();

            locationClient = null;
        }

    }

    /**
     * 开始定位
     */
    public void starLocation() {

        if (null != locationClient) {

            if (!locationClient.isStarted()) {

                locationClient.startLocation();

            }

        } else {

            locationClient = new AMapLocationClient(AiDaiHuoApplication.getInstance().getApplicationContext());

            locationClient.setLocationOption(amapOption.getIntervalOption());

            locationClient.setLocationListener(this);

            locationClient.startLocation();

        }

    }


    @Override
    public void onStart() {
        /**
         * 不必处理
         */
    }

    @Override
    public void onSuccess(String data) {

        SpUtil.getInstance().saveBooleanTosp(ConstSp.SP_KEY_HAS_AREA_OR_NOT, ConstSp.SP_VALUE.IS_HAD_AREA);

    }

    @Override
    public void onFail(int code, String data) {

        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
        if (null != errorEntity) {
            ToastUtil.showMsg(AiDaiHuoApplication.getInstance(), errorEntity.getError_message());
        } else {
            ToastUtil.showMsg(AiDaiHuoApplication.getInstance(), ConstError.PARSE_ERROR_MSG);

        }

    }

    @Override
    public void onFinish() {
        /**
         * 不必处理
         */
    }


    /**
     * dup自己编写的回调
     */

    @Override
    public void onConnect() {

        Log.i("udpSocket","链接成功==============");

    }


    @Override
    public void onSendData(String param) {

        Log.i("udpSocket","发送的数据==============："+param);

    }

    @Override
    public void onHeart(String param) {

        Log.i("udpSocket","心跳数据==============："+param);

    }

    @Override
    public void onClose() {


        Log.i("udpSocket","链接关闭==============");


    }

    @Override
    public void onReceiveData(String data) {




        Log.i("udpSocket","接收到的数据=============="+data);

        UdpEntity udpEntity = parseSerilizable.getParseToObj(data,UdpEntity.class);


        if(null == udpEntity) {

            return;
        }


        switch (udpEntity.getType()) {

            case ConstUdp.TYPE_KICK_OUT:

                Intent intent = new Intent(AiDaiHuoApplication.getInstance(), KickOutActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(ConstIntent.BundleKEY.SOCKET_KICK, udpEntity);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtras(bundle);
                AiDaiHuoApplication.getInstance().startActivity(intent);



                break;


            case ConstUdp.TYPE_RECONNECT:


                adhUdpHelper.doSocketConnect(functionUDP.getUdpConnectJsonString());


                break;


        }





    }

    @Override
    public void onReConnect() {


        Log.i("udpSocket","启动重连Socket=============="+functionUDP.getUdpConnectJsonString());

        adhUdpHelper.doSocketConnect(functionUDP.getUdpConnectJsonString());


    }


    @Override
    public void onNetChange(int netWorkState) {

      boolean result = SpUtil.getInstance().getBoolenValue(ConstSp.SP_KEY_IS_LOGIN_OR_NOT,ConstSp.SP_VALUE.IS_LOGIN_DEFAULT);

        switch (netWorkState) {

            case -1:


                Log.i("udpSocket","切换到无网络");
                adhUdpHelper.doSocketClose();
                break;

            case 0:

                Log.i("udpSocket","切换到4G");


                if(result) {

                    if(null != adhUdpHelper.getSocket()) {

                        adhUdpHelper.doSocketClose();
                        adhUdpHelper.doSocketConnect(functionUDP.getUdpConnectJsonString());

                    }else {

                        adhUdpHelper.doSocketConnect(functionUDP.getUdpConnectJsonString());
                    }

                }


                break;

            case 1:
                Log.i("udpSocket","切换到WIFI");


                if(result) {

                    if(null != adhUdpHelper.getSocket()) {

                        adhUdpHelper.doSocketClose();
                        adhUdpHelper.doSocketConnect(functionUDP.getUdpConnectJsonString());

                    }else {

                        adhUdpHelper.doSocketConnect(functionUDP.getUdpConnectJsonString());
                    }

                }
                break;
        }

    }
}
