package com.adhdriver.work.presenter.driver;

import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.driver.vehicle.DriverVehicle;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.logic.LogicVehicle;
import com.adhdriver.work.method.IVehicle;
import com.adhdriver.work.method.impl.IVehicleImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IVehicleDetailView;
import com.adhdriver.work.verification.VertifyNotNull;

/**
 * Created by Administrator on 2018/1/15.
 * 类描述
 * 版本
 */

public class PresenterDriverVehicleDetail extends BasePresenter<IVehicleDetailView> {

    private IVehicleDetailView iVehicleDetailView;
    private IVehicle iVehicle;
    private VertifyNotNull vertifyNotNull;
    private ParseSerilizable parseSerilizable;


    private LogicVehicle logicVehicle;

    public PresenterDriverVehicleDetail(IVehicleDetailView iVehicleDetailView) {
        this.iVehicleDetailView = iVehicleDetailView;
        this.iVehicle = new IVehicleImpl();
        this.vertifyNotNull = new VertifyNotNull();
        this.parseSerilizable = new ParseSerilizable();
        this.logicVehicle = new LogicVehicle();
    }


    public void doGetVehicleDetail(String url) {

        iVehicle.doGetVehicleDetail(url, new OnDataBackListener() {
            @Override
            public void onStart() {
                iVehicleDetailView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {


                DriverVehicle driverVehicle = parseSerilizable.getParseToObj(data, DriverVehicle.class);
                if(vertifyNotNull.isNotNullObj(driverVehicle)) {



                    if(logicVehicle.isNullCatogroy(driverVehicle.getCategory_name())) {
                        iVehicleDetailView.onDataBackSuccessForHideVehicleCatogory();
                    }else {
                        iVehicleDetailView.onDataBackSuccessForShowVehicleCatogory(driverVehicle.getCategory_name());
                    }

                    if(logicVehicle.isCarPassPending(driverVehicle.getVehicle_status())) {

                        iVehicleDetailView.onDatBackSuccessForVehicleVertifyPending();
                    }

                    if(logicVehicle.isCarPassRejected(driverVehicle.getVehicle_status())) {

                        iVehicleDetailView.onDatBackSuccessForVehicleVertifyRejected();
                    }

                    if(logicVehicle.isCarPassApproved(driverVehicle.getVehicle_status())) {

                        iVehicleDetailView.onDatBackSuccessForVehicleVertifyApproved();
                    }


                    iVehicleDetailView.onDataBackSuccessForVehicleDetail(driverVehicle);
                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data,ErrorEntity.class);

                if(vertifyNotNull.isNotNullObj(errorEntity)) {

                    iVehicleDetailView.onDataBackFail(code,errorEntity.getError_message());
                }else {
                    iVehicleDetailView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
                iVehicleDetailView.dismissLoadingDialog();
            }
        });

    }

    public void doDelVehicle(String url) {

        iVehicle.doDelVehicle(url, new OnDataBackListener() {
            @Override
            public void onStart() {
                iVehicleDetailView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                iVehicleDetailView.onDataBackSuccessForDelete();

            }

            @Override
            public void onFail(int code, String data) {
                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data,ErrorEntity.class);
                if(vertifyNotNull.isNotNullObj(errorEntity)) {
                    iVehicleDetailView.onDataBackFail(code,errorEntity.getError_message());
                }else {
                    iVehicleDetailView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {
                iVehicleDetailView.dismissLoadingDialog();
            }
        });
    }


}
