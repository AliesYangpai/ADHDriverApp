package com.adhdriver.work.presenter.driver;

import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.driver.study.Answer;
import com.adhdriver.work.entity.driver.study.AnswerPackage;
import com.adhdriver.work.entity.driver.study.ExamResult;
import com.adhdriver.work.entity.driver.study.Examine;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.http.requestparam.ExamParam;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.method.IExam;
import com.adhdriver.work.method.impl.IExamImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IExamView;
import com.adhdriver.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created by Administrator on 2018/1/18.
 * 类描述
 * 版本
 */

public class PresenterDriverExam extends BasePresenter<IExamView> {

    private IExamView iExamView;
    private IExam iExam;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;


    public PresenterDriverExam(IExamView iExamView) {
        this.iExamView = iExamView;
        this.iExam = new IExamImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
    }


    public void doGetExamQuestions(String url,String accessToken,int size,int index) {

        iExam.doGetEameQuestions(url, accessToken, size, index, new OnDataBackListener() {
            @Override
            public void onStart() {
                iExamView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                List <Examine> examines = parseSerilizable.getParseToList(data, Examine.class);

                if(vertifyNotNull.isNotNullListSize(examines)) {

                    iExamView.onDataBackSuccessForExamineQuestions(examines);
                }else {

                    iExamView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data,ErrorEntity.class);
                if(vertifyNotNull.isNotNullObj(errorEntity)) {
                    iExamView.onDataBackFail(code,errorEntity.getError_message());
                }else {
                    iExamView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {
                iExamView.dismissLoadingDialog();
            }
        });
    }


    public void doGetAnswers(String url,String accessToken) {

        iExam.doGetEameAnswers(url, accessToken, new OnDataBackListener() {
            @Override
            public void onStart() {
                iExamView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

               List<AnswerPackage> answerPackages = parseSerilizable.getParseToList(data, AnswerPackage.class);
                if(vertifyNotNull.isNotNullListSize(answerPackages)) {

                    iExamView.onDataBackSuccessForAnswerPackages(answerPackages);
                }else {
                    iExamView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data,ErrorEntity.class);
                if(vertifyNotNull.isNotNullObj(errorEntity)) {
                    iExamView.onDataBackFail(code,errorEntity.getError_message());
                }else {
                    iExamView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
                iExamView.dismissLoadingDialog();
            }
        });
    }

    public void doGetExamMark(String url,
                              String accessToken,
                              String driver_id,
                              int mark,
                              int paper_id,
                              int correct_number,
                              final int error_number) {

        iExam.doGetEameMark(
                url,
                accessToken,
                driver_id,
                mark,
                paper_id,
                correct_number,
                error_number, new OnDataBackListener() {
            @Override
            public void onStart() {
                iExamView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {
                ExamResult examResult = parseSerilizable.getParseToObj(data, ExamResult.class);
                if(vertifyNotNull.isNotNullObj(examResult)) {
                    iExamView.onDataBackSuccessForSendExamToGetResult(examResult);
                }else {
                    iExamView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data,ErrorEntity.class);
                if(vertifyNotNull.isNotNullObj(errorEntity)) {
                    iExamView.onDataBackFail(code,errorEntity.getError_message());
                }else {
                    iExamView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFinish() {
                iExamView.dismissLoadingDialog();
            }
        });
    }
}
