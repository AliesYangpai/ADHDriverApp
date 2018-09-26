package com.adhdriver.work.presenter.driver;

import android.content.Context;
import android.util.Log;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.constant.ConstLog;
import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.dao.impl.IClearDataDao;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.OssConfig;
import com.adhdriver.work.entity.driver.temp.TempRegDriverVehicle;
import com.adhdriver.work.entity.driver.token.TokenInfo;
import com.adhdriver.work.entity.driver.vehicle.DriverVehicle;
import com.adhdriver.work.entity.driver.vehicle.LogonVehicle;
import com.adhdriver.work.entity.driver.vehicle.VehicleCategory;
import com.adhdriver.work.entity.driver.vehicle.VehicleType;
import com.adhdriver.work.function.FunctionVehicle;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.http.upload.IOssUpload;
import com.adhdriver.work.http.upload.IOssUploadImpl;
import com.adhdriver.work.http.upload.OSSAdhClient;
import com.adhdriver.work.http.upload.OnOssUploadListener;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.logic.LogicPhoto;
import com.adhdriver.work.logic.LogicVehicle;
import com.adhdriver.work.method.IDriverAbout;
import com.adhdriver.work.method.IVehicle;
import com.adhdriver.work.method.IVisitorAbout;
import com.adhdriver.work.method.impl.IDriverAboutImpl;
import com.adhdriver.work.method.impl.IVehicleImpl;
import com.adhdriver.work.method.impl.IVisitorAboutImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IVehicleEditView;
import com.adhdriver.work.utils.PictureUtil;
import com.adhdriver.work.utils.SpUtil;
import com.adhdriver.work.verification.VertifyNotNull;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.jph.takephoto.model.TResult;

import org.feezu.liuli.timeselector.Utils.TextUtil;

import java.util.List;

/**
 * Created by Administrator on 2018/1/16.
 * 类描述  车里编辑的presnter
 * 版本
 */

public class PresenterDriverVehicleEdit extends BasePresenter<IVehicleEditView> {


    private IVehicleEditView iVehicleEditView;
    private IVehicle iVehicle;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;


    /**
     * 逻辑相关
     *
     * @param iRegVehicleInfoView
     */

    private LogicVehicle logicVehicle;
    private LogicPhoto logicPhoto;

    private IDriverAbout iDriverAbout;
    private IVisitorAbout iVisitorAbout;

    /**
     * 图片上传相关
     *
     * @param iRegUserInfoView
     */

    private IOssUpload iOssUpload;
    private OSSAdhClient ossAdhClient;
    private OssConfig ossConfig;


    /**
     * 数据相关
     *
     * @param iVehicleEditView
     */


    private DriverVehicle driverVehicleTemp; //缓存数据

    private FunctionVehicle functionVehicle;


    private IClearDataDao iClearDataDao;

    public DriverVehicle getDriverVehicleTemp() {
        return driverVehicleTemp;
    }

    public PresenterDriverVehicleEdit(IVehicleEditView iVehicleEditView) {
        this.iVehicleEditView = iVehicleEditView;
        this.iVehicle = new IVehicleImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();

        this.logicVehicle = new LogicVehicle();
        this.logicPhoto = new LogicPhoto();

        this.iOssUpload = new IOssUploadImpl();
        this.driverVehicleTemp = new DriverVehicle();

        iDriverAbout = new IDriverAboutImpl();

        iVisitorAbout = new IVisitorAboutImpl();
        this.functionVehicle = new FunctionVehicle();
        this.iClearDataDao  = new IClearDataDao();
    }


//    /**
//     * 设置catogry
//     */
//    public void doCheckSetCategory(VehicleCategory vehicleCategory) {
//
//        if (vertifyNotNull.isNotNullList(currentVehicleCategoryList)) {
//            currentVehicleCategory = vehicleCategory;
//            tempRegDriverVehicle.setCategory_name(currentVehicleCategory.getCategory_name());
//        }
//
//    }


