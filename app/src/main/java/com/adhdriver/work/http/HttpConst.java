package com.adhdriver.work.http;

/**
 * Created by Administrator on 2016/9/17 0017.
 * 类描述   网络交互的所有常量
 * 版本
 */
public class HttpConst {


    public static final String LOG_REQUEST = "log_request";

    public static final String SERVER_BACK = "server_back";

    public static final String CONTENT_TYPE = "Content-Type";


    public static final String CONTENT_TYPE_APPLICATION = "application/json; charset=UTF-8";

    public static final String BASE_URL = "http://devapi.adaihuo.com/";  //辅助胡含

//    public static final String BASE_URL = "http://testapi.adaihuo.com/";  //外网测试环境

    public static final String API_VERSION = "api/V1/";


    public static final String QUESTION_MARK = "?";//分隔符（用于get请求中有参数在URL中即可）


    public static final String RQUAL_SIGN = "=";//分隔符（用于get请求中有参数在URL中即可）

    public static final String AND = "&";//分隔符（用于get请求中有参数在URL中即可）

    public static final int CODE_200 = 200;//success回调中使用的返回码



    public static final int CODE_201 = 201;//success回调中使用的返回码


    public static final int CODE_204 = 204 ;//success回调中使用的返回码



    public static final int CODE_0 = 0;//网络链接异常

    public static final int CODE_401 = 401;//未授权



    public static final int CODE_502 = 502;//服务器异常

    public static final int NO_RESOURCE = 204;//未知资源，比如更新数据时，没有找到该数据


    public static final String UPLOAD_DATA_KEY = "data";

    public static final String UPLOAD_DATA_KEY_FILE_NAME = "filename";


    public static final String UPLOAD_DATA_VALUE_NAME = "img.jpg";

    public static final int HTTP_WHAT = 0;//当前的请求标记


    public static final int NO_HTTP_CONNECT_ERROR = 0;

    public static final int SERVER_ERROR = 502;//服务器异常（比如服务器没网了）

    public static final int UNAUTHORIZED = 401;// 未授权、token过期等等;


    public static final String AUTHORIZATION = "Authorization";//授权

    public static final String BASIC = "Basic ";//获取token时候，需要加入的basci

    public static final String BEARER = "Bearer ";
    public static final String SEPARATOR = "/";//分隔符（用于get请求中有参数在URL中即可）

    public class URL {




        /**
         * 开始瓜分5元红包
         * post
         */
        public static final String REWARD = BASE_URL + API_VERSION + "driver/Orders/Reward";
        /**
         * 获取奖池信息
         * GET
         */
        public static final String PRIZEPOLL = BASE_URL + API_VERSION + "driver/Drivers/PrizePool";

        /**
         * 发起整车一口价接单
         * post /api/v1/driver/Orders/GrabQuickOrder/{order_no}
         */
        public static final String START_TO_TAKE_FIXED_FULLLOAD = BASE_URL + API_VERSION + "driver/Orders/GrabQuickOrder"; //发起一口价接单






        /**
         * 我要出发
         * 营业部集包订单出发
         * POST
         */
        public static final String START_TO_GO_PAKAGE_ORDER = BASE_URL + API_VERSION + "driver/Orders/PackageOrderStart";

        /**
         * 上传图片
         * POst
         */
        public static final String UPLOAD_PIC = "http://img.adaihuo.com/api/Upload";

        /**
         * 获取验证码
         * POST
         */
        public static final String GET_CONFIRMCODE = BASE_URL + API_VERSION + "Sms/GeneratePassCode";

        /**
         * 校验验证码
         * POST
         */

        public static final String VERTIFY_CONFIRMCODE = BASE_URL + API_VERSION + "Sms/ValidatePassCode";

        /**
         * 用户注册 (司机、企业)
         * POST
         */

        public static final String REGISTER = BASE_URL + API_VERSION + "driver/Drivers/Register";



