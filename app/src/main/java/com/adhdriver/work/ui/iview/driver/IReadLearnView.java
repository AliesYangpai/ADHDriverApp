package com.adhdriver.work.ui.iview.driver;

import com.adhdriver.work.entity.driver.study.Learning;
import com.adhdriver.work.ui.iview.IBaseView;

import java.util.List;

/**
 * Created by Administrator on 2017/12/22.
 * 类描述   阅读学习的view
 * 版本
 */

public interface IReadLearnView extends IBaseView {


    void onDataBackFail(int code, String errorMsg);

    void onDataBackSuccessForLearningInfos(List<Learning> learningList);

}
