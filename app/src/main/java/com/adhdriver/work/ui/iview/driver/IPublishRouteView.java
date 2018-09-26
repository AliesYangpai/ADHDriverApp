package com.adhdriver.work.ui.iview.driver;

import com.adhdriver.work.ui.iview.IBaseView;

import java.util.ArrayList;
import java.util.List;

import cn.addapp.pickers.entity.City;
import cn.addapp.pickers.entity.County;
import cn.addapp.pickers.entity.Province;

/**
 * Created by Administrator on 2017/12/1.
 * 类描述    发布路线的view
 * 版本
 */

public interface IPublishRouteView extends IBaseView{



    void onDataBackFail(int code, String errorMsg);



    void doVertifyErrorNoDepartTime();

    void doVertifyErrorNoDepartPlace();

    void doVertifyErrorNoDestination();

    /**
     * 路线发布成功
     */
    void onDataBackSuccessForPublishRoute();


    /**
     * 获取ASSETES资源文件中的数据
     * @return
     */
    ArrayList<Province> doGetAddressList();


    /**
     * 初始化Addressdialog
     * @param list
     */
    void doInitAddressDialog(ArrayList<Province> list);


    /**
     * 初始化时间dialog
     */
    void doInitTimeDialog();

    void doShowStartPickDialog();

    void doShowEndPickDialog();

    void doStartAddressPicked(String provinceName, String cityName, String countyName);

    void doEndAddressPicked(String provinceName, String cityName, String countyName);

    void doStartPlaceGeocodeSearchedSuccess(String latitude, String longitude, String adcode);

    void doEndPlaceGeocodeSearchedSuccess(String latitude, String longitude, String adcode);

    void onDataBackFailGeocodeNoPlace();

    void onDataBackFailGeocodeCodeError(int rCode);
}

