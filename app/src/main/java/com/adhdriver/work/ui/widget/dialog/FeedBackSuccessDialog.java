package com.adhdriver.work.ui.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.FeedBackSuccessDialogClickCallBack;

/**
 * Created by Administrator on 2017/4/15.
 * 类描述 意见反馈成功的dialog
 * 版本
 */

public class FeedBackSuccessDialog extends AlertDialog implements View.OnClickListener {


    private TextView tv_message;//消息提示文本
    private String messageStr;//从外界设置的消息文本


    private TextView tv_reg_bottom_text; //底部文字按钮
    private String bottomText;//底部显示文字


    private FeedBackSuccessDialogClickCallBack feedBackSuccessDialogClickCallBack;


    public void setFeedBackSuccessDialogClickCallBack(FeedBackSuccessDialogClickCallBack feedBackSuccessDialogClickCallBack) {
        this.feedBackSuccessDialogClickCallBack = feedBackSuccessDialogClickCallBack;
    }

    public FeedBackSuccessDialog(Context context, String messageStr, String bottomText) {
        super(context, R.style.feedBackDialog);
        this.messageStr = messageStr;
        this.bottomText = bottomText;
    }


    private boolean isSuccess;  //用于进行http返回成功或失败的dialog不同提示

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getBottomText() {
        return bottomText;
    }

    public void setBottomText(String bottomText) {
        this.bottomText = bottomText;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_grab_success);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);


        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
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
     * 初始化界面控件的显示数据
     */
    private void initData() {

        tv_message.setText(messageStr);
        tv_reg_bottom_text.setText(bottomText);


    }


    /**
     * 初始化界面控件
     */
    private void initView() {


        tv_message = (TextView) findViewById(R.id.tv_message);
        tv_reg_bottom_text = (TextView) findViewById(R.id.tv_reg_bottom_text);

    }


    public void setMessageStr(String messageStr) {
        this.messageStr = messageStr;
    }

    @Override
    public void onClick(View v) {


        FeedBackSuccessDialog.this.dismiss();

        clickEvent();

    }


    /**
     * 点击事件
     */
    private void clickEvent() {

        if (null != feedBackSuccessDialogClickCallBack) {


            feedBackSuccessDialogClickCallBack.feedBackSuccessDialogClick();
        }

    }
}
