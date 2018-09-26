package com.adhdriver.work.presenter.driver;

import android.content.Context;
import android.util.Log;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.constant.ConstLog;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.OssConfig;
import com.adhdriver.work.entity.driver.temp.RegConfirm;
import com.adhdriver.work.entity.driver.temp.RegDriverComplete;
import com.adhdriver.work.entity.driver.vehicle.VehicleCategory;
import com.adhdriver.work.entity.driver.vehicle.VehicleType;
import com.adhdriver.work.entity.driver.temp.TempRegDriverVehicle;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.http.upload.IOssUpload;
import com.adhdriver.work.http.upload.IOssUploadImpl;
import com.adhdriver.work.http.upload.OSSAdhClient;
import com.adhdriver.work.http.upload.OnOssUploadListener;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.logic.LogicPhoto;
import com.adhdriver.work.logic.LogicRegister;
import com.adhdriver.work.logic.LogicVehicle;
import com.adhdriver.work.method.IRegister;
import com.adhdriver.work.method.IVehicle;
import com.adhdriver.work.method.impl.IRegisterImpl;
import com.adhdriver.work.method.impl.IVehicleImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IRegVehicleInfoView;
import com.adhdriver.work.utils.PictureUtil;
import com.adhdriver.work.verification.VertifyNotNull;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.jph.takephoto.model.TResult;

import java.util.List;

/**
 * Created by Administrator on 2017/12/19.
 * 类描述
 * 版本
 */

public class PresenterDriverVehicleReg extends BasePresenter<IRegVehicleInfoView> {


    private IRegVehicleInfoView iRegVehicleInfoView;
    private IVehicle iVehicle;
    private IRegister iRegister;


    /**
     * 解析相关
     */
    private VertifyNotNull vertifyNotNull;
    private ParseSerilizable parseSerilizable;


    /**
     * 数据类相关
     */
    private TempRegDriverVehicle tempRegDriverVehicle;
    private VehicleCategory currentVehicleCategory;
    private List<VehicleCategory> currentVehicleCategoryList; //当前的车辆对应的 type列表


    /**
     * 逻辑相关
     *
     * @param iRegVehicleInfoView
     */

    private LogicVehicle logicVehicle;
    private LogicRegister logicRegister;
    private LogicPhoto logicPhoto;


    /**
     * 图片上传相关
     *
     * @param iRegUserInfoView
     */

    private IOssUpload iOssUpload;
    private OSSAdhClient ossAdhClient;
    private OssConfig ossConfig;


    public TempRegDriverVehicle getTempRegDriverVehicle() {
        return tempRegDriverVehicle;
    }

    public PresenterDriverVehicleReg(IRegVehicleInfoView iRegVehicleInfoView) {
        this.iRegVehicleInfoView = iRegVehicleInfoView;
        this.iVehicle = new IVehicleImpl();
        this.iRegister = new IRegisterImpl();


        /**
         * 解析相关
         */
        this.vertifyNotNull = new VertifyNotNull();
        this.parseSerilizable = new ParseSerilizable();


        /**
         * 数据类相关
         */
        this.tempRegDriverVehicle = new TempRegDriverVehicle();

        this.logicVehicle = new LogicVehicle();
        this.logicRegister = new LogicRegister();
        this.logicPhoto = new LogicPhoto();
        this.iOssUpload = new IOssUploadImpl();
    }


