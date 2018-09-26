package com.adhdriver.work.http.requestparam;

import com.google.gson.JsonObject;

/**
 * Created by Administrator on 2018/1/10.
 * 类描述  钱物相关接口
 * 版本
 */

public class MoneyParam extends BaseParam {


    public MoneyParam() {
    }


    /**
     * 余额充值接口
     *
     * @param order_type
     * @param amount
     * @param channel_id
     * @param os_type
     * @param device_type
     * @param device_id
     * @param payer
     * @return
     */
    public String getBalanceRechargeParam(String order_type,
                                          String amount,
                                          String channel_id,
                                          String os_type,
                                          String device_type,
                                          String device_id,
                                          String payer) {

        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty("order_type", order_type);
        jsonObject.addProperty("amount", amount);
        jsonObject.addProperty("channel_id", channel_id);
        jsonObject.addProperty("os_type", os_type);
        jsonObject.addProperty("device_type", device_type);
        jsonObject.addProperty("device_id", device_id);
        jsonObject.addProperty("payer", payer);
        return jsonObject.toString();

    }


    /**
     * 押金充值接口
     *
     * @param order_type
     * @param amount
     * @param channel_id
     * @param os_type
     * @param device_type
     * @param device_id
     * @param payer
     * @return
     */
    public String getDepositeRechargeParam(String order_type,
                                           String amount,
                                           String channel_id,
                                           String os_type,
                                           String device_type,
                                           String device_id,
                                           String payer) {

        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty("order_type", order_type);
        jsonObject.addProperty("amount", amount);
        jsonObject.addProperty("channel_id", channel_id);
        jsonObject.addProperty("os_type", os_type);
        jsonObject.addProperty("device_type", device_type);
        jsonObject.addProperty("device_id", device_id);
        jsonObject.addProperty("payer", payer);
        return jsonObject.toString();

    }


    /**
     * 设置提现密码的参数
     *
     * @param new_password
     * @return
     */
    public String getSettingDepositPassParam(String new_password) {

        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty("new_password", new_password);
        return jsonObject.toString();
    }


    /**
     * 零钱提现接口
     *
     * @param amount
     * @param payment_channel_id
     * @param os_type
     * @param device_id
     * @param open_id
     * @param pay_password
     * @return
     */
    public String getBalanceDepositParam(String amount,
                                         String payment_channel_id,
                                         String os_type,
                                         String device_id,
                                         String open_id,
                                         String pay_password) {

        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty("amount", amount);
        jsonObject.addProperty("payment_channel_id", payment_channel_id);
        jsonObject.addProperty("os_type", os_type);
        jsonObject.addProperty("device_id", device_id);
        jsonObject.addProperty("open_id", open_id);
        jsonObject.addProperty("pay_password", pay_password);

        return jsonObject.toString();

    }


}
