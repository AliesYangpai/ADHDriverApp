package com.adhdriver.work.entity.driver.orders;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/5/10.
 * 类描述  订单表
 * 版本
 */

public class Order implements Serializable {


    /**
     * 订单基本信息
     */
    private String order_no; //订单id
    private String user_id;//用户id（这里是发单的用户id）
    private String user_avatar;//用户头像
    private String user_name;//用户名称
    private String business_type;//货运类型“零担、整车、同城”
    private String mode;//【待定】(顺风车、专车、运营中心) ['Hitchhike', 'Carload', 'OverOffice'],
    private String book_time;//用车时间
    private String departure_time;//司机出发时间

    /**
     * from
     */
    private From from;
    /**
     * to
     */
    private To to;
    private String distance;//距离
    /**
     * goods
     * 货物信息
     */
    private Goods goods;
    private String payer;//该订单是收货方付款还是发货方付款 ['Shipper', 'Receiver'],
    /**
     * 额外服务
     */
    private List<AdditionService> addition_services;
    private Fee fee;


    /**
     * 无~~~payments
     */

    private String create_time;//订单创建时间
    private String status;//订单状态  ['NewCreate', 'DuePayment', 'Pending', 'Assigned', 'Weighing', 'Processing', 'Completed', 'Failed', 'Canceled', 'Refunded'],
    private boolean is_same_city;
    private String same_city_standard_code;//    同城城市编码


    /**
     * 无~~~coupons
     */
    private boolean is_first;//是否首单
    private String estimate_fee;//司机预估专车运费 ,竞价订单估价
    private boolean is_received_first_reward;//是否已领取首单奖励
    private String traffic_type;//运输类型 = ['SameCity', 'InterCity']

    /**
     * 车辆类型
     */
    private Vehicle vehicle;








    /**
     * ===================================================
     */
    private Assign assigned;//是否指派
    private Divided divided;
    private String remark;//给司机捎句话
    private String driver_quote_status;//在仅仅应用在 我参与的 界面中专车竞价状态。 = ['Pending', 'Succeed', 'Failed'], 201707028
    private String reach_begin_location_time;//司机到达发货人货物地点时间
    private String arrival_time;//我已到达时间 用来判断用户是否已经到到达
    private String pay_difference_time;  //用户补差价时间
    private String pay_difference;//复称金额
    private String parcel_no;//用于喜来未集包，这两个字段联合判断是否是喜来的订单
    private String TaskState; //用于喜来的未集包


    /**
     * 用于营业部的一口价订单
     */
    private boolean is_fixed_price;
    private FixedPriceFee fixed_price_fee;


    /**
     * 用于整车快捷订单组合判断使用
     */
    private TempAssigned temp_assigned;
    private String paid_time;  //判断这个整车快捷订单用户端是否发生过支付

    private float driver_proporition;//司机分成比例


    private List<PathPoint> path_points;


    private boolean is_quoted;

    private List<String> quote_driver_ids;


    public Order() {
    }


    public List<PathPoint> getPath_points() {
        return path_points;
    }

    public void setPath_points(List<PathPoint> path_points) {
        this.path_points = path_points;
    }

    public TempAssigned getTemp_assigned() {
        return temp_assigned;
    }

    public void setTemp_assigned(TempAssigned temp_assigned) {
        this.temp_assigned = temp_assigned;
    }

    public String getTaskState() {
        return TaskState;
    }

    public void setTaskState(String taskState) {
        TaskState = taskState;
    }

    public String getPay_difference_time() {
        return pay_difference_time;
    }

    public void setPay_difference_time(String pay_difference_time) {
        this.pay_difference_time = pay_difference_time;
    }

    public String getPay_difference() {
        return pay_difference;
    }

