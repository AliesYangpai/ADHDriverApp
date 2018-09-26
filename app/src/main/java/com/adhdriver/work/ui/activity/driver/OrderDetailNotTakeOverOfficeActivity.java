package com.adhdriver.work.ui.activity.driver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstHz;
import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.constant.ConstSign;
import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.constant.ConstTag;
import com.adhdriver.work.entity.driver.orders.AdditionService;
import com.adhdriver.work.entity.driver.orders.City;
import com.adhdriver.work.entity.driver.orders.County;
import com.adhdriver.work.entity.driver.orders.Fee;
import com.adhdriver.work.entity.driver.orders.FeeDetail;
import com.adhdriver.work.entity.driver.orders.FixedPriceFee;
import com.adhdriver.work.entity.driver.orders.From;
import com.adhdriver.work.entity.driver.orders.Goods;
import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.entity.driver.orders.Province;
import com.adhdriver.work.entity.driver.orders.To;
import com.adhdriver.work.entity.driver.orders.Vehicle;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.presenter.driver.PresenterDriverOrderDetailNotTakeOverOffice;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.activity.common.LoginActivity;
import com.adhdriver.work.ui.adapter.forgridview.AdditionServiceAdapter;
import com.adhdriver.work.ui.iview.driver.IOrderDetailNotTakeOverOfficeView;
import com.adhdriver.work.utils.SpUtil;
import com.adhdriver.work.utils.StringUtil;
import com.adhdriver.work.utils.ToastUtil;
import com.adhdriver.work.ui.widget.gridview.AdditionServiceGridView;
import com.xyz.step.FlowViewVertical;

import org.feezu.liuli.timeselector.Utils.TextUtil;

import java.util.List;

