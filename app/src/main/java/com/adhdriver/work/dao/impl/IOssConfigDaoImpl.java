package com.adhdriver.work.dao.impl;

import com.adhdriver.work.dao.IBaseDao;
import com.adhdriver.work.entity.OssConfig;
import com.adhdriver.work.entity.driver.vehicle.LogonVehicle;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 2017/12/11.
 * 类描述  OSS阿里云图片服务器模型
 * 版本
 */

public class IOssConfigDaoImpl implements IBaseDao<OssConfig> {




    @Override
    public boolean save(OssConfig ossConfig) {
        return ossConfig.save();
    }

    @Override
    public int deleteAll(Class<OssConfig> ossConfigClass) {
        return DataSupport.deleteAll(ossConfigClass);
    }

    @Override
    public int deleteAllByCondition(Class<OssConfig> ossConfigClass, String... args) {
        return DataSupport.deleteAll(ossConfigClass,args);
    }

    @Override
    public List<OssConfig> findAll(Class<OssConfig> ossConfigClass) {
        return DataSupport.findAll(ossConfigClass);
    }

    @Override
    public OssConfig findFirst(Class<OssConfig> ossConfigClass) {
        return DataSupport.findFirst(ossConfigClass);
    }
}