    /**
     * 获取vehicle类型列表
     *
     * @param url
     * @param accessToken
     */
    public void doGetVehicleTypes(String url, String accessToken) {


        iVehicle.doGetVehicleTypes(url, accessToken, new OnDataBackListener() {
            @Override
            public void onStart() {
                iRegVehicleInfoView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {


                List<VehicleType> list = parseSerilizable.getParseToNoItemsList(data, VehicleType.class);

                if (vertifyNotNull.isNotNullListSize(list)) {

                    iRegVehicleInfoView.onDataBackSuccessForVehicleTypes(list);

                } else {
                    iRegVehicleInfoView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);

                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    iRegVehicleInfoView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iRegVehicleInfoView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {

                iRegVehicleInfoView.dismissLoadingDialog();
            }
        });


    }


    /**
     * 获取指定车辆的catogrty
     *
     * @param url
     * @param accessToken
     */
    public void doGetVehicleCatogories(String url, String accessToken) {


        iVehicle.doGetVehicleCategories(url, accessToken, new OnDataBackListener() {
            @Override
            public void onStart() {
                iRegVehicleInfoView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {


                currentVehicleCategoryList = parseSerilizable.getParseToNoItemsList(data, VehicleCategory.class);
                iRegVehicleInfoView.doClearCatogroyCheck();
                tempRegDriverVehicle.setCategory_name(null);

                if (vertifyNotNull.isNotNullListSize(currentVehicleCategoryList)) {

                    iRegVehicleInfoView.doShowVehicleCatogoryLayout();
                    iRegVehicleInfoView.doSetVehicleCatogoriesToRG(currentVehicleCategoryList);

                } else {

                    tempRegDriverVehicle.setCategory_name(null);
                    currentVehicleCategory = null;

                }


            }

            @Override
            public void onFail(int code, String data) {


                currentVehicleCategoryList = null;
                currentVehicleCategory = null;
                tempRegDriverVehicle.setCategory_name(null);
                iRegVehicleInfoView.doClearCatogroyCheck();


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iRegVehicleInfoView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iRegVehicleInfoView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {

                iRegVehicleInfoView.dismissLoadingDialog();

            }
        });


    }


    /**
     * 点击完成车辆信息
     *
     * @param url
     * @param accessToken
     * @param brand
     * @param capacity
     * @param volume
     * @param carNum
     * @param
     */
    public void doCompleteDriverVehicleInfo(String url, String accessToken, String brand, String capacity, String volume, String carNum) {

        if (logicVehicle.isNullVehicleBrand(brand)) {
            iRegVehicleInfoView.doVertifyErrorNullForVehicleBrand();
            return;
        }

        if (logicVehicle.isNullVehicleBrandWithListAndCatogray(currentVehicleCategoryList, currentVehicleCategory, tempRegDriverVehicle.getCategory_name())) {
            iRegVehicleInfoView.doVertifyErrorNullForVehicleType();
            return;
        }

        if (logicVehicle.isNullVehicleBrandWithList(currentVehicleCategoryList, currentVehicleCategory)) {
            iRegVehicleInfoView.doVertifyErrorNullForVehicleType();
            return;
        }

        if (logicVehicle.isNullCapacity(capacity)) {
            iRegVehicleInfoView.doVertifyErrorNullForVehicleCapacity();
            return;
        } else {
            if (logicVehicle.is0ForCapacityHasPoint(capacity)) {
                iRegVehicleInfoView.doVertifyErrorNot0ForVehicleCapacity();
                return;
            } else if (logicVehicle.is0ForCapacityNoPoint(capacity)) {
                iRegVehicleInfoView.doVertifyErrorNot0ForVehicleCapacity();
                return;
            }
        }


        if (logicVehicle.isNullVolume(volume)) {
            iRegVehicleInfoView.doVertifyErrorNullForVehicleVolume();
            return;
        } else {
            if (logicVehicle.is0ForVolumeHasPoint(volume)) {
                iRegVehicleInfoView.doVertifyErrorNot0ForVehicleVolume();
                return;
            } else if (logicVehicle.is0ForVolumeNoPoint(volume)) {
                iRegVehicleInfoView.doVertifyErrorNot0ForVehicleVolume();
                return;
            }

        }

        if (logicVehicle.isNullVehicleNo(carNum)) {

            iRegVehicleInfoView.doVertifyErrorNullForVehicleNum();
            return;
        }


        if (logicVehicle.isNullDriverLicence(tempRegDriverVehicle.getDriver_license())) {
            iRegVehicleInfoView.doVertifyErrorNullForDriverListence();
            return;
        }

        if (logicVehicle.isNullVechleListence(tempRegDriverVehicle.getVehicle_license())) {
            iRegVehicleInfoView.doVertifyErrorNullForVehicleLicence();
            return;
        }

        if (logicVehicle.isNullVehiclePhotoFrontPath(tempRegDriverVehicle.getVehicle_photo_front_path())) {
            iRegVehicleInfoView.doVertifyErrorNullForVehiclePhotoFrontPath();
            return;
        }
        if (logicVehicle.isNullVehiclePhotoBackPath(tempRegDriverVehicle.getVehicle_photo_back_path())) {

            iRegVehicleInfoView.doVertifyErrorNullForVehiclePhotoBackPath();
            return;
        }

        if (logicVehicle.isNullVehicleInsurancePolicy(tempRegDriverVehicle.getCar_insurance_policy())) {
            iRegVehicleInfoView.doVertifyErrorNullForVehicleInsurancePolicy();
            return;
        }

        if (logicVehicle.isNullDriverPhotoPath(tempRegDriverVehicle.getDriver_photo_path())) {

            iRegVehicleInfoView.doVertifyErrorNullForDriverPhotoPath();
            return;
        }


        tempRegDriverVehicle.setVehicle_no(carNum);
        tempRegDriverVehicle.setVehicle_dead_weight(capacity);
        tempRegDriverVehicle.setVehicle_stere(volume);


        iVehicle.doCompleteVehicleInfo(url, accessToken, tempRegDriverVehicle, new OnDataBackListener() {
            @Override
            public void onStart() {
                iRegVehicleInfoView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                RegDriverComplete regDriverComplete = parseSerilizable.getParseToObj(data, RegDriverComplete.class);

                if (vertifyNotNull.isNotNullObj(regDriverComplete)) {


                    iRegVehicleInfoView.onDataBackSuccessForCompleteDriver(regDriverComplete);

                } else {


                    iRegVehicleInfoView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);

                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iRegVehicleInfoView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iRegVehicleInfoView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {
                iRegVehicleInfoView.dismissLoadingDialog();
            }
        });


    }


    /**
     * 获取注册状态
     *
     * @param url
     * @param accessToken
     */
    public void doCheckRegStatues(String url, String accessToken) {

        iRegister.doGetRegStatue(url, accessToken, new OnDataBackListener() {
            @Override
            public void onStart() {

                iRegVehicleInfoView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                RegConfirm regConfirm = parseSerilizable.getParseToObj(data, RegConfirm.class);

                if (vertifyNotNull.isNotNullObj(regConfirm)) {

                    if (logicRegister.isExamPass(regConfirm.isPassed())) {

                        iRegVehicleInfoView.doExamPassToMain();

                    } else {

                        iRegVehicleInfoView.doGoToLearn();
                    }


                } else {
                    iRegVehicleInfoView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iRegVehicleInfoView.onDataBackFail(code, errorEntity.getError_message());
                } else {

                    iRegVehicleInfoView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {

                iRegVehicleInfoView.dismissLoadingDialog();
            }
        });

    }


    /**
     * 设置catogry
     */
    public void doCheckSetCategory(VehicleCategory vehicleCategory) {

        if (vertifyNotNull.isNotNullList(currentVehicleCategoryList)) {
            currentVehicleCategory = vehicleCategory;
            tempRegDriverVehicle.setCategory_name(currentVehicleCategory.getCategory_name());
        }

    }


    /**
     * 权限检查
     */
    public void doPermissionCheck() {

        iRegVehicleInfoView.doPermissionCheck();

    }

    /**
     * 权限关闭提醒
     */
    public void doShowPermissionAlert() {

        iRegVehicleInfoView.doShowPermissionAlert();

    }


    /**
     * 显示photopopWindow
     */

    public void doShowPhotoPopWindow() {
        iRegVehicleInfoView.doShowPhotoPopWindow();
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

            if (logicPhoto.isVehicleInsurancePolicyInRegVehicle(viewId)) {
                path = result.getImage().getOriginalPath();
            }
        }

        Log.i(ConstLog.PHOTOS, "当前线程Id" + Thread.currentThread().getId() + " " + path);


        PutObjectRequest putObjectRequest = new PutObjectRequest(ossConfig.getBucket_name(), ossConfig.getObject_key() + PictureUtil.reNamePicture(), path);

        putObjectRequest.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                iRegVehicleInfoView.ossOnProgress(request, currentSize, totalSize);
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

                    case R.id.iv_upload_line1_left:


                        tempRegDriverVehicle.setDriver_license(url);
                        iRegVehicleInfoView.ossOnSuccessDriverLicenseToUi(url, R.drawable.img_upload_bg);

                        break;


                    case R.id.iv_upload_line1_right:


                        tempRegDriverVehicle.setVehicle_license(url);
                        iRegVehicleInfoView.ossOnSuccessVehicleLicensToUi(url, R.drawable.img_upload_bg);
                        break;

                    case R.id.iv_upload_line2_left:


                        tempRegDriverVehicle.setVehicle_photo_front_path(url);
                        iRegVehicleInfoView.ossOnSuccessVehiclePhotoFrontToUi(url, R.drawable.img_upload_bg);
                        break;

                    case R.id.iv_upload_line2_right:


                        tempRegDriverVehicle.setVehicle_photo_back_path(url);
                        iRegVehicleInfoView.ossOnSuccessVehiclePhotoBackToUi(url, R.drawable.img_upload_bg);

                        break;

                    case R.id.iv_upload_line3_left:

                        tempRegDriverVehicle.setCar_insurance_policy(url);
                        iRegVehicleInfoView.ossOnSuccessVehicleInsurancePolicyToUi(url, R.drawable.img_upload_bg);

                        break;

                    case R.id.iv_upload_line3_right:

                        tempRegDriverVehicle.setBusiness_insurance(url);

                        iRegVehicleInfoView.ossOnSuccessBusinessInsuranceToUi(url, R.drawable.img_upload_bg);

                        break;

                    case R.id.iv_upload_line4:

                        tempRegDriverVehicle.setDriver_photo_path(url);
                        iRegVehicleInfoView.ossOnSuccessDriverPhotoPathToUi(url, R.drawable.img_upload_bg);
                        break;

                }
            }

            @Override
            public void onOssFailure(String requestObjectKey, String e, String e1) {

                iRegVehicleInfoView.ossOnFailure();

            }


        });

    }


}
