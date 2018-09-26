package com.adhdriver.work.presenter.driver;

import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.dao.IBaseDao;
import com.adhdriver.work.dao.impl.ILogonVehicleDaoImpl;
import com.adhdriver.work.dao.impl.IUserDaoImpl;
import com.adhdriver.work.dao.impl.lDriverDaoImpl;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.User;
import com.adhdriver.work.entity.driver.Driver;
import com.adhdriver.work.entity.driver.vehicle.LogonVehicle;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IDriverAbout;
import com.adhdriver.work.method.impl.IDriverAboutImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IFgMineDriverView;
import com.adhdriver.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created by Alie on 2017/11/14.
 * 类描述
 * 版本
 */

public class PresenterDriverFgMine extends BasePresenter<IFgMineDriverView> {

    private IFgMineDriverView iFgMineDriverView;

    private IDriverAbout iDriverAbout;


    /**
     * 解析类
     */

    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;

    /**
     * 数据库查询相关
     */
    private IBaseDao<User> iUserDao;
    private IBaseDao<Driver> iDriverDao;
    private IBaseDao<LogonVehicle> iLogonVehicleDao;

    public PresenterDriverFgMine(IFgMineDriverView iFgMineDriverView) {
        this.iFgMineDriverView = iFgMineDriverView;
        this.iDriverAbout = new IDriverAboutImpl();
        this.iUserDao = new IUserDaoImpl();
        this.iDriverDao = new lDriverDaoImpl();
        this.iLogonVehicleDao = new ILogonVehicleDaoImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
    }

    public void doGetUserInfoInFresh(String url) {


        iDriverAbout.doGetUserInfoInFresh(url,new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {

                User user = parseSerilizable.getParseToObj(data, User.class);

                if(null != user) {


                    List<User> userList = iUserDao.findAll(User.class);

                    if (null != userList && userList.size()>0) {

                        iUserDao.deleteAll(User.class);
                        iUserDao.save(user);

                    } else {

                        iUserDao.save(user);

                    }
                    Driver driver = user.getDriver();

                    if (null != driver) {

                        List<Driver> driverList = iDriverDao.findAll(Driver.class);

                        if (null != driverList && driverList.size() > 0) {

                            iDriverDao.deleteAll(Driver.class);
                            iDriverDao.save(driver);

                        } else {
                            iDriverDao.save(driver);
                        }
                        LogonVehicle logonVehicle = driver.getLogon_vehicle();

                        if (null != logonVehicle) {

                            List<LogonVehicle> logonVehicleList = iLogonVehicleDao.findAll(LogonVehicle.class);

                            if (null != logonVehicleList && logonVehicleList.size() > 0) {

                                iLogonVehicleDao.deleteAll(LogonVehicle.class);
                                iLogonVehicleDao.save(logonVehicle);

                            }else {

                                iLogonVehicleDao.save(logonVehicle);
                            }
                            iFgMineDriverView.onDataBackSuccessForGetUserInfo(user);
                        }
                    }
                }else {

                    iFgMineDriverView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if(vertifyNotNull.isNotNullObj(errorEntity)) {

                    iFgMineDriverView.onDataBackFail(Integer.valueOf(errorEntity.getError_code()),errorEntity.getError_message());

                }else {

                    iFgMineDriverView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);


                }

            }

            @Override
            public void onFinish() {

            }
        });





    }






    /**
     * 获取user信息
     * @return
     */
    public void getUserInfoFromDb() {

        User user = iUserDao.findFirst(User.class);

        if(vertifyNotNull.isNotNullObj(user)) {

            iFgMineDriverView.onDataFromUserDb(user);

        }

    }






}
