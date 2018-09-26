package com.adhdriver.work.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/1.
 * 类描述  时间工具类
 * 版本
 */

public class TimeUtil {


    /**
     * timePicke延迟半小时后的时间
     *
     * @return
     */
    public static String getDateFormateDelayHalfHour() {

        String data = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");


        long timeDely = System.currentTimeMillis() + 15 * 60 * 1000; //延迟半个小时


        Date dateDelay = new Date();
        dateDelay.setTime(timeDely);
        data = simpleDateFormat.format(dateDelay);
        return data;
    }


    /**
     * 向后延迟5天
     *
     * @return
     */
    public static String getDateFormateDelayfiveDays() {

        String data = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");


        long timeDely = System.currentTimeMillis() + 24 * 4 * 60 * 60 * 1000; //延迟半个小时


        Date dateDelay = new Date();
        dateDelay.setTime(timeDely);
        data = simpleDateFormat.format(dateDelay);
        return data;

    }


    /**
     * 组装时间
     * @param timeString
     * @return
     */
    public static String getAssembleToSeconds(String timeString) {

        String formate = "";
        SimpleDateFormat dateFormat = null;
        try {

            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date =dateFormat.parse(timeString);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            formate =  simpleDateFormat.format(date);



        } catch (ParseException e) {
            e.printStackTrace();
        }


        return formate;


    }


}
