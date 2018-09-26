package com.adhdriver.work.constant;

/**
 * Created by Administrator on 2016/9/22 0022.
 * 类描述  intent常量
 * 版本
 */
public class ConstIntent {

    /**
     * bundleKey
     */
    public class BundleKEY {


        public static final String DELIVERY_ACCESS_TOKEN = "delivery_access_token";//传递token
        public static final String REG_OR_FIND_PASS = "reg_or_find_pass"; //找回密码、司机、企业界面跳转key
        public static final String REG_UPLOAD_PIC = "reg_upload_pic"; //司机注册，企业注册跳转到上传图片界面
        public static final String GO_TO_STUDY_AND_EXAM = "go_to_study_and_exam";//进入到学习界面和考试界面
        public static final String GO_TO_MAP = "go_to_map";//进入到地图界面
        public static final String SHOW_ORDER_DETIAL_NOT_TAKE = "show_order_detail_not_take";//订单详情展开
        public static final String EDIT_VEHICLE_SUCCESS_BACK_ID = "edit_succece_back_id";//编辑成功后回传回来的driverVIRCLEiD


        public static final String EDIT_VEHICLE = "edit_vehicle";//编辑车辆信息
        public static final String ORDER_TYPE_IN_FG = "order_type_in_fg";//订单类型用于fragment标记（我的指派、我的抢单、我的竞价）
        public static final String REG_TO_DELIVER_USER = "reg_to_deliver_user";//注册时传递的user信息

        public static final String CHANGE_PASS = "change_pass";

        public static final String RESET_PASS = "reset_pass";//用于注册的时候重置密码


        public static final String ORDER_TO_NOT_TAKE = "order_to_not_take";//进入到抢单或 接单界面;


        public static final String DELIVER_ORDER_NO = "deliver_order_no";//传递order


        public static final String DELIVER_ORDER  = "deliver_order";
        public static final String DELIVER_ORDER_TAG  = "deliver_order_tag";

        public static final String JUMP_TAG  = "jump_tag";
        public static final String SETTING_PLACE = "set_place";



        public static final String SET_PLACE_BACK = "set_place_back";//设置地址返回



        public static final String PUSH_TO_PUSH_HANDER = "to_push_handle";//点击推送后进入到pushHanderActivty中


        public static final String SET_WEIGHT_VOLUME_TO_PUBLISH = "set_weight_volume_to_publish";


        public static final String SCAN_WAY_TO_GET_MONEY = "scan_way_to_get_money";

        public static final String SCAN_MONEY = "scan_money";

        public static final String WITHDRAW_DEPOSITE_CHANNEL = "withdraw_deposite_channel";



        public static final String WITHDRAW_MONNEY_CONTENT = "withdraw_money_content";



        public static final String WITHDRAW_WALLET = "withdraw_wallet";


        public static final String ABOUT_SET_PAYPASS = "about_set_paypass";//关于设置密码



        public static final String PAY_CHANNEL_IN_REBIND = "pay_channel_in_rebind";//重新绑定的时候，传入渠道

        public static final String AUTH_CODE_IN_REBIND = "auth_code_in_rebind";//重新绑定的时候，传入autocode


        public static final String ACT_INFO = "actinfo";//活动跳转的点击传递的实体类



        public static final String DEPOSITE_PASS_CONFIRM_CODE = "deposite_pass_confirm_code";//忘记支付密码时候传递的验证码

        public static final String BIDDING_PARTICIPATE_STATE = "bidding_participate_state";//已经参与竞价的竞价订单状态 【竞价中pending】、【竞价失败】、【竞价成功】


        public static final String BIDDING_PRICE = "bidding_price";//竞价价格，传递参数的时候非必要



        public static final String BIDSUCCESS_AND_TO_TARGER_FG = "bidsuccess_and_to_target_fg";//竞价成功，从竞价成功详情界面跳转到 订单4状态界面（1Activity多个fragment，这里要给rb设置tag）


        public static final String DEPOSIT_WAY = "deposit_way";// 用于区分是零钱提现还是 押金提现


        public static final String SHOW_VEHICLE_DETIAL = "show_vehicle_detial";// 进入到车辆详情界面



        public static final String SET_VEHICLE_NO = "set_vehicle_no";//设置车牌号码



        public static final String FIND_PASS_ROLE = "find_pass_role";//跳转 设置密码的角色


        public static final String ORDER_TYPE = "orderType";//订单类型  “同城”“零担”“整车”



