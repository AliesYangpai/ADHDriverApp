package com.adhdriver.work.logic;

import com.adhdriver.work.constant.ConstParams;
import com.adhdriver.work.constant.ConstTag;
import com.adhdriver.work.entity.driver.orders.City;
import com.adhdriver.work.entity.driver.orders.County;
import com.adhdriver.work.entity.driver.orders.Fee;
import com.adhdriver.work.entity.driver.orders.FeeDetail;
import com.adhdriver.work.entity.driver.orders.From;
import com.adhdriver.work.entity.driver.orders.Goods;
import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.entity.driver.orders.PathPoint;
import com.adhdriver.work.entity.driver.orders.Province;
import com.adhdriver.work.entity.driver.orders.To;
import com.adhdriver.work.entity.driver.orders.Vehicle;
import com.adhdriver.work.function.FunctionFee;
import com.adhdriver.work.function.FunctionOrder;
import com.adhdriver.work.function.FunctionPathPoint;
import com.adhdriver.work.verification.VertifyNotNull;

import org.feezu.liuli.timeselector.Utils.TextUtil;

import java.util.List;

/**
 * Created by Administrator on 2018/1/24.
 * 类描述   已经接受的订单的 逻辑类
 * 版本
 */

public class LogicOrderTake {


    private FunctionPathPoint functionPathPoint;
    private FunctionOrder functionOrder;

    private FunctionFee functionFee;

    private VertifyNotNull vertifyNotNull;

    public LogicOrderTake() {
        this.functionPathPoint = new FunctionPathPoint();
        this.functionOrder = new FunctionOrder();
        this.functionFee = new FunctionFee();
        this.vertifyNotNull = new VertifyNotNull();
    }


    public boolean isSameCity(String businessType) {
        return businessType.equals(ConstParams.Orders.SAME_CITY);
    }

    public boolean isFullLoad(String businessType) {
        return businessType.equals(ConstParams.Orders.FULL_LOAD);
    }

    public boolean isOverOffice(String businessType) {
        return businessType.equals(ConstParams.Orders.OVER_OFFICE);
    }





    /**
     * 获取同城的order
     *
     * @param order
     * @return
     */
    public Order getSameCityOrder(Order order) {

        Order targetOrder = null;

        From from = order.getFrom();
        if (vertifyNotNull.isNullObj(from)) {
            return targetOrder;
        }

        Province fromProvince = from.getProvince();
        if (vertifyNotNull.isNullObj(fromProvince)) {
            return targetOrder;
        }


        City fromCity = from.getCity();
        if (vertifyNotNull.isNullObj(fromCity)) {
            return targetOrder;
        }

        County fromCounty = from.getCounty();
        if (vertifyNotNull.isNullObj(fromCounty)) {
            return targetOrder;
        }


        To to = order.getTo();
        if (vertifyNotNull.isNullObj(to)) {
            return targetOrder;
        }

        Province toProvince = to.getProvince();
        if (vertifyNotNull.isNullObj(toProvince)) {
            return targetOrder;
        }

        City toCity = to.getCity();
        if (vertifyNotNull.isNullObj(toCity)) {
            return targetOrder;
        }

        County toCounty = to.getCounty();
        if (vertifyNotNull.isNullObj(toCounty)) {
            return targetOrder;
        }


        Fee fee = order.getFee();
        if (vertifyNotNull.isNullObj(fee)) {
            return targetOrder;
        }

        Vehicle vehicle = order.getVehicle();
        if (vertifyNotNull.isNullObj(vehicle)) {
            return targetOrder;
        }



        targetOrder = order;

        return targetOrder;

    }








    /**
     * 获取同城的orderList
     *
     * @param order
     * @return
     */
    public Order getSameCityOrderInItem(Order order) {

        Order targetOrder = null;

        From from = order.getFrom();
        if (vertifyNotNull.isNullObj(from)) {
            return targetOrder;
        }

        Province fromProvince = from.getProvince();
        if (vertifyNotNull.isNullObj(fromProvince)) {
            return targetOrder;
        }


        City fromCity = from.getCity();
        if (vertifyNotNull.isNullObj(fromCity)) {
            return targetOrder;
        }

        County fromCounty = from.getCounty();
        if (vertifyNotNull.isNullObj(fromCounty)) {
            return targetOrder;
        }


        To to = order.getTo();
        if (vertifyNotNull.isNullObj(to)) {
            return targetOrder;
        }

        Province toProvince = to.getProvince();
        if (vertifyNotNull.isNullObj(toProvince)) {
            return targetOrder;
        }

        City toCity = to.getCity();
        if (vertifyNotNull.isNullObj(toCity)) {
            return targetOrder;
        }

        County toCounty = to.getCounty();
        if (vertifyNotNull.isNullObj(toCounty)) {
            return targetOrder;
        }


        Fee fee = order.getFee();
        if (vertifyNotNull.isNullObj(fee)) {
            return targetOrder;
        }

        Vehicle vehicle = order.getVehicle();
        if (vertifyNotNull.isNullObj(vehicle)) {
            return targetOrder;
        }


        targetOrder = order;

        return targetOrder;

    }





