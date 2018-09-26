package com.adhdriver.work.test;


import android.widget.ListView;

import com.adhdriver.work.entity.driver.orders.AdditionService;
import com.adhdriver.work.entity.driver.orders.Goods;
import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.entity.driver.orders.Vehicle;
import com.adhdriver.work.entity.driver.pay.PayChannelInfo;
import com.adhdriver.work.entity.driver.wallet.FundsDetial;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/8 0008.
 * 类描述
 * 版本
 */
public class TestContent {


 //测试环境
//    public static String TEST_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoiVXNlcjoyMDE2NTE1NSIsImh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vd3MvMjAwOC8wNi9pZGVudGl0eS9jbGFpbXMvcm9sZSI6IkRyaXZlciIsIkRyaXZlclZlaGljbGVJZCI6IjExMDUiLCJuYmYiOjE1MTEzMTMyNDUsImV4cCI6MTUxOTA4OTI0NSwiaXNzIjoiaHR0cDovL2FwaS5hZGFpaHVvLmNvbS8iLCJhdWQiOiJHb3Jkb24gV2FuZyJ9.jr3UfrWlz0ThTQxnHnyv2k2GrY-baSabD1iYFHHc7-s";



    public static String TEST_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoiVXNlcjoyMDE2NTI1MCIsImh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vd3MvMjAwOC8wNi9pZGVudGl0eS9jbGFpbXMvcm9sZSI6IkRyaXZlciIsIkRyaXZlclZlaGljbGVJZCI6Ijc2IiwibmJmIjoxNTEyNjE4Mzk2LCJleHAiOjE1MjAzOTQzOTYsImlzcyI6Imh0dHA6Ly9hcGkuYWRhaWh1by5jb20vIiwiYXVkIjoiR29yZG9uIFdhbmcifQ.IV-U9EVjt0qht0Wyt6FLM7n286O3PJVFszi_uMGmhC8";


    public static String UDP_SERVER_IP= "ws://devapi.adaihuo.com/connect";



    //    public static String UDP_SERVER_IP= "ws://172.22.8.117:5000/connect";
    public static int UDP_PORT = 8080;

    public static boolean pingbi = true ;//v1.2.1上线时需要屏蔽的相关内容 true，表示屏蔽，false表示取消屏蔽

    public static boolean forTest = true;//测试 更改定位数据 false表示屏蔽，true表示开启


    public static final String ACT_URL = "http://mp.weixin.qq.com/s/aQcxgNib4Cdxr02fKQaitA";


    public static final String ACT_CONTENT = "首单奖励，瓜分红包";

    public static final String MY_TOAST_TEXT = "mytosttest";

    public static final String BASE_URL = "http://source.chinacloudsites.cn/";

    public static final String TEST_PIC_URL = "http://himg2.huanqiu.com/attachment2010/2013/0320/20130320044210415.jpg";

    public static final String TEST_WEB_URL = "https://www.hao123.com/";
//    http://source.chinacloudsites.cn/api/values



    public static  boolean PAY_PASS_SET = false;

    public static  boolean PAY_WX_AUTHORITION = false;

    public static  boolean PAY_ALI_AUTHORITION = false;


    public static  boolean IS_AUTHORITON = false;

    public static final String TEST_ORDER = "SHYY05RDXI3M4O";



    public static final int  TEST_SIZE = 6;

    public static final int  TEST_INDEX = 1;




    public static final String TongCheng = "tongcheng";//同城测试标记

    public static final String LingDan = "lingdan";//零担测试标记

    public static final String ZhengChe = "zhengCHe";//整车测试标记




    public static final double DEFAULT_BEIJING_LONGITUDE = 116.427915;//默认北京的经度
    public static final double DEFAULT_BEIJING_LATITUDE = 39.902895;//默认北京的纬度

    public static final double DEFAULT_OTHER_LONGITUDE = 115.888370;//默认北京的经度
    public static final double DEFAULT_OTHER_LATITUDE = 39.902895;//默认北京的纬度

    public static final boolean testDriverNoArea = true;


