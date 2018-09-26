package com.adhdriver.work.presenter.common;

import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.driver.token.TokenInfo;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IVisitorAbout;
import com.adhdriver.work.method.IWelcome;
import com.adhdriver.work.method.impl.IVisitorAboutImpl;
import com.adhdriver.work.method.impl.IWelcomeImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.IWelcomView;
import com.adhdriver.work.utils.SpUtil;
import com.adhdriver.work.verification.VertifyNotNull;

/**
 * Created by Alie on 2017/11/16.
 * 类描述
 * 版本
 */

public class PresenterWelcome extends BasePresenter<IWelcomView> {

    private IWelcomView iWelcomView;
    private IWelcome iWelcome;
    private IVisitorAbout iVisitorAbout;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;


    public PresenterWelcome(IWelcomView iWelcomView) {
        this.iWelcomView = iWelcomView;
//        this.iWelcome = new IWelcomeImpl();
        this.iVisitorAbout = new IVisitorAboutImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
    }


    public void doGoToMain() {

        iWelcomView.doGoToMain();

    }

    /**
     * 执行游客登陆
     */

    public void doVisitorLogon(String url) {

        iVisitorAbout.doVisitorLogon(url, new OnDataBackListener() {
            @Override
            public void onStart() {


            }

            @Override
            public void onSuccess(String data) {

                TokenInfo tokenInfo = parseSerilizable.getParseToObj(data, TokenInfo.class);

                if(vertifyNotNull.isNotNullObj(tokenInfo)) {


                    iWelcomView.onDataBackSuccessForVisitorLogon(tokenInfo);





                }

            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);


                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iWelcomView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iWelcomView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }


            }

            @Override
            public void onFinish() {

            }
        });

    }


}