    /**
     * 整车Order
     *
     * @param order
     * @return
     */
    public Order getFullLoadOrder(Order order) {

        Order targetOrder = null;


        From from = order.getFrom();
        if (vertifyNotNull.isNullObj(from)) {
            return targetOrder;
        }

        Province fromProvince = from.getProvince();
        if (vertifyNotNull.isNullObj(fromProvince)) {
            return targetOrder;
        }


        City fromCity = from.getCity();
        if (vertifyNotNull.isNullObj(fromCity)) {
            return targetOrder;
        }

        County fromCounty = from.getCounty();
        if (vertifyNotNull.isNullObj(fromCounty)) {
            return targetOrder;
        }


        To to = order.getTo();
        if (vertifyNotNull.isNullObj(to)) {
            return targetOrder;
        }

        Province toProvince = to.getProvince();
        if (vertifyNotNull.isNullObj(toProvince)) {
            return targetOrder;
        }

        City toCity = to.getCity();
        if (vertifyNotNull.isNullObj(toCity)) {
            return targetOrder;
        }

        County toCounty = to.getCounty();
        if (vertifyNotNull.isNullObj(toCounty)) {
            return targetOrder;
        }


        Goods goods = order.getGoods();
        if (vertifyNotNull.isNullObj(goods)) {
            return targetOrder;
        }


        Vehicle vehicle = order.getVehicle();


        if (vertifyNotNull.isNullObj(vehicle)) {

            return targetOrder;

        }


        Fee fee = order.getFee();
        if (null == fee) {
            return targetOrder;
        }



        targetOrder = order;


        return targetOrder;

    }







    /**
     * 整车Order
     *
     * @param order
     * @return
     */
    public Order getFullLoadOrderInItem(Order order) {

        Order targetOrder = null;


        From from = order.getFrom();
        if (vertifyNotNull.isNullObj(from)) {
            return targetOrder;
        }

        Province fromProvince = from.getProvince();
        if (vertifyNotNull.isNullObj(fromProvince)) {
            return targetOrder;
        }


        City fromCity = from.getCity();
        if (vertifyNotNull.isNullObj(fromCity)) {
            return targetOrder;
        }

        County fromCounty = from.getCounty();
        if (vertifyNotNull.isNullObj(fromCounty)) {
            return targetOrder;
        }


        To to = order.getTo();
        if (vertifyNotNull.isNullObj(to)) {
            return targetOrder;
        }

        Province toProvince = to.getProvince();
        if (vertifyNotNull.isNullObj(toProvince)) {
            return targetOrder;
        }

        City toCity = to.getCity();
        if (vertifyNotNull.isNullObj(toCity)) {
            return targetOrder;
        }

        County toCounty = to.getCounty();
        if (vertifyNotNull.isNullObj(toCounty)) {
            return targetOrder;
        }


        Goods goods = order.getGoods();
        if (vertifyNotNull.isNullObj(goods)) {
            return targetOrder;
        }


        Vehicle vehicle = order.getVehicle();


        if (vertifyNotNull.isNullObj(vehicle)) {

            return targetOrder;

        }


        Fee fee = order.getFee();
        if (null == fee) {
            return targetOrder;
        }



        targetOrder = order;


        return targetOrder;

    }











