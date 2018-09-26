package com.adhdriver.work.ui.activity.driver;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.AppUpdateDialogCallBack;
import com.adhdriver.work.callback.OnceLocationCallBack;
import com.adhdriver.work.callback.OrderModePopwindowCallBack;
import com.adhdriver.work.callback.PhotoPopwindowCallBack;
import com.adhdriver.work.constant.ConstAliYun;
import com.adhdriver.work.constant.ConstConfig;
import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.constant.ConstLog;
import com.adhdriver.work.constant.ConstParams;
import com.adhdriver.work.constant.ConstSign;
import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.entity.AppUpdate;
import com.adhdriver.work.entity.User;
import com.adhdriver.work.entity.driver.Driver;
import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.entity.driver.orders.OrderTagEntity;
import com.adhdriver.work.entity.driver.photo.PhotoConfig;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.location.OnceLocation;
import com.adhdriver.work.presenter.driver.PresenterDriverMain;
import com.adhdriver.work.push.PushEntity;
import com.adhdriver.work.service.ServiceWatch1;
import com.adhdriver.work.service.ServiceWatch2;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.activity.common.LoginActivity;
import com.adhdriver.work.ui.activity.push.AlertDialogActivity;
import com.adhdriver.work.ui.fragment.driver.main3.FragmentDriverAcrossCity;
import com.adhdriver.work.ui.fragment.driver.main3.FragmentDriverAll;
import com.adhdriver.work.ui.fragment.driver.main3.FragmentDriverSameCity;
import com.adhdriver.work.ui.iview.driver.IMainDriverView;
import com.adhdriver.work.utils.ImgUtil;
import com.adhdriver.work.utils.SpUtil;
import com.adhdriver.work.utils.StringUtil;
import com.adhdriver.work.utils.ToastUtil;
import com.adhdriver.work.utils.VersionUtil;
import com.adhdriver.work.ui.widget.dialog.AppUpdateDialog;
import com.adhdriver.work.ui.widget.popwindow.OrderModePopWindow;
import com.adhdriver.work.ui.widget.popwindow.PhotoPopWindow;
import com.adhdriver.work.ui.widget.rattingstar.StarRattingBar;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.amap.api.location.AMapLocation;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
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

import org.feezu.liuli.timeselector.Utils.TextUtil;

import java.io.File;
import java.util.List;


