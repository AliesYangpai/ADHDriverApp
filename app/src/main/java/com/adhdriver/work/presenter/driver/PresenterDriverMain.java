package com.adhdriver.work.presenter.driver;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.adhdriver.work.AiDaiHuoApplication;
import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstCache;
import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.constant.ConstLog;
import com.adhdriver.work.constant.ConstParams;
import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.dao.IBaseDao;
import com.adhdriver.work.dao.impl.ILogonVehicleDaoImpl;
import com.adhdriver.work.dao.impl.IOssConfigDaoImpl;
import com.adhdriver.work.dao.impl.IUserDaoImpl;
import com.adhdriver.work.dao.impl.lDriverDaoImpl;
import com.adhdriver.work.entity.AppUpdate;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.OssConfig;
import com.adhdriver.work.entity.User;
import com.adhdriver.work.entity.driver.Driver;
import com.adhdriver.work.entity.driver.orders.Assign;
import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.entity.driver.token.TokenInfo;
import com.adhdriver.work.entity.driver.vehicle.LogonVehicle;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.http.upload.IOssUpload;
import com.adhdriver.work.http.upload.IOssUploadImpl;
import com.adhdriver.work.http.upload.OSSAdhClient;
import com.adhdriver.work.http.upload.OnOssUploadListener;
import com.adhdriver.work.listener.OnDataBackListener;

import com.adhdriver.work.logic.LogicAppInfo;
import com.adhdriver.work.logic.LogicDriver;
import com.adhdriver.work.logic.LogicPush;
import com.adhdriver.work.method.IDriverAbout;
import com.adhdriver.work.method.IOrderNotTake;
import com.adhdriver.work.method.IOssConfig;
import com.adhdriver.work.method.IVisitorAbout;
import com.adhdriver.work.method.impl.IDriverAboutImpl;
import com.adhdriver.work.method.impl.IOrderNotTakeImpl;
import com.adhdriver.work.method.impl.IOssConfigImpl;
import com.adhdriver.work.method.impl.IVisitorAboutImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.push.PushEntity;
import com.adhdriver.work.ui.iview.driver.IMainDriverView;
import com.adhdriver.work.utils.DoubleUtil;
import com.adhdriver.work.utils.PictureUtil;
import com.adhdriver.work.utils.SpUtil;
import com.adhdriver.work.utils.ToastUtil;
import com.adhdriver.work.verification.VertifyNotNull;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.amap.api.location.AMapLocation;
import com.iflytek.cloud.thirdparty.V;
import com.jph.takephoto.model.TResult;


import org.feezu.liuli.timeselector.Utils.TextUtil;

import java.util.List;

/**
 * Created by Alie on 2017/11/6.
 * 类描述
 * 版本
 */

public class PresenterDriverMain extends BasePresenter<IMainDriverView> {

    private IMainDriverView iMainDriverView;
    private IDriverAbout iDriverAbout;
    private IVisitorAbout iVisitorAbout;
    private IOrderNotTake iOrderNotTake;


    /**
     * 数据库查询相关
     */
    private IBaseDao<User> iUserDao;
    private IBaseDao<Driver> iDriverDao;
    private IBaseDao<LogonVehicle> iLogonVehicleDao;


    /**
     * 解析验证相关
     */
    private ParseSerilizable parseSerilizable;


    /**
     * 验证逻辑相关
     */
    private VertifyNotNull vertifyNotNull;
    private LogicAppInfo logicAppInfo;
    private LogicDriver logicDriver;
    private LogicPush logicPush;




    private OSSAdhClient ossAdhClient;
    private OssConfig ossConfig;

    private IOssUpload iOssUpload;
    private IOssConfig iOssConfig;

    private IBaseDao<OssConfig> iOssConfigDao;

    public PresenterDriverMain(IMainDriverView iMainDriverView) {
        this.iMainDriverView = iMainDriverView;
        this.iOrderNotTake = new IOrderNotTakeImpl();
        this.iUserDao = new IUserDaoImpl();
        this.iDriverDao = new lDriverDaoImpl();
        this.iDriverAbout = new IDriverAboutImpl();
        this.iVisitorAbout = new IVisitorAboutImpl();
        this.iLogonVehicleDao = new ILogonVehicleDaoImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
        this.logicAppInfo = new LogicAppInfo();
        this.logicDriver = new LogicDriver();
        this.logicPush = new LogicPush();

        this.iOssUpload = new IOssUploadImpl();
        this.iOssConfig = new IOssConfigImpl();
        this.iOssConfigDao = new IOssConfigDaoImpl();
    }


