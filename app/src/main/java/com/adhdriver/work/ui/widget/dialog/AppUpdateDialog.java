package com.adhdriver.work.ui.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.AppUpdateDialogCallBack;
import com.adhdriver.work.entity.AppUpdate;

/**
 * Created by Administrator on 2017/6/26.
 * 类描述  版本更新的dialog
 * 版本
 */

public class AppUpdateDialog extends AlertDialog implements View.OnClickListener {










    /**
     * 底部按钮
     */
    private TextView tv_option;
    private TextView tv_cancel_update; //底部取消
    private TextView tv_sure_update;//底部确认


    /**
     * 数据相关
     */
    private AppUpdate appUpdate;//版本更新信息

    /**
     * 回调函数
     * @param context
     * @param tag
     */

    private AppUpdateDialogCallBack appUpdateDialogCallBack;


    public AppUpdate getAppUpdate() {
        return appUpdate;
    }

    public void setAppUpdate(AppUpdate appUpdate) {
        this.appUpdate = appUpdate;
    }

    public void setAppUpdateDialogCallBack(AppUpdateDialogCallBack appUpdateDialogCallBack) {
        this.appUpdateDialogCallBack = appUpdateDialogCallBack;
    }

    public AppUpdateDialog(Context context, AppUpdate appUpdate) {
        super(context, R.style.appUpdateInfoDialog);
        this.appUpdate = appUpdate;

    }







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_update_appinfo);
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
        tv_cancel_update.setOnClickListener(this); //底部取消
        tv_sure_update.setOnClickListener(this);//底部确认

    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {


        if(null != appUpdate) {

            tv_option.setText(appUpdate.getDescription());

        }




    }


    /**
     * 初始化界面控件
     */
    private void initView() {


        /**
         * 底部按钮
         */
          tv_option = (TextView) findViewById(R.id.tv_option);
          tv_cancel_update = (TextView) findViewById(R.id.tv_cancel_update); //底部取消
          tv_sure_update = (TextView) findViewById(R.id.tv_sure_update);//底部确认



    }



    @Override
    public void onClick(View v) {

        this.dismiss();

        switch (v.getId()) {

            case R.id.tv_cancel_update:  //取消


//                clickEvent(false);

                break;



            case R.id.tv_sure_update:    //确认

                clickEvent();
                break;



        }
    }


    /**
     * 点击事件
     */

    /**
     * 点击事件
     */
    private void clickEvent() {

        if (null != appUpdateDialogCallBack) {



                appUpdateDialogCallBack.appUpdateDialogClickSure(appUpdate);



        }

    }
}