    public void setPay_difference(String pay_difference) {
        this.pay_difference = pay_difference;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getReach_begin_location_time() {
        return reach_begin_location_time;
    }

    public void setReach_begin_location_time(String reach_begin_location_time) {
        this.reach_begin_location_time = reach_begin_location_time;
    }

    public String getDriver_quote_status() {
        return driver_quote_status;
    }

    public void setDriver_quote_status(String driver_quote_status) {
        this.driver_quote_status = driver_quote_status;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrder_no() {
        return order_no;
    }


    public String getParcel_no() {
        return parcel_no;
    }

    public void setParcel_no(String parcel_no) {
        this.parcel_no = parcel_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public String getBusiness_type() {
        return business_type;
    }

    public void setBusiness_type(String business_type) {
        this.business_type = business_type;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getBook_time() {
        return book_time;
    }

    public void setBook_time(String book_time) {
        this.book_time = book_time;
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

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public List<AdditionService> getAddition_services() {
        return addition_services;
    }

    public void setAddition_services(List<AdditionService> addition_services) {
        this.addition_services = addition_services;
    }

    public Fee getFee() {
        return fee;
    }

    public void setFee(Fee fee) {
        this.fee = fee;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean is_same_city() {
        return is_same_city;
    }

    public void setIs_same_city(boolean is_same_city) {
        this.is_same_city = is_same_city;
    }

    public String getSame_city_standard_code() {
        return same_city_standard_code;
    }

    public void setSame_city_standard_code(String same_city_standard_code) {
        this.same_city_standard_code = same_city_standard_code;
    }

    public boolean is_first() {
        return is_first;
    }

    public void setIs_first(boolean is_first) {
        this.is_first = is_first;
    }

    public String getEstimate_fee() {
        return estimate_fee;
    }

    public void setEstimate_fee(String estimate_fee) {
        this.estimate_fee = estimate_fee;
    }

    public boolean is_received_first_reward() {
        return is_received_first_reward;
    }

    public void setIs_received_first_reward(boolean is_received_first_reward) {
        this.is_received_first_reward = is_received_first_reward;
    }

    public String getTraffic_type() {
        return traffic_type;
    }

    public void setTraffic_type(String traffic_type) {
        this.traffic_type = traffic_type;
    }


    public Assign getAssigned() {
        return assigned;
    }

    public void setAssigned(Assign assigned) {
        this.assigned = assigned;
    }

    public Divided getDivided() {
        return divided;
    }

    public void setDivided(Divided divided) {
        this.divided = divided;
    }


    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }


    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean is_fixed_price() {
        return is_fixed_price;
    }

    public void setIs_fixed_price(boolean is_fixed_price) {
        this.is_fixed_price = is_fixed_price;
    }


    public FixedPriceFee getFixed_price_fee() {
        return fixed_price_fee;
    }

    public void setFixed_price_fee(FixedPriceFee fixed_price_fee) {
        this.fixed_price_fee = fixed_price_fee;
    }


    public String getPaid_time() {
        return paid_time;
    }

    public void setPaid_time(String paid_time) {
        this.paid_time = paid_time;
    }


    public float getDriver_proporition() {
        return driver_proporition;
    }

    public void setDriver_proporition(float driver_proporition) {
        this.driver_proporition = driver_proporition;
    }


    public boolean is_quoted() {
        return is_quoted;
    }

    public void setIs_quoted(boolean is_quoted) {
        this.is_quoted = is_quoted;
    }

    public List<String> getQuote_driver_ids() {
        return quote_driver_ids;
    }

    public void setQuote_driver_ids(List<String> quote_driver_ids) {
        this.quote_driver_ids = quote_driver_ids;
    }


    @Override
    public String toString() {
        return "Order{" +
                "order_no='" + order_no + '\'' +
                ", user_id='" + user_id + '\'' +
                ", user_avatar='" + user_avatar + '\'' +
                ", user_name='" + user_name + '\'' +
                ", business_type='" + business_type + '\'' +
                ", mode='" + mode + '\'' +
                ", book_time='" + book_time + '\'' +
                ", departure_time='" + departure_time + '\'' +
                ", from=" + from +
                ", to=" + to +
                ", distance='" + distance + '\'' +
                ", goods=" + goods +
                ", payer='" + payer + '\'' +
                ", addition_services=" + addition_services +
                ", fee=" + fee +
                ", create_time='" + create_time + '\'' +
                ", status='" + status + '\'' +
                ", is_same_city=" + is_same_city +
                ", same_city_standard_code='" + same_city_standard_code + '\'' +
                ", is_first=" + is_first +
                ", estimate_fee='" + estimate_fee + '\'' +
                ", is_received_first_reward=" + is_received_first_reward +
                ", traffic_type='" + traffic_type + '\'' +
                ", vehicle=" + vehicle +
                ", assigned=" + assigned +
                ", divided=" + divided +
                ", remark='" + remark + '\'' +
                ", driver_quote_status='" + driver_quote_status + '\'' +
                ", reach_begin_location_time='" + reach_begin_location_time + '\'' +
                ", arrival_time='" + arrival_time + '\'' +
                ", pay_difference_time='" + pay_difference_time + '\'' +
                ", pay_difference='" + pay_difference + '\'' +
                ", parcel_no='" + parcel_no + '\'' +
                ", TaskState='" + TaskState + '\'' +
                ", is_fixed_price=" + is_fixed_price +
                ", fixed_price_fee=" + fixed_price_fee +
                ", temp_assigned=" + temp_assigned +
                ", paid_time='" + paid_time + '\'' +
                ", driver_proporition=" + driver_proporition +
                ", path_points=" + path_points +
                ", is_quoted=" + is_quoted +
                ", quote_driver_ids=" + quote_driver_ids +
                '}';
    }
}