    public static List<TestPassPint> getList() {
        List<TestPassPint> list = new ArrayList<>();
        TestPassPint testPassPint = new TestPassPint();
        testPassPint.setPintName("西安市莲湖区土门市场");
        testPassPint.setTag(1);


        TestPassPint testPassPint2 = new TestPassPint();
        testPassPint2.setPintName("西安市莲湖区土门市场");
        testPassPint2.setTag(1);

        TestPassPint testPassPint3 = new TestPassPint();
        testPassPint3.setPintName("西安市莲湖区土门市场");
        testPassPint3.setTag(1);

        TestPassPint testPassPint4 = new TestPassPint();
        testPassPint4.setPintName("西安市莲湖区土门市场");
        testPassPint4.setTag(1);


        TestPassPint testPassPint5 = new TestPassPint();
        testPassPint5.setPintName("西安市莲湖区土门市场");
        testPassPint5.setTag(1);


        list.add(testPassPint);
        list.add(testPassPint2);
//        list.add(testPassPint3);
//        list.add(testPassPint4);
//        list.add(testPassPint5);

        return list;

    }


    public static List<PayChannelInfo> getGatherChannelList() {
        List<PayChannelInfo> list = new ArrayList<>();
        PayChannelInfo channelInfo1 = new PayChannelInfo();

        channelInfo1.setName("微信收款");
        channelInfo1.setLogo(TEST_PIC_URL);



        PayChannelInfo channelInfo2 = new PayChannelInfo();

        channelInfo2.setName("支付宝收款");
        channelInfo2.setLogo(TEST_PIC_URL);


        list.add(channelInfo1);
        list.add(channelInfo1);


        return list;

    }




    public static List<AdditionService> getAdditionServiceList() {


        List<AdditionService> list = new ArrayList<>();
        AdditionService additionService = new AdditionService();

        list.add(additionService);
        list.add(additionService);
        list.add(additionService);

        return list;

    }


