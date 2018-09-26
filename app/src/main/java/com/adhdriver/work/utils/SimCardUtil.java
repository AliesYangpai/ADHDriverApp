package com.adhdriver.work.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.adhdriver.work.AiDaiHuoApplication;

/**
 * Created by Administrator on 2017/6/26.
 * 类描述   判断是否存在sim卡的工具类
 * 版本
 */

public class SimCardUtil {


    /**
     * 判断是否包含SIM卡
     *
     * @return 状态
     */
    public static boolean hasSimCard() {

        TelephonyManager telMgr = (TelephonyManager)
                AiDaiHuoApplication.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
        int simState = telMgr.getSimState();
        boolean result = true;
        switch (simState) {
            case TelephonyManager.SIM_STATE_ABSENT:
                result = false; // 没有SIM卡
                break;
            case TelephonyManager.SIM_STATE_UNKNOWN:
                result = false;
                break;
        }

        return result;
    }


}
