package com.adhdriver.work.ui.widget.popwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.DepositPassOperatePopwindowCallBack;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2017/7/7.
 * 类描述  修改支付密码或者忘记支付密码
 * 版本
 */

public class DepositPassOperatePopWindow extends PopupWindow implements View.OnClickListener {


    /**
     * 初始化配置
     */
    private Context context;

    private LayoutInflater inflater;


    private WeakReference<Context> weakReference;


    /**
     * 控件相关
     */

    private View view;

    private RelativeLayout rl_change_pass;//修改密码
    private RelativeLayout rl_forgot_pass;//忘记密码
    private TextView tv_paypass_cancel; //取消


    private DepositPassOperatePopwindowCallBack depositPassOperatePopwindowCallBack; //调用系统相册设置图片的回调函数

    public void setDepositPassOperatePopwindowCallBack(DepositPassOperatePopwindowCallBack depositPassOperatePopwindowCallBack) {
        this.depositPassOperatePopwindowCallBack = depositPassOperatePopwindowCallBack;
    }

    public DepositPassOperatePopWindow(Context context) {
        super(context);


        weakReference = new WeakReference<Context>(context);

        this.inflater = LayoutInflater.from(weakReference.get());
        initViews();
        initListener();

    }


    private void initViews() {





        view = this.inflater.inflate(R.layout.popwindow_operate_deposite_pass, null);

        rl_change_pass = (RelativeLayout) view.findViewById(R.id.rl_change_pass);//修改密码
        rl_forgot_pass = (RelativeLayout) view.findViewById(R.id.rl_forgot_pass);//忘记密码
        tv_paypass_cancel = (TextView) view.findViewById(R.id.tv_paypass_cancel); //取消
        this.setContentView(view);

        ColorDrawable colorDrawable = new ColorDrawable(0x0000000);

        this.setBackgroundDrawable(colorDrawable);

        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        this.setOutsideTouchable(true); //这一步要在showAsDropDown之前调用
        this.setFocusable(true);
        this.setAnimationStyle(R.style.popwindowDepositePassOperateStyle);



    }

    private void initListener() {

        rl_change_pass.setOnClickListener(this);
        rl_forgot_pass.setOnClickListener(this);
        tv_paypass_cancel.setOnClickListener(this);

    }


    @Override
    public boolean isShowing() {


        boolean type = super.isShowing();

        Log.i("payPop", "isSHowIng:" + type);


        if(type) {


            backgroundAlpha((Activity)weakReference.get(),1f);

        }else {

            backgroundAlpha((Activity)weakReference.get(),0.5f);
        }


        return type;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.rl_change_pass:

                DepositPassOperatePopWindow.this.dismiss();


                doChangePass();
                break;

            case R.id.rl_forgot_pass:
                DepositPassOperatePopWindow.this.dismiss();

                doForgotPass();

                break;

            case R.id.tv_paypass_cancel:

                DepositPassOperatePopWindow.this.dismiss();

                break;
        }
    }


    private void doChangePass() {

        if(null != depositPassOperatePopwindowCallBack) {

            depositPassOperatePopwindowCallBack.depositPassPopwindowChangeClick();

        }

    }


    private void doForgotPass(){

        if(null != depositPassOperatePopwindowCallBack) {

            depositPassOperatePopwindowCallBack.depositPassPopwindowForgetClick();

        }

    }


    /**
     * 背景色变暗
     */

    public void backgroundAlpha(Activity context, float bgAlpha)
    {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }


}
