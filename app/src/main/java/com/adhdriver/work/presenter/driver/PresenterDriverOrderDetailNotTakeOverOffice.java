package com.adhdriver.work.presenter.driver;

import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.driver.orders.AdditionService;
import com.adhdriver.work.entity.driver.orders.Goods;
import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.entity.driver.orders.PathPoint;
import com.adhdriver.work.function.FunctionGoods;
import com.adhdriver.work.function.FunctionPathPoint;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.logic.LogicOrderNotTake;
import com.adhdriver.work.method.IOrderNotTake;
import com.adhdriver.work.method.IOrderOperate;
import com.adhdriver.work.method.impl.IOrderNotTakeImpl;
import com.adhdriver.work.method.impl.IOrderOperateImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IOrderDetailNotTakeBiddingView;
import com.adhdriver.work.ui.iview.driver.IOrderDetailNotTakeOverOfficeView;
import com.adhdriver.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created by Administrator on 2017/12/27.
 * 类描述  未接单的 营业部presenter
 * 版本
 */

public class PresenterDriverOrderDetailNotTakeOverOffice extends BasePresenter<IOrderDetailNotTakeOverOfficeView> {


    private IOrderDetailNotTakeOverOfficeView iOrderDetailNotTakeOverOfficeView;
    private IOrderNotTake iOrderNotTake;
    private IOrderOperate iOrderOperate; //订单操作方法
    private LogicOrderNotTake logicOrderNotTake;

    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;
    private FunctionGoods functionGoods;

    private FunctionPathPoint functionPathPoint;

    public PresenterDriverOrderDetailNotTakeOverOffice(IOrderDetailNotTakeOverOfficeView iOrderDetailNotTakeOverOfficeView) {
        this.iOrderDetailNotTakeOverOfficeView = iOrderDetailNotTakeOverOfficeView;
        this.iOrderNotTake = new IOrderNotTakeImpl();
        this.iOrderOperate = new IOrderOperateImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
        this.logicOrderNotTake = new LogicOrderNotTake();
        this.functionGoods = new FunctionGoods();

        this.functionPathPoint = new FunctionPathPoint();
    }


    public void doSetOrderInfoToUi(Order order) {


        if (vertifyNotNull.isNotNullObj(order)) {

            order = logicOrderNotTake.getOverOfficeOrder(order);

            if (vertifyNotNull.isNotNullObj(order)) {


                List<AdditionService> additionServices = logicOrderNotTake.getAdditionServiceList(order);
                Goods goods = logicOrderNotTake.getGood(order);
                String weight = "";

                if (vertifyNotNull.isNotNullObj(goods)) {

                    weight = functionGoods.getTheWeightTonOrdefaultText(goods);

                }
                iOrderDetailNotTakeOverOfficeView.onDataBackSuccessForGetOrderInfoOverOffice(order);



                String[] path_points_arry = functionPathPoint.getPathPointsArrayForOverOffice(order.getFrom(),order.getTo());
                iOrderDetailNotTakeOverOfficeView.onDataBackSuccessForOverPathPoints(path_points_arry);

                iOrderDetailNotTakeOverOfficeView.onDataBackSuccessForOverOfficeGoodsWeight(weight);

                if (vertifyNotNull.isNotNullListSize(additionServices)) {
                    iOrderDetailNotTakeOverOfficeView.onDataBackSuccessForOverOfficeAdditionService(additionServices);
                    iOrderDetailNotTakeOverOfficeView.doAdjustViewForAdditionService();
                } else {
                    iOrderDetailNotTakeOverOfficeView.doHideViewWithOutAdditionService();
                }
            }
        } else {

            iOrderDetailNotTakeOverOfficeView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
        }
    }


    /**
     * 服务端获取订单详情
     *
     * @param url
     */
    public void doGetOrderDetail(String url) {


        iOrderNotTake.doGetOrderDetail(url, new OnDataBackListener() {
            @Override
            public void onStart() {
                iOrderDetailNotTakeOverOfficeView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {


                Order order = parseSerilizable.getParseToObj(data, Order.class);


                if (vertifyNotNull.isNotNullObj(order)) {

                    iOrderDetailNotTakeOverOfficeView.onDataBackSuccessForOrderDetial(order);

                } else {

                    iOrderDetailNotTakeOverOfficeView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);

                }


            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    iOrderDetailNotTakeOverOfficeView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iOrderDetailNotTakeOverOfficeView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }


            }

            @Override
            public void onFinish() {
                iOrderDetailNotTakeOverOfficeView.dismissLoadingDialog();
            }
        });


    }


    /**
     * 开始抢单
     *
     * @param url
     */
    public void doStartGrab(String url) {

        iOrderOperate.doStartGrab(url, new OnDataBackListener() {
            @Override
            public void onStart() {
                iOrderDetailNotTakeOverOfficeView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                iOrderDetailNotTakeOverOfficeView.onDataBackSuccessForGrab();

            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iOrderDetailNotTakeOverOfficeView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iOrderDetailNotTakeOverOfficeView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {

                iOrderDetailNotTakeOverOfficeView.dismissLoadingDialog();
            }
        });

    }


}
