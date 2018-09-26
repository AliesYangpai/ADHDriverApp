package com.adhdriver.work.presenter.driver;

import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.constant.ConstParams;
import com.adhdriver.work.dao.IBaseDao;
import com.adhdriver.work.dao.impl.IOssConfigDaoImpl;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.OssConfig;
import com.adhdriver.work.entity.driver.orders.Coordinate;
import com.adhdriver.work.entity.driver.orders.Fee;
import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.entity.driver.orders.PathPoint;
import com.adhdriver.work.entity.driver.pay.PayChannelInfo;
import com.adhdriver.work.entity.driver.temp.PublishRoute;
import com.adhdriver.work.function.FunctionPathPoint;
import com.adhdriver.work.function.FunctionPayChannel;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.location.amap.AmapNavigationViewOpition;
import com.adhdriver.work.logic.LogicOrderTake;
import com.adhdriver.work.method.IMoney;
import com.adhdriver.work.method.IOrderOperate;
import com.adhdriver.work.method.IOrderTake;
import com.adhdriver.work.method.IVertifyCode;
import com.adhdriver.work.method.impl.IMoneyImpl;
import com.adhdriver.work.method.impl.IOrderOperateImpl;
import com.adhdriver.work.method.impl.IOrderTakeImpl;
import com.adhdriver.work.method.impl.IVertifyCodeImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IOrderTakeDetailView;
import com.adhdriver.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created by Administrator on 2018/1/3.
 * 类描述
 * 版本
 */

public class PresenterDriverOrderTakeDetail extends BasePresenter<IOrderTakeDetailView> {

    private IOrderTakeDetailView iOrderTakeDetailView;
    private IOrderTake iOrderTake;
    private IOrderOperate iOrderOperate;

    private IVertifyCode iVertifyCode;
    private AmapNavigationViewOpition amapNavigationViewOpition;

    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;
    private LogicOrderTake logicOrderTake;

    private IMoney iMoney;

    private IBaseDao<OssConfig> iOssDao;

    private FunctionPayChannel functionPayChannel;
    private FunctionPathPoint functionPathPoint;

    public PresenterDriverOrderTakeDetail(IOrderTakeDetailView iOrderTakeDetailView) {
        this.iOrderTakeDetailView = iOrderTakeDetailView;
        this.iOrderTake = new IOrderTakeImpl();
        this.iOrderOperate = new IOrderOperateImpl();
        this.amapNavigationViewOpition = new AmapNavigationViewOpition();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
        this.iVertifyCode = new IVertifyCodeImpl();
        this.logicOrderTake = new LogicOrderTake();
        this.iMoney = new IMoneyImpl();
        this.iOssDao = new IOssConfigDaoImpl();
        this.functionPayChannel = new FunctionPayChannel();
        this.functionPathPoint = new FunctionPathPoint();
    }


    public void doSetLocationDataToNavigate() {


        iOrderTakeDetailView.doSetLocationDataToNavigate(amapNavigationViewOpition.getNaviViewOptions());
    }


    /**
     * titile Pop的相关方法============================
     */
    public void doShowTitlePopWindow() {

        iOrderTakeDetailView.doShowTitlePopWindow();
    }


    public void doDealShowOrHideTitlePopWindow() {

        iOrderTakeDetailView.doDealShowOrHideTitlePopWindow();
    }


    public void doDestroyTitlePopWindow() {
        iOrderTakeDetailView.doDestroyTitlePopWindow();

    }


    /**
     * titile Pop的相关方法============================
     */


    /**
     * overOffice Pop的相关方法============================
     */

    public void doShowOverOfficePopBottom() {

        iOrderTakeDetailView.doShowOverOfficePopBottom();
    }

    public void doDealShowOrHideOverOfficePopWindow() {

        iOrderTakeDetailView.doDealShowOrHideOverOfficePopWindow();
    }

    public void doDestroyOverOfficePopWindow() {

        iOrderTakeDetailView.doDestroyOverOfficePopWindow();
    }


