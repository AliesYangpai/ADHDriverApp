package com.adhdriver.work.ui.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.h5.RefuelExitDialogCallBack;

import static com.adhdriver.work.R.id.tv_double_cancel;
import static com.adhdriver.work.R.id.tv_double_sure;


/**
 * Created by Administrator on 2017/4/25.
 * 类描述  有2个button的dialog
 * 版本
 */

public class ExitRefuelH5Dialog extends AlertDialog implements View.OnClickListener {






    private TextView tv_cancel;
    private TextView tv_sure;


    private RefuelExitDialogCallBack refuelExitDialogCallBack;


    public void setRefuelExitDialogCallBack(RefuelExitDialogCallBack refuelExitDialogCallBack) {
        this.refuelExitDialogCallBack = refuelExitDialogCallBack;
    }

    public ExitRefuelH5Dialog(Context context) {
        super(context, R.style.exitRefuelH5Dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_exit_refuel_h5);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);


        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
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
        tv_cancel.setOnClickListener(this); //底部取消
        tv_sure.setOnClickListener(this);//底部确认

    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {


    }


    /**
     * 初始化界面控件
     */
    private void initView() {






        /**
         * 底部按钮
         */
          tv_cancel = (TextView) findViewById(R.id.tv_cancel);
          tv_sure = (TextView) findViewById(R.id.tv_sure);



    }



    @Override
    public void onClick(View v) {



        switch (v.getId()) {

            case R.id.tv_cancel:  //取消


                clickEvent(false);

                break;


            case R.id.tv_sure:    //确认


                clickEvent(true);
                break;



        }
    }




    /**
     * 点击事件
     */
    private void clickEvent(boolean isSure) {

        if (null != refuelExitDialogCallBack) {

            if(isSure) {

                refuelExitDialogCallBack.onClickRefuelExitSure();

            }else {

                refuelExitDialogCallBack.onClickRefuelExitCancel();

            }

        }

    }

}
