package com.adhdriver.work.http;

import android.util.Log;


import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.constant.ConstTag;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Administrator on 2017/3/6 0006.
 * 类描述   司机请求参数
 * 版本
 */
public class RequestDriverParams {



    /**
     * 获取支付二维码
     *
     * @param order_type       Order
     * @param reference_number 订单编号
     * @param channel_id       //支付渠道id
     * @param device_id        //设备唯一编号
     * @return
     */


//    {"channel_id":"Weixin","order_type":"Order","device_id":"861626032236341","reference_number":"A0000117065AIP46","parameters": {"appid":"wxf4cf149afc395166","trade_type":"NATIVE"}}
    public static String getQRCodeParam(String order_type, String reference_number, String channel_id, String device_id,String payer) {
        String jsonString = "";
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("order_type", order_type);
        jsonObject.addProperty("reference_number", reference_number);
        jsonObject.addProperty("channel_id", channel_id);
        jsonObject.addProperty("os_type", ConstTag.PayTag.channel_os_type);
        jsonObject.addProperty("device_type", ConstTag.PayTag.channel_device_type);
        jsonObject.addProperty("device_id", device_id);



        jsonObject.addProperty("payer", payer);


        if (channel_id.equals(ConstTag.PayTag.WX)) {

            JsonObject jsonParam = new JsonObject();
            jsonParam.addProperty("appid", ConstLocalData.WX_APPID);
//            jsonParam.addProperty("trade_type",ConstTag.PayTag.QR_WX);

            jsonObject.add("parameters", jsonParam);
        } else {
            JsonObject jsonParam = new JsonObject();

            jsonObject.add("parameters", jsonParam);
        }


        jsonString = jsonObject.toString();


        return jsonString;


    }


    /**
     * 获取手机验证码
     *
     * @param phone
     * @return
     */
    public static String getConfrimCode(String phone,String options) {


        String str = "";


        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("phone", phone);
        jsonObject.addProperty("options", options);

        str = jsonObject.toString();
        return str;


    }


    /**
     * 验证手机验证码
     */

    public  static String getVertifyConfrimCode(String phone, String code) {


        String str = "";


        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("phone", phone);
        jsonObject.addProperty("pass_code", code);
        str = jsonObject.toString();
        return str;


    }


    /**
     * 用户注册
     *
     * @param phone
     * @param password
     * @param pass_code //验证码
     * @return
     */
    public static String getRegisteParams(String phone, String password, String pass_code,String defaultName) {


        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("phone", phone);
        jsonObject.addProperty("password", password);
        jsonObject.addProperty("pass_code", pass_code);
        jsonObject.addProperty("user_name",defaultName);


        String back = jsonObject.toString();

        Log.i(HttpConst.LOG_REQUEST, "注册参数：" + back);

        return back;


    }


    /**
     * 用户登陆
     *
     * @param user_phone
     * @param user_password
     * @return
     */
    public static String getLoginParams(String user_phone, String user_password) {


        String jsonString = "";
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username", user_phone);
        jsonObject.addProperty("password", user_password);

        jsonString = jsonObject.toString();


        return jsonString;


    }


    /**
     * 完善用户信息的注册
     *
     * @param
     * @return
     */
    public static String getUpdateUserInfoInFinishRegist(String userName, String user_identity_card_no, String pathFront, String pathBack, String inviteCode) {

        String jsonString = "";
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user_name", userName);
        jsonObject.addProperty("identity_card_no", user_identity_card_no);
        jsonObject.addProperty("identity_card_path", pathFront);
        jsonObject.addProperty("identity_card_back_path", pathBack);
        jsonObject.addProperty("unique_code", inviteCode);
        jsonString = jsonObject.toString();


        return jsonString;


    }




    /**
     * 获取充值密码的参数
     *
     * @param phone
     * @param passcode
     * @param new_password
     * @return
     */
    public static String getResetPassParam(String phone, String passcode, String new_password) {

        String jsonString = "";
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("phone", phone);
        jsonObject.addProperty("pass_code", passcode);
        jsonObject.addProperty("new_password", new_password);


        jsonString = jsonObject.toString();


        return jsonString;


    }


