package com.adhdriver.work.entity.driver.study;

/**
 * Created by Administrator on 2017/6/15.
 * 类描述 考试答案实体类
 * 版本
 */

public class Answer {



   private int  answer_id;//答案Id ;
   private String answer_content;//答案内容 ,
   private int  t_index ;//答案顺序索引

    public Answer() {
    }

    public int getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(int answer_id) {
        this.answer_id = answer_id;
    }

    public String getAnswer_content() {
        return answer_content;
    }

    public void setAnswer_content(String answer_content) {
        this.answer_content = answer_content;
    }

    public int getT_index() {
        return t_index;
    }

    public void setT_index(int t_index) {
        this.t_index = t_index;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answer_id=" + answer_id +
                ", answer_content='" + answer_content + '\'' +
                ", t_index=" + t_index +
                '}';
    }
}
