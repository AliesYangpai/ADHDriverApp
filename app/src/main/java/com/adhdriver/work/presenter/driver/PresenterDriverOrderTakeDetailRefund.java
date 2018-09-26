package com.adhdriver.work.presenter.driver;

import com.adhdriver.work.entity.driver.orders.AdditionService;
import com.adhdriver.work.entity.driver.orders.Fee;
import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.entity.driver.orders.PathPoint;
import com.adhdriver.work.function.FunctionFee;
import com.adhdriver.work.function.FunctionPathPoint;
import com.adhdriver.work.logic.LogicOrderTake;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IOrderTakeDetailRefundView;
import com.adhdriver.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created by Administrator on 2018/1/24.
 * 类描述
 * 版本
 */

public class PresenterDriverOrderTakeDetailRefund extends BasePresenter<IOrderTakeDetailRefundView> {

    private IOrderTakeDetailRefundView iOrderTakeDetailRefundView;
    private LogicOrderTake logicOrderTake;
    private VertifyNotNull vertifyNotNull;

    private FunctionFee functionFee;
    private FunctionPathPoint functionPathPoint;

    public PresenterDriverOrderTakeDetailRefund(IOrderTakeDetailRefundView iOrderTakeDetailRefundView) {
        this.iOrderTakeDetailRefundView = iOrderTakeDetailRefundView;
        this.logicOrderTake = new LogicOrderTake();
        this.vertifyNotNull = new VertifyNotNull();
        this.functionFee = new FunctionFee();
        this.functionPathPoint = new FunctionPathPoint();
    }


    public void doSetDataToUi(Order order) {


        if (vertifyNotNull.isNotNullObj(order)) {


            String businessType = order.getBusiness_type();
            if (logicOrderTake.isSameCity(businessType)) {

                Order theOrder = logicOrderTake.getSameCityOrder(order);


                if (vertifyNotNull.isNotNullObj(theOrder)) {

                    iOrderTakeDetailRefundView.onDataBackSuccessForSameCityOrderInfo(order);


                    Fee fee = theOrder.getFee();

                    if (vertifyNotNull.isNotNullObj(fee)) {
                        float driverDivide = functionFee.getSameCityDriverDivide(fee, order.getDriver_proporition());
                        iOrderTakeDetailRefundView.onDataBackSuccessForSameCityDriveDivide(String.valueOf(driverDivide));
                    }

                    List<PathPoint> pathPoints = theOrder.getPath_points();
                    String[] arry = null;
                    if(vertifyNotNull.isNotNullListSize(pathPoints)) {
                        arry = functionPathPoint.getHasPathPointAllWay(theOrder.getFrom(),theOrder.getTo(),pathPoints);
                    }else {
                        arry = functionPathPoint.getNotPathPointWay(theOrder.getFrom(),theOrder.getTo());
                    }

                    iOrderTakeDetailRefundView.doSetPathPointToUi(arry);

                    List<AdditionService> additionServices = theOrder.getAddition_services();
                    if (vertifyNotNull.isNotNullListSize(additionServices)) {
                        iOrderTakeDetailRefundView.onDataBackSuccessForSameCityAdditionService(additionServices);
                        iOrderTakeDetailRefundView.doAdjustViewForAdditionService();
                    } else {
                        iOrderTakeDetailRefundView.doHideViewWithOutAdditionService();
                    }
                }
            }


            if (logicOrderTake.isFullLoad(businessType)) {

                Order theOrder = logicOrderTake.getFullLoadOrder(order);
                if (vertifyNotNull.isNotNullObj(theOrder)) {


                    iOrderTakeDetailRefundView.onDataBackSuccessForFullLoadGetOrderInfo(order);

                    Fee fee = theOrder.getFee();

                    float price = functionFee.getFullLoadDriverDivide(fee, order.getDriver_proporition());

                    iOrderTakeDetailRefundView.onDataBackSuccessForFullLoadDriveDivide(String.valueOf(price));


                    String[] arry = functionPathPoint.getNotPathPointWay(order.getFrom(),order.getTo());
                    iOrderTakeDetailRefundView.doSetPathPointToUi(arry);

                    List<AdditionService> additionServices = theOrder.getAddition_services();
                    if (vertifyNotNull.isNotNullListSize(additionServices)) {
                        iOrderTakeDetailRefundView.onDataBackSuccessForFullLoadAdditionService(additionServices);
                        iOrderTakeDetailRefundView.doAdjustViewForAdditionService();
                    } else {
                        iOrderTakeDetailRefundView.doHideViewWithOutAdditionService();
                    }
                }

            }


            if (logicOrderTake.isOverOffice(businessType)) {

                Order theOrder = logicOrderTake.getOverOfficeOrder(order);
                if (vertifyNotNull.isNotNullObj(theOrder)) {


                    iOrderTakeDetailRefundView.onDataBackSuccessForOverOfficeGetOrderInfo(order);

                    String divide = functionFee.getOverOfficeDriverDivide(theOrder);

                    iOrderTakeDetailRefundView.onDataBackSuccessForOverOfficeDriveDivide(divide);


                    String[] arry = functionPathPoint.getPathPointsArrayForOverOffice(order.getFrom(),order.getTo());
                    iOrderTakeDetailRefundView.doSetPathPointToUi(arry);


                    List<AdditionService> additionServices = theOrder.getAddition_services();
                    if (vertifyNotNull.isNotNullListSize(additionServices)) {
                        iOrderTakeDetailRefundView.onDataBackSuccessForOverOfficeAdditionService(additionServices);
                        iOrderTakeDetailRefundView.doAdjustViewForAdditionService();
                    } else {
                        iOrderTakeDetailRefundView.doHideViewWithOutAdditionService();
                    }
                }
            }


        }


    }
}
