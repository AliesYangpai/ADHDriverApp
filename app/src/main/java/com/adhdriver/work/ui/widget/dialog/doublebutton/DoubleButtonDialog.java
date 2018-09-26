package com.adhdriver.work.ui.widget.dialog.doublebutton;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.doublebutton.DoubleDialogCallBack;
import com.adhdriver.work.constant.ConstTag;


/**
 * Created by Administrator on 2017/4/25.
 * 类描述  有2个button的dialog
 * 版本
 */

public class DoubleButtonDialog extends AlertDialog implements View.OnClickListener {


    /**
     * 中间消息
     */

    private String message;//从外界设置的消息文本
    private String msgSure;//确定的按钮文字
    private String msgCancel;//取消的按钮文字





    /**
     * 底部按钮
     */
    private TextView tv_message_duoble;
    private TextView tv_double_cancel; //底部取消
    private TextView tv_double_sure;//底部确认

    /**
     * tag标记
     * 根据这个tag来进行 这个dialog的点击事件与显示说明
     */
    private int tag;//根据这个tag来进行 这个dialog的点击事件与显示说明


    /**
     * 回调函数
     * @param context
     * @param tag
     */

    private DoubleDialogCallBack doubleDialogCallBack;


    public void setDoubleDialogCallBack(DoubleDialogCallBack doubleDialogCallBack) {
        this.doubleDialogCallBack = doubleDialogCallBack;
    }

    public DoubleButtonDialog(Context context, int tag) {
        super(context, R.style.RegDeny);
        this.tag = tag;
    }


    public DoubleButtonDialog(Context context) {
        super(context, R.style.RegDeny);

    }

    public void setTag(int tag) {
        this.tag = tag;
    }




    /**
     * 设置系显示数据
     * @param message
     */

    public void setMessage(String message) {
        this.message = message;
//        tv_message_duoble.setText(this.message);
    }

    public void setMsgSure(String msgSure) {
        this.msgSure = msgSure;


//        tv_double_sure.setText(this.msgSure);

    }

    public void setMsgCancel(String msgCancel) {
        this.msgCancel = msgCancel;
//        tv_double_cancel.setText(this.msgCancel);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_double_button);
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
        tv_double_cancel.setText(msgCancel);
        tv_double_sure.setText(msgSure);

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



        switch (v.getId()) {

            case R.id.tv_double_cancel:  //取消

                clickEventByTag(false);

                break;


            case R.id.tv_double_sure:    //确认

                clickEventByTag(true);

                break;



        }
    }


    /**
     * 点击事件
     */


    /**
     *  根据当前tag选择确认或者
     * @param isSure
     */

    private void clickEventByTag(boolean isSure) {



       switch (tag) {

           case ConstTag.DialogTag.REACH_TO_START_PLACE:
               clickEvent(tag,isSure);
               break;


           case ConstTag.DialogTag.CHANGE_PRICE:
               clickEvent(tag,isSure);
               break;


           case ConstTag.DialogTag.CANCEL_ORDER:
               clickEvent(tag,isSure);
               break;

           case ConstTag.DialogTag.ARRIVED:
               clickEvent(tag,isSure);
               break;

           case ConstTag.DialogTag.SET_PAY_CODE:
               clickEvent(tag,isSure);
               break;


           case ConstTag.DialogTag.CHECK_PAY:

               clickEvent(tag,isSure);

               break;

           case ConstTag.DialogTag.VEHICLE_CURRENT_EDIT_TO_LOGOUT:

               clickEvent(tag,isSure);
               break;

           case ConstTag.DialogTag.VEHICLE_CURRENT_DEL:


               clickEvent(tag,isSure);
               break;

           case ConstTag.DialogTag.REFUEL_CLOSE:
               clickEvent(tag,isSure);

               break;
       }

    }

    /**
     * 点击事件
     */
    private void clickEvent(int tag,boolean isSure) {

        if (null != doubleDialogCallBack) {

            if(isSure) {

                doubleDialogCallBack.dialogClickSure(tag);

            }else {

                doubleDialogCallBack.dialogClickCancel(tag);

            }

        }

    }

}
