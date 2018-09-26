package com.adhdriver.work.ui.activity.driver;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.PhotoPopwindowCallBack;
import com.adhdriver.work.callback.VehicleTypePickDialogClickCallBack;
import com.adhdriver.work.constant.ConstEvent;
import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.constant.ConstLog;
import com.adhdriver.work.entity.EventEntity;
import com.adhdriver.work.entity.driver.photo.PhotoConfig;
import com.adhdriver.work.entity.driver.vehicle.VehicleCategory;
import com.adhdriver.work.entity.driver.vehicle.VehicleType;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.presenter.driver.PresenterDriverVehicleAdd;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.iview.driver.IVehicleAddView;
import com.adhdriver.work.utils.ImgUtil;
import com.adhdriver.work.utils.ToastUtil;
import com.adhdriver.work.ui.widget.FlowRaduoGroup;
import com.adhdriver.work.ui.widget.dialog.VehicleTypePickerDialog;
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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.List;

public class VehicleAddActivity extends BaseActivity<IVehicleAddView, PresenterDriverVehicleAdd> implements
        IVehicleAddView,
        View.OnClickListener,
        VehicleTypePickDialogClickCallBack,
        RadioGroup.OnCheckedChangeListener,
        TakePhoto.TakeResultListener,
        InvokeListener,
        PhotoPopwindowCallBack,
        PermissionListener {

    private PresenterDriverVehicleAdd presenterDriverVehicleAdd;


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
    private RelativeLayout rl_common_pic1;
    private LinearLayout ll_vehicle_type;//车辆类型大布局
    private FlowRaduoGroup frg_group;//车辆类型radioGroup
    private RadioGroup.LayoutParams layoutParams;

    private TextView tv_vehicle_no;
    private TextView tv_vehicle_brand_name;
    private EditText et_max_load_capacity;
    private EditText et_max_volume;


    /**
     * 底部
     */

    private LinearLayout ll_bottom;


    /**
     * 图片相关
     */
    private ImageView iv_upload_line1_left;
    private ImageView iv_upload_line1_right;
    private ImageView iv_upload_line2_left;
    private ImageView iv_upload_line2_right;
    private ImageView iv_upload_line3_right;


    /**
     * 拍照相关
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
    private VehicleTypePickerDialog vehicleTypePickerDialog;

    /**
     * 数据相关
     */
    private int currentViewId;

    /**
     * 获取radioGroup的自定义外布局
     *
     * @return
     */
    private RadioGroup.LayoutParams getRadioGroupParams() {


        if (null == layoutParams) {

            layoutParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);

        }


        layoutParams.setMargins(10, 10, 10, 10);


        return layoutParams;
    }


    private RadioButton getRadioButton(VehicleCategory vehicleCategory) {


        RadioButton radioButton = new RadioButton(this);
        radioButton.setLayoutParams(getRadioGroupParams());
        radioButton.setText(vehicleCategory.getCategory_display_name());
        radioButton.setTag(vehicleCategory);
        radioButton.setTextSize(12);
        radioButton.setGravity(Gravity.CENTER);
        radioButton.setButtonDrawable(android.R.color.transparent);//隐藏单选圆形按钮
        radioButton.setPadding(20, 20, 20, 20);
        radioButton.setTextColor(this.getResources().getColorStateList(R.drawable.selector_vehicle_type_text_bg));//设置选中/未选中的文字颜色
        radioButton.setBackgroundResource(R.drawable.selector_vehicle_type_bg);//设置按钮选中/未选中的背景


        return radioButton;
    }


    /**
     * 从相册从相机中获取
     */
    private void fromCamera() {

        takePhoto = getTakePhoto();
        photoConfig = getPhotoConfig();
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        Uri imageUri = Uri.fromFile(file);
        configCompress(takePhoto, photoConfig);  //设置压缩图片类型
        configTakePhotoOption(takePhoto, photoConfig); //使用takePhoto自带相册

//        takePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions(photoConfig));
        takePhoto.onPickFromCapture(imageUri);//不裁剪

    }

    /**
     * 从相册中获取
     */
    private void fromGallary() {

        takePhoto = getTakePhoto();
        photoConfig = getPhotoConfig();
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        Uri imageUri = Uri.fromFile(file);

        configCompress(takePhoto, photoConfig);  //设置压缩图片类型
        configTakePhotoOption(takePhoto, photoConfig); //使用takePhoto自带相册

//        takePhoto.onPickFromGalleryWithCrop(imageUri, getCropOptions(photoConfig));
        takePhoto.onPickFromGallery();  //不裁剪
    }

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


