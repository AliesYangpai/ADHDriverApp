package com.adhdriver.work.http.upload;

import com.adhdriver.work.entity.OssConfig;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;

/**
 * Created by Administrator on 2017/12/15.
 * 类描述    图片上传的相关接口
 * 版本
 */

public interface IOssUploadView {


    /**
     * 上传中....
     * @param request
     * @param currentSize
     * @param totalSize
     */
    void ossOnProgress(PutObjectRequest request, long currentSize, long totalSize);


    /**
     * 图片上传成功
     */
    void ossOnSuccess(OssConfig ossConfig, String requestObjectKey, String resultData);


    /**
     * 图片上传失败
     */
    void ossOnFailure();

}
