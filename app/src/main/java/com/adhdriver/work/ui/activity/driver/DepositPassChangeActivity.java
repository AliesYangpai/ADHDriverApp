package com.adhdriver.work.ui.activity.driver;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.presenter.driver.PresenterDriverDepositPassChange;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.iview.driver.IDepositPassChangeView;
import com.adhdriver.work.utils.ToastUtil;
import com.jungly.gridpasswordview.GridPasswordView;


/**
 * 修改提现密码
 */
public class DepositPassChangeActivity extends BaseActivity<IDepositPassChangeView,PresenterDriverDepositPassChange> implements
        IDepositPassChangeView,
        View.OnClickListener{


    private PresenterDriverDepositPassChange presenterDriverDepositPassChange;


    /**
     * title
     */

    private ImageView iv_common_back;
    private TextView tv_common_title;
    private ImageView iv_common_search;


    private GridPasswordView gpv_original_withdraw_pass;
    private GridPasswordView gpv_set_new_withdraw_pass;
    private GridPasswordView gpv_set_new_withdraw_pass_again;


    /**
     * 底部botton
     */
    private TextView tv_withdraw_change_sure;



    @Override
    protected PresenterDriverDepositPassChange creatPresenter() {
        if(null == presenterDriverDepositPassChange) {

            presenterDriverDepositPassChange = new PresenterDriverDepositPassChange(this);
        }
        return presenterDriverDepositPassChange;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_deposit_pass);
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
        tv_common_title.setText(this.getString(R.string.change_withdraw_pass));


        gpv_original_withdraw_pass = findADHViewById(R.id.gpv_original_withdraw_pass);
        gpv_set_new_withdraw_pass = findADHViewById(R.id.gpv_set_new_withdraw_pass);
        gpv_set_new_withdraw_pass_again = findADHViewById(R.id.gpv_set_new_withdraw_pass_again);

        tv_withdraw_change_sure = findADHViewById(R.id.tv_withdraw_change_sure);

    }

    @Override
    protected void initListener() {
        iv_common_back.setOnClickListener(this);

        tv_withdraw_change_sure.setOnClickListener(this);
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
    public void onDataBackSuccessForChangeDepositPass() {

        dofinishItself();

    }


    @Override
    public void doVertifyErrorNullOldPass() {

        ToastUtil.showMsg(getApplicationContext(),this.getString(R.string.enter_old_deposit_pass));

    }

    @Override
    public void doVertifyErrorNullNewPass() {
        ToastUtil.showMsg(getApplicationContext(),this.getString(R.string.enter_new_deposit_pass));
    }

    @Override
    public void doVertifyErrorUnLegalPass() {
        ToastUtil.showMsg(getApplicationContext(),this.getString(R.string.deposit_pass_6));
    }

    @Override
    public void doVertifyErrorNullNewPassAgain() {
        ToastUtil.showMsg(getApplicationContext(),this.getString(R.string.enter_again));
    }

    @Override
    public void doVertifyErrorNewPassNotSameToNewPassAgain() {
        ToastUtil.showMsg(getApplicationContext(),this.getString(R.string.pass_not_same));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_common_back:
                this.finish();
                break;

            case R.id.tv_withdraw_change_sure:


                String orginalPass = gpv_original_withdraw_pass.getPassWord().toString().trim();
                String newPass = gpv_set_new_withdraw_pass.getPassWord().toString().trim();
                String newPassAgain =gpv_set_new_withdraw_pass_again.getPassWord().toString().trim();


                presenterDriverDepositPassChange.doChangeDepositePass(HttpConst.URL.CHANGE_DEPOSITE_PASSWORD,
                        orginalPass,
                        newPass,
                        newPassAgain );
                break;

        }
    }
}
