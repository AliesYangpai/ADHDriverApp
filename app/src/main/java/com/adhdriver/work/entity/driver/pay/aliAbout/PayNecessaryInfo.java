package com.adhdriver.work.entity.driver.pay.aliAbout;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/25.
 * 类描述  获取支付根据支付渠道获取必要支付参数
 * 版本
 */

public class PayNecessaryInfo implements Serializable{




    private String payment_record_id;
    private String order_type;
    private String reference_number;
    private String channel_id;
    private String original_amount;
    private String discount;
    private String paid_amount;
    private String result;
    private String action_arguments;


    public PayNecessaryInfo() {
    }

    public String getPayment_record_id() {
        return payment_record_id;
    }

    public void setPayment_record_id(String payment_record_id) {
        this.payment_record_id = payment_record_id;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getReference_number() {
        return reference_number;
    }

    public void setReference_number(String reference_number) {
        this.reference_number = reference_number;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public String getOriginal_amount() {
        return original_amount;
    }

    public void setOriginal_amount(String original_amount) {
        this.original_amount = original_amount;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPaid_amount() {
        return paid_amount;
    }

    public void setPaid_amount(String paid_amount) {
        this.paid_amount = paid_amount;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getAction_arguments() {
        return action_arguments;
    }

    public void setAction_arguments(String action_arguments) {
        this.action_arguments = action_arguments;
    }


    @Override
    public String toString() {
        return "PayNecessaryInfo{" +
                "payment_record_id='" + payment_record_id + '\'' +
                ", order_type='" + order_type + '\'' +
                ", reference_number='" + reference_number + '\'' +
                ", channel_id='" + channel_id + '\'' +
                ", original_amount='" + original_amount + '\'' +
                ", discount='" + discount + '\'' +
                ", paid_amount='" + paid_amount + '\'' +
                ", result='" + result + '\'' +
                ", action_arguments='" + action_arguments + '\'' +
                '}';
    }
}
