package com.adhdriver.work.utils;

/**
 * Created by Administrator on 2017/6/16.
 * 类描述
 * 版本
 */

public class IntegerUtil {



    public static int getExamMak(float rightCount,float allCount) {

        int mark = 100;


        mark = (int) (mark * (rightCount/allCount));

        return mark;

    }
}
