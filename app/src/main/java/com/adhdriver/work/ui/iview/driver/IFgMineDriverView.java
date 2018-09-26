package com.adhdriver.work.ui.iview.driver;

import com.adhdriver.work.entity.User;
import com.adhdriver.work.ui.iview.IBaseView;

/**
 * Created by Alie on 2017/11/14.
 * 类描述
 * 版本
 */

public interface IFgMineDriverView extends IBaseView{


    void onDataBackFail(int code, String errorMsg);

    void onDataBackSuccessForGetUserInfo(User user);

    void onDataFromUserDb(User user);


}
