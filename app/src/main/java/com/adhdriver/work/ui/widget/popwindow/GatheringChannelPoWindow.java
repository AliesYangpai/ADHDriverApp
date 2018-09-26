package com.adhdriver.work.ui.widget.popwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.ChannelGatherSelectCallBack;
import com.adhdriver.work.constant.ConstTag;
import com.adhdriver.work.entity.driver.pay.PayChannelInfo;
import com.adhdriver.work.ui.adapter.forgridview.GatheringChannelAdapter;
import com.adhdriver.work.utils.ToastUtil;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Administrator on 2017/4/5 0005.
 * 类描述 授权渠道的popWindow
 * 版本
 */
public class GatheringChannelPoWindow extends PopupWindow implements AdapterView.OnItemClickListener{

    /**
     * 初始化配置
     */
    private Context context;

    private LayoutInflater inflater;


    private WeakReference<Context> weakReference;

    /**
     * 控件相关
     */

    private View view;


    private GatheringChannelAdapter  gatheringChannelAdapter;
    private GridView gv_pay_channel;







    private List<PayChannelInfo> payChannelInfos;



    private ChannelGatherSelectCallBack channelGatherSelectCallBack;


    public void setChannelGatherSelectCallBack(ChannelGatherSelectCallBack channelGatherSelectCallBack) {
        this.channelGatherSelectCallBack = channelGatherSelectCallBack;
    }

    private void initData() {

    }



    public GatheringChannelPoWindow(Context context) {
        super(context);

        weakReference = new WeakReference<Context>(context);

        this.inflater = LayoutInflater.from(weakReference.get());
        initViews();
        initListener();
        initData();

    }



    public GatheringChannelPoWindow(Context context, List<PayChannelInfo> payChannelInfos) {
        super(context);

        this.payChannelInfos = payChannelInfos;
        weakReference = new WeakReference<Context>(context);
        this.inflater = LayoutInflater.from(weakReference.get());
        initViews();
        initListener();
        initData();

    }


    public void setPayChannelInfos(List<PayChannelInfo> payChannelInfos) {
        this.payChannelInfos = payChannelInfos;
        gatheringChannelAdapter.setList(payChannelInfos);
    }

    private void initViews() {

        view = this.inflater.inflate(R.layout.popwindow_gather_channel, null);
        gv_pay_channel = (GridView) view.findViewById(R.id.gv_pay_channel);
        gatheringChannelAdapter = new GatheringChannelAdapter(weakReference.get());
        gv_pay_channel.setAdapter(gatheringChannelAdapter);
        gatheringChannelAdapter.setList(payChannelInfos);


        this.setContentView(view);
        ColorDrawable colorDrawable = new ColorDrawable(0x0000000);
        this.setBackgroundDrawable(colorDrawable);
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        this.setOutsideTouchable(true); //这一步要在showAsDropDown之前调用
        this.setFocusable(true);
        this.setAnimationStyle(R.style.popwindow_anim_style);


    }

    private void initListener() {

        gv_pay_channel.setOnItemClickListener(this);
    }







    @Override
    public boolean isShowing() {


        boolean type = super.isShowing();

        Log.i("payPop", "isSHowIng:" + type);


//        if (type) {
//
//            backgroundAlpha((Activity) weakReference.get(), 1f);
//
//        } else {
//
//            backgroundAlpha((Activity) weakReference.get(), 0.5f);
//        }
//

        return type;
    }




    /**
     * 背景色变暗
     */

    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        this.dismiss();

        PayChannelInfo payChannelInfo = gatheringChannelAdapter.getList().get(position);

        if(null != payChannelInfo && null!= channelGatherSelectCallBack) {



            channelGatherSelectCallBack.onChannelSelect( payChannelInfo.getChannel_id());



        }



    }
}