    /**
     * 营业部Order
     *
     * @param order
     * @return
     */
    public Order getOverOfficeOrder(Order order) {

        Order targetOrder = null;



        From from = order.getFrom();
        if (vertifyNotNull.isNullObj(from)) {
            return targetOrder;
        }

        Province fromProvince = from.getProvince();
        if (vertifyNotNull.isNullObj(fromProvince)) {
            return targetOrder;
        }


        City fromCity = from.getCity();
        if (vertifyNotNull.isNullObj(fromCity)) {
            return targetOrder;
        }

        County fromCounty = from.getCounty();
        if (vertifyNotNull.isNullObj(fromCounty)) {
            return targetOrder;
        }


        To to = order.getTo();
        if (vertifyNotNull.isNullObj(to)) {
            return targetOrder;
        }

        Province toProvince = to.getProvince();
        if (vertifyNotNull.isNullObj(toProvince)) {
            return targetOrder;
        }

        City toCity = to.getCity();
        if (vertifyNotNull.isNullObj(toCity)) {
            return targetOrder;
        }

        County toCounty = to.getCounty();
        if (vertifyNotNull.isNullObj(toCounty)) {
            return targetOrder;
        }


        Goods goods = order.getGoods();
        if (vertifyNotNull.isNullObj(goods)) {
            return targetOrder;
        }

        Fee fee = order.getFee();
        if (vertifyNotNull.isNullObj(fee)) {
            return targetOrder;
        }



        targetOrder = order;


        return targetOrder;

    }



    /**
     * 订单已完成
     *
     * @param status
     * @return
     */
    public boolean isComplete(String status) {
        return status.equals(ConstParams.OrderStatus.COMPLETED);
    }


    /**
     * 订单已取消
     *
     * @param status
     * @return
     */
    public boolean isCanceled(String status) {
        return status.equals(ConstParams.OrderStatus.Canceled);
    }


    /**
     * 订单已退款
     *
     * @param status
     * @return
     */
    public boolean isRefund(String status) {
        return status.equals(ConstParams.OrderStatus.Refund);
    }

    /**
     * 进行中
     *
     * @param status
     * @return
     */
    public boolean isInTransit(String status) {
        return status.equals(ConstParams.OrderStatus.InTransit);
    }









    public boolean isNotDepartSameCity(String depart_time) {
        return TextUtil.isEmpty(depart_time);
    }


    /**
     * 判断整车是否出发
     *
     * @param
     * @return
     */
    public boolean isNotDepartForFullLoad(String depart_time) {
        return TextUtil.isEmpty(depart_time);
    }




    public boolean isNotDepartForOverOffice(String departTime) {
        return TextUtil.isEmpty(departTime);
    }


    public boolean isNotArriveForOverOffice(String arriveTime) {

        return TextUtil.isEmpty(arriveTime);
    }

    /**
     * 发货方付款
     *
     * @param payer
     * @return
     */
    public boolean isShipper(String payer) {
        return payer.equals(ConstParams.PaySide.SHIPPER);
    }

    /**
     * 收货方付款
     *
     * @param payer
     * @return
     */
    public boolean isReciver(String payer) {
        return payer.equals(ConstParams.PaySide.RECIVER);
    }


    /**
     * 有代收货款
     *
     * @return
     */
    public boolean isHasCod(Fee fee) {
        boolean result = false;
        String cod = functionOrder.getCod(fee);
        if (cod.equals(ConstTag.AdditionServiceTag.COD)) {
            result = true;
        }
        return result;
    }





    /**
     *
     * 同城已到达判断
     * @param
     * @return
     */
    public boolean isHasArrivedForSameCityNoPathPoint(String arrive_time) {

        return !TextUtil.isEmpty(arrive_time);
    }



    public boolean isHasArrivedForSameCity(String arrive_time) {

        return !TextUtil.isEmpty(arrive_time);
    }


    /**
     *
     * 整车已到达判断
     * @param
     * @return
     */
    public boolean isHasArrivedForFullLoad(String arrive_time) {

        return !TextUtil.isEmpty(arrive_time);
    }


    /**
     * 判断发货方是否付款
     *
     * @param fee
     * @return
     */
    public boolean isHasPaid(Fee fee) {


        boolean result = false;
        float shipperHadPaidAll = functionFee.getShipperFullLoadHadPaid(fee);
        float shipperShouldPaidAll = functionFee.getShipperFullLoadShouldPay(fee);

        if (shipperShouldPaidAll == 0 && shipperHadPaidAll == 0) {
            return result;
        }
        if (shipperShouldPaidAll - shipperHadPaidAll == 0) {
            result = true;
        }
        return result;

    }

    /**
     * 是否是营业部一口价订单
     * @param fix
     * @return
     */
    public boolean isOverOfficeFix(boolean fix) {
        return fix;
    }



    public boolean isHasPathPoints(List<PathPoint> pathPoints) {

        return  null!= pathPoints && pathPoints.size()>0;
    }
}
