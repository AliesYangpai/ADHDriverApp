package com.adhdriver.work.function;

import com.adhdriver.work.constant.ConstHz;
import com.adhdriver.work.constant.ConstTag;
import com.adhdriver.work.entity.driver.vehicle.VehicleCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/17.
 * 类描述
 * 版本
 */

public class FunctionVehicle {


    /**
     * 本地设置车型信息
     *
     * @return
     */
    public List<VehicleCategory> getLocalVehicleCatagoryList() {

        List<VehicleCategory> list = new ArrayList<>();

        VehicleCategory vehicleSlab = new VehicleCategory();
        vehicleSlab.setCategory_name(ConstTag.VechileCatogoryTag.SLAB);
        vehicleSlab.setCategory_display_name(ConstHz.SLAB);


        VehicleCategory vehicleGaoLan = new VehicleCategory();
        vehicleGaoLan.setCategory_name(ConstTag.VechileCatogoryTag.GAOLAN);
        vehicleGaoLan.setCategory_display_name(ConstHz.GAOLAN);

        VehicleCategory vehicleBox = new VehicleCategory();
        vehicleBox.setCategory_name(ConstTag.VechileCatogoryTag.BOX);
        vehicleBox.setCategory_display_name(ConstHz.BOX);

        list.add(vehicleSlab);
        list.add(vehicleGaoLan);
        list.add(vehicleBox);


        return list;


    }
}
