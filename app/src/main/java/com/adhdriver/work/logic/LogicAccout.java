package com.adhdriver.work.logic;

import com.adhdriver.work.constant.ConstJsonType;
import com.adhdriver.work.utils.IdCardUtil;

import org.feezu.liuli.timeselector.Utils.TextUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/12/8.
 * 类描述  账户相关的逻辑类 验证、密码
 * 版本
 */

public class LogicAccout {

    /**
     * 合法电话号码
     * @param param
     * @return
     */
    public boolean isLegalPhone(String param) {
        String strPattern = "[1][345678]\\d{9}";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(param);
        return m.matches();

    }



    /**
     * 字母、数字（无字母开头限制）8-16个字符之间
     */

    public boolean isLegalPass(String pass) {

        String strPattern = "(?!^\\d+$)(?!^[a-zA-Z]+$)[0-9a-zA-Z]{8,16}$";

        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(pass);
        return m.matches();
    }


    /**
     * 获取token类型化
     * @param token
     * @return
     */
    public String getTokenType(String token) {

        String result = "";

        if (!TextUtil.isEmpty(token)) {
            if (token.startsWith(ConstJsonType.JSON_ARRY)) {
                result =ConstJsonType.JSON_ARRY;
            } else if (token.startsWith(ConstJsonType.JSON_OBJ)) {
                result = ConstJsonType.JSON_OBJ;
            }
        }
        return result;

    }


    /**
     * jsonObj开头
     * @param token
     * @return
     */
    public boolean isJsonObjStart(String token) {

        boolean result = false;

        if(!TextUtil.isEmpty(token) && token.startsWith(ConstJsonType.JSON_OBJ)) {

            result = true;

        }

        return result;


    }


    /**
     * jsonArray开头
     * @param token
     * @return
     */
    public boolean isJsonArrayStart(String token) {

        boolean result = false;

        if(!TextUtil.isEmpty(token) && token.startsWith(ConstJsonType.JSON_ARRY)) {

            result = true;
        }

        return result;

    }


    /**
     * 身份证合法性验证
     * @param identifyId
     * @return
     */
    public boolean isLegalIdCard(String identifyId) {

        boolean result = false;
        IdCardUtil idCardUtil = new IdCardUtil();
        result = idCardUtil.verify(identifyId);

        return result;
    }





}
