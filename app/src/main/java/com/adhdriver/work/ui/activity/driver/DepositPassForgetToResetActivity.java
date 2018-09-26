package com.adhdriver.work.ui.activity.driver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.DepositPassResetSuccessDialogClickCallBack;
import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.presenter.driver.PresenterDriverDepositPassForgetToReset;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.iview.driver.IDepositPassForgetToResetView;
import com.adhdriver.work.utils.ToastUtil;
import com.adhdriver.work.ui.widget.dialog.DepositPassResetSuccessDialog;
import com.jungly.gridpasswordview.GridPasswordView;


/**
 * 重置提现密码
 */
public class DepositPassForgetToResetActivity extends BaseActivity<IDepositPassForgetToResetView, PresenterDriverDepositPassForgetToReset> implements
        IDepositPassForgetToResetView,
        View.OnClickListener,
        DepositPassResetSuccessDialogClickCallBack {


    private PresenterDriverDepositPassForgetToReset presenterDriverDepositPassForgetToReset;


    /**
     * titile
     */
    private ImageView iv_common_back;
    private TextView tv_common_title;
    private ImageView iv_common_search;


    /**
     * 中间
     *
     * @param savedInstanceState
     */


    private GridPasswordView gpv_set_identify_id;
    private GridPasswordView gpv_forget_to_set_withdraw_pass;
    private GridPasswordView gpv_forget_to_set_withdraw_pass_again;

    /**
     * 底部
     *
     * @param savedInstanceState
     */
    private TextView tv_forget_to_set_withdraw_pass_finish;


    /**
     * dialog相关
     *
     * @param savedInstanceState
     */

    private DepositPassResetSuccessDialog depositPassResetSuccessDialog;


    /**
     * 数据相关
     */
    private String currentConfirmCode;


    @Override
    protected PresenterDriverDepositPassForgetToReset creatPresenter() {
        if (null == presenterDriverDepositPassForgetToReset) {
            presenterDriverDepositPassForgetToReset = new PresenterDriverDepositPassForgetToReset(this);
        }
        return presenterDriverDepositPassForgetToReset;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit_pass_forget_to_reset);
    }


    @Override
    protected void initViews() {

        /**
         * title
         */
        iv_common_back = findADHViewById(R.id.iv_common_back);
        tv_common_title = findADHViewById(R.id.tv_common_title);
        iv_common_search = findADHViewById(R.id.iv_common_search);
        iv_common_search.setVisibility(View.GONE);
        tv_common_title.setText(this.getString(R.string.reset_deposit_pass));


        /**
         * 中间
         *
         * @param savedInstanceState
         */


        gpv_set_identify_id = findADHViewById(R.id.gpv_set_identify_id);
        gpv_set_identify_id.setPasswordVisibility(true);
        gpv_forget_to_set_withdraw_pass = findADHViewById(R.id.gpv_forget_to_set_withdraw_pass);
        gpv_forget_to_set_withdraw_pass_again = findADHViewById(R.id.gpv_forget_to_set_withdraw_pass_again);

        /**
         * 底部
         *
         * @param savedInstanceState
         */
        tv_forget_to_set_withdraw_pass_finish = findADHViewById(R.id.tv_forget_to_set_withdraw_pass_finish);

    }

    @Override
    protected void initListener() {

        iv_common_back.setOnClickListener(this);


        /**
         * 底部
         *
         * @param savedInstanceState
         */
        tv_forget_to_set_withdraw_pass_finish.setOnClickListener(this);


    }

    @Override
    protected void processIntent() {


        Intent intent = this.getIntent();


        if (null != intent) {

            Bundle extras = intent.getExtras();

            if (null != extras) {

                currentConfirmCode = extras.getString(ConstIntent.BundleKEY.DEPOSITE_PASS_CONFIRM_CODE, ConstIntent.BundleValue.DEFAULT_STRING);

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
    public void onDataBackSuccessForResetDepositPass() {
        if (depositPassResetSuccessDialog == null) {
            depositPassResetSuccessDialog = new DepositPassResetSuccessDialog(this, this.getString(R.string.depositpass_success_reset), this.getString(R.string.i_know));

            depositPassResetSuccessDialog.setDepositPassResetSuccessDialogClickCallBack(this);
            depositPassResetSuccessDialog.setCancelable(false);

        }
        depositPassResetSuccessDialog.show();
    }

    @Override
    public void doVertifyErrorNullIDLast4() {

        ToastUtil.showMsg(getApplicationContext(), R.string.enter_identify);

    }

    @Override
    public void doVertifyErrorIDCompleteLast4() {


        ToastUtil.showMsg(getApplicationContext(), R.string.enter_finish_identify);
    }

    @Override
    public void doVertifyErrorNullDepositPass() {

        ToastUtil.showMsg(getApplicationContext(), R.string.enter_withdraw_pass_please);

    }

    @Override
    public void doVertifyErrorUnLegalDepositPass() {


        ToastUtil.showMsg(getApplicationContext(), R.string.withdraw_pass_6_lenth);
    }

    @Override
    public void doVertifyErrorEnterDepositPassAgain() {

        ToastUtil.showMsg(getApplicationContext(), R.string.enter_again);

    }

    @Override
    public void doVertifyErrorNewPassNotSameToNewPassAgain() {

        ToastUtil.showMsg(getApplicationContext(), R.string.not_same_pass);

    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.iv_common_back:
                dofinishItself();
                break;


            case R.id.tv_forget_to_set_withdraw_pass_finish:


                String identify = gpv_set_identify_id.getPassWord();
                String pass = gpv_forget_to_set_withdraw_pass.getPassWord();
                String passAgain = gpv_forget_to_set_withdraw_pass_again.getPassWord();

                presenterDriverDepositPassForgetToReset.doResetDepositPass(HttpConst.URL.RESET_DEPOSITE_PASSWORD,
                        identify,
                        pass,
                        passAgain,
                        currentConfirmCode);

                break;
        }


    }

    @Override
    public void depositPassResetSuccessDialogClick() {


        dofinishItself();
    }
}
