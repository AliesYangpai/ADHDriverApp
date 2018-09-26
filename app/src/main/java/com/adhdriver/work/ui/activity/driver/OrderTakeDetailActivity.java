package com.adhdriver.work.ui.activity.driver;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.ChannelGatherSelectCallBack;
import com.adhdriver.work.callback.DialogEnterPhoneCodeCallBack;
import com.adhdriver.work.callback.OrderFullLoadOperateCallBack;
import com.adhdriver.work.callback.OrderOverOfficeOperateCallBack;
import com.adhdriver.work.callback.OrderSameCityOperateCallBack;
import com.adhdriver.work.callback.PopwindowOrderTakeTitleCallBack;
import com.adhdriver.work.constant.ConstConfig;
import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.constant.ConstParams;
import com.adhdriver.work.constant.ConstTag;
import com.adhdriver.work.entity.driver.orders.Coordinate;
import com.adhdriver.work.entity.driver.orders.Fee;
import com.adhdriver.work.entity.driver.orders.FeeDetail;
import com.adhdriver.work.entity.driver.orders.From;
import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.entity.driver.orders.OrderTagEntity;
import com.adhdriver.work.entity.driver.orders.PathPoint;
import com.adhdriver.work.entity.driver.orders.To;
import com.adhdriver.work.entity.driver.pay.PayChannelInfo;
import com.adhdriver.work.function.FunctionPathPoint;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.location.KeepLocation;
import com.adhdriver.work.presenter.driver.PresenterDriverOrderTakeDetail;
import com.adhdriver.work.test.TestContent;
import com.adhdriver.work.ui.activity.BaseNavigiteActivity;
import com.adhdriver.work.ui.iview.driver.IOrderTakeDetailView;
import com.adhdriver.work.ui.widget.dialog.PhoneCodeDialog;
import com.adhdriver.work.ui.widget.popwindow.OrderTakeBottomSameCityPopWindow;
import com.adhdriver.work.utils.ImgUtil;
import com.adhdriver.work.utils.ToastUtil;
import com.adhdriver.work.ui.widget.popwindow.GatheringChannelPoWindow;
import com.adhdriver.work.ui.widget.popwindow.OrderTakeBottomFullLoadPopWindow;
import com.adhdriver.work.ui.widget.popwindow.OrderTakeBottomOverOfficePopWindow;
import com.amap.api.location.AMapLocation;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.LatLng;
import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.AMapNaviListener;
import com.amap.api.navi.AMapNaviView;
import com.amap.api.navi.AMapNaviViewListener;
import com.amap.api.navi.AMapNaviViewOptions;
import com.amap.api.navi.enums.NaviType;
import com.amap.api.navi.model.AMapLaneInfo;
import com.amap.api.navi.model.AMapNaviCameraInfo;
import com.amap.api.navi.model.AMapNaviCross;
import com.amap.api.navi.model.AMapNaviInfo;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.navi.model.AMapNaviTrafficFacilityInfo;
import com.amap.api.navi.model.AMapServiceAreaInfo;
import com.amap.api.navi.model.AimLessModeCongestionInfo;
import com.amap.api.navi.model.AimLessModeStat;
import com.amap.api.navi.model.NaviInfo;
import com.amap.api.navi.model.NaviLatLng;
import com.autonavi.tbt.TrafficFacilityInfo;

import org.feezu.liuli.timeselector.Utils.TextUtil;

import java.util.ArrayList;
import java.util.List;

