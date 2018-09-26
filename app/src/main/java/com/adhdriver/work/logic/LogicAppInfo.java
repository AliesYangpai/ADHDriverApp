package com.adhdriver.work.logic;

import com.adhdriver.work.utils.VersionUtil;

import org.feezu.liuli.timeselector.Utils.TextUtil;

/**
 * Created by Administrator on 2017/12/8.
 * 类描述  版本信息的逻辑类
 * 版本
 */

public class LogicAppInfo {

    public boolean isNewVersion(String versionNum) {

        boolean result = false;

        if (!TextUtil.isEmpty(versionNum)) {

            int serverCode = Integer.valueOf(versionNum);

            int localCode = VersionUtil.getVersionCode();

            if (serverCode >localCode){

                result = true;
            }

        }

        return result;

    }
}
