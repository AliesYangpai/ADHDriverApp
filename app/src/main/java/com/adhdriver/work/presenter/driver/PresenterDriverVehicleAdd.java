package com.adhdriver.work.presenter.driver;

import android.content.Context;
import android.util.Log;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.constant.ConstLog;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.OssConfig;
import com.adhdriver.work.entity.driver.temp.TempRegDriverVehicle;
import com.adhdriver.work.entity.driver.vehicle.VehicleCategory;
import com.adhdriver.work.entity.driver.vehicle.VehicleType;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.http.upload.IOssUpload;
import com.adhdriver.work.http.upload.IOssUploadImpl;
import com.adhdriver.work.http.upload.OSSAdhClient;
import com.adhdriver.work.http.upload.OnOssUploadListener;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.logic.LogicPhoto;
import com.adhdriver.work.logic.LogicRegister;
import com.adhdriver.work.logic.LogicVehicle;
import com.adhdriver.work.method.IVehicle;
import com.adhdriver.work.method.impl.IVehicleImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IVehicleAddView;
import com.adhdriver.work.utils.PictureUtil;
import com.adhdriver.work.verification.VertifyNotNull;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.jph.takephoto.model.TResult;

import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 * 类描述
 * 版本
 */

public class PresenterDriverVehicleAdd extends BasePresenter<IVehicleAddView> {

    private IVehicleAddView iVehicleAddView;
    private IVehicle iVehicle;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;


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

    public void setTempRegDriverVehicle(TempRegDriverVehicle tempRegDriverVehicle) {
        this.tempRegDriverVehicle = tempRegDriverVehicle;
    }

    public PresenterDriverVehicleAdd(IVehicleAddView iVehicleAddView) {
        this.iVehicleAddView = iVehicleAddView;
        this.iVehicle = new IVehicleImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();


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
        this.logicPhoto = new LogicPhoto();
        this.iOssUpload = new IOssUploadImpl();
    }


