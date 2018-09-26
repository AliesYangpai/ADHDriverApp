package com.adhdriver.work.ui.activity.driver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.DialogRedPacketCallBack;
import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.constant.ConstHz;
import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.constant.ConstParams;
import com.adhdriver.work.constant.ConstSign;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.PrizePool;
import com.adhdriver.work.entity.driver.orders.AdditionService;
import com.adhdriver.work.entity.driver.orders.Goods;
import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.entity.driver.orders.Vehicle;
import com.adhdriver.work.http.CallServer;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.http.HttpSingleResponseListener;
import com.adhdriver.work.http.RequestDriverParams;
import com.adhdriver.work.http.RequestPacking;
import com.adhdriver.work.presenter.driver.PresenterDriverOrderTakeDetailFinish;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.adapter.forgridview.AdditionServiceAdapter;
import com.adhdriver.work.ui.iview.driver.IOrderTakeDetailFinishView;
import com.adhdriver.work.ui.widget.dialog.RedPacketDialog;
import com.adhdriver.work.utils.ImgUtil;
import com.adhdriver.work.utils.StringUtil;
import com.adhdriver.work.ui.widget.gridview.AdditionServiceGridView;
import com.adhdriver.work.utils.ToastUtil;
import com.xyz.step.FlowViewVertical;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.List;

