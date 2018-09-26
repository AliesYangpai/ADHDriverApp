package com.adhdriver.work.ui.activity.driver;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.constant.ConstParams;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.presenter.driver.PresenterDriverDepositPassForgetToGetCode;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.iview.driver.IDepositPassForgetToGetCodeView;
import com.adhdriver.work.utils.CountDownTimerUtils;
import com.adhdriver.work.utils.StringUtil;
import com.adhdriver.work.utils.ToastUtil;
import com.jungly.gridpasswordview.GridPasswordView;

import org.feezu.liuli.timeselector.Utils.TextUtil;


/**
 * 忘记提现密码的获取验证码activity
 */
public class DepositPassForgetToGetCodeActivity extends BaseActivity<IDepositPassForgetToGetCodeView, PresenterDriverDepositPassForgetToGetCode> implements
        IDepositPassForgetToGetCodeView,
        View.OnClickListener,
        GridPasswordView.OnPasswordChangedListener{


    private PresenterDriverDepositPassForgetToGetCode presenterDriverDepositPassForgetToGetCode;


    /**
     * titile
     */
    private ImageView iv_common_back;
    private TextView tv_common_title;
    private ImageView iv_common_search;
    private TextView tv_phone_in_forget_paypass;


    /**
     * 验证码相关
     *
     * @param savedInstanceState
     */


    private GridPasswordView gpv_set_withdraw_pass;
    private View pass_code_line1;
    private View pass_code_line2;
    private View pass_code_line3;
    private View pass_code_line4;
    private View pass_code_line5;
    private View pass_code_line6;
    private TextView tv_get_code;


    /**
     * 底部
     *
     * @param savedInstanceState
     */
    private TextView tv_sure_the_code;
    /**
     * 数据相关
     *
     * @return
     */

    private String userPhone;



    /**
     * 验证码数据变化
     */
    private void changeUiByGetRandomCode() {


        CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(60000, 1000, tv_get_code, (Activity) this);

        countDownTimerUtils.start();

    }

    private void changeBottomColorByStringLenth(String psw) {

        setInitColorForLine();

        if (!TextUtil.isEmpty(psw)) {

            int lenth = psw.length();

            switch (lenth) {

                case 1:

                    pass_code_line1.setBackgroundColor(Color.parseColor("#68bcff"));

                    break;

                case 2:

                    pass_code_line2.setBackgroundColor(Color.parseColor("#68bcff"));

                    break;
                case 3:

                    pass_code_line3.setBackgroundColor(Color.parseColor("#68bcff"));

                    break;
                case 4:

                    pass_code_line4.setBackgroundColor(Color.parseColor("#68bcff"));
                    break;
                case 5:
                    pass_code_line5.setBackgroundColor(Color.parseColor("#68bcff"));

                    break;
                case 6:
                    pass_code_line6.setBackgroundColor(Color.parseColor("#68bcff"));

                    break;

            }

        }


    }


    private void setInitColorForLine() {

        pass_code_line1.setBackgroundColor(Color.parseColor("#BFBFBF"));
        pass_code_line2.setBackgroundColor(Color.parseColor("#BFBFBF"));
        pass_code_line3.setBackgroundColor(Color.parseColor("#BFBFBF"));
        pass_code_line4.setBackgroundColor(Color.parseColor("#BFBFBF"));
        pass_code_line5.setBackgroundColor(Color.parseColor("#BFBFBF"));
        pass_code_line6.setBackgroundColor(Color.parseColor("#BFBFBF"));


    }

    @Override
    protected PresenterDriverDepositPassForgetToGetCode creatPresenter() {
        if (null == presenterDriverDepositPassForgetToGetCode) {

            presenterDriverDepositPassForgetToGetCode = new PresenterDriverDepositPassForgetToGetCode(this);
        }
        return presenterDriverDepositPassForgetToGetCode;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit_pass_forget_to_get_code);

        presenterDriverDepositPassForgetToGetCode.doGetUserPhoneFromDb();
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
        tv_common_title.setText(this.getString(R.string.forget_deposit_pass));

        tv_phone_in_forget_paypass = findADHViewById(R.id.tv_phone_in_forget_paypass);
        /**
         * 验证码相关
         * @param savedInstanceState
         */

        gpv_set_withdraw_pass = findADHViewById(R.id.gpv_set_withdraw_pass);
        gpv_set_withdraw_pass.setPasswordVisibility(true);

        pass_code_line1 = findADHViewById(R.id.pass_code_line1);
        pass_code_line2 = findADHViewById(R.id.pass_code_line2);
        pass_code_line3 = findADHViewById(R.id.pass_code_line3);
        pass_code_line4 = findADHViewById(R.id.pass_code_line4);
        pass_code_line5 = findADHViewById(R.id.pass_code_line5);
        pass_code_line6 = findADHViewById(R.id.pass_code_line6);
        tv_get_code = findADHViewById(R.id.tv_get_code);

        /**
         * 底部
         * @param savedInstanceState
         */
        tv_sure_the_code = findADHViewById(R.id.tv_sure_the_code);

    }

    @Override
    protected void initListener() {
        /**
         * titile
         */
        iv_common_back.setOnClickListener(this);

        /**
         * 验证码监听
         */

        gpv_set_withdraw_pass.setOnPasswordChangedListener(this);


        tv_get_code.setOnClickListener(this);

        /**
         * 底部
         * @param savedInstanceState
         */
        tv_sure_the_code.setOnClickListener(this);
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
    public void doGetUserPhoneToUi(String phone) {

        userPhone = phone;
        String dealPhone = StringUtil.encodePartOfPhone(phone);
        tv_phone_in_forget_paypass.setText(dealPhone);
        presenterDriverDepositPassForgetToGetCode.doGetDepositeVertifyCode(HttpConst.URL.GET_CONFIRMCODE, userPhone,ConstParams.ConfirmCode.USER_EXISTED);

    }

    @Override
    public void onDataBackSuccessForGetCode() {

    }

    @Override
    public void onDataBackSuccessForConfirmDepositVertifyCode() {

        String code = gpv_set_withdraw_pass.getPassWord().trim().toString();
        Bundle bundle = new Bundle();
        bundle.putString(ConstIntent.BundleKEY.DEPOSITE_PASS_CONFIRM_CODE, code);
        openActivityAndFinishItself(DepositPassForgetToResetActivity.class, bundle);

    }

    @Override
    public void doVertifyErrorNullVertifyCode() {

        ToastUtil.showMsg(getApplicationContext(),R.string.enter_confirm_code);
    }

    @Override
    public void doVertifyErrorUnCompletVertifyCode() {

        ToastUtil.showMsg(getApplicationContext(),R.string.enter_confirm_code_to_finish);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_common_back:
                this.finish();
                break;


            case R.id.tv_sure_the_code:


                String code = gpv_set_withdraw_pass.getPassWord().trim().toString();

                presenterDriverDepositPassForgetToGetCode.doConfirmVertifyCode(HttpConst.URL.VERTIFY_CONFIRMCODE,userPhone,code);

                break;


            case R.id.tv_get_code:

                changeUiByGetRandomCode();
                presenterDriverDepositPassForgetToGetCode.doGetDepositeVertifyCode(HttpConst.URL.GET_CONFIRMCODE, userPhone,ConstParams.ConfirmCode.USER_EXISTED);

                break;
        }
    }

    @Override
    public void onTextChanged(String psw) {

        changeBottomColorByStringLenth(psw);

    }

    @Override
    public void onInputFinish(String psw) {

    }
}
