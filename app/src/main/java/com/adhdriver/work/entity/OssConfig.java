package com.adhdriver.work.entity;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/7/6.
 * 类描述  获取阿里云文件上传数据
 * 版本
 */

public class OssConfig extends DataSupport{

    private int id;//自增id

//            "post_url": "http://idaihuo-web.oss-cn-beijing.aliyuncs.com/",
//            "end_point": "http://sms.253.com/msg/balance",
//            "access_key_id": "LTAIqtrwpS6Ht9h1",
//            "access_key_secret": "vX5cRHmV1VS84T98pmuMQay1qt1Omf",
//            "bucket_name": "idaihuo-web",
//            "object_key": "idaihuo-pc-web/adhimg/"







    private String access_key_id;
    private String access_key_secret;
    private String end_point;
    private String bucket_name;
    private String object_key;
    private String post_url; //用于前缀


    public OssConfig() {
    }

    public String getAccess_key_id() {
        return access_key_id;
    }

    public void setAccess_key_id(String access_key_id) {
        this.access_key_id = access_key_id;
    }

    public String getAccess_key_secret() {
        return access_key_secret;
    }

    public void setAccess_key_secret(String access_key_secret) {
        this.access_key_secret = access_key_secret;
    }

    public String getEnd_point() {
        return end_point;
    }

    public void setEnd_point(String end_point) {
        this.end_point = end_point;
    }

    public String getBucket_name() {
        return bucket_name;
    }

    public void setBucket_name(String bucket_name) {
        this.bucket_name = bucket_name;
    }

    public String getObject_key() {
        return object_key;
    }

    public void setObject_key(String object_key) {
        this.object_key = object_key;
    }

    public String getPost_url() {
        return post_url;
    }

    public void setPost_url(String post_url) {
        this.post_url = post_url;
    }

    @Override
    public String toString() {
        return "OssConfig{" +
                "access_key_id='" + access_key_id + '\'' +
                ", access_key_secret='" + access_key_secret + '\'' +
                ", end_point='" + end_point + '\'' +
                ", bucket_name='" + bucket_name + '\'' +
                ", object_key='" + object_key + '\'' +
                ", post_url='" + post_url + '\'' +
                '}';
    }
}