public class OrderTakeDetailActivity extends BaseNavigiteActivity<IOrderTakeDetailView, PresenterDriverOrderTakeDetail>
        implements
        IOrderTakeDetailView,
        View.OnClickListener,
        AMapNaviListener,
        AMapNaviViewListener,
        CompoundButton.OnCheckedChangeListener,
        PopwindowOrderTakeTitleCallBack,
        AMap.OnMapClickListener,
        OrderOverOfficeOperateCallBack,
        OrderFullLoadOperateCallBack,
        DialogEnterPhoneCodeCallBack,
        ChannelGatherSelectCallBack,
        OrderSameCityOperateCallBack {


    private PresenterDriverOrderTakeDetail presenterDriverOrderTakeDetail;


    /**
     * 地图相关
     *
     * @param savedInstanceState
     */


    private RelativeLayout rl_top_bar;

    private AMapNaviView navi_view;
    private AMapNavi mAMapNavi;
    private AMap aMapControl;//地图控制类


    private NaviLatLng mEndLatlng;
    private NaviLatLng mStartLatlng;
    private List<NaviLatLng> sList = new ArrayList<NaviLatLng>();
    private List<NaviLatLng> eList = new ArrayList<NaviLatLng>();
    private List<NaviLatLng> mWayPointList = new ArrayList<NaviLatLng>();


    /**
     * 全览和继续导航
     */
    private CheckBox cb_1;


    /**
     * 数据相关
     *
     * @param savedInstanceState
     */


    private Order currentOrder;
    private OrderTagEntity currentOrderTagEntity; //传递过来的tagOrder依照其中的tag变更界面特效
    private int strategy;


    private View view_line_top;


    /**
     * popWindow相关
     */

    private ImageView iv_common_back;
    private ImageView iv_client_head;
    private TextView tv_client_name;

//    private OrderTakeTitlePopWindow orderTakeTitlePopWindow;

    private OrderTakeBottomOverOfficePopWindow orderTakeBottomOverOfficePopWindow; //营业部的popwindow
    private OrderTakeBottomFullLoadPopWindow orderTakeBottomFullLoadPopWindow; //整车的popwindow
    private OrderTakeBottomSameCityPopWindow orderTakeBottomSameCityPopWindow; //整车的popwindow

    private GatheringChannelPoWindow gatheringChannelPoWindow;


    /**
     * 输入验证码的dialog
     */
    private PhoneCodeDialog phoneCodeDialog;


    private FunctionPathPoint functionPathPoint;

    /**
     * 跳转时候传递的bundle
     *
     * @param order
     * @param orderTagEntity
     * @return
     */
    private Bundle getBundle(Order order, OrderTagEntity orderTagEntity) {

        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstIntent.BundleKEY.DELIVER_ORDER, order);
        bundle.putSerializable(ConstIntent.BundleKEY.DELIVER_ORDER_TAG, orderTagEntity);
        return bundle;

    }


    /**
     * 生成getNaviLatLng 对象
     *
     * @param latitude
     * @param longtitude
     * @return
     */
    private NaviLatLng getNaviLatLng(double latitude, double longtitude) {
        NaviLatLng naviLatLng = new NaviLatLng(latitude, longtitude);
        return naviLatLng;
    }


    private void doSetListNaviLatLng(AMapLocation lastPosition, From from, List<PathPoint> pathPoints, To to) {


        if (null != pathPoints && pathPoints.size() > 0) {


            Coordinate fromCoor = from.getCoordinate();
            Coordinate toCoor = to.getCoordinate();


            sList.add(getNaviLatLng(lastPosition.getLatitude(), lastPosition.getLongitude()));


            eList.add(getNaviLatLng(Double.valueOf(toCoor.getLatitude()), Double.valueOf(toCoor.getLongitude())));


            for (int i = 0; i < pathPoints.size(); i++) {
                PathPoint pathPoint = pathPoints.get(i);
                Coordinate coordinate = pathPoint.getCoordinate();
                mWayPointList.add(getNaviLatLng(Double.valueOf(coordinate.getLatitude()), Double.valueOf(coordinate.getLongitude())));
            }


        } else {
            /**
             * 无途经点
             */
            if (null != from && null != to) {

                Coordinate coordinateFrom = from.getCoordinate();
                Coordinate coordinateTo = to.getCoordinate();
                if (null != lastPosition) {

                    mStartLatlng = new NaviLatLng(lastPosition.getLatitude(), lastPosition.getLongitude());
                } else {

                    if (null != coordinateFrom) {
                        mStartLatlng = new NaviLatLng(Double.valueOf(coordinateFrom.getLatitude()), Double.valueOf(coordinateFrom.getLongitude()));
                    }
                }
                if (null != coordinateTo) {

                    mEndLatlng = new NaviLatLng(Double.valueOf(coordinateTo.getLatitude()), Double.valueOf(coordinateTo.getLongitude()));

                    sList.add(mStartLatlng);
                    eList.add(mEndLatlng);
                }
            }
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_take_detail);

        navi_view.onCreate(savedInstanceState);
        aMapControl = navi_view.getMap();
        aMapControl.setOnMapClickListener(this);

    }

    @Override
    public Resources getResources() {
        return getBaseContext().getResources();
    }


    @Override
    protected PresenterDriverOrderTakeDetail creatPresenter() {
        if (null == presenterDriverOrderTakeDetail) {
            presenterDriverOrderTakeDetail = new PresenterDriverOrderTakeDetail(this);
        }
        return presenterDriverOrderTakeDetail;
    }


    @Override
    protected void onResume() {
        super.onResume();
        navi_view.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        navi_view.onPause();

    }


    @Override
    protected void onDestroy() {
        navi_view.onDestroy();
        //since 1.6.0 不再在naviview destroy的时候自动执行AMapNavi.stopNavi();请自行执行
        mAMapNavi.stopNavi();
        mAMapNavi.destroy();
        super.onDestroy();

    }


    @Override
    protected void initViews() {


        iv_common_back = findADHViewById(R.id.iv_common_back);
        iv_client_head = findADHViewById(R.id.iv_client_head);
        tv_client_name = findADHViewById(R.id.tv_client_name);
        navi_view = findADHViewById(R.id.navi_view);


        /**
         * 地图相关
         */


        mAMapNavi = AMapNavi.getInstance(getApplicationContext());

        /**
         * 放大缩小
         */

        cb_1 = findADHViewById(R.id.cb_1);


        view_line_top = findADHViewById(R.id.view_line_top);
        rl_top_bar = findADHViewById(R.id.rl_top_bar);


        functionPathPoint = new FunctionPathPoint();

    }


    @Override
    protected void initListener() {


        mAMapNavi.addAMapNaviListener(this);
        navi_view.setAMapNaviViewListener(this);

        cb_1.setOnCheckedChangeListener(this);


        navi_view.setOnClickListener(this);
        iv_common_back.setOnClickListener(this);

    }

    @Override
    protected void processIntent() {

        Intent intent = this.getIntent();
        if (null != intent) {
            Bundle bundle = intent.getExtras();
            if (null != bundle) {
                currentOrder = (Order) bundle.getSerializable(ConstIntent.BundleKEY.DELIVER_ORDER);
                currentOrderTagEntity = (OrderTagEntity) bundle.getSerializable(ConstIntent.BundleKEY.DELIVER_ORDER_TAG);
            }
        }
    }

    @Override
    public void showLoadingDialog() {
        showLoadDialog();
    }

    @Override
    public void dismissLoadingDialog() {
        dismissLoadingDialog();
    }

    @Override
    public void onDataBackFail(int code, String errorMsg) {

        ToastUtil.showMsg(getApplicationContext(), errorMsg);
    }

    @Override
    public void onDataBackFailForOverOfficeOrderOperate() {
        if (null != orderTakeBottomOverOfficePopWindow) {
            orderTakeBottomOverOfficePopWindow.getSwipe_btn().toggleState();
        }
    }

    @Override
    public void onDataBackFailForFullLoadOrderOperate() {
        if (null != orderTakeBottomFullLoadPopWindow) {
            orderTakeBottomFullLoadPopWindow.getSwipe_btn().toggleState();
        }

    }

    @Override
    public void onDataBackFailForSameCityOrderOperate() {
        if (null != orderTakeBottomSameCityPopWindow) {
            orderTakeBottomSameCityPopWindow.getSwipe_btn().toggleState();
        }
    }


    @Override
    public void onDataBackSuccessForDeparting() {

    }

    @Override
    public void onDataBackSuccessForArrivePassingPint() {

    }

    @Override
    public void onDataBackSuccessForArriveDestination() {


        if (null != orderTakeBottomOverOfficePopWindow) {

            orderTakeBottomOverOfficePopWindow.getSwipe_btn().setVisibility(View.GONE);
            orderTakeBottomOverOfficePopWindow.getTv_bottom().setText(View.VISIBLE);
        }
    }

    @Override
    public void onDataBackSuccessForGettingGatheringMoneyWay() {

    }

    @Override
    public void doSetLocationDataToNavigate(AMapNaviViewOptions aMapNaviViewOptions) {


//        presenterDriverOrderTakeDetail.doShowTitlePopWindow();


        /**
         * 获取司机上一地点信息
         */
        AMapLocation lastPosition = KeepLocation.getInstance().getLastPosition();


        tv_client_name.setText(currentOrder.getUser_name());
        ImgUtil.getInstance().getRadiusImgFromNetByUrl(currentOrder.getUser_avatar(), iv_client_head, R.drawable.img_default_client_head_round, 120);


        String businessType = currentOrderTagEntity.getBusinessType();
        if (businessType.equals(ConstParams.Orders.OVER_OFFICE)) {


            From from = currentOrder.getFrom();
            To to = currentOrder.getTo();

            Coordinate coordinateFrom = from.getCoordinate();
            Coordinate coordinateTo = to.getCoordinate();


            if (null != lastPosition) {

                mStartLatlng = new NaviLatLng(lastPosition.getLatitude(), lastPosition.getLongitude());
            } else {

                if (null != coordinateFrom) {

                    mStartLatlng = new NaviLatLng(Double.valueOf(coordinateFrom.getLatitude()), Double.valueOf(coordinateFrom.getLongitude()));
                }

            }

            if (null != coordinateTo) {

                mEndLatlng = new NaviLatLng(Double.valueOf(coordinateTo.getLatitude()), Double.valueOf(coordinateTo.getLongitude()));

                sList.add(mStartLatlng);
                eList.add(mEndLatlng);
            }


            presenterDriverOrderTakeDetail.doShowOverOfficePopBottom();


        } else if (businessType.equals(ConstParams.Orders.FULL_LOAD)) {


            From from = currentOrder.getFrom();
            To to = currentOrder.getTo();

            Coordinate coordinateFrom = from.getCoordinate();
            Coordinate coordinateTo = to.getCoordinate();


            if (null != lastPosition) {

                mStartLatlng = new NaviLatLng(lastPosition.getLatitude(), lastPosition.getLongitude());
            } else {

                if (null != coordinateFrom) {

                    mStartLatlng = new NaviLatLng(Double.valueOf(coordinateFrom.getLatitude()), Double.valueOf(coordinateFrom.getLongitude()));
                }

            }

            if (null != coordinateTo) {

                mEndLatlng = new NaviLatLng(Double.valueOf(coordinateTo.getLatitude()), Double.valueOf(coordinateTo.getLongitude()));

                sList.add(mStartLatlng);
                eList.add(mEndLatlng);
            }

            presenterDriverOrderTakeDetail.doShowFullLoadPopBottom();


        } else {

            /**
             * 同城相关
             */

            List<PathPoint> pathPoints = currentOrder.getPath_points();


            doSetListNaviLatLng(lastPosition, currentOrder.getFrom(), pathPoints, currentOrder.getTo());


            presenterDriverOrderTakeDetail.doShowSameCityPopBottom();


        }


        mAMapNavi.setEmulatorNaviSpeed(75);

        navi_view.setViewOptions(aMapNaviViewOptions);


        /**
         * 开始计算了路线
         */
        boolean result = mAMapNavi.calculateDriveRoute(sList, eList, mWayPointList, strategy);


    }


    @Override
    public void doShowTitlePopWindow() {

//        if (null == orderTakeTitlePopWindow) {
//
//            orderTakeTitlePopWindow = new OrderTakeTitlePopWindow(this, currentOrder.getUser_name(), currentOrder.getUser_avatar());
//            orderTakeTitlePopWindow.setPopwindowOrderTakeTitleCallBack(this);
//        }
//
//        orderTakeTitlePopWindow.showAtLocation(navi_view, Gravity.TOP, 0, 0);

    }


    @Override
    public void doShowOverOfficePopBottom() {
        if (null == orderTakeBottomOverOfficePopWindow) {
            orderTakeBottomOverOfficePopWindow = new OrderTakeBottomOverOfficePopWindow(this, currentOrder);
            orderTakeBottomOverOfficePopWindow.setOrderOverOfficeOperateCallBack(this);
        }


        orderTakeBottomOverOfficePopWindow.showAtLocation(navi_view,
                Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void doDealShowOrHideTitlePopWindow() {

//        if (null != orderTakeTitlePopWindow) {
//
//            if (orderTakeTitlePopWindow.isShowing()) {
//                orderTakeTitlePopWindow.dismiss();
//            } else {
//                orderTakeTitlePopWindow.showAsDropDown(view_line_top, 0, 0);
//            }
//        }
    }


    @Override
    public void doDealShowOrHideOverOfficePopWindow() {


        if (null != orderTakeBottomOverOfficePopWindow) {
            if (orderTakeBottomOverOfficePopWindow.isShowing()) {
                orderTakeBottomOverOfficePopWindow.dismiss();
            } else {
                orderTakeBottomOverOfficePopWindow.showAtLocation(navi_view,
                        Gravity.BOTTOM, 0, 0);
            }
        }
    }

    @Override
    public void doDestroyTitlePopWindow() {


//        if (null != orderTakeTitlePopWindow) {
//
//            if (orderTakeTitlePopWindow.isShowing()) {
//                orderTakeTitlePopWindow.dismiss();
//                orderTakeTitlePopWindow = null;
//            } else {
//                orderTakeTitlePopWindow = null;
//            }
//
//        }
    }


    @Override
    public void doDestroyOverOfficePopWindow() {


        if (null != orderTakeBottomOverOfficePopWindow) {

            if (orderTakeBottomOverOfficePopWindow.isShowing()) {
                orderTakeBottomOverOfficePopWindow.dismiss();
                orderTakeBottomOverOfficePopWindow = null;
            } else {

                orderTakeBottomOverOfficePopWindow = null;
            }

        }

    }

    @Override
    public void doShowFullLoadPopBottom() {
        if (null == orderTakeBottomFullLoadPopWindow) {
            orderTakeBottomFullLoadPopWindow = new OrderTakeBottomFullLoadPopWindow(this, currentOrder, currentOrderTagEntity);
            orderTakeBottomFullLoadPopWindow.setOrderFullLoadOperateCallBack(this);
        }


        orderTakeBottomFullLoadPopWindow.showAtLocation(navi_view,
                Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void doDealShowOrHideFullLoadPopWindow() {


        if (null != orderTakeBottomFullLoadPopWindow) {
            if (orderTakeBottomFullLoadPopWindow.isShowing()) {
                orderTakeBottomFullLoadPopWindow.dismiss();
            } else {
                orderTakeBottomFullLoadPopWindow.showAtLocation(navi_view,
                        Gravity.BOTTOM, 0, 0);
            }
        }
    }

    @Override
    public void doDestroyFullLoadPopWindow() {


        if (null != orderTakeBottomFullLoadPopWindow) {

            if (orderTakeBottomFullLoadPopWindow.isShowing()) {
                orderTakeBottomFullLoadPopWindow.dismiss();
                orderTakeBottomFullLoadPopWindow = null;
            } else {

                orderTakeBottomFullLoadPopWindow = null;
            }

        }

    }

    @Override
    public void doShowSameCityPopBottom() {
        if (null == orderTakeBottomSameCityPopWindow) {
            orderTakeBottomSameCityPopWindow = new OrderTakeBottomSameCityPopWindow(this, currentOrder, currentOrderTagEntity);
            orderTakeBottomSameCityPopWindow.setOrderSameCityOperateCallBack(this);
        }

        orderTakeBottomSameCityPopWindow.showAtLocation(navi_view,
                Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void doDealShowOrHideSameCityPopWindow() {


        if (null != orderTakeBottomSameCityPopWindow) {
            if (orderTakeBottomSameCityPopWindow.isShowing()) {
                orderTakeBottomSameCityPopWindow.dismiss();
            } else {
                orderTakeBottomSameCityPopWindow.showAtLocation(navi_view,
                        Gravity.BOTTOM, 0, 0);
            }
        }
    }

    @Override
    public void doDestroySameCityPopWindow() {

        if (null != orderTakeBottomSameCityPopWindow) {

            if (orderTakeBottomSameCityPopWindow.isShowing()) {
                orderTakeBottomSameCityPopWindow.dismiss();
                orderTakeBottomSameCityPopWindow = null;
            } else {

                orderTakeBottomSameCityPopWindow = null;
            }

        }
    }

    @Override
    public void OnDataBackSuccessForOverOfficeArrived() {
        if (null != orderTakeBottomOverOfficePopWindow) {

            orderTakeBottomOverOfficePopWindow.getSwipe_btn().setVisibility(View.GONE);
            orderTakeBottomOverOfficePopWindow.getTv_bottom().setVisibility(View.VISIBLE);


            From from = currentOrder.getFrom();
            To to = currentOrder.getTo();

            String fromAddress = from.getStreet_address();
            String toAddress = to.getStreet_address();

            String[] arry = new String[]{toAddress, fromAddress};

            orderTakeBottomOverOfficePopWindow.getFvl_pass_point().setProgress(0, arry.length, arry, null);
        }
    }

    @Override
    public void onDataBackSuccessForOverOfficeDepart() {

        if (null != orderTakeBottomOverOfficePopWindow) {
            orderTakeBottomOverOfficePopWindow.getSwipe_btn().toggleState();


            From from = currentOrder.getFrom();
            To to = currentOrder.getTo();

            String fromAddress = from.getStreet_address();
            String toAddress = to.getStreet_address();

            String[] arry = new String[]{toAddress, fromAddress};

            orderTakeBottomOverOfficePopWindow.getFvl_pass_point().setProgress(arry.length - 1, arry.length, arry, null);


        }

    }

    @Override
    public void onDataBackSuccessForFullLoadDepart() {


        if (null != orderTakeBottomFullLoadPopWindow) {
            orderTakeBottomFullLoadPopWindow.getSwipe_btn().toggleState();
        }

    }

    @Override
    public void onDataBackSuccessForFullLoadArrive() {


        presenterDriverOrderTakeDetail.doArrivedNext(currentOrder);

    }

    @Override
    public void onDataBackSuccessForSameCityDepart(Order order) {

        presenterDriverOrderTakeDetail.doCheckSameCityDepart(order);
    }

    @Override
    public void onDataBackSuccessForSameCityChangeSwipUiForArrive(Order order) {
        if (null != orderTakeBottomSameCityPopWindow) {

            orderTakeBottomSameCityPopWindow.getSwipe_btn().setText(ConstLocalData.SWIPE_ARRIVE);
            orderTakeBottomSameCityPopWindow.getSwipe_btn().toggleState();


            List<PathPoint> path_points = order.getPath_points();

            From from = order.getFrom();
            To to = order.getTo();
            String[] arry = functionPathPoint.getHasPathPointAllWay(from, to, path_points);

            int count = functionPathPoint.currentDriverNotArrivePathPointCount(path_points);

            if (count == 0) {
                orderTakeBottomSameCityPopWindow.getFvl_pass_point().setProgress(0, arry.length, arry, null);
            } else {


                if(TextUtil.isEmpty(order.getDeparture_time())) {


                    orderTakeBottomSameCityPopWindow.getFvl_pass_point().setProgress(arry.length-count+1, arry.length, arry, null);

                }else {

                    orderTakeBottomSameCityPopWindow.getFvl_pass_point().setProgress(count+1, arry.length, arry, null);


                }

            }


            OrderTagEntity orderTagEntity = new OrderTagEntity();
            orderTagEntity.setBusinessType(currentOrder.getBusiness_type());
            orderTagEntity.setTag(ConstLocalData.DEPART_NOT_ARRIVE);

        }


    }

    @Override
    public void onDataBackSuccessForSameCityChangeSwipUiForToNextPathPoint(Order order) {


        currentOrder = order;
        if (null != orderTakeBottomSameCityPopWindow) {


            List<PathPoint> path_points = order.getPath_points();


            int count = functionPathPoint.currentDriverNotArrivePathPointCount(path_points);



            String[] arry = functionPathPoint.getHasPathPointAllWay(order.getFrom(), order.getTo(), order.getPath_points());
            if (count == 0) {

                orderTakeBottomSameCityPopWindow.getSwipe_btn().setText(ConstLocalData.SWIPE_ARRIVE);
                orderTakeBottomSameCityPopWindow.getSwipe_btn().toggleState();
                orderTakeBottomSameCityPopWindow.getFvl_pass_point().setProgress(arry.length - path_points.size()-1, arry.length, arry, null);
                OrderTagEntity orderTagEntity = new OrderTagEntity();
                orderTagEntity.setBusinessType(currentOrder.getBusiness_type());
                orderTagEntity.setTag(ConstLocalData.DEPART_NOT_ARRIVE);


                orderTakeBottomSameCityPopWindow.setOrderTagEntity(orderTagEntity);

                orderTakeBottomSameCityPopWindow.setOrder(currentOrder);


            } else {

                if(TextUtil.isEmpty(order.getDeparture_time())) {
                    orderTakeBottomSameCityPopWindow.getFvl_pass_point().setProgress(arry.length-count+1, arry.length, arry, null);
                }else {
                    orderTakeBottomSameCityPopWindow.getFvl_pass_point().setProgress(count+1, arry.length, arry, null);
                }
                orderTakeBottomSameCityPopWindow.getSwipe_btn().setText(ConstLocalData.SWIPE_ARRIVE_NEXT);
                orderTakeBottomSameCityPopWindow.getSwipe_btn().toggleState();
                OrderTagEntity orderTagEntity = new OrderTagEntity();
                orderTagEntity.setBusinessType(currentOrder.getBusiness_type());
                orderTagEntity.setTag(ConstLocalData.DEPART_NOT_ARRIVE);
                orderTakeBottomSameCityPopWindow.setOrderTagEntity(orderTagEntity);
                orderTakeBottomSameCityPopWindow.setOrder(currentOrder);
            }



        }

    }

    @Override
    public void onDataBackSuccessForSameCityArrive(Order order) {


        if (null != orderTakeBottomSameCityPopWindow) {

            orderTakeBottomSameCityPopWindow.getSwipe_btn().toggleState();
        }

        presenterDriverOrderTakeDetail.doArrivedNextSameCity(order);


    }

    @Override
    public void onDataBackSuccessForSameCityArriveAndChangeSwipUiForPayToMe(Order order) {
        if (null != orderTakeBottomSameCityPopWindow) {

            orderTakeBottomSameCityPopWindow.getSwipe_btn().setText(ConstLocalData.SWIPE_PAY_TO_ME);
            OrderTagEntity orderTagEntity = new OrderTagEntity();
            orderTagEntity.setBusinessType(currentOrder.getBusiness_type());
            orderTagEntity.setTag(ConstLocalData.ARRIVE_NOT_PAID);
            orderTakeBottomSameCityPopWindow.setOrderTagEntity(orderTagEntity);


            orderTakeBottomSameCityPopWindow.setOrder(order);


            String[] arry = functionPathPoint.getHasPathPointAllWay(order.getFrom(), order.getTo(), order.getPath_points());
            orderTakeBottomSameCityPopWindow.getFvl_pass_point().setProgress(0, arry.length, arry, null);
        }
    }

    @Override
    public void onDataBackSuccessForSameCityArriveAndChangeSwipUiFoSendPhoneCode(Order order) {


        if (null != orderTakeBottomSameCityPopWindow) {

            orderTakeBottomSameCityPopWindow.getSwipe_btn().setText(ConstLocalData.SWIPE_SEND_PHONE_CODE);
            OrderTagEntity orderTagEntity = new OrderTagEntity();
            orderTagEntity.setBusinessType(currentOrder.getBusiness_type());
            orderTagEntity.setTag(ConstLocalData.ARRIVE_HAD_PAID);
            orderTakeBottomSameCityPopWindow.setOrderTagEntity(orderTagEntity);


            orderTakeBottomSameCityPopWindow.setOrder(order);
            String[] arry = functionPathPoint.getHasPathPointAllWay(order.getFrom(), order.getTo(), order.getPath_points());
            orderTakeBottomSameCityPopWindow.getFvl_pass_point().setProgress(0, arry.length, arry, null);
        }

    }


    @Override
    public void onDataBackSuccessForFullLoadArriveAndChangeSwipUiForPayToMe() {
        if (null != orderTakeBottomFullLoadPopWindow) {

            orderTakeBottomFullLoadPopWindow.getSwipe_btn().setText(ConstLocalData.SWIPE_PAY_TO_ME);

            From from = currentOrder.getFrom();
            To to = currentOrder.getTo();

            String fromAddress = from.getStreet_address();
            String toAddress = to.getStreet_address();

            String[] arry = new String[]{toAddress, fromAddress};

            orderTakeBottomFullLoadPopWindow.getFvl_pass_point().setProgress(0, arry.length, arry, null);


            OrderTagEntity orderTagEntity = new OrderTagEntity();
            orderTagEntity.setBusinessType(currentOrder.getBusiness_type());
            orderTagEntity.setTag(ConstLocalData.ARRIVE_NOT_PAID);
            orderTakeBottomFullLoadPopWindow.setOrderTagEntity(orderTagEntity);
        }
    }

    @Override
    public void onDataBackSuccessForFullLoadArriveAndChangeSwipUiFoSendPhoneCode() {

        if (null != orderTakeBottomFullLoadPopWindow) {

            orderTakeBottomFullLoadPopWindow.getSwipe_btn().setText(ConstLocalData.SWIPE_SEND_PHONE_CODE);


            From from = currentOrder.getFrom();
            To to = currentOrder.getTo();

            String fromAddress = from.getStreet_address();
            String toAddress = to.getStreet_address();

            String[] arry = new String[]{toAddress, fromAddress};

            orderTakeBottomFullLoadPopWindow.getFvl_pass_point().setProgress(0, arry.length, arry, null);

            OrderTagEntity orderTagEntity = new OrderTagEntity();
            orderTagEntity.setBusinessType(currentOrder.getBusiness_type());
            orderTagEntity.setTag(ConstLocalData.ARRIVE_HAD_PAID);
            orderTakeBottomFullLoadPopWindow.setOrderTagEntity(orderTagEntity);
        }

    }

    @Override
    public void onDataBackSuccessForSendPhoneCode() {
        if (phoneCodeDialog == null) {
            phoneCodeDialog = new PhoneCodeDialog(this);
            phoneCodeDialog.setDialogEnterPhoneCodeCallBack(this);
            phoneCodeDialog.setCancelable(false);
        }
        phoneCodeDialog.show();
    }

    @Override
    public void onDataBackSuccessForVertifyPhoneCode(String phone, String pass_code) {

        if (null != phoneCodeDialog && phoneCodeDialog.isShowing()) {
            phoneCodeDialog.dismiss();
            phoneCodeDialog = null;
        }


        presenterDriverOrderTakeDetail.doFinishOrder(HttpConst.URL.VERTIFY_TO_FINISH + HttpConst.SEPARATOR + currentOrder.getOrder_no(), pass_code, currentOrderTagEntity.getBusinessType());


    }

    @Override
    public void onDataBackSuccessForOrderFinish() {


        ToastUtil.showMsg(getApplicationContext(), "订单已完成");
        Bundle bundle = getBundle(currentOrder, null);
        openActivityAndFinishItself(OrderTakeDetailFinishActivity.class, bundle);


    }

    @Override
    public void onDataBackSuccessForShowPayChannel(List<PayChannelInfo> payChannelInfos) {

        switch (currentOrderTagEntity.getBusinessType()) {


            case ConstParams.Orders.OVER_OFFICE:
                presenterDriverOrderTakeDetail.doDealShowOrHideOverOfficePopWindow();

                break;

            case ConstParams.Orders.FULL_LOAD:

                presenterDriverOrderTakeDetail.doDealShowOrHideFullLoadPopWindow();
                break;

            case ConstParams.Orders.SAME_CITY:

                presenterDriverOrderTakeDetail.doDealShowOrHideSameCityPopWindow();
                break;
        }


        if (null == gatheringChannelPoWindow) {

            gatheringChannelPoWindow = new GatheringChannelPoWindow(this, payChannelInfos);
            gatheringChannelPoWindow.setChannelGatherSelectCallBack(this);
        }

        gatheringChannelPoWindow.showAtLocation(navi_view,
                Gravity.BOTTOM, 0, 0);


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.iv_common_back:

                String businessType = currentOrder.getBusiness_type();

                switch (businessType) {

                    case ConstParams.Orders.OVER_OFFICE:
                        presenterDriverOrderTakeDetail.doDestroyOverOfficePopWindow();

                        break;

                    case ConstParams.Orders.FULL_LOAD:

                        presenterDriverOrderTakeDetail.doDestroyFullLoadPopWindow();
                        break;

                    case ConstParams.Orders.SAME_CITY:

                        presenterDriverOrderTakeDetail.doDestroySameCityPopWindow();
                        break;
                }


//                presenterDriverOrderTakeDetail.doDestroyTitlePopWindow();


                dofinishItself();

                break;


        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {

            navi_view.displayOverview();

        } else {

            navi_view.recoverLockMode();

        }


    }


    /**
     * ========================================以下全是导航===============================================
     */


    @Override
    public void onInitNaviFailure() {


    }


    @Override
    public void onInitNaviSuccess() {


        Log.i("daohang", " 初始化成功回调" + "onInitNaviSuccess");


        Log.i("daohang", " 初始化成功回调" + "onInitNaviSuccess");


        try {
            //再次强调，最后一个参数为true时代表多路径，否则代表单路径
            strategy = mAMapNavi.strategyConvert(true, false, false, true, false);


        } catch (Exception e) {
            e.printStackTrace();
        }

//        setDataToMap();

        presenterDriverOrderTakeDetail.doSetLocationDataToNavigate();

    }

    @Override
    public void onStartNavi(int i) {

    }

    @Override
    public void onTrafficStatusUpdate() {

    }

    @Override
    public void onLocationChange(AMapNaviLocation aMapNaviLocation) {


        NaviLatLng currentNaviLatLng = aMapNaviLocation.getCoord();


        Log.i("daohang", "导航位置==============" + "onLocationChange" + currentNaviLatLng.getLongitude() + " " + currentNaviLatLng.getLatitude());

    }

    @Override
    public void onGetNavigationText(int i, String s) {
        Log.i("daohang", "回传语音==============" + "onGetNavigationText " + s);
    }

    @Override
    public void onEndEmulatorNavi() {

    }

    @Override
    public void onArriveDestination() {

    }

    @Override
    public void onCalculateRouteSuccess() {


        Log.i("daohang", "计算路线成功==============" + "onCalculateRouteSuccess");

        mAMapNavi.startNavi(NaviType.GPS);
    }

    @Override
    public void onCalculateRouteFailure(int i) {
//        ToastUtil.showMsg(getApplicationContext(), "重新结算路线失败 " + i);

    }

    @Override
    public void onReCalculateRouteForYaw() {

    }

    @Override
    public void onReCalculateRouteForTrafficJam() {

    }

    @Override
    public void onArrivedWayPoint(int i) {

    }

    @Override
    public void onGpsOpenStatus(boolean b) {

    }

    @Override
    public void onNaviInfoUpdate(NaviInfo naviInfo) {

    }

    @Override
    public void onNaviInfoUpdated(AMapNaviInfo aMapNaviInfo) {

    }

    @Override
    public void updateCameraInfo(AMapNaviCameraInfo[] aMapNaviCameraInfos) {

    }

    @Override
    public void onServiceAreaUpdate(AMapServiceAreaInfo[] aMapServiceAreaInfos) {

    }

    @Override
    public void showCross(AMapNaviCross aMapNaviCross) {

    }

    @Override
    public void hideCross() {

    }

    @Override
    public void showLaneInfo(AMapLaneInfo[] aMapLaneInfos, byte[] bytes, byte[] bytes1) {

    }

    @Override
    public void hideLaneInfo() {

    }

    @Override
    public void onCalculateMultipleRoutesSuccess(int[] ints) {

    }

    @Override
    public void notifyParallelRoad(int i) {

    }

    @Override
    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo aMapNaviTrafficFacilityInfo) {

    }

    @Override
    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo[] aMapNaviTrafficFacilityInfos) {

    }

    @Override
    public void OnUpdateTrafficFacility(TrafficFacilityInfo trafficFacilityInfo) {

    }

    @Override
    public void updateAimlessModeStatistics(AimLessModeStat aimLessModeStat) {

    }

    @Override
    public void updateAimlessModeCongestionInfo(AimLessModeCongestionInfo aimLessModeCongestionInfo) {

    }

    @Override
    public void onPlayRing(int i) {

    }

    @Override
    public void onNaviSetting() {

    }

    @Override
    public void onNaviCancel() {

    }

    @Override
    public boolean onNaviBackClick() {


        Log.i("Sss", "onNaviBackClick====");
        return false;
    }

    @Override
    public void onNaviMapMode(int i) {

    }

    @Override
    public void onNaviTurnClick() {

    }

    @Override
    public void onNextRoadClick() {

    }

    @Override
    public void onScanViewButtonClick() {

    }

    @Override
    public void onLockMap(boolean b) {

    }


    @Override
    public void onNaviViewLoaded() {


        Log.i("daohang", "导航页面加载成功==============" + "onNaviViewLoaded");


//
//        try {
//            //再次强调，最后一个参数为true时代表多路径，否则代表单路径
//            strategy = mAMapNavi.strategyConvert(true, false, false, true, false);
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
////        setDataToMap();
//
//        presenterDriverOrderTakeDetail.doSetLocationDataToNavigate();
    }


    /**
     * 顶部titlepopwindow点击关闭
     */
    @Override
    public void onClickBack() {


        String businessType = currentOrder.getBusiness_type();

        switch (businessType) {

            case ConstParams.Orders.OVER_OFFICE:
                presenterDriverOrderTakeDetail.doDestroyOverOfficePopWindow();

                break;

            case ConstParams.Orders.FULL_LOAD:

                presenterDriverOrderTakeDetail.doDestroyFullLoadPopWindow();
                break;

            case ConstParams.Orders.SAME_CITY:

                presenterDriverOrderTakeDetail.doDestroySameCityPopWindow();
                break;
        }


//        presenterDriverOrderTakeDetail.doDestroyTitlePopWindow();


//        presenterDriverOrderTakeDetail.doDestroyBottomInfoPopWindow();

        dofinishItself();

    }

    @Override
    public void onMapClick(LatLng latLng) {
//        presenterDriverOrderTakeDetail.doDealShowOrHideBottomInfoPopWindow();


        String businessType = currentOrder.getBusiness_type();

        switch (businessType) {

            case ConstParams.Orders.OVER_OFFICE:
                presenterDriverOrderTakeDetail.doDealShowOrHideOverOfficePopWindow();

                break;

            case ConstParams.Orders.FULL_LOAD:

                presenterDriverOrderTakeDetail.doDealShowOrHideFullLoadPopWindow();
                break;

            case ConstParams.Orders.SAME_CITY:

                presenterDriverOrderTakeDetail.doDealShowOrHideSameCityPopWindow();
                break;
        }

//        presenterDriverOrderTakeDetail.doDealShowOrHideTitlePopWindow();


    }


    @Override
    public void onOverOfficeDepart(Order order) {


        presenterDriverOrderTakeDetail.doOverOfficeDepart(HttpConst.URL.START_TO_GO_PAKAGE_ORDER + HttpConst.SEPARATOR + order.getOrder_no());

    }

    @Override
    public void onOverOfficeArrive(Order order) {


        presenterDriverOrderTakeDetail.doOverOfficeArrive(HttpConst.URL.REACH_TO_DESTINATION + HttpConst.SEPARATOR + order.getOrder_no());
    }

    @Override
    public void onFullLoadGetPhoneCode(Order order) {
        /**
         * 调用生成code接口
         */

        String receivePhone = order.getTo().getPhone();


        presenterDriverOrderTakeDetail.doSendVertifyCode(HttpConst.URL.GET_CONFIRMCODE, receivePhone, ConstTag.ConfirmCodeTag.ANYWAY);

    }

    @Override
    public void onFullLoadArrive(Order order) {


        presenterDriverOrderTakeDetail.doFullLoadArrive(HttpConst.URL.REACH_TO_DESTINATION + HttpConst.SEPARATOR + order.getOrder_no());
    }

    @Override
    public void onFullLoadDepart(Order order) {


        AMapLocation aMapLocation = KeepLocation.getInstance().getLastPosition();
        if (null == aMapLocation) {
            return;
        }


        presenterDriverOrderTakeDetail.doFullLoadDepart(
                HttpConst.URL.START_TO_GO + HttpConst.SEPARATOR + order.getOrder_no(),
                aMapLocation.getLongitude(),
                aMapLocation.getLatitude());

    }

    @Override
    public void onFullLoadGetPayChannel() {


        presenterDriverOrderTakeDetail.doGetGatherChannel(
                HttpConst.URL.PAY_CHANNELS,
                ConstConfig.ORDER_TYPE,
                ConstConfig.OS_TYPE,
                ConstConfig.DEVICE_TYPE);
    }

    @Override
    public void dialogEnterPhoneCodeCancel() {
        if (null != phoneCodeDialog) {

            phoneCodeDialog.dismiss();
            phoneCodeDialog = null;
        }


        switch (currentOrderTagEntity.getBusinessType()) {

            case ConstParams.Orders.SAME_CITY:

                break;


            case ConstParams.Orders.FULL_LOAD:
                if (null != orderTakeBottomFullLoadPopWindow) {
                    orderTakeBottomFullLoadPopWindow.getSwipe_btn().toggleState();
                }
                break;
        }


    }

    @Override
    public void dialogEnterPhoneCodeSure(String text) {


        /**
         * 验证验证码，
         * 界面跳转
         */

        To to = currentOrder.getTo();
        String phoneReciver = to.getPhone();


        presenterDriverOrderTakeDetail.doVertifyPhoneCode(HttpConst.URL.VERTIFY_CONFIRMCODE, phoneReciver, text, currentOrderTagEntity.getBusinessType());


    }

    @Override
    public void onChannelSelect(String ChannelId) {




        String payer = currentOrder.getPayer();

        String currentOrderNo = currentOrder.getOrder_no();
        Bundle bundle = new Bundle();
        bundle.putString(ConstIntent.BundleKEY.SCAN_WAY_TO_GET_MONEY, ChannelId);
        bundle.putString(ConstIntent.BundleKEY.DELIVER_ORDER_NO, currentOrderNo);
        if (payer.equals(ConstParams.PaySide.SHIPPER)) {
            bundle.putFloat(ConstIntent.BundleKEY.SCAN_MONEY, getGodMoneyInShipper());

        } else if (payer.equals(ConstParams.PaySide.RECIVER)) {

            bundle.putFloat(ConstIntent.BundleKEY.SCAN_MONEY, getThePayAmount());
        }


        Log.i("scanMoney", "传过去的money：" + getGodMoneyInShipper());


        Intent intent = new Intent(this, ScanPayActivity.class);
        intent.putExtras(bundle);

        startActivityForResult(intent, ConstIntent.RequestCode.GO_TO_SCAN_PAY);
    }


    /**
     * 获取当发货款时，含有代收货款的数额
     *
     * @return
     */
    private float getGodMoneyInShipper() {

        float money = 0;


        Fee fee = currentOrder.getFee();

        if (vertifyObject(fee)) {


            List<FeeDetail> details = fee.getDetails();

            if (null != details && details.size() > 0) {

                for (int i = 0; i < details.size(); i++) {

                    FeeDetail detail = details.get(i);

                    String associated_addition_service = detail.getAssociated_addition_service();

                    if (!TextUtils.isEmpty(associated_addition_service)
                            && associated_addition_service.equals(ConstTag.AdditionServiceTag.COD)) {


                        float temp = Float.valueOf(detail.getSubtotal());

                        money += temp;

                    }

                }

            }

        }

        return money;

    }


    /**
     * 获取收货人应该之后
     *
     * @return
     */
    private float getThePayAmount() {

        float money = 0;


        Fee fee = null;
        List<FeeDetail> feeDetails = null;

        if (null != currentOrder) {

            fee = currentOrder.getFee();


            if (vertifyObject(fee)) {

                feeDetails = fee.getDetails();


                if (vertifyObject(feeDetails)) {


                    if (feeDetails.size() > 0) {


                        for (int i = 0; i < feeDetails.size(); i++) {


                            FeeDetail feeDetail = feeDetails.get(i);

                            String payer = feeDetail.getPayer();

                            String moneyTemp = feeDetail.getSubtotal();

                            String name = feeDetail.getName();

                            String associated_addition_service = feeDetail.getAssociated_addition_service();

                            /**
                             * 1.区分线上线下
                             * 2.区分是是否收货人付款
                             */

                            if (payer.equals(ConstParams.PaySide.RECIVER)) {

                                if (name.startsWith(ConstTag.Fee.freight)) {


                                    money += Float.valueOf(moneyTemp);

                                    Log.i("PayMoney", "收货人支付：" + name + " 价格：" + money);


                                }
                                if (!TextUtils.isEmpty(associated_addition_service) &&
                                        associated_addition_service.equals(ConstTag.AdditionServiceTag.COD)) {

                                    money += Float.valueOf(moneyTemp);


                                    Log.i("PayMoney", "收货人支付：" + name + " 价格：" + money);
                                }
                            }


                        }

                    }


                }

            }

        }

        return money;

    }


    private boolean vertifyObject(Object object) {

        boolean result = false;

        if (null != object) {

            result = true;

        }


        return result;

    }

    @Override
    public void onSameCityGetPhoneCode(Order order) {


        /**
         * 调用生成code接口
         */

        String receivePhone = order.getTo().getPhone();

        presenterDriverOrderTakeDetail.doSendVertifyCodeSameCity(HttpConst.URL.GET_CONFIRMCODE, receivePhone, ConstTag.ConfirmCodeTag.ANYWAY);


    }

    @Override
    public void onSameCityArrive(Order order) {
        presenterDriverOrderTakeDetail.doSameCityArrive(HttpConst.URL.REACH_TO_DESTINATION + HttpConst.SEPARATOR + order.getOrder_no());


    }

    @Override
    public void onSameCityArriveNext(Order order) {


        presenterDriverOrderTakeDetail.doSameCityArriveNext(HttpConst.URL.ARRIVE_SAME_CITY_PATH_POINT + HttpConst.SEPARATOR + order.getOrder_no(), order);
    }

    @Override
    public void onSameCityDepart(Order order) {
        AMapLocation aMapLocation = KeepLocation.getInstance().getLastPosition();
        if (null == aMapLocation) {
            return;
        }


        presenterDriverOrderTakeDetail.doSameCityDepart(
                HttpConst.URL.START_TO_GO + HttpConst.SEPARATOR + order.getOrder_no(),
                aMapLocation.getLongitude(),
                aMapLocation.getLatitude());
    }

    @Override
    public void onSameCityGetPayChannel() {


        presenterDriverOrderTakeDetail.doGetGatherChannel(
                HttpConst.URL.PAY_CHANNELS,
                ConstConfig.ORDER_TYPE,
                ConstConfig.OS_TYPE,
                ConstConfig.DEVICE_TYPE);

    }


}
