package com.adhdriver.work.ui.activity.driver;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.VehicleChangeAlertDialogCallBack;
import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.entity.driver.vehicle.DriverVehicle;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.presenter.driver.PresenterDriverVehicles;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.adapter.VehiclesAdapter;
import com.adhdriver.work.ui.iview.driver.IVehiclesView;
import com.adhdriver.work.utils.DoubleUtil;
import com.adhdriver.work.utils.SpUtil;
import com.adhdriver.work.utils.ToastUtil;
import com.adhdriver.work.ui.widget.dialog.VehicleChangeAlertDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

/**
 * 司机车辆列表
 */
public class VehiclesActivity extends BaseActivity<IVehiclesView, PresenterDriverVehicles> implements
        IVehiclesView,
        View.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.OnItemChildClickListener,
        BaseQuickAdapter.RequestLoadMoreListener,
        VehicleChangeAlertDialogCallBack {


    private PresenterDriverVehicles presenterDriverVehicles;


    /**
     * title
     *
     * @param savedInstanceState
     */

    private ImageView iv_common_back;
    private TextView tv_common_title;


    private SwipeRefreshLayout srefresh_layout;
    private RecyclerView rv_vehicles;
    private RecyclerView.LayoutManager layoutManager;
    private VehiclesAdapter vehiclesAdapter;

    /**
     * 底部
     */


    private LinearLayout ll_bottom;

    /**
     * 数据相关
     */
    private int currentSize = ConstLocalData.DEFAULT_INCREMENT_SIZE;//当前显示的数量 用于下拉差量更新    默认6 //加载刷新
    private int currentIndex = ConstLocalData.DEFUALT_LIST_INDEX;//用于上拉加载更多；       默认1       //加载刷新


    /**
     * dialog相关
     *
     * @param savedInstanceState
     */


    private VehicleChangeAlertDialog vehicleChangeAlertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles);


        presenterDriverVehicles.doGetVehicles(
                HttpConst.URL.DRIVER_VEHICLES,
                currentSize,
                ConstLocalData.DEFUALT_LIST_INDEX);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {

            case ConstIntent.ResponseCode.GO_TO_ADD_NEW_VEHICLE:  //添加车辆的成功返回

                presenterDriverVehicles.doGetVehicles(
                        HttpConst.URL.DRIVER_VEHICLES,
                        currentSize,
                        ConstLocalData.DEFUALT_LIST_INDEX);

                break;
//
            case ConstIntent.ResponseCode.GO_TO_SHOW_VEHICLE_DETAIL_DEL:  //删除车辆的成功返回

                presenterDriverVehicles.doGetVehicles(
                        HttpConst.URL.DRIVER_VEHICLES,
                        currentSize,
                        ConstLocalData.DEFUALT_LIST_INDEX);

                break;


            case ConstIntent.ResponseCode.GO_TO_SHOW_JUST_BUCK:  //详情界面点击返回或backpress

                presenterDriverVehicles.doGetVehicles(
                        HttpConst.URL.DRIVER_VEHICLES,
                        currentSize,
                        ConstLocalData.DEFUALT_LIST_INDEX);

                break;


//            case ConstIntent.ResponseCode.GO_TO_SHOW_CHANGE_VEHICLE_SUCCESS:  //更换车辆成功
//
//                getVehicleListInfoFromServer();
//
//                break;


        }
    }

    @Override
    protected PresenterDriverVehicles creatPresenter() {
        if (null == presenterDriverVehicles) {
            presenterDriverVehicles = new PresenterDriverVehicles(this);
        }
        return presenterDriverVehicles;
    }

    @Override
    protected void initViews() {
        /**
         * title
         * @param savedInstanceState
         */
        iv_common_back = findADHViewById(R.id.iv_common_back);
        tv_common_title = findADHViewById(R.id.tv_common_title);
        tv_common_title.setText(getString(R.string.my_vehicles));

        srefresh_layout = findADHViewById(R.id.srefresh_layout);
        srefresh_layout.setColorSchemeColors(getSwipeRefreshColor());

        rv_vehicles = findADHViewById(R.id.rv_vehicles);
        layoutManager = new LinearLayoutManager(this);


        vehiclesAdapter = new VehiclesAdapter(R.layout.item_vehicles);
        rv_vehicles.setLayoutManager(layoutManager);
        rv_vehicles.setAdapter(vehiclesAdapter);


        ll_bottom = findADHViewById(R.id.ll_bottom);
    }

    @Override
    protected void initListener() {


        iv_common_back.setOnClickListener(this);


        srefresh_layout.setOnRefreshListener(this);
        vehiclesAdapter.setOnLoadMoreListener(this, rv_vehicles);
        vehiclesAdapter.setOnItemChildClickListener(this);

        //默认第一次加载会进入回调，如果不需要可以配置
        vehiclesAdapter.disableLoadMoreIfNotFullPage(rv_vehicles);

        ll_bottom.setOnClickListener(this);

    }

    @Override
    protected void processIntent() {

    }

    @Override
    public void showLoadingDialog() {
        showLoadDialog();
    }

    @Override
    public void dismissLoadingDialog() {
        dismissLoadDialog();
    }

    @Override
    public void onDataBackFail(int code, String errorMsg) {

        ToastUtil.showMsg(getApplicationContext(), errorMsg);
    }

    @Override
    public void onDataBackFailInLoadMore(int code, String errorMsg) {
        vehiclesAdapter.loadMoreFail();
        ToastUtil.showMsg(getApplicationContext(), errorMsg);
    }

    @Override
    public void dismissFreshLoading() {
        srefresh_layout.setRefreshing(false);
    }

    @Override
    public void onDataBackSuccessForVehicles(List<DriverVehicle> list) {
        vehiclesAdapter.setNewData(list);
    }

    @Override
    public void onDataBackSuccessForVehiclesInFresh(List<DriverVehicle> list) {
        vehiclesAdapter.setNewData(list);
    }

    @Override
    public void onDataBackSuccessForVehiclesInLoadMore(List<DriverVehicle> list) {
        if (null != list && list.size() > 0) {

            vehiclesAdapter.addData(list);
            vehiclesAdapter.loadMoreComplete();
            currentSize += ConstLocalData.DEFAULT_INCREMENT_SIZE;   //这是设置给 下拉刷新用的//加载刷新
            currentIndex += ConstLocalData.DEFUALT_LIST_INDEX;

        } else {
            vehiclesAdapter.loadMoreEnd();
        }
    }

    @Override
    public void onDataBackSuccessForSwitchAndGetToken() {
        presenterDriverVehicles.doGetUserInfo(HttpConst.URL.GET_USER_INFO);


    }

    @Override
    public void onDataBackSuccessForUpLoadNewVehicleLocation() {

        ToastUtil.showMsg(getApplicationContext(), "车辆切换成功");

    }

    @Override
    public void onDataBackSuccessForGetUserInfo() {


        presenterDriverVehicles.doGetVehicles(
                HttpConst.URL.DRIVER_VEHICLES,
                currentSize,
                ConstLocalData.DEFUALT_LIST_INDEX);


        String longitude = SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_LONGITUDE_FOR_VEHICLE_CAHNEG, ConstSp.SP_VALUE.DEFAULT_STRING);
        String latitude = SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_LATITUDE_FOR_VEHICLE_CAHNEG, ConstSp.SP_VALUE.DEFAULT_STRING);
        String area_code = SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_AREA_CODE_FOR_VEHICLE_CAHNEG, ConstSp.SP_VALUE.DEFAULT_STRING);
        String driver_vehicle_id = SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_LOGON_DRIVER_VEHICLE_ID, ConstSp.SP_VALUE.DEFAULT_STRING);
        String vehicle_catogory = SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_LOGON_DRIVER_CATEGORY, ConstSp.SP_VALUE.DEFAULT_STRING);

        presenterDriverVehicles.doUpLoadNewVehicleLocation(
                HttpConst.URL.HEART_LOCATION,
                DoubleUtil.getDecima6(Double.valueOf(longitude)),
                DoubleUtil.getDecima6(Double.valueOf(latitude)),
                area_code,
                driver_vehicle_id,
                vehicle_catogory);


    }

    @Override
    public void doShowSwitchAlertDialog(DriverVehicle driverVehicle) {


        if (null == vehicleChangeAlertDialog) {
            vehicleChangeAlertDialog = new VehicleChangeAlertDialog(this);
            vehicleChangeAlertDialog.setDriverVehicle(driverVehicle);
            vehicleChangeAlertDialog.setVehicleChangeAlertDialogCallBack(this);
        } else {
            vehicleChangeAlertDialog.doUpdateData(driverVehicle);
        }
        vehicleChangeAlertDialog.show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_common_back:
                dofinishItself();
                break;


            case R.id.ll_bottom:
                ToastUtil.showMsg(getApplicationContext(), "增加车辆");

                Intent intent = new Intent(this, VehicleAddActivity.class);
                startActivityForResult(intent, ConstIntent.RequestCode.GO_TO_ADD_NEW_VEHICLE);

                break;
        }
    }

    @Override
    public void onRefresh() {

        presenterDriverVehicles.doGetVehiclesInFresh(
                HttpConst.URL.DRIVER_VEHICLES,
                currentSize,
                ConstLocalData.DEFUALT_LIST_INDEX);

    }


    @Override
    public void onLoadMoreRequested() {


        int tempIndex = currentIndex + ConstLocalData.DEFUALT_LIST_INDEX;

        presenterDriverVehicles.doGetVehiclesInLoadMore(
                HttpConst.URL.DRIVER_VEHICLES,
                ConstLocalData.DEFAULT_INCREMENT_SIZE,
                tempIndex);


        Log.i("onLoadMore", "=============ChildFragmentFullLoad=onLoadMoreRequested");

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {


        DriverVehicle driverVehicle = (DriverVehicle) adapter.getData().get(position);
        switch (view.getId()) {

            case R.id.rl_top:


                Bundle bundle = new Bundle();
                bundle.putString(ConstIntent.BundleKEY.SHOW_VEHICLE_DETIAL, String.valueOf(driverVehicle.getDriver_vehicle_id()));
                Intent intent = new Intent(this, VehicleDetialActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, ConstIntent.RequestCode.GO_TO_SHOW_VEHICLE_DETAIL);


                break;

            case R.id.ll_vehicle_pass:

                presenterDriverVehicles.doShowSwitchVehicleAlertOrNot(driverVehicle);

                break;
        }
    }


    @Override
    public void sureChangeToThisVehicleCallBack(int driver_vehicle_id) {

        presenterDriverVehicles.doSwitchVehicle(HttpConst.URL.SWITCH_VEHICLE + HttpConst.SEPARATOR + driver_vehicle_id);
    }
}
