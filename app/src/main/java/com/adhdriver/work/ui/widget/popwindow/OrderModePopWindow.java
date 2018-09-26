package com.adhdriver.work.ui.widget.popwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.OrderModePopwindowCallBack;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2017/7/7.
 * 类描述  订单模式的popWindow
 * 版本
 */

public class OrderModePopWindow extends PopupWindow implements View.OnClickListener {


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

    private TextView tv_mode_same_city;//同城模式
    private TextView tv_mode_across_city;//跨城模式
    private TextView tv_mode_all; //全部模式


    private OrderModePopwindowCallBack orderModePopwindowCallBack; //调用系统相册设置图片的回调函数

    private int popupHeight;
    private int popupWidth;


    public void setOrderModePopwindowCallBack(OrderModePopwindowCallBack orderModePopwindowCallBack) {
        this.orderModePopwindowCallBack = orderModePopwindowCallBack;
    }

    public OrderModePopWindow(Context context) {
        super(context);


        weakReference = new WeakReference<Context>(context);

        this.inflater = LayoutInflater.from(weakReference.get());
        initViews();
        initListener();

    }


    private void initViews() {


        view = this.inflater.inflate(R.layout.popwindow_order_mode, null);



        tv_mode_same_city = (TextView) view.findViewById(R.id.tv_mode_same_city);//同城模式
        tv_mode_across_city = (TextView) view.findViewById(R.id.tv_mode_across_city);//跨城模式
        tv_mode_all = (TextView) view.findViewById(R.id.tv_mode_all); //全部模式

        this.setContentView(view);

        ColorDrawable colorDrawable = new ColorDrawable(0x0000000);

        this.setBackgroundDrawable(colorDrawable);

//        this.setWidth();
//        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setOutsideTouchable(true); //这一步要在showAsDropDown之前调用
        this.setFocusable(true);
        this.setAnimationStyle(R.style.popwindowDepositePassOperateStyle);


        //获取自身的长宽高
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        popupHeight = view.getMeasuredHeight();
        popupWidth = view.getMeasuredWidth();

    }

    private void initListener() {

        tv_mode_same_city.setOnClickListener(this);
        tv_mode_across_city.setOnClickListener(this);
        tv_mode_all.setOnClickListener(this);

    }


    @Override
    public boolean isShowing() {


        boolean type = super.isShowing();

        Log.i("payPop", "isSHowIng:" + type);


        if (type) {


            backgroundAlpha((Activity) weakReference.get(), 1f);

        } else {

            backgroundAlpha((Activity) weakReference.get(), 0.5f);
        }


        return type;
    }

    @Override
    public void onClick(View v) {

        OrderModePopWindow.this.dismiss();
        switch (v.getId()) {

            case R.id.tv_mode_same_city:


                if (null != orderModePopwindowCallBack) {

                    orderModePopwindowCallBack.orderModeSameCityClick();

                }

                break;

            case R.id.tv_mode_across_city:

                if (null != orderModePopwindowCallBack) {

                    orderModePopwindowCallBack.orderModeAcrossCityClick();

                }
                break;

            case R.id.tv_mode_all:

                if (null != orderModePopwindowCallBack) {

                    orderModePopwindowCallBack.orderModeAllClick();

                }
                break;
        }
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


    public void showUp(View v) {
        //获取需要在其上方显示的控件的位置信息
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        //在控件上方显示
        showAtLocation(v, Gravity.NO_GRAVITY, (location[0] + v.getWidth() / 2) - popupWidth / 2, location[1] - popupHeight);
    }


}
