package com.adhdriver.work.presenter.driver;

import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.dao.IBaseDao;
import com.adhdriver.work.dao.impl.ILogonVehicleDaoImpl;
import com.adhdriver.work.dao.impl.IUserDaoImpl;
import com.adhdriver.work.dao.impl.lDriverDaoImpl;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.User;
import com.adhdriver.work.entity.driver.Driver;
import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.entity.driver.token.TokenInfo;
import com.adhdriver.work.entity.driver.vehicle.DriverVehicle;
import com.adhdriver.work.entity.driver.vehicle.LogonVehicle;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.logic.LogicDriver;
import com.adhdriver.work.logic.LogicVehicle;
import com.adhdriver.work.method.IDriverAbout;
import com.adhdriver.work.method.IVehicle;
import com.adhdriver.work.method.impl.IDriverAboutImpl;
import com.adhdriver.work.method.impl.IVehicleImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IVehiclesView;
import com.adhdriver.work.utils.SpUtil;
import com.adhdriver.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 * 类描述
 * 版本
 */

public class PresenterDriverVehicles extends BasePresenter<IVehiclesView> {

    private IVehiclesView iVehiclesView;
    private IVehicle iVehicle;
    private IDriverAbout iDriverAbout;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;


    /**
     * 数据库查询相关
     */
    private IBaseDao<User> iUserDao;
    private IBaseDao<Driver> iDriverDao;
    private IBaseDao<LogonVehicle> iLogonVehicleDao;


    private LogicDriver logicDriver;
    private LogicVehicle logicVehicle;

    public PresenterDriverVehicles(IVehiclesView iVehiclesView) {
        this.iVehiclesView = iVehiclesView;
        this.iVehicle = new IVehicleImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();


        this.iDriverAbout = new IDriverAboutImpl();
        this.iUserDao = new IUserDaoImpl();
        this.iDriverDao = new lDriverDaoImpl();
        this.iLogonVehicleDao = new ILogonVehicleDaoImpl();
        this.logicDriver = new LogicDriver();
        this.logicVehicle = new LogicVehicle();
    }


    /**
     * 获取我的车辆列表
     *
     * @param url
     * @param size
     * @param index
     */
    public void doGetVehicles(String url, int size, int index) {

        iVehicle.doGetVehicles(url, size, index, new OnDataBackListener() {
            @Override
            public void onStart() {
                iVehiclesView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                List<DriverVehicle> list = parseSerilizable.getParseToList(data, DriverVehicle.class);
                if (vertifyNotNull.isNotNullListSize(list)) {
                    iVehiclesView.onDataBackSuccessForVehicles(list);
                }
            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iVehiclesView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iVehiclesView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
                iVehiclesView.dismissLoadingDialog();
            }
        });

    }


    /**
     * 下拉差量更新
     *
     * @param url
     * @param size
     * @param index
     */
    public void doGetVehiclesInFresh(String url, int size, int index) {


        iVehicle.doGetVehiclesInFresh(url, size, index, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {


                List<DriverVehicle> list = parseSerilizable.getParseToList(data, DriverVehicle.class);
                iVehiclesView.onDataBackSuccessForVehiclesInFresh(list);
            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    iVehiclesView.onDataBackFail(code, errorEntity.getError_message());
                } else {

                    iVehiclesView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);

                }
            }

