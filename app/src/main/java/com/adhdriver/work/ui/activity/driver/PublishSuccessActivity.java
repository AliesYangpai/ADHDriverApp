package com.adhdriver.work.ui.activity.driver;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.presenter.driver.PresenterDriverPublishRouteSuccess;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.iview.driver.IPublishRouteSuccessView;
import com.adhdriver.work.utils.ToastUtil;

public class PublishSuccessActivity extends BaseActivity<IPublishRouteSuccessView,PresenterDriverPublishRouteSuccess>
        implements View.OnClickListener,
        IPublishRouteSuccessView{


    private PresenterDriverPublishRouteSuccess presenterDriverPublishRouteSuccess;





    /**
     * title
     */
    private ImageView iv_common_back;
    private TextView tv_common_title;
    private ImageView iv_common_search;


    /**
     * 底部
     *
     * @param savedInstanceState
     */
    private TextView tv_publish_success;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_success);
    }

    @Override
    protected PresenterDriverPublishRouteSuccess creatPresenter() {
        if(null == presenterDriverPublishRouteSuccess) {
            presenterDriverPublishRouteSuccess= new PresenterDriverPublishRouteSuccess(this);
        }
        return presenterDriverPublishRouteSuccess;
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
        tv_common_title.setText(this.getString(R.string.publish_success));


        /**
         * 底部
         * @param savedInstanceState
         */
        tv_publish_success = findADHViewById(R.id.tv_publish_success);
    }

    @Override
    protected void initListener() {


        iv_common_back.setOnClickListener(this);
        tv_publish_success.setOnClickListener(this);

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

            case R.id.tv_publish_success:

//                openActivityAndFinishItself(PulishListActivity.class,null);


                ToastUtil.showMsg(getApplicationContext(),"返回前往发布列表");

                break;

        }
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }
}
