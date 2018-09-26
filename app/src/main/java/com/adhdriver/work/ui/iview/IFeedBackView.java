package com.adhdriver.work.ui.iview;

import com.adhdriver.work.entity.AppUpdate;
import com.adhdriver.work.ui.iview.IBaseView;

/**
 * Created by Administrator on 2017/11/23.
 * 类描述
 * 版本
 */

public interface IFeedBackView extends IBaseView{




    void onDataBackFail(int code, String errorMsg);


    void onDataBackSuccessToShowDialog();


    void doShowAlertWithOutMsg();

    void doGetUserPhoneFromDb(String phone);





}
