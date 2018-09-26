package com.adhdriver.work.function;

import com.adhdriver.work.entity.driver.credit.RatingDetial;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 * 类描述
 * 版本
 */

public class FunctionCredit {


    public HashMap<String,Float> getRadaDriverData(List<RatingDetial> ratingDetials) {
        HashMap<String,Float>  valueHash = new LinkedHashMap<>();

        for(RatingDetial ratingDetial:ratingDetials) {

            valueHash.put(ratingDetial.getRating_category_name(),ratingDetial.getTotal_score()*20);

        }


//        valueHash.put("沟通", 33F);
//        valueHash.put("行为", 100F);
//        valueHash.put("履约", 66F);
//        valueHash.put("服务", 77F);


        return valueHash;

    }
}
