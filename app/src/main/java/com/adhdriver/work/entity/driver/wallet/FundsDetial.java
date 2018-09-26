package com.adhdriver.work.entity.driver.wallet;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/6.
 * 类描述  零钱明细
 * 版本
 */

public class FundsDetial implements Serializable{


    private String fund_flow_no;//   资金流水编号。
    private String organization_id;//    合伙人编号
    private String user_id;//用户编号
    private String driver_id;//     司机编号。
    private String wallet_id;//    用户钱包编号。
    private String order_no;//    订单编号。 ,
    private String amount;//    金额。 ,
    private String income;//    收入。 ,
    private String outlay;//    支出。 ,
    private String create_time;//    创建时间。
    private String pay_mode;//    支付方式。 ,
    private String flow_direction;//    流水方向。 ,
    private String flow_comment;//    备注。


    public FundsDetial() {
    }


    public String getFund_flow_no() {
        return fund_flow_no;
    }

    public void setFund_flow_no(String fund_flow_no) {
        this.fund_flow_no = fund_flow_no;
    }

    public String getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(String organization_id) {
        this.organization_id = organization_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getWallet_id() {
        return wallet_id;
    }

    public void setWallet_id(String wallet_id) {
        this.wallet_id = wallet_id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getOutlay() {
        return outlay;
    }

    public void setOutlay(String outlay) {
        this.outlay = outlay;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getPay_mode() {
        return pay_mode;
    }

    public void setPay_mode(String pay_mode) {
        this.pay_mode = pay_mode;
    }

    public String getFlow_direction() {
        return flow_direction;
    }

    public void setFlow_direction(String flow_direction) {
        this.flow_direction = flow_direction;
    }

    public String getFlow_comment() {
        return flow_comment;
    }

    public void setFlow_comment(String flow_comment) {
        this.flow_comment = flow_comment;
    }

    @Override
    public String toString() {
        return "FundsDetial{" +
                "fund_flow_no='" + fund_flow_no + '\'' +
                ", organization_id='" + organization_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", driver_id='" + driver_id + '\'' +
                ", wallet_id='" + wallet_id + '\'' +
                ", order_no='" + order_no + '\'' +
                ", amount='" + amount + '\'' +
                ", income='" + income + '\'' +
                ", outlay='" + outlay + '\'' +
                ", create_time='" + create_time + '\'' +
                ", pay_mode='" + pay_mode + '\'' +
                ", flow_direction='" + flow_direction + '\'' +
                ", flow_comment='" + flow_comment + '\'' +
                '}';
    }
}