        /**
         * 获取oss相关配置信息
         * GET
         */
        public static final String OSS_SERVER_INOF = BASE_URL + API_VERSION + "driver/Images/ServerInfo";

        /**
         * 用户登陆（司机、企业）
         * 获取token
         * POST
         */

        public static final String LOGIN = BASE_URL + API_VERSION+ "driver/Drivers/Logon";


        /**
         * 获取注册状态返回
         * GET
         */
        public static final String REGISTER_STATE = BASE_URL + API_VERSION + "driver/Drivers/GetDriverWithCurrentUser";






        /**
         * 司机上传头像/
         * PUT
         */
        public static final String DRIVER_SET_AVATAR = BASE_URL + API_VERSION + "driver/Drivers/Avatar";

        /**
         * 注册时，更新用户信息
         * POST
         */


        public static final String REGISTER_UPDAT_USER = BASE_URL + API_VERSION + "driver/Drivers/CompleteUser";

        /**
         * 获取司机车辆列表 /获取单个车辆信息/增加、删除，均使用此接口
         * post/get
         */
        public static final String DRIVER_VEHICLES = BASE_URL + API_VERSION + "driver/Vehicles";


        /**
         * 零钱充值请求
         * POST
         */
        public static final String BALANCE_RECHARGE = BASE_URL + API_VERSION + "driver/Payments/BalanceRecharge";
        /**
         * 获取车辆类型信息（管理台配置好的）
         * GET
         */


        public static final String VEHICLE_TYPES = BASE_URL + API_VERSION + "driver/VehicleTypes";

        /**
         * 获取指定车辆的车辆种类信息列表
         * Get
         */
        public static final String DRIVER_GET_VEHICLE_CATEGORIES = BASE_URL + API_VERSION + "driver/VehicleTypes/VehicleCategories";

        /**
         * 司机信息提交
         * POST
         */

        public static final String COMPLETE_DRIVER = BASE_URL + API_VERSION + "driver/Drivers/CompleteDriver";


        /**
         * 获取用户信息(可用于完善注册时获取用户信息)
         * GET
         */


        public static final String CURRENT_USER = BASE_URL + API_VERSION + "driver/Drivers/Current";


        /**
         * 注册时候创建传入司机信息和车辆信息
         * POST
         */


        public static final String COMPLETE_DRIVER_AND_CAR_IN_FINISH_REG = BASE_URL + API_VERSION + "driver/Drivers/CompleteDriver";





        /**
         * 更换车辆接口/
         * PUT
         * put /api/v1/driver/Drivers/SwitchVehicle/{driver_vehicle_id}
         */
        public static final String SWITCH_VEHICLE = BASE_URL + API_VERSION + "driver/Drivers/SwitchVehicle";


        /**
         * 重置密码（用在找回密码界面）
         * POST
         */

        public static final String RESET_PASSWORD = BASE_URL + API_VERSION + "driver/Drivers/ResetPassword";


        /**
         * 修改密码
         * POST
         */


        public static final String CHANGE_PASSWORD = BASE_URL + API_VERSION + "driver/Drivers/ChangePassword";

        /**
         * 获取USER信息
         * GET 登陆后第一件事
         */
        public static final String GET_USER_INFO = BASE_URL + API_VERSION + "driver/Drivers/Current";






        /**
         * 获取活动列表
         * 获取活动列表
         */
        public static final String GET_ACT_LIST = BASE_URL + API_VERSION + "driver/Drivers/actlist";



        /**
         * 1.订单列表（抢单、竞价）
         * 2.（加参数时，获取订单详情）
         * <p>
         * GET
         */


        public static final String ORDER_NEWEST = BASE_URL + API_VERSION + "driver/Orders/Newest";





        /**
         * 发起抢同城
         * POST
         */
        public static final String START_TO_GRAB_SAMECITY = BASE_URL + API_VERSION + "driver/Orders/GrabSameCity"; //发起抢单