public class OrderDetailNotTakeOverOfficeActivity extends BaseActivity<IOrderDetailNotTakeOverOfficeView,PresenterDriverOrderDetailNotTakeOverOffice>
        implements
        IOrderDetailNotTakeOverOfficeView,
        View.OnClickListener{


    private PresenterDriverOrderDetailNotTakeOverOffice presenterDriverOrderDetailNotTakeOverOffice;



    /**
     * title
     *
     * @param savedInstanceState
     */
    private ImageView iv_common_back;





    /**
     * 货物信息
     *
     * @param savedInstanceState
     */


    private TextView tv_order_time; //时间
    private TextView tv_price;//订单价格
    private TextView tv_business_type; //货运类型
    private TextView tv_goods_name; //货物名称
    private TextView tv_goods_weight; //货物重量
    private TextView tv_goods_volume; //货物体积
    private LinearLayout ll_extra_service;

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

    private AdditionServiceGridView gv_addition_service;
    private AdditionServiceAdapter additionServiceAdapter;


    /**
     * 底部按钮
     */
    private TextView tv_not_take_bottom;


    /**
     * 数据相关
     * @param savedInstanceState
     */


    private Order currentOrder;







    private boolean isVisitor() {

        return SpUtil.getInstance().getBoolenValue(ConstSp.SP_KEY_VISITOR, ConstSp.SP_VALUE.DEFAULT_TRUE_VISITOR);

    }




    /**
     * 获取营业部 一口价或 非一口价的收益价格
     * @param order
     * @return
     */
    private String getOverOfficePrice(Order order) {

        String price = "";

        boolean fixed_price = order.is_fixed_price(); //判断是不是 一口价的集包订单

        if(fixed_price) {


            FixedPriceFee fixed_price_fee = order.getFixed_price_fee();

            price = getDriverFee(fixed_price_fee.getDetails());

        }else {


            Fee fee = order.getFee();
            price = getDriverFee(fee.getDetails());

        }

        return price;


    }


    /**
     * 计算出司机收益
     * @param details
     * @return
     */
    private String getDriverFee(List<FeeDetail> details) {


        String driverFee = "";


        float feeTemp = 0;



        if(null == details) {

            return driverFee;
        }

        for (FeeDetail feeDetail : details) {

            String name = feeDetail.getName();
            String subtotal = feeDetail.getSubtotal();
            String divided_type = feeDetail.getDivided_type();

            if(!TextUtil.isEmpty(subtotal) && name.startsWith(ConstTag.Fee.freight)) {

                /**
                 * 价格过滤优惠券
                 */
                if(!TextUtil.isEmpty(divided_type) && divided_type.equals(ConstTag.Fee.coupon)) {
                    continue;
                }

                feeTemp+= Float.valueOf(subtotal);
            }


        }


        driverFee = String.valueOf(feeTemp);

        return driverFee;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail_not_take_over_office);




        presenterDriverOrderDetailNotTakeOverOffice.doSetOrderInfoToUi(currentOrder);
    }

    @Override
    protected PresenterDriverOrderDetailNotTakeOverOffice creatPresenter() {
        if(null == presenterDriverOrderDetailNotTakeOverOffice) {

            presenterDriverOrderDetailNotTakeOverOffice = new PresenterDriverOrderDetailNotTakeOverOffice(this);
        }
        return presenterDriverOrderDetailNotTakeOverOffice;
    }

    @Override
    protected void initViews() {



        /**
         * title
         * @param savedInstanceState
         */

        iv_common_back = findADHViewById(R.id.iv_common_back);



        tv_price = findADHViewById(R.id.tv_price);
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



        ll_extra_service = findADHViewById(R.id.ll_extra_service);
        /**
         * 额外服务
         * @param savedInstanceState
         */

        gv_addition_service = findADHViewById(R.id.gv_addition_service);
        additionServiceAdapter = new AdditionServiceAdapter(this);
        gv_addition_service.setAdapter(additionServiceAdapter);


        /**
         * 底部按钮
         */
        tv_not_take_bottom = findADHViewById(R.id.tv_not_take_bottom);
        tv_not_take_bottom.setText(getString(R.string.click_to_grab));
    }

    @Override
    protected void initListener() {

        iv_common_back.setOnClickListener(this);
        tv_not_take_bottom.setOnClickListener(this);

    }

    @Override
    protected void processIntent() {


        Intent intent = getIntent();
        if(null != intent) {
           Bundle bundle  = intent.getExtras();

            if(null != bundle) {
                currentOrder = (Order) bundle.getSerializable(ConstIntent.BundleKEY.DELIVER_ORDER);
            }
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

        ToastUtil.showMsg(getApplicationContext(),errorMsg);
    }

    @Override
    public void onDataBackSuccessForOrderDetial(Order order) {


        /**
         * 暂不处理
         */

    }

    @Override
    public void onDataBackSuccessForGetOrderInfoOverOffice(Order order) {
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





        tv_goods_name.setText(goods.getDescription());  //货物名称
        tv_order_time.setText(order.getCreate_time()); //用车时间
        tv_goods_volume.setText(goods.getGoods_volume() + ConstSign.VOLUME_UNIT);//货物体积
        tv_business_type.setText(StringUtil.getOrderTypeHz(order.getBusiness_type()));

       tv_price.setText(getOverOfficePrice(order)+ ConstHz.RMB_UNIT);
//        doSetPassPoint(order);




    }

    @Override
    public void onDataBackSuccessForOverOfficeGoodsWeight(String weight) {


        tv_goods_weight.setText(weight);
    }

    @Override
    public void onDataBackSuccessForOverPathPoints(String[] pathPoint) {
        fvl_pass_point.setProgress(pathPoint.length, pathPoint.length, pathPoint, null);
    }

    @Override
    public void onDataBackSuccessForOverOfficeAdditionService(List<AdditionService> additionServices) {


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
    public void onDataBackSuccessForGrab() {


        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstIntent.BundleKEY.DELIVER_ORDER,currentOrder);

        openActivityAndFinishItself(OrderTakeDetailActivity.class,bundle);
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



                if(null != currentOrder) {

                   String  order_No = currentOrder.getOrder_no();
                    boolean isfixed = currentOrder.is_fixed_price();


                    if(isfixed) {

                        presenterDriverOrderDetailNotTakeOverOffice.doStartGrab(HttpConst.URL.START_TO_TAKE_FIXED_FULLLOAD+HttpConst.SEPARATOR+order_No);

                    }else {

                        presenterDriverOrderDetailNotTakeOverOffice.doStartGrab(HttpConst.URL.START_TO_GRAB_OVER_OFFICE+HttpConst.SEPARATOR+order_No);

                    }
                }



                break;
        }
    }



}