    public static final String BIDDING_DATA = "{\"count\":49,\"items\":[{\"order_no\":\"S000001707KO0XYE\",\"user_id\":20161152,\"mode\":\"Carload\",\"book_time\":\"2017-07-19 10:00:00\",\"vehicle\":{\"vehicle_id\":1,\"name\":\"私家车\",\"avatar\":\"1.png\",\"description\":\"微型货车(0.2吨以下)\",\"starting_price\":20.00,\"starting_mileage\":5,\"price\":1.50,\"deadweight\":200,\"length\":1.00,\"width\":1.00,\"height\":1.00,\"fuel_type\":\"Diesel\",\"fuel_consumption\":0.0},\"from\":{\"province\":{\"zone_id\":11,\"name\":\"陕西省\",\"standard_code\":\"610000\"},\"city\":{\"zone_id\":19,\"name\":\"西安市\",\"standard_code\":\"610100\"},\"county\":{\"zone_id\":9,\"name\":\"未央区\",\"standard_code\":\"610112\",\"is_downtown\":false},\"street_address\":\"西安北站\",\"fullname\":\"朱\",\"phone\":\"18392899687\",\"coordinate\":{\"longitude\":108.93982887268068,\"latitude\":34.372708256930252}},\"to\":{\"province\":{\"zone_id\":11,\"name\":\"陕西省\",\"standard_code\":\"610000\"},\"city\":{\"zone_id\":19,\"name\":\"西安市\",\"standard_code\":\"610100\"},\"county\":{\"zone_id\":8,\"name\":\"灞桥区\",\"standard_code\":\"610111\",\"is_downtown\":false},\"street_address\":\"纺织城客运站\",\"fullname\":\"朱\",\"phone\":\"18392899687\",\"coordinate\":{\"longitude\":109.06973361968996,\"latitude\":34.279453407296423}},\"distance\":28.0,\"goods\":{\"description\":\"3km\",\"type\":\"LTL\",\"quantity\":123,\"weight\":123,\"length\":0.0,\"width\":0.0,\"height\":0.0,\"details\":{},\"package_count\":0},\"payer\":\"Receiver\",\"addition_services\":[],\"fee\":{\"description\":\"订单 S000001707KO0XYE 的货款或费用\",\"quoted\":54.50,\"discount\":0.0,\"total\":54.50,\"driver_commission\":37.06,\"details\":[{\"name\":\"运费: 起价\",\"base_price\":20.00,\"unit_price\":20.00,\"quantity\":1,\"quoted\":20.00,\"discount\":0.0,\"subtotal\":20.00,\"payer\":\"Receiver\",\"paid\":0.0},{\"name\":\"运费: 公里价\",\"base_price\":1.50,\"unit_price\":1.50,\"quantity\":23,\"quoted\":34.50,\"discount\":0.0,\"subtotal\":34.50,\"payer\":\"Receiver\",\"paid\":0.0}]},\"payments\":[],\"create_time\":\"2017-07-19 09:03:55\",\"status\":\"NewCreate\",\"is_same_city\":true,\"same_city_standard_code\":\"610100\",\"coupons\":[],\"is_first\":false},{\"order_no\":\"S0003917076PO17Q\",\"user_id\":1,\"mode\":\"Carload\",\"book_time\":\"2017-07-21 15:00:00\",\"vehicle\":{\"vehicle_id\":4,\"name\":\"入城厢货\",\"avatar\":\"4.png\",\"description\":\"微型货车(2吨以下)\",\"starting_price\":0.0,\"starting_mileage\":0,\"price\":0.0,\"deadweight\":2000,\"length\":4.20,\"width\":1.20,\"height\":1.80,\"fuel_type\":\"Gas92\",\"fuel_consumption\":12.00},\"from\":{\"province\":{\"zone_id\":11,\"name\":\"陕西省\",\"standard_code\":\"610000\"},\"city\":{\"zone_id\":19,\"name\":\"西安市\",\"standard_code\":\"610100\"},\"county\":{\"zone_id\":10,\"name\":\"雁塔区\",\"standard_code\":\"610113\",\"is_downtown\":false},\"street_address\":\"南三环辅路3001号靠近XMOCA艺术馆套餐体现出\",\"fullname\":\"糖醋鱼\",\"phone\":\"13586050505\",\"coordinate\":{\"longitude\":109.013613,\"latitude\":34.209275}},\"to\":{\"province\":{\"zone_id\":11,\"name\":\"陕西省\",\"standard_code\":\"610000\"},\"city\":{\"zone_id\":24,\"name\":\"延安市\",\"standard_code\":\"610600\"},\"county\":{\"zone_id\":56,\"name\":\"宝塔区\",\"standard_code\":\"610602\",\"is_downtown\":false},\"street_address\":\"延安站GV鱼鱼\",\"fullname\":\"故意一次\",\"phone\":\"15129070426\",\"coordinate\":{\"longitude\":109.48112010955811,\"latitude\":36.557015714514556}},\"distance\":330.9625,\"goods\":{\"description\":\"鱼鱼鱼\",\"type\":\"LTL\",\"quantity\":50,\"weight\":50,\"length\":0.0,\"width\":0.0,\"height\":0.0,\"details\":{},\"package_count\":0},\"payer\":\"Receiver\",\"addition_services\":[],\"fee\":{\"description\":\"订单 S0003917076PO17Q 的货款或费用\",\"quoted\":46.00,\"discount\":0.0,\"total\":46.00,\"driver_commission\":31.28,\"details\":[{\"name\":\"运费\",\"base_price\":50.0,\"unit_price\":0.92,\"quantity\":50,\"quoted\":46.00,\"discount\":0.0,\"subtotal\":46.00,\"payer\":\"Receiver\",\"paid\":0.0}]},\"payments\":[],\"create_time\":\"2017-07-18 15:04:01\",\"status\":\"NewCreate\",\"is_same_city\":false,\"same_city_standard_code\":\"\",\"coupons\":[],\"is_first\":false},{\"order_no\":\"S000391707OG9JSW\",\"user_id\":1,\"mode\":\"Carload\",\"book_time\":\"2017-07-21 15:00:00\",\"vehicle\":{\"vehicle_id\":2,\"name\":\"小面包\",\"avatar\":\"2.png\",\"description\":\"微型货车(1吨以下)\",\"starting_price\":0.0,\"starting_mileage\":0,\"price\":0.0,\"deadweight\":1000,\"length\":1.70,\"width\":1.10,\"height\":1.00,\"fuel_type\":\"Gas92\",\"fuel_consumption\":7.00},\"from\":{\"province\":{\"zone_id\":11,\"name\":\"陕西省\",\"standard_code\":\"610000\"},\"city\":{\"zone_id\":19,\"name\":\"西安市\",\"standard_code\":\"610100\"},\"county\":{\"zone_id\":10,\"name\":\"雁塔区\",\"standard_code\":\"610113\",\"is_downtown\":false},\"street_address\":\"南三环辅路3001号靠近XMOCA艺术馆\",\"fullname\":\"是你\",\"phone\":\"15900620994\",\"coordinate\":{\"longitude\":109.013613,\"latitude\":34.209275}},\"to\":{\"province\":{\"zone_id\":11,\"name\":\"陕西省\",\"standard_code\":\"610000\"},\"city\":{\"zone_id\":28,\"name\":\"商洛市\",\"standard_code\":\"611000\"},\"county\":{\"zone_id\":102,\"name\":\"商州区\",\"standard_code\":\"611002\",\"is_downtown\":false},\"street_address\":\"商洛学院\",\"fullname\":\"天天\",\"phone\":\"18092295018\",\"coordinate\":{\"longitude\":109.95718002319337,\"latitude\":33.864562692905473}},\"distance\":125.0,\"goods\":{\"description\":\"石榴\",\"type\":\"LTL\",\"quantity\":20,\"weight\":20,\"length\":0.0,\"width\":0.0,\"height\":0.0,\"details\":{},\"package_count\":0},\"payer\":\"Receiver\",\"addition_services\":[],\"fee\":{\"description\":\"订单 S000391707OG9JSW 的货款或费用\",\"quoted\":20.00,\"discount\":0.0,\"total\":20.00,\"driver_commission\":13.60,\"details\":[{\"name\":\"运费 - 底价\",\"base_price\":20.00,\"unit_price\":20.00,\"quantity\":1,\"quoted\":20.00,\"discount\":0.0,\"subtotal\":20.00,\"payer\":\"Receiver\",\"paid\":0.0}]},\"payments\":[],\"create_time\":\"2017-07-18 14:58:58\",\"status\":\"NewCreate\",\"is_same_city\":false,\"same_city_standard_code\":\"\",\"coupons\":[],\"is_first\":false},{\"order_no\":\"S000391707JUKBYQ\",\"user_id\":20161152,\"mode\":\"Carload\",\"book_time\":\"2017-07-18 14:49:18\",\"vehicle\":{\"vehicle_id\":2,\"name\":\"小面包\",\"avatar\":\"2.png\",\"description\":\"微型货车(1吨以下)\",\"starting_price\":0.0,\"starting_mileage\":0,\"price\":0.0,\"deadweight\":1000,\"length\":1.70,\"width\":1.10,\"height\":1.00,\"fuel_type\":\"Gas92\",\"fuel_consumption\":7.00},\"from\":{\"province\":{\"zone_id\":11,\"name\":\"陕西省\",\"standard_code\":\"610000\"},\"city\":{\"zone_id\":19,\"name\":\"西安市\",\"standard_code\":\"610100\"},\"county\":{\"zone_id\":10,\"name\":\"雁塔区\",\"standard_code\":\"610113\",\"is_downtown\":false},\"street_address\":\"南三环辅路3001号靠近XMOCA艺术馆\",\"fullname\":\"朱\",\"phone\":\"18392899687\",\"coordinate\":{\"longitude\":109.013615,\"latitude\":34.209282}},\"to\":{\"province\":{\"zone_id\":11,\"name\":\"陕西省\",\"standard_code\":\"610000\"},\"city\":{\"zone_id\":28,\"name\":\"商洛市\",\"standard_code\":\"611000\"},\"county\":{\"zone_id\":102,\"name\":\"商州区\",\"standard_code\":\"611002\",\"is_downtown\":false},\"street_address\":\"商洛市人民政府\",\"fullname\":\"朱\",\"phone\":\"18392899687\",\"coordinate\":{\"longitude\":109.91857767105101,\"latitude\":33.872731639647419}},\"distance\":123.0,\"goods\":{\"description\":\"测4\",\"type\":\"LTL\",\"quantity\":123,\"weight\":123,\"length\":0.0,\"width\":0.0,\"height\":0.0,\"details\":{},\"package_count\":0},\"payer\":\"Receiver\",\"addition_services\":[],\"fee\":{\"description\":\"订单 S000391707JUKBYQ 的货款或费用\",\"quoted\":108.24,\"discount\":0.0,\"total\":108.24,\"driver_commission\":73.60,\"details\":[{\"name\":\"运费\",\"base_price\":123.0,\"unit_price\":0.88,\"quantity\":123,\"quoted\":108.24,\"discount\":0.0,\"subtotal\":108.24,\"payer\":\"Receiver\",\"paid\":0.0}]},\"payments\":[],\"create_time\":\"2017-07-18 14:52:51\",\"status\":\"NewCreate\",\"is_same_city\":false,\"same_city_standard_code\":\"\",\"coupons\":[],\"is_first\":false},{\"order_no\":\"S000391707MEVYL3\",\"user_id\":20161152,\"mode\":\"Carload\",\"book_time\":\"2017-07-18 14:49:18\",\"vehicle\":{\"vehicle_id\":10,\"name\":\"7.6米厢车\",\"avatar\":\"10.png\",\"description\":\"中型货车(8吨以下)\",\"starting_price\":0.0,\"starting_mileage\":0,\"price\":0.0,\"deadweight\":8000,\"length\":7.60,\"width\":2.40,\"height\":2.50,\"fuel_type\":\"Gas92\",\"fuel_consumption\":20.00},\"from\":{\"province\":{\"zone_id\":11,\"name\":\"陕西省\",\"standard_code\":\"610000\"},\"city\":{\"zone_id\":19,\"name\":\"西安市\",\"standard_code\":\"610100\"},\"county\":{\"zone_id\":10,\"name\":\"雁塔区\",\"standard_code\":\"610113\",\"is_downtown\":false},\"street_address\":\"南三环辅路3001号靠近XMOCA艺术馆\",\"fullname\":\"朱\",\"phone\":\"18392899687\",\"coordinate\":{\"longitude\":109.013615,\"latitude\":34.209282}},\"to\":{\"province\":{\"zone_id\":11,\"name\":\"陕西省\",\"standard_code\":\"610000\"},\"city\":{\"zone_id\":26,\"name\":\"榆林市\",\"standard_code\":\"610800\"},\"county\":{\"zone_id\":80,\"name\":\"榆阳区\",\"standard_code\":\"610802\",\"is_downtown\":false},\"street_address\":\"榆林市人民政府\",\"fullname\":\"朱\",\"phone\":\"18392899687\",\"coordinate\":{\"longitude\":109.73421335220338,\"latitude\":38.285734444867}},\"distance\":572.9645,\"goods\":{\"description\":\"测3\",\"type\":\"LTL\",\"quantity\":123,\"weight\":123,\"length\":0.0,\"width\":0.0,\"height\":0.0,\"details\":{},\"package_count\":0},\"payer\":\"Receiver\",\"addition_services\":[],\"fee\":{\"description\":\"订单 S000391707MEVYL3 的货款或费用\",\"quoted\":137.76,\"discount\":0.0,\"total\":137.76,\"driver_commission\":93.68,\"details\":[{\"name\":\"运费\",\"base_price\":123.0,\"unit_price\":1.12,\"quantity\":123,\"quoted\":137.76,\"discount\":0.0,\"subtotal\":137.76,\"payer\":\"Receiver\",\"paid\":0.0}]},\"payments\":[],\"create_time\":\"2017-07-18 14:52:02\",\"status\":\"NewCreate\",\"is_same_city\":false,\"same_city_standard_code\":\"\",\"coupons\":[],\"is_first\":false},{\"order_no\":\"S000391707WPE99R\",\"user_id\":20161152,\"mode\":\"Carload\",\"book_time\":\"2017-07-18 14:49:18\",\"vehicle\":{\"vehicle_id\":2,\"name\":\"小面包\",\"avatar\":\"2.png\",\"description\":\"微型货车(1吨以下)\",\"starting_price\":0.0,\"starting_mileage\":0,\"price\":0.0,\"deadweight\":1000,\"length\":1.70,\"width\":1.10,\"height\":1.00,\"fuel_type\":\"Gas92\",\"fuel_consumption\":7.00},\"from\":{\"province\":{\"zone_id\":11,\"name\":\"陕西省\",\"standard_code\":\"610000\"},\"city\":{\"zone_id\":19,\"name\":\"西安市\",\"standard_code\":\"610100\"},\"county\":{\"zone_id\":10,\"name\":\"雁塔区\",\"standard_code\":\"610113\",\"is_downtown\":false},\"street_address\":\"南三环辅路3001号靠近XMOCA艺术馆\",\"fullname\":\"朱\",\"phone\":\"18392899687\",\"coordinate\":{\"longitude\":109.013615,\"latitude\":34.209282}},\"to\":{\"province\":{\"zone_id\":11,\"name\":\"陕西省\",\"standard_code\":\"610000\"},\"city\":{\"zone_id\":24,\"name\":\"延安市\",\"standard_code\":\"610600\"},\"county\":{\"zone_id\":68,\"name\":\"黄陵县\",\"standard_code\":\"610632\",\"is_downtown\":false},\"street_address\":\"黄陵县广播电视台\",\"fullname\":\"朱\",\"phone\":\"18392899687\",\"coordinate\":{\"longitude\":109.26295995712282,\"latitude\":35.579429613008024}},\"distance\":192.767125,\"goods\":{\"description\":\"测2\",\"type\":\"LTL\",\"quantity\":123,\"weight\":123,\"length\":0.0,\"width\":0.0,\"height\":0.0,\"details\":{},\"package_count\":0},\"payer\":\"Receiver\",\"addition_services\":[],\"fee\":{\"description\":\"订单 S000391707WPE99R 的货款或费用\",\"quoted\":113.16,\"discount\":0.0,\"total\":113.16,\"driver_commission\":76.95,\"details\":[{\"name\":\"运费\",\"base_price\":123.0,\"unit_price\":0.92,\"quantity\":123,\"quoted\":113.16,\"discount\":0.0,\"subtotal\":113.16,\"payer\":\"Receiver\",\"paid\":0.0}]},\"payments\":[],\"create_time\":\"2017-07-18 14:50:58\",\"status\":\"NewCreate\",\"is_same_city\":false,\"same_city_standard_code\":\"\",\"coupons\":[],\"is_first\":false}]}";

