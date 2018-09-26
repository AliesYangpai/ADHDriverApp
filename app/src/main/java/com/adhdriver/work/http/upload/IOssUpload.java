package com.adhdriver.work.http.upload;

import com.adhdriver.work.entity.OssConfig;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;

/**
 * Created by Administrator on 2017/12/15.
 * 类描述  图片上传的方法
 * 版本
 */

public interface IOssUpload {

    void doOssUpLoad(OSSClient ossClient,
                     OssConfig ossConfig,
                     PutObjectRequest putObjectRequest,
                     OnOssUploadListener onOssUploadListener);


}