    /**
     * 修改密码
     */

    public static String getChangePassParam(String oldPass, String new_password) {

        String jsonString = "";
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("password", oldPass);
        jsonObject.addProperty("new_password", new_password);


        jsonString = jsonObject.toString();


        return jsonString;


    }






//    /**
//     * 获取司机经纬度参数
//     *
//     * @param longitude 经度
//     * @param latitude  纬度
//     * @return
//     */
//    public static String getCoordinate(double longitude, double latitude,String zone_code) {
//
//        String jsonString = "";
//
//        JSONObject jsonObject = new JSONObject();
//        try {
//
//            jsonObject.put("longitude", longitude);
//            jsonObject.put("latitude", latitude);
//            jsonObject.put("zone_code", zone_code);
//
//            jsonString = jsonObject.toString();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//        return jsonString;
//
//
//    }

    /**
     * 获取司机经纬度参数
     *
     * @param longitude 经度
     * @param latitude  纬度
     * @return
     */
    public static String getCoordinate(double longitude, double latitude,String zone_code,String driver_vehicle_id,String vehicle_category_name) {

        String jsonString = "";

        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("longitude", longitude);
            jsonObject.put("latitude", latitude);
            jsonObject.put("zone_code", zone_code);
            jsonObject.put("driver_vehicle_id", driver_vehicle_id);
            jsonObject.put("vehicle_category_name",vehicle_category_name);
            jsonString = jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return jsonString;


    }


    /**
     * 发起抢单传入参数
     *
     * @param driverId
     * @return
     */

    public static String getGrabOrderParam(String driverId, String order_no) {

        String jsonString = "";
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("driverId", driverId);

        jsonObject.addProperty("order_no", order_no);

        jsonString = jsonObject.toString();


        return jsonString;


    }


    /**
     * 发起竞价传入参数
     *
     * @param
     * @param
     * @param price
     * @return
     */
    public static String getBiddingOrderParam(String price) {

        String jsonString = "";
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("price", price);  //0512

        jsonString = jsonObject.toString();


        return jsonString;


    }


    /**
     * 设置图片到服务端
     *
     * @param
     * @return
     */
    public static String getSetPicParam(String left, String front, String back) {


        String jsonString = "";
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("load_photo_left", left);  //0512
        jsonObject.addProperty("load_photo_front", front);  //0512
        jsonObject.addProperty("load_photo_back", back);  //0512
        jsonString = jsonObject.toString();


        return jsonString;

    }


    /**
     * 设置取消原因
     *
     * @return
     */
    public static String getCancelReasonParam(String reason,String os_type) {


        String jsonString = "";
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("remark", reason);  //0512
        jsonObject.addProperty("os_type", os_type);  //0803
        jsonString = jsonObject.toString();


        return jsonString;

    }


    /**
     * 需要改价
     *
     * @param //重量
     * @return
     */
    public static String getChangeWeightParam(String weight, String length, String width, String height) {


        String jsonString = "";
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("weight", weight);  //0512
        jsonObject.addProperty("length", length);  //0512
        jsonObject.addProperty("width", width);  //0512
        jsonObject.addProperty("height", height);  //0512
        jsonString = jsonObject.toString();


        return jsonString;

    }

    /**
     * 输入收货人的验证码 来完成这个订单
     *
     * @param
     * @return
     */
    public static String getReciverCodeParam(String reciverCode) {


        String jsonString = "";
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("pass_code", reciverCode);


        jsonString = jsonObject.toString();


        return jsonString;

    }








    /**
     * 生车考试成绩的参数
     *
     * @param driver_id      司机id
     * @param mark           成绩
     * @param paper_id       试卷id
     * @param correct_number 正确题目数量
     * @param error_number   错误题目数量
     * @return
     */
    public static String getExamScoreParam(String driver_id, int mark, int paper_id, int correct_number, int error_number) {
        String jsonString = "";
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("driver_id", driver_id);
        jsonObject.addProperty("mark", mark);
        jsonObject.addProperty("paper_id", paper_id);
        jsonObject.addProperty("correct_number", correct_number);
        jsonObject.addProperty("error_number", error_number);
        jsonString = jsonObject.toString();


        return jsonString;


    }






