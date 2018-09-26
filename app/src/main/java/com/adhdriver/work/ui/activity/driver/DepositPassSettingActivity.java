package com.adhdriver.work.ui.activity.driver;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.presenter.driver.PresenterDriverSettingDepositPass;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.iview.driver.ISettingDepositPassView;
import com.adhdriver.work.utils.ToastUtil;
import com.jungly.gridpasswordview.GridPasswordView;

public class DepositPassSettingActivity extends BaseActivity<ISettingDepositPassView, PresenterDriverSettingDepositPass> implements
        View.OnClickListener,
        ISettingDepositPassView {


    private PresenterDriverSettingDepositPass presenterDriverSettingDepositPass;

    /**
     * title
     */

    private ImageView iv_common_back;
    private TextView tv_common_title;


    /**
     * 中间
     */


    private LinearLayout ll_set_withdraw_pass;
    private LinearLayout ll_set_withdraw_pass_again;


    private TextView tv_set_first;
    private TextView tv_set_second;

    private GridPasswordView gpv_set_withdraw_pass;
    private GridPasswordView gpv_set_withdraw_pass_again;
    private TextView tv_withdraw_sure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit_pass_setting);
    }

    @Override
    protected PresenterDriverSettingDepositPass creatPresenter() {
        if (null == presenterDriverSettingDepositPass) {
            presenterDriverSettingDepositPass = new PresenterDriverSettingDepositPass(this);
        }
        return presenterDriverSettingDepositPass;
    }

    @Override
    protected void initViews() {


        /**
         * titile
         *
         */
        iv_common_back = findADHViewById(R.id.iv_common_back);
        tv_common_title = findADHViewById(R.id.tv_common_title);


        tv_common_title.setText(this.getString(R.string.set_withdraww_pass));
        /**
         * 中间
         *
         */
        ll_set_withdraw_pass = findADHViewById(R.id.ll_set_withdraw_pass);
        ll_set_withdraw_pass_again = findADHViewById(R.id.ll_set_withdraw_pass_again);
        tv_set_first = findADHViewById(R.id.tv_set_first);
        tv_set_second = findADHViewById(R.id.tv_set_second);

        gpv_set_withdraw_pass = findADHViewById(R.id.gpv_set_withdraw_pass);
        gpv_set_withdraw_pass_again = findADHViewById(R.id.gpv_set_withdraw_pass_again);
        tv_withdraw_sure = findADHViewById(R.id.tv_withdraw_sure);


    }

    @Override
    protected void initListener() {


        iv_common_back.setOnClickListener(this);

        /**
         * 中间
         *
         */


        tv_withdraw_sure.setOnClickListener(this);
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

            case R.id.tv_withdraw_sure:


                String pass = gpv_set_withdraw_pass.getPassWord().toString().trim();
                String passAgain = gpv_set_withdraw_pass_again.getPassWord().toString().trim();
                presenterDriverSettingDepositPass.doSetDepositPass(HttpConst.URL.SET_PAY_PASS, pass, passAgain);

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
    public void doVertifyErrorForPassNull() {


        ToastUtil.showMsg(getApplicationContext(), R.string.enter_withdraw_pass_please);
    }

    @Override
    public void doVertifyErrorForPassLessThan6() {
        ToastUtil.showMsg(getApplicationContext(), R.string.withdraw_pass_6_lenth);

    }

    @Override
    public void doVertifyErrorForPassAgainNull() {
        ToastUtil.showMsg(getApplicationContext(), R.string.enter_again);
    }

    @Override
    public void doVertifyErrorForPassNotSame() {
        ToastUtil.showMsg(getApplicationContext(), R.string.not_same_pass);
    }

    @Override
    public void onDataBackSuccessForSettingDepositPass() {

        this.setResult(ConstIntent.ResponseCode.SET_PAY_PASS);
        this.finish();

    }
}
