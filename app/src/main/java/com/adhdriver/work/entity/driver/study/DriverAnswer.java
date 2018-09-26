package com.adhdriver.work.entity.driver.study;

/**
 * Created by Administrator on 2017/6/16.
 * 类描述  司机答案的实体类
 * 版本
 */

public class DriverAnswer {

    private int index;   //选择答案时候的按钮索引

    private int rb_id;   //选择答案时候的按钮id

    private int selfAnswerId; //自己的答案


    public DriverAnswer() {
    }


    public DriverAnswer(int index, int rb_id, int selfAnswerId) {
        this.index = index;
        this.rb_id = rb_id;
        this.selfAnswerId = selfAnswerId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getRb_id() {
        return rb_id;
    }

    public void setRb_id(int rb_id) {
        this.rb_id = rb_id;
    }

    public int getSelfAnswerId() {
        return selfAnswerId;
    }

    public void setSelfAnswerId(int selfAnswerId) {
        this.selfAnswerId = selfAnswerId;
    }

    @Override
    public String toString() {
        return "DriverAnswer{" +
                "index=" + index +
                ", rb_id=" + rb_id +
                ", selfAnswerId=" + selfAnswerId +
                '}';
    }
}