    /**
     * 设置数据到界面
     *
     * @param driverVehicle
     */
    public void doSetDataToUi(DriverVehicle driverVehicle) {

        if (vertifyNotNull.isNotNullObj(driverVehicle)) {

            driverVehicleTemp.setVehicle_license(driverVehicle.getVehicle_license());//行驶证
            driverVehicleTemp.setVehicle_photo_front_path(driverVehicle.getVehicle_photo_front_path());//车头照片
            driverVehicleTemp.setVehicle_photo_back_path(driverVehicle.getVehicle_photo_back_path());//车尾照片
            driverVehicleTemp.setCar_insurance_policy(driverVehicle.getCar_insurance_policy());//车辆保单地址
            driverVehicleTemp.setBusiness_insurance(driverVehicle.getBusiness_insurance());//商业保险地址


            iVehicleEditView.doSetDataToUi(driverVehicle);

            if (logicVehicle.isNullCatogroy(driverVehicle.getCategory_name())) {

                iVehicleEditView.doHindVehicleCatograyLayout();

            } else {

                iVehicleEditView.doShowVehicleCatogoryLayout();

                iVehicleEditView.doSetLocalDataToRbGroup(functionVehicle.getLocalVehicleCatagoryList());

                iVehicleEditView.doSetCurrentCategoryNameToRb(driverVehicle.getCategory_name());
            }

        }


    }


    /**
     * 权限检查
     */
    public void doPermissionCheck() {

        iVehicleEditView.doPermissionCheck();

    }

    /**
     * 权限关闭提醒
     */
    public void doShowPermissionAlert() {

        iVehicleEditView.doShowPermissionAlert();

    }


    /**
     * 显示photopopWindow
     */

