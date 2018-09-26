package com.adhdriver.work.ui.activity.driver;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstAliYun;
import com.adhdriver.work.constant.ConstConfig;
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.constant.ConstSign;
import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.presenter.driver.PresenterDriverLoginPassChange;
import com.adhdriver.work.service.ServiceWatch1;
import com.adhdriver.work.service.ServiceWatch2;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.iview.driver.IChangeLoginPassView;
import com.adhdriver.work.utils.SpUtil;
import com.adhdriver.work.utils.ToastUtil;
import com.adhdriver.work.utils.VersionUtil;

public class ChangeLoginPassActivity extends BaseActivity<IChangeLoginPassView,PresenterDriverLoginPassChange> implements
        IChangeLoginPassView,
        View.OnClickListener{



    public static String TAG = ChangeLoginPassActivity.class.getSimpleName(); //获得类名称


    private PresenterDriverLoginPassChange presenterDriverLoginPassChange;



    /**
     * title
     */

    private ImageView iv_common_back;
    private TextView tv_common_title;
    private ImageView iv_common_search;


    private EditText et_old_pass;
    private EditText et_new_pass;
    private EditText et_new_pass_again;
    private LinearLayout ll_change_finish;



    /**
     * 停止2个service
     */
    private void stopThe2Service() {

        this.stopService(getTheIntent(this, ServiceWatch1.class));
        this.stopService(getTheIntent(this, ServiceWatch2.class));
    }


    private Intent getTheIntent(Context context, Class<?> targetClass) {

        Intent intent = new Intent(context, targetClass);

        return intent;

    }

    @Override
    protected PresenterDriverLoginPassChange creatPresenter() {
        if(null == presenterDriverLoginPassChange) {

            presenterDriverLoginPassChange = new PresenterDriverLoginPassChange(this);
        }
        return presenterDriverLoginPassChange;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_login_pass);
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
        tv_common_title.setText(this.getString(R.string.change_the_login_pass));




        et_old_pass = findADHViewById(R.id.et_old_pass);
        et_new_pass = findADHViewById(R.id.et_new_pass);
        et_new_pass_again = findADHViewById(R.id.et_new_pass_again);
        ll_change_finish = findADHViewById(R.id.ll_change_finish);

    }

    @Override
    protected void initListener() {


        iv_common_back.setOnClickListener(this);

        ll_change_finish.setOnClickListener(this);

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

    }

    @Override
    public void doVertifyErrorNullOldPass() {

        ToastUtil.showMsg(getApplicationContext(),this.getString(R.string.enter_old_pass));
    }

    @Override
    public void doVertifyErrorNullNewPass() {

        ToastUtil.showMsg(getApplicationContext(), this.getString(R.string.enter_new_pass));

    }

    @Override
    public void doVertifyErrorUnLegalPass() {
        ToastUtil.showMsg(this.getApplicationContext(), this.getString(R.string.passWord_type));
    }

    @Override
    public void doVertifyErrorNullNewPassAgain() {
        ToastUtil.showMsg(getApplicationContext(),this.getString(R.string.enter_new_pass_again));
    }

    @Override
    public void doVertifyErrorNewPassNotSameToNewPassAgain() {
        ToastUtil.showMsg(this.getApplicationContext(), this.getString(R.string.passWord_not_same));
    }



    @Override
    public void onDataBackSuccessForChangeLoginPass() {



        presenterDriverLoginPassChange.doLogout(
                HttpConst.URL.UPDARE_DEVICE_TO_PUSH,
                ConstConfig.OS_TYPE,
                VersionUtil.getPhoneSystemVersion(),
                VersionUtil.getPhoneBrand() + ConstSign.UNDER_LINE + VersionUtil.getPhoneType(),
                VersionUtil.getTheIMEI(),
                ConstAliYun.ALIYUN,
                SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_ALIYUN_PUSH_DEVICE_TOKEN, ConstSp.SP_VALUE.DEFAULT_STRING),
                ConstLocalData.IS_QUIET);

    }

    @Override
    public void onDataBackSuccessForClosePush() {

        presenterDriverLoginPassChange.doInitVisitorLogon(HttpConst.URL.VISITORS_LOGON);

    }

    @Override
    public void onDataBackSuccessForGetVisitorToken() {


        presenterDriverLoginPassChange.doClearAll();

        stopThe2Service();  //mvp后续完成

        removeAllFromActiviiesStack(TAG);

        openActivity(MainDriverActivity.class,null);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_common_back:
                dofinishItself();
                break;

            case R.id.ll_change_finish:


                String oldPass = et_old_pass.getText().toString().trim();
                String newPass = et_new_pass.getText().toString().trim();
                String newPassAgain = et_new_pass_again.getText().toString().trim();


                presenterDriverLoginPassChange.doChangeLoginPass(HttpConst.URL.CHANGE_PASSWORD,oldPass,newPass,newPassAgain);

                break;

        }
    }
}
