package com.adhdriver.work.ui.widget.dialog.edit;

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
import com.adhdriver.work.callback.edit.DialogClickEditCallBack;
import com.adhdriver.work.constant.ConstTag;

/**
 * Created by Administrator on 2017/5/12.
 * 类描述
 * 版本
 */

public class EditDialog extends Dialog implements View.OnClickListener {
    /**
     * 中间消息
     */

    private String msgSure;//确定的按钮文字
    private String msgCancel;//取消的按钮文字


    /**
     * 中间文本
     */
    private String title;
    private String hint;

    /**
     * 中间控件
     */

    private TextView tv_title;
    private TextView tv_unit;


    /**
     * 底部按钮
     */

    private EditText et_content;//中间编辑按钮
    private TextView tv_bidding_alert;//竞价的手续费提示文字
    private TextView tv_edit_cancel; //底部取消
    private TextView tv_edit_sure;//底部确认


    /**
     * 回调函数
     *
     * @param context
     * @param tag
     */

    private DialogClickEditCallBack dialogClickEditCallBack;
    private Context context;
    private View view;

    /**
     * 数据相关
     */
    private int tag;
    private String commonString;


    public String getCommonString() {
        return commonString;
    }

    public void setCommonString(String commonString) {
        this.commonString = commonString;
    }

    public EditDialog(Context context) {
        super(context, R.style.RegDeny);
        this.context = context;

    }


    public EditDialog(Context context, String title, int tag) {
        super(context, R.style.RegDeny);
        this.title = title;
        this.context = context;
        this.tag = tag;
    }


    public EditDialog(Context context, String title, int tag, String commonString) {
        super(context, R.style.RegDeny);
        this.title = title;
        this.tag = tag;
        this.commonString = commonString;
        this.context = context;
    }



    public EditDialog(Context context, AttributeSet attrs) {
        super(context, R.style.RegDeny);
        this.context = context;


    }


    public String getMsgSure() {
        return msgSure;
    }

    public String getMsgCancel() {
        return msgCancel;
    }

    public String getTitle() {
        return title;
    }


    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public void setDialogClickEditCallBack(DialogClickEditCallBack dialogClickEditCallBack) {
        this.dialogClickEditCallBack = dialogClickEditCallBack;
    }

    public void setMsgSure(String msgSure) {
        this.msgSure = msgSure;


    }

    public void setMsgCancel(String msgCancel) {
        this.msgCancel = msgCancel;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_edit);

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
        tv_edit_cancel.setOnClickListener(this); //底部取消
        tv_edit_sure.setOnClickListener(this);//底部确认

    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {





        changeUiByTag();


        tv_title.setText(title);

    }

    private void changeUiByTag() {


        switch (tag) {




            case ConstTag.DialogTag.ENTER_RECIVER_CODE:
                tv_bidding_alert.setVisibility(View.GONE);
                tv_unit.setVisibility(View.GONE);
                et_content.setInputType( InputType.TYPE_CLASS_NUMBER);
                et_content.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
                break;


            case ConstTag.DialogTag.CANCEL_ORDER_IN_EDIT:
                tv_bidding_alert.setVisibility(View.GONE);
                tv_unit.setVisibility(View.GONE);
                break;



        }

    }


    /**
     * 初始化界面控件
     */
    private void initView() {


        /**
         * title
         */


        tv_title = (TextView) findViewById(R.id.tv_title);


        tv_unit = (TextView) findViewById(R.id.tv_unit);
        /**
         * 文本信息
         */
        et_content = (EditText) findViewById(R.id.et_content);

        tv_bidding_alert = (TextView) findViewById(R.id.tv_bidding_alert);

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

        if (null != dialogClickEditCallBack) {

            if (isSure) {


                String text = et_content.getText().toString().trim();

                if (vertifyCode(text)) {


                    dialogClickEditCallBack.dialogEditClickSure(tag, text);

                }


            } else {

                dialogClickEditCallBack.dialogEditClickCancel(tag);

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
