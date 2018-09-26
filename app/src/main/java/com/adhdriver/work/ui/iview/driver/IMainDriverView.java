package com.adhdriver.work.ui.iview.driver;

import com.adhdriver.work.entity.AppUpdate;
import com.adhdriver.work.entity.User;
import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.push.PushEntity;
import com.adhdriver.work.ui.iview.IBaseView;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;

/**
 * Created by Alie on 2017/11/6.
 * 类描述  司机首页的iview
 * 版本
 */

public interface IMainDriverView extends IBaseView{


    void onDataBackFail(int code, String errorMsg);



    void onDataBackFailForPush();


    void onDataBackSuccessForGetUserInfo(User user);


    void onDataBackSuccessForGetAppInfo(AppUpdate appUpdate);




    void doGoToUrlDownLoad(String downLoadUrl);



    void doGoToLogon();

    void doShowSelectFg(String fgTag);

    void onDataBackSuccessForGetVisitorToken();

    void doinitLocationForVisitor();


    /**
     * 处理推送 ，获取订单详情
     * @param
     */
    void doPushToGetOrderInfo(PushEntity pushEntity);


    /**
     * 跳转到订收益列表界面
     */
    void doPushToGatherPage();


    void doPushGoSameCityAlertPage(PushEntity pushEntity,Order order);


    void doPushGoOverOfficeAlertPage(PushEntity pushEntity);

    /**
     * 进入ordeTake界面
     * @param order
     */
    void  doPushGoToOrderTake(Order order);

    /**
     * 上传中....
     * @param request
     * @param currentSize
     * @param totalSize
     */
    void ossOnProgress(PutObjectRequest request, long currentSize, long totalSize);


    void ossOnSuccessLoadIdUserHeadToUi(String url,int id);


    void ossOnFailure();



    /**
     * 权限检查
     */

    void doPermissionCheck();


    /**
     * 关闭权限时候的打开提醒
     */
    void doShowPermissionAlert();


    /**
     * 弹出控件显示
     */
    void doShowPhotoPopWindow();

    /**
     * 向自己的服务器更新图片路径成功
     */
    void onDataBackSuccessForUpLoadAvatar();
}
