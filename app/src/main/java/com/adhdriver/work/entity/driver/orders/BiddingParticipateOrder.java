package com.adhdriver.work.entity.driver.orders;

/**
 * Created by Administrator on 2017/7/21.
 * 类描述  我参与的竞价列表实体类，当然了，进入到竞价详情中 我们调用的是获取订单详情接口
 * 版本
 */

public class BiddingParticipateOrder {

    private String order_no;
    private String status;
    private String mode;//(顺风车、专车、运营中心) ['Hitchhike', 'Carload', 'OverOffice'],
    private From from;
    private To to;
    private Goods goods;
    private Fee fee;
    private String price;//竞价价格


    /**
     * 车辆类型
     */
    private Vehicle vehicle;

    private String user_avatar;//用户头像
    private String user_name;//用户名称

    public BiddingParticipateOrder() {
    }


    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public From getFrom() {
        return from;
    }

    public void setFrom(From from) {
        this.from = from;
    }

    public To getTo() {
        return to;
    }

    public void setTo(To to) {
        this.to = to;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }


    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }


    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }


    public Fee getFee() {
        return fee;
    }

    public void setFee(Fee fee) {
        this.fee = fee;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "BiddingParticipateOrder{" +
                "order_no='" + order_no + '\'' +
                ", status='" + status + '\'' +
                ", mode='" + mode + '\'' +
                ", from=" + from +
                ", to=" + to +
                ", goods=" + goods +
                ", fee=" + fee +
                ", price='" + price + '\'' +
                ", vehicle=" + vehicle +
                ", user_avatar='" + user_avatar + '\'' +
                ", user_name='" + user_name + '\'' +
                '}';
    }
}
