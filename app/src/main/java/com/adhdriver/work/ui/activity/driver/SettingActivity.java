package com.adhdriver.work.ui.activity.driver;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.DepositPassOperatePopwindowCallBack;
import com.adhdriver.work.constant.ConstAliYun;
import com.adhdriver.work.constant.ConstConfig;
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.constant.ConstSign;
import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.presenter.driver.PresenterDriverSetting;
import com.adhdriver.work.service.ServiceWatch1;
import com.adhdriver.work.service.ServiceWatch2;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.activity.common.FeedBackActivity;
import com.adhdriver.work.ui.activity.common.LoginActivity;
import com.adhdriver.work.ui.activity.common.VersionActivity;
import com.adhdriver.work.ui.iview.driver.ISettingDriverView;
import com.adhdriver.work.utils.SpUtil;
import com.adhdriver.work.utils.ToastUtil;
import com.adhdriver.work.utils.VersionUtil;
import com.adhdriver.work.ui.widget.popwindow.DepositPassOperatePopWindow;

/**
 * 设置Activity
 */
public class SettingActivity extends BaseActivity<ISettingDriverView, PresenterDriverSetting> implements
        ISettingDriverView,
        View.OnClickListener,
        DepositPassOperatePopwindowCallBack {

    public static String TAG = SettingActivity.class.getSimpleName(); //获得类名称

    private PresenterDriverSetting presenterDriverSetting;


    /**
     * titile
     */
    private ImageView iv_common_back;
    private TextView tv_common_title;
    private ImageView iv_common_search;


    /**
     * 中间：
     */

    private RelativeLayout rl_driver_third_bind;
    private RelativeLayout rl_driver_qr;
    private RelativeLayout rl_change_pass;
    //    private RelativeLayout rl_edit_user_info;
    private RelativeLayout rl_version_info;
    private RelativeLayout rl_feedback;
    private RelativeLayout rl_change_withdraw_pass;


    /**
     * 底部退出按钮
     *
     * @param savedInstanceState
     */
    private LinearLayout ll_logout_or_login;
    private TextView tv_logout_or_login;


    /**
     * popwindow相关
     *
     * @return
     */

    private DepositPassOperatePopWindow depositPassOperatePopWindow;




    /**
     * 停止2个service
     */
    private void stopThe2Service() {


        this.stopService(getTheIntent(this, ServiceWatch1.class));

        this.stopService(getTheIntent(this, ServiceWatch2.class));
    }


    private Intent getTheIntent(Context context, Class<?> targetClass) {

        Intent intent = new Intent(context, targetClass);

        return intent;

    }


    private boolean isVisitor() {

        return SpUtil.getInstance().getBoolenValue(ConstSp.SP_KEY_VISITOR, ConstSp.SP_VALUE.DEFAULT_TRUE_VISITOR);

    }


    @Override
    protected PresenterDriverSetting creatPresenter() {
        if (null == presenterDriverSetting) {

            presenterDriverSetting = new PresenterDriverSetting(this);
        }
        return presenterDriverSetting;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);



        if(!isVisitor()) {
            presenterDriverSetting.doGetMyWalletInfo(HttpConst.URL.MY_WALLETE);
        }

    }


    @Override
    protected void initViews() {
        /**
         * title
         */
        iv_common_back = findADHViewById(R.id.iv_common_back);
        tv_common_title = findADHViewById(R.id.tv_common_title);
        iv_common_search = findADHViewById(R.id.iv_common_search);
        iv_common_search.setVisibility(View.GONE);

        tv_common_title.setText(this.getString(R.string.setting));


        /**
         * 中间：
         */
        rl_driver_third_bind = findADHViewById(R.id.rl_driver_third_bind);
        rl_driver_qr = findADHViewById(R.id.rl_driver_qr);
        rl_change_pass = findADHViewById(R.id.rl_change_pass);
        rl_version_info = findADHViewById(R.id.rl_version_info);
        rl_feedback = findADHViewById(R.id.rl_feedback);
        rl_change_withdraw_pass = findADHViewById(R.id.rl_change_withdraw_pass);


        /**
         * 底部退出按钮
         * @param savedInstanceState
         */
        ll_logout_or_login = findADHViewById(R.id.ll_logout_or_login);
        tv_logout_or_login = findADHViewById(R.id.tv_logout_or_login);
        if(isVisitor()) {


            /**
             * 中间：
             */
            rl_driver_third_bind.setVisibility(View.GONE);
            rl_driver_qr.setVisibility(View.GONE);
            rl_change_pass.setVisibility(View.GONE);
            rl_version_info.setVisibility(View.VISIBLE);
            rl_feedback.setVisibility(View.GONE);
            tv_logout_or_login.setText(getString(R.string.text_login));

        }else {

            /**
             * 中间：
             */
            rl_driver_third_bind.setVisibility(View.GONE);
            rl_driver_qr.setVisibility(View.GONE);
            rl_change_pass.setVisibility(View.VISIBLE);
            rl_version_info.setVisibility(View.VISIBLE);
            rl_feedback.setVisibility(View.VISIBLE);
            tv_logout_or_login.setText(getString(R.string.text_logout));

        }
    }

    @Override
    protected void initListener() {

        /**
         * titile
         */
        iv_common_back.setOnClickListener(this);


        /**
         * 中间：
         */

        rl_driver_third_bind.setOnClickListener(this);
        rl_driver_qr.setOnClickListener(this);

        rl_change_pass.setOnClickListener(this);
//        rl_edit_user_info.setOnClickListener(this);
        rl_version_info.setOnClickListener(this);
        rl_feedback.setOnClickListener(this);
        rl_change_withdraw_pass.setOnClickListener(this);


        /**
         * 底部退出按钮
         *
         */
        ll_logout_or_login = findADHViewById(R.id.ll_logout_or_login);
        ll_logout_or_login.setOnClickListener(this);

    }

    @Override
    protected void processIntent() {

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
    public void onDataBackSuccessForWalletInfoHasDepositPass() {

        rl_change_withdraw_pass.setVisibility(View.VISIBLE);

    }

    @Override
    public void onDataBackSuccessForWalletInfoNoDepositPass() {

        rl_change_withdraw_pass.setVisibility(View.GONE);
    }

    @Override
    public void onDataBackSuccessForClosePush() {


        presenterDriverSetting.doInitVisitorLogon(HttpConst.URL.VISITORS_LOGON);





    }

    @Override
    public void onDataBackSuccessForGetVisitorToken() {

        presenterDriverSetting.doClearAll();

        stopThe2Service();  //mvp后续完成

        removeAllFromActiviiesStack(TAG);

        openActivity(MainDriverActivity.class,null);
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.iv_common_back:
                dofinishItself();
                break;


            case R.id.rl_driver_third_bind:

                openActivity(ThirdAuthActivity.class, null);

                break;


            case R.id.rl_change_pass:


                openActivity(ChangeLoginPassActivity.class, null);
                break;

            case R.id.rl_version_info:


                openActivity(VersionActivity.class, null);
                break;

            case R.id.rl_feedback:


                openActivity(FeedBackActivity.class, null);
                break;


            case R.id.ll_logout_or_login:


                if(isVisitor()) {

                    openActivity(LoginActivity.class,null);
                }else {


                    presenterDriverSetting.doLogout(
                            HttpConst.URL.UPDARE_DEVICE_TO_PUSH,
                            ConstConfig.OS_TYPE,
                            VersionUtil.getPhoneSystemVersion(),
                            VersionUtil.getPhoneBrand() + ConstSign.UNDER_LINE + VersionUtil.getPhoneType(),
                            VersionUtil.getTheIMEI(),
                            ConstAliYun.ALIYUN,
                            SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_ALIYUN_PUSH_DEVICE_TOKEN, ConstSp.SP_VALUE.DEFAULT_STRING),
                            ConstLocalData.IS_QUIET);

                }

//                presenterDriverSetting.doClosePush(
//                        HttpConst.URL.UPDARE_DEVICE_TO_PUSH,
//                        ConstParams.Config.OS_TYPE,
//                        VersionUtil.getPhoneSystemVersion(),
//                        VersionUtil.getPhoneBrand() + ConstSign.UNDER_LINE + VersionUtil.getPhoneType(),
//                        VersionUtil.getTheIMEI(),
//                        ConstAliYun.ALIYUN,
//                        SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_ALIYUN_PUSH_DEVICE_TOKEN, ConstSp.SP_VALUE.DEFAULT_STRING),
//                        ConstLocalData.IS_QUIET
//                );


                break;

            case R.id.rl_change_withdraw_pass:


                if (null == depositPassOperatePopWindow) {
                    depositPassOperatePopWindow = new DepositPassOperatePopWindow(this);
                    depositPassOperatePopWindow.setDepositPassOperatePopwindowCallBack(this);

                }
                depositPassOperatePopWindow.showAtLocation(tv_common_title,
                        Gravity.BOTTOM, 0, 0);


                break;


            case R.id.rl_driver_qr:


                ToastUtil.showMsg(getApplicationContext(), "我的二维码");

//                openActivity(DriverQRActivity.class, null);


                break;
        }

    }


    /**
     * dialog点击事件
     */
    @Override
    public void depositPassPopwindowChangeClick() {


        openActivity(DepositPassChangeActivity.class,null);

    }

    @Override
    public void depositPassPopwindowForgetClick() {




        openActivity(DepositPassForgetToGetCodeActivity.class,null);


    }
}
