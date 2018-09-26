package com.adhdriver.work.entity;

/**
 * Created by Alie on 2017/11/6.
 * 类描述
 * 版本
 */

public class ErrorEntity {


    /**
     * 统一错误数据处理 解析String
     *
     * @param string {
     *               "error_label": "AdminOperationIdNotExists",
     *               "error_code": "001033002",
     *               "error_message": "管理员操作记录不存在"
     *               }
     * @return
     */



    private String error_label;
    private String error_code;
    private String error_message;


    public ErrorEntity() {
    }

    public String getError_label() {
        return error_label;
    }

    public void setError_label(String error_label) {
        this.error_label = error_label;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    @Override
    public String toString() {
        return "ErrorEntity{" +
                "error_label='" + error_label + '\'' +
                ", error_code='" + error_code + '\'' +
                ", error_message='" + error_message + '\'' +
                '}';
    }
}
