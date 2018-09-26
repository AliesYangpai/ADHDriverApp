package com.adhdriver.work.ui.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.DialogRedPacketCallBack;
import com.adhdriver.work.constant.ConstHz;


/**
 * Created by Administrator on 2017/7/10.
 * 类描述
 * 版本 红包的dialog
 */

public class RedPacketDialog extends AlertDialog implements View.OnClickListener {


    /**
     * 顶部按钮
     */
    private RelativeLayout rl_red_packet_big_bg; //最外层背景

    /**
     * 瓜分相关
     */
    private RelativeLayout rl_carve_up_about;//瓜分相关
    private TextView tv_jackpot_count;//奖池金额
    private TextView tv_award_person;//已领奖人数
    private TextView tv_start_carve_up;//立即瓜分


    /**
     * 打开查看
     */


    private RelativeLayout rl_rp_show_packet_about;//红包袋相关
    private ImageView iv_rp_packet;//红包袋图片
    private TextView tv_start_to_check;//立即查看


    /**
     * 数据相关
     *
     * @param context
     */

    private String mJackPool;
    private String mAwardPersion;


    /**
     * 回调接口
     * @param context
     */

    private DialogRedPacketCallBack dialogRedPacketCallBack;

    public void setDialogRedPacketCallBack(DialogRedPacketCallBack dialogRedPacketCallBack) {
        this.dialogRedPacketCallBack = dialogRedPacketCallBack;
    }

    public RedPacketDialog(Context context) {
        super(context, R.style.RegDeny);
    }


    public RedPacketDialog(Context context, String mJackPool, String mAwardPersion) {
        super(context, R.style.RegDeny);
        this.mJackPool = mJackPool;
        this.mAwardPersion = mAwardPersion;
    }


    public String getmJackPool() {
        return mJackPool;
    }

    public void setmJackPool(String mJackPool) {
        this.mJackPool = mJackPool;
    }

    public String getmAwardPersion() {
        return mAwardPersion;
    }

    public void setmAwardPersion(String mAwardPersion) {
        this.mAwardPersion = mAwardPersion;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_red_packet);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);

//        setCancelable(false);//点击backpress不允许消失

        //初始化界面控件
        initView();

        //初始化界面控件的事件
        initListener();

        //初始化界面数据
        initData();


    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initListener() {
        /**
         * 底部按钮
         */
        tv_start_carve_up.setOnClickListener(this); //立即瓜分
        tv_start_to_check.setOnClickListener(this);//立即查看

    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {

          tv_jackpot_count.setText(mJackPool+ ConstHz.RMB_UNIT);//奖池金额
          tv_award_person.setText(mAwardPersion+ConstHz.REN);//已领奖人数



    }


    /**
     * 初始化界面控件
     */
    private void initView() {

        /**
         * 顶部按钮
         */
        rl_red_packet_big_bg = (RelativeLayout) findViewById(R.id.rl_red_packet_big_bg); //最外层背景

        /**
         *  瓜分相关
         */
        rl_carve_up_about = (RelativeLayout) findViewById(R.id.rl_carve_up_about);//瓜分相关
        tv_jackpot_count = (TextView) findViewById(R.id.tv_jackpot_count);//奖池金额
        tv_award_person = (TextView) findViewById(R.id.tv_award_person);//已领奖人数
        tv_start_carve_up = (TextView) findViewById(R.id.tv_start_carve_up);//立即瓜分


        /**
         * 打开查看
         */


        rl_rp_show_packet_about = (RelativeLayout) findViewById(R.id.rl_rp_show_packet_about);//红包袋相关
        iv_rp_packet = (ImageView) findViewById(R.id.iv_rp_packet);//红包袋图片
        tv_start_to_check = (TextView) findViewById(R.id.tv_start_to_check);//立即查看


    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.tv_start_carve_up:  //立即瓜分


                if(null != dialogRedPacketCallBack) {

                    dialogRedPacketCallBack.startCarveUpClick(
                            rl_red_packet_big_bg,
                             rl_carve_up_about,
                             tv_start_carve_up,
                             rl_rp_show_packet_about,
                             tv_start_to_check);

                }

                break;


            case R.id.tv_start_to_check:    //立即查看 直接跳转到 我的钱包界面

                if(null != dialogRedPacketCallBack) {

                    dialogRedPacketCallBack.startGetAwardBunds();

                }
                break;


        }
    }


}
