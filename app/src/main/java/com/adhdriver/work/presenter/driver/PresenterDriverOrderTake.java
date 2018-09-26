package com.adhdriver.work.presenter.driver;

import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.driver.orders.Fee;
import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.entity.driver.orders.PathPoint;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.logic.LogicAmap;
import com.adhdriver.work.logic.LogicOrderTake;
import com.adhdriver.work.method.IOrderOperate;
import com.adhdriver.work.method.IOrderTake;
import com.adhdriver.work.method.impl.IOrderOperateImpl;
import com.adhdriver.work.method.impl.IOrderTakeImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.iview.driver.IOrderTakeView;
import com.adhdriver.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created by Administrator on 2018/1/8.
 * 类描述
 * 版本
 */

public class PresenterDriverOrderTake extends BasePresenter<IOrderTakeView> {

    private IOrderTakeView iOrderTakeView;
    private IOrderTake iOrderTake;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;
    private LogicOrderTake logicOrderTake;
    private LogicAmap logicAmap;
    private IOrderOperate iOrderOperate;




    public PresenterDriverOrderTake(IOrderTakeView iOrderTakeView) {
        this.iOrderTakeView = iOrderTakeView;

        this.iOrderTake = new IOrderTakeImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
        this.logicOrderTake = new LogicOrderTake();
        this.iOrderOperate = new IOrderOperateImpl();
        this.logicAmap = new LogicAmap();
    }


