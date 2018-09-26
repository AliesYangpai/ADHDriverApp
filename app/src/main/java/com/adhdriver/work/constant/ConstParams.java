package com.adhdriver.work.constant;

/**
 * Created by Administrator on 2017/11/20.
 * 类描述
 * 版本
 */

public class ConstParams {












    public class Orders {



        public static final String SAME_CITY = "SameCity"; //同城参数
        public static final String PART_LOAD = "PartLoad"; //零担参数
        public static final String FULL_LOAD = "FullLoad"; //整车参数
        public static final String OVER_OFFICE = "OverOffice"; //营业部参数



    }


    /**
     * 付款方式
     */
    public class PaySide {



        public static final String RECIVER = "Receiver"; //收货方付款
        public static final String SHIPPER = "Shipper"; //发货方付款

    }



    /**
     * 付款方式
     */
    public class Config {
        public static final String OS_TYPE = "Android"; //
        public static final String SOURCE  = "DriverApp" ;
    }




    public class OrderStatus {






        /**
         * 待分配（竞价中也有应用，在竞价中，表示 竞价中...）
         */


        public static final String Pending = "Pending";


        /**
         * 用于竞价成功的状态（存在于我参与的竞价）
         */
        public static final String Succeed = "Succeed";

        /**
         * 用于竞价失败的状态（存在于我参与的竞价）导致失败的原因可能是因为 用户取消或用户选择其他人
         */
        public static final String Failed = "Failed";


        /**
         * 竞价结束，发生在 1.用户选择司机，但是未付款（发货时付款）2.用户选取消竞价
         */
        public static final String Close = "Close";


        /**
         * 已接单（已分配）
         */
        public static final String ASSIGNED = "Assigned";



        /**
         * 取货中
         */

        public static final String PICKING = "Picking";


        /**
         * 代补差价（用于司机端发送差价后界面变更）
         */
        public static final String Differencing = "Differencing";



        /**
         * 进行中 (此处作参数传入)
         */
        public static final String PROCESSING = "Processing";


        /**
         * 进行中 (此处作为服务端返回数据，目前仅仅应用到 竞价成功界面中进行状态判断，从而显示出当前订单的进行情况)
         */
        public static final String InTransit = "InTransit";

        /**
         * 已完成 （已签收）
         */
        public static final String COMPLETED = "Completed";


        /**
         * onCreat状态
         */


        public static final String NewCreate = "NewCreate";


        /**
         * Canceled 用户已取消
         */
        public static final String Canceled = "Canceled";


        /**
         * Refund用户已退款
         */
        public static final String Refund = "Refunded";


    }


    /**
     * 验证码的类型
     */
    public class ConfirmCode {


//         ['Anyway', 'UserExisted', 'UserNotExisted']


        public static final String  ANYWAY = "Anyway";//用于给收货人发送验证码


        public static final String  USER_EXISTED = "UserExisted";// 用于忘记密码操作


        public static final String  USER_NOT_EXISTED = "UserNotExisted";//用于注册

    }

}
