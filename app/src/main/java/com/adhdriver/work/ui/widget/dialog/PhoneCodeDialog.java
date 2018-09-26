package com.adhdriver.work.ui.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.DialogEnterPhoneCodeCallBack;

/**
 * Created by Administrator on 2017/5/12.
 * 类描述  验证码的dialog
 * 版本
 */

public class PhoneCodeDialog extends Dialog implements View.OnClickListener {




    /**
     * 底部按钮
     */

    private EditText et_content;//中间编辑按钮

    private TextView tv_edit_cancel; //底部取消
    private TextView tv_edit_sure;//底部确认


    /**
     * 回调函数
     *
     * @param context
     * @param tag
     */

    private DialogEnterPhoneCodeCallBack dialogEnterPhoneCodeCallBack;
    private Context context;
    private View view;





    public PhoneCodeDialog(Context context) {
        super(context, R.style.RegDeny);
        this.context = context;

    }



    public PhoneCodeDialog(Context context, AttributeSet attrs) {
        super(context, R.style.RegDeny);
        this.context = context;


    }


    public void setDialogEnterPhoneCodeCallBack(DialogEnterPhoneCodeCallBack dialogEnterPhoneCodeCallBack) {
        this.dialogEnterPhoneCodeCallBack = dialogEnterPhoneCodeCallBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_enter_phone_code);

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
        tv_edit_cancel.setOnClickListener(this); //底部取消
        tv_edit_sure.setOnClickListener(this);//底部确认

    }






    /**
     * 初始化界面控件
     */
    private void initView() {



        /**
         * 文本信息
         */
        et_content = (EditText) findViewById(R.id.et_content);


        /**
         * 底部按钮
         */
        tv_edit_cancel = (TextView) findViewById(R.id.tv_edit_cancel); //底部取消
        tv_edit_sure = (TextView) findViewById(R.id.tv_edit_sure);//底部确认




    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.tv_edit_cancel:  //取消

                clickEventByTag(false);

                break;


            case R.id.tv_edit_sure:    //确认

                clickEventByTag(true);

                break;


        }
    }


    /**
     * 点击事件
     */


    /**
     * 根据当前tag选择确认或者
     *
     * @param isSure
     */

    private void clickEventByTag(boolean isSure) {

        clickEvent(isSure);

    }

    /**
     * 点击事件
     */
    private void clickEvent(boolean isSure) {

        if (null != dialogEnterPhoneCodeCallBack) {

            if (isSure) {


                String text = et_content.getText().toString().trim();

                if (vertifyCode(text)) {


                    dialogEnterPhoneCodeCallBack.dialogEnterPhoneCodeSure( text);

                }


            } else {

                dialogEnterPhoneCodeCallBack.dialogEnterPhoneCodeCancel();

            }

        }

    }


    private boolean vertifyCode(String str) {

        boolean result = false;


        if (!TextUtils.isEmpty(str)) {

            result = true;

        }

        return result;

    }

}
