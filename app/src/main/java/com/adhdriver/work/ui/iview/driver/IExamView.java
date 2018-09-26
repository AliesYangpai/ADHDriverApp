package com.adhdriver.work.ui.iview.driver;

import com.adhdriver.work.entity.driver.study.AnswerPackage;
import com.adhdriver.work.entity.driver.study.ExamResult;
import com.adhdriver.work.entity.driver.study.Examine;
import com.adhdriver.work.ui.iview.IBaseView;

import java.util.List;

/**
 * Created by Administrator on 2018/1/18.
 * 类描述
 * 版本
 */

public interface IExamView extends IBaseView {

    void onDataBackFail(int code, String errorMsg);

    /**
     * 获取考试列表
     */
    void onDataBackSuccessForExamineQuestions(List<Examine> examineList);


    /**
     * 获取答案列表
     * @param answerPackageList
     */
    void onDataBackSuccessForAnswerPackages(List<AnswerPackage> answerPackageList);

    void onDataBackSuccessForSendExamToGetResult(ExamResult examResult);
}
