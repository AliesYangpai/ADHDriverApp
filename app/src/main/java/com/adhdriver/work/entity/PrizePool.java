package com.adhdriver.work.entity;

/**
 * Created by Administrator on 2017/7/10.
 * 类描述 奖池信息的实体类
 * 版本
 */

public class PrizePool {



//    {
//        "prize_pool_id": 0,
//            "amount": 0,
//            "balance": 0,
//            "create_time": "2017-07-10T02:19:45.839Z",
//            "received_count": 0,
//            "source": "string"
//    }


    private String prize_pool_id;
    private String amount; //奖池金额
    private String balance;
    private String create_time;
    private String received_count; //已领取人数
    private String source;


    public PrizePool() {
    }


    public String getPrize_pool_id() {
        return prize_pool_id;
    }

    public void setPrize_pool_id(String prize_pool_id) {
        this.prize_pool_id = prize_pool_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getReceived_count() {
        return received_count;
    }

    public void setReceived_count(String received_count) {
        this.received_count = received_count;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "PrizePool{" +
                "prize_pool_id='" + prize_pool_id + '\'' +
                ", amount='" + amount + '\'' +
                ", balance='" + balance + '\'' +
                ", create_time='" + create_time + '\'' +
                ", received_count='" + received_count + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
