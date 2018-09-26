package com.adhdriver.work.ui.activity.driver;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.entity.driver.refuel.RefuleCoordinate;
import com.adhdriver.work.presenter.driver.PresenterDriverRefulNaviagte;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.iview.driver.IRefuelNavigateView;
import com.adhdriver.work.utils.ToastUtil;
import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.AMapNaviListener;
import com.amap.api.navi.AMapNaviView;
import com.amap.api.navi.AMapNaviViewListener;
import com.amap.api.navi.AMapNaviViewOptions;
import com.amap.api.navi.enums.NaviType;
import com.amap.api.navi.model.AMapLaneInfo;
import com.amap.api.navi.model.AMapNaviCameraInfo;
import com.amap.api.navi.model.AMapNaviCross;
import com.amap.api.navi.model.AMapNaviInfo;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.navi.model.AMapNaviTrafficFacilityInfo;
import com.amap.api.navi.model.AMapServiceAreaInfo;
import com.amap.api.navi.model.AimLessModeCongestionInfo;
import com.amap.api.navi.model.AimLessModeStat;
import com.amap.api.navi.model.NaviInfo;
import com.amap.api.navi.model.NaviLatLng;
import com.amap.api.navi.view.ZoomButtonView;
import com.autonavi.tbt.TrafficFacilityInfo;

import java.util.ArrayList;
import java.util.List;

public class RefuelNavigateActivity extends BaseActivity<IRefuelNavigateView, PresenterDriverRefulNaviagte>
        implements
        View.OnClickListener,
        AMapNaviListener,
        AMapNaviViewListener,
        CompoundButton.OnCheckedChangeListener ,
        IRefuelNavigateView{


    private PresenterDriverRefulNaviagte presenterDriverRefulNaviagte;

    /**
     * titile
     */
    private ImageView iv_common_back;
    private TextView tv_common_title;
    private ImageView iv_common_search;


    /**
     * 地图相关
     *
     * @param savedInstanceState
     */
    private AMapNaviView mAMapNaviView;
    private AMapNavi mAMapNavi;


    private NaviLatLng mEndLatlng;
    private NaviLatLng mStartLatlng;
    private List<NaviLatLng> sList = new ArrayList<NaviLatLng>();
    private List<NaviLatLng> eList = new ArrayList<NaviLatLng>();
    private List<NaviLatLng> mWayPointList;


    /**
     * 放大缩小
     */
    private ZoomButtonView mZoomButtonView;


    /**
     * 全览和继续导航
     */
    private CheckBox cb_1;


    /**
     * 数据相关
     *
     * @param savedInstanceState
     */


    private RefuleCoordinate currentCoordinate;


    private int strategy;


    private void setDataToMap() {



        if(null != currentCoordinate) {


            mStartLatlng = new NaviLatLng(Double.valueOf(currentCoordinate.getStartLat()), Double.valueOf(currentCoordinate.getStartLng()));
            mEndLatlng = new NaviLatLng(Double.valueOf(currentCoordinate.getEndLat()), Double.valueOf(currentCoordinate.getEndLng()));

        }



        mAMapNavi.setEmulatorNaviSpeed(75);
        sList.add(mStartLatlng);
        eList.add(mEndLatlng);


        /**
         * 原来的
         */
        AMapNaviViewOptions options = new AMapNaviViewOptions();
        options.setLayoutVisible(false);
        options.setTilt(0);
        options.setTrafficBarEnabled(false); //光柱不显示
        options.setCrossDisplayShow(false);    //设置不显示路口放大
        options.setScreenAlwaysBright( true);//设置导航状态下屏幕是否一直开启。

        /**
         * 新修改
         */
//        AMapNaviViewOptions options = new AMapNaviViewOptions();
//        options.setLayoutVisible(true);
//        options.setTilt(0);
//        options.setTrafficBarEnabled(true); //光柱不显示
//        options.setCrossDisplayShow(true);    //设置显示路口放大
//        options.setScreenAlwaysBright( true);//设置导航状态下屏幕是否一直开启。
//        options.setReCalculateRouteForYaw(true);//偏航时是否重新计算路径(计算路径需要联网）。
//        options.setSettingMenuEnabled(false);






//        options.setLayoutVisible(true);
//        options.setTilt(1);
//        options.setTrafficBarEnabled(false); //光柱不显示
//        options.setCrossDisplayShow(true);    //设置显示路口放大
//        options.setCameraInfoUpdateEnabled(true);//设置摄像头播报是否打开（只适用于驾车导航）。
//        options.setReCalculateRouteForYaw(true);//偏航时是否重新计算路径(计算路径需要联网）。
//        options.setScreenAlwaysBright( true);//设置导航状态下屏幕是否一直开启。


        mAMapNaviView.setViewOptions(options);
        /**
         * 加入放大缩小
         */
        mAMapNaviView.setLazyZoomButtonView(mZoomButtonView);


        /**
         * 开始计算了路线
         */
        mAMapNavi.calculateDriveRoute(sList, eList, mWayPointList, strategy);


    }


    @Override
    public Resources getResources() {
        return getBaseContext().getResources();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refuel_navigate2);
        mAMapNaviView.onCreate(savedInstanceState);
    }

    @Override
    protected PresenterDriverRefulNaviagte creatPresenter() {
        if(null == presenterDriverRefulNaviagte) {
            presenterDriverRefulNaviagte= new PresenterDriverRefulNaviagte(this);
        }
        return presenterDriverRefulNaviagte;
    }

    @Override
    protected void initViews() {

        /**
         * titile
         * @param
         */

        iv_common_back = (ImageView) findViewById(R.id.iv_common_back);
        tv_common_title = (TextView) findViewById(R.id.tv_common_title);
        iv_common_search = (ImageView) findViewById(R.id.iv_common_search);
        iv_common_search.setVisibility(View.GONE);
        tv_common_title.setText(this.getString(R.string.refuel_to_petrol_station));


        /**
         * 地图相关
         */

        mAMapNaviView = (AMapNaviView) findViewById(R.id.navi_view);
        mAMapNavi = AMapNavi.getInstance(getApplicationContext());

        /**
         * 放大缩小
         */

        mZoomButtonView = (ZoomButtonView) findViewById(R.id.myZoomButtonView);
        cb_1 = (CheckBox) findViewById(R.id.cb_1);
    }

    @Override
    protected void initListener() {


        iv_common_back.setOnClickListener(this);
        iv_common_search.setOnClickListener(this);


        mAMapNavi.addAMapNaviListener(this);
        mAMapNaviView.setAMapNaviViewListener(this);


        cb_1.setOnCheckedChangeListener(this);

    }

    @Override
    protected void processIntent() {

        Intent intent = this.getIntent();

        if (null != intent) {

            Bundle bundle = intent.getExtras();


            if (null != bundle) {

                currentCoordinate = (RefuleCoordinate) bundle.getSerializable(ConstIntent.BundleKEY.REFUEL_TO_NAVIGATE);
            }

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_common_back:
               dofinishItself();
                break;


        }
    }

    @Override
    protected void onResume() {
        super.onResume();



        mAMapNaviView.onResume();

    }


    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPageEnd(OrderDetailIngActivity.class.getSimpleName());
