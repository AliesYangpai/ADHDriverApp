package com.adhdriver.work.constant;

/**
 * Created by Administrator on 2016/9/19 0019.
 * 类描述  Sp的常量
 * 版本
 */
public class ConstSp {

    public static final String ADH_DRIVER_SP_NAME = "ADH_driver_sp";

    public static final int SP_OPEN = 0;

    /**
     * 首次启动的限制
     */
    public static final String SP_KEY_LOAD_OR_NOT = "load_or_not";

    /**
     * 用户是否登陆
     */

    public static final String SP_KEY_IS_LOGIN_OR_NOT = "is_login_or_not";


    /**
     * 默认收车
     */
    public static final String SP_KEY_VEHICLE_DIPATCH = "vehicle_dipatch";




    /**
     * 登陆返回的token
     */


    public static final String SP_KEY_TOKEN = "token";

    /**
     * 司机id
     */

    public static final String SP_KEY_DRIVER_ID = "driver_id";



    public static final String SP_KEY_ALIYUN_PUSH_DEVICE_TOKEN = "aliyun_push_device_token";


    public static final String SP_KEY_SGIN_ACCOUNT = "sgin_account";//登陆时，用于记住账号


    public static final String SP_KEY_USER_ID = "sgin_user_id";//登陆成功记住用户user_id


    public static final String SP_KEY_LOGIN_ROLE = "login_role";//当前登陆角色



    public static final String SP_KEY_BUSINESS_ORDER_NO = "business_order_no";// 本地保存用于获取司机location



    public static final String SP_KEY_LOGON_DRIVER_VEHICLE_ID = "logon_driver_vehicle_id";// 本地保存的当前登陆司机的当前登陆车辆id


    public static final String SP_KEY_LOGON_DRIVER_CATEGORY = "logon_driver_category";// 本地保存的当前登陆司机的当前登陆车辆的 Box Slab GoaLan;


    public static final String SP_KEY_HAS_PUSH = "has_push";// 含有推送



    public static final String SP_KEY_HAS_KEEP_LOCATION = "has_keep_location";//已经进行了经纬度上传




    public static final String SP_KEY_HAS_AREA_OR_NOT = "has_area_or_not";//返回是 current返回否含有 省市区 编码的标记



    /**
     * 游客模式
     */


    public static final String SP_KEY_VISITOR = "sp_key_visitor";//游客模式


    /**
     * TestContent.forTest,上线时改为Gone
     */

    public static final String SP_KEY_LOTITUDE_forTest = "longitude";//经纬度保存

    public static final String SP_KEY_LATITUDE_forTest = "latityde";//已经进行了经纬度上传
    public static final String SP_KEY_ad_code_forTest = "adcode";//区域码
    public static final String SP_KEY_CUREENTPLACE_forTest = "currentplace";//已经进行了经纬度上传


    /**
     * 当回传信息为空的时候，默认回传北京的经纬度
     */


    public static final String SP_KEY_BEIJING_LONGITUDE = "beijing_logitude";//默认北京的经度
    public static final String SP_KEY_BEIJING_LATITUDE = "beijing_latitude";//默认北京的纬度
    public static final String SP_KEY_BEIJING_ZONE_CODE = "beijing_zone_code";//默认北京AcCode



    public static final String SP_KEY_LONGITUDE_FOR_VEHICLE_CAHNEG = "longitude_for_vehicle_change";//用于更换车辆时候的标记，最后一步上传经纬度，退出时，不必清空
    public static final String SP_KEY_LATITUDE_FOR_VEHICLE_CAHNEG = "latitude_for_vehicle_change";//用于更换车辆时候的标记，最后一步上传经纬度，退出时，不必清空

    public static final String SP_KEY_AREA_CODE_FOR_VEHICLE_CAHNEG = "area_code_for_vehicle_change";//用于更换车辆时候的标记，最后一步上传地理位置信息，退出时，不必清空







    /**
     * 相关value
     */
    public class SP_VALUE {

        public static final String DEFAULT_STRING = "";


        public static final String DEFAULT_BEIJING_LONGITUDE = "116.427915";//默认北京东城区的经度
        public static final String DEFAULT_BEIJING_LATITUDE = "39.902895";//默认北京东城区的纬度
        public static final String DEFAULT_BEIJING_ZONE_CODE = "110101";//东城区的区号

        public static final boolean IS_HAD_AREA = true;  //该司机已经有省市区id

        public static final boolean DEFAULT_BOOLEAN = false;
        public static final boolean IS_LOADED_APP = true;
        public static final boolean DEFAULT_TRUE_VISITOR = true;



        public static final boolean IS_DISPATCH = true;
        /**
         * 用户已登陆
         */
        public static final boolean IS_LOGIN = true;


        /**
         * 用户默认登陆状态（默认退出）
         */
        public static final boolean IS_LOGIN_DEFAULT = false;


        public static final boolean PUSH_REMIND_DEFULT = true;


        public static final boolean PUSH_REMIND_ON = true;


        public static final String ROLE_DRIVER = "Driver";


        /**
         * 司机角色
         */
        public static final String ROLE_BUSINESS = "Business";


        /**
         * 企业角色
         */
        public static final boolean PUSH_REMIND_OFF = false;





        /**
         * 标记推送
         */
        public static final boolean SIGN_PUSH_TRUE = true;


        /**
         * 推送还原
         */
        public static final boolean SIGN_PUSH_FALSE= false;
    }

}
