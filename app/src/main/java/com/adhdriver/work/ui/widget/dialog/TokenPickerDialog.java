package com.adhdriver.work.ui.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.adhdriver.work.R;

import com.adhdriver.work.callback.DialogTokenPickerClickCallBack;
import com.adhdriver.work.entity.driver.token.TokenInfo;
import com.adhdriver.work.ui.adapter.fordialog.TokenDialogPickerAdapter;
import com.adhdriver.work.utils.ToastUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/8/28.
 * 类描述
 * 版本
 */

public class TokenPickerDialog extends AlertDialog implements AdapterView.OnItemClickListener {


    /**
     * title
     * @param context
     */

    private TextView tv_piker_dialog_title;

    /**
     * 中间listView
     * @param context
     */

    private ListView lv_tokens;



    private TokenDialogPickerAdapter tokenDialogPickerAdapter;

    private Context context;




    /**
     * 数据相关
     */
    private List<TokenInfo> list;

    private DialogTokenPickerClickCallBack dialogTokenPickerClickCallBack;





    public void setDialogTokenPickerClickCallBack(DialogTokenPickerClickCallBack dialogTokenPickerClickCallBack) {
        this.dialogTokenPickerClickCallBack = dialogTokenPickerClickCallBack;
    }

    public List<TokenInfo> getList() {
        return list;
    }

    public void setList(List<TokenInfo> list) {
        this.list = list;
        if(null != tokenDialogPickerAdapter) {

            tokenDialogPickerAdapter.setList(this.list);
        }

    }

    public TokenPickerDialog(Context context) {
        super(context, R.style.TokenPickerVehicleDialog);
        this.context = context;
    }



    public TokenPickerDialog(Context context, List<TokenInfo> list) {
        super(context, R.style.TokenPickerVehicleDialog);
        this.context = context;
        this.list = list;
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_picker_tokens);
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
     * 初始化界面控件
     */
    private void initView() {

        /**
         * title
         * @param context
         */


        tv_piker_dialog_title = (TextView) findViewById(R.id.tv_piker_dialog_title);


        /**
         * 中间listView
         * @param context
         */

        lv_tokens = (ListView) findViewById(R.id.lv_tokens);
        tokenDialogPickerAdapter = new TokenDialogPickerAdapter(context);
        lv_tokens.setAdapter(tokenDialogPickerAdapter);





    }



    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {



        tokenDialogPickerAdapter.setList(this.list);

    }



    /**
     * 初始化界面的确定和取消监听器
     */
    private void initListener() {

        lv_tokens.setOnItemClickListener(this);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        this.dismiss();


        TokenInfo tokenInfo = tokenDialogPickerAdapter.getList().get(position);

       boolean isAllAccess =  tokenDialogPickerAdapter.isAccessOrDeny();

        if(null != dialogTokenPickerClickCallBack && null!= tokenInfo) {


            doSelectToSetToken(isAllAccess,tokenInfo);

        }



    }


    private void  doSelectToSetToken(boolean isAllAccess,TokenInfo tokenInfo) {

        if(isAllAccess) {

            dialogTokenPickerClickCallBack.pickerTokenItemClick(tokenInfo.getAccess_token());

        }else {



            int processing_order_counts = tokenInfo.getProcessing_order_counts();

            if(processing_order_counts != 0) {

                dialogTokenPickerClickCallBack.pickerTokenItemClick(tokenInfo.getAccess_token());

            }else {

                ToastUtil.showMsg(context.getApplicationContext(),"您不能登陆该车辆");

            }


        }
    }

}
