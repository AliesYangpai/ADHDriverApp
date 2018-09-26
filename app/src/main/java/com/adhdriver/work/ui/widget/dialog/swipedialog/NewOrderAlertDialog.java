package com.adhdriver.work.ui.widget.dialog.swipedialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.DialogAlertActivtyCallBack;
import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.constant.ConstHz;
import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.constant.ConstParams;
import com.adhdriver.work.constant.ConstPush;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.driver.orders.City;
import com.adhdriver.work.entity.driver.orders.From;
import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.entity.driver.orders.Province;
import com.adhdriver.work.entity.driver.orders.To;
import com.adhdriver.work.http.CallServer;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.http.HttpSingleResponseListener;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.http.RequestPacking;
import com.adhdriver.work.push.PushEntity;
import com.adhdriver.work.ui.activity.driver.OrderDetailNotTakeBiddingActivity;
import com.adhdriver.work.ui.activity.driver.OrderTakeActivity;
import com.adhdriver.work.ui.activity.driver.OrderTakeDetailActivity;
import com.adhdriver.work.ui.widget.dialog.swipedialog.process.CountDownProgress;
import com.adhdriver.work.utils.ToastUtil;
import com.adhdriver.work.verification.VertifyNotNull;
import com.iflytek.cloud.thirdparty.V;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import org.feezu.liuli.timeselector.Utils.TextUtil;


/**
 * Created by Administrator on 2017/10/7.
 * 类描述
 * 版本
 */

