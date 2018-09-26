package com.adhdriver.work.constant;

/**
 * Created by Administrator on 2017/4/18.
 * 类描述   基本的tag标记
 * 版本
 */

public class ConstTag {




    /**
     * 费用相关
     */
    public class Fee {

        public static final String freight = "运费";

        public static final String coupon = "Coupon";//优惠券

    }



    /**
     * 额外服务相关类型
     */
    public class AdditionServiceTag {

        public static final String HANDLING = "Handling";//装卸货物
        public static final String RECEIPT = "Receipt";//回单
        public static final String INSURED  = "Insured";//保价
        public static final String COD = "Cod";//代收货款
    }



    /**
     * 信息tag
     */
    public class MessageTag {

        public static final String System = "System";  // 系统
        public static final String Activity = "Activity";//活动公告
        public static final String Feedback = "Feedback";  //意见反馈
        public static final String Withdraw = "Withdraw";//提现
        public static final String CustomerService = "CustomerService";//客服
    }





    /**
     * 信息tag
     */
    public class JsonTag {

        public static final String JSON_ARRY = "json_arry";  // jsonArray
        public static final String JSON_OBJ = "json_obj";//jsonObj

    }




    /**
     * 授权Tag
     */
    public class AuthTag {

        public static final String QQ = "QQ";  // QQ
        public static final String WX = "Weixin";//微信

    }


    /**
     * 用户信息tag验证（实名认证）
     */
    public class CertificationStatusTag {


        public static final String  PENDING = "Pending";//未认证
        public static final String  APPROVED = "Approved";//已认证
        public static final String AUTHORIZING = "Authorizing";//认证中
        public static final String REJECTED = "Rejected";//认证失败


    }



    /**
     * 车辆信息审核
     * 用于车辆信息审核
     */
    public class CarPassStatusTag {

        public static final String  PENDING = "Pending";//审核中
        public static final String  APPROVED = "Approved";//审核通过
        public static final String REJECTED = "Rejected";//审核失败

    }




    /**
     * 车辆catogroy
     */
    public class  VechileCatogoryTag {

        public static final String BOX = "Box";
        public static final String SLAB = "Slab";
        public static final String GAOLAN = "Goalan";
    }


    /**
     * 验证码的类型
     */
    public class ConfirmCodeTag {


//         ['Anyway', 'UserExisted', 'UserNotExisted']

        public static final String  ANYWAY = "Anyway";//用于给收货人发送验证码


        public static final String  USER_EXISTED = "UserExisted";// 用于忘记密码操作


        public static final String  USER_NOT_EXISTED = "UserNotExisted";//用于注册

    }



    public class DialogTag {


        public static final int REG_DENY = 1; //注册被拒绝
        public static final int START_TO_READ_AND_EXAM = 2; //注册已完成
        public static final int START_TO_SHOW_MAP_AFTER_EXAM = 3; //考试成功后查看地图
        public static final int START_TO_EXAM_FAIL = 4; //考试未通过

        public static final int GRAB_IT_CITY_WIDE = 5;//同城抢单（出现抢单dalog）

        public static final int GRAB_IT_LESS_THAN_CARLOAD = 23;//零担抢单（出现抢单dalog）

        public static final int GRAB_IT_OVER_OFFICE = 25;//营业部抢单（出现抢单dalog）


        public static final int GRAB_IT_FULLLOAD_FIXED_PRICE = 28;//整车一口价订单（出现抢单dalog）

        public static final int BIDLLING_IT = 6;//竞价（参与竞价）

        /**
         * 接单相关
         */
        public static final int REACH_TO_START_PLACE = 5; //我已到达(取货的时候)
        public static final int CHANGE_PRICE = 6; //我要改价
        public static final int CANCEL_ORDER = 7;//取消订单



        /**
         * 订单进行中
         */

        public static final int ARRIVED = 8;//我已抵达目的地




        public static final int  CANCEL_ORDER_IN_EDIT = 9;//输入原因的取消订单
        public static final int  ENTER_NEW_WEIGHT = 10;//输入我的新价格


        public static final int ENTER_RECIVER_CODE = 11;//输入收货人的code

        public static final int REG_FINISH_TO_WAIT_CHECK = 12;//注册完毕，等待审核


        public static final int FEED_BACK_SUCCESS = 13;//提交反馈成功返回

        public static final int SET_PAY_CODE = 14;//我已抵达目的地



        public static final int HAS_GET_RED_PAKETE = 15; //已经抢过红包了


        public static final int WITH_DRAW_DIPOSITE_SUCCESS = 16; //提现成功的tag


        public static final int WITH_DRAW_PASS_FORGET_TO_SET_SUCCESS = 17; //支付密码重置成功




        public static final int DO_DEPOSIT = 18;//进行提现

        public static final int DO_RECHARGE = 19 ;//进行充值



        public static final int BUSINESS_INFO_SUBMIT_SUCCEE = 20;//企业信息提交成功的dialog

        public static final int BUSINESS_INVITE_DRIVER_SUCCEE = 21;//企业邀请司机成功的dialog


        public static final int DRIVER_JOIN_SUCCESS = 22;//司机加入企业成功


        public static final int CHECK_PAY = 24;//验证是否收款的dialogTag



        public static final int VEHICLE_CHANGE_SUCCESS = 26;//车辆更换成功


        public static final int VEHICLE_CURRENT_EDIT_TO_LOGOUT = 27;//当前车辆编辑完成


        public static final int VEHICLE_CURRENT_DEL = 29;//删除当前车辆


        public static final int REFUEL_CLOSE = 30;//关闭加油界面的
    }






    /**
     * 支付tag
     */
    public class PayTag {
        public static final int  scan_qr_to_get_money = 1;//扫码向我支付

        public static final String  WX = "Weixin";//微信渠道
        public static final String  ALIPAY = "Alipay";//支付宝渠道

        public static final String  BALANCE = "Balance";//余额渠道

        public static final int  pay_deposite = 2;//提现



        public static final int  pay_rebind_chanel = 3;//重新绑定渠道


        public static final int  pay_recharge = 4;//充值

        public static final String QR_WX = "NATIVE";//用于识别微信请求的二维码标记



        public static final String channel_order_type = "Order";//获取支付渠道的参数

        public static final String channel_order_type_BalanceRecharge = "BalanceRecharge";//余额充值

        public static final String channel_order_type_DepositRecharge = "DepositRecharge";//押金充值




        public static final String channel_os_type = "Android";//获取支付渠道的参数

        public static final String channel_device_type = "Phone";//获取支付渠道的参数


        public static final String SYSTEM_PAY = "SystemPay";// 用于零钱流水，流入系统的钱

        public static final String WITHDRAWALS = "Withdrawals";// 用于零钱流水,用户提现的前

        public static final String REWARD = "Reward";// 用于零钱流水,现金奖励

        public static final String  BALANCE_RECHARGE = "BalanceRecharge"; //零钱充值【用于零钱明细的】

        public static final String  DEPOSIT_RECHARGE = "DepositRecharge"; //押金充值【用于零钱明细】



        public static final String ALI_CLIENT_CHECK_URL = "alipays://platformapi/startApp";// 支付宝客户端判断的url


        public static final String SCAN_ORDER_INFO_INIT = "scan_order_info_init";// 初始化 支付二维码 获取订单信息


        public static final String SCAN_ORDER_INFO_GET_AFTER = "scan_order_info_get_after";//  付款后判断 order是否为空的再次获取订单信息

    }




}
