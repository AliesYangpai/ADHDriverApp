package com.adhdriver.work.ui.activity.driver;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.PhotoPopwindowCallBack;
import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.constant.ConstLog;
import com.adhdriver.work.entity.driver.photo.PhotoConfig;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.presenter.driver.PresenterDriverRegUserInfo;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.iview.driver.IRegUserInfoView;
import com.adhdriver.work.utils.ImgUtil;
import com.adhdriver.work.utils.ToastUtil;
import com.adhdriver.work.ui.widget.popwindow.PhotoPopWindow;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.model.TakePhotoOptions;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.io.File;
import java.util.List;

public class InfoRegDriverActivity extends BaseActivity<IRegUserInfoView, PresenterDriverRegUserInfo> implements
        IRegUserInfoView,
        View.OnClickListener,
        TakePhoto.TakeResultListener,
        InvokeListener,
        PhotoPopwindowCallBack,
        PermissionListener {


    private PresenterDriverRegUserInfo presenterDriverRegUserInfo;


    /**
     * title
     *
     * @param savedInstanceState
     */

    private ImageView iv_common_back;
    private TextView tv_common_title;
    private ImageView iv_common_search;

    /**
     * 中间部分
     *
     * @param savedInstanceState
     */

    private EditText et_reg_name;     //司机姓名
    private EditText et_identify_id;  //身份证号
    private EditText et_inviter_num;  //邀请人号码


    /**
     * 底部提交
     *
     * @param savedInstanceState
     */
    private ImageView iv_upload_identify_left;
    private ImageView iv_upload_identify_right;
    private LinearLayout ll_diver_info_go_next;//点击下一步


    /**
     * 数据相关
     *
     * @param savedInstanceState
     */
    private String currentToken;
    private int currentViewId;

    /**
     * 图片相关
     *
     * @param savedInstanceState
     */

    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    private PhotoConfig photoConfig;

    /**
     * 弹出控件相关
     *
     * @param savedInstanceState
     */


    private PhotoPopWindow photoPopWindow;


    /**
     * 获取TakePhoto实例
     *
     * @return
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }

    /**
     * 获得裁剪的相关实体类
     *
     * @return
     */
    private PhotoConfig getPhotoConfig() {

        if (photoConfig == null) {

            photoConfig = new PhotoConfig();

            photoConfig.setCrop(false);
            photoConfig.setOwnCropTool(true);
            photoConfig.setCropWidth(800);
            photoConfig.setCropHight(800);


            /**
             * 压缩工具配置
             */
            photoConfig.setCompress(true);
            photoConfig.setCompressWidth(2500);
            photoConfig.setCompressHeight(2500);
            photoConfig.setMaxSize(102400);
            photoConfig.setShowCompressProcess(true);
            /**
             * 选择相片配置
             */
            photoConfig.setTakePhotoGallery(true);
            photoConfig.setMaxSelectPicCount(1);
            photoConfig.setFixRotationAngle(true);
            photoConfig.setSaveRawPic(true);


        }


        return photoConfig;

    }


    /**
     * 从相册从相机中获取 不裁剪
     */
    private void fromCamera() {

        takePhoto = getTakePhoto();
        photoConfig = getPhotoConfig();
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        Uri imageUri = Uri.fromFile(file);
        configCompress(takePhoto, photoConfig);  //设置压缩图片类型
        configTakePhotoOption(takePhoto, photoConfig); //使用takePhoto自带相册

        takePhoto.onPickFromCapture(imageUri);//不裁剪
//        takePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions(photoConfig));
    }

    /**
     * 从相册中获取 不裁剪
     */
    private void fromGallary() {

        takePhoto = getTakePhoto();
        photoConfig = getPhotoConfig();
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        Uri imageUri = Uri.fromFile(file);

        configCompress(takePhoto, photoConfig);  //设置压缩图片类型
        configTakePhotoOption(takePhoto, photoConfig); //使用takePhoto自带相册

        takePhoto.onPickFromGallery();  //不裁剪

//        takePhoto.onPickFromGalleryWithCrop(imageUri, getCropOptions(photoConfig));


    }


    /**
     * 编辑压缩图片
     *
     * @param takePhoto
     */
    private void configCompress(TakePhoto takePhoto, PhotoConfig photoConfig) {


        CompressConfig config;
        config = new CompressConfig.Builder()
                .setMaxSize(photoConfig.getMaxSize())
                .setMaxPixel(photoConfig.getCompressWidth() >= photoConfig.getCompressHeight() ? photoConfig.getCompressWidth() : photoConfig.getCompressHeight())
                .enableReserveRaw(photoConfig.isSaveRawPic())
                .create();
        takePhoto.onEnableCompress(config, photoConfig.isShowCompressProcess());

    }


    /**
     * 选择图片配置，使用系统takePhoto相册
     *
     * @param takePhoto
     */
    private void configTakePhotoOption(TakePhoto takePhoto, PhotoConfig photoConfig) {


        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
        builder.setWithOwnGallery(photoConfig.isTakePhotoGallery()); //使用takephoto自带相册
        builder.setCorrectImage(photoConfig.isFixRotationAngle());//纠正拍照旋转
        takePhoto.setTakePhotoOptions(builder.create());


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_reg_driver);
        presenterDriverRegUserInfo.doGetOssConfigInfo(HttpConst.URL.OSS_SERVER_INOF,currentToken);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

    @Override
    protected PresenterDriverRegUserInfo creatPresenter() {
        if (null == presenterDriverRegUserInfo) {
            presenterDriverRegUserInfo = new PresenterDriverRegUserInfo(this);
        }
        return presenterDriverRegUserInfo;
    }

    @Override
    protected void initViews() {

        /**
         * titile
         * @param savedInstanceState
         */
        iv_common_back = findADHViewById(R.id.iv_common_back);
        tv_common_title = findADHViewById(R.id.tv_common_title);
        iv_common_search = findADHViewById(R.id.iv_common_search);
        iv_common_search.setVisibility(View.GONE);
        tv_common_title.setText(this.getString(R.string.title_driver_info));


        /**
         * 中间部分
         * @param savedInstanceState
         */

        et_reg_name = findADHViewById(R.id.et_reg_name);     //司机姓名
        et_identify_id = findADHViewById(R.id.et_identify_id);  //身份证号
        et_inviter_num = findADHViewById(R.id.et_inviter_num);  //邀请人号码


        /**
         * 底部提交
         * @param savedInstanceState
         */

        iv_upload_identify_left = findADHViewById(R.id.iv_upload_identify_left);
        iv_upload_identify_right = findADHViewById(R.id.iv_upload_identify_right);
        ll_diver_info_go_next = findADHViewById(R.id.ll_diver_info_go_next);//点击下一步


    }

    @Override
    protected void initListener() {

        iv_common_back.setOnClickListener(this);


        iv_upload_identify_left.setOnClickListener(this);
        iv_upload_identify_right.setOnClickListener(this);

        ll_diver_info_go_next.setOnClickListener(this);

    }

    @Override
    protected void processIntent() {


        Intent intent = this.getIntent();
        if (null != intent) {

            Bundle bundle = intent.getExtras();
            if (null != bundle) {
                currentToken = bundle.getString(ConstIntent.BundleKEY.DELIVERY_ACCESS_TOKEN, ConstIntent.BundleValue.DEFAULT_STRING);
            }

        }

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
    public void doVertifyErrorNullDriverName() {
        ToastUtil.showMsg(getApplicationContext(), R.string.enter_driver_name);
    }

    @Override
    public void doVertifyErrorNullIdentifyNo() {
        ToastUtil.showMsg(getApplicationContext(), R.string.enter_identifyId);
    }

    @Override
    public void doVertifyErrorUnLegalIdentifyNo() {
        ToastUtil.showMsg(getApplicationContext(), R.string.enter_legal_identifyId);
    }

    @Override
    public void doVertifyErrorNullIdentifyFrontPic() {
        ToastUtil.showMsg(getApplicationContext(), R.string.update_front_indentify_img);
    }

    @Override
    public void doVertifyErrorNullIdentifyBackPic() {
        ToastUtil.showMsg(getApplicationContext(), R.string.update_back_indentify_img);
    }

    @Override
    public void onDataBackSuccessForOssConfig(String accessToken) {

    }

    @Override
    public void onDataBackSuccessForCompleteUserInfo(String accessToken) {

        presenterDriverRegUserInfo.doCheckRegStatues(HttpConst.URL.REGISTER_STATE, accessToken);

    }

    @Override
    public void onDataBackSuccessForSetVehicleInfo(String accessToken) {


        Bundle bundle = new Bundle();
        bundle.putString(ConstIntent.BundleKEY.DELIVERY_ACCESS_TOKEN, accessToken);
        openActivityAndFinishItself(InRegDriverVehicleActivity.class, bundle);
    }

    @Override
    public void onDataBackSuccessForExamPass() {

        openActivityAndFinishItself(MainDriverActivity.class, null);

    }

    @Override
    public void onDataBackSuccessForDoLearnAndExam(String accessToken) {
        Bundle bundle = new Bundle();
        bundle.putString(ConstIntent.BundleKEY.DELIVERY_ACCESS_TOKEN, accessToken);
        openActivityAndFinishItself(ReadLearnActivity.class, bundle);
    }

    @Override
    public void ossOnProgress(PutObjectRequest request, long currentSize, long totalSize) {

        Log.i("oss", "上传中：" + "currentSize: " + currentSize + " totalSize: " + totalSize + "当前线程：" + Thread.currentThread().getId());
    }

    @Override
    public void ossOnSuccessLoadIdFrontPicToUi(String url, int drawableId) {
        ToastUtil.showMsg(getApplicationContext(), R.string.upload_success);
        Log.i("oss", "上传成功：" + "图片全路径：" + url + "当前线程：" + Thread.currentThread().getId());
        ImgUtil.getInstance().getImgFromNetByUrl(url, iv_upload_identify_left, drawableId);
    }

    @Override
    public void ossOnSuccessLoadIdBackPicToUi(String url, int drawableId) {

        ToastUtil.showMsg(getApplicationContext(), R.string.upload_success);
        Log.i("oss", "上传成功：" + "图片全路径：" + url + "当前线程：" + Thread.currentThread().getId());
        ImgUtil.getInstance().getImgFromNetByUrl(url, iv_upload_identify_right, drawableId);
    }


    @Override
    public void ossOnFailure() {
        ToastUtil.showMsg(getApplicationContext(), R.string.upload_fail);
    }


    /**
     * 权限检查
     */
    @Override
    public void doPermissionCheck() {
        AndPermission
                .with(this)
                .requestCode(ConstIntent.RequestCode.APPLY_FOR_PERMISSION)
                .permission(Manifest.permission.CAMERA)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, final Rationale rationale) {
                        AndPermission.rationaleDialog(InfoRegDriverActivity.this, rationale).show();
                    }
                })
                .callback(this)
                .start();
    }


    /**
     * 权限提醒
     */
    @Override
    public void doShowPermissionAlert() {
        AndPermission.defaultSettingDialog(InfoRegDriverActivity.this, ConstIntent.RequestCode.SYSYEM_SETTING)
                .setTitle(InfoRegDriverActivity.this.getString(R.string.permission_title))
                .setMessage(InfoRegDriverActivity.this.getString(R.string.permission_msg))
                .setPositiveButton(InfoRegDriverActivity.this.getString(R.string.permission_sure))
                .setNegativeButton(InfoRegDriverActivity.this.getString(R.string.permission_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    @Override
    public void doShowPhotoPopWindow() {

        if (null == photoPopWindow) {
            photoPopWindow = new PhotoPopWindow(this);
            photoPopWindow.setPhotoPopwindowCallBack(this);
        }
        photoPopWindow.showAtLocation(tv_common_title,
                Gravity.BOTTOM, 0, 0);

    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.iv_common_back:

                dofinishItself();
                break;

            case R.id.ll_diver_info_go_next:
                String driverName = et_reg_name.getText().toString().trim();
                String identifyId = et_identify_id.getText().toString().trim();
                String inviter_no = et_inviter_num.getText().toString().trim();


                presenterDriverRegUserInfo.doCompleteUser(
                        HttpConst.URL.REGISTER_UPDAT_USER,
                        currentToken,
                        driverName,
                        identifyId,
                        inviter_no);


                break;


            case R.id.iv_upload_identify_left:
                currentViewId = v.getId();
                presenterDriverRegUserInfo.doPermissionCheck();
                break;

            case R.id.iv_upload_identify_right:
                currentViewId = v.getId();
                presenterDriverRegUserInfo.doPermissionCheck();
                break;
        }

    }


    @Override
    public void takeSuccess(TResult result) {
        Log.i(ConstLog.PHOTOS, "takeSuccess" + " " + result);

        presenterDriverRegUserInfo.doOssUpLoad(result, this, currentViewId);

    }

    @Override
    public void takeFail(TResult result, String msg) {


        ToastUtil.showMsg(this.getApplicationContext(), R.string.upload_fail);
    }

    @Override
    public void takeCancel() {

    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //以下代码为处理Android6.0、7.0动态权限所需
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }


    @Override
    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
        /**
         * 申请权限全部允许之前不会回调该方法
         */
        Log.i("quanxianxxxx", "onSucceed " + requestCode + "   ");
        switch (requestCode) {
            case ConstIntent.RequestCode.APPLY_FOR_PERMISSION:

                presenterDriverRegUserInfo.doShowPhotoPopWindow();

                break;

        }
    }

    @Override
    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {


        Log.i("quanxianxxxx", "onFailed " + requestCode + "   ");
        switch (requestCode) {
            case ConstIntent.RequestCode.APPLY_FOR_PERMISSION:

                // 第一种：用AndPermission默认的提示语。
                presenterDriverRegUserInfo.doShowPermissionAlert();

                break;

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        try {
            getTakePhoto().onActivityResult(requestCode, resultCode, data);
            super.onActivityResult(requestCode, resultCode, data);

        } catch (Exception e) {

            ToastUtil.showMsg(getApplicationContext(), R.string.pic_error);

        }

//        getTakePhoto().onActivityResult(requestCode, resultCode, data);
//        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void callBackFromCamera() {
        fromCamera();
    }

    @Override
    public void callBackFromGallery() {
        fromGallary();
    }
}
