package com.adhdriver.work.presenter.driver;

import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.driver.orders.AdditionService;
import com.adhdriver.work.entity.driver.orders.Goods;
import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.function.FunctionGoods;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.logic.LogicOrderNotTake;
import com.adhdriver.work.method.IOrderNotTake;
import com.adhdriver.work.method.IOrderOperate;
import com.adhdriver.work.method.impl.IOrderNotTakeImpl;
import com.adhdriver.work.method.impl.IOrderOperateImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IOrderDetailNotTakeBiddingView;
import com.adhdriver.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created by Administrator on 2017/12/27.
 * 类描述
 * 版本
 */

public class PresenterDriverOrderDetailNotTakeBidding extends BasePresenter<IOrderDetailNotTakeBiddingView> {


    private IOrderDetailNotTakeBiddingView iOrderDetailNotTakeBiddingView;
    private IOrderNotTake iOrderNotTake;
    private IOrderOperate iOrderOperate; //订单操作方法


    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;

    private LogicOrderNotTake logicOrderNotTake;
    private FunctionGoods functionGoods;

    public PresenterDriverOrderDetailNotTakeBidding(IOrderDetailNotTakeBiddingView iOrderDetailNotTakeBiddingView) {
        this.iOrderDetailNotTakeBiddingView = iOrderDetailNotTakeBiddingView;
        this.iOrderNotTake = new IOrderNotTakeImpl();
        this.iOrderOperate = new IOrderOperateImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
        this.logicOrderNotTake = new LogicOrderNotTake();
        this.functionGoods = new FunctionGoods();

    }


    public void doSetOrderInfoToUi(Order order) {




        if (vertifyNotNull.isNotNullObj(order)) {

            order = logicOrderNotTake.getFullLoadOrder(order);

            if (vertifyNotNull.isNotNullObj(order)) {


                List<AdditionService> additionServices = logicOrderNotTake.getAdditionServiceList(order);
                Goods goods = logicOrderNotTake.getGood(order);
                String weight = "";

                if (vertifyNotNull.isNotNullObj(goods)) {

                    weight = functionGoods.getTheWeightTonOrdefaultText(goods);

                }
                iOrderDetailNotTakeBiddingView.onDataBackSuccessForGetOrderInfoFullLoad(order);
                iOrderDetailNotTakeBiddingView.onDataBackSuccessForFullLoadGoodsWeight(weight);

                if (vertifyNotNull.isNotNullListSize(additionServices)) {
                    iOrderDetailNotTakeBiddingView.onDataBackSuccessForFullLoadAdditionService(additionServices);
                    iOrderDetailNotTakeBiddingView.doAdjustViewForAdditionService();
                } else {
                    iOrderDetailNotTakeBiddingView.doHideViewWithOutAdditionService();
                }
            }
        } else {

            iOrderDetailNotTakeBiddingView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
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
                iOrderDetailNotTakeBiddingView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {


                Order order = parseSerilizable.getParseToObj(data, Order.class);


                if (vertifyNotNull.isNotNullObj(order)) {

                    iOrderDetailNotTakeBiddingView.onDataBackSuccessForOrderDetial(order);

                } else {

                    iOrderDetailNotTakeBiddingView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);

                }


            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    iOrderDetailNotTakeBiddingView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iOrderDetailNotTakeBiddingView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }


            }

            @Override
            public void onFinish() {
                iOrderDetailNotTakeBiddingView.dismissLoadingDialog();
            }
        });


    }


    /**
     * 开始竞价
     *
     * @param url
     * @param price
     */
    public void doStartBidding(String url, String price) {

        iOrderOperate.doStartBidding(url, price, new OnDataBackListener() {
            @Override
            public void onStart() {
                iOrderDetailNotTakeBiddingView.showLoadingDialog();
                ;
            }

            @Override
            public void onSuccess(String data) {

                iOrderDetailNotTakeBiddingView.onDataBackSuccessForBiddingSuccess();

            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iOrderDetailNotTakeBiddingView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iOrderDetailNotTakeBiddingView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {

                iOrderDetailNotTakeBiddingView.dismissLoadingDialog();
            }
        });

    }


}
