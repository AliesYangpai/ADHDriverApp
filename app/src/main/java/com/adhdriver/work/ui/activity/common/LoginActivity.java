package com.adhdriver.work.ui.activity.common;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.DialogTokenPickerClickCallBack;
import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.constant.ConstSign;
import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.entity.driver.token.TokenInfo;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.presenter.common.PresenterLogin;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.activity.driver.InRegDriverVehicleActivity;
import com.adhdriver.work.ui.activity.driver.InfoRegDriverActivity;
import com.adhdriver.work.ui.activity.driver.MainDriverActivity;
import com.adhdriver.work.ui.activity.driver.ReadLearnActivity;
import com.adhdriver.work.ui.activity.driver.RegPhonePassActivity;
import com.adhdriver.work.ui.iview.ILoginView;
import com.adhdriver.work.utils.AssetsUtil;
import com.adhdriver.work.utils.SpUtil;
import com.adhdriver.work.utils.ToastUtil;
import com.adhdriver.work.ui.widget.dialog.TokenPickerDialog;

import java.util.List;

public class LoginActivity extends BaseActivity<ILoginView, PresenterLogin> implements
        View.OnClickListener,
        ILoginView,
        DialogTokenPickerClickCallBack {

    private PresenterLogin presenterLogin;

    /**
     * title
     *
     * @param savedInstanceState
     */

    private ImageView iv_common_back;

    /**
     * 中间部分
     *
     * @param savedInstanceState
     */
    private EditText et_username;
    private EditText et_password;
    private ImageView iv_clear_account;
    private ImageView iv_clear_pass;


    /**
     * 登陆
     */
    private LinearLayout layout_login;


    private TextView tv_register_driver;//司机注册界面


    /**
     * dialog
     *
     * @param savedInstanceState
     */

    private TokenPickerDialog tokenPickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected PresenterLogin creatPresenter() {
        if (null == presenterLogin) {
            presenterLogin = new PresenterLogin(this);
        }
        return presenterLogin;
    }

    @Override
    protected void initViews() {


        /**
         * title
         *
         * @param savedInstanceState
         */

        iv_common_back = findADHViewById(R.id.iv_common_back);

        /**
         * 中间部分
         *
         * @param savedInstanceState
         */
        et_username = findADHViewById(R.id.et_username);
        et_password = findADHViewById(R.id.et_password);
        iv_clear_account = findADHViewById(R.id.iv_clear_account);
        iv_clear_pass = findADHViewById(R.id.iv_clear_pass);


        /**
         * 登陆
         */
        layout_login = findADHViewById(R.id.layout_login);
        tv_register_driver = findADHViewById(R.id.tv_register_driver);


    }

    @Override
    protected void initListener() {

        iv_common_back.setOnClickListener(this);
        iv_clear_account.setOnClickListener(this);
        iv_clear_pass.setOnClickListener(this);
        layout_login.setOnClickListener(this);

        tv_register_driver.setOnClickListener(this);
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

            case R.id.iv_clear_account:

                et_username.setText(ConstSign.DOUBLE_QUOTE);

                break;


            case R.id.iv_clear_pass:
                et_password.setText(ConstSign.DOUBLE_QUOTE);
                break;


            case R.id.layout_login:

                String phone = et_username.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                presenterLogin.doLogon(HttpConst.URL.LOGIN, phone, password);

                break;

            case R.id.tv_register_driver:




                openActivity(RegPhonePassActivity.class, null);

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
    public void onDataBackSuccessForLoginJsonObject(String accessToken) {
        presenterLogin.doGetOssConfigInfo(HttpConst.URL.OSS_SERVER_INOF, accessToken);
    }

    @Override
    public void onDataBackSuccessForLoginJsonArray(List<TokenInfo> list) {


        if (null == tokenPickerDialog) {

            tokenPickerDialog = new TokenPickerDialog(this, list);
            tokenPickerDialog.setDialogTokenPickerClickCallBack(this);
            tokenPickerDialog.setCancelable(true);
            tokenPickerDialog.show();

        }
        tokenPickerDialog.show();
    }

    @Override
    public void onDataBackSuccessForOssConfig(String accessToken) {

        presenterLogin.doCheckRegStatues(HttpConst.URL.REGISTER_STATE, accessToken);
    }


    /**
     * 进入设置用户信息界面
     *
     * @param accessToken
     */
    @Override
    public void onDataBackSuccessForSetIdentifyInfo(String accessToken) {
        Bundle bundle = new Bundle();
        bundle.putString(ConstIntent.BundleKEY.DELIVERY_ACCESS_TOKEN, accessToken);
        openActivityAndFinishItself(InfoRegDriverActivity.class, bundle);
    }

    /**
     * 进如设置车辆信息界面
     *
     * @param accessToken
     */
    @Override
    public void onDataBackSuccessForSetVehicleInfo(String accessToken) {


        Bundle bundle = new Bundle();
        bundle.putString(ConstIntent.BundleKEY.DELIVERY_ACCESS_TOKEN, accessToken);
        openActivityAndFinishItself(InRegDriverVehicleActivity.class, bundle);

    }


    /**
     * 身份信息审核中
     */
    @Override
    public void onDataBackSuccessForIndentifyInfoAuthorizing() {
        ToastUtil.showMsg(getApplicationContext(), R.string.identify_info_checking);

    }


    /**
     * 身份信息被拒绝
     */
    @Override
    public void onDataBackSuccessForIndentifyInfoAuthorReject(String accessToken) {


        ToastUtil.showMsg(getApplicationContext(), R.string.identify_info_deny);
        Bundle bundle = new Bundle();
        bundle.putString(ConstIntent.BundleKEY.DELIVERY_ACCESS_TOKEN, accessToken);
        openActivityAndFinishItself(InfoRegDriverActivity.class, bundle);
    }


    /**
     * 进入到考试界面
     *
     * @param accessToken
     */
    @Override
    public void onDataBackSuccessForDoLearnAndExam(String accessToken) {
        Bundle bundle = new Bundle();
        bundle.putString(ConstIntent.BundleKEY.DELIVERY_ACCESS_TOKEN, accessToken);
        openActivityAndFinishItself(ReadLearnActivity.class, bundle);

    }

    /**
     * 车辆信息被拒绝
     *
     * @param accessToken
     */
    @Override
    public void onDataBackSuccessForVehicleReject(String accessToken) {

        ToastUtil.showMsg(getApplicationContext(), R.string.vehicle_info_deny);
        Bundle bundle = new Bundle();
        bundle.putString(ConstIntent.BundleKEY.DELIVERY_ACCESS_TOKEN, accessToken);
        openActivityAndFinishItself(InRegDriverVehicleActivity.class, bundle);
    }

    /**
     * 车辆信息审核中
     */
    @Override
    public void onDataBackSuccessForForVehicleAuthorizing() {


        ToastUtil.showMsg(getApplicationContext(), R.string.vehicle_info_checking);


    }

    @Override
    public void onDataBackSuccessForAllAcessGoToMain(String accessToken) {





        //Aliyu之后还原
        AssetsUtil.copyShareImage();


        SpUtil.getInstance().saveBooleanTosp(ConstSp.SP_KEY_VISITOR, ConstSp.SP_VALUE.DEFAULT_BOOLEAN);//设置游客模式为false
        SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_TOKEN, accessToken); //这时候才能保存token 这个是登陆的token
        SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_SGIN_ACCOUNT, et_username.getText().toString()); //保存当前的登陆账号
        SpUtil.getInstance().saveBooleanTosp(ConstSp.SP_KEY_IS_LOGIN_OR_NOT, ConstSp.SP_VALUE.IS_LOGIN); //判断是否已经登陆
        SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_LOGIN_ROLE, ConstSp.SP_VALUE.ROLE_DRIVER); //保存当前角色为司机



        Log.i("theToken","=====登陆被保存的token："+accessToken);


        Bundle bundle = new Bundle();
        bundle.putInt(ConstIntent.BundleKEY.JUMP_TAG,ConstIntent.BundleValue.LOGIN_SUCCESS);
        openActivityAndFinishItself(MainDriverActivity.class, bundle);

    }


    @Override
    public void doVertifyErrorNullUsername() {

        ToastUtil.showMsg(getApplicationContext(), R.string.input_userName);


    }

    @Override
    public void doVertifyErrorUnlegalUsername() {

        ToastUtil.showMsg(getApplicationContext(), R.string.userName_legal);

    }

    @Override
    public void doVertifyErrorPasswordBetween8_16() {

        ToastUtil.showMsg(getApplicationContext(), R.string.passWord_type);

    }

    @Override
    public void doVertifyErrorNullPassword() {
        ToastUtil.showMsg(getApplicationContext(), R.string.input_passWord);
    }

    @Override
    public void pickerTokenItemClick(String access_token) {
        presenterLogin.doGetOssConfigInfo(HttpConst.URL.OSS_SERVER_INOF,access_token);
    }
}
