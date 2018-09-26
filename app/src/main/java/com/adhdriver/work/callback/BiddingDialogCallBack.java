package com.adhdriver.work.callback;

import com.adhdriver.work.entity.driver.orders.Order;

/**
 * Created by Administrator on 2017/12/29.
 * 类描述  竞价dialog的接口
 * 版本
 */

public interface BiddingDialogCallBack {



    void startBidding(String orderNo, String price);  //开始竞价

    void cancelBidding();//取消竞价

}
