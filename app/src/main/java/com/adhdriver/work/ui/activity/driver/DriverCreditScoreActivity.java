package com.adhdriver.work.ui.activity.driver;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.entity.User;
import com.adhdriver.work.entity.driver.credit.DriverCridet;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.presenter.driver.PresenterDriverCreditScore;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.iview.driver.ICreditScoreView;
import com.adhdriver.work.utils.ImgUtil;
import com.adhdriver.work.utils.ToastUtil;
import com.adhdriver.work.ui.widget.radaview.RadarChartView;
import com.adhdriver.work.ui.widget.rattingstar.StarRattingBar;

import java.util.HashMap;
import java.util.LinkedHashMap;


public class DriverCreditScoreActivity extends BaseActivity<ICreditScoreView, PresenterDriverCreditScore> implements
        ICreditScoreView,
        View.OnClickListener{


    private PresenterDriverCreditScore presenterDriverCreditScore;





    private ImageView iv_common_back;
    private TextView tv_common_title;

    private ImageView iv_userHead;
    private TextView tv_user_name;
    private StarRattingBar srb_synthesize_score;

    private RadarChartView radarView;
    private HashMap<String, Float> valueHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_credit_score);

        // set max value for the chart

        presenterDriverCreditScore.doGetUserInfoFromDb();

        presenterDriverCreditScore.doGetCreditScore(HttpConst.URL.DRIVER_CREDIT);
    }

    @Override
    protected PresenterDriverCreditScore creatPresenter() {
        if(null == presenterDriverCreditScore) {
            presenterDriverCreditScore= new PresenterDriverCreditScore(this);
        }
        return presenterDriverCreditScore;
    }

    @Override
    protected void initViews() {


          iv_common_back = findADHViewById(R.id.iv_common_back);
          tv_common_title = findADHViewById(R.id.tv_common_title);
        tv_common_title.setText(getString(R.string.grade));






        radarView = findADHViewById(R.id.radarView);
        radarView.setCircle(true); //是否圆圈
        radarView.setGridding(false); //网格描边
        radarView.setAxisNumb(4); //网格分支个数
        radarView.setAxisTick(6);//网格层数


        valueHash = new LinkedHashMap<>();
        valueHash.put("沟通", 0F);
        valueHash.put("行为", 0F);
        valueHash.put("履约", 0F);
        valueHash.put("服务", 0F);
        radarView.addData(valueHash);


          iv_userHead = findADHViewById(R.id.iv_userHead);
          tv_user_name = findADHViewById(R.id.tv_user_name);
          srb_synthesize_score = findADHViewById(R.id.srb_synthesize_score);

    }

    @Override
    protected void initListener() {

        iv_common_back.setOnClickListener(this);

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
    public void onDataBackSuccessDbForUser(User user) {
        tv_user_name.setText(user.getFullname());
        ImgUtil.getInstance().getRadiusImgFromNetByUrl(user.getAvatar(),iv_userHead,R.drawable.img_default_client_head_round,120);

    }

    @Override
    public void onDataBackSuccessForGetCreditScore(DriverCridet driverCridet) {
        srb_synthesize_score.setMark(driverCridet.getCredit_points());
    }

    @Override
    public void onDataBackSuccessForSetDataToRadaView(HashMap<String, Float> valueHash) {
        radarView.refreshView();
        radarView.addData(valueHash);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_common_back:
                dofinishItself();
                break;
        }
    }
}
