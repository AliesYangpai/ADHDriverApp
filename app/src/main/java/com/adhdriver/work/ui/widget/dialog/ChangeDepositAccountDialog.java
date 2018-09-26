package com.adhdriver.work.ui.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.DepositChangeAccountChannelCallBack;
import com.adhdriver.work.callback.DepositChannelCallBack;
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.entity.driver.pay.PayChannelInfo;
import com.adhdriver.work.ui.adapter.fordialog.ChangeDepositAccountDialogAdapter;
import com.adhdriver.work.ui.adapter.fordialog.DepositMoneyChannelDialogAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/5/12.
 * 类描述  提现账户变更的dilog
 * 版本
 */

public class ChangeDepositAccountDialog extends Dialog implements View.OnClickListener,AdapterView.OnItemClickListener{


    /**
     * 回调函数
     *
     * @param context
     * @param tag
     */


    private Context context;
    private View view;


    private TextView tv_cancel;
    private ListView lv_pay_channel;
    private ChangeDepositAccountDialogAdapter changeDepositAccountDialogAdapter;


    /**
     * 数据相关
     * @param context
     */

    private List<PayChannelInfo> payChannelInfos;


    /**
     * callback
     * @param payChannelInfos
     */

    public DepositChangeAccountChannelCallBack depositChangeAccountChannelCallBack;

    public void setDepositChangeAccountChannelCallBack(DepositChangeAccountChannelCallBack depositChangeAccountChannelCallBack) {
        this.depositChangeAccountChannelCallBack = depositChangeAccountChannelCallBack;
    }

    public void setPayChannelInfos(List<PayChannelInfo> payChannelInfos) {
        this.payChannelInfos = payChannelInfos;
    }

    public ChangeDepositAccountDialog(Context context) {
        super(context, R.style.changeDepositAccountDialog);
        this.context = context;
    }


    public ChangeDepositAccountDialog(Context context, AttributeSet attrs) {
        super(context, R.style.changeDepositAccountDialog);
        this.context = context;


    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_change_deposit_account);

        setCancelable(false);

        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);


        /**
         * 屏幕宽屏
         */


        initWindow();




        //初始化界面控件
        initView();

        //初始化界面控件的事件
        initListener();


        //初始化界面数据
        initData();


    }


    /**
     * 设置dialog宽度全部充满
     */
    private void initWindow() {

        Window win = this.getWindow();
        win.getDecorView().setPadding(0,0,0,0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        win.setAttributes(lp);

    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initListener() {
        tv_cancel.setOnClickListener(this);
        lv_pay_channel.setOnItemClickListener(this);
    }




    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {

        changeDepositAccountDialogAdapter.setList(payChannelInfos);


    }




    /**
     * 初始化界面控件
     */
    private void initView() {


        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        lv_pay_channel = (ListView) findViewById(R.id.lv_pay_channel);
        changeDepositAccountDialogAdapter = new ChangeDepositAccountDialogAdapter(context);
        lv_pay_channel.setAdapter(changeDepositAccountDialogAdapter);
    }


    @Override
    public void onClick(View v) {


        this.dismiss();

        switch (v.getId()) {

            case R.id.tv_cancel:  //关闭dialog
                break;
        }
    }


    /**
     * 点击事件
     */





    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        this.dismiss();

        PayChannelInfo payChannelInfo = changeDepositAccountDialogAdapter.getList().get(position);

        if(null != payChannelInfo && null != depositChangeAccountChannelCallBack) {

            switch (payChannelInfo.getChannel_id()) {


                case ConstLocalData.ALIPAY:
                    depositChangeAccountChannelCallBack.doAliChangeDepositAccountCallBack(payChannelInfo);
                    break;

                case ConstLocalData.WX:
                    depositChangeAccountChannelCallBack.doWxChangeDepositAccountCallBack(payChannelInfo);
                    break;
            }



        }


    }
}
