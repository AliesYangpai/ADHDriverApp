package com.adhdriver.work.push;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/22.
 * 类描述   推送字段
 * 版本
 */

public class PushEntity implements Serializable{



//    {"PushScopeType":"S000001705E0WEMH","OrderNo":0,"Status":2}




    private String PushScopeType;//推送类型 “订单相关”、“扫码添加相关”
    private String OrderNo; //订单号
    private String BusinessType;// 订单类型
    private String Status; //订单状态

    private String CancelReason; //取消原因

    private String FromProvince; //出发地 省
    private String FromCity; //出发地市
    private String FromCounty; //出发地区


    private String ToProvince; //目的地 省
    private String ToCity; //目的地市
    private String ToCounty; //目的地区

    private String FeeTotal;//运费






    private String DriverQuoteStatus;  //竞价状态，用于竞价
    private String PayDifferenceTime; //复称修改支付时间
    private String bissinessId;//企业添加司机相关


    private String  BusinessTypeChildType;//一口价订单（用于整车）  //QuickFullLoadOrder

    private String PaidTime; //一口价订单（用于整车）  //付款推送

    private float DriverProporition;//分成比例，仅仅适用于整车 快捷订单

    public PushEntity() {
    }

    public String getPushScopeType() {
        return PushScopeType;
    }

    public void setPushScopeType(String pushScopeType) {
        PushScopeType = pushScopeType;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }

    public String getBusinessType() {
        return BusinessType;
    }

    public void setBusinessType(String businessType) {
        BusinessType = businessType;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getCancelReason() {
        return CancelReason;
    }

    public void setCancelReason(String cancelReason) {
        CancelReason = cancelReason;
    }

    public String getFromProvince() {
        return FromProvince;
    }

    public void setFromProvince(String fromProvince) {
        FromProvince = fromProvince;
    }

    public String getFromCity() {
        return FromCity;
    }

    public void setFromCity(String fromCity) {
        FromCity = fromCity;
    }

    public String getFromCounty() {
        return FromCounty;
    }

    public void setFromCounty(String fromCounty) {
        FromCounty = fromCounty;
    }

    public String getToProvince() {
        return ToProvince;
    }

    public void setToProvince(String toProvince) {
        ToProvince = toProvince;
    }

    public String getToCity() {
        return ToCity;
    }

    public void setToCity(String toCity) {
        ToCity = toCity;
    }

    public String getToCounty() {
        return ToCounty;
    }

    public void setToCounty(String toCounty) {
        ToCounty = toCounty;
    }

    public String getFeeTotal() {
        return FeeTotal;
    }

    public void setFeeTotal(String feeTotal) {
        FeeTotal = feeTotal;
    }

    public String getDriverQuoteStatus() {
        return DriverQuoteStatus;
    }

    public void setDriverQuoteStatus(String driverQuoteStatus) {
        DriverQuoteStatus = driverQuoteStatus;
    }

    public String getPayDifferenceTime() {
        return PayDifferenceTime;
    }

    public void setPayDifferenceTime(String payDifferenceTime) {
        PayDifferenceTime = payDifferenceTime;
    }

    public String getBissinessId() {
        return bissinessId;
    }

    public void setBissinessId(String bissinessId) {
        this.bissinessId = bissinessId;
    }




    public String getPaidTime() {
        return PaidTime;
    }

    public void setPaidTime(String paidTime) {
        PaidTime = paidTime;
    }

    public String getBusinessTypeChildType() {
        return BusinessTypeChildType;
    }

    public void setBusinessTypeChildType(String businessTypeChildType) {
        BusinessTypeChildType = businessTypeChildType;
    }


    public float getDriverProporition() {
        return DriverProporition;
    }

    public void setDriverProporition(float driverProporition) {
        DriverProporition = driverProporition;
    }


    @Override
    public String toString() {
        return "PushEntity{" +
                "PushScopeType='" + PushScopeType + '\'' +
                ", OrderNo='" + OrderNo + '\'' +
                ", BusinessType='" + BusinessType + '\'' +
                ", Status='" + Status + '\'' +
                ", CancelReason='" + CancelReason + '\'' +
                ", FromProvince='" + FromProvince + '\'' +
                ", FromCity='" + FromCity + '\'' +
                ", FromCounty='" + FromCounty + '\'' +
                ", ToProvince='" + ToProvince + '\'' +
                ", ToCity='" + ToCity + '\'' +
                ", ToCounty='" + ToCounty + '\'' +
                ", FeeTotal='" + FeeTotal + '\'' +
                ", DriverQuoteStatus='" + DriverQuoteStatus + '\'' +
                ", PayDifferenceTime='" + PayDifferenceTime + '\'' +
                ", bissinessId='" + bissinessId + '\'' +
                ", BusinessTypeChildType='" + BusinessTypeChildType + '\'' +
                ", PaidTime='" + PaidTime + '\'' +
                ", DriverProporition=" + DriverProporition +
                '}';
    }
}
