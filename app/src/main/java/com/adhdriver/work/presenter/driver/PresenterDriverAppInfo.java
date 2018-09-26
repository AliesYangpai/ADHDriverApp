package com.adhdriver.work.presenter.driver;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.entity.AppUpdate;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IAppVersion;
import com.adhdriver.work.method.impl.IAppVersionImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IAppVersionView;
import com.adhdriver.work.utils.ToastUtil;
import com.adhdriver.work.utils.VersionUtil;

/**
 * Created by Administrator on 2017/11/23.
 * 类描述
 * 版本
 */

public class PresenterDriverAppInfo extends BasePresenter<IAppVersionView> {

    private IAppVersionView iAppVersionView;
    private IAppVersion iAppVersion;
    private ParseSerilizable parseSerilizable;


    public PresenterDriverAppInfo(IAppVersionView iAppVersionView) {
        this.iAppVersionView = iAppVersionView;
        this.iAppVersion = new IAppVersionImpl();
        this.parseSerilizable = new ParseSerilizable();
    }




    public void doGetAppInfo(String url, String os_type, String os_version, String model, String device_id) {

        iAppVersion.doGetAppInfo(url, os_type, os_version, model, device_id, new OnDataBackListener() {


            @Override
            public void onStart() {

                iAppVersionView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {


                AppUpdate appUpdate =   parseSerilizable.getParseToObj(data, AppUpdate.class);

                if(null != appUpdate) {

                    String serverBackVersionNum = appUpdate.getVersion_number();

                    int serverCode = Integer.valueOf(serverBackVersionNum);

                    int localCode = VersionUtil.getVersionCode();


                    if(serverCode > localCode) {

                        iAppVersionView.onDataBackSuccessForGetAppInfoNotNew(appUpdate);

                    }else if(serverCode == localCode) {

                        iAppVersionView.onDataBackSuccessForGetAppInfoIsNew();

                    }
                }else {

                    iAppVersionView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);

                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                if(null != errorEntity) {
                    iAppVersionView.onDataBackFail(code,errorEntity.getError_message());
                }else {

                    iAppVersionView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {

                iAppVersionView.dismissLoadingDialog();
            }
        });


    }


    /**
     * 设置应用信息
     */
    public void doSetLocalInfoToUi() {

        iAppVersionView.doSetLocalInfoToUi();
    }

}
