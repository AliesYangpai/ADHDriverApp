package com.adhdriver.work.http.upload;

import android.content.Context;

import com.adhdriver.work.AiDaiHuoApplication;
import com.adhdriver.work.dao.IBaseDao;
import com.adhdriver.work.dao.impl.IOssConfigDaoImpl;
import com.adhdriver.work.entity.OssConfig;
import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;

import org.litepal.crud.DataSupport;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/7/6.
 * 类描述   OSSAdhClient实体类 用于初始化数据配置
 * 版本
 */

public class OSSAdhClient {


    private OSSClient ossClient;
    private OSSCredentialProvider credentialProvider;
    private ClientConfiguration conf;




    private IBaseDao<OssConfig> iOssConfigDao;


    public OSSAdhClient(Context context) {


        iOssConfigDao = new IOssConfigDaoImpl();
        OssConfig ossConfig = iOssConfigDao.findFirst(OssConfig.class);


        if (ossConfig != null) {

            String access_key_id = ossConfig.getAccess_key_id();
            String access_key_secret = ossConfig.getAccess_key_secret();
            String end_point = ossConfig.getEnd_point();

            credentialProvider = new OSSPlainTextAKSKCredentialProvider(access_key_id, access_key_secret);
            conf = new ClientConfiguration();
            conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
            conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
            conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
            conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
            ossClient = new OSSClient(context, end_point, credentialProvider, conf);



        }


    }

    public OSSClient getOssClient() {
        return ossClient;
    }


    /**
     * 获取OssConfig信息
     * @return
     */
    public OssConfig getOssConfig() {

        OssConfig ossConfig = iOssConfigDao.findFirst(OssConfig.class);

        return ossConfig;

    }




}
