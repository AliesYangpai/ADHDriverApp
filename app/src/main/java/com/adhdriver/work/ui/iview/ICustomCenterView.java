package com.adhdriver.work.ui.iview;

/**
 * Created by Administrator on 2017/11/20.
 * 类描述
 * 版本
 */

public interface ICustomCenterView {


    void doSetCustomerCenterData();


    void doCallUs(String phone);


    void doOpenOfficialWeb(String url);


    void doOpenSinaClient(String url, String sinaId, String type);


    void doOpenSinaWeb(String url, String sinaId, String type);
}
