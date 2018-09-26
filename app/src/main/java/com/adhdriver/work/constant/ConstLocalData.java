package com.adhdriver.work.constant;

import android.os.Environment;

import com.adhdriver.work.entity.driver.temp.PublishRoute;

/**
 * Created by Alie on 2017/11/17.
 * 类描述  用于本地的参数
 * 版本
 */

public class ConstLocalData {

    /**
     * 用户协议
     */
    public static final String AGRREMENT_URL = "http://idaihuo-web.oss-cn-beijing.aliyuncs.com/idaihuo-pc-web/html/agreement-driver.html";


    public static final String REG_PACKET_DEFAULT_AMOUNT = "5"; //红包默认5元

    /**
     * 客服电话
     */
    public static final String CUSTOMER_SERVICE_CALL = "400-032-0520"; //客服电话


    public static final String OFFICIAL_WEB = "http://www.adaihuo.com/";//官网地址
    public static final String SINA_TYPE = "Weibo";
    public static final String SINA_ID_ADH = "5928886321";//爱带货id
    public static final String SINA_CLIENT_URL = "sinaweibo://userinfo?uid=";//新浪客户端地址
    public static final String SINA_WEB_URL = "http://weibo.com/u/";//新浪web地址
    public static final String CONTACT_ADDR = "http://idaihuo-web.oss-cn-beijing.aliyuncs.com/idaihuo-pc-web/html/agreement-driver.html";//adh联系地址




    public static final double SAME_CITY_PROPORTION = 0.85;//同城0.85
    public static final double PART_LOAD_PROPORTION = 0.85;//零担0.85
    public static final double FULL_LOAD_PROPORTION = 0.95;//整车0.95


    public static final String BOX = "Box";
    public static final String SLAB = "Slab";
    public static final String GAOLAN = "Goalan";





    public static final int DEFAULT_INCREMENT_SIZE = 6;  //默认订单数增量
    public static final int DEFUALT_LIST_INDEX = 1; //默认加载更多


    public static final boolean IS_QUIET = true;  //推送关闭
    public static final boolean NOT_QUIET = false;  //推送打开



    public static final String APPID_QQ = "1106253080";//QQ appId
    public static final String QQ_SCOPE = "all";//需要授权的选项
    public static final String APPID_WX = "wxf4cf149afc395166";  //微信appId




    public static final double DEFAULT_BEIJING_LONGITUDE = 116.427915;//默认北京的经度
    public static final double DEFAULT_BEIJING_LATITUDE = 39.902895;//默认北京的纬度
    public static final String DEFAULT_BEIJING_ZONE_CODE = "110101";//默认北京AcCode



    public static final String ORDER_TYPE = "Order";  //支付提现 需要用到的参数


    public static final String  WX = "Weixin";//微信渠道
    public static final String  ALIPAY = "Alipay";//支付宝渠道

    public static final String WITHDRAWALS = "Withdrawals";// 用于零钱流水,用户提现的前


    public static final String ALI_CLIENT_CHECK_URL = "alipays://platformapi/startApp";// 支付宝客户端判断的url
    public static final String FIRST_AUTN_PASS = "666666"; //用于默认占位


    public static final String BALANCE_RECHARGE = "BalanceRecharge";//余额充值


    /**
     * 分享相关
     */
    public  static final String DOWNLOAD_PATH = "http://www.adaihuo.com/download/";
    public  static  final String SHARE_IMAGE_DIR = Environment.getExternalStorageDirectory().getPath()+"/.adh_share/";//分享的图片文件夹
    public  static final String SHARE_IMAGE_PATH = Environment.getExternalStorageDirectory().getPath()+"/.adh_share/sharepic.png"; //分享的图片文件路径

    /**
     * 注册默认占位符
     */
    public static final String DEFAULT_REG_TEXT = "托蒂";


    /**
     * 用于学习和考试
     */
    public static final int EXAM_COUNT = 50;  //默认考试数量
    public static final int LEARN_COUNT = 20;  //默认考试数量
    public static final int EXAM_PASS_SCORE = 60;  //考试及格线


    public static final int PIC_ROUNT_RADIUS = 120;//图片圆形radius

    public static final String PHOTO_URL = "photo_url";


    public static final String WX_APPID = "wxf4cf149afc395166";  //微信appId



    public static final int COMPLETE = 1000; //已到达标记
    public static final int CANCEL = 2000;   //已取消
    public static final int REFUND = 3000;   //已退款
    public static final int ARRIVE_HAD_PAID = 1001; //到达已付款
    public static final int ARRIVE_NOT_PAID = 1002;  //到达未付款
    public static final int DEPART_NOT_ARRIVE = 1003; //出发未到达
    public static final int DEPART_NOT = 1004; //未出发

    public static final int DEPART_OVER_OFFICE = 1005; //已到达




    public static final String HANDLING = "Handling"; //装卸货
    public static final String RECEIPT = "Receipt"; //司机带回
    public static final String COD = "Cod"; //代收货款
    public static final String INSURED = "Insured"; //保价


    public static final String SWIPE_GO = "滑动出发"; //滑动出发
    public static final String SWIPE_ARRIVE = "滑动到达"; //滑动到达

    public static final String SWIPE_ARRIVE_NEXT = "滑动到达下一地点"; //滑动到达下一地点

    public static final String SWIPE_SEND_PHONE_CODE = "滑动发送验证码"; //发送验证码

    public static final String SWIPE_PAY_TO_ME = "滑动向我付款"; //滑动到达




}
