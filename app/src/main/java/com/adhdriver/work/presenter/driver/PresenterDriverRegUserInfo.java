package com.adhdriver.work.presenter.driver;


import android.content.Context;
import android.util.Log;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstCache;
import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.constant.ConstLog;
import com.adhdriver.work.constant.ConstMap;
import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.dao.IBaseDao;
import com.adhdriver.work.dao.impl.IOssConfigDaoImpl;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.OssConfig;
import com.adhdriver.work.entity.driver.temp.RegConfirm;
import com.adhdriver.work.function.FunctionRegUserInfo;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.http.upload.IOssUpload;
import com.adhdriver.work.http.upload.IOssUploadImpl;
import com.adhdriver.work.http.upload.IOssUploadView;
import com.adhdriver.work.http.upload.OSSAdhClient;
import com.adhdriver.work.http.upload.OnOssUploadListener;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.logic.LogicAccout;
import com.adhdriver.work.logic.LogicVehicle;
import com.adhdriver.work.method.IOssConfig;
import com.adhdriver.work.method.IRegister;
import com.adhdriver.work.method.impl.IOssConfigImpl;
import com.adhdriver.work.method.impl.IRegisterImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IRegUserInfoView;
import com.adhdriver.work.utils.ImgUtil;
import com.adhdriver.work.utils.PictureUtil;
import com.adhdriver.work.utils.SpUtil;
import com.adhdriver.work.verification.VertifyNotNull;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.jph.takephoto.model.TResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/12.
 * 类描述
 * 版本
 */

public class PresenterDriverRegUserInfo extends BasePresenter<IRegUserInfoView> {

    private IRegUserInfoView iRegUserInfoView;
    private IRegister iRegister;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;


    private LogicAccout logicAccout;
    private LogicVehicle logicVehicle;


    /**
     * 数据相关
     */
    public Map<String, String> mapPic;


    /**
     * 方法
     *
     * @param iRegUserInfoView
     */

    private FunctionRegUserInfo functionRegUserInfo;


    /**
     * 图片上传相关
     *
     * @param iRegUserInfoView
     */


    private OSSAdhClient ossAdhClient;
    private OssConfig ossConfig;

    private IOssUpload iOssUpload;
    private IOssConfig iOssConfig;

    private IBaseDao<OssConfig> iOssConfigDao;

    public PresenterDriverRegUserInfo(IRegUserInfoView iRegUserInfoView) {
        this.iRegUserInfoView = iRegUserInfoView;
        this.iRegister = new IRegisterImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
        this.logicAccout = new LogicAccout();
        this.logicVehicle = new LogicVehicle();
        this.mapPic = new HashMap<>();
        this.functionRegUserInfo = new FunctionRegUserInfo();
        this.iOssUpload = new IOssUploadImpl();
        this.iOssConfig = new IOssConfigImpl();
        this.iOssConfigDao = new IOssConfigDaoImpl();
    }





