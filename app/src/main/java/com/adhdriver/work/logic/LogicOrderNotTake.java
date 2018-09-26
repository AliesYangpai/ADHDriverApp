package com.adhdriver.work.logic;

import com.adhdriver.work.constant.ConstParams;
import com.adhdriver.work.entity.driver.orders.AdditionService;
import com.adhdriver.work.entity.driver.orders.City;
import com.adhdriver.work.entity.driver.orders.County;
import com.adhdriver.work.entity.driver.orders.Fee;
import com.adhdriver.work.entity.driver.orders.FeeDetail;
import com.adhdriver.work.entity.driver.orders.From;
import com.adhdriver.work.entity.driver.orders.Goods;
import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.entity.driver.orders.Province;
import com.adhdriver.work.entity.driver.orders.To;
import com.adhdriver.work.entity.driver.orders.Vehicle;
import com.adhdriver.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created by Administrator on 2017/12/4.
 * 类描述  未接单的逻辑方法处理类
 * 版本
 */

public class LogicOrderNotTake {

    private VertifyNotNull vertifyNotNull;


    public LogicOrderNotTake() {
        this.vertifyNotNull = new VertifyNotNull();
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
     * 零担order
     *
     * @param order
     * @return
     */
    public Order getPartLoadOrder(Order order) {

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
     * 获取 费用明细列表
     *
     * @param order
     * @return
     */
    public List<FeeDetail> getFeeDetailList(Order order) {

        List<FeeDetail> details = null;

        if (vertifyNotNull.isNotNullObj(order)) {
            Fee fee = order.getFee();
            if (null != fee) {
                details = fee.getDetails();
            }
        }

        return details;

    }

    /**
     * 获取额外服务列表
     *
     * @param order
     * @return
     */
    public List<AdditionService> getAdditionServiceList(Order order) {

        List<AdditionService> additionServices = null;
        if (vertifyNotNull.isNotNullObj(order)) {
            additionServices = order.getAddition_services();
        }
        return additionServices;
    }


    /**
     * 获取货物对象
     * @param order
     * @return
     */
    public Goods getGood(Order order) {
        Goods goods = null;

        if (vertifyNotNull.isNotNullObj(order)) {
            goods = order.getGoods();
        }

        return goods;
    }


    /**
     * 是否是营业部一口价订单
     * @param fix
     * @return
     */
    public boolean isOverOfficeFix(boolean fix) {
        return fix;
    }


    public boolean isSameCity(String businessType) {
        return ConstParams.Orders.SAME_CITY.equals(businessType);
    }

    public boolean isFullLoad(String businessType) {
        return ConstParams.Orders.FULL_LOAD.equals(businessType);
    }

    public boolean isOverOffice(String businessType) {
        return ConstParams.Orders.OVER_OFFICE.equals(businessType);
    }

}
