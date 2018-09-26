package com.adhdriver.work.function;

import android.text.TextUtils;

/**
 * Created by Administrator on 2017/12/12.
 * 类描述
 * 版本
 */

public class FunctionRegUserInfo {




    /**
     * 获取默认的String的值
     *
     * @param code
     * @return
     */
    public String  getTargetInviteCode(String code) {
        String target = "0";
        if (!TextUtils.isEmpty(code)) {
            target = code;
        }
        return target;
    }

}