//            photoConfig = new PhotoConfig();
//
//            photoConfig.setCrop(false);
//            photoConfig.setOwnCropTool(false);
//            photoConfig.setCropWidth(800);
//            photoConfig.setCropHight(800);
//
//
//            /**
//             * 压缩工具配置
//             */
//            photoConfig.setCompress(false);
//            photoConfig.setCompressWidth(2500);
//            photoConfig.setCompressHeight(2500);
//            photoConfig.setMaxSize(102400);
//            photoConfig.setShowCompressProcess(false);
//            /**
//             * 选择相片配置
//             */
//            photoConfig.setTakePhotoGallery(true);
//            photoConfig.setMaxSelectPicCount(1);
//            photoConfig.setFixRotationAngle(true);
//            photoConfig.setSaveRawPic(true);


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
        setContentView(R.layout.activity_vehicle_add2);
        EventBus.getDefault().register(this);
        presenterDriverVehicleAdd.doGetVehicleTypes(HttpConst.URL.VEHICLE_TYPES);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        try {
            getTakePhoto().onActivityResult(requestCode, resultCode, data);
            super.onActivityResult(requestCode, resultCode, data);

        } catch (Exception e) {

            ToastUtil.showMsg(getApplicationContext(), R.string.pic_error);

        }

    }


    @Override
    protected PresenterDriverVehicleAdd creatPresenter() {
        presenterDriverVehicleAdd = new PresenterDriverVehicleAdd(this);

        return presenterDriverVehicleAdd;
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
        tv_common_title.setText(this.getString(R.string.driver_add_vehicle));
        iv_common_search.setVisibility(View.GONE);


        /**
         * 中间layout
         */
        rl_common_pic1 = findADHViewById(R.id.rl_common_pic1);
        ll_vehicle_type = findADHViewById(R.id.ll_vehicle_type);//车辆类型大布局
        frg_group = findADHViewById(R.id.frg_group);//车辆类型radioGroup

        tv_vehicle_no = findADHViewById(R.id.tv_vehicle_no);
        tv_vehicle_brand_name = findADHViewById(R.id.tv_vehicle_brand_name);
        et_max_load_capacity = findADHViewById(R.id.et_max_load_capacity);
        et_max_volume = findADHViewById(R.id.et_max_volume);


        /**
         * 底部
         */

        ll_bottom = findADHViewById(R.id.ll_bottom);





        /**
         * 图片相关
         */
        iv_upload_line1_left = findADHViewById(R.id.iv_upload_line1_left);
        iv_upload_line1_right = findADHViewById(R.id.iv_upload_line1_right);

        iv_upload_line2_left = findADHViewById(R.id.iv_upload_line2_left);
        iv_upload_line2_right = findADHViewById(R.id.iv_upload_line2_right);


        iv_upload_line3_right = findADHViewById(R.id.iv_upload_line3_right);


    }

    @Override
    protected void initListener() {
        iv_common_back.setOnClickListener(this);

        frg_group.setOnCheckedChangeListener(this);

        tv_vehicle_no.setOnClickListener(this); //车牌
        tv_vehicle_brand_name.setOnClickListener(this);//车辆品牌
        /**
         * 图片相关
         */
        iv_upload_line1_left.setOnClickListener(this);
        iv_upload_line1_right.setOnClickListener(this);

        iv_upload_line2_left.setOnClickListener(this);
        iv_upload_line2_right.setOnClickListener(this);

        iv_upload_line3_right.setOnClickListener(this);

        ll_bottom.setOnClickListener(this);


    }

    @Override
    protected void processIntent() {

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventInActivty(EventEntity eventEntity) {


        Log.i("ASdasdasd", "onEventInActivty" + "=========================");

        if (null != eventEntity) {
            switch (eventEntity.getNotifyTag()) {
                case ConstEvent.ADD_SET_VECHICLE_NUM:
                    /**
                     * 设置当前的车牌号
                     */

                    tv_vehicle_no.setText(eventEntity.getNotifyMsg());

                    break;

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
    public void onDataBackSuccessForAddVehicle() {

        this.setResult(ConstIntent.ResponseCode.GO_TO_ADD_NEW_VEHICLE);
        this.finish();

    }

    @Override
    public void onDataBackSuccessForVehicleTypes(List<VehicleType> vehicleTypes) {
        if (null == vehicleTypePickerDialog) {
            vehicleTypePickerDialog = new VehicleTypePickerDialog(this, vehicleTypes);
            vehicleTypePickerDialog.setVehicleTypePickDialogClickCallBack(this);
            vehicleTypePickerDialog.setCancelable(true);
        } else {
            vehicleTypePickerDialog.setList(vehicleTypes);

        }
    }

    @Override
    public void doShowVehicleCatogoryLayout() {
        ll_vehicle_type.setVisibility(View.VISIBLE);

    }

    @Override
    public void doSetVehicleCatogoriesToRG(List<VehicleCategory> vehicleCategories) {
        if (frg_group.getChildCount() == 0) {

            for (VehicleCategory vehicleCategory : vehicleCategories) {

                frg_group.addView(getRadioButton(vehicleCategory));


            }

        }
    }

    @Override
    public void doClearCatogroyCheck() {
        frg_group.clearCheck();
        frg_group.removeAllViews();
        ll_vehicle_type.setVisibility(View.GONE);
    }

    @Override
    public void doVertifyErrorNullForVehicleBrand() {
        ToastUtil.showMsg(getApplicationContext(), R.string.reg_car_brand);

    }

    @Override
    public void doVertifyErrorNullForVehicleType() {
        ToastUtil.showMsg(getApplicationContext(), R.string.reg_car_type);
    }

    @Override
    public void doVertifyErrorNullForVehicleCapacity() {

        ToastUtil.showMsg(getApplicationContext(), R.string.enter_max_capacity);

    }

    @Override
    public void doVertifyErrorNot0ForVehicleCapacity() {
        ToastUtil.showMsg(getApplicationContext(), R.string.not_0_capacity);
    }

    @Override
    public void doVertifyErrorNullForVehicleVolume() {

        ToastUtil.showMsg(getApplicationContext(), R.string.enter_max_volume);

    }

    @Override
    public void doVertifyErrorNot0ForVehicleVolume() {

        ToastUtil.showMsg(getApplicationContext(), R.string.not_0_volume);
    }

    @Override
    public void doVertifyErrorNullForVehicleNum() {
        ToastUtil.showMsg(getApplicationContext(), R.string.reg_car_num);
    }

    @Override
    public void doVertifyErrorNullForVehicleLicence() {
        ToastUtil.showMsg(getApplicationContext(), R.string.reg_upload_vechlelicence);

    }

    @Override
    public void doVertifyErrorNullForVehiclePhotoFrontPath() {
        ToastUtil.showMsg(getApplicationContext(), R.string.reg_upload_vechle_front);
    }

    @Override
    public void doVertifyErrorNullForVehiclePhotoBackPath() {
        ToastUtil.showMsg(getApplicationContext(), R.string.reg_upload_vechle_back);
    }

    @Override
    public void doVertifyErrorNullForVehicleInsurancePolicy() {
        ToastUtil.showMsg(getApplicationContext(), R.string.reg_upload_car_insurance_policy);
    }


    @Override
    public void doPermissionCheck() {
        AndPermission
                .with(this)
                .requestCode(ConstIntent.RequestCode.APPLY_FOR_PERMISSION)
                .permission(Manifest.permission.CAMERA)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, final Rationale rationale) {
                        AndPermission.rationaleDialog(VehicleAddActivity.this, rationale).show();
                    }
                })
                .callback(this)
                .start();
    }

    @Override
    public void doShowPermissionAlert() {
        AndPermission.defaultSettingDialog(VehicleAddActivity.this, ConstIntent.RequestCode.SYSYEM_SETTING)
                .setTitle(VehicleAddActivity.this.getString(R.string.permission_title))
                .setMessage(VehicleAddActivity.this.getString(R.string.permission_msg))
                .setPositiveButton(VehicleAddActivity.this.getString(R.string.permission_sure))
                .setNegativeButton(VehicleAddActivity.this.getString(R.string.permission_cancel), new DialogInterface.OnClickListener() {
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
    public void ossOnProgress(PutObjectRequest request, long currentSize, long totalSize) {
        Log.i("oss", "上传中：" + "currentSize: " + currentSize + " totalSize: " + totalSize + "当前线程：" + Thread.currentThread().getId());
    }

    @Override
    public void ossOnFailure() {
        ToastUtil.showMsg(getApplicationContext(), R.string.upload_fail);
    }


    @Override
    public void ossOnSuccessVehicleLicensToUi(String url, int drawableId) {
        ImgUtil.getInstance().getImgFromNetByUrl(url, iv_upload_line3_right, R.drawable.img_upload_bg);
    }

    @Override
    public void ossOnSuccessVehiclePhotoFrontToUi(String url, int drawableId) {
        ImgUtil.getInstance().getImgFromNetByUrl(url, iv_upload_line1_left, R.drawable.img_upload_bg);

    }

    @Override
    public void ossOnSuccessVehiclePhotoBackToUi(String url, int drawableId) {
        ImgUtil.getInstance().getImgFromNetByUrl(url, iv_upload_line1_right, R.drawable.img_upload_bg);
    }

    @Override
    public void ossOnSuccessVehicleInsurancePolicyToUi(String url, int drawableId) {
        ImgUtil.getInstance().getImgFromNetByUrl(url, iv_upload_line2_left, R.drawable.img_upload_bg);
    }

    @Override
    public void ossOnSuccessBusinessInsuranceToUi(String url, int drawableId) {
        ImgUtil.getInstance().getImgFromNetByUrl(url, iv_upload_line2_right, R.drawable.img_upload_bg);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_common_back:
                dofinishItself();
                break;


            case R.id.ll_bottom:


                String brand = tv_vehicle_brand_name.getText().toString().trim();//车辆品牌
                String capacity = et_max_load_capacity.getText().toString().trim(); //最大载重量
                String volume = et_max_volume.getText().toString().trim();//最大体积
                String carNum = tv_vehicle_no.getText().toString().trim();   //车牌号码

                presenterDriverVehicleAdd.doAddVehicle(
                        HttpConst.URL.DRIVER_VEHICLES,
                        brand,
                        capacity,
                        volume,
                        carNum);
                break;

            case R.id.tv_vehicle_no:

                Bundle bundle = new Bundle();
                bundle.putInt(ConstIntent.BundleKEY.SET_VEHICLE_NO, ConstIntent.BundleValue.ADD_TO_SET_VEHICLE_NO);
                openActivity(VehicleNoSettingActivity.class, bundle);
                break;


            case R.id.tv_vehicle_brand_name:


                if(null != vehicleTypePickerDialog) {
                    vehicleTypePickerDialog.show();
                }
                break;

            /**
             * 图片相关
             */


            case R.id.iv_upload_line1_left://车头照片
                currentViewId = v.getId();
                presenterDriverVehicleAdd.doPermissionCheck();

                break;


            case R.id.iv_upload_line1_right://车尾照片
                currentViewId = v.getId();
                presenterDriverVehicleAdd.doPermissionCheck();

                break;

            case R.id.iv_upload_line2_left://(交强保险单 可选)
                currentViewId = v.getId();
                presenterDriverVehicleAdd.doPermissionCheck();

                break;

            case R.id.iv_upload_line2_right://(商险单 可选)
                currentViewId = v.getId();
                presenterDriverVehicleAdd.doPermissionCheck();

                break;


            case R.id.iv_upload_line3_right: //行驶证
                currentViewId = v.getId();
                presenterDriverVehicleAdd.doPermissionCheck();

                break;


        }
    }


    @Override
    public void callBackFromCamera() {
        fromCamera();
    }

    @Override
    public void callBackFromGallery() {
        fromGallary();
    }

    @Override
    public void takeSuccess(TResult result) {
        Log.i(ConstLog.PHOTOS, "takeSuccess" + " " + result);


        presenterDriverVehicleAdd.doOssUpLoad(result, this, currentViewId); //完后还原

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

                presenterDriverVehicleAdd.doShowPhotoPopWindow();//完后还原

                break;

        }
    }

    @Override
    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {


        Log.i("quanxianxxxx", "onFailed " + requestCode + "   ");
        switch (requestCode) {
            case ConstIntent.RequestCode.APPLY_FOR_PERMISSION:

                // 第一种：用AndPermission默认的提示语。
                presenterDriverVehicleAdd.doShowPermissionAlert();//完后还原

                break;

        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        RadioButton radioButton = findADHViewById(group.getCheckedRadioButtonId());

        if (null != radioButton) {

            VehicleCategory vehicleCategory = (VehicleCategory) radioButton.getTag();



            presenterDriverVehicleAdd.doCheckSetCategory(vehicleCategory);//完后还原
            Log.i("vehicle_category", "================当前category车型id：" + vehicleCategory.getVehicle_category_id() + "===当前TypeId==" + vehicleCategory.getVehicle_type_id() + "===当前name==" + vehicleCategory.getCategory_name());

        }
    }

    @Override
    public void pickVehicleTypeClick(VehicleType vehicleType) {
        String vehicle_type_id = vehicleType.getVehicle_type_id();
        presenterDriverVehicleAdd.getTempRegDriverVehicle().setVehicle_id(vehicle_type_id);
        tv_vehicle_brand_name.setText(vehicleType.getVehicle_brand());
        presenterDriverVehicleAdd.doGetVehicleCatogories(
                HttpConst.URL.DRIVER_GET_VEHICLE_CATEGORIES + HttpConst.SEPARATOR + vehicle_type_id);

        //完后还原
    }
}