public class MainDriverActivity extends BaseActivity<IMainDriverView, PresenterDriverMain> implements
        IMainDriverView,
        View.OnClickListener,
        OnceLocationCallBack,
        AppUpdateDialogCallBack,
        OrderModePopwindowCallBack,
        TakePhoto.TakeResultListener,
        InvokeListener,
        PhotoPopwindowCallBack,
        PermissionListener {


    private PresenterDriverMain presenterDriverMain;

    private SlidingMenu sm_main;


    /**
     * abouveView======================
     */

    /**
     * 用于计算高度 popWindow的弹出高度
     */
    private RelativeLayout rl_top_bar;
    private FrameLayout fragment_container;

    /**
     * fragment相关
     */
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private String currentFgTag; //记录当前栈顶的fragment的Tag

    /**
     * 上
     */
    private ImageView iv_mine;
    private ImageView iv_msg;

    /**
     * 下
     */
    private TextView tv_order_mode;
    private TextView tv_order_listen;//听单中
    private TextView tv_draw_start;

    private OrderModePopWindow orderModePopWindow;


    /**
     * BehindView======================
     */


    private TextView tv_visitor;//游客模式
    private ImageView iv_userHead;
    private LinearLayout ll_phone_start;//登陆后的布局
    private TextView tv_user_name;//用户电话
    private StarRattingBar srb_driver;//评分的星星
    private LinearLayout ll_journey;  //行程
    private LinearLayout ll_wallet;    //钱包
    private LinearLayout ll_coupon;   //优惠券
    private LinearLayout ll_grade;    //信用分
    private LinearLayout ll_mgr_car;    //车辆管理
    private LinearLayout ll_refueling;  // 一键加油
    private LinearLayout ll_invite_friend; //邀请好友
    private LinearLayout ll_customer;    //客服中心
    private LinearLayout ll_setting;    //设置


    /**
     * dialog相关
     */

    private AppUpdateDialog appUpdateDialog;

    /**
     * 游客模式相关
     */
    private OnceLocation onceLocation;//单次定位用于访客模式

    /**
     * 数据相关
     */
    private User currentUser;
    private PushEntity currentPushEntity; //当前的推送数据


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


    private LinearLayout ll_bidding_list;


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


    private boolean isVisitor() {

        return SpUtil.getInstance().getBoolenValue(ConstSp.SP_KEY_VISITOR, ConstSp.SP_VALUE.DEFAULT_TRUE_VISITOR);

    }


    /**
     * 默认收车
     *
     * @return
     */
    public boolean isDipatch() {

        return SpUtil.getInstance().getBoolenValue(ConstSp.SP_KEY_VEHICLE_DIPATCH, ConstSp.SP_VALUE.DEFAULT_BOOLEAN);
    }

    private void doDispatchOrClose() {


        if (isVisitor()) {


            openActivity(LoginActivity.class, null);
            return;
        }

        /**
         * 设置状态
         */
        if (isDipatch()) {

            SpUtil.getInstance().saveBooleanTosp(ConstSp.SP_KEY_VEHICLE_DIPATCH, ConstSp.SP_VALUE.DEFAULT_BOOLEAN);

        } else {

            SpUtil.getInstance().saveBooleanTosp(ConstSp.SP_KEY_VEHICLE_DIPATCH, ConstSp.SP_VALUE.IS_DISPATCH);
        }


        /**
         * 设置按钮文字变换
         */


        if (isDipatch()) {


            tv_draw_start.setText(this.getString(R.string.vehicle_close));


        } else {

            tv_draw_start.setText(this.getString(R.string.vehicle_dipatch));
        }

        doShowOrHideCirlce(FragmentDriverSameCity.TAG);


        /**
         * 开启推送
         */
        if (currentFgTag.equals(FragmentDriverAcrossCity.TAG)) {

            if (isDipatch()) {

                presenterDriverMain.doOpenPush(
                        HttpConst.URL.UPDARE_DEVICE_TO_PUSH,
                        ConstConfig.OS_TYPE,
                        VersionUtil.getPhoneSystemVersion(),
                        VersionUtil.getPhoneBrand() + ConstSign.UNDER_LINE + VersionUtil.getPhoneType(),
                        VersionUtil.getTheIMEI(),
                        ConstAliYun.ALIYUN,
                        SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_ALIYUN_PUSH_DEVICE_TOKEN, ConstSp.SP_VALUE.DEFAULT_STRING),
                        ConstLocalData.IS_QUIET);

            } else {

                presenterDriverMain.doOpenPush(
                        HttpConst.URL.UPDARE_DEVICE_TO_PUSH,
                        ConstConfig.OS_TYPE,
                        VersionUtil.getPhoneSystemVersion(),
                        VersionUtil.getPhoneBrand() + ConstSign.UNDER_LINE + VersionUtil.getPhoneType(),
                        VersionUtil.getTheIMEI(),
                        ConstAliYun.ALIYUN,
                        SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_ALIYUN_PUSH_DEVICE_TOKEN, ConstSp.SP_VALUE.DEFAULT_STRING),
                        ConstLocalData.IS_QUIET);

            }

        } else {


            if (isDipatch()) {
                presenterDriverMain.doOpenPush(
                        HttpConst.URL.UPDARE_DEVICE_TO_PUSH,
                        ConstConfig.OS_TYPE,
                        VersionUtil.getPhoneSystemVersion(),
                        VersionUtil.getPhoneBrand() + ConstSign.UNDER_LINE + VersionUtil.getPhoneType(),
                        VersionUtil.getTheIMEI(),
                        ConstAliYun.ALIYUN,
                        SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_ALIYUN_PUSH_DEVICE_TOKEN, ConstSp.SP_VALUE.DEFAULT_STRING),
                        ConstLocalData.NOT_QUIET);
            } else {

                presenterDriverMain.doOpenPush(
                        HttpConst.URL.UPDARE_DEVICE_TO_PUSH,
                        ConstConfig.OS_TYPE,
                        VersionUtil.getPhoneSystemVersion(),
                        VersionUtil.getPhoneBrand() + ConstSign.UNDER_LINE + VersionUtil.getPhoneType(),
                        VersionUtil.getTheIMEI(),
                        ConstAliYun.ALIYUN,
                        SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_ALIYUN_PUSH_DEVICE_TOKEN, ConstSp.SP_VALUE.DEFAULT_STRING),
                        ConstLocalData.IS_QUIET);

            }


        }


    }


    public void doShowOrHideCirlce(String fgTag) {

        Fragment fragment = fragmentManager.findFragmentByTag(fgTag); //通过id找到fragment

        if (null != fragment) {
            if (fragment instanceof FragmentDriverSameCity) {

                FragmentDriverSameCity fragmentDriverSameCity = (FragmentDriverSameCity) fragment;
                fragmentDriverSameCity.doShowOrHideCircle();
            }
        }
    }


    /**
     * 根据不同FragmnetTag来得到不同fragment实例
     *
     * @param fgTag
     */
    private void getDiffirentFragment(String fgTag) {


        fragmentManager = getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentByTag(fgTag); //通过id找到fragment
        transaction = fragmentManager.beginTransaction();

        if (null == fragment) {

            if (fgTag.equals(FragmentDriverSameCity.TAG)) {
                //主页fragment
                fragment = new FragmentDriverSameCity();

            } else if (fgTag.equals(FragmentDriverAcrossCity.TAG)) {

                //订单fragment
                fragment = new FragmentDriverAcrossCity();
            } else if (fgTag.equals(FragmentDriverAll.TAG)) {

                fragment = new FragmentDriverAll();

            }


            Log.i("fragmentTest", "==============实例化的fragment：" + fgTag);

            Fragment currentTopFragment = fragmentManager.findFragmentByTag(currentFgTag); //得到当前栈顶部的fragmnet

            if (null != currentTopFragment) {


                Log.i("fragmentTest", "当前栈顶的fragment：---->" + currentFgTag + " 被隐藏掉" + "  新的栈顶fragment：" + fgTag);

                transaction.hide(currentTopFragment).add(R.id.fragment_container, fragment, fgTag).commit();  //如果存在则让其隐藏【解决创建后隐藏问题】
                currentFgTag = fgTag;


            } else {


                Log.i("fragmentTest", "当前栈顶没有fragment！！！！！！！！，将" + fgTag + " 设置到栈顶");

                transaction.add(R.id.fragment_container, fragment, fgTag).commit();

                currentFgTag = fgTag;


            }

        } else {


            Fragment currentFragment = fragmentManager.findFragmentByTag(currentFgTag);


            if (currentFgTag.equals(fgTag)) {

                Log.i("fragmentTest", fgTag + " ***********选中它，并且已经被实例化了,但是与currentFgTag相等，执行return");


                return;
            }


            Log.i("fragmentTest", fgTag + " ***********选中它，并且已经被实例化了" + " 当前栈顶的fragment：" + currentFgTag + "被隐藏掉");


            switchFragment(currentFragment, fragment);


            currentFgTag = fgTag;


        }


    }

    /**
     * fragment隐藏切换
     *
     * @param from
     * @param to
     */
    public void switchFragment(Fragment from, Fragment to) {
        if (!to.isAdded()) {    // 先判断是否被add过
            transaction.hide(from).add(R.id.fragment_container, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
        } else {
            transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
        }
    }


    /**
     * 推送处理相关==========================================================================
     *
     * @param
     */
    private void dealWithPush() {


        boolean hasPush = SpUtil.getInstance().getBoolenValue(ConstSp.SP_KEY_HAS_PUSH, ConstSp.SP_VALUE.SIGN_PUSH_FALSE);

        if (hasPush) {


            if (null != currentPushEntity) {

                presenterDriverMain.doDealWithPush(currentPushEntity);

                clearPushSign();
            }

        }


    }

    /**
     * 清空标记推送
     */

    private void clearPushSign() {

        /**
         * 标记推送
         */
        SpUtil.getInstance().saveBooleanTosp(ConstSp.SP_KEY_HAS_PUSH, ConstSp.SP_VALUE.SIGN_PUSH_FALSE);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_driver);

        dealWithPush();


//        if (isVisitor()) {
//
//            presenterDriverMain.doInitVisitorData(HttpConst.URL.VISITORS_LOGON);
//
//            getDiffirentFragment(FragmentDriverAll.TAG);
//
//        } else {
//
//
//            presenterDriverMain.doGetUserInfo(HttpConst.URL.GET_USER_INFO);
//
//            getDiffirentFragment(FragmentDriverAll.TAG);
//
//        }


        if (isVisitor()) {

            presenterDriverMain.doInitVisitorData(HttpConst.URL.VISITORS_LOGON);

        } else {


            presenterDriverMain.doGetUserInfo(HttpConst.URL.GET_USER_INFO);

            getDiffirentFragment(FragmentDriverAll.TAG);

        }


    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (null != intent) {
            Bundle bundle = intent.getExtras();
            if (null != bundle) {
                int tag = bundle.getInt(ConstIntent.BundleKEY.JUMP_TAG);
                switch (tag) {
                    case ConstIntent.BundleValue.LOGIN_SUCCESS:


                        if (null != onceLocation) {
                            onceLocation.destroylocation();
                        }
                        presenterDriverMain.doGetUserInfo(HttpConst.URL.GET_USER_INFO);
                        getDiffirentFragment(FragmentDriverAll.TAG);

                        break;

                    case ConstIntent.BundleValue.PUSH:


                        currentPushEntity = (PushEntity) bundle.getSerializable(ConstIntent.BundleKEY.PUSH_CLICK_OPEN);

                        dealWithPush();
                        break;
                }

            }
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
    }

    @Override
    protected PresenterDriverMain creatPresenter() {

        if (null == presenterDriverMain) {
            presenterDriverMain = new PresenterDriverMain(this);
        }
        return presenterDriverMain;
    }

    @Override
    protected void initViews() {


        sm_main = findADHViewById(R.id.sm_main);


        /**
         * abouveView======================
         */


        rl_top_bar = findADHViewById(R.id.rl_top_bar);
        fragment_container = findADHViewById(R.id.fragment_container);

        iv_mine = findADHViewById(R.id.iv_mine);
        iv_msg = findADHViewById(R.id.iv_msg);

        tv_order_mode = findADHViewById(R.id.tv_order_mode);
        tv_draw_start = findADHViewById(R.id.tv_draw_start);
        tv_order_listen = findADHViewById(R.id.tv_order_listen);


        /**
         * BehindView======================
         */
        tv_visitor = findADHViewById(R.id.tv_visitor);//游客模式

        ll_bidding_list = findADHViewById(R.id.ll_bidding_list);//点击进入竞价列表
        ll_phone_start = findADHViewById(R.id.ll_phone_start);//登陆后的布局
        iv_userHead = findADHViewById(R.id.iv_userHead);
        tv_user_name = findADHViewById(R.id.tv_user_name);//用户名
        srb_driver = findADHViewById(R.id.srb_driver);//评分星星
        srb_driver.setMark((float) 5);

        ll_journey = findADHViewById(R.id.ll_journey);  //行程
        ll_wallet = findADHViewById(R.id.ll_wallet);    //钱包
        ll_coupon = findADHViewById(R.id.ll_coupon);   //优惠券
        ll_grade = findADHViewById(R.id.ll_grade);    //信用分
        ll_mgr_car = findADHViewById(R.id.ll_mgr_car);    //车辆管理
        ll_refueling = findADHViewById(R.id.ll_refueling);  // 一键加油
        ll_invite_friend = findADHViewById(R.id.ll_invite_friend); //邀请好友
        ll_customer = findADHViewById(R.id.ll_customer);    //客服中心
        ll_setting = findADHViewById(R.id.ll_setting);    //设置


        if (isVisitor()) {
            tv_visitor.setVisibility(View.VISIBLE);
            ll_phone_start.setVisibility(View.GONE);
            tv_draw_start.setText(this.getString(R.string.vehicle_dipatch));

        } else {


            tv_visitor.setVisibility(View.GONE);
            ll_phone_start.setVisibility(View.VISIBLE);

            if (isDipatch()) {

                tv_draw_start.setText(this.getString(R.string.vehicle_close));

            } else {

                tv_draw_start.setText(this.getString(R.string.vehicle_dipatch));
            }
        }


    }

    @Override
    protected void initListener() {

        iv_mine.setOnClickListener(this);
        iv_msg.setOnClickListener(this);

        tv_order_mode.setOnClickListener(this);
        tv_draw_start.setOnClickListener(this);


        /**
         * BehindView======================
         */

        iv_userHead.setOnClickListener(this);
        ll_journey.setOnClickListener(this);    //行程
        ll_wallet.setOnClickListener(this);   //钱包
        ll_coupon.setOnClickListener(this);   //优惠券
        ll_grade.setOnClickListener(this);    //信用分
        ll_mgr_car.setOnClickListener(this);  //车辆管理
        ll_refueling.setOnClickListener(this); // 一键加油
        ll_invite_friend.setOnClickListener(this); //邀请好友
        ll_customer.setOnClickListener(this);   //客服中心
        ll_setting.setOnClickListener(this);   //设置
    }

    @Override
    protected void processIntent() {
        Intent intent = this.getIntent();

        if (null != intent) {


            Bundle bundle = intent.getExtras();

            if (null != bundle) {


                currentPushEntity = (PushEntity) bundle.getSerializable(ConstIntent.BundleKEY.PUSH_CLICK_OPEN);

            }
        }
    }


    @Override
    public void showLoadingDialog() {

        /**
         * 初始化dialog
         */

        showLoadDialog();
    }

    @Override
    public void dismissLoadingDialog() {


        /**
         * 初始化dialog
         * 消失dialog
         */
        dismissLoadDialog();
    }

    @Override
    public void onDataBackFail(int code, String errorMsg) {


//        doTest();
        ToastUtil.showMsg(getApplicationContext(), errorMsg);

    }

    @Override
    public void onDataBackFailForPush() {
        ToastUtil.showMsg(getApplicationContext(), R.string.order_has_been_assign);
    }

    @Override
    public void onDataBackSuccessForGetUserInfo(User user) {


        currentUser = user;

        tv_visitor.setVisibility(View.GONE);
        ll_phone_start.setVisibility(View.VISIBLE);
        tv_user_name.setText(StringUtil.encodePartOfPhone(user.getPhone()));


       Driver driver = currentUser.getDriver();

        if(null != driver) {


            driver.getCredit_points();

            srb_driver.setMark(driver.getCredit_points());
        }
        /**
         * 司机头像
         */
        ImgUtil.getInstance().getRadiusImgFromNetByUrl(user.getAvatar(), iv_userHead, R.drawable.img_default_client_head_round, 120);


        /**
         * 这里启动Service
         */

        Intent intent = new Intent(this, ServiceWatch1.class);
        Intent intent2 = new Intent(this, ServiceWatch2.class);
        startService(intent);
        startService(intent2);


        presenterDriverMain.doGetAppInfo(
                HttpConst.URL.GET_APP_VERSION_INFO,
                ConstConfig.OS_TYPE,
                VersionUtil.getPhoneSystemVersion(),
                VersionUtil.getPhoneBrand() + ConstSign.UNDER_LINE + VersionUtil.getPhoneType(),
                VersionUtil.getTheIMEI());


    }

    @Override
    public void onDataBackSuccessForGetAppInfo(AppUpdate appUpdate) {


        /**
         * 这里展示版本更新的dialog
         */


        if (appUpdateDialog == null) {
            appUpdateDialog = new AppUpdateDialog(this, appUpdate);
            appUpdateDialog.setAppUpdateDialogCallBack(this);
            appUpdateDialog.setCancelable(false);
        }

        appUpdateDialog.show();

    }


    @Override
    public void doGoToUrlDownLoad(String downLoadUrl) {
        Uri uri = Uri.parse(downLoadUrl);
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);

    }

    @Override
    public void doGoToLogon() {

        /**
         * 此处不做处理
         */

    }

    @Override
    public void doShowSelectFg(String fgTag) {
//        getDiffirentFragment(fgTag);


        /**
         * 此处不做处理
         */
    }

    @Override
    public void onDataBackSuccessForGetVisitorToken() {


        getDiffirentFragment(FragmentDriverAll.TAG);

        tv_visitor.setVisibility(View.VISIBLE);
        ll_phone_start.setVisibility(View.GONE);

        presenterDriverMain.doInitLocationForVisitor();

//        doTest();
    }

    @Override
    public void doinitLocationForVisitor() {
        /**
         * 获取一下经纬度
         */
        onceLocation = new OnceLocation();
        onceLocation.setOnceLocationCallBack(this);
        onceLocation.doStart();


        presenterDriverMain.doGetAppInfoForVisitor(
                HttpConst.URL.VISITORS_UPGRAD,
                ConstConfig.OS_TYPE,
                VersionUtil.getPhoneSystemVersion(),
                VersionUtil.getPhoneBrand() + ConstSign.UNDER_LINE + VersionUtil.getPhoneType(),
                VersionUtil.getTheIMEI());
    }

    @Override
    public void doPushToGetOrderInfo(PushEntity pushEntity) {

        presenterDriverMain.doPushGetOrderInfo(HttpConst.URL.ORDER_INFO + HttpConst.SEPARATOR + pushEntity.getOrderNo(), pushEntity);
    }

    @Override
    public void doPushToGatherPage() {
        removeTargetActivityFromStack(MyWalletInfosActivity.class.getSimpleName());
        openActivity(MyWalletInfosActivity.class, null);
    }

    @Override
    public void doPushGoSameCityAlertPage(PushEntity pushEntity,Order order) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();


        bundle.putSerializable(ConstIntent.BundleKEY.PUSH_DIALOG_ORDER, order);
        bundle.putSerializable(ConstIntent.BundleKEY.PUSH_DIALOG, pushEntity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClass(this, AlertDialogActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void doPushGoOverOfficeAlertPage(PushEntity pushEntity) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        PushEntity currentPushEntity = new PushEntity();
        bundle.putSerializable(ConstIntent.BundleKEY.PUSH_DIALOG, currentPushEntity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClass(this, AlertDialogActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void doPushGoToOrderTake(Order order) {


       String status =  order.getStatus();



        if(status.equals(ConstParams.OrderStatus.COMPLETED)){


            removeTargetActivityFromStack(OrderTakeDetailFinishActivity.class.getSimpleName());
            Bundle bundle = new Bundle();
            bundle.putSerializable(ConstIntent.BundleKEY.DELIVER_ORDER,order);
            openActivity(OrderTakeDetailFinishActivity.class, bundle);

        }else if(status.equals(ConstParams.OrderStatus.Canceled)){



            removeTargetActivityFromStack(OrderTakeDetailCancelActivity.class.getSimpleName());
            Bundle bundle = new Bundle();
            bundle.putSerializable(ConstIntent.BundleKEY.DELIVER_ORDER,order);
            openActivity(OrderTakeDetailCancelActivity.class, bundle);


        }else if(status.equals(ConstParams.OrderStatus.Refund)){



            removeTargetActivityFromStack(OrderTakeDetailRefundActivity.class.getSimpleName());
            Bundle bundle = new Bundle();
            bundle.putSerializable(ConstIntent.BundleKEY.DELIVER_ORDER,order);
            openActivity(OrderTakeDetailRefundActivity.class, bundle);


        }else {


            removeTargetActivityFromStack(OrderTakeActivity.class.getSimpleName());
            openActivity(OrderTakeActivity.class,null);

        }

//        openActivity(OrderTakeActivity.class,null);

    }

    @Override
    public void ossOnProgress(PutObjectRequest request, long currentSize, long totalSize) {
        Log.i("oss", "上传中：" + "currentSize: " + currentSize + " totalSize: " + totalSize + "当前线程：" + Thread.currentThread().getId());
    }

    @Override
    public void ossOnSuccessLoadIdUserHeadToUi(String url, int id) {
        ImgUtil.getInstance().getRadiusImgFromNetByUrl(url, iv_userHead, R.drawable.img_default_client_head_round, 120);

        presenterDriverMain.doUploadAvatar(HttpConst.URL.DRIVER_SET_AVATAR + ConstSign.QUESTION_MARK + ConstLocalData.PHOTO_URL + ConstSign.EQUAL_SIGN + url);
    }

    @Override
    public void ossOnFailure() {
        ToastUtil.showMsg(getApplicationContext(), R.string.upload_fail);
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
                        AndPermission.rationaleDialog(MainDriverActivity.this, rationale).show();
                    }
                })
                .callback(this)
                .start();
    }

    @Override
    public void doShowPermissionAlert() {
        AndPermission.defaultSettingDialog(MainDriverActivity.this, ConstIntent.RequestCode.SYSYEM_SETTING)
                .setTitle(MainDriverActivity.this.getString(R.string.permission_title))
                .setMessage(MainDriverActivity.this.getString(R.string.permission_msg))
                .setPositiveButton(MainDriverActivity.this.getString(R.string.permission_sure))
                .setNegativeButton(MainDriverActivity.this.getString(R.string.permission_cancel), new DialogInterface.OnClickListener() {
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
        photoPopWindow.showAtLocation(rl_top_bar,
                Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void onDataBackSuccessForUpLoadAvatar() {

//        presenterDriverMain.doGetUserInfo(HttpConst.URL.GET_USER_INFO);

        presenterDriverMain.doGetUserInfoAfterAvatar(HttpConst.URL.GET_USER_INFO);
    }


    /**
     * 访客模式的单次定位
     *
     * @param aMapLocation
     */
    @Override
    public void onceLocationInfoBack(AMapLocation aMapLocation) {

        Log.i("onceLocation", aMapLocation.getAdCode() + " " + aMapLocation.getLongitude() + " " + aMapLocation.getLatitude());

        presenterDriverMain.doUpLoadLocationForVisitor(HttpConst.URL.VISITORS_SAVE, aMapLocation);
    }

    @Override
    public void appUpdateDialogClickSure(AppUpdate appUpdate) {

        presenterDriverMain.doGoToUrlDownLoad(appUpdate);

    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.iv_mine:

                sm_main.showMenu();

                break;

            case R.id.iv_msg:

                openActivity(MessageActivity.class, null);

                break;

            case R.id.tv_order_mode:

                if (null == orderModePopWindow) {
                    orderModePopWindow = new OrderModePopWindow(this);
                    orderModePopWindow.setOrderModePopwindowCallBack(this);

                }
                orderModePopWindow.showUp(tv_order_mode);


                break;

            case R.id.tv_draw_start:


                doDispatchOrClose();


//                doTestAlert();


                break;


            case R.id.ll_journey:


                openActivity(OrderTakeActivity.class, null);

                break;

            case R.id.ll_wallet:


                if (isVisitor()) {
                    openActivity(LoginActivity.class, null);
                } else {

                    openActivity(MyWalletActivity.class, null);
                }


                break;
            case R.id.ll_coupon:

                ToastUtil.showMsg(getApplicationContext(), "优惠券");
                break;
            case R.id.ll_grade:


                if (isVisitor()) {
                    openActivity(LoginActivity.class, null);
                } else {
                    openActivity(DriverCreditScoreActivity.class, null);
                }


                break;
            case R.id.ll_mgr_car:


                if (isVisitor()) {
                    openActivity(LoginActivity.class, null);
                } else {
                    openActivity(VehiclesActivity.class, null);
                }

//                openActivity(VehiclesActivity.class, null);


                break;

            case R.id.ll_refueling:


                if (isVisitor()) {
                    openActivity(LoginActivity.class, null);
                } else {

                    if (null != currentUser) {
                        String phone = currentUser.getPhone();

                        if (!TextUtil.isEmpty(phone)) {

                            Bundle bundle = new Bundle();
                            bundle.putString(ConstIntent.BundleKEY.REFUEL_DRIVER_PHONE, phone);
                            openActivity(RefuelActivity.class,bundle);
                        } else {
                            ToastUtil.showMsg(getApplicationContext(), "暂无用户信息");
                        }

                    } else {
                        ToastUtil.showMsg(getApplicationContext(), "暂无用户信息");
                    }
                }


                break;
            case R.id.ll_invite_friend:


                if (isVisitor()) {
                    openActivity(LoginActivity.class, null);
                } else {

                    openActivity(ShareActivity.class, null);

                }


                break;


            case R.id.ll_customer:
                openActivity(CustomCenterActivity.class, null);


                break;
            case R.id.ll_setting:

                openActivity(SettingActivity.class, null);
                break;

            case R.id.iv_userHead:

                if (isVisitor()) {
                    openActivity(LoginActivity.class, null);
                } else {

                    presenterDriverMain.doPermissionCheck();
                }
                break;
        }

    }




    @Override
    public void orderModeSameCityClick() {

        getDiffirentFragment(FragmentDriverSameCity.TAG);
        tv_order_mode.setText(getString(R.string.mode_same_city));


        if (!isVisitor() && isDipatch()) {
            /**
             * 开推送
             */
            presenterDriverMain.doOpenPush(
                    HttpConst.URL.UPDARE_DEVICE_TO_PUSH,
                    ConstConfig.OS_TYPE,
                    VersionUtil.getPhoneSystemVersion(),
                    VersionUtil.getPhoneBrand() + ConstSign.UNDER_LINE + VersionUtil.getPhoneType(),
                    VersionUtil.getTheIMEI(),
                    ConstAliYun.ALIYUN,
                    SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_ALIYUN_PUSH_DEVICE_TOKEN, ConstSp.SP_VALUE.DEFAULT_STRING),
                    ConstLocalData.NOT_QUIET);

        }


    }

    @Override
    public void orderModeAcrossCityClick() {


        getDiffirentFragment(FragmentDriverAcrossCity.TAG);
        tv_order_mode.setText(getString(R.string.mode_across_city));

        /**
         * 关闭推送
         */


        if (!isVisitor() && isDipatch()) {
            presenterDriverMain.doOpenPush(
                    HttpConst.URL.UPDARE_DEVICE_TO_PUSH,
                    ConstConfig.OS_TYPE,
                    VersionUtil.getPhoneSystemVersion(),
                    VersionUtil.getPhoneBrand() + ConstSign.UNDER_LINE + VersionUtil.getPhoneType(),
                    VersionUtil.getTheIMEI(),
                    ConstAliYun.ALIYUN,
                    SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_ALIYUN_PUSH_DEVICE_TOKEN, ConstSp.SP_VALUE.DEFAULT_STRING),
                    ConstLocalData.IS_QUIET);
        }


    }

    @Override
    public void orderModeAllClick() {


        tv_order_mode.setText(getString(R.string.mode_all));
        getDiffirentFragment(FragmentDriverAll.TAG);


        /**
         * 开推送
         */
        if (!isVisitor() && isDipatch()) {
            presenterDriverMain.doOpenPush(
                    HttpConst.URL.UPDARE_DEVICE_TO_PUSH,
                    ConstConfig.OS_TYPE,
                    VersionUtil.getPhoneSystemVersion(),
                    VersionUtil.getPhoneBrand() + ConstSign.UNDER_LINE + VersionUtil.getPhoneType(),
                    VersionUtil.getTheIMEI(),
                    ConstAliYun.ALIYUN,
                    SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_ALIYUN_PUSH_DEVICE_TOKEN, ConstSp.SP_VALUE.DEFAULT_STRING),
                    ConstLocalData.NOT_QUIET);
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


//        String path = result.getImage().getCompressPath();
//
//        String path2 = result.getImage().getOriginalPath();
//
//        sssssssssssssssssssss
//
//
//        if(currentViewId ==  R.id.iv_upload_line3_left) {
//
//            path = result.getImage().getOriginalPath();
//        }

        presenterDriverMain.doOssUpLoad(result, this, R.id.iv_userHead);
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

                presenterDriverMain.doShowPhotoPopWindow();

                break;

        }
    }

    @Override
    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {

        Log.i("quanxianxxxx", "onFailed " + requestCode + "   ");
        switch (requestCode) {
            case ConstIntent.RequestCode.APPLY_FOR_PERMISSION:

                // 第一种：用AndPermission默认的提示语。
                presenterDriverMain.doShowPermissionAlert();

                break;

        }
    }
}
