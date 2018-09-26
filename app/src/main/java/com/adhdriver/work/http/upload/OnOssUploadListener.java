package com.adhdriver.work.http.upload;

import com.adhdriver.work.entity.OssConfig;


/**
 * Created by Administrator on 2017/12/15.
 * 类描述  oSS图片上传的listener
 * 版本
 */

public interface OnOssUploadListener {


    void onOssSuccess(String requestObjectKey, String resultData);


    void onOssFailure(String requestObjectKey, String e, String e1);


}