    /**
     * 获取vehicle类型列表
     *
     * @param url
     * @param
     */
    public void doGetVehicleTypes(String url) {


        iVehicle.doGetVehicleTypes(url, new OnDataBackListener() {
            @Override
            public void onStart() {
                iVehicleAddView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {


                List<VehicleType> list = parseSerilizable.getParseToNoItemsList(data, VehicleType.class);

                if (vertifyNotNull.isNotNullListSize(list)) {

                    iVehicleAddView.onDataBackSuccessForVehicleTypes(list);

                } else {
                    iVehicleAddView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);

                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    iVehicleAddView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iVehicleAddView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {

                iVehicleAddView.dismissLoadingDialog();
            }
        });


    }


    /**
     * 获取指定车辆的catogrty
     *
     * @param url
     * @param
     */
    public void doGetVehicleCatogories(String url) {


        iVehicle.doGetVehicleCategories(url, new OnDataBackListener() {
            @Override
            public void onStart() {
                iVehicleAddView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {


                currentVehicleCategoryList = parseSerilizable.getParseToNoItemsList(data, VehicleCategory.class);
                iVehicleAddView.doClearCatogroyCheck();
                tempRegDriverVehicle.setCategory_name(null);

                if (vertifyNotNull.isNotNullListSize(currentVehicleCategoryList)) {

                    iVehicleAddView.doShowVehicleCatogoryLayout();
                    iVehicleAddView.doSetVehicleCatogoriesToRG(currentVehicleCategoryList);

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
                iVehicleAddView.doClearCatogroyCheck();


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iVehicleAddView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iVehicleAddView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {

                iVehicleAddView.dismissLoadingDialog();

            }
        });


    }


    /**
     * 添加车辆的接口
     *
     * @param url
     * @param brand
     * @param capacity
     * @param volume
     * @param carNum
     */
    public void doAddVehicle(String url,
                             String brand,
                             String capacity,
                             String volume,
                             String carNum) {


        if (logicVehicle.isNullVehicleBrand(brand)) {
            iVehicleAddView.doVertifyErrorNullForVehicleBrand();
            return;
        }

        if (logicVehicle.isNullVehicleBrandWithListAndCatogray(currentVehicleCategoryList, currentVehicleCategory, tempRegDriverVehicle.getCategory_name())) {
            iVehicleAddView.doVertifyErrorNullForVehicleType();
            return;
        }

        if (logicVehicle.isNullVehicleBrandWithList(currentVehicleCategoryList, currentVehicleCategory)) {
            iVehicleAddView.doVertifyErrorNullForVehicleType();
            return;
        }

        if (logicVehicle.isNullCapacity(capacity)) {
            iVehicleAddView.doVertifyErrorNullForVehicleCapacity();
            return;
        } else {
            if (logicVehicle.is0ForCapacityHasPoint(capacity)) {
                iVehicleAddView.doVertifyErrorNot0ForVehicleCapacity();
                return;
            } else if (logicVehicle.is0ForCapacityNoPoint(capacity)) {
                iVehicleAddView.doVertifyErrorNot0ForVehicleCapacity();
                return;
            }
        }


        if (logicVehicle.isNullVolume(volume)) {
            iVehicleAddView.doVertifyErrorNullForVehicleVolume();
            return;
        } else {
            if (logicVehicle.is0ForVolumeHasPoint(volume)) {
                iVehicleAddView.doVertifyErrorNot0ForVehicleVolume();
                return;
            } else if (logicVehicle.is0ForVolumeNoPoint(volume)) {
                iVehicleAddView.doVertifyErrorNot0ForVehicleVolume();
                return;
            }

        }

        if (logicVehicle.isNullVehicleNo(carNum)) {

            iVehicleAddView.doVertifyErrorNullForVehicleNum();
            return;
        }


        if (logicVehicle.isNullVechleListence(tempRegDriverVehicle.getVehicle_license())) {
            iVehicleAddView.doVertifyErrorNullForVehicleLicence();
            return;
        }

        if (logicVehicle.isNullVehiclePhotoFrontPath(tempRegDriverVehicle.getVehicle_photo_front_path())) {
            iVehicleAddView.doVertifyErrorNullForVehiclePhotoFrontPath();
            return;
        }
        if (logicVehicle.isNullVehiclePhotoBackPath(tempRegDriverVehicle.getVehicle_photo_back_path())) {

            iVehicleAddView.doVertifyErrorNullForVehiclePhotoBackPath();
            return;
        }

        if (logicVehicle.isNullVehicleInsurancePolicy(tempRegDriverVehicle.getCar_insurance_policy())) {
            iVehicleAddView.doVertifyErrorNullForVehicleInsurancePolicy();
            return;
        }


        tempRegDriverVehicle.setVehicle_dead_weight(capacity);
        tempRegDriverVehicle.setVehicle_stere(volume);
        tempRegDriverVehicle.setVehicle_no(carNum);


        iVehicle.doAddVehicle(url,
                tempRegDriverVehicle, new OnDataBackListener() {
                    @Override
                    public void onStart() {

                        iVehicleAddView.showLoadingDialog();

                    }

                    @Override
                    public void onSuccess(String data) {

                        iVehicleAddView.onDataBackSuccessForAddVehicle();

                    }

                    @Override
                    public void onFail(int code, String data) {

                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                        if (vertifyNotNull.isNotNullObj(errorEntity)) {
                            iVehicleAddView.onDataBackFail(code, errorEntity.getError_message());
                        } else {
                            iVehicleAddView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                        }

                    }

                    @Override
                    public void onFinish() {

                        iVehicleAddView.dismissLoadingDialog();

                    }
                });

    }


    /**
     * 设置catogry
     */
    public void doCheckSetCategory(VehicleCategory vehicleCategory) {

//        if (vertifyNotNull.isNotNullList(currentVehicleCategoryList)) {
//            currentVehicleCategory = vehicleCategory;
//            tempRegDriverVehicle.setCategory_name(currentVehicleCategory.getCategory_name());
//        }

        currentVehicleCategory = vehicleCategory;
        tempRegDriverVehicle.setCategory_name(currentVehicleCategory.getCategory_name());



    }


    /**
     * 权限检查
     */
    public void doPermissionCheck() {

        iVehicleAddView.doPermissionCheck();

    }

    /**
     * 权限关闭提醒
     */
    public void doShowPermissionAlert() {

        iVehicleAddView.doShowPermissionAlert();

    }


    /**
     * 显示photopopWindow
     */

    public void doShowPhotoPopWindow() {
        iVehicleAddView.doShowPhotoPopWindow();
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

            if (logicPhoto.isVehicleInsurancePolicyInAddVehicle(viewId)) {
                path = result.getImage().getOriginalPath();
            }
        }

        Log.i(ConstLog.PHOTOS, "当前线程Id" + Thread.currentThread().getId() + " " + path);


        PutObjectRequest putObjectRequest = new PutObjectRequest(ossConfig.getBucket_name(), ossConfig.getObject_key() + PictureUtil.reNamePicture(), path);

        putObjectRequest.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                iVehicleAddView.ossOnProgress(request, currentSize, totalSize);
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

                        tempRegDriverVehicle.setVehicle_photo_front_path(url);
                        iVehicleAddView.ossOnSuccessVehiclePhotoFrontToUi(url, R.drawable.img_upload_bg);


                        break;


                    case R.id.iv_upload_line1_right:

                        tempRegDriverVehicle.setVehicle_photo_back_path(url);
                        iVehicleAddView.ossOnSuccessVehiclePhotoBackToUi(url, R.drawable.img_upload_bg);
                        break;

                    case R.id.iv_upload_line2_left:


                        tempRegDriverVehicle.setCar_insurance_policy(url);
                        iVehicleAddView.ossOnSuccessVehicleInsurancePolicyToUi(url, R.drawable.img_upload_bg);
                        break;

                    case R.id.iv_upload_line2_right:


                        tempRegDriverVehicle.setBusiness_insurance(url);
                        iVehicleAddView.ossOnSuccessBusinessInsuranceToUi(url, R.drawable.img_upload_bg);

                        break;


                    case R.id.iv_upload_line3_right:

                        tempRegDriverVehicle.setVehicle_license(url);
                        iVehicleAddView.ossOnSuccessVehicleLicensToUi(url, R.drawable.img_upload_bg);

                        break;


                }
            }

            @Override
            public void onOssFailure(String requestObjectKey, String e, String e1) {
                iVehicleAddView.ossOnFailure();
            }

        });

    }
}
