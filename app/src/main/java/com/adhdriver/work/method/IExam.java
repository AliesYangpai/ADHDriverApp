package com.adhdriver.work.method;

import com.adhdriver.work.listener.OnDataBackListener;

/**
 * Created by Administrator on 2018/1/18.
 * 类描述
 * 版本
 */

public interface IExam {


    /**
     * 获取全部考试题
     * @param url
     * @param accessToken
     * @param size
     * @param index
     * @param onDataBackListener
     */
    void doGetEameQuestions(String url, String accessToken, int size, int index, OnDataBackListener onDataBackListener);

    /**
     * 获取全部答案
     * @param url
     * @param accessToken
     * @param onDataBackListener
     */
    void doGetEameAnswers(String url, String accessToken, OnDataBackListener onDataBackListener);


    /**
     * 获取考试成绩
     * @param url
     * @param accessToken
     * @param driver_id
     * @param mark
     * @param paper_id
     * @param correct_number
     * @param error_number
     * @param onDataBackListener
     */
    void doGetEameMark(String url,
                       String accessToken,
                       String driver_id,
                       int mark,
                       int paper_id,
                       int correct_number,
                       int error_number, OnDataBackListener onDataBackListener);
}
