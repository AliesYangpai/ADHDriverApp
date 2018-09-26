package com.adhdriver.work.presenter.driver;

import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.dao.IBaseDao;
import com.adhdriver.work.dao.impl.lDriverDaoImpl;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.driver.Driver;
import com.adhdriver.work.entity.driver.temp.PublishRoute;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.logic.LogicPublishRoute;
import com.adhdriver.work.method.IPublishRoute;
import com.adhdriver.work.method.impl.IPublishRouteImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IPublishRouteView;
import com.adhdriver.work.utils.StringUtil;
import com.adhdriver.work.verification.VertifyNotNull;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeResult;

import java.util.ArrayList;
import java.util.List;

import cn.addapp.pickers.entity.City;
import cn.addapp.pickers.entity.County;
import cn.addapp.pickers.entity.Province;
import cn.addapp.pickers.util.ConvertUtils;

/**
 * Created by Administrator on 2017/12/1.
 * 类描述 发布路线的presenter
 * 版本
 */

public class PresenterDriverPublishRoute extends BasePresenter<IPublishRouteView> {

    private IPublishRouteView iPublishRouteView;
    private IPublishRoute iPublishRoute;
    private VertifyNotNull vertifyNotNull;
    private ParseSerilizable parseSerilizable;
    private LogicPublishRoute logicPublishRoute;
    private IBaseDao<Driver> iDriverDao;


    /**
     * 数据相关
     */

    private boolean isStart = true;


    public void setStart(boolean start) {
        isStart = start;
    }

    public PresenterDriverPublishRoute(IPublishRouteView iPublishRouteView) {
        this.iPublishRouteView = iPublishRouteView;
        this.iPublishRoute = new IPublishRouteImpl();
        this.vertifyNotNull = new VertifyNotNull();
        this.parseSerilizable = new ParseSerilizable();
        this.logicPublishRoute = new LogicPublishRoute();
        this.iDriverDao = new lDriverDaoImpl();
    }

    /**
     * 发布顺风路线
     * @param url
     * @param currentTime
     * @param startplace
     * @param destination
     * @param publishRoute
     */
    public void doPublishRoute(String url,
                              String currentTime,
                              String startplace,
                              String destination,
                              PublishRoute publishRoute) {



        if(vertifyNotNull.isNullString(currentTime)) {
            iPublishRouteView.doVertifyErrorNoDepartTime();
            return;
        }
        if(vertifyNotNull.isNullString(startplace)) {
            iPublishRouteView.doVertifyErrorNoDepartPlace();
            return;
        }
        if(vertifyNotNull.isNullString(destination)) {
            iPublishRouteView.doVertifyErrorNoDestination();
            return;
        }


        if(vertifyNotNull.isNotNullObj(publishRoute)) {


            Driver driver = iDriverDao.findFirst(Driver.class);

            if(null != driver) {
                publishRoute.setDriver_id(driver.getDriver_id());
            }

            iPublishRoute.doPublishRoute(url,
                    publishRoute.getHitchhike_no(),
                    publishRoute.getDriver_id(),
                    publishRoute.getDepart_time(),
                    publishRoute.getDepart_zone_code(),
                    publishRoute.getDepart_address(),
                    publishRoute.getDepart_coordinate_x(),
                    publishRoute.getDepart_coordinate_y(),
                    publishRoute.getDestination_zone_code(),
                    publishRoute.getDestination_address(),
                    publishRoute.getDestination_coordinate_x(),
                    publishRoute.getDestination_coordinate_y(),

                    new OnDataBackListener() {
                        @Override
                        public void onStart() {
                            iPublishRouteView.showLoadingDialog();
                        }

                        @Override
                        public void onSuccess(String data) {


                            iPublishRouteView.onDataBackSuccessForPublishRoute();

                        }

                        @Override
                        public void onFail(int code, String data) {
                            ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                            if (vertifyNotNull.isNotNullObj(errorEntity)) {

                                iPublishRouteView.onDataBackFail(code, errorEntity.getError_message());
                            } else {

                                iPublishRouteView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);

                            }

                        }

                        @Override
                        public void onFinish() {

                            iPublishRouteView.dismissLoadingDialog();
                        }
                    });



        }



    }


    /**
     * 初始化dialog
     */
    public void doInitAddressDialog() {


        ArrayList<Province> list = iPublishRouteView.doGetAddressList();

        if (vertifyNotNull.isNotNullListSize(list)) {
            iPublishRouteView.doInitAddressDialog(list);
        }

    }

    /**
     * 初始化时间的dialog
     */
    public void doInitTimeDialog() {


        iPublishRouteView.doInitTimeDialog();

    }


    /**
     * 触发地选择
     *
     * @param province
     * @param city
     * @param county
     */
    public void doPlaceAddressPicked(Province province, City city, County county) {

        if (isStart) {

            if (vertifyNotNull.isNotNullObj(province) &&
                    vertifyNotNull.isNotNullObj(city) &&
                    vertifyNotNull.isNotNullObj(county)) {
                iPublishRouteView.doStartAddressPicked(province.getAreaName(), city.getAreaName(), city.getAreaName());

            }

        } else {


            if (vertifyNotNull.isNotNullObj(province) &&
                    vertifyNotNull.isNotNullObj(city) &&
                    vertifyNotNull.isNotNullObj(county)) {
                iPublishRouteView.doEndAddressPicked(province.getAreaName(), city.getAreaName(), city.getAreaName());

            }

        }


    }


    public void doShowDialogForStartPlace() {


        iPublishRouteView.doShowStartPickDialog();

    }

    public void doShowDialogForEndPlace() {


        iPublishRouteView.doShowEndPickDialog();

    }


    /**
     * 初始地、目的地 地理解码返回成功处理
     *
     * @param geocodeResult
     * @param rCode
     */
    public void doDealWithAddressGeocodeSearchedSuccess(GeocodeResult geocodeResult, int rCode) {


        if (logicPublishRoute.isMapCallbackSuccess(rCode)) {

            if (vertifyNotNull.isNotNullObj(geocodeResult) &&
                    vertifyNotNull.isNotNullListSize(geocodeResult.getGeocodeAddressList())) {
                GeocodeAddress address = geocodeResult.getGeocodeAddressList().get(0);

                String adcode = address.getAdcode();//区域编码
                double latitude = address.getLatLonPoint().getLatitude(); //纬度
                double longitude = address.getLatLonPoint().getLongitude();//经度

                if(isStart) {

                    if(logicPublishRoute.isAdCodeLenthBigThan5(adcode)) {
                        adcode = adcode.substring(0, 4);
                    }
                    iPublishRouteView.doStartPlaceGeocodeSearchedSuccess(String.valueOf(latitude),String.valueOf(longitude),adcode);
                }else {
                    if(logicPublishRoute.isAdCodeLenthBigThan5(adcode)) {
                        adcode = adcode.substring(0, 4);
                    }

                    iPublishRouteView.doEndPlaceGeocodeSearchedSuccess(String.valueOf(latitude),String.valueOf(longitude),adcode);
                }

            }else {

                iPublishRouteView.onDataBackFailGeocodeNoPlace();

            }

        }else {



            iPublishRouteView.onDataBackFailGeocodeCodeError(rCode);

        }

    }

}