        /**
         * 接受同城指派
         * POST
         *post /api/v1/driver/Orders/AcceptAssigned/{order_no}
         */
        public static final String ACCEPT_SAMECITY_ASSIGNED = BASE_URL + API_VERSION + "driver/Orders/AcceptAssigned"; //发起抢单



        /**
         *拒绝同城指派
         * POST
         *post /api/v1/driver/Orders/RejectAssigned/{order_no}
         */
        public static final String REJECT_SAMECITY_ASSIGNED = BASE_URL + API_VERSION + "driver/Orders/RejectAssigned"; //发起抢单







        /**
         * 发起抢零担
         * post
         */
        public static final String START_TO_GRAB_PARTLOAD = BASE_URL + API_VERSION + "driver/Orders/GrabPartLoad"; //发起抢零担单


        /**
         * 发起营业部抢单
         * POST
         * 【目前，采取营业部抢单和 零担起那抢单是同一个接口，后续再变更】
         */
        public static final String START_TO_GRAB_OVER_OFFICE = BASE_URL + API_VERSION + "driver/Orders/GrabPartLoad"; //发起营业部抢单


        /**
         * 发起竞价
         * POST
         */
        public static final String START_TO_BIDDING = BASE_URL + API_VERSION + "driver/Orders/Quote"; //发起竞价


        /**
         * 获取订单详情
         */
        public static final String ORDER_INFO = BASE_URL + API_VERSION + "driver/Orders";



        /**
         * 获取订单详情
         */
        public static final String ORDERS = BASE_URL + API_VERSION + "driver/Orders";

        /**
         * 修改提现密码
         * POST
         */
        public static final String CHANGE_DEPOSITE_PASSWORD = BASE_URL + API_VERSION + "driver/Wallets/ChangePassword";


        /**
         * 重置提现密码
         * post
         */
        public static final String RESET_DEPOSITE_PASSWORD = BASE_URL + API_VERSION + "driver/Wallets/ResetPayPassword";



        /**
         * 取消发布的顺风车
         * Post
         */
        public static final String CANCEL_HITCHHIKE = BASE_URL + API_VERSION + "driver/Hitchhikes/Cancel";




        /**
         * 用户区域信息修改/
         * PUT
         * put /api/v1/driver/Drivers/Region/{county_code}
         */
        public static final String DRIVER_REGION = BASE_URL + API_VERSION + "driver/Drivers/Region";



        /**
         * 获取消息列表借口
         *
         *get /api/v1/driver/AppMessage
         *
         */
        public static final String APP_MESSAGES = BASE_URL + API_VERSION + "driver/AppMessages";



        /**
         * 获取消息列表借口
         *
         *
         post /api/v1/driver/Orders/ArriveSameCity/{order_no}
         *
         */
        public static final String ARRIVE_SAME_CITY_PATH_POINT = BASE_URL + API_VERSION + "driver/Orders/ArriveSameCity";

        /**
         * 获取当日接单信息
         *
         *
         get /api/v1/driver/Orders/TodayStatistics
         *
         */
        public static final String TODAY_STATISTICS = BASE_URL + API_VERSION + "driver/Orders/TodayStatistics";


        /**
         * 获取消息列表借口
         *
         *
         get /api/v1/driver/Drivers/Credit
         *
         */
        public static final String DRIVER_CREDIT = BASE_URL + API_VERSION + "driver/Drivers/Credit";


        /**
         * 获取授权列表信息
         *
         *get /api/v1/driver/AppMessages
         */
        public static final String GET_AUTH_LIST = BASE_URL + API_VERSION + "driver/Drivers/GetAuthList";


        /**
         * 带三方登陆授权验证接口
         *
         *post /api/v1/driver/Drivers/ValidAuth
         */
        public static final String VALID_AUTH = BASE_URL + API_VERSION + "driver/Drivers/ValidAuth";

        /**
         * 绑定授权接口
         *
         *post /api/v1/driver/Drivers/BindAuth
         */
        public static final String BIND_AUTH = BASE_URL + API_VERSION + "driver/Drivers/BindAuth";