        public static final String REFUEL_TO_NAVIGATE = "refuel_to_navigate";//加油界面js回调前往导航界面

        public static final String REFUEL_DRIVER_PHONE = "driver_phone";//跳转到加油界面



        public static final String HAS_PASS_BUT_NOT_THIS_CHANNEL_AUTH = "has_pass_but_not_thie_channel_auth";//跳转到加油界面

        /**
         * 企业相关
         */
        public static final String BUSINESS_DRIVER_ID = "bisiness_driver_id";//从企业司机列表跳转到司机详情界面

        public static final String BUSINESS_SHOW_DRIVER_DETAIL_MODE = "business_show_driver_detail_mode";//从企业司机列表跳转到司机详情界面的显示方式【扫一扫添加？还是进入显示】





        public static final String BUSINESS_TO_SERVICE_ORDER = "business_to_service_order";//从企业司机列表跳转到司机详情界面的显示方式【扫一扫添加？还是进入显示】





        public static final String BUSINESS_ID = "business_id";//企业id 目前用于 扫码界面跳转





        public static final String ORDER_STATE = "order_state";//订单状态，用于主页main选择订单分页，并指定订单分页中的childeFragemnt选择为哪一个






        public static final String PUSH_DIALOG = "push_dialog";//进入提示AlertActitvy


        public static final String PUSH_DIALOG_ENTITY = "push_entity";//推送的entity
        public static final String PUSH_DIALOG_ORDER = "push_order";//推送的entity


        public static final String PUSH_CLICK_OPEN = "push_click_open";//点击打开


        public static final String  MSG_DETAIL = "msg_detail";//信息详情

        public static final String  SOCKET_KICK = "socket_kick";//信息详情

    }


    public class BundleValue {

        public static final String DEFAULT_STRING = "";//默认String类型数据

        public static final int DRIVER_FIND_PASS = 1; //司机找回密码
        public static final int BUSINESS_FIND_PASS = 27; //企业找回密码
        public static final int DRIVER_REG = 2; //司机注册界面跳转value
        public static final int COMPANY_REG = 3; //企业注册界面跳转value

        public static final int REG_DRIVER_UPLOAD = 4; //司机上传图片
        public static final int REG_COMPANY_UPLOAD = 5; //企业上传图片

        public static final int DRIVER_STUDY_AND_EXAM = 6; //司机学习和考试
        public static final int COMPANY_STUDY_AND_EXAM = 7;//企业学习和考试

        public static final int DRIVER_TO_MAP = 8;//司机进入到地图界面
        public static final int COMPANY_TO_MAP = 9;//企业进入到地图界面

        public static final int GRAB_TO_ORDER_DETAIL_NOT_TAKE = 10;//抢单列表进入到界面详情（还未接单）
        public static final int BIDDING_TO_ORDER_DETAIL_NOT_TAKE = 11;//竞价列表进入到界面详情（还未接单）


        public static final int ORDER_TYPE_MY_APPOINT = 12;//我的指派界面
        public static final int ORDER_TYPE_MY_GRAB = 13;//我的抢单
        public static final int ORDER_TYPE_MY_BIDDING = 14;//我的竞价


        public static final int CHANGE_LOGIN_PASS = 15;//进入到修改登陆密码

        public static final int CHANGE_WITHDRAWW_PASS = 16;//进入到修改提现密码


        public static final int SET_START_PLACE = 17;//设置开始地点


        public static final int SET_END_PLACE = 18;//设置终止地点


        public static final int SET_PAYPASS = 19;//设置支付密码密码

        public static final int CONFIRM_PAYPASS = 20;//验证支付密码密码


        public static final int BIDSUCCESS_HAS_TAKE = 21;//竞价成功界面跳转到我的竞价界面，【已接单】

        public static final int BIDSUCCESS_TAKE_GOODS = 22;//竞价成功界面跳转到我的竞价界面，【取货中】

        public static final int BIDSUCCESS_TRAVELLING = 23;//竞价成功界面跳转到我的竞价界面，【进行中】

        public static final int BIDSUCCESS_FINISH = 24;//竞价成功界面跳转到我的竞价界面，【已完成】



        public static final int REG_TO_SET_VEHICLE_NO = 25;//注册时候设置车牌号


        public static final int ADD_TO_SET_VEHICLE_NO = 26;//添加车辆时，设置车牌号




        public static final int BUSINESS_SHOW_DRIVER_DETIAL = 29;//对应BUSINESS_SHOW_DRIVER_DETAIL_MODE 这个是显示司机详情