//        MobclickAgent.onPause(this);
        mAMapNaviView.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAMapNaviView.onDestroy();
        //since 1.6.0 不再在naviview destroy的时候自动执行AMapNavi.stopNavi();请自行执行
        mAMapNavi.stopNavi();
        mAMapNavi.destroy();
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {

            mAMapNaviView.displayOverview();

        } else {

            mAMapNaviView.recoverLockMode();

        }
    }

    @Override
    public void onInitNaviFailure() {

    }

    @Override
    public void onInitNaviSuccess() {

        Log.i("daohang", " 初始化成功回调" + "onInitNaviSuccess");


        try {
            //再次强调，最后一个参数为true时代表多路径，否则代表单路径
            strategy = mAMapNavi.strategyConvert(true, false, false, true, false);


        } catch (Exception e) {
            e.printStackTrace();
        }

        setDataToMap();
    }

    @Override
    public void onStartNavi(int i) {

    }

    @Override
    public void onTrafficStatusUpdate() {

    }

    @Override
    public void onLocationChange(AMapNaviLocation aMapNaviLocation) {

        NaviLatLng currentNaviLatLng = aMapNaviLocation.getCoord();


        Log.i("daohang", "导航位置==============" + "onLocationChange" + currentNaviLatLng.getLongitude() + " " + currentNaviLatLng.getLatitude());

    }

    @Override
    public void onGetNavigationText(int i, String s) {

    }

    @Override
    public void onEndEmulatorNavi() {

    }

    @Override
    public void onArriveDestination() {

    }

    @Override
    public void onCalculateRouteSuccess() {
        Log.i("daohang", "计算路线成功==============" + "onCalculateRouteSuccess");

        mAMapNavi.startNavi(NaviType.GPS);
    }

    @Override
    public void onCalculateRouteFailure(int i) {

        ToastUtil.showMsg(getApplicationContext(), "重新结算路线失败 " + i);

    }

    @Override
    public void onReCalculateRouteForYaw() {

    }

    @Override
    public void onReCalculateRouteForTrafficJam() {

    }

    @Override
    public void onArrivedWayPoint(int i) {

    }

    @Override
    public void onGpsOpenStatus(boolean b) {

    }

    @Override
    public void onNaviInfoUpdate(NaviInfo naviInfo) {

    }

    @Override
    public void onNaviInfoUpdated(AMapNaviInfo aMapNaviInfo) {

    }

    @Override
    public void updateCameraInfo(AMapNaviCameraInfo[] aMapNaviCameraInfos) {

    }

    @Override
    public void onServiceAreaUpdate(AMapServiceAreaInfo[] aMapServiceAreaInfos) {

    }

    @Override
    public void showCross(AMapNaviCross aMapNaviCross) {

    }

    @Override
    public void hideCross() {

    }

    @Override
    public void showLaneInfo(AMapLaneInfo[] aMapLaneInfos, byte[] bytes, byte[] bytes1) {

    }

    @Override
    public void hideLaneInfo() {

    }

    @Override
    public void onCalculateMultipleRoutesSuccess(int[] ints) {

    }

    @Override
    public void notifyParallelRoad(int i) {

    }

    @Override
    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo aMapNaviTrafficFacilityInfo) {

    }

    @Override
    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo[] aMapNaviTrafficFacilityInfos) {

    }

    @Override
    public void OnUpdateTrafficFacility(TrafficFacilityInfo trafficFacilityInfo) {

    }

    @Override
    public void updateAimlessModeStatistics(AimLessModeStat aimLessModeStat) {

    }

    @Override
    public void updateAimlessModeCongestionInfo(AimLessModeCongestionInfo aimLessModeCongestionInfo) {

    }

    @Override
    public void onPlayRing(int i) {

    }

    @Override
    public void onNaviSetting() {

    }

    @Override
    public void onNaviCancel() {

    }

    @Override
    public boolean onNaviBackClick() {
        return false;
    }

    @Override
    public void onNaviMapMode(int i) {

    }

    @Override
    public void onNaviTurnClick() {

    }

    @Override
    public void onNextRoadClick() {

    }

    @Override
    public void onScanViewButtonClick() {

    }

    @Override
    public void onLockMap(boolean b) {

    }

    @Override
    public void onNaviViewLoaded() {

    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }
}