    public void doShowPhotoPopWindow() {
        iVehicleEditView.doShowPhotoPopWindow();
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
                iVehicleEditView.ossOnProgress(request, currentSize, totalSize);
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

                        driverVehicleTemp.setVehicle_photo_front_path(url);
                        iVehicleEditView.ossOnSuccessVehiclePhotoFrontToUi(url, R.drawable.img_upload_bg);


                        break;


                    case R.id.iv_upload_line1_right:

                        driverVehicleTemp.setVehicle_photo_back_path(url);
                        iVehicleEditView.ossOnSuccessVehiclePhotoBackToUi(url, R.drawable.img_upload_bg);
                        break;

                    case R.id.iv_upload_line2_left:


                        driverVehicleTemp.setCar_insurance_policy(url);
                        iVehicleEditView.ossOnSuccessVehicleInsurancePolicyToUi(url, R.drawable.img_upload_bg);
                        break;

                    case R.id.iv_upload_line2_right:


                        driverVehicleTemp.setBusiness_insurance(url);
                        iVehicleEditView.ossOnSuccessBusinessInsuranceToUi(url, R.drawable.img_upload_bg);

                        break;


                    case R.id.iv_upload_line3_right:

                        driverVehicleTemp.setVehicle_license(url);
                        iVehicleEditView.ossOnSuccessVehicleLicensToUi(url, R.drawable.img_upload_bg);

                        break;


                }
            }

            @Override
            public void onOssFailure(String requestObjectKey, String e, String e1) {
                iVehicleEditView.ossOnFailure();
            }

        });

    }


    /**
     * 判断更新
     *
     * @param driverVehicleId
     */
    public void doCheckCurrentLogonVehicleAndDoNext(int driverVehicleId) {

        if (logicVehicle.isCurrentLogonVehicle(driverVehicleId)) {

            iVehicleEditView.doUpdateIsCurrent();


        } else {

            iVehicleEditView.doUpdate();

        }


    }


    public void doUpdate(String url,
                         String hintCapacity,
                         String hintVolume,
                         String textCapacity,
                         String textVolume) {



        if( !logicVehicle.isNullCapacity(textCapacity)) {
            if(!logicVehicle.isNullVolume(textVolume)){

                driverVehicleTemp.setVehicle_dead_weight(textCapacity);
                driverVehicleTemp.setVehicle_stere(textVolume);
            }else {
                driverVehicleTemp.setVehicle_dead_weight(textCapacity);
                driverVehicleTemp.setVehicle_stere(hintVolume);

            }

        }else {

            if(logicVehicle.isNullVolume(textVolume)) {

                driverVehicleTemp.setVehicle_dead_weight(hintCapacity);
                driverVehicleTemp.setVehicle_stere(textVolume);
            }else {
                driverVehicleTemp.setVehicle_dead_weight(hintCapacity);
                driverVehicleTemp.setVehicle_stere(hintVolume);
            }

        }




        iVehicle.doUpdateVehicle(
                url,
                driverVehicleTemp, new OnDataBackListener() {
                    @Override
                    public void onStart() {

                        iVehicleEditView.showLoadingDialog();
                    }

                    @Override
                    public void onSuccess(String data) {

                        iVehicleEditView.onDataBackSuccessForUpdateSuccess();

                    }

                    @Override
                    public void onFail(int code, String data) {

                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data,ErrorEntity.class);
                        if(vertifyNotNull.isNotNullObj(errorEntity)) {

                            iVehicleEditView.onDataBackFail(code,errorEntity.getError_message());
                        }else {
                            iVehicleEditView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                        }


                    }

                    @Override
                    public void onFinish() {

                        iVehicleEditView.dismissLoadingDialog();
                    }
                });

    }





    public void doUpdateCurrent(String url,
                         String hintCapacity,
                         String hintVolume,
                         String textCapacity,
                         String textVolume,DriverVehicle driverVehicleTemp) {



        if( !logicVehicle.isNullCapacity(textCapacity)) {
            if(!logicVehicle.isNullVolume(textVolume)){

                driverVehicleTemp.setVehicle_dead_weight(textCapacity);
                driverVehicleTemp.setVehicle_stere(textVolume);
            }else {
                driverVehicleTemp.setVehicle_dead_weight(textCapacity);
                driverVehicleTemp.setVehicle_stere(hintVolume);

            }

        }else {

            if(logicVehicle.isNullVolume(textVolume)) {

                driverVehicleTemp.setVehicle_dead_weight(hintCapacity);
                driverVehicleTemp.setVehicle_stere(textVolume);
            }else {
                driverVehicleTemp.setVehicle_dead_weight(hintCapacity);
                driverVehicleTemp.setVehicle_stere(hintVolume);
            }

        }




        iVehicle.doUpdateVehicle(
                url,
                driverVehicleTemp, new OnDataBackListener() {
                    @Override
                    public void onStart() {

                        iVehicleEditView.showLoadingDialog();
                    }

                    @Override
                    public void onSuccess(String data) {

                        iVehicleEditView.onDataBackSuccessForUpdateCurrentSuccess();

                    }

                    @Override
                    public void onFail(int code, String data) {

                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data,ErrorEntity.class);
                        if(vertifyNotNull.isNotNullObj(errorEntity)) {

                            iVehicleEditView.onDataBackFail(code,errorEntity.getError_message());
                        }else {
                            iVehicleEditView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                        }

                        iVehicleEditView.dismissLoadingDialog();
                    }

                    @Override
                    public void onFinish() {


                    }
                });

    }


    /**
     * 关闭推送
     *
     * @param url
     * @param os_type
     * @param os_version
     * @param model
     * @param device_id
     * @param push_type
     * @param push_token
     * @param quiet_mode
     */
    public void doLogout(String url,
                         String os_type,
                         String os_version,
                         String model,
                         String device_id,
                         String push_type,
                         String push_token,
                         boolean quiet_mode) {


        iDriverAbout.doClosePush(url,
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



                        iVehicleEditView.onDataBackSuccessForClosePush();

                    }

                    @Override
                    public void onFail(int code, String data) {


                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                        if (vertifyNotNull.isNotNullObj(errorEntity)) {

                            iVehicleEditView.onDataBackFail(code, errorEntity.getError_message());
                        } else {

                            iVehicleEditView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                        }

                        iVehicleEditView.dismissLoadingDialog();

                    }

                    @Override
                    public void onFinish() {


                    }
                });

    }


    public void doInitVisitorLogon(String url) {

        iVisitorAbout.doVisitorLogon(url, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {


                TokenInfo tokenInfo = parseSerilizable.getParseToObj(data, TokenInfo.class);

                if (vertifyNotNull.isNotNullObj(tokenInfo)) {

                    String access_token = tokenInfo.getAccess_token();

                    SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_TOKEN, access_token); //这个是 visitor的token
                    SpUtil.getInstance().saveBooleanTosp(ConstSp.SP_KEY_VISITOR, ConstSp.SP_VALUE.DEFAULT_TRUE_VISITOR);

                    iVehicleEditView.onDataBackSuccessForGetVisitorToken();

                } else {

                    iVehicleEditView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    iVehicleEditView.onDataBackFail(code, errorEntity.getError_message());

                } else {

                    iVehicleEditView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);

                }


            }

            @Override
            public void onFinish() {

                iVehicleEditView.dismissLoadingDialog();
            }
        });

    }



    public void doClearAll() {

        iClearDataDao.doClearAllTable();

        iClearDataDao.doClearSp();

    }



}
