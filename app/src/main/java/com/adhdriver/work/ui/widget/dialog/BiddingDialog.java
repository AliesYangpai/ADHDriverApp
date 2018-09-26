package com.adhdriver.work.ui.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adhdriver.work.R;

import com.adhdriver.work.callback.BiddingDialogCallBack;
import com.adhdriver.work.constant.ConstSign;
import com.adhdriver.work.entity.driver.orders.AdditionService;
import com.adhdriver.work.entity.driver.orders.City;
import com.adhdriver.work.entity.driver.orders.County;
import com.adhdriver.work.entity.driver.orders.From;
import com.adhdriver.work.entity.driver.orders.Goods;
import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.entity.driver.orders.To;
import com.adhdriver.work.entity.driver.orders.Vehicle;
import com.adhdriver.work.logic.LogicMoney;
import com.adhdriver.work.ui.widget.tagview.TagView;
import com.adhdriver.work.utils.ImgUtil;
import com.adhdriver.work.utils.VehicleUtil;
import com.adhdriver.work.ui.widget.tagview.Tag;

import org.feezu.liuli.timeselector.Utils.TextUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/5/12.
 * 类描述
 * 版本
 */

public class BiddingDialog extends Dialog implements View.OnClickListener, TextWatcher {


    private ImageView iv_user_head;//用户头像
    private TextView tv_user_name;//用户名
    private TextView tv_car_lenth;//车辆车辆长度
    private TextView tv_catogry;//车来个catogry
    private TextView tv_goods_weight;//车辆体积
    private TagView tagview_sgin;//标签

    private TextView tv_start_place;//开始地
    private TextView tv_destination_place;//目的地
    private LinearLayout ll_bidding_about;//上部分信息布局，用于测量下面的云标签

    private EditText et_bidding_price;//竞价ediText
    private TextView tv_start_bidding;//开始竞价
    private TextView tv_cancel_bidding;//取消竞价

    /**
     * 回调函数
     *
     * @param context
     * @param tag
     */


    private Context context;
    private View view;

    /**
     * 数据相关
     */
    private Order order;


    /**
     * 逻辑相关
     */


    private LogicMoney logicMoney;

    private BiddingDialogCallBack biddingDialogCallBack;


    public void setBiddingDialogCallBack(BiddingDialogCallBack biddingDialogCallBack) {
        this.biddingDialogCallBack = biddingDialogCallBack;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public BiddingDialog(Context context) {
        super(context, R.style.biddingDialog);
        this.context = context;
        this.logicMoney = new LogicMoney();
    }


    public BiddingDialog(Context context, AttributeSet attrs) {
        super(context, R.style.biddingDialog);
        this.context = context;
        this.logicMoney = new LogicMoney();


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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_bidding);

        setCancelable(false);

        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);

        //初始化界面控件
        initView();

        //初始化界面控件的事件
        initListener();


        //初始化界面数据
        initData();

