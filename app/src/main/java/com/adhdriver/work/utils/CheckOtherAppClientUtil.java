package com.adhdriver.work.utils;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import com.adhdriver.work.AiDaiHuoApplication;

import java.util.List;

/**
 * Created by Administrator on 2017/11/20.
 * 类描述
 * 版本
 */

public class CheckOtherAppClientUtil {


    /**
     * 检测是否安装 新浪app
     * @return
     */
    public static boolean hasSineClient() {


        boolean result = false;

        Intent weiboIntent = new Intent(Intent.ACTION_SEND);
        weiboIntent.setType("text/plain");
        PackageManager pm = AiDaiHuoApplication.getInstance().getPackageManager();
        List<ResolveInfo> matches = pm.queryIntentActivities(weiboIntent,
                PackageManager.MATCH_DEFAULT_ONLY);
        String packageName = "com.sina.weibo";
        ResolveInfo info = null;
        for (ResolveInfo each : matches) {
            String pkgName = each.activityInfo.applicationInfo.packageName;
            if (packageName.equals(pkgName)) {
                info = each;
                break;
            }
        }

        if (null != info) {

            result = true;

        }

        return result;

    }
}
