package com.adhdriver.work.ui.fragment.driver.main3;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.BiddingDialogCallBack;
import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.constant.ConstParams;
import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.entity.EventEntity;
import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.presenter.driver.PresenterDriverFgAcrossCityOrder;
import com.adhdriver.work.ui.activity.common.LoginActivity;
import com.adhdriver.work.ui.activity.driver.OrderDetailNotTakeBiddingActivity;
import com.adhdriver.work.ui.activity.driver.OrderDetailNotTakeOverOfficeActivity;
import com.adhdriver.work.ui.adapter.OrderAcrossCityNotTakeAdapter;
import com.adhdriver.work.ui.fragment.BaseFragment;
import com.adhdriver.work.ui.iview.driver.IFgOrderNotTakeAcrossCityView;
import com.adhdriver.work.utils.SpUtil;
import com.adhdriver.work.utils.ToastUtil;
import com.adhdriver.work.ui.widget.dialog.BiddingDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

/**
 * 跨城的fragment.
 */
public class FragmentDriverAcrossCity extends BaseFragment<IFgOrderNotTakeAcrossCityView,PresenterDriverFgAcrossCityOrder> implements
        IFgOrderNotTakeAcrossCityView,
        BaseQuickAdapter.OnItemChildClickListener,
        BaseQuickAdapter.OnItemClickListener,
        SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener,
        BiddingDialogCallBack {

    public static final String TAG = FragmentDriverAcrossCity.class.getSimpleName();

    private PresenterDriverFgAcrossCityOrder presenterDriverFgAcrossCityOrder;







    private SwipeRefreshLayout srefresh_layout;
    private RecyclerView rv_nottake_across_city;
    private RecyclerView.LayoutManager layoutManager;
    private OrderAcrossCityNotTakeAdapter orderAcrossCityNotTakeAdapter;



    /**
     * 数据相关
     */
    private int currentSize = ConstLocalData.DEFAULT_INCREMENT_SIZE;//当前显示的数量 用于下拉差量更新    默认6 //加载刷新
    private int currentIndex = ConstLocalData.DEFUALT_LIST_INDEX;//用于上拉加载更多；       默认1       //加载刷新


    /**
     * dialog相关
     * @return
     */

    private BiddingDialog biddingDialog;




    private boolean isVisitor() {

        return SpUtil.getInstance().getBoolenValue(ConstSp.SP_KEY_VISITOR, ConstSp.SP_VALUE.DEFAULT_TRUE_VISITOR);

    }

    @Override
    protected PresenterDriverFgAcrossCityOrder creatPresenter() {
        if(null == presenterDriverFgAcrossCityOrder) {
            presenterDriverFgAcrossCityOrder = new PresenterDriverFgAcrossCityOrder(this);
        }
        return presenterDriverFgAcrossCityOrder;
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_driver_across_city;
    }

    @Override
    protected void getSendData(Bundle arguments) {

    }

    @Override
    protected void initView() {


        srefresh_layout = findADHViewById(R.id.srefresh_layout);
        srefresh_layout.setColorSchemeColors(getSwipeRefreshColor());

        rv_nottake_across_city = findADHViewById(R.id.rv_nottake_across_city);
        layoutManager = new LinearLayoutManager(mActivity);


        orderAcrossCityNotTakeAdapter = new OrderAcrossCityNotTakeAdapter(R.layout.item_order_not_take_across_city);


        rv_nottake_across_city.setLayoutManager(layoutManager);
        rv_nottake_across_city.setAdapter(orderAcrossCityNotTakeAdapter);
    }

    @Override
    protected void initListener() {


        srefresh_layout.setOnRefreshListener(this);
        orderAcrossCityNotTakeAdapter.setOnLoadMoreListener(this, rv_nottake_across_city);
        orderAcrossCityNotTakeAdapter.setOnItemChildClickListener(this);
        orderAcrossCityNotTakeAdapter.setOnItemClickListener(this);


        //默认第一次加载会进入回调，如果不需要可以配置
        orderAcrossCityNotTakeAdapter.disableLoadMoreIfNotFullPage(rv_nottake_across_city);

    }

    @Override
    protected void onLazyLoad() {



        if(isVisitor()) {


            presenterDriverFgAcrossCityOrder.doGetAcrossCityOrders(
                    HttpConst.URL.VISITORS_CROSS_CITY_NEWEST,
                    currentSize,
                    ConstLocalData.DEFUALT_LIST_INDEX);

        }else {
            presenterDriverFgAcrossCityOrder.doGetAcrossCityOrders(
                    HttpConst.URL.CROSS_CITY_NEWEST,
                    currentSize,
                    ConstLocalData.DEFUALT_LIST_INDEX);

        }

//        presenterDriverFgAcrossCityOrder.doGetAcrossCityOrders(
//                HttpConst.URL.CROSS_CITY_NEWEST,
//                currentSize,
//                ConstLocalData.DEFUALT_LIST_INDEX);

//        orderAcrossCityNotTakeAdapter.addData(TestContent.getTestList());

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (!hidden) {



            if(isVisitor()){



                presenterDriverFgAcrossCityOrder.doGetAcrossCityOrders(
                        HttpConst.URL.VISITORS_CROSS_CITY_NEWEST,
                        currentSize,
                        ConstLocalData.DEFUALT_LIST_INDEX);

            }else {
                presenterDriverFgAcrossCityOrder.doGetAcrossCityOrders(
                        HttpConst.URL.CROSS_CITY_NEWEST,
                        currentSize,
                        ConstLocalData.DEFUALT_LIST_INDEX);
            }

        }

    }

    @Override
    protected void onEventBack(EventEntity eventEntity) {

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
        ToastUtil.showMsg(mActivity.getApplicationContext(),errorMsg);
    }



    @Override
    public void onDataBackFailInLoadMore(int code, String errorMsg) {


        orderAcrossCityNotTakeAdapter.loadMoreFail();
        ToastUtil.showMsg(mActivity.getApplicationContext(), errorMsg);

    }


    @Override
    public void dismissFreshLoading() {

        srefresh_layout.setRefreshing(false);

    }

    @Override
    public void onDataBackSuccessForAcrossCityOrders(List<Order> list) {
        orderAcrossCityNotTakeAdapter.setNewData(list);
    }

    @Override
    public void onDataBackSuccessForAcrossCityOrdersInFresh(List<Order> list) {

        orderAcrossCityNotTakeAdapter.setNewData(list);

    }

    @Override
    public void onDataBackSuccessForAcrossCityOrdersInLoadMore(List<Order> list) {



        if (null != list && list.size() > 0) {

            orderAcrossCityNotTakeAdapter.addData(list);
            orderAcrossCityNotTakeAdapter.loadMoreComplete();
            currentSize += ConstLocalData.DEFAULT_INCREMENT_SIZE;   //这是设置给 下拉刷新用的//加载刷新
            currentIndex += ConstLocalData.DEFUALT_LIST_INDEX;

        } else {

            orderAcrossCityNotTakeAdapter.loadMoreEnd();
        }


    }

    @Override
    public void onDataBackSuccessForBiddingSuccess() {
        ToastUtil.showMsg(mActivity.getApplicationContext(),"竞价成功请静候佳音");

        biddingDialog.dismiss();
        biddingDialog = null;
    }

    @Override
    public void onDataBackSuccessForGrabSuccess() {
        /**
         * 暂不处理
         */
    }

    @Override
    public void onDataBackSuccessForOrderDetial(Order order) {


        String businessType = order.getBusiness_type();


        Bundle bundle = new Bundle();
        switch (businessType) {


            case ConstParams.Orders.FULL_LOAD:
                bundle.putSerializable(ConstIntent.BundleKEY.DELIVER_ORDER,order);
                openActivity(OrderDetailNotTakeBiddingActivity.class,bundle);
                break;

            case ConstParams.Orders.OVER_OFFICE:

                bundle.putSerializable(ConstIntent.BundleKEY.DELIVER_ORDER,order);
                openActivity(OrderDetailNotTakeOverOfficeActivity.class,bundle);
                break;

        }
    }


    @Override
    public void onRefresh() {


        if(isVisitor()) {



            presenterDriverFgAcrossCityOrder.doGetAcrossCityOrdersInFresh(
                    HttpConst.URL.VISITORS_CROSS_CITY_NEWEST,
                    currentSize,
                    ConstLocalData.DEFUALT_LIST_INDEX);
        }else {

            presenterDriverFgAcrossCityOrder.doGetAcrossCityOrdersInFresh(
                    HttpConst.URL.CROSS_CITY_NEWEST,
                    currentSize,
                    ConstLocalData.DEFUALT_LIST_INDEX);
        }



    }


    private boolean isMineBidding(Order order) {


        boolean result = false;
        boolean quoted = order.is_quoted();
        List<String> quote_driver_ids = order.getQuote_driver_ids();


        if (quoted) {


            if (null != quote_driver_ids && quote_driver_ids.size() > 0) {
                for (String driverId : quote_driver_ids) {
                    String localDriverId = SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_DRIVER_ID, ConstSp.SP_VALUE.DEFAULT_STRING);
                    if (driverId.equals(localDriverId)) {
                        result = true;
                        break;
                    }
                }
            }
        }
        return result;

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {


       Order order = (Order) adapter.getData().get(position);

        switch (view.getId()) {

            case R.id.iv_bidding:



                if(isVisitor()) {
                    openActivity(LoginActivity.class,null);
                    return;
                }



                if(!isMineBidding(order)) {

                    if(null == biddingDialog) {

                        biddingDialog = new BiddingDialog(getActivity());
                        biddingDialog.setBiddingDialogCallBack(this);
                        biddingDialog.setOrder(order);
                    }
                    biddingDialog.show();

                }

//                if(null == biddingDialog) {
//
//                    biddingDialog = new BiddingDialog(getActivity());
//                    biddingDialog.setBiddingDialogCallBack(this);
//                    biddingDialog.setOrder(order);
//                }
//                biddingDialog.show();



                break;


            case R.id.iv_grab:

                if(isVisitor()) {
                    openActivity(LoginActivity.class,null);
                    return;
                }



                boolean isfix = order.is_fixed_price();

                if(isfix) {

                    presenterDriverFgAcrossCityOrder.doStartGrab(HttpConst.URL.START_TO_TAKE_FIXED_FULLLOAD+HttpConst.SEPARATOR+order.getOrder_no());

                }else {

                    presenterDriverFgAcrossCityOrder.doStartGrab(HttpConst.URL.START_TO_GRAB_OVER_OFFICE+HttpConst.SEPARATOR+order.getOrder_no());

                }


                break;
        }

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        Order order = (Order) adapter.getData().get(position);


        String businessType = order.getBusiness_type();

        switch (businessType) {


            case ConstParams.Orders.FULL_LOAD:

                presenterDriverFgAcrossCityOrder.doGetOrderDetail(HttpConst.URL.ORDERS+HttpConst.SEPARATOR+order.getOrder_no());

                break;

            case ConstParams.Orders.OVER_OFFICE:

                presenterDriverFgAcrossCityOrder.doGetOrderDetail(HttpConst.URL.ORDERS+HttpConst.SEPARATOR+order.getOrder_no());
                break;

        }




    }

    @Override
    public void onLoadMoreRequested() {



        int tempIndex = currentIndex + ConstLocalData.DEFUALT_LIST_INDEX;


        if(isVisitor()) {

            presenterDriverFgAcrossCityOrder.doGetAcrossCityOrdersInLoadMore(
                    HttpConst.URL.VISITORS_CROSS_CITY_NEWEST,
                    ConstLocalData.DEFAULT_INCREMENT_SIZE,
                    tempIndex);

        }else {
            presenterDriverFgAcrossCityOrder.doGetAcrossCityOrdersInLoadMore(
                    HttpConst.URL.CROSS_CITY_NEWEST,
                    ConstLocalData.DEFAULT_INCREMENT_SIZE,
                    tempIndex);
        }




        Log.i("onLoadMore", "=============ChildFragmentFullLoad=onLoadMoreRequested");

    }

    @Override
    public void startBidding(String orderNo, String price) {

        presenterDriverFgAcrossCityOrder.doStartBidding(HttpConst.URL.START_TO_BIDDING+HttpConst.SEPARATOR+orderNo,price);


    }

    @Override
    public void cancelBidding() {

        biddingDialog.dismiss();
        biddingDialog = null;
    }
}
