package com.adhdriver.work.entity.driver.orders;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/11.
 * 类描述  费用细则
 * 版本
 */






public class FeeDetail implements Serializable{

    private String name; //名称。
    private String base_price;//基准价格。 ,
    private String unit_price;//单价
    private String quantity;//计价数量。 ,
    private String quoted;//价格。
    private String discount;//折扣
    private String subtotal;//折后小计
    private String payer;//该费用明细是由谁来付费 = ['Shipper', 'Receiver']
    private String associated_addition_service; //     关联的增值服务。 = ['Insured', 'Cod', 'Receipt', 'Handling', 'Pickup', 'Delivery']
    private String paid;
    private String divided_type;//分成类型 "Coupon" //优惠券


    public FeeDetail() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBase_price() {
        return base_price;
    }

    public void setBase_price(String base_price) {
        this.base_price = base_price;
    }

    public String getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(String unit_price) {
        this.unit_price = unit_price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getQuoted() {
        return quoted;
    }

    public void setQuoted(String quoted) {
        this.quoted = quoted;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getAssociated_addition_service() {
        return associated_addition_service;
    }

    public void setAssociated_addition_service(String associated_addition_service) {
        this.associated_addition_service = associated_addition_service;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }


    public String getDivided_type() {
        return divided_type;
    }

    public void setDivided_type(String divided_type) {
        this.divided_type = divided_type;
    }

    @Override
    public String toString() {
        return "FeeDetail{" +
                "name='" + name + '\'' +
                ", base_price='" + base_price + '\'' +
                ", unit_price='" + unit_price + '\'' +
                ", quantity='" + quantity + '\'' +
                ", quoted='" + quoted + '\'' +
                ", discount='" + discount + '\'' +
                ", subtotal='" + subtotal + '\'' +
                ", payer='" + payer + '\'' +
                ", associated_addition_service='" + associated_addition_service + '\'' +
                ", paid='" + paid + '\'' +
                ", divided_type='" + divided_type + '\'' +
                '}';
    }
}




























//public class FeeDetail implements Serializable{
//
//    private String name;
//    private String base_price;
//    private String unit_price;
//    private String quantity;
//    private String quoted;
//    private String discount;
//    private String subtotal;
//    private String payer;//该费用明细是由谁来付费 = ['Shipper', 'Receiver']
//    private String associated_addition_service; //     关联的增值服务。 = ['Insured', 'Cod', 'Receipt', 'Handling', 'Pickup', 'Delivery']
//
//    private String paid;
//
//
//    public FeeDetail() {
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getBase_price() {
//        return base_price;
//    }
//
//    public void setBase_price(String base_price) {
//        this.base_price = base_price;
//    }
//
//    public String getUnit_price() {
//        return unit_price;
//    }
//
//    public void setUnit_price(String unit_price) {
//        this.unit_price = unit_price;
//    }
//
//    public String getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(String quantity) {
//        this.quantity = quantity;
//    }
//
//    public String getQuoted() {
//        return quoted;
//    }
//
//    public void setQuoted(String quoted) {
//        this.quoted = quoted;
//    }
//
//    public String getDiscount() {
//        return discount;
//    }
//
//    public void setDiscount(String discount) {
//        this.discount = discount;
//    }
//
//    public String getSubtotal() {
//        return subtotal;
//    }
//
//    public void setSubtotal(String subtotal) {
//        this.subtotal = subtotal;
//    }
//
//
//    public String getPaid() {
//        return paid;
//    }
//
//    public void setPaid(String paid) {
//        this.paid = paid;
//    }
//
//    public String getPayer() {
//        return payer;
//    }
//
//    public void setPayer(String payer) {
//        this.payer = payer;
//    }
//
//    public String getAssociated_addition_service() {
//        return associated_addition_service;
//    }
//
//    public void setAssociated_addition_service(String associated_addition_service) {
//        this.associated_addition_service = associated_addition_service;
//    }
//
//    @Override
//    public String toString() {
//        return "FeeDetail{" +
//                "name='" + name + '\'' +
//                ", base_price='" + base_price + '\'' +
//                ", unit_price='" + unit_price + '\'' +
//                ", quantity='" + quantity + '\'' +
//                ", quoted='" + quoted + '\'' +
//                ", discount='" + discount + '\'' +
//                ", subtotal='" + subtotal + '\'' +
//                ", payer='" + payer + '\'' +
//                ", associated_addition_service='" + associated_addition_service + '\'' +
//                ", paid='" + paid + '\'' +
//                '}';
//    }
//}