        //测量tagView的最大宽度并初始化界面数据
        measuredWithAndSetData();


    }


    /**
     * 初始化界面的确定和取消监听器
     */
    private void initListener() {

        tv_start_bidding.setOnClickListener(this);
        tv_cancel_bidding.setOnClickListener(this);
        et_bidding_price.addTextChangedListener(this);
    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {

        From from = order.getFrom();//wen我的orders


        if (null == from) {

            return;
        }


        City fromCity = from.getCity();//市


        if (null == fromCity) {

            return;
        }

        County fromCounty = from.getCounty();//区


        if (null == fromCounty) {

            return;
        }


        To to = order.getTo();


        if (null == to) {

            return;
        }


        City toCity = to.getCity();//市

        if (null == toCity) {

            return;
        }


        County toCounty = to.getCounty();//区


        if (null == toCounty) {

            return;
        }


        Goods goods = order.getGoods();

        if (null == goods) {

            return;
        }

        Vehicle vehicle = order.getVehicle();

        if (null == vehicle) {

            return;

        }


        tv_user_name.setText(order.getUser_name());

        ImgUtil.getInstance().getRadiusImgFromNetByUrl(order.getUser_avatar(), iv_user_head, R.drawable.img_default_client_head_round, 120);


        tv_user_name.setText(order.getUser_name());//用户名
        tv_car_lenth.setText(order.getVehicle().getLength() + ConstSign.METER);//车辆车辆长度
        tv_catogry.setText(VehicleUtil.getVehicleTypeDescriptionHz(order.getVehicle().getCategory_name()));//车来个catogry
        tv_goods_weight.setText(order.getGoods().getWeight() + ConstSign.WEIGHT_UNIT_KG);//h货物重量

        tv_start_place.setText(fromCity.getName() + fromCounty.getName() + from.getStreet_address());
        tv_destination_place.setText(toCity.getName() + toCounty.getName() + to.getStreet_address());


        List<AdditionService> additionServices = order.getAddition_services();

        if (null != additionServices && additionServices.size() > 0) {

            tagview_sgin.removeAllTags();
            tagview_sgin.setVisibility(View.VISIBLE);
            doAddAdditionServiceToTagSgin(additionServices);

        } else {

            tagview_sgin.setVisibility(View.GONE);
        }


//        tagview_sgin.addTag(getTag("传真回执单", "#FFFFFFFF", "#ff5613", "#ff5613"));


    }


    public void doAddAdditionServiceToTagSgin(List<AdditionService> additionServices) {

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


    /**
     * 初始化界面控件
     */
    private void initView() {


        iv_user_head = (ImageView) findViewById(R.id.iv_user_head);//用户头像
        tv_user_name = (TextView) findViewById(R.id.tv_user_name);//用户名

        tv_car_lenth = (TextView) findViewById(R.id.tv_car_lenth);//车辆车辆长度
        tv_catogry = (TextView) findViewById(R.id.tv_catogry);//车来个catogry
        tv_goods_weight = (TextView) findViewById(R.id.tv_goods_weight);
        ;//车辆体积

        tagview_sgin = (TagView) findViewById(R.id.tagview_sgin); //tagView的标签
        ll_bidding_about = (LinearLayout) findViewById(R.id.ll_bidding_about);

        tv_start_place = (TextView) findViewById(R.id.tv_start_place);//开始地
        tv_destination_place = (TextView) findViewById(R.id.tv_destination_place);
        ;//目的地

//        tagview_sgin.setMaxWith(getWidth());

        et_bidding_price = (EditText) findViewById(R.id.et_bidding_price);//竞价ediText
        tv_start_bidding = (TextView) findViewById(R.id.tv_start_bidding);//开始竞价
        tv_cancel_bidding = (TextView) findViewById(R.id.tv_cancel_bidding);//取消竞价


    }


    /**
     * 这里使用 ll_car_goods的宽度作为tagView的最大宽度
     */
    private void measuredWithAndSetData() {

        ViewTreeObserver vto = ll_bidding_about.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ll_bidding_about.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int maxWidth = ll_bidding_about.getWidth();
                Log.i("tgaViewSize", "MeasuredWidth" + maxWidth);
                tagview_sgin.setMaxWith(maxWidth);
                initData();
            }
        });
    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.tv_cancel_bidding:  //取消竞价


                clickEventByTag(false);

                break;


            case R.id.tv_start_bidding:    //确认竞价

                clickEventByTag(true);

                break;


        }
    }


    /**
     * 点击事件
     */


    /**
     * 根据当前tag选择确认或者
     *
     * @param isSure
     */

    private void clickEventByTag(boolean isSure) {

        clickEvent(isSure);

    }

    /**
     * 点击事件
     */
    private void clickEvent(boolean isSure) {

        if (null != biddingDialogCallBack) {

            if (isSure) {


                String price = et_bidding_price.getText().toString().trim();

                if (vertifyCode(price)) {


                    biddingDialogCallBack.startBidding(order.getOrder_no(), price);

                }


            } else {

                biddingDialogCallBack.cancelBidding();

            }

        }

    }


    private boolean vertifyCode(String str) {

        boolean result = false;


        if (!TextUtils.isEmpty(str)) {

            result = true;

        }

        return result;

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        String param = String.valueOf(s);


        if (logicMoney.isEmptyCash(param)) {

            tv_start_bidding.setEnabled(false);
            return;
        }


        if (logicMoney.isPointStart(param)) {

            if (logicMoney.isSmallThan0Point1(param)) {
                tv_start_bidding.setEnabled(false);
            } else {
                tv_start_bidding.setEnabled(true);
            }
        } else {

            if (logicMoney.is0(param)) {
                tv_start_bidding.setEnabled(false);
            } else if (!logicMoney.isSmallThan0Point1(param)) {

                tv_start_bidding.setEnabled(true);
            }

        }


    }
}