    /**
     * 获取oss配置信息
     *
     * @param url
     * @param accessToken
     */
    public void doGetOssConfigInfo(String url, final String accessToken) {

        iOssConfig.doGetOssConfigInfo(url, accessToken, new OnDataBackListener() {
            @Override
            public void onStart() {
                iRegUserInfoView.showLoadingDialog();
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

                    if (result) {
                        iRegUserInfoView.onDataBackSuccessForOssConfig(accessToken);
                    }

                } else {

                    iRegUserInfoView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iRegUserInfoView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iRegUserInfoView.onDataBackFail(code, errorEntity.getError_message());
                }
            }

            @Override
            public void onFinish() {
                iRegUserInfoView.dismissLoadingDialog();
            }
        });

    }




    /**
     * 长传用户注册信息
     *
     * @param url
     * @param accessToken
     * @param userName
     * @param user_identity_card_no
     * @param inviteCode
     */
    public void doCompleteUser(String url,
                               final String accessToken,
                               String userName,
                               String user_identity_card_no, String inviteCode) {


        if (vertifyNotNull.isNullString(userName)) {
            iRegUserInfoView.doVertifyErrorNullDriverName();
            return;
        }

        if (vertifyNotNull.isNullString(user_identity_card_no)) {

            iRegUserInfoView.doVertifyErrorNullIdentifyNo();
            return;
        } else if (!logicAccout.isLegalIdCard(user_identity_card_no)) {
            iRegUserInfoView.doVertifyErrorUnLegalIdentifyNo();

        } else {

            String pathFront = mapPic.get(ConstCache.IDENTIFY_FRONT);
            String pathBack = mapPic.get(ConstCache.IDENTIFY_BACK);

            if (vertifyNotNull.isNullString(pathFront)) {
                iRegUserInfoView.doVertifyErrorNullIdentifyFrontPic();
                return;
            }

            if (vertifyNotNull.isNullString(pathBack)) {
                iRegUserInfoView.doVertifyErrorNullIdentifyBackPic();
                return;
            }
        }


        String inviteTargetCode = functionRegUserInfo.getTargetInviteCode(inviteCode);


        iRegister.doCompleteUser(
                url,
                accessToken,
                userName,
                user_identity_card_no,
                mapPic.get(ConstCache.IDENTIFY_FRONT),
                mapPic.get(ConstCache.IDENTIFY_BACK),
                inviteTargetCode, new OnDataBackListener() {
                    @Override
                    public void onStart() {
                        iRegUserInfoView.showLoadingDialog();
                    }

                    @Override
                    public void onSuccess(String data) {


                        iRegUserInfoView.onDataBackSuccessForCompleteUserInfo(accessToken);

                    }

                    @Override
                    public void onFail(int code, String data) {

                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                        if (vertifyNotNull.isNotNullObj(errorEntity)) {
                            iRegUserInfoView.onDataBackFail(code, errorEntity.getError_message());
                        } else {
                            iRegUserInfoView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                        }

                    }

                    @Override
                    public void onFinish() {
                        iRegUserInfoView.dismissLoadingDialog();
                    }
                });


    }


    /**
     * 获取当前的司机注册状态
     *
     * @param url
     * @param accessToken
     */
    public void doCheckRegStatues(String url, final String accessToken) {

        iRegister.doGetRegStatue(url, accessToken, new OnDataBackListener() {
            @Override
            public void onStart() {
                iRegUserInfoView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {


                RegConfirm regConfirm = parseSerilizable.getParseToObj(data, RegConfirm.class);

                if (vertifyNotNull.isNotNullObj(regConfirm)) {


                    boolean passed = regConfirm.isPassed();//考试是否通过
                    String driverId = regConfirm.getDriver_id();
                    String car_pass = regConfirm.getCar_pass();
                    SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_DRIVER_ID, driverId);


                    if (vertifyNotNull.isNullString(car_pass)) {


                        iRegUserInfoView.onDataBackSuccessForSetVehicleInfo(accessToken);


                    } else if (logicVehicle.isCarPassApproved(car_pass)) {

                        if (passed) {

                            iRegUserInfoView.onDataBackSuccessForExamPass();


                        } else {

                            iRegUserInfoView.onDataBackSuccessForDoLearnAndExam(accessToken);
                        }

                    } else {

                        iRegUserInfoView.onDataBackSuccessForSetVehicleInfo(accessToken);
                    }

                } else {

                    iRegUserInfoView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }


            }


            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iRegUserInfoView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iRegUserInfoView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);

                }
            }

            @Override
            public void onFinish() {

                iRegUserInfoView.dismissLoadingDialog();
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
                iRegUserInfoView.ossOnProgress(request, currentSize, totalSize);
            }
        });


        iOssUpload.doOssUpLoad(ossAdhClient.getOssClient(), ossConfig, putObjectRequest, new OnOssUploadListener() {

            @Override
            public void onOssSuccess(String requestObjectKey, String resultData) {
                String url = ossConfig.getPost_url() + requestObjectKey;

                switch (viewId) {

                    /**
                     * 图片相关
                     */

                    case R.id.iv_upload_identify_left:
                        mapPic.put(ConstCache.IDENTIFY_FRONT, url);
                        iRegUserInfoView.ossOnSuccessLoadIdFrontPicToUi(url,R.drawable.img_upload_bg);
                        break;


                    case R.id.iv_upload_identify_right:
                        mapPic.put(ConstCache.IDENTIFY_BACK, url);
                        iRegUserInfoView.ossOnSuccessLoadIdBackPicToUi(url,R.drawable.img_upload_bg);
                        break;

                }
            }

            @Override
            public void onOssFailure(String requestObjectKey, String e, String e1) {


                iRegUserInfoView.ossOnFailure();
            }


        });

    }


    /**
     * 权限检查
     */
    public void doPermissionCheck() {

        iRegUserInfoView.doPermissionCheck();

    }


    /**
     * 权限关闭提醒
     */
    public void doShowPermissionAlert() {

        iRegUserInfoView.doShowPermissionAlert();

    }
    /**
     * 显示photopopWindow
     */

    public void doShowPhotoPopWindow() {
        iRegUserInfoView.doShowPhotoPopWindow();
    }



}
