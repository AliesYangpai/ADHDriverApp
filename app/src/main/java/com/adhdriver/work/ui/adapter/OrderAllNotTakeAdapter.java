package com.adhdriver.work.ui.adapter;

import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.ImageView;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstHz;
import com.adhdriver.work.constant.ConstParams;
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
import com.adhdriver.work.entity.driver.orders.Office;
import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.entity.driver.orders.Province;
import com.adhdriver.work.entity.driver.orders.To;
import com.adhdriver.work.entity.driver.orders.Vehicle;
import com.adhdriver.work.utils.ImgUtil;
import com.adhdriver.work.utils.SpUtil;
import com.adhdriver.work.utils.StringUtil;
import com.adhdriver.work.utils.VehicleUtil;
import com.adhdriver.work.ui.widget.tagview.Tag;
import com.adhdriver.work.ui.widget.tagview.TagView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.feezu.liuli.timeselector.Utils.TextUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/11/20.
 * 类描述  跨城未接单的adapter
 * 版本
 */

public class OrderAllNotTakeAdapter extends BaseQuickAdapter<Order, BaseViewHolder> {


    public OrderAllNotTakeAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Order order) {






        if (isFullLoad(order.getBusiness_type())) {

            From from = order.getFrom();//wen我的orders


            if (null == from) {

                return ;
            }


            City fromCity = from.getCity();//市


            if (null == fromCity) {

                return ;
            }

            County fromCounty = from.getCounty();//区


            if (null == fromCounty) {

                return ;
            }


            To to = order.getTo();


            if (null == to) {

                return ;
            }


            City toCity = to.getCity();//市

            if (null == toCity) {

                return ;
            }


            County toCounty = to.getCounty();//区


            if (null == toCounty) {

                return ;
            }


            Goods goods = order.getGoods();

            if (null == goods) {

                return ;
            }


            Vehicle vehicle = order.getVehicle();

            if(null == vehicle) {

                return ;

            }





            helper.getView(R.id.ll_bidding_about).setVisibility(View.VISIBLE);
            helper.getView(R.id.ll_over_office_about).setVisibility(View.GONE);
            helper.getView(R.id.iv_bidding).setVisibility(View.VISIBLE);
            helper.getView(R.id.iv_grab).setVisibility(View.GONE);



            boolean result = isMineBidding(order);




            if(result) {

                ImageView imageView = helper.getView(R.id.iv_bidding);
                imageView.setImageResource(R.drawable.img_bidding_state_ing);

            }else {

                ImageView imageView = helper.getView(R.id.iv_bidding);
                imageView.setImageResource(R.drawable.img_bidding_normal);

            }

            helper.setText(R.id.tv_car_lenth, order.getVehicle().getLength()+ ConstSign.METER)
                    .setText(R.id.tv_user_name,order.getUser_name())
                    .setText(R.id.tv_catogry, VehicleUtil.getVehicleTypeDescriptionHz(order.getVehicle().getCategory_name()))
                    .setText(R.id.tv_goods_weight, getTheWeightTonOrdefaultText(goods)+ConstSign.WEIGHT_TON_UNIT)
                    .setText(R.id.tv_start_place,fromCity.getName()+fromCounty.getName()+from.getStreet_address())
                    .setText(R.id.tv_destination_place, toCity.getName()+toCounty.getName()+to.getStreet_address())
                    .addOnClickListener(R.id.iv_bidding);


        } else if(isOverOffice(order.getBusiness_type())) {


            From from = order.getFrom();//wen我的orders

            if(null == from) {
                return;

            }

            Office fromOffice = from.getOffice();


            if(null == fromOffice) {


                return;
            }


            Province fromOfficeProvince = fromOffice.getProvince();

            if(null == fromOfficeProvince) {

                return;
            }


            City fromOfficeCity = fromOffice.getCity();

            if(null == fromOfficeCity) {

                return;

            }

            County fromOfficeCounty = fromOffice.getCounty();

            if(null == fromOfficeCounty) {

                return;
            }


            To to = order.getTo();


            if(null == to) {

                return;
            }

            Fee fee = order.getFee();

            if(null == fee) {

                return;
            }


            Goods goods = order.getGoods();

            if(goods == null) {

                return;

            }

            helper.getView(R.id.ll_bidding_about).setVisibility(View.GONE);
            helper.getView(R.id.ll_over_office_about).setVisibility(View.VISIBLE);
            helper.getView(R.id.iv_bidding).setVisibility(View.GONE);
            helper.getView(R.id.iv_grab).setVisibility(View.VISIBLE);


            helper.setText(R.id.tv_over_office_goods_weight, order.getGoods().getWeight()+ConstSign.WEIGHT_UNIT_KG)
                    .setText(R.id.tv_user_name,order.getUser_name())
                    .setText(R.id.tv_over_office_goods_volume,  order.getGoods().getGoods_volume()+ConstSign.VOLUME_UNIT)
                    .setText(R.id.tv_goods_over_office_fee, getOverOfficePrice(order)+ ConstHz.RMB_UNIT)
                    .setText(R.id.tv_start_place,fromOffice.getName())
                    .setText(R.id.tv_destination_place, getDestinationDetail(to))
                    .addOnClickListener(R.id.iv_grab);

        }


        TagView tagView = helper.getView(R.id.tagview_sgin);
        List<AdditionService> additionServices = order.getAddition_services();
        if(null != additionServices && additionServices.size() > 0) {

            tagView.removeAllTags();
            tagView.setVisibility(View.VISIBLE);
            tagView.addTag(getTag("额外服务", "#FFFFFFFF", "#ff5613", "#ff5613"));

        }else {
            tagView.setVisibility(View.GONE);
        }





//        tagView.addTag(getTag("120km", "#5fd77e", "#FFFFFFFF", "#5fd77e"));


        ImgUtil.getInstance().getRadiusImgFromNetByUrl(order.getUser_avatar(), (ImageView) helper.getView(R.id.iv_userHead), R.drawable.img_default_client_head_round,120);


    }


    private Tag getTag(String str,String textColor,String layoutColor,String layoutBoderColor) {




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

    private boolean isFullLoad(String businessType) {

        return businessType.equals(ConstParams.Orders.FULL_LOAD);
    }


    private boolean isOverOffice(String businessType) {

        return businessType.equals(ConstParams.Orders.OVER_OFFICE);
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


            if(!TextUtil.isEmpty(subtotal) && name.startsWith(ConstTag.Fee.freight)) {


                feeTemp+= Float.valueOf(subtotal);


            }


        }

        driverFee = String.valueOf(feeTemp);


        return driverFee;

    }


    /**
     * 获取目的地 营业部详情信息，如果没有目的地营业补信息，则取目的地 省市区信息
     * @param to
     * @return
     */
    private String getDestinationDetail(To to) {

        String destinationName = "";


        StringBuffer stringBuffer = new StringBuffer();
        Office toOffice = to.getOffice();

        if(null == toOffice) {

            Province province = to.getProvince();
            City city = to.getCity();
            County county = to.getCounty();


            if(checkObject(province)) {

                stringBuffer.append(province.getName());

            }

            if(checkObject(city)) {

                stringBuffer.append(city.getName());

            }

            if(checkObject(county)) {

                stringBuffer.append(county.getName());

            }

            destinationName = stringBuffer.toString();


        }else {



            destinationName =  toOffice.getName();

        }


        return destinationName;

    }


    /**
     * 判断对象非空
     * @param object
     * @return
     */
    private boolean checkObject(Object object) {

        boolean result = false;

        if(null != object) {


            result = true;
        }


        return result;


    }



    /**
     * 货物重量 吨
     *
     * @param goods
     * @return
     */
    public String getTheWeightTonOrdefaultText(Goods goods) {

        String str = ConstHz.NO_WEIGHT;


        String weight = goods.getWeight();


        if (!TextUtil.isEmpty(weight)) {


            Float theWeight = Float.valueOf(weight);

            if (theWeight > 0) {

                str = String.valueOf(theWeight);

                str = StringUtil.valueKgToTun(str);

            }
        }


        return str;

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




}