    /**
     * 获取已接单的订单列表
     *
     * @param url
     * @param size
     * @param index
     */
    public void doGetOrderTake(String url, int size, int index) {


        iOrderTake.doGetOrderTake(url, size, index, new OnDataBackListener() {
            @Override
            public void onStart() {
                iOrderTakeView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                List<Order> list = parseSerilizable.getParseToList(data, Order.class);


                if (vertifyNotNull.isNotNullListSize(list)) {
                    iOrderTakeView.onDataBackSuccessForOrderTakeOrders(list);

                }


            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    iOrderTakeView.onDataBackFail(code, errorEntity.getError_message());
                } else {

                    iOrderTakeView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {

                iOrderTakeView.dismissLoadingDialog();
            }
        });

    }


    /**
     * 下拉差量更新已接单订单列表
     *
     * @param url
     * @param size
     * @param index
     */
    public void doGetOrderTakeInFresh(String url, int size, int index) {

        iOrderTake.doGetOrderTakeInFresh(url, size, index, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {


                List<Order> list = parseSerilizable.getParseToList(data, Order.class);
                iOrderTakeView.onDataBackSuccessForOrderTakeInFresh(list);
            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    iOrderTakeView.onDataBackFail(code, errorEntity.getError_message());
                } else {

                    iOrderTakeView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);

                }
            }

            @Override
            public void onFinish() {


                iOrderTakeView.dismissFreshLoading();
            }
        });


    }


    public void doGetOrderTakeInLoadMore(String url, int size, int index) {


        iOrderTake.doGetOrderTakeInLoadMore(url, size, index, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {


                List<Order> list = parseSerilizable.getParseToList(data, Order.class);
                iOrderTakeView.onDataBackSuccessForOrderTakeInLoadMore(list);
            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    iOrderTakeView.onDataBackFailInLoadMore(code, errorEntity.getError_message());
                } else {

                    iOrderTakeView.onDataBackFailInLoadMore(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);

                }

            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void doGetOrderInfo(String url) {


        iOrderTake.doGetOrderInfo(url, new OnDataBackListener() {
            @Override
            public void onStart() {
                iOrderTakeView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                Order order = parseSerilizable.getParseToObj(data, Order.class);
                if (vertifyNotNull.isNotNullObj(order)) {




                    String businessType = order.getBusiness_type();
                    String status = order.getStatus();
                    Fee fee = order.getFee();

                    /**
                     * 同城
                     */
                    if (logicOrderTake.isSameCity(businessType)) {


                        order = logicOrderTake.getSameCityOrder(order);




                        if (vertifyNotNull.isNotNullObj(order)) {

                            String payer =  order.getPayer();
                            List<PathPoint> pathPoints = order.getPath_points();

                            if (logicOrderTake.isComplete(status)) {
                                iOrderTakeView.onDataBackSuccessForSameCityComplete(order);
                                return;
                            }

                            if (logicOrderTake.isCanceled(status)) {
                                iOrderTakeView.onDataBackSuccessForSameCityCancel(order);
                                return;
                            }

                            if (logicOrderTake.isRefund(status)) {
                                iOrderTakeView.onDataBackSuccessForSameCityRefund(order);
                                return;
                            }


                            /**
                             * 发货方付款
                             */
                            if(logicOrderTake.isShipper(payer)) {
                                /**
                                 * 还未出发
                                 */
                                if (logicOrderTake.isNotDepartSameCity(order.getDeparture_time())) {


                                    iOrderTakeView.onDataBackSuccessForSameCityNotDepart(order);

                                } else {
                                    /**
                                     * 已经出发
                                     */
                                    /**
                                     * 有代收货款
                                     */
                                    if (logicOrderTake.isHasCod(fee)) {
                                        /**
                                         * 此处需要重新修改
                                         */

                                        if(logicOrderTake.isHasArrivedForSameCity(order.getArrival_time())) {

                                            if(logicOrderTake.isHasPaid(fee)){

                                                /**
                                                 * 弹出验证码界面
                                                 */
                                                iOrderTakeView.onDataBackSuccessForSameCityArriveAndHasPaid(order);

                                            }else {
                                                /**
                                                 * 已经到但是没有付款，弹出付款对话框
                                                 */
                                                iOrderTakeView.onDataBackSuccessForSameCityArriveAndNotPaid(order);
                                            }
                                        }else {
                                            /**
                                             * 还没有到达.......
                                             */
                                            iOrderTakeView.onDataBackSuccessForSameCityDepartAndNotArrive(order);
                                        }
                                    } else {
                                        /**
                                         * 判断是否已经到达
                                         */
                                        if(logicOrderTake.isHasArrivedForSameCity(order.getArrival_time())) {
                                            /**
                                             * 已经付过款了
                                             */
                                           if(logicOrderTake.isHasPaid(fee)) {
                                               /**
                                                * 弹出验证码界面
                                                */
                                               iOrderTakeView.onDataBackSuccessForSameCityArriveAndHasPaid(order);
                                           }else {
                                               /**
                                                * 已经到但是没有付款，弹出付款对话框
                                                */
                                               iOrderTakeView.onDataBackSuccessForSameCityArriveAndNotPaid(order);

                                           }
                                        }else {
                                            /**
                                             * 还没有到达.......
                                             */
                                            iOrderTakeView.onDataBackSuccessForSameCityDepartAndNotArrive(order);
                                        }
                                    }
                                }
                            }else if(logicOrderTake.isReciver(payer)){
                                /**
                                 * 还未出发收货方未出发
                                 * 无途经点
                                 */

                                if(!vertifyNotNull.isNotNullListSize(pathPoints)) {



                                    if(logicOrderTake.isNotDepartSameCity(order.getDeparture_time())) {

                                        iOrderTakeView.onDataBackSuccessForSameCityNotDepart(order);

                                    }else {

                                        /**
                                         * 有代收货款
                                         */
                                        if(logicOrderTake.isHasCod(fee)) {


                                            if(logicOrderTake.isHasArrivedForSameCity(order.getArrival_time())) {

                                                if(logicOrderTake.isHasPaid(fee)){

                                                    /**
                                                     * 弹出验证码界面
                                                     */
                                                    iOrderTakeView.onDataBackSuccessForSameCityArriveAndHasPaid(order);

                                                }else {
                                                    /**
                                                     * 已经到但是没有付款，弹出付款对话框
                                                     */
                                                    iOrderTakeView.onDataBackSuccessForSameCityArriveAndNotPaid(order);
                                                }
                                            }else {
                                                /**
                                                 * 还没有到达.......
                                                 */
                                                iOrderTakeView.onDataBackSuccessForSameCityDepartAndNotArrive(order);
                                            }
                                        }else {

                                            /**
                                             * 判断是否已经到达
                                             */
                                            if(logicOrderTake.isHasArrivedForSameCity(order.getArrival_time())) {

                                                /**
                                                 * 已经支付
                                                 */
                                                if(logicOrderTake.isHasPaid(fee)) {
                                                    /**
                                                     * 显示发送验证码控件
                                                     */
                                                    iOrderTakeView.onDataBackSuccessForSameCityArriveAndHasPaid(order);
                                                }else {
                                                    /**
                                                     * 已经到但是没有付款，弹出付款对话框
                                                     */
                                                    iOrderTakeView.onDataBackSuccessForSameCityArriveAndNotPaid(order);
                                                }

                                            } else {
                                                /**
                                                 * 还没有到达.......
                                                 */
                                                iOrderTakeView.onDataBackSuccessForSameCityDepartAndNotArrive(order);
                                            }
                                        }
                                    }
                                }else {


                                    if(logicOrderTake.isNotDepartSameCity(order.getDeparture_time())) {

                                        iOrderTakeView.onDataBackSuccessForSameCityNotDepart(order);

                                    }else {

                                        /**
                                         * 有代收货款
                                         */
                                        if(logicOrderTake.isHasCod(fee)) {


                                            if(logicOrderTake.isHasArrivedForSameCity(order.getArrival_time())) {

                                                if(logicOrderTake.isHasPaid(fee)){

                                                    /**
                                                     * 弹出验证码界面
                                                     */
                                                    iOrderTakeView.onDataBackSuccessForSameCityArriveAndHasPaid(order);

                                                }else {
                                                    /**
                                                     * 已经到但是没有付款，弹出付款对话框
                                                     */
                                                    iOrderTakeView.onDataBackSuccessForSameCityArriveAndNotPaid(order);
                                                }
                                            }else {
                                                /**
                                                 * 还没有到达.......
                                                 */
                                                iOrderTakeView.onDataBackSuccessForSameCityDepartAndNotArrive(order);
                                            }
                                        }else {

                                            /**
                                             * 判断是否已经到达
                                             */
                                            if(logicOrderTake.isHasArrivedForSameCityNoPathPoint(order.getArrival_time())) {

                                                /**
                                                 * 已经支付
                                                 */
                                                if(logicOrderTake.isHasPaid(fee)) {
                                                    /**
                                                     * 显示发送验证码控件
                                                     */
                                                    iOrderTakeView.onDataBackSuccessForSameCityArriveAndHasPaid(order);
                                                }else {
                                                    /**
                                                     * 已经到但是没有付款，弹出付款对话框
                                                     */
                                                    iOrderTakeView.onDataBackSuccessForSameCityArriveAndNotPaid(order);
                                                }

                                            } else {
                                                /**
                                                 * 还没有到达.......
                                                 */
                                                iOrderTakeView.onDataBackSuccessForSameCityDepartAndNotArrive(order);
                                            }
                                        }
                                    }

                                }
                            }
                        } else {
                            iOrderTakeView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                        }


                    }
                    /**
                     * 整车
                     */
                    if (logicOrderTake.isFullLoad(order.getBusiness_type())) {



                        if (vertifyNotNull.isNotNullObj(order)) {

                            String payer =  order.getPayer();

                            if (logicOrderTake.isComplete(status)) {
                                iOrderTakeView.onDataBackSuccessForFullLoadComplete(order);
                                return;
                            }

                            if (logicOrderTake.isCanceled(status)) {
                                iOrderTakeView.onDataBackSuccessForFullLoadCancel(order);
                                return;
                            }

                            if (logicOrderTake.isRefund(status)) {
                                iOrderTakeView.onDataBackSuccessForFullLoadRefund(order);
                                return;
                            }


                            /**
                             * 发货方付款
                             */
                            if(logicOrderTake.isShipper(payer)) {
                                /**
                                 * 还未出发
                                 */

                                if (logicOrderTake.isNotDepartForFullLoad(order.getDeparture_time())) {


                                    iOrderTakeView.onDataBackSuccessForFullLoadNotDepart(order);

                                } else {
                                    /**
                                     * 已经出发
                                     */
                                    /**
                                     * 有代收货款
                                     */
                                    if (logicOrderTake.isHasCod(fee)) {


                                        if(logicOrderTake.isHasArrivedForFullLoad(order.getArrival_time())) {

                                            if(logicOrderTake.isHasPaid(fee)){

                                                /**
                                                 * 弹出验证码界面
                                                 */
                                                iOrderTakeView.onDataBackSuccessForFullLoadArriveAndHasPaid(order);

                                            }else {
                                                /**
                                                 * 已经到但是没有付款，弹出付款对话框
                                                 */
                                                iOrderTakeView.onDataBackSuccessForFullLoadArriveAndNotPaid(order);

                                            }

                                        }else {

                                            /**
                                             * 还没有到达.......
                                             */
                                            iOrderTakeView.onDataBackSuccessForFullLoadDepartAndNotArrive(order);
                                        }
                                    } else {
                                        /**
                                         * 判断是否已经到达
                                         */
                                        if(logicOrderTake.isHasArrivedForFullLoad(order.getArrival_time())) {

                                            iOrderTakeView.onDataBackSuccessForFullLoadArriveAndHasPaid(order);
                                        }else {
                                            /**
                                             * 还没有到达.......
                                             */
                                            iOrderTakeView.onDataBackSuccessForFullLoadDepartAndNotArrive(order);
                                        }
                                    }
                                }
                            }else if(logicOrderTake.isReciver(payer)){

                                /**
                                 * 还未出发收货方未出发
                                 */
                                if (logicOrderTake.isNotDepartForFullLoad(order.getDeparture_time())) {

                                    iOrderTakeView.onDataBackSuccessForFullLoadNotDepart(order);

                                }else {

                                    /**
                                     * 已经出发
                                     */

                                    /**
                                     * 有代收货款
                                     */
                                    if(logicOrderTake.isHasCod(fee)) {


                                        if(logicOrderTake.isHasArrivedForFullLoad(order.getArrival_time())) {

                                            if(logicOrderTake.isHasPaid(fee)){

                                                /**
                                                 * 弹出验证码界面
                                                 */
                                                iOrderTakeView.onDataBackSuccessForFullLoadArriveAndHasPaid(order);

                                            }else {
                                                /**
                                                 * 已经到但是没有付款，弹出付款对话框
                                                 */
                                                iOrderTakeView.onDataBackSuccessForFullLoadArriveAndNotPaid(order);
                                            }
                                        }else {
                                            /**
                                             * 还没有到达.......
                                             */
                                            iOrderTakeView.onDataBackSuccessForFullLoadDepartAndNotArrive(order);
                                        }
                                    }else {

                                        /**
                                         * 判断是否已经到达
                                         */
                                        if(logicOrderTake.isHasArrivedForFullLoad(order.getArrival_time())) {

                                            /**
                                             * 已经支付
                                             */
                                            if(logicOrderTake.isHasPaid(fee)) {
                                                /**
                                                 * 显示发送验证码控件
                                                 */
                                                iOrderTakeView.onDataBackSuccessForFullLoadArriveAndHasPaid(order);
                                            }else {
                                                /**
                                                 * 已经到但是没有付款，弹出付款对话框
                                                 */
                                                iOrderTakeView.onDataBackSuccessForFullLoadArriveAndNotPaid(order);
                                            }

                                        } else {
                                            /**
                                             * 还没有到达.......
                                             */
                                            iOrderTakeView.onDataBackSuccessForFullLoadDepartAndNotArrive(order);
                                        }
                                    }
                                }
                            }
                        } else {
                            iOrderTakeView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                        }
                        return;
                    }


                    /**
                     * 营业部
                     */
                    if (logicOrderTake.isOverOffice(order.getBusiness_type())) {



                        order = logicOrderTake.getOverOfficeOrder(order);

                        if(vertifyNotNull.isNotNullObj(order)) {



                            if (logicOrderTake.isComplete(status)) {
                                iOrderTakeView.onDataBackSuccessForOverOfficeComplete(order);
                                return;
                            }


                            /**
                             * 判断营业部订单是否已经出发
                             */
                            if(logicOrderTake.isNotDepartForOverOffice(order.getDeparture_time())) {
                                iOrderTakeView.onDataBackSuccessForOverOfficeNotDepart(order);
                                return;
                            }

                            if(!logicOrderTake.isNotDepartForOverOffice(order.getDeparture_time())
                                    &&logicOrderTake.isNotArriveForOverOffice(order.getArrival_time())) {
                                iOrderTakeView.onDataBackSuccessForOverOfficeDepartAndNotArrive(order);
                                return;
                            }


                            if(!logicOrderTake.isNotArriveForOverOffice(order.getArrival_time())) {
                                iOrderTakeView.onDataBackSuccessForOverOfficeArrive(order);

                            }

                        }
                    }

                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    iOrderTakeView.onDataBackFailInLoadMore(code, errorEntity.getError_message());
                } else {

                    iOrderTakeView.onDataBackFailInLoadMore(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);

                }

            }

            @Override
            public void onFinish() {

                iOrderTakeView.dismissLoadingDialog();

            }
        });


    }


    /**
     * 开始出发
     */
    public void doDepartSameCity(String url,Order order,float meter,double longitude,double latitude) {

        if(logicAmap.isDistanceBiggerThan1000(meter)) {

            iOrderTakeView.onDataBackSuccessForSameCityNotInAutoDepartArea(order);
        }else {


            iOrderOperate.doDepartSameCity(url, longitude, latitude, new OnDataBackListener() {
                @Override
                public void onStart() {

                    iOrderTakeView.showLoadingDialog();
                }

                @Override
                public void onSuccess(String data) {



                    Order order = parseSerilizable.getParseToObj(data,Order.class);
                    if(vertifyNotNull.isNotNullObj(order)) {
                        iOrderTakeView.onDataBackSuccessForSameCityAutoDepart(order);
                    }


                }

                @Override
                public void onFail(int code, String data) {

                    ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                    if (vertifyNotNull.isNotNullObj(errorEntity)) {

                        iOrderTakeView.onDataBackFailInLoadMore(code, errorEntity.getError_message());
                    } else {

                        iOrderTakeView.onDataBackFailInLoadMore(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);

                    }


                }

                @Override
                public void onFinish() {

                    iOrderTakeView.dismissLoadingDialog();

                }
            });

        }
    }


    /**
     * 整车
     * 开始出发
     */
    public void doDepartFullLoad(String url,Order order,float meter,double longitude,double latitude) {

        if(logicAmap.isDistanceBiggerThan1000(meter)) {

            iOrderTakeView.onDataBackSuccessForFullLoadNotInAutoDepartArea(order);
        }else {


            iOrderOperate.doDepartFullLoad(url, longitude, latitude, new OnDataBackListener() {
                @Override
                public void onStart() {

                    iOrderTakeView.showLoadingDialog();
                }

                @Override
                public void onSuccess(String data) {



                    Order order = parseSerilizable.getParseToObj(data,Order.class);
                    if(vertifyNotNull.isNotNullObj(order)) {
                        iOrderTakeView.onDataBackSuccessForFullLoadAutoDepart(order);
                    }


                }

                @Override
                public void onFail(int code, String data) {

                    ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                    if (vertifyNotNull.isNotNullObj(errorEntity)) {

                        iOrderTakeView.onDataBackFailInLoadMore(code, errorEntity.getError_message());
                    } else {

                        iOrderTakeView.onDataBackFailInLoadMore(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);

                    }


                }

                @Override
                public void onFinish() {

                    iOrderTakeView.dismissLoadingDialog();

                }
            });

        }
    }




    /**
     * 营业部出发
     * 开始出发
     */
    public void doDepartOverOffice(String url,Order order,float meter) {

        if(logicAmap.isDistanceBiggerThan1000(meter)) {

            iOrderTakeView.onDataBackSuccessForOverOfficeNotInAutoDepartArea(order);
        }else {


            iOrderOperate.doDepartOverOffice(url, new OnDataBackListener() {
                @Override
                public void onStart() {

                    iOrderTakeView.showLoadingDialog();
                }

                @Override
                public void onSuccess(String data) {



                    Order order = parseSerilizable.getParseToObj(data,Order.class);
                    if(vertifyNotNull.isNotNullObj(order)) {
                        iOrderTakeView.onDataBackSuccessForOverOfficeAutoDepart(order);
                    }


                }

                @Override
                public void onFail(int code, String data) {

                    ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                    if (vertifyNotNull.isNotNullObj(errorEntity)) {

                        iOrderTakeView.onDataBackFailInLoadMore(code, errorEntity.getError_message());
                    } else {
                        iOrderTakeView.onDataBackFailInLoadMore(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                    }
                }

                @Override
                public void onFinish() {

                    iOrderTakeView.dismissLoadingDialog();
                }
            });

        }
    }

}
