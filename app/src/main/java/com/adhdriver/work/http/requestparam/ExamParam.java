package com.adhdriver.work.http.requestparam;

import com.google.gson.JsonObject;

/**
 * Created by Administrator on 2018/1/18.
 * 类描述
 * 版本
 */

public class ExamParam extends BaseParam{


    /**
     * 获取考试成绩
     * @param driver_id
     * @param mark
     * @param paper_id
     * @param correct_number
     * @param error_number
     * @return
     */
    public String getExamMarkParam(String driver_id, int mark, int paper_id, int correct_number, int error_number) {

        JsonObject jsonObject = getJsonObject();

        jsonObject.addProperty("driver_id", driver_id);
        jsonObject.addProperty("mark", mark);
        jsonObject.addProperty("paper_id", paper_id);
        jsonObject.addProperty("correct_number", correct_number);
        jsonObject.addProperty("error_number", error_number);



        return jsonObject.toString();

    }

}
