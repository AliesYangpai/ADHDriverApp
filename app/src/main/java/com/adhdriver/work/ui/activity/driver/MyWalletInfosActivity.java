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
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.entity.driver.wallet.FundsDetial;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.presenter.driver.PresenterDriverWalletInfos;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.adapter.WalletInfoAdapter;
import com.adhdriver.work.ui.iview.driver.IWalletInfosView;
import com.adhdriver.work.utils.ToastUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

/**
 * 零钱明细列表
 */

public class MyWalletInfosActivity extends BaseActivity<IWalletInfosView,PresenterDriverWalletInfos> implements
        IWalletInfosView,
        View.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener{



    private PresenterDriverWalletInfos presenterDriverWalletInfos;


    /**
     * title
     * @param savedInstanceState
     */

    private ImageView iv_common_back;
    private TextView tv_common_title;




    private SwipeRefreshLayout srefresh_layout;
    private RecyclerView rv_wallet_info;
    private RecyclerView.LayoutManager layoutManager;
    private WalletInfoAdapter walletInfoAdapter;



    /**
     * 数据相关
     */
    private int currentSize = ConstLocalData.DEFAULT_INCREMENT_SIZE;//当前显示的数量 用于下拉差量更新    默认6 //加载刷新
    private int currentIndex = ConstLocalData.DEFUALT_LIST_INDEX;//用于上拉加载更多；       默认1       //加载刷新

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet_infos);



        presenterDriverWalletInfos.doGetWalletInfos(
                HttpConst.URL.WALLETE_DETAILS_LIST,
                currentSize,
                ConstLocalData.DEFUALT_LIST_INDEX);

//        walletInfoAdapter.addData(TestContent.getFundsDetail());
    }

    @Override
    protected PresenterDriverWalletInfos creatPresenter() {
        if(null == presenterDriverWalletInfos) {
            presenterDriverWalletInfos = new PresenterDriverWalletInfos(this);
        }
        return presenterDriverWalletInfos;
    }

    @Override
    protected void initViews() {


        /**
         * title
         * @param savedInstanceState
         */
        iv_common_back = findADHViewById(R.id.iv_common_back);
        tv_common_title = findADHViewById(R.id.tv_common_title);
        tv_common_title.setText(getString(R.string.change_detial));



        srefresh_layout = findADHViewById(R.id.srefresh_layout);
        srefresh_layout.setColorSchemeColors(getSwipeRefreshColor());

        rv_wallet_info = findADHViewById(R.id.rv_wallet_info);
        layoutManager = new LinearLayoutManager(this);


        walletInfoAdapter = new WalletInfoAdapter(R.layout.item_wallet_info);
        rv_wallet_info.setLayoutManager(layoutManager);
        rv_wallet_info.setAdapter(walletInfoAdapter);
    }

    @Override
    protected void initListener() {


        iv_common_back.setOnClickListener(this);


        srefresh_layout.setOnRefreshListener(this);
        walletInfoAdapter.setOnLoadMoreListener(this, rv_wallet_info);


        //默认第一次加载会进入回调，如果不需要可以配置
        walletInfoAdapter.disableLoadMoreIfNotFullPage(rv_wallet_info);

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
        ToastUtil.showMsg(getApplicationContext(),errorMsg);
    }

    @Override
    public void onDataBackFailInLoadMore(int code, String errorMsg) {
        walletInfoAdapter.loadMoreFail();
        ToastUtil.showMsg(getApplicationContext(), errorMsg);
    }

    @Override
    public void dismissFreshLoading() {

        srefresh_layout.setRefreshing(false);

    }

    @Override
    public void onDataBackSuccessForWalletInfos(List<FundsDetial> list) {


        walletInfoAdapter.setNewData(list);
    }

    @Override
    public void onDataBackSuccessForWalletInfosInFresh(List<FundsDetial> list) {

        walletInfoAdapter.setNewData(list);
    }

    @Override
    public void onDataBackSuccessForWalletInfosInLoadMore(List<FundsDetial> list) {

        if (null != list && list.size() > 0) {

            walletInfoAdapter.addData(list);
            walletInfoAdapter.loadMoreComplete();
            currentSize += ConstLocalData.DEFAULT_INCREMENT_SIZE;   //这是设置给 下拉刷新用的//加载刷新
            currentIndex += ConstLocalData.DEFUALT_LIST_INDEX;

        } else {
            walletInfoAdapter.loadMoreEnd();
        }

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


        presenterDriverWalletInfos.doGetWalletInfosInFresh(
                HttpConst.URL.WALLETE_DETAILS_LIST,
                currentSize,
                ConstLocalData.DEFUALT_LIST_INDEX);


    }

    @Override
    public void onLoadMoreRequested() {



        int tempIndex = currentIndex + ConstLocalData.DEFUALT_LIST_INDEX;
        presenterDriverWalletInfos.doGetWalletInfosInLoadMore(HttpConst.URL.WALLETE_DETAILS_LIST,
                ConstLocalData.DEFAULT_INCREMENT_SIZE,
                tempIndex);


        Log.i("onLoadMore", "=============ChildFragmentFullLoad=onLoadMoreRequested");
    }
}
