package com.adhdriver.work.logic;

import android.text.TextUtils;

import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.constant.ConstSign;

import org.feezu.liuli.timeselector.Utils.TextUtil;

/**
 * Created by Administrator on 2017/12/29.
 * 类描述
 * 版本
 */

public class LogicMoney {


    /**
     * 用户没有输入金额
     *
     * @param param
     * @return
     */
    public boolean isEmptyCash(String param) {

        return TextUtil.isEmpty(param);
    }


    /**
     * 用户输入先输入“.”
     *
     * @param param
     * @return
     */
    public boolean isPointStart(String param) {
        return param.equals(ConstSign.POINT);

    }

    /**
     * 是否是0
     *
     * @param param
     * @return
     */
    public boolean is0(String param) {

        float f = Float.valueOf(param);
        return f == 0;
    }


    /**
     * 小于0.1
     *
     * @return
     */
    public boolean isSmallThan0Point1(String param) {

        float f = Float.valueOf(param);
        return f < 0.1;
    }


    /**
     * 小于1
     *
     * @return
     */
    public boolean isSmallThan1(String param) {

        float f = Float.valueOf(param);
        return f < 1;
    }

    /**
     * ==============================================用于验证提现密码的=============================================================
     */

    /**
     * @param pass
     * @return
     */
    public boolean isEmptyDepositPass(String pass) {

        return TextUtils.isEmpty(pass);

    }


    public boolean isPassLessThan6(String pass) {

        return pass.length() < 6;
    }

    public boolean isEmptyPassAgain(String passAgain) {

        return TextUtils.isEmpty(passAgain);
    }


    public boolean isPassNotSame(String pass,String passAgain) {

        return !pass.equals(passAgain);
    }

    /**
     * ==============================================用于验证提现密码的=============================================================
     */



    public boolean isAliChannel(String channelId) {

        return channelId.equals(ConstLocalData.ALIPAY);

    }

    public boolean isWxChannel(String channelId) {
        return channelId.equals(ConstLocalData.WX);
    }



}
