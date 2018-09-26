package com.adhdriver.work.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.adhdriver.work.constant.ConstEvent;
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.entity.EventEntity;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {



    private IWXAPI api;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.pay_result);
////
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {



        Log.i("wexinzhifu","==================onReq  "+baseResp.getType()+ "code:"+baseResp.errCode+"openId:"+baseResp.openId+"transaction:"+baseResp.transaction+"=========ToString:"+baseResp.toString());



//        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//
//            if(baseResp.errCode == 0) {
//                postEventToWeight(ConstEvent.WX_PAY_SUCCESS_BACK);
//            }else {
//                postEventToWeight(ConstEvent.WX_PAY_FAIL_BACK);
//            }
//
//            WXPayEntryActivity.this.finish();
//        }else {
//            postEventToWeight(ConstEvent.WX_PAY_FAIL_BACK);
//        }
//
//
//        WXPayEntryActivity.this.finish();




        WXPayEntryActivity.this.finish();

        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX && baseResp.errCode == 0) {

            postEventToWeight(ConstEvent.WX_PAY_SUCCESS_BACK);

        }


    }



    private void postEventToWeight(int tag) {

        EventEntity eventEntity = new EventEntity();
        eventEntity.setNotifyTag(tag);

        EventBus.getDefault().post(eventEntity);

    }





}