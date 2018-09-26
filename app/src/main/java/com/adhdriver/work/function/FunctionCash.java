package com.adhdriver.work.function;

import com.adhdriver.work.constant.ConstSign;

/**
 * Created by Administrator on 2018/1/11.
 * 类描述
 * 版本
 */

public class FunctionCash {

    /**
     * 自动补全数字
     *
     * @param cash
     * @return
     */
    public  String getZeroFrontPoint(String cash) {

        String finalCash = cash;

        if (cash.startsWith(ConstSign.POINT)) {

            finalCash = "0" + cash;

        }

        return finalCash;

    }

}
