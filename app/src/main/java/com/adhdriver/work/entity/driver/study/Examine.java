package com.adhdriver.work.entity.driver.study;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/6/15.
 * 类描述  考试实体类
 * 版本
 */

public class Examine extends DataSupport {

    private int id;//自增id

    private String test_questions_no;//试题编号
    private int paper_id;//试卷Id
    private int daturn_id;//资料Id
    private int daturn_answer_id;//正确答案Id ,
    private String create_time;//创建时间 ,
    private String daturn_topic;//试题题目


    public Examine() {
    }

    public String getTest_questions_no() {
        return test_questions_no;
    }

    public void setTest_questions_no(String test_questions_no) {
        this.test_questions_no = test_questions_no;
    }

    public int getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(int paper_id) {
        this.paper_id = paper_id;
    }

    public int getDaturn_id() {
        return daturn_id;
    }

    public void setDaturn_id(int daturn_id) {
        this.daturn_id = daturn_id;
    }

    public int getDaturn_answer_id() {
        return daturn_answer_id;
    }

    public void setDaturn_answer_id(int daturn_answer_id) {
        this.daturn_answer_id = daturn_answer_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getDaturn_topic() {
        return daturn_topic;
    }

    public void setDaturn_topic(String daturn_topic) {
        this.daturn_topic = daturn_topic;
    }

    @Override
    public String toString() {
        return "Examine{" +
                "test_questions_no='" + test_questions_no + '\'' +
                ", paper_id=" + paper_id +
                ", daturn_id=" + daturn_id +
                ", daturn_answer_id=" + daturn_answer_id +
                ", create_time='" + create_time + '\'' +
                ", daturn_topic='" + daturn_topic + '\'' +
                '}';
    }
}
