package com.adhdriver.work.http.upload;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.adhdriver.work.constant.ConstOss;
import com.adhdriver.work.entity.OssConfig;
import com.adhdriver.work.verification.VertifyNotNull;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;

/**
 * Created by Administrator on 2017/7/6.
 * 类描述
 * 版本
 */

public abstract class OSSAdhCompletedCallBack implements OSSCompletedCallback<PutObjectRequest, PutObjectResult> {


    private Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);


            Bundle bundle = getMsgBundle(msg);


            if (checkObject(bundle)) {


                getPassedObject(msg.what, bundle);
            }


        }

    };


    private void getPassedObject(int tag, Bundle bundle) {


        switch (tag) {


            case ConstOss.SUCCESS:

                String objectKeySuccess = bundle.getString(ConstOss.BUNDLE_REQUEST, ConstOss.DEFAULT);
                String returnData = bundle.getString(ConstOss.BUNDLE_RESULT, ConstOss.DEFAULT);
                onOssSuccess(objectKeySuccess, returnData);


                break;


            case ConstOss.FAILURE:


                String objectKeyFailure = bundle.getString(ConstOss.BUNDLE_REQUEST);
                String e = bundle.getString(ConstOss.CLIENT_EXCEPTION);
                String e1 = bundle.getString(ConstOss.SERVICE_EXCEPTION);

                onOssFailure(objectKeyFailure, e, e1);

                break;
        }


    }


    private Bundle getMsgBundle(Message msg) {


        Bundle bundle = null;

        if (checkObject(msg)) {


            bundle = msg.getData();

        }


        return bundle;

    }

    private boolean checkObject(Object object) {

        boolean result = false;

        if (null != object) {

            result = true;

        }

        return result;


    }


    @Override
    public void onSuccess(PutObjectRequest putObjectRequest, PutObjectResult putObjectResult) {


        Log.i("oss", "上传成功：" + "objectkey:" + putObjectRequest.getObjectKey() + " result返回：" + putObjectResult.getServerCallbackReturnBody());


        Log.i("oss", "上传成功：" + "objectkey:" + putObjectRequest.getObjectKey() + " result返回：" + putObjectResult.getServerCallbackReturnBody());


        Message message = uiHandler.obtainMessage();
        message.what = ConstOss.SUCCESS;
        Bundle bundle = new Bundle();
        bundle.putString(ConstOss.BUNDLE_REQUEST, putObjectRequest.getObjectKey());
        bundle.putString(ConstOss.BUNDLE_RESULT, putObjectResult.getServerCallbackReturnBody());
        message.setData(bundle);
        uiHandler.sendMessage(message);

    }


    @Override
    public void onFailure(PutObjectRequest putObjectRequest, ClientException e, ServiceException e1) {

        // 请求异常
        if (e != null) {
            // 本地异常如网络异常等
            e.printStackTrace();
        }
        if (e1 != null) {
            // 服务异常
            Log.e("ErrorCode", e1.getErrorCode());
            Log.e("RequestId", e1.getRequestId());
            Log.e("HostId", e1.getHostId());
            Log.e("RawMessage", e1.getRawMessage());

            Log.i("oss",
                    "上传失败：" + "ErrorCode：" + e1.getErrorCode() +
                            " RequestId：" + e1.getRequestId() +
                            " HostId：" + e1.getHostId() +
                            " RawMessage：" + e1.getRawMessage()
            );

        }


        Message message = uiHandler.obtainMessage();
        message.what = ConstOss.FAILURE;
        Bundle bundle = new Bundle();
        bundle.putString(ConstOss.BUNDLE_REQUEST, putObjectRequest.getObjectKey());


        if (null != e) {
            bundle.putString(ConstOss.CLIENT_EXCEPTION, e.getMessage());
        }
        if (null != e1) {

            bundle.putString(ConstOss.SERVICE_EXCEPTION, e1.getMessage());
        }


        message.setData(bundle);
        uiHandler.sendMessage(message);

    }






    public abstract void onOssSuccess(String requestObjectKey, String resultData);


    public abstract void onOssFailure(String requestObjectKey, String e, String e1);

}
