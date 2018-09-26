package com.adhdriver.work.presenter.driver;

import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.driver.orders.Order;
import com.adhdriver.work.entity.driver.temp.PublishRoute;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IPublishRoute;
import com.adhdriver.work.method.impl.IPublishRouteImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IPublishRoutesView;
import com.adhdriver.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created by Administrator on 2017/12/7.
 * 类描述
 * 版本
 */

public class PresenterDriverPublishRoutes extends BasePresenter<IPublishRoutesView>  {

    private IPublishRoutesView iPublishRoutesView;
    private IPublishRoute iPublishRoute;
    private VertifyNotNull vertifyNotNull;
    private ParseSerilizable parseSerilizable;

    public PresenterDriverPublishRoutes(IPublishRoutesView iPublishRoutesView) {
        this.iPublishRoutesView = iPublishRoutesView;
        this.iPublishRoute = new IPublishRouteImpl();
        this.vertifyNotNull = new VertifyNotNull();
        this.parseSerilizable = new ParseSerilizable();
    }


    public void doGetPublishRoutes(String url,int size,int index) {

        iPublishRoute.doGetPublishRoutes(url, size, index, new OnDataBackListener() {
            @Override
            public void onStart() {
                iPublishRoutesView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {


                List<PublishRoute> list = parseSerilizable.getParseToList(data, PublishRoute.class);
                if (vertifyNotNull.isNotNullListSize(list)) {
                    iPublishRoutesView.onDataBackSuccessForPublishRoutes(list);
                }

            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if(vertifyNotNull.isNotNullObj(errorEntity)) {

                    iPublishRoutesView.onDataBackFail(code,errorEntity.getError_message());
                }else {

                    iPublishRoutesView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
                iPublishRoutesView.dismissLoadingDialog();
            }
        });
    }


    /**
     * 下拉刷新获取路线列表
     * @param url
     * @param size
     * @param index
     */
    public void doGetPublishRoutesInFresh(String url,int size,int index) {

        iPublishRoute.doGetPublishRoutesInFresh(url, size, index, new OnDataBackListener() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(String data) {


                List<PublishRoute> list = parseSerilizable.getParseToList(data, PublishRoute.class);
                iPublishRoutesView.onDataBackSuccessForPublishRoutesInFresh(list);

            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if(vertifyNotNull.isNotNullObj(errorEntity)) {

                    iPublishRoutesView.onDataBackFail(code,errorEntity.getError_message());
                }else {

                    iPublishRoutesView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
            }
        });
    }



    /**
     * 上拉加载获取路线列表
     * @param url
     * @param size
     * @param index
     */
    public void doGetPublishRoutesInLoadMore(String url,int size,int index) {

        iPublishRoute.doGetPublishRoutesInLoadMore(url, size, index, new OnDataBackListener() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(String data) {


                List<PublishRoute> list = parseSerilizable.getParseToList(data, PublishRoute.class);
                iPublishRoutesView.onDataBackSuccessForPublishRoutesInLoadMore(list);

            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if(vertifyNotNull.isNotNullObj(errorEntity)) {

                    iPublishRoutesView.onDataBackFail(code,errorEntity.getError_message());
                }else {

                    iPublishRoutesView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
            }
        });
    }


    /**
     * 执行取消本条线路
     * @param url
     */
    public void doCancelThisRoute(String url) {


        iPublishRoute.doCancelThisRoute(url, new OnDataBackListener() {
            @Override
            public void onStart() {
                iPublishRoutesView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                iPublishRoutesView.onDataBackSuccessForCancelThisRoute();

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data,ErrorEntity.class);
                if(vertifyNotNull.isNotNullObj(errorEntity)) {
                    iPublishRoutesView.onDataBackFail(code,errorEntity.getError_message());
                }else {

                    iPublishRoutesView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);

                }
            }

            @Override
            public void onFinish() {
                iPublishRoutesView.dismissLoadingDialog();
            }
        });

    }


    /**
     * 展示cancel的dialog
     * @param list
     * @param position
     */
    public void doShowCancelRouteDialog(List<PublishRoute> list,int position) {

        if(vertifyNotNull.isNotNullListSize(list)){
            PublishRoute publishRoute = list.get(position);
            if(vertifyNotNull.isNotNullObj(publishRoute)) {

                iPublishRoutesView.doShowCancelThisRouteAlertDialog(publishRoute.getHitchhike_no());
            }
        }



    }




}