        public static final int BUSINESS_ADD_DRIVER_DETIAL = 30;//对应BUSINESS_SHOW_DRIVER_DETAIL_MODE 这个是显示司机详情【添加司机的时候】


        public static final int LOGIN_SUCCESS = 31;//用于登陆成功


        public static final int PUSH = 32;//用于登陆成功
    }









    /**
     * requestCode,activty跳转时，携带的请求码
     */

    public class RequestCode {


        public static final int SETTING_PLACE = 10;   //搜索地址的请求码


        public static final int VEHICLE_DETAIL_TO_EDIT = 31 ;//从车辆详情进入到编辑车辆信息界面

        public static final int GO_TO_ORDER_OPENING = 13;//跳转到进行中界面展开的activity


        public static final int GO_TO_SCAN_PAY = 16;//到系统中设置

        public static final int SET_VOLUME_WEIGHT_TO_PUBLISH = 15;   //进入设置体积和重量来发布顺风车

        public static final int APPLY_FOR_PERMISSION =200;//申请权限


        public static final int SYSYEM_SETTING = 400;//到系统中设置


        public static final int WALLET_TO_WITHDRAW = 17;//钱包界面的处理



        public static final int BIDDING_LIST_TO_DETAIL = 23;//从竞价列表进入到竞价详情；


        public static final int LOGIN_TO_REGISTER = 24 ;//从登陆界面跳转到注册界面传递的code




        public static final int GO_TO_ADD_NEW_VEHICLE = 26 ;//从车辆列表界面进入到添加车辆的界面



        public static final int GO_TO_SHOW_VEHICLE_DETAIL = 28 ;//从车辆列表界面进入到司机详界面











        public static final int BUSINESS_TO_DRIVER_DETAIL = 30 ;//进入到司机详情界面
    }

    /**
     * responseCode,目标activty销毁时，携带的返回码
     */


    public class ResponseCode {

        public static final int SETTING_START_PLACE_BACK = 11; //搜索出发地址的返回码

        public static final int SETTING_END_PLACE_BACK = 12; //搜索目的地地址的返回码


        public static final int GO_TO_ORDER_OPENING_BACK = 14;//返回展开开的activity

        public static final int GO_TO_PUBLISH_ROUTACTIVTY = 16;//返回发布顺风车界面并携带数据


        public static final int GO_TO_PUBLISH_NO_BACK = 17;//返回发布顺风车界面点击左上角返回不携带数据


        public static final int PAY_FINISH_IN_PAY_SCAN = 18;//在扫码支付验证已支付，并且发送完验证码后，之后回传并且finish



        public static final int PAY_DESTROY = 19;//在扫码支付界面点击返回


        public static final int SET_PAY_PASS = 20;//设置支付密码返回


        public static final int DO_WITHDRAW_DISPOSIT = 21;//进行提现的返回


        public static final int CONFIRM_PAY_PASS = 22;//验证支付密码



        public static final int CONFIRM_PAY_PASS_ALI_NOT_BIND =33;//阿里绑定



        public static final int CONFIRM_PAY_PASS_FOR_WX = 26;//验证微信支付密码


        public static final int BIDDING_LIST_TO_DETAIL = 24;//从竞价列表进入到竞价详情；



        public static final int LOGIN_TO_REGISTER = 25 ;//从注册返回登陆界面传递的值


        public static final int GO_TO_ADD_NEW_VEHICLE = 27 ;//车辆添加成功后的返回


        public static final int GO_TO_SHOW_VEHICLE_DETAIL_DEL = 29 ;//从车辆列表界面进入到司机详界面的删除返回

        public static final int GO_TO_SHOW_JUST_BUCK = 35;//详情界面点击返回
        public static final int VEHICLE_EDIT_SUCCESS_BACK = 32 ;//司机编辑成功的返回
    }


    /**
     * 广播相关
     */
    public class IntentAction {

        public static final String USER_EDIT_SUCCESS = "user_edit_success";

        public static final String AWAKE_WATCH_1 = "anction_awake_watch1";//


        public static final String AWAKE_WATCH_2 = "anction_awake_watch2";//


    }




    /**
     * 首页默认订单标记
     */
    public class OrderMainDefault {

        public static final int ORDER_ABOUT_DEFUALT = 1000;  //默认外层订单default

        public static final int ORDER_CHILDE_ABOUT_DEFAULT = 1001;//默认childeDEFAULT


    }

}