    /**
     * 初次设置支付密码
     * @param new_password
     * @return
     */
    public static String getSetPayPass(String new_password) {
        String jsonString = "";
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("new_password", new_password);

        jsonString = jsonObject.toString();


        return jsonString;


    }






    /**
     * 开始提现的请求参数
     * @param amount
     * @param payment_channel_id
     * @param os_type
     * @param device_id
     * @param open_id
     * @return
     */
    public static String getWithDrawDepositeParam(String amount,String payment_channel_id,String os_type,String device_id,String open_id,String pay_password) {


        String jsonString = "";
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("amount", amount);
        jsonObject.addProperty("payment_channel_id", payment_channel_id);
        jsonObject.addProperty("os_type", os_type);
        jsonObject.addProperty("device_id", device_id);
        jsonObject.addProperty("open_id", open_id);
        jsonObject.addProperty("pay_password", pay_password);

        jsonString = jsonObject.toString();


        return jsonString;


    }


    /**
     * 开始瓜分红包 的请求参数
     * @param amount
     * @return
     */
    public static String getToCarveUpRegPacketParam(String amount) {
        String jsonString = "";
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("amount", amount);

        jsonString = jsonObject.toString();


        return jsonString;


    }



    /**
     * 修改支付密码的参数
     * @param password
     * @param new_password
     * @return
     */
    public static String getChangePayParam(String password,String new_password) {
        String jsonString = "";
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("password", password);
        jsonObject.addProperty("new_password", new_password);
        jsonString = jsonObject.toString();

        return jsonString;


    }






    /**
     * 忘记支付密码的参数
     * @param new_pay_password
     * @param last_card_no
     * @param pass_code
     * @return
     */
    public static String getForgetPayPassParam(String last_card_no,String new_pay_password,String pass_code) {
        String jsonString = "";
        JsonObject jsonObject = new JsonObject();





        jsonObject.addProperty("last_card_no", last_card_no);
        jsonObject.addProperty("new_pay_password", new_pay_password);
        jsonObject.addProperty("pass_code", pass_code);
        jsonString = jsonObject.toString();

        return jsonString;


    }







//    {
//        "order_type": "Order",
//            "amount": 0,
//            "channel_id": "string",
//            "os_type": "iOS",
//            "device_type": "Web",
//            "device_id": "string",
//            "parameters": {}
//    }

    /**
     * 获取 充值参数
     * @param order_type  ['Order', 'BalanceRecharge', 'DepositRecharge', 'Other'],
     * @param amount    充值金额。 ,
     * @param channel_id  支付渠道
     * @param os_type     操作系统。 = ['iOS', 'Android', 'Windows'],
     * @param device_type   设备类型。 = ['Web', 'PC', 'Phone', 'Pad']
     * @param device_id    设备编号。 ,
     * @return
     */
    public static String getRechagreParam(
            String order_type,
            String amount,
            String channel_id,
            String os_type,
            String device_type,
            String device_id,
            String payer
            ) {






//        {
//            "order_type": "Order",
//                "amount": 0,
//                "channel_id": "string",
//                "os_type": "iOS",
//                "device_type": "Web",
//                "device_id": "string",
//                "parameters": {},
//            "payer": "Shipper"
//        }






        String jsonString = "";
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("order_type", order_type);
        jsonObject.addProperty("amount", amount);
        jsonObject.addProperty("channel_id", channel_id);
        jsonObject.addProperty("os_type", os_type);
        jsonObject.addProperty("device_type", device_type);
        jsonObject.addProperty("device_id", device_id);
        jsonObject.addProperty("payer", payer);
        jsonString = jsonObject.toString();

        return jsonString;


    }


