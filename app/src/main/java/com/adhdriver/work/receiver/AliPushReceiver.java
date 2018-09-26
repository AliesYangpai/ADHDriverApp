package com.adhdriver.work.receiver;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

;
import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.constant.ConstPush;
import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.http.CallServer;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.http.HttpSingleResponseListener;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.http.RequestPacking;
import com.adhdriver.work.push.PushEntity;
import com.adhdriver.work.ui.activity.common.FirstEnterActivity;
import com.adhdriver.work.ui.activity.driver.MainDriverActivity;
import com.adhdriver.work.ui.activity.push.AlertDialogActivity;
import com.adhdriver.work.utils.SpUtil;
import com.alibaba.sdk.android.push.MessageReceiver;
import com.alibaba.sdk.android.push.notification.CPushMessage;
import com.google.gson.Gson;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.Map;


/**
 * Created by Administrator on 2017/9/8.
 * 类描述  阿里云推送 的Receiver
 * 版本
 */

public class AliPushReceiver extends MessageReceiver {


    // 消息接收部分的LOG_TAG
    public static final String REC_TAG = "receiver";

    private ParseSerilizable parseSerilizable;


    public AliPushReceiver() {

        parseSerilizable = new ParseSerilizable();

    }

    @Override
    public void onNotification(Context context, String title, String summary, Map<String, String> extraMap) {
        // TODO 处理推送通知
        Log.i("aliyunPush", "Receive notification, title: " + title + ", summary: " + summary + ", extraMap: " + extraMap);


    }


    @Override
    public void onMessage(Context context, CPushMessage cPushMessage) {
        Log.i("aliyunPush", "onMessage, messageId: " + cPushMessage.getMessageId() + ", title: " + cPushMessage.getTitle() + ", content:" + cPushMessage.getContent());
    }

    @Override
    public void onNotificationOpened(Context context, String title, String summary, String extraMap) {
        Log.i("aliyunPush", "Receive onNotificationOpened, title: " + title + ", summary: " + summary + ", extraMap: " + extraMap);




        doClickOpened(context, extraMap);


    }


