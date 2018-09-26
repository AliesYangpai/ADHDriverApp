package com.adhdriver.work.ui.iview.driver;

import com.adhdriver.work.dao.IBaseDao;
import com.adhdriver.work.entity.UdpEntity;
import com.adhdriver.work.ui.iview.IBaseView;

/**
 * Created by Administrator on 2018/1/22.
 * 类描述 踢出Activity
 * 版本
 */

public interface IKickOutView extends IBaseView {



    void onDataBackFail(int code, String errorMsg);


    void doShowKickOutAlertDialog(UdpEntity udpEntity);


    void doDestroyAlertDialog();



    void onDataBackSuccessForClosePush();

    void onDataBackSuccessForGetVisitorToken();
}