    public class URL {

        /**
         * binner广告页
         */
        public static final String  TESTLOGIN= "api/values";


        public static final String  TEST_AD1= "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492161211017&di=98d1c903a5e4049d33e449e90b3139f1&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fpic%2Fitem%2F63d0f703918fa0ec97d5c1dc269759ee3c6ddbd5.jpg";


        public static final String TEST_AD2 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492161885989&di=4a0086551e4c2366666946d5d7a4c34a&imgtype=0&src=http%3A%2F%2Fimg001.file.rongbiz.net%2Fuploadfile%2F201305%2F14%2F07%2F33-38-7893.jpg";




        public static final String URLGET = "http://source.chinacloudsites.cn/api/values/1";


        public static final String URLPSOT = "http://source.chinacloudsites.cn/api/values";

        public static final String TEST_DRIVER_QR = "https://www.taobao.com/";


    }



    public static List<Order> getTestList() {

        List<Order> list = new ArrayList<>();
        Order order = new Order();
        Vehicle vehicle = new Vehicle();
        vehicle.setName("卡车1号");
        Goods goods = new Goods();
        goods.setDescription("貂皮");
        order.setVehicle(vehicle);
        order.setUser_name("刘德华");

        order.setGoods(goods);


        list.add(order);
        list.add(order);
        list.add(order);
        list.add(order);
        list.add(order);
        list.add(order);
        list.add(order);

        return list;

    }





    public static List<PayChannelInfo> getPayChannels() {

        List<PayChannelInfo>  list = new ArrayList<>();
        PayChannelInfo channelInfo = new PayChannelInfo();
        list.add(channelInfo);
        list.add(channelInfo);

        return list;

    }



    public static List<FundsDetial> getFundsDetail() {

        List<FundsDetial>  list = new ArrayList<>();
        FundsDetial fundsDetial = new FundsDetial();
        fundsDetial.setAmount("sss");
        fundsDetial.setCreate_time("2018.10.26");
        fundsDetial.setFlow_direction("流入");
        fundsDetial.setFlow_comment("买入一批股票");
        fundsDetial.setIncome("253");


        list.add(fundsDetial);
        list.add(fundsDetial);
        list.add(fundsDetial);
        list.add(fundsDetial);
        list.add(fundsDetial);
        list.add(fundsDetial);
        list.add(fundsDetial);
        list.add(fundsDetial);
        return list;

    }








}
