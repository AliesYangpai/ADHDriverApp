package com.adhdriver.work.ui.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.DialogAlertActivtyCallBack;
import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.entity.UdpEntity;
import com.adhdriver.work.utils.SpUtil;


/**
 * Created by Administrator on 2017/10/7.
 * 类描述
 * 版本
 */

public class KickAlertDialog extends AlertDialog implements
        View.OnClickListener {


    private Context mContext;


    /**
     * 数据相关
     */
    private UdpEntity udpEntity;


    private DialogAlertActivtyCallBack dialogAlertActivtyCallBack;


    private TextView tv_message;
    private TextView tv_reg_bottom_text;


    public void setDialogAlertActivtyCallBack(DialogAlertActivtyCallBack dialogAlertActivtyCallBack) {
        this.dialogAlertActivtyCallBack = dialogAlertActivtyCallBack;
    }


    public void setUdpEntity(UdpEntity udpEntity) {
        this.udpEntity = udpEntity;
    }

    public KickAlertDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    public KickAlertDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);

        this.mContext = context;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("NewOrderAlert", "=============onCreate");
        setContentView(R.layout.dialog_kicked_button);
        setCanceledOnTouchOutside(false);
        initView();
        initListener();
        initData();



        Log.i("AlertDialogActivity", "=============NewOrderAlertDialog 调用onCreate==");
    }





    private void initView() {
          tv_message = (TextView) findViewById(R.id.tv_message);
          tv_reg_bottom_text= (TextView) findViewById(R.id.tv_reg_bottom_text);

    }


    private void initListener() {


        tv_reg_bottom_text.setOnClickListener(this);

    }


    private void initData() {

        if(null!= udpEntity) {

            tv_message.setText(udpEntity.getContent());

        }


    }





    @Override
    public void dismiss() {

        super.dismiss();


        if (null != dialogAlertActivtyCallBack) {

            dialogAlertActivtyCallBack.doFinishPage();

        }


        Log.i("NewOrderAlert", "=============dismiss");

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_reg_bottom_text:


                /**
                 * 跳转到登陆界面
                 */

                if(null != dialogAlertActivtyCallBack) {

                    dialogAlertActivtyCallBack.doFinishPage();

                }

                break;

        }
    }


}
