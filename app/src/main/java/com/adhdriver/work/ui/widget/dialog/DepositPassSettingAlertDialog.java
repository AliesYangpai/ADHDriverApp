package com.adhdriver.work.ui.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.DepositPassAlertSettingDialogCallBack;
import com.adhdriver.work.constant.ConstTag;


/**
 * Created by Administrator on 2017/4/25.
 * 类描述  提醒 设置提现密码的dialog
 * 版本
 */

public class DepositPassSettingAlertDialog extends AlertDialog implements View.OnClickListener {


    private TextView tv_double_cancel; //底部取消
    private TextView tv_double_sure;//底部确认

    private DepositPassAlertSettingDialogCallBack depositPassAlertSettingDialogCallBack;


    public void setDepositPassAlertSettingDialogCallBack(DepositPassAlertSettingDialogCallBack depositPassAlertSettingDialogCallBack) {
        this.depositPassAlertSettingDialogCallBack = depositPassAlertSettingDialogCallBack;
    }

    public DepositPassSettingAlertDialog(Context context) {
        super(context, R.style.depositPassSettingAlertDialog);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_deposit_pass_setting_alert);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);

        //初始化界面控件
        initView();

        //初始化界面控件的事件
        initListener();


    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initListener() {
        /**
         * 底部按钮
         */
        tv_double_cancel.setOnClickListener(this); //底部取消
        tv_double_sure.setOnClickListener(this);//底部确认

    }


    /**
     * 初始化界面控件
     */
    private void initView() {

        /**
         * 底部按钮
         */
        tv_double_cancel = (TextView) findViewById(R.id.tv_double_cancel); //底部取消
        tv_double_sure = (TextView) findViewById(R.id.tv_double_sure);//底部确认
    }


    @Override
    public void onClick(View v) {



        switch (v.getId()) {

            case R.id.tv_double_cancel:  //取消

                break;


            case R.id.tv_double_sure:    //确认

                if (null != depositPassAlertSettingDialogCallBack) {
                    depositPassAlertSettingDialogCallBack.sureToSetDepositPass();
                }
                break;

        }

        dismiss();
    }


}
