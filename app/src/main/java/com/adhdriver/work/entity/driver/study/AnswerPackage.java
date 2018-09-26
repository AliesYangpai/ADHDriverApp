package com.adhdriver.work.entity.driver.study;

import java.util.List;

/**
 * Created by Administrator on 2017/6/15.
 * 类描述
 * 版本
 */

public class AnswerPackage {


    private String questions_no;

    private List<Answer> answers;


    public AnswerPackage() {
    }

    public String getQuestions_no() {
        return questions_no;
    }

    public void setQuestions_no(String questions_no) {
        this.questions_no = questions_no;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "AnswerPackage{" +
                "questions_no='" + questions_no + '\'' +
                ", answers=" + answers +
                '}';
    }
}
