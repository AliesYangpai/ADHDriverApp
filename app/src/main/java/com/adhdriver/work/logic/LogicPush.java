package com.adhdriver.work.logic;

import com.adhdriver.work.constant.ConstParams;
import com.adhdriver.work.constant.ConstPush;
import com.adhdriver.work.entity.driver.orders.Assign;

/**
 * Created by Administrator on 2018/1/19.
 * 类描述
 * 版本
 */

public class LogicPush {


    /**
     * 订单类型的推送
     * @param pushType
     * @return
     */
    public boolean isScopeTypeOrder(String pushType) {

        return pushType.equals(ConstPush.ScopeType.ORDER);
    }

    /**
     * 收钱的推送
     * @param pushType
     * @return
     */
    public boolean isScopeTypeEarnings(String pushType) {

        return pushType.equals(ConstPush.ScopeType.EARNINGS);

    }

    /**
     * 订单已经指派了
     * @param assign
     * @return
     */
    public boolean isAssigned(Assign assign) {
        return null!= assign;
    }

    /**
     * 相同的driverVehicleId
     * @param pushDriverVehicleId
     * @param localDriverVehicleId
     * @return
     */
    public boolean isSameDriverVehicleId(String pushDriverVehicleId,String localDriverVehicleId) {

        return pushDriverVehicleId.equals(localDriverVehicleId);
    }

    public boolean isSameCityOrder(String businesstype) {
        return businesstype.equals(ConstParams.Orders.SAME_CITY);
    }

    public boolean isOverOfficeOrder(String businesstype) {
        return businesstype.equals(ConstParams.Orders.OVER_OFFICE);
    }
}
