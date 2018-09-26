package com.adhdriver.work.ui.widget.popwindow;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adhdriver.work.AiDaiHuoApplication;
import com.adhdriver.work.R;
import com.adhdriver.work.callback.OrderOverOfficeOperateCallBack;
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.constant.ConstSign;
import com.adhdriver.work.entity.driver.orders.AdditionService;
import com.adhdriver.work.entity.driver.orders.From;
import com.adhdriver.work.entity.driver.orders.Goods;
import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.entity.driver.orders.To;
import com.adhdriver.work.function.FunctionFee;
import com.adhdriver.work.function.FunctionPathPoint;
import com.adhdriver.work.test.TestContent;
import com.adhdriver.work.ui.adapter.forgridview.AdditionServiceAdapter;
import com.adhdriver.work.ui.widget.tagview.TagView;
import com.adhdriver.work.utils.SimCardUtil;
import com.adhdriver.work.utils.StringUtil;
import com.adhdriver.work.utils.ToastUtil;
import com.adhdriver.work.ui.widget.gridview.AdditionServiceGridView;
import com.adhdriver.work.ui.widget.swipbutton.OnActiveListener;
import com.adhdriver.work.ui.widget.swipbutton.SwipeButton;
import com.adhdriver.work.ui.widget.tagview.Tag;
import com.xyz.step.FlowViewVertical;

import org.feezu.liuli.timeselector.Utils.TextUtil;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Administrator on 2018/1/3.
 * 类描述
 * 版本
 */

