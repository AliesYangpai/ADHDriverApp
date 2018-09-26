package com.adhdriver.work.ui.activity.driver;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.BiddingDialogCallBack;
import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.constant.ConstSign;
import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.entity.driver.orders.AdditionService;
import com.adhdriver.work.entity.driver.orders.City;
import com.adhdriver.work.entity.driver.orders.County;
import com.adhdriver.work.entity.driver.orders.From;
import com.adhdriver.work.entity.driver.orders.Goods;
import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.entity.driver.orders.Province;
import com.adhdriver.work.entity.driver.orders.To;
import com.adhdriver.work.entity.driver.orders.Vehicle;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.presenter.driver.PresenterDriverOrderDetailNotTakeBidding;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.activity.common.LoginActivity;
import com.adhdriver.work.ui.adapter.forgridview.AdditionServiceAdapter;
import com.adhdriver.work.ui.iview.driver.IOrderDetailNotTakeBiddingView;
import com.adhdriver.work.utils.ImgUtil;
import com.adhdriver.work.utils.SpUtil;
import com.adhdriver.work.utils.StringUtil;
import com.adhdriver.work.utils.ToastUtil;
import com.adhdriver.work.utils.VehicleUtil;
import com.adhdriver.work.ui.widget.dialog.BiddingDialog;
import com.adhdriver.work.ui.widget.gridview.AdditionServiceGridView;
import com.adhdriver.work.ui.widget.tagview.Tag;
import com.adhdriver.work.ui.widget.tagview.TagView;
import com.xyz.step.FlowViewVertical;

import java.util.List;


/**
 * 未接单时的竞价订单详情界面
 */
