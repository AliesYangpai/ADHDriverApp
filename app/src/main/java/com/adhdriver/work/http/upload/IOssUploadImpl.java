package com.adhdriver.work.http.upload;

import com.adhdriver.work.entity.OssConfig;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;

/**
 * Created by Administrator on 2017/12/15.
 * 类描述  上传图片的实现类
 * 版本
 */

public class IOssUploadImpl implements IOssUpload {



    @Override
    public void doOssUpLoad(OSSClient ossClient,
                            final OssConfig ossConfig,
                            PutObjectRequest putObjectRequest,
                            final OnOssUploadListener onOssUploadListener) {


        ossClient.asyncPutObject(putObjectRequest, new OSSAdhCompletedCallBack() {

            @Override
            public void onOssSuccess(String requestObjectKey, String resultData) {

                onOssUploadListener.onOssSuccess(requestObjectKey,resultData);

            }

            @Override
            public void onOssFailure(String requestObjectKey, String e, String e1) {


                onOssUploadListener.onOssFailure(requestObjectKey,e,e1);
            }


        });




    }
}
