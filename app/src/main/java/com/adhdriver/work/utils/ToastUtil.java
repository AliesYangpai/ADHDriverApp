package com.adhdriver.work.utils;

import android.content.Context;
import android.widget.Toast;

import org.feezu.liuli.timeselector.Utils.TextUtil;

/**
 * Created by Administrator on 2016/9/22 0022.
 * 类描述
 * 版本
 */
public class ToastUtil {



    public static void showMsg(Context context, String message) {

            if(!TextUtil.isEmpty(message)) {

                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
//            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


    public static void showMsg(Context context, int id) {

         Toast.makeText(context,context.getString(id), Toast.LENGTH_SHORT).show();

    }



}
