package com.adhdriver.work.presenter.driver;

import com.adhdriver.work.logic.LogicRefuel;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IRefuelView;

/**
 * Created by Administrator on 2018/1/2.
 * 类描述 车主帮的presenter
 * 版本
 */

public class PresenterDriverRefuel extends BasePresenter<IRefuelView> {

    private IRefuelView iRefuelView;
    private LogicRefuel logicRefuel;

    public PresenterDriverRefuel(IRefuelView iRefuelView) {
        this.iRefuelView = iRefuelView;
        this.logicRefuel = new LogicRefuel();
    }


    /**
     * 初始化H5
     * @param url
     * @param platform
     * @param phone
     */
    public void doInitH5Refuel(String url, String platform, String phone) {

        iRefuelView.doInitH5Refuel(url, platform, phone);

    }

    public void doGotoRefuelNavigate(String startLat, String startLng, String endLat, String endLng) {


        if(logicRefuel.isNullStartLat(startLat)) {

            iRefuelView.doVertifyErrorForNullCurrentLat();
            return;
        }

        if(logicRefuel.isNullStartLnt(startLng)){
            iRefuelView.doVertifyErrorForNullCurrentLnt();
            return;
        }

        if(logicRefuel.isNullGasStationLat(endLat)){

            iRefuelView.doVertifyErrorForNullGasStationLat();
            return;
        }

        if(logicRefuel.isNullGasStationLng(endLng)) {
            iRefuelView.doVertifyErrorForNullGasStationLnt();
            return;
        }

        iRefuelView.doGotoRefuelNavigate(startLat,startLng,endLat,endLng);



    }
}
