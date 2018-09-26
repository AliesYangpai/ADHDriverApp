package com.adhdriver.work.ui.iview.driver;

import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.entity.driver.pay.PayChannelInfo;
import com.adhdriver.work.ui.iview.IBaseView;
import com.amap.api.navi.AMapNaviViewOptions;

import java.util.List;

/**
 * Created by 已接单的接口 on 2018/1/3.
 * 类描述
 * 版本
 */

public interface IOrderTakeDetailView extends IBaseView {


    void onDataBackFail(int code, String errorMsg);


    void onDataBackFailForOverOfficeOrderOperate();

    void onDataBackFailForFullLoadOrderOperate();



    void onDataBackFailForSameCityOrderOperate();

    void onDataBackSuccessForDeparting();

    void onDataBackSuccessForArrivePassingPint();


    void onDataBackSuccessForArriveDestination();

    void onDataBackSuccessForGettingGatheringMoneyWay();

    void doSetLocationDataToNavigate(AMapNaviViewOptions aMapNaviViewOptions);

    void doShowTitlePopWindow();



    void doShowOverOfficePopBottom();
    void doDealShowOrHideTitlePopWindow();




    void doDealShowOrHideOverOfficePopWindow();


    void doDestroyTitlePopWindow();


    void doDestroyOverOfficePopWindow();

    void doShowFullLoadPopBottom();
    void doDealShowOrHideFullLoadPopWindow();
    void doDestroyFullLoadPopWindow();




    void doShowSameCityPopBottom();
    void doDealShowOrHideSameCityPopWindow();
    void doDestroySameCityPopWindow();




    void OnDataBackSuccessForOverOfficeArrived();
    void onDataBackSuccessForOverOfficeDepart();

    void onDataBackSuccessForFullLoadDepart();
    void onDataBackSuccessForFullLoadArrive();
    void onDataBackSuccessForFullLoadArriveAndChangeSwipUiForPayToMe();
    void onDataBackSuccessForFullLoadArriveAndChangeSwipUiFoSendPhoneCode();



    void onDataBackSuccessForSameCityDepart(Order order);
    void onDataBackSuccessForSameCityChangeSwipUiForArrive(Order order);
    void onDataBackSuccessForSameCityChangeSwipUiForToNextPathPoint(Order order);
    void onDataBackSuccessForSameCityArrive(Order order);

    void onDataBackSuccessForSameCityArriveAndChangeSwipUiForPayToMe(Order order);
    void onDataBackSuccessForSameCityArriveAndChangeSwipUiFoSendPhoneCode(Order order);




    void onDataBackSuccessForSendPhoneCode();

    void onDataBackSuccessForVertifyPhoneCode(String phone,String pass_code);



    void onDataBackSuccessForOrderFinish();


    void onDataBackSuccessForShowPayChannel(List<PayChannelInfo> payChannelInfos);

}