        /**
         * 根据司机id获取司机信息
         * GET
         */
        public static final String GET_DRIVER_INFO = BASE_URL + API_VERSION + "driver/Drivers";

        /**
         * 每个7分钟上传司机位置信息
         * POST
         */
        public static final String HEART_LOCATION = BASE_URL + API_VERSION + "driver/Drivers/SetLocation";


        /**
         * 发起抢单
         * POST
         */
        public static final String START_TO_GRAB = BASE_URL + API_VERSION + "driver/Orders/Grab"; //发起抢单





        /**
         * 宽城订单列表
         * get /api/v1/driver/Orders/CrossCityNewest
         */
        public static final String CROSS_CITY_NEWEST = BASE_URL + API_VERSION + "driver/Orders/CrossCityNewest"; //发起抢单





        /**
         * 宽城订单列表
         * visitor
         get /api/v1/driver/Visitors/CrossCityNewest
         */
        public static final String VISITORS_CROSS_CITY_NEWEST = BASE_URL + API_VERSION + "driver/Visitors/CrossCityNewest"; //发起抢单




        /**
         * 获取我的
         * 指派给我的订单/我的抢单/我的专车竞价订单列表
         * 这里的数据有别与上面的ORDER_INFO 这个是不带参数，那个是带参数的
         * GET
         */
        public static final String THE_TAKNED_ORDER = BASE_URL + API_VERSION + "driver/Orders"; //指派给我的订单/我的抢单/我的专车竞价订单列表

        /**
         * 司机到达发货地
         * POST
         */

        public static final String ARRIVE_START_PLACE = BASE_URL + API_VERSION + "driver/Orders/ArriveDeparture";

        /**
         * 复称修改接口
         * POST
         */
        public static final String REWEIGHT = BASE_URL + API_VERSION + "driver/Orders/Reweigh";


        /**
         * 出发前设置图片接口
         * POST
         */

        public static final String SET_PIC_BEFORE_START_GO = BASE_URL + API_VERSION + "driver/Orders/UploadPhotos";


        /**
         * 我要出发
         * POST
         */

        public static final String START_TO_GO = BASE_URL + API_VERSION + "driver/Orders/Start";


        /**
         * 取消订单
         * POST
         */


        public static final String CANCEL_THE_ORDER = BASE_URL + API_VERSION + "driver/Orders/Cancel";


        /**
         * 复称无需修改
         * POST
         */
        public static final String NOT_CHANGE_WEIGHT = BASE_URL + API_VERSION + "driver/Orders/ReweighWithoutChangePrice";


        /**
         * 复称需要改价
         * Post
         */
        public static final String CHANGE_WEIGHT = BASE_URL + API_VERSION + "driver/Orders/ChangeWeight";


        /**
         * 我已抵达（抵达目的地）
         * POST
         */
        public static final String REACH_TO_DESTINATION = BASE_URL + API_VERSION + "driver/Orders/ArriveDestination";


        /**
         * 收货验证，用于完成验证
         * POST
         */

        public static final String VERTIFY_TO_FINISH = BASE_URL + API_VERSION + "driver/Orders/Complete";

        /**
         * 发布顺风车 POST
         * 【也可以用获取发布列表】GET
         */

        public static final String PUBLISH_HITCHHIKES = BASE_URL + API_VERSION + "driver/Hitchhikes";


        /**
         * 上传push信息
         */

        public static final String UPDARE_DEVICE_TO_PUSH = BASE_URL + API_VERSION + "driver/Drivers/UpdateDevice";


        /**
         * 获取支付渠道信息
         * GET
         */
        public static final String PAY_CHANNELS = BASE_URL + API_VERSION + "driver/Payments/Channels";


        /**
         * 获取支付二维码信息
         * POST
         */
        public static final String PAY_QR_CODE = BASE_URL + API_VERSION + "driver/Payments/QRCode";