            @Override
            public void onFinish() {

                iVehiclesView.dismissFreshLoading();
            }
        });

    }


    /**
     * 上拉加载
     *
     * @param url
     * @param size
     * @param index
     */
    public void doGetVehiclesInLoadMore(String url, int size, int index) {


        iVehicle.doGetVehiclesInLoadMore(url, size, index, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {


                List<DriverVehicle> list = parseSerilizable.getParseToList(data, DriverVehicle.class);
                iVehiclesView.onDataBackSuccessForVehiclesInLoadMore(list);
            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    iVehiclesView.onDataBackFailInLoadMore(code, errorEntity.getError_message());
                } else {

                    iVehiclesView.onDataBackFailInLoadMore(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);

                }

            }

            @Override
            public void onFinish() {

            }
        });
    }


    /**
     * 切换车辆信息
     *
     * @param url
     */
    public void doSwitchVehicle(String url) {

        iVehicle.doSwitchVehicle(url, new OnDataBackListener() {
            @Override
            public void onStart() {
                iVehiclesView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {


                TokenInfo tokenInfo = parseSerilizable.getParseToObj(data, TokenInfo.class);
                if (vertifyNotNull.isNotNullObj(tokenInfo)) {
                    SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_TOKEN, tokenInfo.getAccess_token());

                    iVehiclesView.onDataBackSuccessForSwitchAndGetToken();

                } else {

                    iVehiclesView.dismissLoadingDialog();
                }


            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iVehiclesView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iVehiclesView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
                iVehiclesView.dismissLoadingDialog();
            }

            @Override
            public void onFinish() {
                iVehiclesView.dismissLoadingDialog();
            }
        });

    }



    public void doGetUserInfo(String url) {

        iDriverAbout.doGetUserInfo(url, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {


                User user = parseSerilizable.getParseToObj(data, User.class);


                if (vertifyNotNull.isNotNullObj(user)) {
                    List<User> userList = iUserDao.findAll(User.class);
                    if (vertifyNotNull.isNotNullListSize(userList)) {
                        iUserDao.deleteAll(User.class);
                        iUserDao.save(user);
                    } else {
                        iUserDao.save(user);
                    }

                    String province_id = user.getProvince_id();
                    String community_id = user.getCommunity_id();
                    String county_id = user.getCounty_id();


                    if (logicDriver.isNotNullProvice(province_id)
                            && logicDriver.isNotNullConmmunity(community_id)
                            && logicDriver.isNotNullCounty(county_id)) {

                        SpUtil.getInstance().saveBooleanTosp(ConstSp.SP_KEY_HAS_AREA_OR_NOT, ConstSp.SP_VALUE.IS_HAD_AREA);

                    }

                    Driver driver = user.getDriver();
                    if (vertifyNotNull.isNotNullObj(driver)) {
                        List<Driver> driverList = iDriverDao.findAll(Driver.class);
                        if (vertifyNotNull.isNotNullListSize(driverList)) {
                            iDriverDao.deleteAll(Driver.class);
                            iDriverDao.save(driver);
                        } else {
                            iDriverDao.save(driver);
                        }
                        LogonVehicle logonVehicle = driver.getLogon_vehicle();
                        if (vertifyNotNull.isNotNullObj(logonVehicle)) {
                            List<LogonVehicle> logonVehicleList = iLogonVehicleDao.findAll(LogonVehicle.class);
                            if (vertifyNotNull.isNotNullListSize(logonVehicleList)) {
                                iLogonVehicleDao.deleteAll(LogonVehicle.class);
                                iLogonVehicleDao.save(logonVehicle);
                            }else {
                                iLogonVehicleDao.save(logonVehicle);
                            }

                            String driver_vehicle_id = logonVehicle.getDriver_vehicle_id();
                            String category_name = logonVehicle.getCategory_name();
                            SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_LOGON_DRIVER_VEHICLE_ID, driver_vehicle_id); //保存的，当前登陆的车辆id
                            SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_LOGON_DRIVER_CATEGORY, category_name);//保存的，当前登陆的车辆类型id

                            iVehiclesView.onDataBackSuccessForGetUserInfo();

                        } else {
                            iVehiclesView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                            iVehiclesView.dismissLoadingDialog();
                        }
                    } else {
                        iVehiclesView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                        iVehiclesView.dismissLoadingDialog();
                    }
                } else {

                    iVehiclesView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                    iVehiclesView.dismissLoadingDialog();
                }




            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iVehiclesView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iVehiclesView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
                iVehiclesView.dismissLoadingDialog();
            }

            @Override
            public void onFinish() {

            }
        });

    }


    public void doUpLoadNewVehicleLocation(String url,
                                           double longitude,
                                           double latitude,
                                           String zone_code,
                                           String driver_vehicle_id,
                                           String vehicle_category_name) {

        iVehicle.doUpLoadNewVehicleLocation(url, longitude, latitude, zone_code, driver_vehicle_id, vehicle_category_name, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {

                iVehiclesView.onDataBackSuccessForUpLoadNewVehicleLocation();


            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iVehiclesView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iVehiclesView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {

                iVehiclesView.dismissLoadingDialog();
            }
        });
    }

    /**
     * 判断是否显示切换车辆的dialog
     * @param driverVehicle
     */

    public void doShowSwitchVehicleAlertOrNot(DriverVehicle driverVehicle) {


        if(logicVehicle.isCurrentLogonVehicle(driverVehicle.getDriver_vehicle_id())) {
            return;
        }

        if(!logicVehicle.isVertifyPass(driverVehicle.getVehicle_status())){
            return;
        }
        iVehiclesView.doShowSwitchAlertDialog(driverVehicle);




    }

}
