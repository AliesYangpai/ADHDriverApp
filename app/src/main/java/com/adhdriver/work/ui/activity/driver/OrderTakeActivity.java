package com.adhdriver.work.ui.activity.driver;

import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.entity.driver.orders.Coordinate;
import com.adhdriver.work.entity.driver.orders.From;
import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.entity.driver.orders.OrderTagEntity;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.location.KeepLocation;
import com.adhdriver.work.presenter.driver.PresenterDriverOrderTake;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.adapter.OrderTakeAdapter;
import com.adhdriver.work.ui.iview.driver.IOrderTakeView;
import com.adhdriver.work.utils.ToastUtil;
import com.amap.api.location.AMapLocation;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

public class OrderTakeActivity extends BaseActivity<IOrderTakeView, PresenterDriverOrderTake> implements
        IOrderTakeView,
        View.OnClickListener,
        BaseQuickAdapter.OnItemClickListener,
        SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {


    private PresenterDriverOrderTake presenterDriverOrderTake;


    /**
     * title
     *
     * @param savedInstanceState
     */

    private ImageView iv_common_back;
    private TextView tv_common_title;


    private SwipeRefreshLayout srefresh_layout;
    private RecyclerView rv_order_take;
    private RecyclerView.LayoutManager layoutManager;
    private OrderTakeAdapter orderTakeAdapter;


    /**
     * 数据相关
     */
    private int currentSize = ConstLocalData.DEFAULT_INCREMENT_SIZE;//当前显示的数量 用于下拉差量更新    默认6 //加载刷新
    private int currentIndex = ConstLocalData.DEFUALT_LIST_INDEX;//用于上拉加载更多；       默认1       //加载刷新


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_take);


        presenterDriverOrderTake.doGetOrderTake(
                HttpConst.URL.ORDERS,
                currentSize,
                ConstLocalData.DEFUALT_LIST_INDEX);


    }

    @Override
    protected PresenterDriverOrderTake creatPresenter() {
        if (null == presenterDriverOrderTake) {
            presenterDriverOrderTake = new PresenterDriverOrderTake(this);
        }
        return presenterDriverOrderTake;
    }

    @Override
    protected void initViews() {
        /**
         * title
         * @param savedInstanceState
         */
        iv_common_back = findADHViewById(R.id.iv_common_back);
        tv_common_title = findADHViewById(R.id.tv_common_title);
        tv_common_title.setText(getString(R.string.journey));

        srefresh_layout = findADHViewById(R.id.srefresh_layout);
        srefresh_layout.setColorSchemeColors(getSwipeRefreshColor());

        rv_order_take = findADHViewById(R.id.rv_order_take);
        layoutManager = new LinearLayoutManager(this);


        orderTakeAdapter = new OrderTakeAdapter(R.layout.item_order_take);
        rv_order_take.setLayoutManager(layoutManager);
        rv_order_take.setAdapter(orderTakeAdapter);
    }

    @Override
    protected void initListener() {

        iv_common_back.setOnClickListener(this);


        srefresh_layout.setOnRefreshListener(this);
        orderTakeAdapter.setOnLoadMoreListener(this, rv_order_take);
        orderTakeAdapter.setOnItemClickListener(this);


        //默认第一次加载会进入回调，如果不需要可以配置
        orderTakeAdapter.disableLoadMoreIfNotFullPage(rv_order_take);
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
    public void onDataBackFailInLoadMore(int code, String errorMsg) {


        orderTakeAdapter.loadMoreFail();
        ToastUtil.showMsg(getApplicationContext(), errorMsg);
    }

    @Override
    public void dismissFreshLoading() {
        srefresh_layout.setRefreshing(false);
    }

    @Override
    public void onDataBackSuccessForOrderTakeOrders(List<Order> list) {
        orderTakeAdapter.setNewData(list);
    }

    @Override
    public void onDataBackSuccessForOrderTakeInFresh(List<Order> list) {
        orderTakeAdapter.setNewData(list);
    }

    @Override
    public void onDataBackSuccessForOrderTakeInLoadMore(List<Order> list) {
        if (null != list && list.size() > 0) {

            orderTakeAdapter.addData(list);
            orderTakeAdapter.loadMoreComplete();
            currentSize += ConstLocalData.DEFAULT_INCREMENT_SIZE;   //这是设置给 下拉刷新用的//加载刷新
            currentIndex += ConstLocalData.DEFUALT_LIST_INDEX;

        } else {
            orderTakeAdapter.loadMoreEnd();
        }

    }

    @Override
    public void onDataBackSuccessForSameCityComplete(Order order) {


        OrderTagEntity orderTagEntity = new OrderTagEntity(order.getBusiness_type(), ConstLocalData.COMPLETE);
        Bundle bundle = getBundle(order, orderTagEntity);
        openActivity(OrderTakeDetailFinishActivity.class, bundle);


    }

    @Override
    public void onDataBackSuccessForSameCityCancel(Order order) {


        OrderTagEntity orderTagEntity = new OrderTagEntity(order.getBusiness_type(), ConstLocalData.CANCEL);
        Bundle bundle = getBundle(order, orderTagEntity);
        openActivity(OrderTakeDetailCancelActivity.class, bundle);


    }

    @Override
    public void onDataBackSuccessForSameCityRefund(Order order) {

        OrderTagEntity orderTagEntity = new OrderTagEntity(order.getBusiness_type(), ConstLocalData.REFUND);
        Bundle bundle = getBundle(order, orderTagEntity);
        openActivity(OrderTakeDetailRefundActivity.class, bundle);


    }

    @Override
    public void onDataBackSuccessForSameCityNotDepart(Order order) {

        /**
         * 执行经纬度距离作差并出发判断并出发
         */


        From from = order.getFrom();


        if(null == from) {
            return;
        }
        Coordinate coordinate = from.getCoordinate();



        if (null == coordinate) {
            return;
        }
        LatLng latLng1 = new LatLng(Double.valueOf(coordinate.getLatitude()), Double.valueOf(coordinate.getLongitude()));
        AMapLocation aMapLocation = KeepLocation.getInstance().getLastPosition();
        if (null == aMapLocation) {
            return;
        }
        LatLng latLng2 = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
        float meter = AMapUtils.calculateLineDistance(latLng1, latLng2);

        presenterDriverOrderTake.doDepartSameCity(HttpConst.URL.START_TO_GO+HttpConst.SEPARATOR+order.getOrder_no(), order, meter, aMapLocation.getLongitude(), aMapLocation.getLatitude());


    }

    @Override
    public void onDataBackSuccessForSameCityArriveAndHasPaid(Order order) {


        OrderTagEntity orderTagEntity = new OrderTagEntity(order.getBusiness_type(), ConstLocalData.ARRIVE_HAD_PAID);
        Bundle bundle = getBundle(order, orderTagEntity);
        openActivity(OrderTakeDetailActivity.class, bundle);


    }

    @Override
    public void onDataBackSuccessForSameCityArriveAndNotPaid(Order order) {


        OrderTagEntity orderTagEntity = new OrderTagEntity(order.getBusiness_type(), ConstLocalData.ARRIVE_NOT_PAID);
        Bundle bundle = getBundle(order, orderTagEntity);
        openActivity(OrderTakeDetailActivity.class, bundle);

    }

    @Override
    public void onDataBackSuccessForSameCityDepartAndNotArrive(Order order) {

        OrderTagEntity orderTagEntity = new OrderTagEntity(order.getBusiness_type(), ConstLocalData.DEPART_NOT_ARRIVE);
        Bundle bundle = getBundle(order, orderTagEntity);
        openActivity(OrderTakeDetailActivity.class, bundle);


    }

    @Override
    public void onDataBackSuccessForSameCityAutoDepart(Order order) {

        OrderTagEntity orderTagEntity = new OrderTagEntity(order.getBusiness_type(), ConstLocalData.DEPART_NOT_ARRIVE);
        Bundle bundle = getBundle(order, orderTagEntity);
        openActivity(OrderTakeDetailActivity.class, bundle);


    }

    @Override
    public void onDataBackSuccessForSameCityNotInAutoDepartArea(Order order) {


        OrderTagEntity orderTagEntity = new OrderTagEntity(order.getBusiness_type(), ConstLocalData.DEPART_NOT);
        Bundle bundle = getBundle(order, orderTagEntity);
        openActivity(OrderTakeDetailActivity.class, bundle);


    }

    @Override
    public void onDataBackSuccessForFullLoadComplete(Order order) {


        OrderTagEntity orderTagEntity = new OrderTagEntity(order.getBusiness_type(), ConstLocalData.COMPLETE);
        Bundle bundle = getBundle(order, orderTagEntity);
        openActivity(OrderTakeDetailFinishActivity.class, bundle);





    }

    @Override
    public void onDataBackSuccessForFullLoadCancel(Order order) {


        OrderTagEntity orderTagEntity = new OrderTagEntity(order.getBusiness_type(), ConstLocalData.CANCEL);
        Bundle bundle = getBundle(order, orderTagEntity);
        openActivity(OrderTakeDetailCancelActivity.class, bundle);

    }

    @Override
    public void onDataBackSuccessForFullLoadRefund(Order order) {


        OrderTagEntity orderTagEntity = new OrderTagEntity(order.getBusiness_type(), ConstLocalData.REFUND);
        Bundle bundle = getBundle(order, orderTagEntity);
        openActivity(OrderTakeDetailRefundActivity.class, bundle);
    }

    @Override
    public void onDataBackSuccessForFullLoadNotDepart(Order order) {
        /**
         * 执行经纬度距离作差并出发判断并出发
         */


        From from = order.getFrom();
        Coordinate coordinate = null;
        if(null != from) {

            coordinate = from.getCoordinate();
        }

        if (null == coordinate) {
            return;
        }
        LatLng latLng1 = new LatLng(Double.valueOf(coordinate.getLatitude()), Double.valueOf(coordinate.getLongitude()));
        AMapLocation aMapLocation = KeepLocation.getInstance().getLastPosition();
        if (null == aMapLocation) {
            return;
        }
        LatLng latLng2 = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
        float meter = AMapUtils.calculateLineDistance(latLng1, latLng2);


        presenterDriverOrderTake.doDepartFullLoad(HttpConst.URL.START_TO_GO + HttpConst.SEPARATOR + order.getOrder_no(), order, meter, aMapLocation.getLongitude(), aMapLocation.getLatitude());

    }

    @Override
    public void onDataBackSuccessForFullLoadArriveAndHasPaid(Order order) {
        OrderTagEntity orderTagEntity = new OrderTagEntity(order.getBusiness_type(), ConstLocalData.ARRIVE_HAD_PAID);
        Bundle bundle = getBundle(order, orderTagEntity);
        openActivity(OrderTakeDetailActivity.class, bundle);

    }

    @Override
    public void onDataBackSuccessForFullLoadArriveAndNotPaid(Order order) {

        OrderTagEntity orderTagEntity = new OrderTagEntity(order.getBusiness_type(), ConstLocalData.ARRIVE_NOT_PAID);
        Bundle bundle = getBundle(order, orderTagEntity);
        openActivity(OrderTakeDetailActivity.class, bundle);
    }

    @Override
    public void onDataBackSuccessForFullLoadDepartAndNotArrive(Order order) {
        OrderTagEntity orderTagEntity = new OrderTagEntity(order.getBusiness_type(), ConstLocalData.DEPART_NOT_ARRIVE);
        Bundle bundle = getBundle(order, orderTagEntity);
        openActivity(OrderTakeDetailActivity.class, bundle);

    }

    @Override
    public void onDataBackSuccessForFullLoadAutoDepart(Order order) {


        OrderTagEntity orderTagEntity = new OrderTagEntity(order.getBusiness_type(), ConstLocalData.DEPART_NOT_ARRIVE);
        Bundle bundle = getBundle(order, orderTagEntity);
        openActivity(OrderTakeDetailActivity.class, bundle);

    }

    @Override
    public void onDataBackSuccessForFullLoadNotInAutoDepartArea(Order order) {
        OrderTagEntity orderTagEntity = new OrderTagEntity(order.getBusiness_type(), ConstLocalData.DEPART_NOT);
        Bundle bundle = getBundle(order, orderTagEntity);
        openActivity(OrderTakeDetailActivity.class, bundle);

    }


    @Override
    public void onDataBackSuccessForOverOfficeComplete(Order order) {


        OrderTagEntity orderTagEntity = new OrderTagEntity(order.getBusiness_type(), ConstLocalData.COMPLETE);
        Bundle bundle = getBundle(order, orderTagEntity);
        openActivity(OrderTakeDetailFinishActivity.class, bundle);

    }


    @Override
    public void onDataBackSuccessForOverOfficeNotDepart(Order order) {

        From from = order.getFrom();
        Coordinate coordinate = from.getCoordinate();

        if (null == coordinate) {
            return;
        }
        LatLng latLng1 = new LatLng(Double.valueOf(coordinate.getLatitude()), Double.valueOf(coordinate.getLongitude()));

        AMapLocation aMapLocation = KeepLocation.getInstance().getLastPosition();
        if (null == aMapLocation) {
            return;
        }
        LatLng latLng2 = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
        float meter = AMapUtils.calculateLineDistance(latLng1, latLng2);


        presenterDriverOrderTake.doDepartOverOffice(HttpConst.URL.START_TO_GO_PAKAGE_ORDER + HttpConst.SEPARATOR + order.getOrder_no(), order, meter);

    }

    @Override
    public void onDataBackSuccessForOverOfficeDepartAndNotArrive(Order order) {


        OrderTagEntity orderTagEntity = new OrderTagEntity(order.getBusiness_type(), ConstLocalData.DEPART_NOT_ARRIVE);
        Bundle bundle = getBundle(order, orderTagEntity);
        openActivity(OrderTakeDetailActivity.class, bundle);

    }

    @Override
    public void onDataBackSuccessForOverOfficeArrive(Order order) {

        OrderTagEntity orderTagEntity = new OrderTagEntity(order.getBusiness_type(), ConstLocalData.DEPART_OVER_OFFICE);
        Bundle bundle = getBundle(order, orderTagEntity);
        openActivity(OrderTakeDetailActivity.class, bundle);

    }

    @Override
    public void onDataBackSuccessForOverOfficeAutoDepart(Order order) {


        OrderTagEntity orderTagEntity = new OrderTagEntity(order.getBusiness_type(), ConstLocalData.DEPART_NOT_ARRIVE);
        Bundle bundle = getBundle(order, orderTagEntity);
        openActivity(OrderTakeDetailActivity.class, bundle);

    }

    @Override
    public void onDataBackSuccessForOverOfficeNotInAutoDepartArea(Order order) {


        OrderTagEntity orderTagEntity = new OrderTagEntity(order.getBusiness_type(), ConstLocalData.DEPART_NOT);
        Bundle bundle = getBundle(order, orderTagEntity);
        openActivity(OrderTakeDetailActivity.class, bundle);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_common_back:
                dofinishItself();
                break;
        }
    }

    @Override
    public void onRefresh() {
        presenterDriverOrderTake.doGetOrderTakeInFresh(
                HttpConst.URL.ORDERS,
                currentSize,
                ConstLocalData.DEFUALT_LIST_INDEX);
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


        Order order = (Order) adapter.getData().get(position);

//        Bundle bundle = new Bundle();
//        bundle.putSerializable(ConstIntent.BundleKEY.DELIVER_ORDER, order);
//        openActivity(OrderTakeDetailActivity.class, bundle);


        presenterDriverOrderTake.doGetOrderInfo(HttpConst.URL.ORDERS + HttpConst.SEPARATOR + order.getOrder_no());

    }

    @Override
    public void onLoadMoreRequested() {
        int tempIndex = currentIndex + ConstLocalData.DEFUALT_LIST_INDEX;

        presenterDriverOrderTake.doGetOrderTakeInLoadMore(
                HttpConst.URL.ORDERS,
                ConstLocalData.DEFAULT_INCREMENT_SIZE,
                tempIndex);


        Log.i("onLoadMore", "=============ChildFragmentFullLoad=onLoadMoreRequested");

    }
}
