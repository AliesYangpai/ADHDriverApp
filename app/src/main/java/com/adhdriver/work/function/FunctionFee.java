package com.adhdriver.work.function;

import com.adhdriver.work.constant.ConstSign;
import com.adhdriver.work.constant.ConstTag;
import com.adhdriver.work.entity.driver.orders.Fee;
import com.adhdriver.work.entity.driver.orders.FeeDetail;
import com.adhdriver.work.entity.driver.orders.FixedPriceFee;
import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.utils.DoubleUtil;

import org.feezu.liuli.timeselector.Utils.TextUtil;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2017/12/5.
 * 类描述  费用计算相关方法
 * 版本
 */

public class FunctionFee {

    public FunctionFee() {

    }

    /**
     * 获取总用费
     *
     * @return
     */
    public String getfreight(List<FeeDetail> details) {

        String driverFee = "";
        float feeTemp = 0;
        for (FeeDetail feeDetail : details) {

            String name = feeDetail.getName();
            String subtotal = feeDetail.getSubtotal();
            String divided_type = feeDetail.getDivided_type();
            if (!TextUtil.isEmpty(subtotal) && name.startsWith(ConstTag.Fee.freight)) {
                /**
                 * 价格过滤优惠券
                 */
                if (!TextUtil.isEmpty(divided_type) && divided_type.equals(ConstTag.Fee.coupon)) {
                    continue;
                }
                feeTemp += Float.valueOf(subtotal);
            }


        }
        driverFee = String.valueOf(feeTemp);

        return driverFee;

    }


    /**
     * 获取司机分成
     *
     * @param details
     * @param businessType
     * @return
     */
    public String getDriverDivide(List<FeeDetail> details, String businessType,float proporition) {

        String freight = getfreight(details);

        float driverDivide = 0;
        if (!TextUtil.isEmpty(businessType)) {

            driverDivide = Float.valueOf(freight) * proporition;
            driverDivide = Float.parseFloat(new DecimalFormat(ConstSign.DECIMAL_2_MONEY).format(driverDivide));
        }
        return String.valueOf(driverDivide);
    }


    /**
     * 获取营业部一口价司机分成
     *
     * @return
     */
    public String getOverOfficeFixedPriceDriverDivide(Order order) {

        String divide = "";


        if(null != order) {
            boolean fixed_price = order.is_fixed_price(); //判断是不是 一口价的集包订单

            if (fixed_price) {


                FixedPriceFee fixed_price_fee = order.getFixed_price_fee();
                if (null != fixed_price_fee) {

                    List<FeeDetail> details = fixed_price_fee.getDetails();

                    if (null != details && details.size() > 0) {

                        divide = getfreight(fixed_price_fee.getDetails());
                    }

                }

            }
        }



        return divide;
    }


    /**
     * 获取营业部非一口价司机分成
     *
     * @return
     */
    public String getOverOfficeNotFixedPriceDriverDivide(Order order) {

        String divide = "";

        boolean fixed_price = order.is_fixed_price(); //判断是不是 一口价的集包订单

        if(null != order) {
            if (!fixed_price) {


                FixedPriceFee fixed_price_fee = order.getFixed_price_fee();

                Fee fee = order.getFee();

                if (null != fee) {

                    List<FeeDetail> details = fee.getDetails();


                    if(null != details && details.size() > 0) {
                        divide = getfreight(fixed_price_fee.getDetails());
                    }


                }


            }
        }



        return divide;
    }







    public float getShipperFullLoadShouldPay(Fee fee) {



        float shouldPayAll = 0;
        if(null != fee) {

            List<FeeDetail> details = fee.getDetails();
            if(null == details) {
                return shouldPayAll;
            }

            for (FeeDetail feeDetail : details) {

                float showPay = Float.valueOf(feeDetail.getSubtotal());
                shouldPayAll += showPay;
            }
        }

        return shouldPayAll;

    }


    public float getShipperFullLoadHadPaid(Fee fee) {



        float hadPayAll = 0;
        if(null != fee) {
            List<FeeDetail> details = fee.getDetails();
            if(null == details) {
                return 0;
            }

            for (FeeDetail feeDetail : details) {
                float paid = Float.valueOf(feeDetail.getPaid());
                hadPayAll += paid;
            }
        }

        return hadPayAll;

    }



    /**
     * 获取营业部一口价 或者 非一口价司机分成
     *
     * @return
     */
    public String getOverOfficeDriverDivide(Order order) {

        String divide = "";

        boolean fixed_price = order.is_fixed_price(); //判断是不是 一口价的集包订单

        if(null != order) {
            if (!fixed_price) {



                Fee fee = order.getFee();

                if (null != fee) {

                    List<FeeDetail> details = fee.getDetails();


                    if(null != details && details.size() > 0) {
                        divide = getfreight(details);
                    }

                }

            }else {

                FixedPriceFee fixed_price_fee = order.getFixed_price_fee();
                if (null != fixed_price_fee) {

                    List<FeeDetail> details = fixed_price_fee.getDetails();

                    if (null != details && details.size() > 0) {

                        divide = getfreight(fixed_price_fee.getDetails());
                    }

                }

            }
        }



        return divide;
    }

    /**
     * 同城分成
     * @param fee
     * @param proporition
     * @return
     */
    public float getSameCityDriverDivide(Fee fee,float proporition) {

        String freight = getfreight(fee.getDetails());

        float driverDivide = 0;

        if (!TextUtil.isEmpty(freight)) {

            driverDivide = Float.valueOf(freight) * proporition;

            driverDivide = Float.parseFloat(new DecimalFormat(ConstSign.DECIMAL_2_MONEY).format(driverDivide));

        }

        return driverDivide;

    }


    /**
     * 整车分成
     * @param fee
     * @param proporition
     * @return
     */
    public float getFullLoadDriverDivide(Fee fee,float proporition) {

        String freight = getfreight(fee.getDetails());

        float driverDivide = 0;

        if (!TextUtil.isEmpty(freight)) {

            driverDivide = Float.valueOf(freight) * proporition;

            driverDivide = Float.parseFloat(new DecimalFormat(ConstSign.DECIMAL_2_MONEY).format(driverDivide));

        }

        return driverDivide;

    }



}
