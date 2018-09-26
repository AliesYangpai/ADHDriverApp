package com.adhdriver.work.entity.driver.study;

/**
 * Created by Administrator on 2017/6/16.
 * 类描述  提交考试结果后的返回
 * 版本
 */

public class ExamResult {

//    {"examine_mark_id":1,
//            "driver_id":65,
//            "mark":0,
//            "paper_id":1,
//            "correct_number":0,
//            "error_number":9,
//            "create_time":"2017-04-24 14:36:55"}


    private int examine_mark_id;
    private String driver_id;
    private int mark;
    private int paper_id;
    private int correct_number;
    private int error_number;
    private String create_time;


    public ExamResult() {
    }

    public int getExamine_mark_id() {
        return examine_mark_id;
    }

    public void setExamine_mark_id(int examine_mark_id) {
        this.examine_mark_id = examine_mark_id;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(int paper_id) {
        this.paper_id = paper_id;
    }

    public int getCorrect_number() {
        return correct_number;
    }

    public void setCorrect_number(int correct_number) {
        this.correct_number = correct_number;
    }

    public int getError_number() {
        return error_number;
    }

    public void setError_number(int error_number) {
        this.error_number = error_number;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }


    @Override
    public String toString() {
        return "ExamResult{" +
                "examine_mark_id=" + examine_mark_id +
                ", driver_id='" + driver_id + '\'' +
                ", mark=" + mark +
                ", paper_id=" + paper_id +
                ", correct_number=" + correct_number +
                ", error_number=" + error_number +
                ", create_time='" + create_time + '\'' +
                '}';
    }
}
