package com.adhdriver.work.presenter.driver;

import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.driver.study.Learning;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IReadLearn;
import com.adhdriver.work.method.impl.IReadLearnImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.iview.driver.IReadLearnView;
import com.adhdriver.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created by Administrator on 2017/12/22.
 * 类描述
 * 版本
 */

public class PresenterDriverReadLearn extends BasePresenter<IReadLearnView> {

    private IReadLearnView iReadLearnView;
    private IReadLearn iReadLearn;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;

    public PresenterDriverReadLearn(IReadLearnView iReadLearnView) {
        this.iReadLearnView = iReadLearnView;
        this.iReadLearn = new IReadLearnImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
    }



    public void doGetLearningMaterials(String url, String accessToken, int size, int index) {

        iReadLearn.doGetLearningMaterials(url, accessToken, size, index, new OnDataBackListener() {
            @Override
            public void onStart() {

                iReadLearnView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                List<Learning> learnings = parseSerilizable.getParseToList(data, Learning.class);

                if(vertifyNotNull.isNotNullListSize(learnings)){

                    iReadLearnView.onDataBackSuccessForLearningInfos(learnings);

                }else {
                    iReadLearnView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }


            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if(vertifyNotNull.isNotNullObj(errorEntity)) {
                    iReadLearnView.onDataBackFail(code,errorEntity.getError_message());
                }else {
                    iReadLearnView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {


                iReadLearnView.dismissLoadingDialog();
            }
        });


    }

}