    /**
     * overOffice Pop的相关方法============================
     */


    /**
     * fullLoad Pop的相关方法============================
     */


    public void doShowFullLoadPopBottom() {

        iOrderTakeDetailView.doShowFullLoadPopBottom();
    }

    public void doDealShowOrHideFullLoadPopWindow() {

        iOrderTakeDetailView.doDealShowOrHideFullLoadPopWindow();
    }

    public void doDestroyFullLoadPopWindow() {

        iOrderTakeDetailView.doDestroyFullLoadPopWindow();
    }



    /**
     * fullLoad Pop的相关方法============================
     */

    public void doShowSameCityPopBottom() {

        iOrderTakeDetailView.doShowSameCityPopBottom();
    }



    public void doDealShowOrHideSameCityPopWindow() {

        iOrderTakeDetailView.doDealShowOrHideSameCityPopWindow();
    }


    public void doDestroySameCityPopWindow(){

        iOrderTakeDetailView.doDestroySameCityPopWindow();
    }


    public void doOverOfficeArrive(String url) {

        iOrderOperate.doArriveOverOffice(url, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {

                iOrderTakeDetailView.OnDataBackSuccessForOverOfficeArrived();

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iOrderTakeDetailView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iOrderTakeDetailView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }


                iOrderTakeDetailView.onDataBackFailForOverOfficeOrderOperate();
            }

            @Override
            public void onFinish() {

            }
        });
    }


    public void doOverOfficeDepart(String url) {


        iOrderOperate.doDepartOverOffice(url, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {


                iOrderTakeDetailView.onDataBackSuccessForOverOfficeDepart();
            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iOrderTakeDetailView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iOrderTakeDetailView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }


                iOrderTakeDetailView.onDataBackFailForOverOfficeOrderOperate();
            }

            @Override
            public void onFinish() {

            }
        });
    }


    public void doFullLoadDepart(String url, double longitude, double latitude) {


        iOrderOperate.doDepartFullLoad(url, longitude, latitude, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {


                iOrderTakeDetailView.onDataBackSuccessForFullLoadDepart();
            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iOrderTakeDetailView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iOrderTakeDetailView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }


                iOrderTakeDetailView.onDataBackFailForFullLoadOrderOperate();
            }

            @Override
            public void onFinish() {

            }
        });
    }


    public void doSameCityDepart(String url, double longitude, double latitude) {


        iOrderOperate.doDepartSameCity(url, longitude, latitude, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {

                Order order = parseSerilizable.getParseToObj(data, Order.class);

                order = logicOrderTake.getSameCityOrder(order);

                if(vertifyNotNull.isNotNullObj(order)) {
                    iOrderTakeDetailView.onDataBackSuccessForSameCityDepart(order);
                }


            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iOrderTakeDetailView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iOrderTakeDetailView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }


                iOrderTakeDetailView.onDataBackFailForSameCityOrderOperate();
            }

            @Override
            public void onFinish() {

            }
        });
    }


    public void doFullLoadArrive(String url) {
        iOrderOperate.doArriveFullLoad(url, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {


                iOrderTakeDetailView.onDataBackSuccessForFullLoadArrive();
            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iOrderTakeDetailView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iOrderTakeDetailView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }


                iOrderTakeDetailView.onDataBackFailForSameCityOrderOperate();
            }

            @Override
            public void onFinish() {

            }
        });

    }

    public void doSendVertifyCode(String url, String phone, String option) {


        iVertifyCode.doSendVertifyCode(url, phone, option, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {
                iOrderTakeDetailView.onDataBackSuccessForSendPhoneCode();
            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iOrderTakeDetailView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iOrderTakeDetailView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }


                iOrderTakeDetailView.onDataBackFailForFullLoadOrderOperate();
            }

            @Override
            public void onFinish() {

            }
        });
    }


    public void doVertifyPhoneCode(String url, final String phone, final String pass_code, final String businessType) {


        iVertifyCode.doConfirmVertifyCode(url, phone, pass_code, new OnDataBackListener() {
            @Override
            public void onStart() {
//                iOrderTakeDetailView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {
                iOrderTakeDetailView.onDataBackSuccessForVertifyPhoneCode(phone, pass_code);

            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iOrderTakeDetailView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iOrderTakeDetailView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }


                switch (businessType) {

                    case ConstParams.Orders.FULL_LOAD:
                        iOrderTakeDetailView.onDataBackFailForFullLoadOrderOperate();
                        break;

                    case ConstParams.Orders.SAME_CITY:

                        iOrderTakeDetailView.onDataBackFailForSameCityOrderOperate();
                        break;
                }


            }

            @Override
            public void onFinish() {
//                iOrderTakeDetailView.dismissLoadingDialog();
            }
        });
    }


    public void doFinishOrder(String url, String pass_code, final String businessType) {
        iOrderOperate.doFinishOrder(url, pass_code, new OnDataBackListener() {
            @Override
            public void onStart() {

//                iOrderTakeDetailView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                iOrderTakeDetailView.onDataBackSuccessForOrderFinish();

            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iOrderTakeDetailView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iOrderTakeDetailView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

                switch (businessType) {

                    case ConstParams.Orders.FULL_LOAD:
                        iOrderTakeDetailView.onDataBackFailForFullLoadOrderOperate();
                        break;

                    case ConstParams.Orders.SAME_CITY:
                        iOrderTakeDetailView.onDataBackFailForSameCityOrderOperate();
                        break;
                }


            }

            @Override
            public void onFinish() {
//                iOrderTakeDetailView.dismissLoadingDialog();
            }
        });
    }


    public void doArrivedNext(Order order) {


        String payer = order.getPayer();

        Fee fee = order.getFee();
        /**
         * 发货方付款
         */
        if (logicOrderTake.isShipper(payer)) {


            /**
             * 有代收货款
             */
            if (logicOrderTake.isHasCod(fee)) {


                /**
                 * 变更Ui为滑动向我付款
                 */
                iOrderTakeDetailView.onDataBackSuccessForFullLoadArriveAndChangeSwipUiForPayToMe();

            } else {

                iOrderTakeDetailView.onDataBackSuccessForFullLoadArriveAndChangeSwipUiFoSendPhoneCode();

            }

        } else {


            /**
             * 变更Ui为滑动向我付款
             */
            iOrderTakeDetailView.onDataBackSuccessForFullLoadArriveAndChangeSwipUiForPayToMe();
        }


    }


    public void doGetGatherChannel(String url, String order_type, String os_type, String device_type) {
        iMoney.doGetPaymentsChannels(url, order_type, os_type, device_type, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {


                List<PayChannelInfo> payChannelInfos = parseSerilizable.getParseToNoItemsList(data, PayChannelInfo.class);

                OssConfig ossConfig = iOssDao.findFirst(OssConfig.class);

                if (vertifyNotNull.isNotNullListSize(payChannelInfos) && vertifyNotNull.isNotNullObj(ossConfig)) {

                    payChannelInfos = functionPayChannel.getReSetChannelInfos(payChannelInfos, ossConfig);

                }

                iOrderTakeDetailView.onDataBackSuccessForShowPayChannel(payChannelInfos);


            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iOrderTakeDetailView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iOrderTakeDetailView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

                iOrderTakeDetailView.onDataBackFailForSameCityOrderOperate();
            }

            @Override
            public void onFinish() {


            }
        });

    }


    public void doSendVertifyCodeSameCity(String url, String phone, String option) {


        iVertifyCode.doSendVertifyCode(url, phone, option, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {
                iOrderTakeDetailView.onDataBackSuccessForSendPhoneCode();
            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iOrderTakeDetailView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iOrderTakeDetailView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
                iOrderTakeDetailView.onDataBackFailForSameCityOrderOperate();
            }

            @Override
            public void onFinish() {

            }
        });
    }


    public void doCheckSameCityDepart(Order order) {


        List<PathPoint> pathPoints = order.getPath_points();

        if (functionPathPoint.isHasPassPoint(pathPoints)) {

            int count = functionPathPoint.currentDriverNotArrivePathPointCount(pathPoints);



            if (count == 0) {

                iOrderTakeDetailView.onDataBackSuccessForSameCityChangeSwipUiForArrive(order);
            }else {

                iOrderTakeDetailView.onDataBackSuccessForSameCityChangeSwipUiForToNextPathPoint(order);

            }

        } else {

            iOrderTakeDetailView.onDataBackSuccessForSameCityChangeSwipUiForArrive(order);

        }


    }


    public void doSameCityArriveNext(String url, final Order order) {



       final String bussinessType = order.getBusiness_type();
        List<PathPoint> pathPoints = order.getPath_points();


        if (!vertifyNotNull.isNotNullListSize(pathPoints)) {

            return;
        }


        int step = functionPathPoint.currentDriverPathPointStepIndex(pathPoints);



        PathPoint pathPoint = pathPoints.get(step);

        if (!vertifyNotNull.isNotNullObj(pathPoint)) {

            return;
        }

        Coordinate coordinate = pathPoint.getCoordinate();

        if (!vertifyNotNull.isNotNullObj(coordinate)) {

            return;
        }


        iOrderOperate.doArriveSameCityNext(url, Double.valueOf(coordinate.getLongitude()), Double.valueOf(coordinate.getLatitude()), new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {

                Order order = parseSerilizable.getParseToObj(data, Order.class);

                if (vertifyNotNull.isNotNullObj(order)) {

                    order = logicOrderTake.getSameCityOrder(order);
                    if(vertifyNotNull.isNotNullObj(order)) {
                        iOrderTakeDetailView.onDataBackSuccessForSameCityChangeSwipUiForToNextPathPoint(order);
                    }

                }



            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iOrderTakeDetailView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iOrderTakeDetailView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

                switch (bussinessType) {

                    case ConstParams.Orders.FULL_LOAD:
                        iOrderTakeDetailView.onDataBackFailForFullLoadOrderOperate();
                        break;

                    case ConstParams.Orders.SAME_CITY:
                        iOrderTakeDetailView.onDataBackFailForSameCityOrderOperate();
                        break;
                }
            }

            @Override
            public void onFinish() {

            }
        });

    }


    public void doSameCityArrive(String url) {
        iOrderOperate.doArriveSameCity(url, new OnDataBackListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String data) {


                Order order = parseSerilizable.getParseToObj(data,Order.class);

                if(vertifyNotNull.isNotNullObj(order)) {

                    order = logicOrderTake.getSameCityOrder(order);

                    if(vertifyNotNull.isNotNullObj(order)) {

                        iOrderTakeDetailView.onDataBackSuccessForSameCityArrive(order);
                    }
                }


            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iOrderTakeDetailView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iOrderTakeDetailView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }


                iOrderTakeDetailView.onDataBackFailForSameCityOrderOperate();
            }

            @Override
            public void onFinish() {


            }
        });

    }


    public void doArrivedNextSameCity(Order order) {


        String payer = order.getPayer();

        Fee fee = order.getFee();
        /**
         * 发货方付款
         */
        if (logicOrderTake.isShipper(payer)) {


            /**
             * 有代收货款
             */
            if (logicOrderTake.isHasCod(fee)) {
                /**
                 * 变更Ui为滑动向我付款
                 */
                iOrderTakeDetailView.onDataBackSuccessForSameCityArriveAndChangeSwipUiForPayToMe(order);
            } else {
                iOrderTakeDetailView.onDataBackSuccessForSameCityArriveAndChangeSwipUiFoSendPhoneCode(order);
            }
        } else {

            /**
             * 变更Ui为滑动向我付款
             */
            iOrderTakeDetailView.onDataBackSuccessForSameCityArriveAndChangeSwipUiForPayToMe(order);
        }


    }

}
