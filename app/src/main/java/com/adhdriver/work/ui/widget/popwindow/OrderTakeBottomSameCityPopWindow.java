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
import com.adhdriver.work.callback.OrderFullLoadOperateCallBack;
import com.adhdriver.work.callback.OrderSameCityOperateCallBack;
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.constant.ConstSign;
import com.adhdriver.work.entity.driver.orders.AdditionService;
import com.adhdriver.work.entity.driver.orders.Fee;
import com.adhdriver.work.entity.driver.orders.From;
import com.adhdriver.work.entity.driver.orders.Goods;
import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.entity.driver.orders.OrderTagEntity;
import com.adhdriver.work.entity.driver.orders.PathPoint;
import com.adhdriver.work.entity.driver.orders.To;
import com.adhdriver.work.entity.driver.orders.Vehicle;
import com.adhdriver.work.function.FunctionFee;
import com.adhdriver.work.function.FunctionPathPoint;
import com.adhdriver.work.test.TestContent;
import com.adhdriver.work.ui.adapter.forgridview.AdditionServiceAdapter;
import com.adhdriver.work.ui.widget.gridview.AdditionServiceGridView;
import com.adhdriver.work.ui.widget.swipbutton.OnActiveListener;
import com.adhdriver.work.ui.widget.swipbutton.SwipeButton;
import com.adhdriver.work.ui.widget.tagview.Tag;
import com.adhdriver.work.ui.widget.tagview.TagView;
import com.adhdriver.work.utils.ImgUtil;
import com.adhdriver.work.utils.SimCardUtil;
import com.adhdriver.work.utils.StringUtil;
import com.adhdriver.work.utils.ToastUtil;
import com.adhdriver.work.utils.VehicleUtil;
import com.xyz.step.FlowViewVertical;

import org.feezu.liuli.timeselector.Utils.TextUtil;

import java.lang.ref.WeakReference;
import java.util.List;

import static android.support.v4.content.PermissionChecker.checkSelfPermission;

/**
 * Created by Administrator on 2018/1/3.
 * 类描述  用于整车的底部popwindow
 * 版本
 */

