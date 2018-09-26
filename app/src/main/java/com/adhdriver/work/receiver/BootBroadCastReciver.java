package com.adhdriver.work.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.constant.ConstLog;
import com.adhdriver.work.service.ServiceWatch1;
import com.adhdriver.work.service.ServiceWatch2;

/**
 * Created by Administrator on 2017/5/11.
 * 类描述   监听杀死的广播
 * 版本
 */

public class BootBroadCastReciver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        if (null != intent) {

            String action = intent.getAction();


            switch (action) {

                case ConstIntent.IntentAction.AWAKE_WATCH_1:  //唤醒1号


                    context.startService( getTheIntent(context,ServiceWatch1.class));

                    Log.i(ConstLog.SERVICE_TAG,"======广播唤醒1号");

                    break;


                case ConstIntent.IntentAction.AWAKE_WATCH_2: //唤醒2号

//                    Intent intentService2=  new Intent(context,ServiceWatch2.class);

                    context.startService(getTheIntent(context,ServiceWatch2.class));

                    Log.i(ConstLog.SERVICE_TAG,"======广播唤醒2号");

                    break;





            }

        }

    }


    private Intent getTheIntent(Context context ,Class<?> targetClass) {

        Intent intent = new Intent(context,targetClass);

        return intent;

    }






}
