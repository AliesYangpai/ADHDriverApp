package com.adhdriver.work.ui.iview.driver;

import com.adhdriver.work.entity.ActInfo;
import com.adhdriver.work.entity.AppUpdate;
import com.adhdriver.work.ui.iview.IBaseView;

import java.util.List;

/**
 * Created by Administrator on 2017/11/23.
 * 类描述
 * 版本
 */

public interface IAppVersionView extends IBaseView{




    void onDataBackFail(int code, String errorMsg);


    void onDataBackSuccessForGetAppInfoIsNew();


    void  onDataBackSuccessForGetAppInfoNotNew(AppUpdate appUpdateBack);



    void doSetLocalInfoToUi();

}
