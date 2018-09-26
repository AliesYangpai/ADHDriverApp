package com.adhdriver.work.ui.fragment.driver.main3;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstSign;
import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.entity.EventEntity;
import com.adhdriver.work.entity.driver.wallet.TodayIncome;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.presenter.driver.PresenterDriverFgSameCity;
import com.adhdriver.work.ui.fragment.BaseFragment;
import com.adhdriver.work.ui.iview.driver.IFgOrderNotTakeSameCityView;
import com.adhdriver.work.utils.SpUtil;
import com.adhdriver.work.utils.ToastUtil;
import com.adhdriver.work.ui.widget.circle.WaitOrderProgress;

import java.text.DecimalFormat;


/**
 * 同城的fragment
 */
public class FragmentDriverSameCity extends BaseFragment<IFgOrderNotTakeSameCityView, PresenterDriverFgSameCity>
        implements
        IFgOrderNotTakeSameCityView,
        View.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener {


    public static final String TAG = FragmentDriverSameCity.class.getSimpleName();


    private PresenterDriverFgSameCity presenterDriverFgSameCity;
    /**
     * titile
     *
     * @param savedInstanceState
     */


    private SwipeRefreshLayout srefresh_layout;

    private TextView tv_order_recive_count;
    private TextView tv_order_recive_income;

    private WaitOrderProgress wp_cirlce;


    public void doShowOrHideCircle() {

        if (isDipatch()) {
            wp_cirlce.setVisibility(View.VISIBLE);
        } else {
            wp_cirlce.setVisibility(View.GONE);
        }
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


    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_driver_same_city;
    }

    @Override
    protected void getSendData(Bundle arguments) {

    }

    @Override
    protected void initView() {


        srefresh_layout = findADHViewById(R.id.srefresh_layout);
        srefresh_layout.setColorSchemeColors(getSwipeRefreshColor());

        tv_order_recive_count = findADHViewById(R.id.tv_order_recive_count);
        tv_order_recive_income = findADHViewById(R.id.tv_order_recive_income);

        wp_cirlce = findADHViewById(R.id.wp_cirlce);


    }

    @Override
    protected void initListener() {


        srefresh_layout.setOnRefreshListener(this);

    }


    @Override
    protected void onLazyLoad() {


        if (isVisitor()) {

            wp_cirlce.setVisibility(View.GONE);

        } else {
            doShowOrHideCircle();

            presenterDriverFgSameCity.doGetDailyInCome(HttpConst.URL.TODAY_STATISTICS);
        }


    }

    @Override
    protected void onEventBack(EventEntity eventEntity) {

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (!hidden) {

            doShowOrHideCircle();

        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("fragment", TAG + " onCreate==============================");
    }

    @Override
    protected PresenterDriverFgSameCity creatPresenter() {


        if (null == presenterDriverFgSameCity) {

            presenterDriverFgSameCity = new PresenterDriverFgSameCity(this);
        }

        return presenterDriverFgSameCity;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.i("fragment", TAG + " ***************onDestroy********************************");
    }


    private void testLoadMore() {
        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // 千万别忘了告诉控件加载完毕了哦！


                srefresh_layout.setRefreshing(false);
            }
        }.sendEmptyMessageDelayed(0, 2000);

    }


    @Override
    public void onRefresh() {


        if (isVisitor()) {


            ToastUtil.showMsg(mActivity.getApplicationContext(), "登陆后查看当日收益");

            srefresh_layout.setRefreshing(false);
        } else {

            presenterDriverFgSameCity.doGetDailyInComeInFresh(HttpConst.URL.TODAY_STATISTICS);

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
        ToastUtil.showMsg(mActivity.getApplicationContext(), errorMsg);
    }

    @Override
    public void dismissFreshLoading() {
        srefresh_layout.setRefreshing(false);
    }


    @Override
    public void onDataBackSuccessForDailyInCome(TodayIncome todayIncome) {

        tv_order_recive_count.setText(String.valueOf(todayIncome.getOrder_count()));

//        new DecimalFormat(ConstSign.DECIMAL_2_MONEY).format()
        tv_order_recive_income.setText(new DecimalFormat(ConstSign.DECIMAL_2_MONEY).format(todayIncome.getIncome()));


    }

    @Override
    public void onDataBackSuccessForDailyInComeInFresh(TodayIncome todayIncome) {
        tv_order_recive_count.setText(String.valueOf(todayIncome.getOrder_count()));
        tv_order_recive_income.setText(new DecimalFormat(ConstSign.DECIMAL_2_MONEY).format(todayIncome.getIncome()));
    }


    @Override
    public void onClick(View v) {

    }
}
