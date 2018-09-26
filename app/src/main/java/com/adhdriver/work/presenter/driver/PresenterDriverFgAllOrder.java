package com.adhdriver.work.presenter.driver;

import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.logic.LogicOrderNotTake;
import com.adhdriver.work.method.IOrderNotTake;
import com.adhdriver.work.method.IOrderOperate;
import com.adhdriver.work.method.impl.IOrderNotTakeImpl;
import com.adhdriver.work.method.impl.IOrderOperateImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IFgOrderNotTakeAllView;
import com.adhdriver.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created by Administrator on 2017/12/27.
 * 类描述
 * 版本
 */

public class PresenterDriverFgAllOrder extends BasePresenter<IFgOrderNotTakeAllView> {


    private IFgOrderNotTakeAllView iFgOrderNotTakeAllView;
    private IOrderNotTake iOrderNotTake;
    private IOrderOperate iOrderOperate; //订单操作的方法



    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;
    private LogicOrderNotTake logicOrderNotTake;

    public PresenterDriverFgAllOrder(IFgOrderNotTakeAllView iFgOrderNotTakeAllView) {
        this.iFgOrderNotTakeAllView = iFgOrderNotTakeAllView;
        this.iOrderNotTake = new IOrderNotTakeImpl();
        this.iOrderOperate = new IOrderOperateImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
        this.logicOrderNotTake = new LogicOrderNotTake();

    }




    /**
     * 服务端获取跨城列表
     * @param url
     * @param
     * @param size
     * @param index
     */
    public void doGetAllOrders(String url,int size,int index) {


        this.iOrderNotTake.doGetAllOrders(url , size, index, new OnDataBackListener() {
            @Override
            public void onStart() {
                iFgOrderNotTakeAllView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {


                List<Order> list = parseSerilizable.getParseToList(data,Order.class);



                if(vertifyNotNull.isNotNullListSize(list)) {
                    iFgOrderNotTakeAllView.onDataBackSuccessForAllOrders(list);
                }


            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if(vertifyNotNull.isNotNullObj(errorEntity)) {

                    iFgOrderNotTakeAllView.onDataBackFail(code,errorEntity.getError_message());
                }else  {

                    iFgOrderNotTakeAllView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);

                }


            }

            @Override
            public void onFinish() {
                iFgOrderNotTakeAllView.dismissLoadingDialog();
            }
        });




    }


    /**
     * 下拉差量更新
     * @param url
     * @param
     * @param size
     * @param index
     */
    public void doGetAllOrdersInFresh(String url,int size,int index) {

        iOrderNotTake.doGetAllOrdersInFresh(url, size, index,  new OnDataBackListener() {
            @Override
            public void onStart() {



            }

            @Override
            public void onSuccess(String data) {


                List<Order> list = parseSerilizable.getParseToList(data,Order.class);
                iFgOrderNotTakeAllView.onDataBackSuccessForAllOrdersInFresh(list);

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if(vertifyNotNull.isNotNullObj(errorEntity)) {

                    iFgOrderNotTakeAllView.onDataBackFail(code,errorEntity.getError_message());
                }else  {

                    iFgOrderNotTakeAllView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);

                }

            }

            @Override
            public void onFinish() {

                iFgOrderNotTakeAllView.dismissFreshLoading();
            }
        });

    };


    /**
     * 上拉加载更多
     * @param url
     * @param
     * @param size
     * @param index
     */
    public void doGetAllOrdersInLoadMore(String url,int size,int index) {

        iOrderNotTake.doGetAllOrdersInLoadMore(url, size, index, new OnDataBackListener() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(String data) {


                List<Order> list = parseSerilizable.getParseToList(data,Order.class);
                iFgOrderNotTakeAllView.onDataBackSuccessForAllOrdersInLoadMore(list);

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if(vertifyNotNull.isNotNullObj(errorEntity)) {

                    iFgOrderNotTakeAllView.onDataBackFailInLoadMore(code,errorEntity.getError_message());
                }else  {

                    iFgOrderNotTakeAllView.onDataBackFailInLoadMore(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);

                }


            }

            @Override
            public void onFinish() {


            }
        });

    }



    /**
     * 开始竞价
     * @param url
     * @param price
     */
    public void doStartBidding(String url,String price) {

        iOrderOperate.doStartBidding(url, price, new OnDataBackListener() {
            @Override
            public void onStart() {
                iFgOrderNotTakeAllView.showLoadingDialog();;
            }

            @Override
            public void onSuccess(String data) {

                iFgOrderNotTakeAllView.onDataBackSuccessForBiddingSuccess();

            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if(vertifyNotNull.isNotNullObj(errorEntity)) {
                    iFgOrderNotTakeAllView.onDataBackFail(code,errorEntity.getError_message());
                }else {
                    iFgOrderNotTakeAllView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {

                iFgOrderNotTakeAllView.dismissLoadingDialog();
            }
        });

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
                iFgOrderNotTakeAllView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {


                Order order = parseSerilizable.getParseToObj(data, Order.class);


                if (vertifyNotNull.isNotNullObj(order)) {


                    String businessType  = order.getBusiness_type();

                    if(logicOrderNotTake.isOverOffice(businessType)) {


                        order = logicOrderNotTake.getOverOfficeOrder(order);
                    }else if(logicOrderNotTake.isFullLoad(businessType)) {

                        order = logicOrderNotTake.getFullLoadOrder(order);
                    }



                    if (vertifyNotNull.isNotNullObj(order)) {


                        iFgOrderNotTakeAllView.onDataBackSuccessForOrderDetial(order);

                    }
                } else {

                    iFgOrderNotTakeAllView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }


            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    iFgOrderNotTakeAllView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iFgOrderNotTakeAllView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }


            }

            @Override
            public void onFinish() {
                iFgOrderNotTakeAllView.dismissLoadingDialog();
            }
        });


    }


}
