package com.adhdriver.work.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.adhdriver.work.constant.ConstEvent;
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.entity.EventEntity;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;


public class WXEntryActivity extends Activity implements IWXAPIEventHandler {


    private IWXAPI api;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.pay_result);
        api = WXAPIFactory.createWXAPI(this, ConstLocalData.APPID_WX);
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


        WXEntryActivity.this.finish();

        if (null != baseResp) {

            if (baseResp instanceof SendAuth.Resp) {

                SendAuth.Resp resp = (SendAuth.Resp) baseResp;

                int errCode = resp.errCode;

                switch (errCode) {

                    case BaseResp.ErrCode.ERR_OK:


                        Log.i("wx_autonLoginback", "返回码:" + resp.errCode + " country:" + resp.country + " resp.code" + resp.code);


                        postEventToWXAuth(ConstEvent.WxAuthAbout.SUCCESS,resp.code);

                        break;


                    case BaseResp.ErrCode.ERR_USER_CANCEL:

                        postEventToWXAuth(ConstEvent.WxAuthAbout.CANCEL,"授权取消");


                        break;


                    case BaseResp.ErrCode.ERR_AUTH_DENIED:


                        postEventToWXAuth(ConstEvent.WxAuthAbout.FAIL,"");
                        break;
                }
            }
        }

    }


    private void postEventToWXAuth(int tag,String params) {

        EventEntity eventEntity = new EventEntity();
        eventEntity.setNotifyTag(tag);
        eventEntity.setNotifyMsg(params);
        EventBus.getDefault().post(eventEntity);

    }


}