public class OrderTakeBottomOverOfficePopWindow extends PopupWindow
        implements
        View.OnClickListener,
        OnActiveListener {


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


    private ImageView iv_userHead;     //用户头像
    private TextView tv_client_name;   //用户姓名
    private TextView tv_goods_name;    //货物名称
    private TextView tv_car_info;      //车辆信息
    private ImageView iv_call_client;  //电话
    private TagView tagview_sgin;      //额外服务
    private LinearLayout ll_goods_and_carinfo;//货物、车辆信息布局，目的是为了测量tag

    private RelativeLayout rl_middle_info;//中间布局


    private TextView tv_start_time;    //开始时间
    private FlowViewVertical fvl_pass_point;//途经点


    private TextView tv_bottom;         //底部按钮

    private SwipeButton swipe_btn;
    private TextView tv_price;//价格


    private Order currentOrder;

    private FunctionFee functionFee;
    private FunctionPathPoint functionPathPoint;
    private OrderOverOfficeOperateCallBack orderOverOfficeOperateCallBack;


    /**
     * 展开布局
     *
     * @param overOfficeArriveCallBack
     */
    private LinearLayout ll_inner;
    private AdditionServiceGridView gv_addition_service;
    private AdditionServiceAdapter additionServiceAdapter;
    private TextView tv_business_type;
    private TextView tv_goods_name_4;
    private TextView tv_goods_weight;
    private TextView tv_goods_volume;
    private LinearLayout ll_extra_service;


    public FlowViewVertical getFvl_pass_point() {
        return fvl_pass_point;
    }

    public TextView getTv_bottom() {
        return tv_bottom;
    }

    public SwipeButton getSwipe_btn() {
        return swipe_btn;
    }



    public void setOrderOverOfficeOperateCallBack(OrderOverOfficeOperateCallBack orderOverOfficeOperateCallBack) {
        this.orderOverOfficeOperateCallBack = orderOverOfficeOperateCallBack;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    private Tag getTag(String str, String textColor, String layoutColor, String layoutBoderColor) {


        Tag tag = new Tag(str);
        tag.tagTextColor = Color.parseColor(textColor);
        tag.layoutColor = Color.parseColor(layoutColor);
//or tag.background = this.getResources().getDrawable(R.drawable.custom_bg);
        tag.radius = 20f;
        tag.tagTextSize = 12f;
        tag.layoutBorderSize = 1f;
        tag.layoutBorderColor = Color.parseColor(layoutBoderColor);
        tag.isDeletable = false;
        return tag;


    }

    private void measuredWithAndSetData() {

        ViewTreeObserver vto = ll_goods_and_carinfo.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ll_goods_and_carinfo.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int maxWidth = ll_goods_and_carinfo.getWidth();
                Log.i("tgaViewSize", "MeasuredWidth" + maxWidth);
                tagview_sgin.setMaxWith(maxWidth);
                initData();
            }
        });
    }


    public OrderTakeBottomOverOfficePopWindow(Context context, Order order) {
        super(context);

        this.currentOrder = order;
        weakReference = new WeakReference<Context>(context);

        functionFee = new FunctionFee();
        functionPathPoint = new FunctionPathPoint();
        this.inflater = LayoutInflater.from(weakReference.get());
        initViews();
        initListener();

        measuredWithAndSetData();
    }


    private void initData() {


        Goods goods = currentOrder.getGoods();
        List<AdditionService> additionServices = currentOrder.getAddition_services();
        String overOfficeDriverDivide = functionFee.getOverOfficeDriverDivide(currentOrder);

        tv_goods_name.setText(goods.getDescription());


        if (null != additionServices && additionServices.size() > 0) {
            tagview_sgin.setVisibility(View.VISIBLE);
            doSetAdditionServiceToUi(additionServices);
        } else {
            tagview_sgin.setVisibility(View.GONE);
        }

        tv_price.setText(overOfficeDriverDivide);










        doSetStartAndEnd();


        doSetExpandData();

        doSetSwipeText();

    }



    private void doSetStartAndEnd() {
        From from = currentOrder.getFrom();
        To to = currentOrder.getTo();

        String fromAddress = from.getStreet_address();
        String toAddress = to.getStreet_address();

        String[] arry = new String[]{toAddress,fromAddress};


        String departTime =  currentOrder.getDeparture_time();
        String arrival_time = currentOrder.getArrival_time();

        if(null != departTime && null != arrival_time) {
            fvl_pass_point.setProgress(0,arry.length,arry,null);
            return;
        }

        if(null == departTime ) {

            fvl_pass_point.setProgress(arry.length,arry.length,arry,null);
            return;

        }

        if(null != departTime && null == arrival_time) {

            fvl_pass_point.setProgress(arry.length-1,arry.length,arry,null);
            return;
        }



    }




    private void doSetSwipeText() {

        String arriveTime = currentOrder.getArrival_time();
        String departureTime = currentOrder.getDeparture_time();

        if(TextUtil.isEmpty(departureTime)) {

            swipe_btn.setText(ConstLocalData.SWIPE_GO);
            return;
        }

        if(!TextUtil.isEmpty(departureTime) && TextUtil.isEmpty(arriveTime)) {

            swipe_btn.setText(ConstLocalData.SWIPE_ARRIVE);
            return;
        }

        if(!TextUtil.isEmpty(arriveTime)) {


            swipe_btn.setVisibility(View.GONE);
            tv_bottom.setVisibility(View.VISIBLE);
        }


    }

    private void doSetExpandData() {

        Goods goods = currentOrder.getGoods();

        tv_business_type.setText(StringUtil.getOrderTypeHz(currentOrder.getBusiness_type()));
        tv_goods_name_4.setText(goods.getDescription());
        tv_goods_weight.setText(goods.getWeight() + ConstSign.WEIGHT_UNIT_KG);
        tv_goods_volume.setText(goods.getGoods_volume() + ConstSign.VOLUME_UNIT);

        List<AdditionService> additionServices = currentOrder.getAddition_services();
        if (null != additionServices && additionServices.size() > 0) {

            additionServiceAdapter.setList(additionServices);

        } else {

            ll_extra_service.setVisibility(View.GONE);
        }
    }

    private void doSetAdditionServiceToUi(List<AdditionService> additionServices) {

        for (AdditionService additionService : additionServices) {

            tagview_sgin.addTag(getTag(setDefultText(additionService.getRemark()), "#FFFFFFFF", "#ff5613", "#ff5613"));

        }


    }


    private String setDefultText(String remark) {


        String text = "额外服务";

        if (!TextUtil.isEmpty(remark)) {

            text = remark;

        }
        return text;

    }




    private void initViews() {


        view = this.inflater.inflate(R.layout.popwindow_order_take_over_office_bottom, null);


        iv_userHead = (ImageView) view.findViewById(R.id.iv_userHead);     //用户头像
        tv_client_name = (TextView) view.findViewById(R.id.tv_client_name);   //用户姓名
        tv_goods_name = (TextView) view.findViewById(R.id.tv_goods_name);    //货物名称
        tv_car_info = (TextView) view.findViewById(R.id.tv_car_info);      //车辆信息
        ll_goods_and_carinfo = (LinearLayout) view.findViewById(R.id.ll_goods_and_carinfo);//货物、车辆信息布局，目的是为了测量tag
        iv_call_client = (ImageView) view.findViewById(R.id.iv_call_client);  //电话
        tagview_sgin = (TagView) view.findViewById(R.id.tagview_sgin);      //额外服务


        rl_middle_info = (RelativeLayout) view.findViewById(R.id.rl_middle_info);//中间布局


        ll_inner = (LinearLayout) view.findViewById(R.id.ll_inner);


        gv_addition_service = (AdditionServiceGridView) view.findViewById(R.id.gv_addition_service);
        additionServiceAdapter = new AdditionServiceAdapter(weakReference.get());
        gv_addition_service.setAdapter(additionServiceAdapter);

        tv_start_time = (TextView) view.findViewById(R.id.tv_start_time);    //开始时间
        fvl_pass_point = (FlowViewVertical) view.findViewById(R.id.fvl_pass_point);//途经点
        tv_bottom = (TextView) view.findViewById(R.id.tv_bottom);         //底部按钮

        swipe_btn = (SwipeButton) view.findViewById(R.id.swipe_btn);
        tv_price = (TextView) view.findViewById(R.id.tv_price);//价格


        /**
         * 展开布局
         */


        tv_business_type = (TextView) view.findViewById(R.id.tv_business_type);
        tv_goods_name_4 = (TextView) view.findViewById(R.id.tv_goods_name_4);
        tv_goods_weight = (TextView) view.findViewById(R.id.tv_goods_weight);
        tv_goods_volume = (TextView) view.findViewById(R.id.tv_goods_volume);
        ll_extra_service = (LinearLayout) view.findViewById(R.id.ll_extra_service);


        this.setContentView(view);

        ColorDrawable colorDrawable = new ColorDrawable(0x0000000);

        this.setBackgroundDrawable(colorDrawable);

        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);

        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        this.setOutsideTouchable(false); //这一步要在showAsDropDown之前调用

        this.setFocusable(false);

        this.setAnimationStyle(R.style.popwindowOrderTakeBottom);


    }


    private void initListener() {
        rl_middle_info.setOnClickListener(this);
        iv_call_client.setOnClickListener(this);
        tv_bottom.setOnClickListener(this);

        swipe_btn.setOnActiveListener(this);
//        swipe_btn.setOnStateChangeListener(this);
    }


    @Override
    public boolean isShowing() {


        boolean type = super.isShowing();

        Log.i("payPop", "isSHowIng:" + type);


        return type;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_call_client:




                startToCallReciver(currentOrder.getTo().getPhone());

                break;

            case R.id.rl_middle_info:


                if (ll_inner.getVisibility() == View.VISIBLE) {

                    ll_inner.setVisibility(View.GONE);
                } else {

                    ll_inner.setVisibility(View.VISIBLE);
                }

                break;


        }
    }


    /**
     * 开始拨打电话
     * //6.26新加入
     */
    private void startToCallReciver(String callPhone) {


        /**
         * 打电话需要获取系统权限，需要到AndroidManifest.xml里面配置权限
         * <uses-permission android:name="android.permission.CALL_PHONE"/>
         */


        if (TextUtils.isEmpty(callPhone)) {


            ToastUtil.showMsg(weakReference.get().getApplicationContext(), R.string.have_not_get_contact);

            return;

        }

        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + callPhone));
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }


        if (!SimCardUtil.hasSimCard()) {


            ToastUtil.showMsg(weakReference.get().getApplicationContext(), R.string.not_sim_card);
            return;
        }

        weakReference.get().startActivity(intent);
    }



    @Override
    public void onActive() {


        String departure_time = currentOrder.getDeparture_time();
        String arrival_time = currentOrder.getArrival_time();
        if (TextUtil.isEmpty(departure_time)) {

            if (null != orderOverOfficeOperateCallBack) {
                orderOverOfficeOperateCallBack.onOverOfficeDepart(currentOrder);
            }
            return;
        }


        if (!TextUtil.isEmpty(departure_time) && TextUtil.isEmpty(arrival_time)) {
            if (null != orderOverOfficeOperateCallBack) {
                orderOverOfficeOperateCallBack.onOverOfficeArrive(currentOrder);
            }

            return;
        }


    }




}
