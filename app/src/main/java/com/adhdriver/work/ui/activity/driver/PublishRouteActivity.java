package com.adhdriver.work.ui.activity.driver;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.entity.driver.temp.PublishRoute;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.presenter.driver.PresenterDriverPublishRoute;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.iview.driver.IPublishRouteView;
import com.adhdriver.work.utils.StringUtil;
import com.adhdriver.work.utils.TimeUtil;
import com.adhdriver.work.utils.ToastUtil;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.feezu.liuli.timeselector.TimeSelector;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cn.addapp.pickers.entity.City;
import cn.addapp.pickers.entity.County;
import cn.addapp.pickers.entity.Province;
import cn.addapp.pickers.listeners.OnLinkageListener;
import cn.addapp.pickers.picker.AddressPicker;
import cn.addapp.pickers.util.ConvertUtils;

public class PublishRouteActivity extends BaseActivity<IPublishRouteView, PresenterDriverPublishRoute> implements
        IPublishRouteView,
        View.OnClickListener,
        GeocodeSearch.OnGeocodeSearchListener,
        TimeSelector.ResultHandler,
        OnLinkageListener {


    private PresenterDriverPublishRoute presenterDriverPublishRoute;


    /**
     * title
     */
    private ImageView iv_common_back;
    private TextView tv_common_title;
    private ImageView iv_common_search;


    /**
     * 中间
     *
     * @param savedInstanceState
     */


    private TextView tv_current_time;
    private TextView tv_set_start_place;
    private TextView tv_set_end_place;


    /**
     * 底部发布按钮
     *
     * @param savedInstanceState
     */

    private TextView tv_publish;


    /**
     * 控件相关
     */

    private AddressPicker picker;

    /**
     * 数据相关
     *
     * @param savedInstanceState
     */
    private GeocodeSearch geocoderSearch;
    private PublishRoute publishRoute = new PublishRoute();

    private void doShowPick() {

        if (null != picker) {
            picker.show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_route);
    }

    @Override
    protected PresenterDriverPublishRoute creatPresenter() {
        if (null == presenterDriverPublishRoute) {
            presenterDriverPublishRoute = new PresenterDriverPublishRoute(this);
        }
        return presenterDriverPublishRoute;
    }

    @Override
    protected void initViews() {


        /**
         * titile
         *
         */
        iv_common_back = findADHViewById(R.id.iv_common_back);
        tv_common_title = findADHViewById(R.id.tv_common_title);
        iv_common_search = findADHViewById(R.id.iv_common_search);
        tv_common_title.setText(this.getString(R.string.ride_sharing_publish));

        iv_common_search.setImageResource(R.drawable.img_publish_list_icon);


        /**
         * 中间
         * @param savedInstanceState
         */


        tv_current_time = findADHViewById(R.id.tv_current_time);
        tv_set_start_place = findADHViewById(R.id.tv_set_start_place);
        tv_set_end_place = findADHViewById(R.id.tv_set_end_place);


        /**
         * 底部发布按钮
         *
         * @param savedInstanceState
         */

        tv_publish = findADHViewById(R.id.tv_publish);


        /**
         * 数据相关
         */
        geocoderSearch = new GeocodeSearch(this);


        /**
         * 初始化AddressDialog
         */

        presenterDriverPublishRoute.doInitAddressDialog();


    }

    @Override
    protected void initListener() {


        iv_common_back.setOnClickListener(this);
        iv_common_search.setOnClickListener(this);

        tv_publish.setOnClickListener(this);

        /**
         * 设置出发时间起点和终点
         */

        tv_current_time.setOnClickListener(this);
        tv_set_start_place.setOnClickListener(this);
        tv_set_end_place.setOnClickListener(this);



        picker.setOnLinkageListener(this);
        geocoderSearch.setOnGeocodeSearchListener(this);


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
    public void doVertifyErrorNoDepartTime() {
        ToastUtil.showMsg(getApplicationContext(),getString(R.string.set_depart_time));
    }

    @Override
    public void doVertifyErrorNoDepartPlace() {
        ToastUtil.showMsg(getApplicationContext(),getString(R.string.set_depart_place));
    }

    @Override
    public void doVertifyErrorNoDestination() {
        ToastUtil.showMsg(getApplicationContext(),getString(R.string.set_destination_place));

    }

    @Override
    public void onDataBackSuccessForPublishRoute() {


        openActivityAndFinishItself(PublishSuccessActivity.class, null);      //之后还原


    }

    @Override
    public ArrayList<Province> doGetAddressList() {


        String json = null;
        ArrayList<Province> list = new ArrayList<>();
        try {
            json = ConvertUtils.toString(this.getResources().getAssets().open("city.json"));
            Type type = new TypeToken<List<Province>>() {
            }.getType();

            list = new Gson().fromJson(json, type);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void doInitAddressDialog(ArrayList<Province> list) {
        picker = new AddressPicker(this, list);
        picker.setHideProvince(false);
        picker.setCanLoop(false);
        picker.setWheelModeEnable(true);
        picker.setHideCounty(false);
    }


    @Override
    public void doInitTimeDialog() {


        TimeSelector timeSelector = new TimeSelector(this, this, TimeUtil.getDateFormateDelayHalfHour(), TimeUtil.getDateFormateDelayfiveDays());
        timeSelector.setIsLoop(false);
        timeSelector.show();

    }

    @Override
    public void doShowStartPickDialog() {

        doShowPick();
        presenterDriverPublishRoute.setStart(true);
    }

    @Override
    public void doShowEndPickDialog() {
        doShowPick();
        presenterDriverPublishRoute.setStart(false);
    }

    @Override
    public void doStartAddressPicked(String provinceName, String cityName, String countyName) {


        tv_set_start_place.setText(provinceName+StringUtil.replaceTheLongCity(cityName,countyName)+countyName);
        publishRoute.setDepart_address(provinceName+StringUtil.replaceTheLongCity(cityName,countyName)+countyName);
        showLoadDialog();

    }

    @Override
    public void doEndAddressPicked(String provinceName, String cityName, String countyName) {

        tv_set_end_place.setText(provinceName+StringUtil.replaceTheLongCity(cityName,countyName)+countyName);
        publishRoute.setDestination_address(provinceName+StringUtil.replaceTheLongCity(cityName,countyName)+countyName);
        showLoadDialog();
    }

    @Override
    public void doStartPlaceGeocodeSearchedSuccess(String latitude, String longitude, String adcode) {

        publishRoute.setDepart_coordinate_x(latitude);
        publishRoute.setDepart_coordinate_y(longitude);
        publishRoute.setDepart_zone_code(adcode);
    }

    @Override
    public void doEndPlaceGeocodeSearchedSuccess(String latitude, String longitude, String adcode) {

        publishRoute.setDestination_coordinate_x(latitude);
        publishRoute.setDestination_coordinate_y(longitude);
        publishRoute.setDestination_zone_code(adcode);
    }

    @Override
    public void onDataBackFailGeocodeNoPlace() {
        ToastUtil.showMsg(getApplicationContext(), R.string.no_location_back);
    }

    @Override
    public void onDataBackFailGeocodeCodeError(int rCode) {

        ToastUtil.showMsg(getApplicationContext(), this.getString(R.string.no_location_back)+rCode);

    }




    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.iv_common_back:

                dofinishItself();

                break;


            case R.id.tv_current_time:

                presenterDriverPublishRoute.doInitTimeDialog();

                break;

            case R.id.tv_set_start_place:

                presenterDriverPublishRoute.doShowDialogForStartPlace();

                break;

            case R.id.tv_set_end_place:

                presenterDriverPublishRoute.doShowDialogForEndPlace();

                break;


            case R.id.tv_publish:


                String currentTime = tv_current_time.getText().toString().trim();
                String startplace = tv_set_start_place.getText().toString().trim();
                String destination = tv_set_end_place.getText().toString().trim();

                presenterDriverPublishRoute.doPublishRoute(
                        HttpConst.URL.PUBLISH_HITCHHIKES,
                        currentTime,
                        startplace,
                        destination,
                        publishRoute);

                break;


            case R.id.iv_common_search:

                openActivity(PublishRoutesActivity.class, null);

                break;


        }

    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {


        dismissLoadDialog();
        presenterDriverPublishRoute.doDealWithAddressGeocodeSearchedSuccess(geocodeResult, i);


    }

    @Override
    public void onAddressPicked(Province province, City city, County county) {
        GeocodeQuery geocodeQuery = new GeocodeQuery(province.getAreaName() + city.getAreaName(), city.getAreaName());
        geocoderSearch.getFromLocationNameAsyn(geocodeQuery);
        presenterDriverPublishRoute.doPlaceAddressPicked(province,city,county);
        showLoadDialog();
    }


    @Override
    public void handle(String time) {
        tv_current_time.setText(TimeUtil.getAssembleToSeconds(time));
    }
}