        /**
         * 我的钱包
         * GET
         */
        public static final String MY_WALLETE = BASE_URL + API_VERSION + "driver/Wallets/Current";



        /**
         * 钱包明细列表
         * GET
         */
        public static final String WALLETE_DETAILS_LIST = BASE_URL + API_VERSION + "driver/Wallets/Details";


        /**
         * 获取学习资料信息
         * GET
         */

        public static final String LEARNS = BASE_URL + API_VERSION + "driver/Learns";


        /**
         * 获取考试题
         * GET
         */
        public static final String EXAMINE_TEST_QESTIONS = BASE_URL + API_VERSION + "driver/Learns/ExamineTestquestions";




        /**
         * 获取考试答案
         * GET
         */
        public static final String QUESTION_ANSWERS = BASE_URL + API_VERSION + "driver/Learns/QuestionAnswers";


        /**
         * 生成考试成绩
         * POST
         */

        public static final String CREAT_EXAMINE_MARK = BASE_URL + API_VERSION + "driver/Learns/CreateExamineMark";


        /**
         * 获取附近的营业部
         * GET
         */
        public static final String OFFICES_NEARBY = BASE_URL + API_VERSION + "Offices/Nearby";


        /**
         * 意见反馈
         * POST
         */

        public static final String FEEDBACKS = BASE_URL + API_VERSION + "Feedbacks/Create";


        /**
         * 获取app版本信息
         * GET
         */
        public static final String GET_APP_VERSION_INFO = BASE_URL + API_VERSION + "driver/Drivers/Upgrade";






        /**
         * 游客模式获取版本信息
         * get /api/v1/driver/Visitors/Upgrade
         */

        public static final String VISITORS_UPGRAD = BASE_URL + API_VERSION + "driver/Visitors/Upgrade";


        /**
         * 上传游客模式的经纬度
         * post /api/v1/driver/Visitors/Save
         */
        public static final String VISITORS_SAVE = BASE_URL + API_VERSION + "driver/Visitors/Save";




        /**
         * 游客模式登陆
         * <p>
         * post /api/v1/driver/Visitors/Logon
         */
        public static final String VISITORS_LOGON = BASE_URL + API_VERSION + "driver/Visitors/Logon";




        /**
         * 设置提现密码
         * Post
         */
        public static final String SET_PAY_PASS = BASE_URL + API_VERSION + "driver/Wallets/SetPayPassword";




        /**
         * 获取授权信息
         * post
         */


        public static final String AUTH_INFO = BASE_URL + API_VERSION + "driver/Payments/AppAuthLogin";


        /**
         * 获取渠道信息
         * Post
         */
        public static final String AUTH_LOGIN_INFO = BASE_URL + API_VERSION + "driver/Payments/AuthLoginInfo";

        /**
         * 开始申请余额提现
         * Post
         */
        public static final String CHANGE_TRANSFER = BASE_URL + API_VERSION + "driver/Wallets/Transfer";


        /**
         * 查询验证微信 wx支付结果
         * POST
         */
        public static final String CHECK_WX_PAY_RESULT = BASE_URL + API_VERSION + "Payments/WeipayCheckPaymentResultInformation";


        /**
         * 查询验证阿里 AliPay支付结果
         * 扫码收款
         * POST
         */
        public static final String CHECK_ALI_PAY_RESULT = BASE_URL + API_VERSION + "Payments/AlipayCheckOrderPaymentResult";


        /**
         * 查询验证支付结果Alipay
         * 支付宝支付
         */
        public static final String CHECK_ALI_PAY_RESULT_INFORMATION = BASE_URL + API_VERSION + "Payments/AlipayCheckPaymentResultInformation";


        /**
         * 紧接上面，更新主动更新paid字段
         * 查询验证阿里 AliPay支付结果
         * PUT
         */

        public static final String UPDATE_ORDER_PAID = BASE_URL + API_VERSION + "driver/Orders/Paid";
    }






}
