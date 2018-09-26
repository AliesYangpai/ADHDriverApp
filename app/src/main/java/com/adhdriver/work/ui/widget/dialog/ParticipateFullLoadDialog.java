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
import com.adhdriver.work.callback.ParticipateFullLoadDialogCallBack;
import com.adhdriver.work.ui.widget.inputfilter.InputFilterMinMax;

/**
 * Created by Administrator on 2017/5/12.
 * 类描述   司机输入竞价价格的dialog
 * 版本
 */

public class ParticipateFullLoadDialog extends Dialog implements View.OnClickListener {









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

    private ParticipateFullLoadDialogCallBack participateFullLoadDialogCallBack;
    private Context context;


    public ParticipateFullLoadDialog(Context context) {
        super(context, R.style.participateFullDialog);
        this.context = context;

    }






    public ParticipateFullLoadDialog(Context context, AttributeSet attrs) {
        super(context, R.style.participateFullDialog);
        this.context = context;


    }


    public void setParticipateFullLoadDialogCallBack(ParticipateFullLoadDialogCallBack participateFullLoadDialogCallBack) {
        this.participateFullLoadDialogCallBack = participateFullLoadDialogCallBack;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_participate_fullload);

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




        tv_bidding_alert.setText(context.getString(R.string.bidding_alert));
        et_content.setInputType( InputType.TYPE_CLASS_NUMBER);
        et_content.setFilters(new InputFilter[]{new InputFilterMinMax("1","999999")});


    }




    /**
     * 初始化界面控件
     */
    private void initView() {


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

        this.dismiss();


        switch (v.getId()) {

            case R.id.tv_edit_cancel:  //取消


                break;


            case R.id.tv_edit_sure:    //确认

                clickEventByTag();

                break;


        }
    }


    /**
     * 点击事件
     */


    /**
     * 根据当前tag选择确认或者
     *
     * @param
     */

    private void clickEventByTag() {

        if (null != participateFullLoadDialogCallBack) {

            String price = et_content.getText().toString().trim();

            if (vertifyCode(price)) {

                participateFullLoadDialogCallBack.participateFullLoadDialogClickSure( price);

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