    /**
     * 推送处理相关
     *
     * @param currentPushEntity
     */
    public void doDealWithPush(PushEntity currentPushEntity) {

        String pushType = currentPushEntity.getPushScopeType();
        if (vertifyNotNull.isNotNullString(pushType)) {

            if (logicPush.isScopeTypeOrder(pushType)) {

                iMainDriverView.doPushToGetOrderInfo(currentPushEntity);
            }

            if (logicPush.isScopeTypeEarnings(pushType)) {


                iMainDriverView.doPushToGatherPage();
            }

        }


    }


    public void doPushGetOrderInfo(String url, final PushEntity pushEntity) {

        iOrderNotTake.doGetOrderDetail(url, new OnDataBackListener() {
            @Override
            public void onStart() {
                iMainDriverView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {


                Order order = parseSerilizable.getParseToObj(data, Order.class);
                Assign assign = order.getAssigned();



                if (logicPush.isAssigned(assign)) {


                    String driver_vehicle_id_local = SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_LOGON_DRIVER_VEHICLE_ID, ConstSp.SP_VALUE.DEFAULT_STRING);
                    String driver_vehicle_id_assigned = assign.getDriver_vehicle_id();

                    if (logicPush.isSameDriverVehicleId(driver_vehicle_id_assigned, driver_vehicle_id_local)) {


                        iMainDriverView.doPushGoToOrderTake(order);

                    } else {


                        iMainDriverView.onDataBackFailForPush();
                    }

                } else {

                    String businessType = order.getBusiness_type();

                    if (logicPush.isSameCityOrder(businessType)) {
                        //同城
                        iMainDriverView.doPushGoSameCityAlertPage(pushEntity,order);
                    }


                    if (logicPush.isOverOfficeOrder(businessType)) {
                        //营业部订单
                        iMainDriverView.doPushGoOverOfficeAlertPage(pushEntity);
                    }
                }
            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iMainDriverView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iMainDriverView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {
                iMainDriverView.dismissLoadingDialog();
            }
        });

    }


    /**
     * 获取用户信息
     */
    public void doGetUserInfo(final String url) {

        iDriverAbout.doGetUserInfo(url, new OnDataBackListener() {
            @Override
            public void onStart() {

                iMainDriverView.showLoadingDialog();
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
                    SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_USER_ID,String.valueOf(user.getUser_id()));

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
                            } else {
                                iLogonVehicleDao.save(logonVehicle);
                            }

                            String driver_vehicle_id = logonVehicle.getDriver_vehicle_id();
                            String category_name = logonVehicle.getCategory_name();
                            SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_LOGON_DRIVER_VEHICLE_ID, driver_vehicle_id); //保存的，当前登陆的车辆id
                            SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_LOGON_DRIVER_CATEGORY, category_name);//保存的，当前登陆的车辆类型id

                            iMainDriverView.onDataBackSuccessForGetUserInfo(user);

                        } else {
                            iMainDriverView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                        }
                    } else {
                        iMainDriverView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                    }
                } else {

                    iMainDriverView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

                iMainDriverView.dismissLoadingDialog();
            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iMainDriverView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iMainDriverView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
                iMainDriverView.dismissLoadingDialog();
            }

            @Override
            public void onFinish() {

            }
        });

    }


    /**
     * 获取app版本信息你
     */
    public void doGetAppInfo(String url, String os_type, String os_version, String model, String device_id) {


        iDriverAbout.doGetAppInfo(url, os_type, os_version, model, device_id, new OnDataBackListener() {
            @Override
            public void onStart() {


            }

            @Override
            public void onSuccess(String data) {

                AppUpdate appUpdate = new ParseSerilizable().getParseToObj(data, AppUpdate.class);


                if (vertifyNotNull.isNotNullObj(appUpdate)) {

                    if (logicAppInfo.isNewVersion(appUpdate.getVersion_number())) {
                        iMainDriverView.onDataBackSuccessForGetAppInfo(appUpdate);
                    }
                }

                iMainDriverView.dismissLoadingDialog();


            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = new ParseSerilizable().getParseToObj(data, ErrorEntity.class);

                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    iMainDriverView.onDataBackFail(code, errorEntity.getError_message());

                } else {
                    iMainDriverView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_LOCAL_SEL_MSG);
                }

                iMainDriverView.dismissLoadingDialog();

            }

            @Override
            public void onFinish() {


            }
        });

    }


    /**
     * 初始化游客模式的单次定位
     */
    public void doInitLocationForVisitor() {

        iMainDriverView.doinitLocationForVisitor();

    }


    /**
     * 游客模式获取App版本信息
     *
     * @param url
     * @param os_type
     * @param os_version
     * @param model
     * @param device_id
     */
    public void doGetAppInfoForVisitor(String url, String os_type, String os_version, String model, String device_id) {

        iVisitorAbout.doGetAppInfo(url, os_type, os_version, model, device_id, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {


                AppUpdate appUpdate = new ParseSerilizable().getParseToObj(data, AppUpdate.class);


                if (vertifyNotNull.isNotNullObj(appUpdate) &&
                        logicAppInfo.isNewVersion(appUpdate.getVersion_number())) {
                    iMainDriverView.onDataBackSuccessForGetAppInfo(appUpdate);
                }
                iMainDriverView.dismissLoadingDialog();


            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iMainDriverView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iMainDriverView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

                iMainDriverView.dismissLoadingDialog();
            }

            @Override
            public void onFinish() {

            }
        });

    }


    /**
     * 跳转下载App
     *
     * @param appUpdate
     */
    public void doGoToUrlDownLoad(AppUpdate appUpdate) {

        if (vertifyNotNull.isNotNullObj(appUpdate)) {

            iMainDriverView.doGoToUrlDownLoad(appUpdate.getDownload_url());
        }

    }


    public void doUpLoadLocationForVisitor(String url, AMapLocation aMapLocation) {

        if (vertifyNotNull.isNotNullObj(aMapLocation)) {


            iVisitorAbout.doVisitorSave(
                    url,
                    DoubleUtil.getDecima6(aMapLocation.getLongitude()),
                    DoubleUtil.getDecima6(aMapLocation.getLatitude()),
                    aMapLocation.getAdCode(), new OnDataBackListener() {
                        @Override
                        public void onStart() {

                        }

                        @Override
                        public void onSuccess(String data) {


                        }

                        @Override
                        public void onFail(int code, String data) {

                            ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                            if (null != errorEntity) {
                                iMainDriverView.onDataBackFail(code, errorEntity.getError_message());
                            } else {

                                iMainDriverView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                            }

                        }

                        @Override
                        public void onFinish() {

                        }
                    });

        }


    }





    public void doInitVisitorData(String url) {

        iVisitorAbout.doVisitorLogon(url, new OnDataBackListener() {
            @Override
            public void onStart() {
                iMainDriverView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {


                TokenInfo tokenInfo = parseSerilizable.getParseToObj(data, TokenInfo.class);

                if (vertifyNotNull.isNotNullObj(tokenInfo)) {

                    String access_token = tokenInfo.getAccess_token();

                    SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_TOKEN, access_token); //这个是 visitor的token
                    SpUtil.getInstance().saveBooleanTosp(ConstSp.SP_KEY_LOAD_OR_NOT, ConstSp.SP_VALUE.IS_LOADED_APP);
                    SpUtil.getInstance().saveBooleanTosp(ConstSp.SP_KEY_VISITOR, ConstSp.SP_VALUE.DEFAULT_TRUE_VISITOR);


                    iMainDriverView.onDataBackSuccessForGetVisitorToken();


                } else {


                    iMainDriverView.dismissLoadingDialog();
                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    iMainDriverView.onDataBackFail(code, errorEntity.getError_message());

                } else {

                    iMainDriverView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);

                }

                iMainDriverView.dismissLoadingDialog();
            }

            @Override
            public void onFinish() {


            }
        });

    }


    /**
     * 获取oss配置信息
     * 这里用作测试
     *
     * @param url
     * @param accessToken
     */
    public void doGetOssConfigInfo(String url, final String accessToken) {

        iOssConfig.doGetOssConfigInfo(url, accessToken, new OnDataBackListener() {
            @Override
            public void onStart() {
                iMainDriverView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                OssConfig ossConfig = parseSerilizable.getParseToObj(data, OssConfig.class);

                if (vertifyNotNull.isNotNullObj(ossConfig)) {

                    OssConfig first = iOssConfigDao.findFirst(OssConfig.class);

                    boolean result = false;
                    if (vertifyNotNull.isNotNullObj(first)) {

                        iOssConfigDao.deleteAll(OssConfig.class);
                        result = iOssConfigDao.save(ossConfig);
                    } else {
                        result = iOssConfigDao.save(ossConfig);
                    }


                } else {

                    iMainDriverView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iMainDriverView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iMainDriverView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
                iMainDriverView.dismissLoadingDialog();
            }
        });

    }



    public void doOpenPush(String url,
                           String os_type,
                           String os_version,
                           String model,
                           String device_id,
                           String push_type,
                           String push_token,
                           boolean quiet_mode) {

        iDriverAbout.doOpenPush(
                url,
                os_type,
                os_version,
                model,
                device_id,
                push_type,
                push_token,
                quiet_mode, new OnDataBackListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(String data) {


                    }

                    @Override
                    public void onFail(int code, String data) {
                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                        if (vertifyNotNull.isNotNullObj(errorEntity)) {
                            iMainDriverView.onDataBackFail(code, errorEntity.getError_message());
                        } else {
                            iMainDriverView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                        }

                    }

                    @Override
                    public void onFinish() {

                    }
                });
    }


    public void doClosePush(String url,
                           String os_type,
                           String os_version,
                           String model,
                           String device_id,
                           String push_type,
                           String push_token,
                           boolean quiet_mode) {

        iDriverAbout.doClosePush(
                url,
                os_type,
                os_version,
                model,
                device_id,
                push_type,
                push_token,
                quiet_mode, new OnDataBackListener() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onSuccess(String data) {


                    }

                    @Override
                    public void onFail(int code, String data) {
                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                        if (vertifyNotNull.isNotNullObj(errorEntity)) {
                            iMainDriverView.onDataBackFail(code, errorEntity.getError_message());
                        } else {
                            iMainDriverView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                        }

                    }

                    @Override
                    public void onFinish() {

                    }
                });
    }



    /**
     * 进行图片上传
     *
     * @param result
     */
    public void doOssUpLoad(TResult result, Context context, final int viewId) {


        if (vertifyNotNull.isNullObj(ossAdhClient) && vertifyNotNull.isNullObj(ossConfig)) {
            ossAdhClient = new OSSAdhClient(context);
            ossConfig = ossAdhClient.getOssConfig();
        }


        String path = "";
        if (vertifyNotNull.isNotNullObj(result)) {

            path = result.getImage().getCompressPath();

        }

        Log.i(ConstLog.PHOTOS, "当前线程Id" + Thread.currentThread().getId() + " " + path);


        PutObjectRequest putObjectRequest = new PutObjectRequest(ossConfig.getBucket_name(), ossConfig.getObject_key() + PictureUtil.reNamePicture(), path);

        putObjectRequest.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                iMainDriverView.ossOnProgress(request, currentSize, totalSize);
            }
        });


        iOssUpload.doOssUpLoad(ossAdhClient.getOssClient(), ossConfig, putObjectRequest, new OnOssUploadListener() {

            @Override
            public void onOssSuccess(String requestObjectKey, String resultData) {
                String url = ossConfig.getPost_url() + requestObjectKey;

                iMainDriverView.ossOnSuccessLoadIdUserHeadToUi(url,viewId);
            }

            @Override
            public void onOssFailure(String requestObjectKey, String e, String e1) {


                iMainDriverView.ossOnFailure();
            }


        });

    }



    /**
     * 权限检查
     */
    public void doPermissionCheck() {

        iMainDriverView.doPermissionCheck();

    }

    /**
     * 显示photopopWindow
     */

    public void doShowPhotoPopWindow() {
        iMainDriverView.doShowPhotoPopWindow();
    }

    /**
     * 权限关闭提醒
     */
    public void doShowPermissionAlert() {

        iMainDriverView.doShowPermissionAlert();

    }


    public void doUploadAvatar(String url) {


        iDriverAbout.doUploadAvatar(url, new OnDataBackListener() {
            @Override
            public void onStart() {
                iMainDriverView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                iMainDriverView.onDataBackSuccessForUpLoadAvatar();

            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iMainDriverView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iMainDriverView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }

                iMainDriverView.dismissLoadingDialog();
            }

            @Override
            public void onFinish() {

            }
        });
    }


    /**
     * 获取用户信息 在上传头像之后
     */
    public void doGetUserInfoAfterAvatar(final String url) {

        iDriverAbout.doGetUserInfo(url, new OnDataBackListener() {
            @Override
            public void onStart() {

                iMainDriverView.showLoadingDialog();
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
                    SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_USER_ID,String.valueOf(user.getUser_id()));

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
                            } else {
                                iLogonVehicleDao.save(logonVehicle);
                            }

                            String driver_vehicle_id = logonVehicle.getDriver_vehicle_id();
                            String category_name = logonVehicle.getCategory_name();
                            SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_LOGON_DRIVER_VEHICLE_ID, driver_vehicle_id); //保存的，当前登陆的车辆id
                            SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_LOGON_DRIVER_CATEGORY, category_name);//保存的，当前登陆的车辆类型id

                        } else {
                            iMainDriverView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                        }
                    } else {
                        iMainDriverView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                    }
                } else {

                    iMainDriverView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

                iMainDriverView.dismissLoadingDialog();
            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iMainDriverView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iMainDriverView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
                iMainDriverView.dismissLoadingDialog();
            }

            @Override
            public void onFinish() {

            }
        });

    }
}
