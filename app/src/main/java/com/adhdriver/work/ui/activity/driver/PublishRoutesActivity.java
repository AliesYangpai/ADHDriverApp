package com.adhdriver.work.ui.activity.driver;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.PublishDialogCancelClickCallBack;
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.entity.driver.temp.PublishRoute;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.presenter.driver.PresenterDriverPublishRoutes;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.adapter.PublishRoutesAdapter;
import com.adhdriver.work.ui.iview.driver.IPublishRoutesView;
import com.adhdriver.work.utils.ToastUtil;
import com.adhdriver.work.ui.widget.dialog.PublishRouteCancelDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

public class PublishRoutesActivity extends BaseActivity<IPublishRoutesView, PresenterDriverPublishRoutes> implements
        View.OnClickListener,
        IPublishRoutesView,
        SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.OnItemChildClickListener ,
        BaseQuickAdapter.RequestLoadMoreListener,
        PublishDialogCancelClickCallBack {


    private PresenterDriverPublishRoutes presenterDriverPublishRoutes;


    /**
     * title
     */
    private ImageView iv_common_back;
    private TextView tv_common_title;
    private ImageView iv_common_search;

    /**
     * 刷新控件相关
     */

    private SwipeRefreshLayout srefresh_layout;
    private RecyclerView rv_publish_routes;
    private RecyclerView.LayoutManager layoutManager;
    private PublishRoutesAdapter publishRoutesAdapter;

    /**
     * 数据相关
     *
     * @return
     */


    private int currentSize = ConstLocalData.DEFAULT_INCREMENT_SIZE;//当前显示的数量 用于下拉差量更新    默认6 //加载刷新
    private int currentIndex = ConstLocalData.DEFUALT_LIST_INDEX;//用于上拉加载更多；       默认1       //加载刷新


    /**
     * dialog相关
     * @param savedInstanceState
     */

    private PublishRouteCancelDialog publishRouteCancelDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_routes);


        presenterDriverPublishRoutes.doGetPublishRoutes(
                HttpConst.URL.PUBLISH_HITCHHIKES,
                currentSize,
                ConstLocalData.DEFUALT_LIST_INDEX);


    }

    @Override
    protected PresenterDriverPublishRoutes creatPresenter() {
        if (null == presenterDriverPublishRoutes) {
            presenterDriverPublishRoutes = new PresenterDriverPublishRoutes(this);
        }
        return presenterDriverPublishRoutes;
    }

    @Override
    protected void initViews() {

        /**
         * titile
         *
         */
        iv_common_back = findADHViewById(R.id.iv_common_back);
        tv_common_title = findADHViewById(R.id.tv_common_title);
        iv_common_search = findADHViewById(R.id.iv_common_search);
        tv_common_title.setText(this.getString(R.string.ride_sharing_publish_list));
        iv_common_search.setVisibility(View.GONE);


        srefresh_layout = findADHViewById(R.id.srefresh_layout);
        srefresh_layout.setColorSchemeColors(getSwipeRefreshColor());
        rv_publish_routes = findADHViewById(R.id.rv_publish_routes);
        layoutManager = new LinearLayoutManager(this);
        publishRoutesAdapter = new PublishRoutesAdapter(R.layout.item_publish_routes);
        rv_publish_routes.setLayoutManager(layoutManager);
        rv_publish_routes.setAdapter(publishRoutesAdapter);
    }

    @Override
    protected void initListener() {
        iv_common_back.setOnClickListener(this);


        /**
         * 刷新配置
         */


        srefresh_layout.setOnRefreshListener(this);
        publishRoutesAdapter.setOnLoadMoreListener(this, rv_publish_routes);
        publishRoutesAdapter.setOnItemChildClickListener(this);

        //默认第一次加载会进入回调，如果不需要可以配置
        publishRoutesAdapter.disableLoadMoreIfNotFullPage(rv_publish_routes);

    }

    @Override
    protected void processIntent() {

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
    public void onDataBackFailInFresh(int code, String errorMsg) {

        srefresh_layout.setRefreshing(false);
        ToastUtil.showMsg(getApplicationContext(), errorMsg);
    }

    @Override
    public void onDataBackFailInLoadMore(int code, String errorMsg) {

        publishRoutesAdapter.loadMoreFail();
        ToastUtil.showMsg(getApplicationContext(),errorMsg);
    }

    @Override
    public void onDataBackSuccessForPublishRoutes(List<PublishRoute> list) {
        publishRoutesAdapter.setNewData(list);

    }

    @Override
    public void onDataBackSuccessForPublishRoutesInFresh(List<PublishRoute> list) {

        srefresh_layout.setRefreshing(false);
        publishRoutesAdapter.setNewData(list);

    }

    @Override
    public void onDataBackSuccessForPublishRoutesInLoadMore(List<PublishRoute> list) {

        if (null != list && list.size() > 0) {

            publishRoutesAdapter.addData(list);
            publishRoutesAdapter.loadMoreComplete();
            currentSize += ConstLocalData.DEFAULT_INCREMENT_SIZE;   //这是设置给 下拉刷新用的//加载刷新
            currentIndex += ConstLocalData.DEFUALT_LIST_INDEX;

        } else {

            publishRoutesAdapter.loadMoreEnd();
        }

    }

    @Override
    public void onDataBackSuccessForCancelThisRoute() {

        presenterDriverPublishRoutes.doGetPublishRoutes(
                HttpConst.URL.PUBLISH_HITCHHIKES,
                currentSize,
                ConstLocalData.DEFUALT_LIST_INDEX);
    }

    @Override
    public void doShowCancelThisRouteAlertDialog(String hitchhike_on) {


        if (publishRouteCancelDialog == null) {

            publishRouteCancelDialog = new PublishRouteCancelDialog(this);
            publishRouteCancelDialog.setHitchhike_on(hitchhike_on);
            publishRouteCancelDialog.setPublishDialogCancelClickCallBack(this);
            publishRouteCancelDialog.setMessage(this.getString(R.string.cancel_this_hitchhik));
            publishRouteCancelDialog.setCancelable(false);
            publishRouteCancelDialog.show();

        } else {

            publishRouteCancelDialog.show();

        }
    }




    @Override
    public void onRefresh() {


        presenterDriverPublishRoutes.doGetPublishRoutesInFresh(
                HttpConst.URL.PUBLISH_HITCHHIKES,
                currentSize,
                ConstLocalData.DEFUALT_LIST_INDEX);


    }


    @Override
    public void onLoadMoreRequested() {

        int tempIndex = currentIndex + ConstLocalData.DEFUALT_LIST_INDEX;
        presenterDriverPublishRoutes.doGetPublishRoutesInLoadMore(
                HttpConst.URL.PUBLISH_HITCHHIKES,
                ConstLocalData.DEFAULT_INCREMENT_SIZE,
                tempIndex);









        Log.i("onLoadMore", "=============ChildFragmentPartLoad=onLoadMoreRequested===当前index："+tempIndex);

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

        presenterDriverPublishRoutes.doShowCancelRouteDialog(adapter.getData(),position);

    }

    @Override
    public void publishDialogCancel() {

    }

    @Override
    public void publishDialogSure(String hitchhike_on) {

        presenterDriverPublishRoutes.doCancelThisRoute(HttpConst.URL.CANCEL_HITCHHIKE+HttpConst.SEPARATOR+hitchhike_on);
    }
}