public class OrderTakeBottomSameCityPopWindow extends PopupWindow
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


    private SwipeButton swipe_btn;
    private TextView tv_price;//价格


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


    /**
     * 数据相关
     *
     * @param str
     * @param textColor
     * @param layoutColor
     * @param layoutBoderColor
     * @return
     */


    private OrderTagEntity orderTagEntity;
    private Order order;

    public void setOrder(Order order) {
        this.order = order;
    }

    private FunctionPathPoint functionPathPoint;


    public FlowViewVertical getFvl_pass_point() {
        return fvl_pass_point;
    }

    public void setOrderTagEntity(OrderTagEntity orderTagEntity) {
        this.orderTagEntity = orderTagEntity;
    }

    private FunctionFee functionFee;

    public SwipeButton getSwipe_btn() {
        return swipe_btn;
    }

    public OrderTagEntity getOrderTagEntity() {
        return orderTagEntity;
    }

    private OrderSameCityOperateCallBack orderSameCityOperateCallBack;


    public void setOrderSameCityOperateCallBack(OrderSameCityOperateCallBack orderSameCityOperateCallBack) {
        this.orderSameCityOperateCallBack = orderSameCityOperateCallBack;
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


    public OrderTakeBottomSameCityPopWindow(Context context, Order order, OrderTagEntity orderTagEntity) {
        super(context);

        this.order = order;
        this.orderTagEntity = orderTagEntity;

        functionFee = new FunctionFee();
        this.functionPathPoint = new FunctionPathPoint();
        weakReference = new WeakReference<Context>(context);

        this.inflater = LayoutInflater.from(weakReference.get());
        initViews();
        initListener();

        measuredWithAndSetData();
    }


    private void initData() {


        doSetBaseData();

        doSetExpandData();


        doSetDynamicData();


    }

    private void doSetBaseData() {


        String avatar = order.getUser_avatar();
        String userName = order.getUser_name();

        Vehicle vehicle = order.getVehicle();
        String bookTime = order.getBook_time();
        Fee fee = order.getFee();

        ImgUtil.getInstance().getRadiusImgFromNetByUrl(avatar, iv_userHead, R.drawable.img_default_client_head_round, 120);

        tv_goods_name.setText(vehicle.getCategory_name());
        tv_client_name.setText(userName);
        tv_car_info.setText(vehicle.getLength() + ConstSign.METER + VehicleUtil.getVehicleTypeDescriptionHz(vehicle.getCategory_name()));
        tv_start_time.setText(bookTime);

        tv_price.setText(String.valueOf(functionFee.getSameCityDriverDivide(fee, order.getDriver_proporition())));
    }


    private void doSetExpandData() {


        tv_business_type.setText(StringUtil.getOrderTypeHz(order.getBusiness_type()));


        List<AdditionService> additionServices = order.getAddition_services();
        if (null != additionServices && additionServices.size() > 0) {
            additionServiceAdapter.setList(additionServices);
        } else {
            ll_extra_service.setVisibility(View.GONE);
        }
    }

    private void doSetDynamicData() {


        doSetPathPointData();


        int tag = orderTagEntity.getTag();
        switch (tag) {

            case ConstLocalData.ARRIVE_HAD_PAID://到达已付款

                swipe_btn.setText(ConstLocalData.SWIPE_SEND_PHONE_CODE); //滑动发送验证码
                break;

            case ConstLocalData.ARRIVE_NOT_PAID: //到达未付款

                swipe_btn.setText(ConstLocalData.SWIPE_PAY_TO_ME);

                break;
            case ConstLocalData.DEPART_NOT_ARRIVE://出发未到达


                doCheckNextText();


                break;

            case ConstLocalData.DEPART_NOT://未出发

                swipe_btn.setText(ConstLocalData.SWIPE_GO); //滑动出发
                break;

        }
    }


    private void doCheckNextText() {

        List<PathPoint> pathPoint = order.getPath_points();

        if (functionPathPoint.isHasPassPoint(pathPoint)) {

            int step = functionPathPoint.currentDriverPathPointStep(pathPoint);


            int count = functionPathPoint.currentDriverNotArrivePathPointCount(pathPoint);


            if (count == 0) {

                swipe_btn.setText(ConstLocalData.SWIPE_ARRIVE); //滑动到达
            } else {

                swipe_btn.setText(ConstLocalData.SWIPE_ARRIVE_NEXT); //滑动到达下一个途经点
            }

        } else {
            swipe_btn.setText(ConstLocalData.SWIPE_ARRIVE); //滑动到达

        }

    }


    public void doSetPathPointData() {


        List<PathPoint> pathPoints = order.getPath_points();


        From from = order.getFrom();

        To to = order.getTo();

        if (functionPathPoint.isHasPassPoint(pathPoints)) {

            String[] arry = functionPathPoint.getHasPathPointAllWay(from, to, pathPoints);

            int count = functionPathPoint.currentDriverNotArrivePathPointCount(pathPoints);

            if (count == 0) {
                fvl_pass_point.setProgress(0, arry.length, arry, null);
            } else {

                if(TextUtil.isEmpty(order.getDeparture_time())) {

                    fvl_pass_point.setProgress(arry.length-count+1, arry.length, arry, null);

                }else {

                    fvl_pass_point.setProgress(count+1, arry.length, arry, null);

                }

            }


        } else {

            doSetStartAndEnd();
        }


    }


    private void doSetStartAndEnd() {
        From from = order.getFrom();
        To to = order.getTo();

        String fromAddress = from.getStreet_address();
        String toAddress = to.getStreet_address();

        String[] arry = new String[]{toAddress, fromAddress};

        fvl_pass_point.setProgress(arry.length - 1, arry.length, arry, null);


    }

    private void initViews() {


        view = this.inflater.inflate(R.layout.popwindow_order_take_sane_city_bottom, null);


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
                startToCallReciver(order.getTo().getPhone());
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
//        if (checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }


        if (!SimCardUtil.hasSimCard()) {


            ToastUtil.showMsg(weakReference.get().getApplicationContext(), R.string.not_sim_card);
            return;
        }

        weakReference.get().startActivity(intent);
    }



    @Override
    public void onActive() {

        int tag = orderTagEntity.getTag();
        switch (tag) {

            case ConstLocalData.ARRIVE_HAD_PAID://到达已付款

                //滑动发送验证码
                if (null != orderSameCityOperateCallBack) {
                    orderSameCityOperateCallBack.onSameCityGetPhoneCode(order);

                }
                break;

            case ConstLocalData.DEPART_NOT_ARRIVE://出发未到达

                //滑动到达


                doSwipMethod();


                break;

            case ConstLocalData.DEPART_NOT://未出发
                //滑动到达
                if (null != orderSameCityOperateCallBack) {
                    orderSameCityOperateCallBack.onSameCityDepart(order);

                }
                break;


            case ConstLocalData.ARRIVE_NOT_PAID://到达未付款
                if (null != orderSameCityOperateCallBack) {
                    orderSameCityOperateCallBack.onSameCityGetPayChannel();
                }
        }
    }


    private void doSwipMethod() {

        List<PathPoint> pathPoints = order.getPath_points();

        if (functionPathPoint.isHasPassPoint(pathPoints)) {



            int count = functionPathPoint.currentDriverNotArrivePathPointCount(pathPoints);



            if(count == 0) {

                if (null != orderSameCityOperateCallBack) {
                    orderSameCityOperateCallBack.onSameCityArrive(order);
                }
            }else {

                if (null != orderSameCityOperateCallBack) {
                    orderSameCityOperateCallBack.onSameCityArriveNext(order);

                } //滑动到达下一个途经点
            }


        } else {
            if (null != orderSameCityOperateCallBack) {
                orderSameCityOperateCallBack.onSameCityArrive(order);

            }//滑动到达

        }

    }


}
