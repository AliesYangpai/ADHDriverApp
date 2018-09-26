package com.adhdriver.work.constant;

/**
 * Created by Administrator on 2017/5/17.
 * 类描述
 * 版本
 */

public class ConstEvent {

    /**
     * 同城抢单成功
     */

    public static final int GRAB_SUCCESS_CITY_WIDE = 1;


    /**
     * 整车抢单成功
     */
    public static final int GRAB_SUCCESS_LESS_THAN_CARLOAD = 23;


    /**
     * 营业部抢单成功
     */
    public static final int GRAB_SUCCESS_OVER_OFFICE = 24;


    /**
     * 竞价成功
     */

    public static final int BIDDING_SUCCESS= 2;


    /**
     * 进入到称重界面
     */


    public static final int TO_TAKE_GOODS = 3;  //activity中设置cb选中取货中


    public static final int CANCEL_ORDER_TEKE_GOODS = 4; //取货中界面取消订单


    public static final int TO_ORDERING = 5;


    public static final int TO_FINSH = 6;//activiti中设置cb选中已完成


    public static final int TO_CHANGE_WEIGHT = 7;//司机修改价格


    public static final int REG_TO_SET_VECHICLE_NUM = 8;//注册时候设置车牌号的返回


    public static final int TO_EXAM_PASS = 9;//考试通过


    public static final int CANCEL_ORDER_TAKE = 10; //已接单但没有进入到复称时取消订单


    public static final int LOGIN_TO_INIT_UI = 11; //登陆后修改上传完毕经纬度，再变更下面的Ui


    /**
     * wx支付成功
     */
    public static final int WX_PAY_SUCCESS_BACK = 12;  //微信支付成功


    /**
     * wx支付失败
     */
    public static final int WX_PAY_FAIL_BACK = 13;  //微信支付失败



    public static final int ADD_SET_VECHICLE_NUM = 14;//添加车辆时设置车牌号的返回



    public static final int BUSSINESS_DRIVER_LOCATION = 15;//定位获取司机的经纬度



    public static final int BUSINESS_DEL_DRIVER_AND_FRESH = 16;//企业删除司机后发出的事件


    /**
     * ==================================大改相关===============================
     */

    public static final int ORDER_ABOUT = 17;//订单相关，用于进入到 订单分页



    public static final int ORDER_ABOUT_SET_TAG_ONLY = 22;//订单相关，用于主页4bar点击后再点击订单分页，界面没有记录 上次的订单状态的选中记录




    public static final int SET_PATH_TO_FG_MINE = 27;//发送pic到FragmentMine设置图片

    public static final int EXAM_SUCCESS_TO_MAIN = 28;//考试通过

    /**
     * 订单相关的子tag，用来标记进入到“订单”分页的“已接单”、“取货中”、“进行中”、“已完成”
     *
     */
    public class OrderChild {

        public static final int  TAKE= 18;  //已接单 分页

        public static final int  TAKE_GOODS= 19; //取货中 分页

        public static final int  ORDERING= 20;//进行中 分页

        public static final int  FINISH= 21;// 已完成 分页

    }




    /**
     * 订单相关的子tag，用来标记进入到“订单”分页的“已接单”、“取货中”、“进行中”、“已完成”
     *
     */
    public class WxAuthAbout {

        public static final int  SUCCESS= 25;  //微信授权成功

        public static final int  CANCEL= 26; //微信授权取消

        public static final int  FAIL= 27;//微信授权失败


    }




}