    @Override
    protected void onNotificationClickedWithNoAction(Context context, String title, String summary, String extraMap) {
        Log.e("aliyunPush", "onNotificationClickedWithNoAction, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap);

    }


    @Override
    protected void onNotificationReceivedInApp(Context context, String title, String summary, Map<String, String> extraMap, int openType, String openActivity, String openUrl) {
        Log.i("aliyunPush", "onNotificationReceivedInApp, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap + ", openType:" + openType + ", openActivity:" + openActivity + ", openUrl:" + openUrl);


        Log.i("AlertDialogActivity", "onNotificationReceivedInApp 调用了=======");


        doAppIn(context, extraMap);

    }

    @Override
    protected void onNotificationRemoved(Context context, String messageId) {

        Log.e("aliyunPush", "onNotificationRemoved");
    }


    /**
     * 点击打开事件
     *
     * @param context
     * @param pushString
     */
    private void doClickOpened(Context context, String pushString) {


        if (isLogin()) {

            doGoToMain(context, pushString);


        } else {

            /**
             * 进入到首次进入的界面登陆界面
             */

            doGoToFirstPage(context);

        }


    }


    /**
     * 进入主界面
     *
     * @param context
     * @param pushString
     */
    private void doGoToMain(Context context, String pushString) {

        PushEntity pushEntity = getPushObject(pushString);

        if (null != pushEntity) {

            Bundle bundle = new Bundle();
            Intent intent = new Intent();
            bundle.putSerializable(ConstIntent.BundleKEY.PUSH_CLICK_OPEN, pushEntity);
            bundle.putInt(ConstIntent.BundleKEY.JUMP_TAG,ConstIntent.BundleValue.PUSH);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setClass(context, MainDriverActivity.class);
            intent.putExtras(bundle);
            context.startActivity(intent);

            /**
             * 标记推送
             */
            SpUtil.getInstance().saveBooleanTosp(ConstSp.SP_KEY_HAS_PUSH, ConstSp.SP_VALUE.SIGN_PUSH_TRUE);

        }


    }


    /**
     * 内操作时候进行的处理
     *
     * @param context
     * @param extraMap
     */
    private void doAppIn(Context context, Map extraMap) {


        /**
         * 1.判断用户是否已经退出
         */


        if (isLogin()) {

            if (null != extraMap) {
                /**
                 * 进行有效核心操作
                 */
                PushEntity pushEntity = getPushObject(extraMap.toString());


                String pushScopeType = pushEntity.getPushScopeType();


                switch (pushScopeType) {


                    case ConstPush.ScopeType.ORDER:



                        doGetOrderInfoAndShowAlert(HttpConst.URL.ORDERS+HttpConst.SEPARATOR+pushEntity.getOrderNo(),context,pushEntity);


//                        goToDialogActivity(pushEntity, context);

                        break;

//                    case ConstPush.ScopeType.BUSINESS:
//
//                        doDriverScanAbout(pushEntity, context);
//
//                        break;
                }


            }

        } else {


            doGoToFirstPage(context);

        }
    }





    private void doGetOrderInfoAndShowAlert(String url, final Context context, final PushEntity pushEntity) {


        Request<String> request = RequestPacking.getInstance().getRequestParamForJson(url, RequestMethod.GET,null);

        CallServer.getInstance().add(HttpConst.HTTP_WHAT, request, new HttpSingleResponseListener<String>() {
            @Override
            protected void OnHttpStart(int what) {

            }

            @Override
            protected void OnHttpSuccessed(int what, Response<String> response) {
                if(null != parseSerilizable) {
                   Order order  =parseSerilizable.getParseToObj(response.get(), Order.class);

                    if(null != order) {

                        goToDialogActivity(pushEntity,order, context);
                    }
                }

            }

            @Override
            protected void onHttpFailed(int what, Response<String> response) {

            }

            @Override
            protected void OnHttpFinish(int what) {

            }
        });


    }




    private void goToDialogActivity(PushEntity pushEntity ,Order order, Context context) {


        Intent intent = new Intent(context, AlertDialogActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstIntent.BundleKEY.PUSH_DIALOG_ORDER, order);
        bundle.putSerializable(ConstIntent.BundleKEY.PUSH_DIALOG_ENTITY,pushEntity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtras(bundle);
        context.startActivity(intent);


    }

//    /**
//     * 进行司机扫码添加相关
//     */
//
//    private void doDriverScanAbout(PushEntity pushEntity, Context mContext) {
//
//        String bussinessId = pushEntity.getBissinessId();
//
//        /**
//         * 我的抢单
//         */
//        /**
//         * 判断并清除指定的activity
//         */
//        Bundle bundle = new Bundle();
//        bundle.putString(ConstIntent.BundleKEY.BUSINESS_ID, bussinessId);
//        Intent intent = new Intent(mContext, InviteDetialActivity.class);
//        intent.putExtras(bundle);
//        mContext.startActivity(intent);
//
//    }


    /**
     * 解析推送对象
     *
     * @param mapString
     * @return
     */
    private PushEntity getPushObject(String mapString) {


        PushEntity pushEntity = new Gson().fromJson(mapString, PushEntity.class);
        return pushEntity;
    }


    /**
     * 判断是否已经登陆
     *
     * @return
     */
    private boolean isLogin() {

        boolean result = SpUtil.getInstance().getBoolenValue(ConstSp.SP_KEY_IS_LOGIN_OR_NOT, ConstSp.SP_VALUE.DEFAULT_BOOLEAN);

        return result;

    }

    /**
     * 进入首页
     */
    private void doGoToFirstPage(Context mContext) {


        Bundle bundle = new Bundle();
        Intent intent = new Intent(mContext, FirstEnterActivity.class);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);


    }


}