public class NewOrderAlertDialog extends SwipeAwayDialog implements
        View.OnClickListener,
        CountDownProgress.OnCountdownFinishListener {


    private TextView tv_distance; //距离
    private TextView tv_push_time; //时间
    private ImageView iv_omit; //中间省略图标

    private TextView tv_start_place_name;  //出发地名称
    private TextView tv_start_place_detial; //出发地详情

    private TextView tv_destination_name; //目的地名称
    private TextView tv_destination_detial; //目的地详情


    private CountDownProgress cdp_time_count_take;//接单按钮

    private Activity mContext;


    /**
     * 数据相关
     */
    private PushEntity currentPushEntity;

    private Order currentOrder;

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public void setCurrentPushEntity(PushEntity currentPushEntity) {
        this.currentPushEntity = currentPushEntity;
    }

    public PushEntity getCurrentPushEntity() {
        return currentPushEntity;
    }


    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;

    private DialogAlertActivtyCallBack dialogAlertActivtyCallBack;


    public void setDialogAlertActivtyCallBack(DialogAlertActivtyCallBack dialogAlertActivtyCallBack) {
        this.dialogAlertActivtyCallBack = dialogAlertActivtyCallBack;
    }


    public NewOrderAlertDialog(@NonNull Activity context) {
        super(context);
        this.mContext = context;
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
    }

    public NewOrderAlertDialog(@NonNull Activity context, @StyleRes int themeResId) {
        super(context, themeResId);

        this.mContext = context;
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
    }


    private boolean isOfficeAssign() {

        return null != currentPushEntity.getBusinessTypeChildType() &&
                currentPushEntity.getBusinessTypeChildType().equals(ConstPush.OFFICE_ASSIGNED_ORDER);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("NewOrderAlert", "=============onCreate");
        setContentView(R.layout.dialog_new_order_same_city_alert);
        setCanceledOnTouchOutside(false);
        initView();
        initListener();
        initData();


        Log.i("AlertDialogActivity", "=============NewOrderAlertDialog 调用onCreate==");
    }


    /**
     * 设置设置新的数据后，根据 货运类型变更Ui
     */
    public void doReChangeUi() {


        doSetData();

        cdp_time_count_take.setCountdownTime(30 * 1000);
        cdp_time_count_take.startCountDownTime(this);


    }


    private void initView() {


        tv_distance = (TextView) findViewById(R.id.tv_distance); //距离
        tv_push_time = (TextView) findViewById(R.id.tv_push_time); //时间
        iv_omit = (ImageView) findViewById(R.id.iv_omit); //中间省略图标
        tv_start_place_name = (TextView) findViewById(R.id.tv_start_place_name);  //出发地名称
        tv_start_place_detial = (TextView) findViewById(R.id.tv_start_place_detial); //出发地详情
        tv_destination_name = (TextView) findViewById(R.id.tv_destination_name); //目的地名称
        tv_destination_detial = (TextView) findViewById(R.id.tv_destination_detial); //目的地详情


        cdp_time_count_take = (CountDownProgress) findViewById(R.id.cdp_time_count_take);
        cdp_time_count_take.setCountdownTime(30 * 1000);


    }


    private void initListener() {
        cdp_time_count_take.setOnClickListener(this);
    }


    private void initData() {


        cdp_time_count_take.startCountDownTime(this);


        doSetData();

    }

    private void doSetData() {
        if (isOfficeAssign()) {
            cdp_time_count_take.theText = "不接";

        } else {

            cdp_time_count_take.theText = "接";
        }


        if (null != currentOrder) {


            From from = currentOrder.getFrom();
            To to = currentOrder.getTo();


            if (null == from && null == to) {
                return;

            }


            String fromStreet = from.getStreet_address();
            String toStreet = to.getStreet_address();
            String time = currentOrder.getBook_time();


            Province provinceFrom = from.getProvince();
            City cityFrom = from.getCity();
            Province toProvice = to.getProvince();
            City toCity = to.getCity();


            if (null == provinceFrom &&
                    null == cityFrom &&
                    null == toProvice &&
                    null == toCity) {

                return;
            }

            tv_start_place_name.setText(fromStreet);
            tv_push_time.setText(time);
            tv_start_place_detial.setText(provinceFrom.getName() + cityFrom.getName());
            tv_destination_name.setText(toStreet);
            tv_destination_detial.setText(toProvice.getName() + toCity.getName());


            tv_distance.setText(currentOrder.getDistance() + ConstHz.KM);


            if (null != currentOrder.getPath_points() && currentOrder.getPath_points().size() > 0) {

                iv_omit.setVisibility(View.VISIBLE);
            } else {
                iv_omit.setVisibility(View.GONE);
            }

        }

    }


    @Override
    public void dismiss() {


        super.dismiss();


        if (null != dialogAlertActivtyCallBack) {

            dialogAlertActivtyCallBack.doFinishPage();

        }
        Log.i("NewOrderAlert", "=============dismiss");

    }


    /**
     * If you want to handle swiped away event, implement onSwipedAway.You can prevent dismissing by returning true.
     *
     * @param toRight
     * @return
     */
    @Override
    public boolean onSwipedAway(boolean toRight) {


        Log.i("NewOrderAlert", "=============onSwipedAway");

        if (isOfficeAssign()) {

            doSameCityRejectAssignServer();

        }

        return false;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.cdp_time_count_take:


                if (!isOfficeAssign()) {
                    doGetOrder();

                } else {

                    doSameCityRejectAssignServer();

                }


                break;

        }
    }


    /**
     * 点击接单或竞价
     */
    private void doGetOrder() {


        if (null != dialogAlertActivtyCallBack) {

            dialogAlertActivtyCallBack.doFinishPage();

        }


        if (null != currentPushEntity) {


            String businessType = currentPushEntity.getBusinessType();


            switch (businessType) {


                case ConstParams.Orders.SAME_CITY:  //同城

                    doSameCityGrabFromServer();

                    break;


                case ConstParams.Orders.OVER_OFFICE://营业部


                    doOverOfficeGrabFromServer();

                    break;

                case ConstParams.Orders.FULL_LOAD:  //竞价

                    doFullLoadFromServer();


                    break;
            }
        }

    }


    /**
     * 同城抢单
     */
    private void doSameCityGrabFromServer() {


        if (null == currentPushEntity) {

            return;
        }

        String orderNo = currentPushEntity.getOrderNo();


        Request request = RequestPacking.getInstance()
                .getRequestParamForJson(HttpConst.URL.START_TO_GRAB_SAMECITY + HttpConst.SEPARATOR + orderNo,
                        RequestMethod.POST,
                        null);


        CallServer.getInstance().add(HttpConst.HTTP_WHAT, request, new HttpSingleResponseListener<String>() {
            @Override
            protected void OnHttpStart(int what) {

            }


            @Override
            protected void OnHttpSuccessed(int what, Response<String> response) {


                Intent intent = new Intent(mContext, OrderTakeActivity.class);
                mContext.startActivity(intent);

            }

            @Override
            protected void onHttpFailed(int what, Response<String> response) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(response.get(), ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    ToastUtil.showMsg(mContext.getApplicationContext(), errorEntity.getError_message());


                } else {

                    ToastUtil.showMsg(mContext.getApplicationContext(), ConstError.PARSE_ERROR_MSG);

                }
            }

            @Override
            protected void OnHttpFinish(int what) {

                NewOrderAlertDialog.this.dismiss();

                if (null != dialogAlertActivtyCallBack) {

                    dialogAlertActivtyCallBack.doFinishPage();

                }

            }
        });


    }


    /**
     * 营业部抢单
     */
    private void doOverOfficeGrabFromServer() {


        if (null == currentPushEntity) {

            return;
        }


        String orderNo = currentPushEntity.getOrderNo();


        Request request = RequestPacking.getInstance()
                .getRequestParamForJson(HttpConst.URL.START_TO_GRAB_OVER_OFFICE + HttpConst.SEPARATOR + orderNo,
                        RequestMethod.POST,
                        null);


        CallServer.getInstance().add(HttpConst.HTTP_WHAT, request, new HttpSingleResponseListener<String>() {
            @Override
            protected void OnHttpStart(int what) {

            }

            @Override
            protected void OnHttpSuccessed(int what, Response<String> response) {

                Intent intent = new Intent(mContext, OrderTakeActivity.class);
                mContext.startActivity(intent);

            }

            @Override
            protected void onHttpFailed(int what, Response<String> response) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(response.get(), ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    ToastUtil.showMsg(mContext.getApplicationContext(), errorEntity.getError_message());


                } else {

                    ToastUtil.showMsg(mContext.getApplicationContext(), ConstError.PARSE_ERROR_MSG);

                }
            }

            @Override
            protected void OnHttpFinish(int what) {


                NewOrderAlertDialog.this.dismiss();

                if (null != dialogAlertActivtyCallBack) {

                    dialogAlertActivtyCallBack.doFinishPage();

                }
            }
        });


    }


    /**
     * 整车界面
     */
    private void doFullLoadFromServer() {


        if (null == currentPushEntity) {

            return;
        }


        Intent intent = new Intent(mContext, OrderDetailNotTakeBiddingActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstIntent.BundleKEY.DELIVER_ORDER, currentOrder);
        intent.putExtras(bundle);
        mContext.startActivity(intent);


    }


    /**
     * 接受同城指派
     */
    private void doSameCityAcceptAssignServer() {


        if (null == currentPushEntity) {

            return;
        }


        this.dismiss();
        if (null != dialogAlertActivtyCallBack) {

            dialogAlertActivtyCallBack.doFinishPage();

        }

        String orderNo = currentPushEntity.getOrderNo();


        Request request = RequestPacking.getInstance()
                .getRequestParamForJson(HttpConst.URL.ACCEPT_SAMECITY_ASSIGNED + HttpConst.SEPARATOR + orderNo,
                        RequestMethod.POST,
                        null);


        CallServer.getInstance().add(HttpConst.HTTP_WHAT, request, new HttpSingleResponseListener<String>() {
            @Override
            protected void OnHttpStart(int what) {

            }


            @Override
            protected void OnHttpSuccessed(int what, Response<String> response) {


                Intent intent = new Intent(mContext, OrderTakeActivity.class);
                mContext.startActivity(intent);

            }

            @Override
            protected void onHttpFailed(int what, Response<String> response) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(response.get(), ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    ToastUtil.showMsg(mContext.getApplicationContext(), errorEntity.getError_message());


                } else {

                    ToastUtil.showMsg(mContext.getApplicationContext(), ConstError.PARSE_ERROR_MSG);

                }
            }

            @Override
            protected void OnHttpFinish(int what) {

                NewOrderAlertDialog.this.dismiss();

                if (null != dialogAlertActivtyCallBack) {

                    dialogAlertActivtyCallBack.doFinishPage();

                }

            }
        });


    }


    /**
     * 拒绝同城指派
     */
    private void doSameCityRejectAssignServer() {


        if (null == currentPushEntity) {

            return;
        }

        String orderNo = currentPushEntity.getOrderNo();


        Request request = RequestPacking.getInstance()
                .getRequestParamForJson(HttpConst.URL.REJECT_SAMECITY_ASSIGNED + HttpConst.SEPARATOR + orderNo,
                        RequestMethod.POST,
                        null);


        CallServer.getInstance().add(HttpConst.HTTP_WHAT, request, new HttpSingleResponseListener<String>() {
            @Override
            protected void OnHttpStart(int what) {

            }


            @Override
            protected void OnHttpSuccessed(int what, Response<String> response) {


                NewOrderAlertDialog.this.dismiss();
                if (null != dialogAlertActivtyCallBack) {

                    dialogAlertActivtyCallBack.doFinishPage();

                }

            }

            @Override
            protected void onHttpFailed(int what, Response<String> response) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(response.get(), ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    ToastUtil.showMsg(mContext.getApplicationContext(), errorEntity.getError_message());


                } else {

                    ToastUtil.showMsg(mContext.getApplicationContext(), ConstError.PARSE_ERROR_MSG);

                }
            }

            @Override
            protected void OnHttpFinish(int what) {

                NewOrderAlertDialog.this.dismiss();

                if (null != dialogAlertActivtyCallBack) {

                    dialogAlertActivtyCallBack.doFinishPage();

                }

            }
        });


    }


    @Override
    public void countdownFinished() {


        if (isOfficeAssign()) {


            doSameCityAcceptAssignServer();


        } else {

            if (null != dialogAlertActivtyCallBack) {

                dialogAlertActivtyCallBack.doFinishPage();

            }
        }


    }
}