public class OrderTakeDetailFinishActivity extends BaseActivity<IOrderTakeDetailFinishView, PresenterDriverOrderTakeDetailFinish>
        implements
        View.OnClickListener,
        IOrderTakeDetailFinishView,
        DialogRedPacketCallBack {


    private PresenterDriverOrderTakeDetailFinish presenterDriverOrderTakeDetailFinish;


    /**
     * title
     *
     * @param savedInstanceState
     */

    private ImageView iv_common_back;
    private TextView tv_common_title;


    private ImageView iv_user_head;
    private TextView tv_user_name;

    private TextView tv_top_left;
    private TextView tv_top_center;
    private TextView tv_top_right;

    private ImageView iv_state;

    private TextView tv_pay_side;
    private LinearLayout ll_pay_side;
    private TextView tv_order_time;
    private TextView tv_business_type;

    private LinearLayout ll_goods_name;
    private TextView tv_goods_name;

    private LinearLayout ll_goods_weight;
    private TextView tv_goods_weight;

    private LinearLayout ll_goods_volume;
    private TextView tv_goods_volume;

    private FlowViewVertical fvl_pass_point;

    private LinearLayout ll_extra_service;
    private AdditionServiceGridView gv_addition_service;
    private AdditionServiceAdapter additionServiceAdapter;

    /**
     * 数据相关
     *
     * @param savedInstanceState
     */

    private Order currentOrder;

    private RedPacketDialog redPacketDialog;


    /**
     * 展示首单奖励
     */
    private void showFirstAwardOrNot() {


        if (null != currentOrder) {

            if (currentOrder.is_first()) {


                if (!currentOrder.is_received_first_reward()) {

                    getTheFirstRewardInfoFromServer();

                }


            }

        }


    }


    /**
     * 获取首单奖励
     */
    private void getTheFirstRewardInfoFromServer() {


        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
                HttpConst.URL.PRIZEPOLL,
                RequestMethod.GET,
                null
        );


        CallServer.getInstance().add(HttpConst.HTTP_WHAT, request, new HttpSingleResponseListener<String>() {
            @Override
            protected void OnHttpStart(int what) {
                showLoadDialog();
            }

            @Override
            protected void OnHttpSuccessed(int what, Response<String> response) {

                PrizePool prizePoolBack = presenterDriverOrderTakeDetailFinish.parseSerilizable.getParseToObj(response.get(), PrizePool.class);

                initRedPacketDialog(prizePoolBack);

            }

            @Override
            protected void onHttpFailed(int what, Response<String> response) {

            }

            @Override
            protected void OnHttpFinish(int what) {
                dismissLoadDialog();
            }
        });

    }


    /**
     * 现金红包的dialog
     */
    private void initRedPacketDialog(PrizePool prizePoolBack) {


        if (null != prizePoolBack) {

            if (redPacketDialog == null) {

                redPacketDialog = new RedPacketDialog(this, prizePoolBack.getBalance(), prizePoolBack.getReceived_count());

                redPacketDialog.setDialogRedPacketCallBack(this);

                redPacketDialog.show();

            }

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_take_detail_finish);


        presenterDriverOrderTakeDetailFinish.doSetDataToUi(currentOrder);


    }


    @Override
    protected PresenterDriverOrderTakeDetailFinish creatPresenter() {
        if (null == presenterDriverOrderTakeDetailFinish) {
            presenterDriverOrderTakeDetailFinish = new PresenterDriverOrderTakeDetailFinish(this);
        }
        return presenterDriverOrderTakeDetailFinish;
    }

    @Override
    protected void initViews() {
        /**
         * title
         * @param savedInstanceState
         */
        iv_common_back = findADHViewById(R.id.iv_common_back);
        tv_common_title = findADHViewById(R.id.tv_common_title);
        tv_common_title.setText(getString(R.string.ordring_detial));


        iv_user_head = findADHViewById(R.id.iv_user_head);
        tv_user_name = findADHViewById(R.id.tv_user_name);

        tv_top_left = findADHViewById(R.id.tv_top_left);
        tv_top_center = findADHViewById(R.id.tv_top_center);
        tv_top_right = findADHViewById(R.id.tv_top_right);

        iv_state = findADHViewById(R.id.iv_state);

        tv_order_time = findADHViewById(R.id.tv_order_time);
        tv_business_type = findADHViewById(R.id.tv_business_type);

        tv_pay_side = findADHViewById(R.id.tv_pay_side);
        ll_pay_side = findADHViewById(R.id.ll_pay_side);
        ll_goods_name = findADHViewById(R.id.ll_goods_name);
        tv_goods_name = findADHViewById(R.id.tv_goods_name);

        ll_goods_weight = findADHViewById(R.id.ll_goods_weight);
        tv_goods_weight = findADHViewById(R.id.tv_goods_weight);

        ll_goods_volume = findADHViewById(R.id.ll_goods_volume);
        tv_goods_volume = findADHViewById(R.id.tv_goods_volume);

        fvl_pass_point = findADHViewById(R.id.fvl_pass_point);

        ll_extra_service = findADHViewById(R.id.ll_extra_service);
        gv_addition_service = findADHViewById(R.id.gv_addition_service);
        additionServiceAdapter = new AdditionServiceAdapter(this);
        gv_addition_service.setAdapter(additionServiceAdapter);


        switch (currentOrder.getBusiness_type()) {

            case ConstParams.Orders.SAME_CITY:

                tv_top_center.setVisibility(View.GONE);
                ll_goods_name.setVisibility(View.GONE);
                ll_goods_volume.setVisibility(View.GONE);
                ll_goods_weight.setVisibility(View.GONE);
                break;

            case ConstParams.Orders.FULL_LOAD:

                break;

            case ConstParams.Orders.OVER_OFFICE:
                ll_pay_side.setVisibility(View.GONE);
                tv_top_right.setVisibility(View.GONE);
                break;
        }


    }

    @Override
    protected void initListener() {


        iv_common_back.setOnClickListener(this);

    }

    @Override
    protected void processIntent() {

        Intent intent = this.getIntent();
        if (null != intent) {
            Bundle bundle = intent.getExtras();
            currentOrder = (Order) bundle.getSerializable(ConstIntent.BundleKEY.DELIVER_ORDER);

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_common_back:
                dofinishItself();
                break;
        }
    }

    @Override
    public void onDataBackSuccessForSameCityOrderInfo(Order order) {

        String avatar = order.getUser_avatar();
        String name = order.getUser_name();
        String vehicleName = order.getVehicle().getName();
        String book_time = order.getBook_time();
        String businessType = order.getBusiness_type();


        ImgUtil.getInstance().getRadiusImgFromNetByUrl(avatar, iv_user_head, R.drawable.img_default_client_head_round, 120);
        tv_user_name.setText(name);
        tv_top_left.setText(vehicleName);
        tv_order_time.setText(book_time);
        tv_business_type.setText(StringUtil.getOrderTypeHz(businessType));
        tv_pay_side.setText(StringUtil.getPayWayByPayer(order.getPayer()));


        showFirstAwardOrNot();
    }

    @Override
    public void onDataBackSuccessForSameCityDriveDivide(String driverDivide) {
        tv_top_right.setText(driverDivide + ConstHz.RMB_UNIT);
    }

    @Override
    public void onDataBackSuccessForSameCityAdditionService(List<AdditionService> additionServices) {

        ll_extra_service.setVisibility(View.VISIBLE);
        additionServiceAdapter.setList(additionServices);
    }

    @Override
    public void onDataBackSuccessForOverOfficeGetOrderInfo(Order order) {

        String avatar = order.getUser_avatar();
        String name = order.getUser_name();
        String book_time = order.getBook_time();

        String businessType = order.getBusiness_type();
        Goods goods = order.getGoods();

        ImgUtil.getInstance().getRadiusImgFromNetByUrl(avatar, iv_user_head, R.drawable.img_default_client_head_round, 120);
        tv_user_name.setText(name);
        tv_order_time.setText(book_time);

        tv_business_type.setText(StringUtil.getOrderTypeHz(businessType));
        tv_goods_name.setText(goods.getDescription());  //货物名称
        tv_order_time.setText(order.getCreate_time()); //用车时间
        tv_goods_volume.setText(goods.getGoods_volume() + ConstSign.VOLUME_UNIT);//货物体积
        tv_goods_weight.setText(goods.getWeight() + ConstSign.VOLUME_UNIT);
        tv_business_type.setText(StringUtil.getOrderTypeHz(order.getBusiness_type()));

        showFirstAwardOrNot();


    }

    @Override
    public void onDataBackSuccessForOverOfficeDriveDivide(String driverDivide) {
        tv_top_right.setText(driverDivide);
    }

    @Override
    public void onDataBackSuccessForOverOfficeAdditionService(List<AdditionService> additionServices) {
        ll_extra_service.setVisibility(View.VISIBLE);
        additionServiceAdapter.setList(additionServices);
    }

    @Override
    public void onDataBackSuccessForFullLoadGetOrderInfo(Order order) {


        String avatar = order.getUser_avatar();
        String name = order.getUser_name();
        String book_time = order.getBook_time();

        String businessType = order.getBusiness_type();
        Vehicle vehicle = order.getVehicle();

        Goods goods = order.getGoods();

        ImgUtil.getInstance().getRadiusImgFromNetByUrl(avatar, iv_user_head, R.drawable.img_default_client_head_round, 120);
        tv_user_name.setText(name);


        tv_top_left.setText(vehicle.getName());
        tv_top_center.setText(vehicle.getCategory_name());
        tv_order_time.setText(book_time);

        tv_business_type.setText(StringUtil.getOrderTypeHz(businessType));
        tv_goods_name.setText(goods.getDescription());  //货物名称
        tv_order_time.setText(order.getCreate_time()); //用车时间
        tv_goods_volume.setText(goods.getGoods_volume() + ConstSign.VOLUME_UNIT);//货物体积
        tv_goods_weight.setText(goods.getWeight() + ConstSign.VOLUME_UNIT);
        tv_business_type.setText(StringUtil.getOrderTypeHz(order.getBusiness_type()));

        tv_pay_side.setText(StringUtil.getPayWayByPayer(order.getPayer()));
        showFirstAwardOrNot();
    }

    @Override
    public void onDataBackSuccessForFullLoadDriveDivide(String driverDivide) {
        tv_top_right.setText(driverDivide + ConstHz.RMB_UNIT);
    }

    @Override
    public void onDataBackSuccessForFullLoadAdditionService(List<AdditionService> additionServices) {
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
    public void doSetPathPointToUi(String[] arry) {


        fvl_pass_point.setProgress(0, arry.length, arry, null);

    }

    @Override
    public void startCarveUpClick(final RelativeLayout rl_red_packet_big_bg, final RelativeLayout rl_carve_up_about, final TextView tv_start_carve_up, final RelativeLayout rl_rp_show_packet_about, final TextView tv_start_to_check) {
        /**
         * 开始瓜分红包
         */


        String jsonRequest = RequestDriverParams.getToCarveUpRegPacketParam(ConstLocalData.REG_PACKET_DEFAULT_AMOUNT);

        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(
                HttpConst.URL.REWARD + HttpConst.SEPARATOR + currentOrder.getOrder_no(),
                RequestMethod.POST,
                jsonRequest);


        CallServer.getInstance().add(HttpConst.HTTP_WHAT, request, new HttpSingleResponseListener<String>() {
            @Override
            protected void OnHttpStart(int what) {

                showLoadDialog();
            }

            @Override
            protected void OnHttpSuccessed(int what, Response<String> response) {
                /**
                 *
                 * @param rl_red_packet_big_bg 大背景图
                 * @param rl_carve_up_about  carveUp背景
                 * @param tv_start_carve_up  立即瓜分
                 * @param rl_rp_show_packet_about 红包袋布局
                 * @param tv_start_to_check   立即查看
                 */
                rl_red_packet_big_bg.setBackgroundResource(R.drawable.img_rp_packet_bg);
                rl_carve_up_about.setVisibility(View.GONE);
                tv_start_carve_up.setVisibility(View.GONE);
                rl_rp_show_packet_about.setVisibility(View.VISIBLE);
                tv_start_to_check.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onHttpFailed(int what, Response<String> response) {

                ErrorEntity errorEntity = presenterDriverOrderTakeDetailFinish.parseSerilizable.getParseToObj(response.get(), ErrorEntity.class);
                if (null != errorEntity) {
                    ToastUtil.showMsg(getApplicationContext(), errorEntity.getError_message());
                }else {
                    ToastUtil.showMsg(getApplicationContext(), ConstError.PARSE_ERROR_MSG);
                }


                if (null != redPacketDialog && redPacketDialog.isShowing()) {

                    redPacketDialog.dismiss();

                    redPacketDialog = null;

                }


            }

            @Override
            protected void OnHttpFinish(int what) {
                destroyLoadDialog();
            }
        });


    }

    @Override
    public void startGetAwardBunds() {
        /**
         * 点击领取奖励，并且跳转到零钱界面，同时销毁当前界面
         */


        if (null != redPacketDialog && redPacketDialog.isShowing()) {

            redPacketDialog.dismiss();

            redPacketDialog = null;

            openActivityAndFinishItself(MyWalletActivity.class, null);

        }
    }
}
