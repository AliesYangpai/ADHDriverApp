package com.adhdriver.work.entity.driver.study;

/**
 * Created by Administrator on 2017/6/15.
 * 类描述 正确答案实体类
 * 版本
 */

public class CorrectAnswer {

    private String questionNo;

    private int answerId;

    public CorrectAnswer() {
    }


    public String getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(String questionNo) {
        this.questionNo = questionNo;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    @Override
    public String toString() {
        return "CorrectAnswer{" +
                "questionNo='" + questionNo + '\'' +
                ", answerId=" + answerId +
                '}';
    }
}
