package com.adhdriver.work.ui.activity.push;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.DialogAlertActivtyCallBack;
import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.presenter.driver.PresenterDriverPushAlert;
import com.adhdriver.work.push.PushEntity;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.iview.driver.IPushAlertView;
import com.adhdriver.work.ui.widget.dialog.swipedialog.NewOrderAlertDialog;

public class AlertDialogActivity extends BaseActivity<IPushAlertView, PresenterDriverPushAlert> implements
        IPushAlertView,
        DialogAlertActivtyCallBack {


    private PresenterDriverPushAlert presenterDriverPushAlert;


    /**
     * 数据相关
     * @param savedInstanceState
     */

    /**
     * 数据相关
     */
    private Order currentOrder;

    private PushEntity currentPushEntity;
    /**
     * 控件相关
     */
    private NewOrderAlertDialog newOrderAlertDialog;

    private MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);


        presenterDriverPushAlert.doPlayAlertVoice();

        presenterDriverPushAlert.doShowAlertDialog();
    }

    @Override
    protected PresenterDriverPushAlert creatPresenter() {

        if (null == presenterDriverPushAlert) {
            presenterDriverPushAlert = new PresenterDriverPushAlert(this);
        }
        return presenterDriverPushAlert;
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

                currentOrder = (Order) extras.getSerializable(ConstIntent.BundleKEY.PUSH_DIALOG_ORDER);
                currentPushEntity = (PushEntity) extras.getSerializable(ConstIntent.BundleKEY.PUSH_DIALOG_ENTITY);
            }
        }

    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (null != intent) {

            Bundle extras = intent.getExtras();

            if (null != extras) {

                currentOrder = (Order) extras.getSerializable(ConstIntent.BundleKEY.PUSH_DIALOG_ORDER);
                currentPushEntity = (PushEntity) extras.getSerializable(ConstIntent.BundleKEY.PUSH_DIALOG_ENTITY);
            }

        }

        presenterDriverPushAlert.doPlayAlertVoice();

        presenterDriverPushAlert.doShowAlertDialog();


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();


        if (null != mp) {

            mp.stop();
            mp.release();
            mp = null;

        }

        Log.i("AlertDialogActivity", "=============onDestroy");

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
    public void doPlayAlertVoice() {


        try {

            if (null != mp) {
                mp.stop();
                mp.release();
            }

            mp = MediaPlayer.create(this, R.raw.new_order_alert);//重新设置要播放的音频
            mp.start();//开始播放

            Log.i("mpssss", "=============播放正常");


        } catch (Exception e) {
            e.printStackTrace();//输出异常信息

            Log.i("mpssss", "=============播放异常：" + e.toString());
        }

    }

    @Override
    public void doShowAlertDialog() {


        if (null == currentOrder) {

            this.finish();
            return;

        }


        if (null == newOrderAlertDialog) {

            Log.i("AlertDialogActivity", "=============newOrderAlertDialog = null");

            newOrderAlertDialog = new NewOrderAlertDialog(this);
            newOrderAlertDialog.setCancelable(false);
            newOrderAlertDialog.setDialogAlertActivtyCallBack(this);
            newOrderAlertDialog.setCurrentPushEntity(currentPushEntity);
            newOrderAlertDialog.setCurrentOrder(currentOrder);
            newOrderAlertDialog.show();

        } else {

            if (newOrderAlertDialog.isShowing()) {

                Log.i("AlertDialogActivity", "=============newOrderAlertDialog != null 但是还是在showing");
                newOrderAlertDialog.setCurrentPushEntity(currentPushEntity);
                newOrderAlertDialog.setCurrentOrder(currentOrder);
                newOrderAlertDialog.doReChangeUi();

            } else {


                Log.i("AlertDialogActivity", "=============newOrderAlertDialog != null 没有showing");

                newOrderAlertDialog.setCurrentPushEntity(currentPushEntity);
                newOrderAlertDialog.setCurrentOrder(currentOrder);
                newOrderAlertDialog.doReChangeUi();
                newOrderAlertDialog.show();

            }


        }

    }


    @Override
    public void doFinishPage() {

        if (null != newOrderAlertDialog && newOrderAlertDialog.isShowing()) {

            newOrderAlertDialog.dismiss();
            newOrderAlertDialog = null;

        }

        dofinishItself();

    }
}