public class OrderDetailNotTakeBiddingActivity extends BaseActivity<IOrderDetailNotTakeBiddingView, PresenterDriverOrderDetailNotTakeBidding>
        implements View.OnClickListener,
        IOrderDetailNotTakeBiddingView,
        BiddingDialogCallBack {


    private PresenterDriverOrderDetailNotTakeBidding presenterDriverOrderDetailNotTakeBidding;


    /**
     * title
     *
     * @param savedInstanceState
     */
    private ImageView iv_common_back;


    /**
     * top
     */
    private ImageView iv_user_head;
    private TextView tv_user_name;
    private LinearLayout ll_bidding_about;
    private TextView tv_car_lenth;
    private TextView tv_catogry;


    private TagView tagview_sgin;


    /**
     * 货物信息
     *
     * @param savedInstanceState
     */

    private TextView tv_order_time; //时间
    private TextView tv_business_type; //货运类型
    private TextView tv_goods_name; //货物名称
    private TextView tv_goods_weight; //货物重量
    private TextView tv_goods_volume; //货物体积


    /**
     * 途径地
     *
     * @param savedInstanceState
     */

    private FlowViewVertical fvl_pass_point;//途径地


    /**
     * 额外服务
     *
     * @param savedInstanceState
     */

    private LinearLayout ll_extra_service;
    private AdditionServiceGridView gv_addition_service;
    private AdditionServiceAdapter additionServiceAdapter;


    /**
     * 底部按钮
     */
    private TextView tv_not_take_bottom;
private TextView tv_not_take_bidding;

    /**
     * dialog相关
     *
     * @return
     */

    private BiddingDialog biddingDialog;

    /**
     * 数据相关
     *
     * @param savedInstanceState
     */

    private Order currentOrder;


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



    private boolean isVisitor() {

        return SpUtil.getInstance().getBoolenValue(ConstSp.SP_KEY_VISITOR, ConstSp.SP_VALUE.DEFAULT_TRUE_VISITOR);

    }

    private void doTestPassPoint() {


//        String[] title = new String[]{"陕西省显示莲湖区丰庆路","陕西省显示黄去头村","陕西省显示妇幼医院","南三环路口","城南客运站"};
        String[] title = new String[]{"陕西省显示莲湖区丰庆路", "陕西省显示黄去头村"};
        fvl_pass_point.setProgress(title.length, title.length, title, null);

    }


    private void doTestAddTag() {

//        tagview_sgin.addTag(getTag("120km", "#5fd77e", "#FFFFFFFF", "#5fd77e"));
//        tagview_sgin.addTag(getTag("过路费:200元", "#5fd77e", "#FFFFFFFF", "#5fd77e"));
//        tagview_sgin.addTag(getTag("代收货款", "#FFFFFFFF", "#ff5613", "#ff5613"));
//
//        tagview_sgin.addTag(getTag("打印回执单", "#FFFFFFFF", "#ff5613", "#ff5613"));
//        tagview_sgin.addTag(getTag("传真回执单", "#FFFFFFFF", "#ff5613", "#ff5613"));
        tagview_sgin.addTag(getTag("传真回执单", "#FFFFFFFF", "#ff5613", "#ff5613"));


    }


    public void doSetPassPoint(Order order) {

        From from = order.getFrom();//wen我的orders
        Province fromProvince = from.getProvince();//省
        City fromCity = from.getCity();//市
        County fromCounty = from.getCounty();//区


        To to = order.getTo();
        Province toProvince = to.getProvince();//省
        City toCity = to.getCity();//市
        County toCounty = to.getCounty();//区


        String startPlace = fromProvince.getName() + fromCity.getName() + fromCounty.getName() + from.getStreet_address();
        String toPlace = toProvince.getName() + toCity.getName() + toCounty.getName() + to.getStreet_address();
        String[] title = new String[]{toPlace,startPlace};

        fvl_pass_point.setProgress(title.length, title.length, title, null);
    }

    public void doAddAdditionServiceToTagSgin(List<AdditionService> additionServices) {

        for (AdditionService additionService : additionServices) {
            tagview_sgin.addTag(getTag(additionService.getRemark(), "#FFFFFFFF", "#ff5613", "#ff5613"));
        }


    }


    private void measuredWithAndSetData(final List<AdditionService> additionServices) {

        ViewTreeObserver vto = ll_bidding_about.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ll_bidding_about.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int maxWidth = ll_bidding_about.getWidth();
                Log.i("tgaViewSize", "MeasuredWidth" + maxWidth);
                tagview_sgin.setMaxWith(maxWidth);

                doAddAdditionServiceToTagSgin(additionServices);

            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail_not_take_bidding);


        presenterDriverOrderDetailNotTakeBidding.doSetOrderInfoToUi(currentOrder);


//        measuredWithAndSetData();


    }

    @Override
    protected PresenterDriverOrderDetailNotTakeBidding creatPresenter() {
        if (null == presenterDriverOrderDetailNotTakeBidding) {
            presenterDriverOrderDetailNotTakeBidding = new PresenterDriverOrderDetailNotTakeBidding(this);
        }
        return presenterDriverOrderDetailNotTakeBidding;
    }

    @Override
    protected void initViews() {


        /**
         * title
         * @param savedInstanceState
         */

        iv_common_back = findADHViewById(R.id.iv_common_back);

        /**
         * top
         */

        /**
         * top
         */
        iv_user_head = findADHViewById(R.id.iv_user_head);
        tv_user_name = findADHViewById(R.id.tv_user_name);
        ll_bidding_about = findADHViewById(R.id.ll_bidding_about);
        tv_car_lenth = findADHViewById(R.id.tv_car_lenth);
        tv_catogry = findADHViewById(R.id.tv_catogry);
        tagview_sgin = findADHViewById(R.id.tagview_sgin);

        /**
         * 货物信息
         * @param savedInstanceState
         */

        tv_order_time = findADHViewById(R.id.tv_order_time); //时间
        tv_business_type = findADHViewById(R.id.tv_business_type); //货运类型
        tv_goods_name = findADHViewById(R.id.tv_goods_name); //货物名称
        tv_goods_weight = findADHViewById(R.id.tv_goods_weight); //货物重量
        tv_goods_volume = findADHViewById(R.id.tv_goods_volume); //货物体积


        /**
         * 途径地
         * @param savedInstanceState
         */

        fvl_pass_point = findADHViewById(R.id.fvl_pass_point);//途径地


        /**
         * 额外服务
         * @param savedInstanceState
         */

        ll_extra_service = findADHViewById(R.id.ll_extra_service);
        gv_addition_service = findADHViewById(R.id.gv_addition_service);
        additionServiceAdapter = new AdditionServiceAdapter(this);
        gv_addition_service.setAdapter(additionServiceAdapter);


        /**
         * 底部按钮
         */
        tv_not_take_bottom = findADHViewById(R.id.tv_not_take_bottom);
        tv_not_take_bottom.setText(getString(R.string.click_to_bidding));


        tv_not_take_bidding = findADHViewById(R.id.tv_not_take_bidding);


    }

    @Override
    protected void initListener() {
        iv_common_back.setOnClickListener(this);
        tv_not_take_bottom.setOnClickListener(this);
    }

    @Override
    protected void processIntent() {


        Intent intent = this.getIntent();
        if (null != intent) {
            Bundle bundle = intent.getExtras();
            if (null != bundle) {

                currentOrder = (Order) bundle.getSerializable(ConstIntent.BundleKEY.DELIVER_ORDER);
            }
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_common_back:
                dofinishItself();
                break;

            case R.id.tv_not_take_bottom:



                if(isVisitor()) {
                    openActivity(LoginActivity.class,null);
                    return;
                }


                if (null == biddingDialog) {

                    biddingDialog = new BiddingDialog(OrderDetailNotTakeBiddingActivity.this);
                    biddingDialog.setBiddingDialogCallBack(this);
                    biddingDialog.setOrder(currentOrder);
                }
                biddingDialog.show();

                break;
        }
    }

    @Override
    public void showLoadingDialog() {
        showLoadDialog();
    }

    @Override
    public void dismissLoadingDialog() {
        dismissLoadDialog();
    }

    @Override
    public void onDataBackFail(int code, String errorMsg) {

        ToastUtil.showMsg(getApplicationContext(), errorMsg);
    }

    @Override
    public void onDataBackSuccessForOrderDetial(Order order) {


        /**
         * 显示订单详情
         */


    }

    @Override
    public void onDataBackSuccessForBiddingSuccess() {
        /**
         * 竞价成功后界面跳转
         */



            if(null != biddingDialog && biddingDialog.isShowing()) {

                biddingDialog.dismiss();

            }


            dofinishItself();
    }

    @Override
    public void onDataBackSuccessForGetOrderInfoFullLoad(Order order) {

        /**
         * 中间的
         */

        From from = order.getFrom();
        Province fromProvince = from.getProvince();
        City fromCity = from.getCity();
        County fromCounty = from.getCounty();


        To to = order.getTo();
        Province toProvince = to.getProvince();
        City toCity = to.getCity();
        County toCounty = to.getCounty();
        Goods goods = order.getGoods();
        Vehicle vehicle = order.getVehicle();


        tv_user_name.setText(order.getUser_name()); //用户民
        tv_car_lenth.setText(vehicle.getLength() + ConstSign.METER);
        tv_catogry.setText(VehicleUtil.getVehicleTypeDescriptionHz(vehicle.getCategory_name()));
        tv_goods_name.setText(goods.getDescription());  //货物名称
        tv_order_time.setText(order.getCreate_time()); //用车时间
        tv_goods_volume.setText(goods.getGoods_volume() + ConstSign.VOLUME_UNIT);//货物体积
        tv_business_type.setText(StringUtil.getOrderTypeHz(order.getBusiness_type()));


        doSetPassPoint(order);


        ImgUtil.getInstance().getRadiusImgFromNetByUrl(order.getUser_avatar(), iv_user_head, R.drawable.img_default_client_head_round, ConstLocalData.PIC_ROUNT_RADIUS);




        doShowOrHideView();


    }


    private void doShowOrHideView() {

        if(isMineBidding(currentOrder)) {


            tv_not_take_bottom.setVisibility(View.GONE);
            tv_not_take_bidding.setVisibility(View.VISIBLE);
        }else {

            tv_not_take_bidding.setVisibility(View.GONE);
            tv_not_take_bottom.setVisibility(View.VISIBLE);

        }

    }





    private boolean isMineBidding(Order order) {


        boolean result = false;
        boolean quoted = order.is_quoted();
        List<String> quote_driver_ids = order.getQuote_driver_ids();


        if (quoted) {


            if (null != quote_driver_ids && quote_driver_ids.size() > 0) {
                for (String driverId : quote_driver_ids) {
                    String localDriverId = SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_DRIVER_ID, ConstSp.SP_VALUE.DEFAULT_STRING);
                    if (driverId.equals(localDriverId)) {
                        result = true;
                        break;
                    }
                }
            }
        }
        return result;

    }






    @Override
    public void onDataBackSuccessForFullLoadGoodsWeight(String weight) {
        tv_goods_weight.setText(weight);
    }

    @Override
    public void onDataBackSuccessForFullLoadAdditionService(List<AdditionService> additionServices) {

        measuredWithAndSetData(additionServices);


//        doAddAdditionServiceToTagSgin(additionServices);
        ll_extra_service.setVisibility(View.VISIBLE);
        additionServiceAdapter.setList(additionServices);
    }

    @Override
    public void doAdjustViewForAdditionService() {


        //解决进入界面后，srollview自动滑动到下部的listView，listView上面的数据不再顶部
        gv_addition_service.setFocusable(true);
        gv_addition_service.setFocusableInTouchMode(true);
        gv_addition_service.requestFocus();
    }

    @Override
    public void doHideViewWithOutAdditionService() {
        ll_extra_service.setVisibility(View.GONE);

    }




    @Override
    public void startBidding(String orderNo, String price) {


        presenterDriverOrderDetailNotTakeBidding.doStartBidding(HttpConst.URL.START_TO_BIDDING + HttpConst.SEPARATOR + orderNo, price);
    }

    @Override
    public void cancelBidding() {

        biddingDialog.dismiss();
        biddingDialog = null;


    }
}
