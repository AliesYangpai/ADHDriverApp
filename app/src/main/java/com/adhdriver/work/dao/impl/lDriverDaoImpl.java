package com.adhdriver.work.dao.impl;

import com.adhdriver.work.dao.IBaseDao;
import com.adhdriver.work.entity.driver.Driver;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Alie on 2017/11/6.
 * 类描述   司机本地查询表
 * 版本
 */

public class lDriverDaoImpl implements IBaseDao<Driver> {



    @Override
    public boolean save(Driver driver) {
        return driver.save();
    }

    @Override
    public int deleteAll(Class<Driver> driverClass) {
        return DataSupport.deleteAll(driverClass);
    }

    @Override
    public int deleteAllByCondition(Class<Driver> driverClass, String... args) {
        return DataSupport.deleteAll(driverClass,args);
    }

    @Override
    public List<Driver> findAll(Class<Driver> driverClass) {
        return  DataSupport.findAll(driverClass);
    }

    @Override
    public Driver findFirst(Class<Driver> driverClass) {
        return DataSupport.findFirst(driverClass);
    }
}
