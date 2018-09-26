package com.adhdriver.work.ui.activity.driver;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.constant.ConstTag;
import com.adhdriver.work.entity.driver.token.TokenInfo;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.presenter.driver.PresenterDriverRegPhonePass;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.iview.driver.IRegDriverPhonePassView;
import com.adhdriver.work.utils.CountDownTimerUtils;
import com.adhdriver.work.utils.ToastUtil;

public class RegPhonePassActivity extends BaseActivity<IRegDriverPhonePassView, PresenterDriverRegPhonePass> implements
        IRegDriverPhonePassView,
        View.OnClickListener {


    private PresenterDriverRegPhonePass presenterDriverRegPhonePass;


    /**
     * titile
     *
     * @param savedInstanceState
     */
    private ImageView iv_common_back;
    private TextView tv_common_title;
    private ImageView iv_common_search;


    /**
     * 中间部分
     *
     * @param savedInstanceState
     */
    private EditText et_phone_number; //输入手机号
    private EditText et_confirm_code;//输入验证码
    private EditText et_enter_reg_pass;//输入密码

    private TextView tv_get_code; //文字 验证码


    private RelativeLayout rl_enter_reg_pass; //输入密码界面，用于注册时候显示

    private LinearLayout ll_find_pass_go_next;//下一步


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_phone_pass);
    }


    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

    @Override
    protected PresenterDriverRegPhonePass creatPresenter() {
        if (null == presenterDriverRegPhonePass) {
            presenterDriverRegPhonePass = new PresenterDriverRegPhonePass(this);
        }
        return presenterDriverRegPhonePass;
    }

    @Override
    protected void initViews() {

        /**
         * titile
         * @param savedInstanceState
         */
        iv_common_back = findADHViewById(R.id.iv_common_back);
        tv_common_title = findADHViewById(R.id.tv_common_title);
        tv_common_title.setText(this.getString(R.string.register_driver));

        /**
         * 中间部分
         * @param savedInstanceState
         */
        et_phone_number = findADHViewById(R.id.et_phone_number); //输入手机号
        et_confirm_code = findADHViewById(R.id.et_confirm_code);//输入验证码
        et_enter_reg_pass = findADHViewById(R.id.et_enter_reg_pass);//输入密码


        tv_get_code = findADHViewById(R.id.tv_get_code); ////获取验证码


        rl_enter_reg_pass = findADHViewById(R.id.rl_enter_reg_pass); //输入密码界面

        ll_find_pass_go_next = findADHViewById(R.id.ll_find_pass_go_next);//下一步


    }

    @Override
    protected void initListener() {

        iv_common_back.setOnClickListener(this);
        tv_get_code.setOnClickListener(this);
        ll_find_pass_go_next.setOnClickListener(this);

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
    public void doVertifyErrorForNullPhone() {
        ToastUtil.showMsg(this.getApplicationContext(), this.getString(R.string.input_phone));
    }

    @Override
    public void doVertifyErrorForUnlegalPhone() {
        ToastUtil.showMsg(this.getApplicationContext(), this.getString(R.string.userName_legal));
    }

    @Override
    public void doVertifyErrorForNullPhoneCode() {
        ToastUtil.showMsg(this.getApplicationContext(), this.getString(R.string.input_confirmCode));
    }

    @Override
    public void doVertifyErrorForNullPass() {
        ToastUtil.showMsg(this.getApplicationContext(), this.getString(R.string.input_passWord));
    }

    @Override
    public void doVertifyErrorForUnlegalPass() {
        ToastUtil.showMsg(this.getApplicationContext(), this.getString(R.string.passWord_type));
    }

    @Override
    public void onDataBackSuccessForGetPhoneCode() {

        CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(60000, 1000, tv_get_code, (Activity) this);

        countDownTimerUtils.start();

    }

    @Override
    public void onDataBackSuccessForVeryifyPhoneCode(String phone, String pass_code, String pass) {
        presenterDriverRegPhonePass.doRegister(HttpConst.URL.REGISTER, phone, pass, pass_code, ConstLocalData.DEFAULT_REG_TEXT);
    }

    @Override
    public void onDataBackSuccessForReg(String phone, String pass) {
        presenterDriverRegPhonePass.doRegGetToken(HttpConst.URL.LOGIN, phone, pass);
    }

    @Override
    public void onDataBackSuccessForGetToken(TokenInfo tokenInfo) {


        String token = tokenInfo.getAccess_token();
        Bundle bundle = new Bundle();
        bundle.putString(ConstIntent.BundleKEY.DELIVERY_ACCESS_TOKEN, token);
        openActivityAndFinishItself(InfoRegDriverActivity.class, bundle);

    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.iv_common_back:

                dofinishItself();
                break;
            case R.id.tv_get_code:

                String phone = et_phone_number.getText().toString().trim();
                presenterDriverRegPhonePass.doGetPhoneCode(HttpConst.URL.GET_CONFIRMCODE, phone, ConstTag.ConfirmCodeTag.USER_NOT_EXISTED);

                break;

            case R.id.ll_find_pass_go_next:


                String confirmCode = et_confirm_code.getText().toString().trim();
                String thePhone = et_phone_number.getText().toString().trim();
                String thePass = et_enter_reg_pass.getText().toString().trim();
                presenterDriverRegPhonePass.doVertifyPhoneCode(HttpConst.URL.VERTIFY_CONFIRMCODE, thePhone, confirmCode,thePass);


                break;

        }

    }
}
