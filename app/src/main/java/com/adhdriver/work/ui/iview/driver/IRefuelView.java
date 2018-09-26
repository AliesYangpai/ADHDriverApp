package com.adhdriver.work.ui.iview.driver;

import com.adhdriver.work.ui.iview.IBaseView;

/**
 * Created by Administrator on 2018/1/2.
 * 类描述  一键加油的IView
 * 版本
 */

public interface IRefuelView extends IBaseView {

    void doInitH5Refuel(String url, String platform, String phone);


    void doGotoRefuelNavigate(String startLat, String startLng, String endLat, String endLng);


    void doVertifyErrorForNullCurrentLat();

    void doVertifyErrorForNullCurrentLnt();

    void doVertifyErrorForNullGasStationLat();

    void doVertifyErrorForNullGasStationLnt();
}
