package com.adhdriver.work.ui.activity.driver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.entity.driver.study.Learning;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.presenter.driver.PresenterDriverReadLearn;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.adapter.forlistview.LearnAdapter;
import com.adhdriver.work.ui.iview.driver.IReadLearnView;
import com.adhdriver.work.utils.ToastUtil;

import java.util.List;

public class ReadLearnActivity extends BaseActivity<IReadLearnView, PresenterDriverReadLearn> implements IReadLearnView
        , View.OnClickListener {


    private PresenterDriverReadLearn presenterDriverReadLearn;


    /**
     * titile
     *
     * @param savedInstanceState
     */
    private ImageView iv_common_back;
    private TextView tv_common_title;
    private ImageView iv_common_search;


    /**
     * 底部按钮
     */

    private LinearLayout ll_common_read_finish;


    /**
     * listView相关
     */

    private ListView lv_learns;
    private LearnAdapter learnAdapter;


    /**
     * 数据相关
     *
     * @param savedInstanceState
     */
    private List<Learning> learningList;
    private String currentRegToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_learn);

        presenterDriverReadLearn.doGetLearningMaterials(HttpConst.URL.LEARNS,currentRegToken, ConstLocalData.LEARN_COUNT,1);
    }

    @Override
    protected PresenterDriverReadLearn creatPresenter() {

        if (null == presenterDriverReadLearn) {
            presenterDriverReadLearn = new PresenterDriverReadLearn(this);
        }
        return presenterDriverReadLearn;
    }

    @Override
    protected void initViews() {

        /**
         * titile
         * @param
         */
        iv_common_back = findADHViewById(R.id.iv_common_back);
        tv_common_title = findADHViewById(R.id.tv_common_title);
        iv_common_search = findADHViewById(R.id.iv_common_search);
        iv_common_search.setVisibility(View.GONE);

        tv_common_title.setText(this.getString(R.string.read_learn));


        /**
         * 底部按钮
         *
         */

        ll_common_read_finish = findADHViewById(R.id.ll_common_read_finish);


        /**
         * listView 相关
         */

        lv_learns = findADHViewById(R.id.lv_learns);
        learnAdapter = new LearnAdapter(this);
        lv_learns.setAdapter(learnAdapter);
    }

    @Override
    protected void initListener() {
        iv_common_back.setOnClickListener(this);

        /**
         * 底部按钮
         *
         */
        ll_common_read_finish.setOnClickListener(this);
    }

    @Override
    protected void processIntent() {

        Intent intent = this.getIntent();

        if (null != intent) {


            Bundle bundle = intent.getExtras();

            if (null != bundle) {


                currentRegToken = bundle.getString(ConstIntent.BundleKEY.DELIVERY_ACCESS_TOKEN, ConstIntent.BundleValue.DEFAULT_STRING);
            }
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
    public void onDataBackSuccessForLearningInfos(List<Learning> learningList) {


        learnAdapter.setList(learningList);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.iv_common_back:

                dofinishItself();
                break;

            case R.id.ll_common_read_finish:

                Bundle bundle = new Bundle();
                bundle.putString(ConstIntent.BundleKEY.DELIVERY_ACCESS_TOKEN, currentRegToken);
                openActivity(ExamActivity.class, bundle);
                break;
        }
    }
}
