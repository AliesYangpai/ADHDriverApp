package com.adhdriver.work.dao.impl;

import com.adhdriver.work.dao.IBaseDao;
import com.adhdriver.work.entity.driver.vehicle.LogonVehicle;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Alie on 2017/11/6.
 * 类描述   司机当前登录的车辆
 * 版本
 */

public class ILogonVehicleDaoImpl implements IBaseDao<LogonVehicle>{


    @Override
    public boolean save(LogonVehicle logonVehicle) {
        return logonVehicle.save();
    }

    @Override
    public int deleteAll(Class<LogonVehicle> logonVehicleClass) {
        return DataSupport.deleteAll(logonVehicleClass);
    }

    @Override
    public int deleteAllByCondition(Class<LogonVehicle> logonVehicleClass, String... args) {
        return DataSupport.deleteAll(logonVehicleClass,args);
    }

    @Override
    public List<LogonVehicle> findAll(Class<LogonVehicle> logonVehicleClass) {
        return DataSupport.findAll(logonVehicleClass);
    }

    @Override
    public LogonVehicle findFirst(Class<LogonVehicle> logonVehicleClass) {
        return DataSupport.findFirst(logonVehicleClass);
    }
}
