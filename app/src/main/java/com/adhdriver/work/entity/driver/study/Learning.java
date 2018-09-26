package com.adhdriver.work.entity.driver.study;

/**
 * Created by Administrator on 2017/6/14.
 * 类描述 学习实体类
 * 版本
 */

public class Learning {




    private String daturn_id;//题目id
    private String daturn_topic;//题目内容
    private String daturn_answer_id;//答案id
    private String create_time;//创建时间
    private String modify_admin_id;
    private String  modify_date_time;
    private String answer_content;//答案

    public Learning() {
    }

    public String getDaturn_id() {
        return daturn_id;
    }

    public void setDaturn_id(String daturn_id) {
        this.daturn_id = daturn_id;
    }

    public String getDaturn_topic() {
        return daturn_topic;
    }

    public void setDaturn_topic(String daturn_topic) {
        this.daturn_topic = daturn_topic;
    }

    public String getDaturn_answer_id() {
        return daturn_answer_id;
    }

    public void setDaturn_answer_id(String daturn_answer_id) {
        this.daturn_answer_id = daturn_answer_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getModify_admin_id() {
        return modify_admin_id;
    }

    public void setModify_admin_id(String modify_admin_id) {
        this.modify_admin_id = modify_admin_id;
    }

    public String getModify_date_time() {
        return modify_date_time;
    }

    public void setModify_date_time(String modify_date_time) {
        this.modify_date_time = modify_date_time;
    }

    public String getAnswer_content() {
        return answer_content;
    }

    public void setAnswer_content(String answer_content) {
        this.answer_content = answer_content;
    }


    @Override
    public String toString() {
        return "Learning{" +
                "daturn_id='" + daturn_id + '\'' +
                ", daturn_topic='" + daturn_topic + '\'' +
                ", daturn_answer_id='" + daturn_answer_id + '\'' +
                ", create_time='" + create_time + '\'' +
                ", modify_admin_id='" + modify_admin_id + '\'' +
                ", modify_date_time='" + modify_date_time + '\'' +
                ", answer_content='" + answer_content + '\'' +
                '}';
    }
}
