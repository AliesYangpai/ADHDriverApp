package com.adhdriver.work.ui.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.PublishDialogCancelClickCallBack;


/**
 * Created by Administrator on 2017/4/25.
 * 顺风车取消的dialog'
 * 版本
 */

public class PublishRouteCancelDialog extends AlertDialog implements View.OnClickListener {


    /**
     * 中间消息
     */

    private String message;//从外界设置的消息文本

    private String hitchhike_on;


    /**
     * 底部按钮
     */
    private TextView tv_message_duoble;
    private TextView tv_double_cancel; //底部取消
    private TextView tv_double_sure;//底部确认


    /**
     * 回调函数
     *
     * @param context
     * @param tag
     */

    private PublishDialogCancelClickCallBack publishDialogCancelClickCallBack;

    public void setPublishDialogCancelClickCallBack(PublishDialogCancelClickCallBack publishDialogCancelClickCallBack) {
        this.publishDialogCancelClickCallBack = publishDialogCancelClickCallBack;
    }

    public PublishRouteCancelDialog(Context context) {
        super(context, R.style.publishRouteCancelDialog);

    }


    public String getHitchhike_on() {
        return hitchhike_on;
    }

    public void setHitchhike_on(String hitchhike_on) {
        this.hitchhike_on = hitchhike_on;
    }

    /**
     * 设置系显示数据
     *
     * @param message
     */

    public void setMessage(String message) {
        this.message = message;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_publish_route_cancel);
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
        tv_double_cancel.setOnClickListener(this); //底部取消
        tv_double_sure.setOnClickListener(this);//底部确认

    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {

        tv_message_duoble.setText(message);

    }


    /**
     * 初始化界面控件
     */
    private void initView() {


        /**
         * 文本信息
         */
        tv_message_duoble = (TextView) findViewById(R.id.tv_message_duoble);


        /**
         * 底部按钮
         */
        tv_double_cancel = (TextView) findViewById(R.id.tv_double_cancel); //底部取消
        tv_double_sure = (TextView) findViewById(R.id.tv_double_sure);//底部确认


    }


    @Override
    public void onClick(View v) {


        dismiss();



        switch (v.getId()) {

            case R.id.tv_double_cancel:  //取消


                if (null != publishDialogCancelClickCallBack) {

                    publishDialogCancelClickCallBack.publishDialogCancel();
                }


                break;


            case R.id.tv_double_sure:    //确认


                if (null != publishDialogCancelClickCallBack) {

                    publishDialogCancelClickCallBack.publishDialogSure(hitchhike_on);
                }


                break;


        }
    }


}
