package com.adhdriver.work.ui.activity.driver;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.entity.driver.AppMessage;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.presenter.driver.PresenterDriverMessage;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.adapter.MessageAdapter;
import com.adhdriver.work.ui.iview.driver.IMessageView;
import com.adhdriver.work.utils.ToastUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;


/**
 * 我的消息列表
 */
public class MessageActivity extends BaseActivity<IMessageView, PresenterDriverMessage> implements IMessageView,
        OnClickListener,
        SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener,
        BaseQuickAdapter.OnItemClickListener {


    private PresenterDriverMessage presenterDriverMessage;

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
    private RecyclerView rv_messages;
    private RecyclerView.LayoutManager layoutManager;
    private MessageAdapter messageAdapter;


    /**
     * 数据相关
     */
    private int currentSize = ConstLocalData.DEFAULT_INCREMENT_SIZE;//当前显示的数量 用于下拉差量更新    默认6 //加载刷新
    private int currentIndex = ConstLocalData.DEFUALT_LIST_INDEX;//用于上拉加载更多；       默认1       //加载刷新


    @Override
    protected PresenterDriverMessage creatPresenter() {
        if (null == presenterDriverMessage) {
            presenterDriverMessage = new PresenterDriverMessage(this);
        }
        return presenterDriverMessage;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);


        presenterDriverMessage.doGetMessages(
                HttpConst.URL.APP_MESSAGES,
                currentSize,
                ConstLocalData.DEFUALT_LIST_INDEX);

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
        iv_common_search.setVisibility(View.GONE);
        tv_common_title.setText(this.getString(R.string.msg_list));


        /**
         * 刷新控件相关
         */

        srefresh_layout = findADHViewById(R.id.srefresh_layout);
        rv_messages = findADHViewById(R.id.rv_messages);

//        rv_messages.addItemDecoration(new ADHDividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        srefresh_layout.setColorSchemeColors(getSwipeRefreshColor());

        layoutManager = new LinearLayoutManager(this);
        messageAdapter = new MessageAdapter(R.layout.item_message);

        rv_messages.setLayoutManager(layoutManager);
        rv_messages.setAdapter(messageAdapter);


    }

    @Override
    protected void initListener() {


        iv_common_back.setOnClickListener(this);
        srefresh_layout.setOnRefreshListener(this);
        messageAdapter.setOnLoadMoreListener(this, rv_messages);
        messageAdapter.setOnItemClickListener(this);

        //默认第一次加载会进入回调，如果不需要可以配置

        messageAdapter.disableLoadMoreIfNotFullPage(rv_messages);
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
    public void onDataBackFailInFresh(int code, String errorMsg) {

        srefresh_layout.setRefreshing(false);
        ToastUtil.showMsg(getApplicationContext(), errorMsg);
    }

    @Override
    public void onDataBackFailInLoadMore(int code, String errorMsg) {
        messageAdapter.loadMoreFail();
        ToastUtil.showMsg(getApplicationContext(), errorMsg);
    }

    @Override
    public void onDataBackSuccessForMessages(List<AppMessage> list) {

        messageAdapter.setNewData(list);


    }

    @Override
    public void onDataBackSuccessForMessagesInFresh(List<AppMessage> list) {
        srefresh_layout.setRefreshing(false);
        messageAdapter.setNewData(list);
    }

    @Override
    public void onDataBackSuccessForMessagesInLoadMore(List<AppMessage> list) {



        if (null != list && list.size() > 0) {

            messageAdapter.addData(list);
            messageAdapter.loadMoreComplete();
            currentSize += ConstLocalData.DEFAULT_INCREMENT_SIZE;   //这是设置给 下拉刷新用的//加载刷新
            currentIndex += ConstLocalData.DEFUALT_LIST_INDEX;

        } else {

            messageAdapter.loadMoreEnd();
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


        presenterDriverMessage.doGetMessagesInFresh(
                HttpConst.URL.APP_MESSAGES,
                currentSize,
                ConstLocalData.DEFUALT_LIST_INDEX);

    }

    @Override
    public void onLoadMoreRequested() {




        int tempIndex = currentIndex + ConstLocalData.DEFUALT_LIST_INDEX;

        presenterDriverMessage.doGetMessagesInLoadMore(HttpConst.URL.APP_MESSAGES,
                ConstLocalData.DEFAULT_INCREMENT_SIZE,
                tempIndex);

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

       AppMessage appMessage = messageAdapter.getData().get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstIntent.BundleKEY.MSG_DETAIL,appMessage);
        openActivity(MessageDetailActivity.class,bundle);

    }
}