    /**
     * 添加新车辆的相关请求参数
     * @param vehicle_id
     * @param plate_number
     * @param vehicle_license
     * @param vehicle_photo_front_path
     * @param vehicle_photo_back_path
     * @param car_insurance_policy
     * @param business_insurance
     * @return
     */
    public static String getAddNewVehicleInfoParam(String vehicle_id,
                                                   String plate_number,
                                                   String vehicle_license,
                                                   String vehicle_photo_front_path,
                                                   String vehicle_photo_back_path,
                                                   String car_insurance_policy,
                                                   String business_insurance,
                                                   String category_name,
                                                   String vehicle_dead_weight,
                                                   String vehicle_stere) {

//        {
//            "vehicle_id": 0,
//                "plate_number": "string",
//                "vehicle_license": "string",
//                "vehicle_photo_front_path": "string",
//                "vehicle_photo_back_path": "string",
//                "car_insurance_policy": "string",
//                "business_insurance": "string",
//                "category_name": "Box",
//                "vehicle_dead_weight": 0,
//                "vehicle_stere": 0
//        }







        String jsonString = "";
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("vehicle_id", vehicle_id);
        jsonObject.addProperty("plate_number", plate_number);
        jsonObject.addProperty("vehicle_license", vehicle_license);
        jsonObject.addProperty("vehicle_photo_front_path", vehicle_photo_front_path);
        jsonObject.addProperty("vehicle_photo_back_path", vehicle_photo_back_path);
        jsonObject.addProperty("car_insurance_policy", car_insurance_policy);
        jsonObject.addProperty("business_insurance", business_insurance);
        jsonObject.addProperty("category_name", category_name);
        jsonObject.addProperty("vehicle_dead_weight", vehicle_dead_weight);
        jsonObject.addProperty("vehicle_stere", vehicle_stere);
        jsonString = jsonObject.toString();

        return jsonString;

    }






    /**
     *  微信支付验证收宽
     * @param app_type  //['ClientApp', 'DriverApp', 'PartnerApp', 'BusinessApp'],
     * @param os_type  //['iOS', 'Android', 'Windows'],
     * @param payment_record_id  //支付记录号
     * @return
     */
    public static String getWXPayCheckParam(String app_type,String os_type,String payment_record_id,String payer) {


        String jsonString = "";
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("app_type", app_type);
        jsonObject.addProperty("os_type", os_type);
        jsonObject.addProperty("payment_record_id", payment_record_id);
        jsonObject.addProperty("payer", payer);

        jsonString = jsonObject.toString();

        return jsonString;


    }







    /**
     * 我要出发传递的参数
     * @param longitude 经度
     * @param latitude   纬度
     * @return
     */
    public static String getStartGoParams(double longitude,double latitude) {


//        {
//            "longitude": 0,
//                "latitude": 0
//        }

        String jsonString = "";
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("longitude", longitude);
        jsonObject.addProperty("latitude", latitude);
        jsonString = jsonObject.toString();

        return jsonString;


    }






    /**
     *  阿里验证收款
     * @param app_type  //['ClientApp', 'DriverApp', 'PartnerApp', 'BusinessApp'],
     * @param os_type  //['iOS', 'Android', 'Windows'],
     * @param reference_number  //系统订单号
     * @return
     */
    /**
     *  阿里验证收款
     * @param out_trade_no //商户订单号 ,
     * @param trade_no 支付宝交易号，可为空
     * @param os_type   //['iOS', 'Android', 'Windows'],
     * @param app_type ['ClientApp', 'DriverApp', 'PartnerApp', 'BusinessApp'],
     * @return
     */
    public static String getAliPayCheckParam(String out_trade_no,String trade_no,String os_type,String app_type) {


        String jsonString = "";
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("out_trade_no", out_trade_no);
        jsonObject.addProperty("trade_no", trade_no);
        jsonObject.addProperty("app_type", app_type);
        jsonObject.addProperty("os_type", os_type);

        jsonString = jsonObject.toString();

        return jsonString;


    }




}
