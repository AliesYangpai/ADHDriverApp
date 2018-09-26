package com.adhdriver.work.ui.activity.kick;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.DialogAlertActivtyCallBack;
import com.adhdriver.work.constant.ConstAliYun;
import com.adhdriver.work.constant.ConstConfig;
import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.constant.ConstSign;
import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.entity.UdpEntity;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.presenter.driver.PresenterDriverKickOut;
import com.adhdriver.work.service.ServiceWatch1;
import com.adhdriver.work.service.ServiceWatch2;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.activity.driver.MainDriverActivity;
import com.adhdriver.work.ui.iview.driver.IKickOutView;
import com.adhdriver.work.utils.SpUtil;
import com.adhdriver.work.utils.VersionUtil;
import com.adhdriver.work.ui.widget.dialog.KickAlertDialog;

public class KickOutActivity extends BaseActivity<IKickOutView, PresenterDriverKickOut> implements
        IKickOutView,
        DialogAlertActivtyCallBack{



    public static String TAG = KickOutActivity.class.getSimpleName(); //获得类名称
    private PresenterDriverKickOut presenterDriverKickOut;


    /**
     * 数据相关
     */
    private UdpEntity currentUdpEntity;


    /**
     * 控件相关
     */
    private KickAlertDialog kickAlertDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kick_out);


        presenterDriverKickOut.doShowKickOutAlert(currentUdpEntity);

        presenterDriverKickOut.doLogout(
                HttpConst.URL.UPDARE_DEVICE_TO_PUSH,
                ConstConfig.OS_TYPE,
                VersionUtil.getPhoneSystemVersion(),
                VersionUtil.getPhoneBrand() + ConstSign.UNDER_LINE + VersionUtil.getPhoneType(),
                VersionUtil.getTheIMEI(),
                ConstAliYun.ALIYUN,
                SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_ALIYUN_PUSH_DEVICE_TOKEN, ConstSp.SP_VALUE.DEFAULT_STRING),
                ConstLocalData.IS_QUIET);



    }

    @Override
    protected PresenterDriverKickOut creatPresenter() {
        if (null == presenterDriverKickOut) {
            presenterDriverKickOut = new PresenterDriverKickOut(this);
        }
        return presenterDriverKickOut;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void processIntent() {
        Intent intent = this.getIntent();

        if (null != intent) {

            Bundle extras = intent.getExtras();

            if (null != extras) {

                currentUdpEntity = (UdpEntity) extras.getSerializable(ConstIntent.BundleKEY.SOCKET_KICK);

            }
        }

    }


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

    @Override
    public void showLoadingDialog() {


        /**
         * 暂不处理
         */

    }

    @Override
    public void dismissLoadingDialog() {


        /**
         * 暂不处理
         */

    }

    @Override
    public void onDataBackFail(int code, String errorMsg) {

    }

    @Override
    public void doShowKickOutAlertDialog(UdpEntity udpEntity) {

        if (null == udpEntity) {

            this.finish();
            return;

        }


        if (null == kickAlertDialog) {
            Log.i("KickOutActivity", "=============newOrderAlertDialog = null");
            kickAlertDialog = new KickAlertDialog(this);
            kickAlertDialog.setCancelable(false);
            kickAlertDialog.setDialogAlertActivtyCallBack(this);
            kickAlertDialog.setUdpEntity(udpEntity);
        }
        kickAlertDialog.show();

    }

    @Override
    public void doDestroyAlertDialog() {

        if (null != kickAlertDialog && kickAlertDialog.isShowing()) {

            kickAlertDialog.dismiss();
            kickAlertDialog = null;

        }



    }

    @Override
    public void onDataBackSuccessForClosePush() {
        presenterDriverKickOut.doInitVisitorLogon(HttpConst.URL.VISITORS_LOGON);
    }

    @Override
    public void onDataBackSuccessForGetVisitorToken() {

        presenterDriverKickOut.doClearAll();

        stopThe2Service();  //mvp后续完成

//        removeAllFromActiviiesStack(TAG);

//        openActivity(MainDriverActivity.class,null);
    }


    @Override
    public void doFinishPage() {

        presenterDriverKickOut.doDestroyAlertDialog();
        removeAllFromActiviiesStack(TAG);
        openActivityAndFinishItself(MainDriverActivity.class, null);
    }
}
