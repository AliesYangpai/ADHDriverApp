package com.adhdriver.work.verification;

import com.adhdriver.work.constant.ConstSign;
import com.adhdriver.work.constant.ConstTag;

import org.feezu.liuli.timeselector.Utils.TextUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/11/24.
 * 类描述 规则验证
 * 版本
 */

public class VertifyRule {


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
     * 电话号码号段验证
     */
            /*
        移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		联通：130、131、132、152、155、156、185、186
		电信：133、153、180、189、（1349卫通）
		总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		*/
    public  boolean isLegalPhone(String str) {

        String strPattern = "[1][345678]\\d{9}";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(str);
        return m.matches();
    }



    /**
     * 获取tokenType
     *
     * @param back
     * @return
     */
    public String getTokenTypeByContains(String back) {


        String result = "";

        if (!TextUtil.isEmpty(back)) {
            if (back.contains(ConstSign.JSON_ARRY_START)) {
                result = ConstTag.JsonTag.JSON_ARRY;
            } else {
                result = ConstTag.JsonTag.JSON_OBJ;
            }
        }
        return result;

    }


    /**
     * 判断是jsonObj
     *
     * @param back
     * @return
     */
    public boolean isJsonObj(String back) {

        boolean result = false;

        String str = getTokenTypeByContains(back);

        if(str.equals(ConstTag.JsonTag.JSON_OBJ)) {
            result = true;
        }
        return result;

    }


    /**
     * 判断是jsonJsonArry
     *
     * @param back
     * @return
     */
    public boolean isJsonArry(String back) {

        boolean result = false;

        String str = getTokenTypeByContains(back);

        if(str.equals(ConstTag.JsonTag.JSON_ARRY)) {
            result = true;
        }
        return result;

    }

}
