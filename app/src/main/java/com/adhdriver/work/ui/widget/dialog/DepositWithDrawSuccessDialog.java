package com.adhdriver.work.ui.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.adhdriver.work.R;

/**
 * Created by Administrator on 2017/4/15.
 * 类描述  提现申请提交成功的dialog
 * 版本
 */

public class DepositWithDrawSuccessDialog extends AlertDialog implements View.OnClickListener {


    private TextView tv_reg_bottom_text;

    public DepositWithDrawSuccessDialog(Context context) {
        super(context, R.style.depositWithDrawSuccessDialog);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_deposit_withdraw_success);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);


        //初始化界面控件
        initView();

        //初始化界面控件的事件
        initEvent();


    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
        tv_reg_bottom_text.setOnClickListener(this);

    }




    /**
     * 初始化界面控件
     */
    private void initView() {


        tv_reg_bottom_text = (TextView) findViewById(R.id.tv_reg_bottom_text);

    }




    @Override
    public void onClick(View v) {


        DepositWithDrawSuccessDialog.this.dismiss();



    }


}
