package com.adhdriver.work.entity.driver.credit;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/19.
 * 类描述
 * 版本
 */

public class RatingDetial implements Serializable {


    private float rating_count;//    评分次数

    private float total_score;//单项总得分

    private String rating_category_name;//评分类型名称 ,


    private String rating_category_description;//评分类型描述


    public RatingDetial() {
    }

    public float getRating_count() {
        return rating_count;
    }

    public void setRating_count(float rating_count) {
        this.rating_count = rating_count;
    }

    public float getTotal_score() {
        return total_score;
    }

    public void setTotal_score(float total_score) {
        this.total_score = total_score;
    }

    public String getRating_category_name() {
        return rating_category_name;
    }

    public void setRating_category_name(String rating_category_name) {
        this.rating_category_name = rating_category_name;
    }

    public String getRating_category_description() {
        return rating_category_description;
    }

    public void setRating_category_description(String rating_category_description) {
        this.rating_category_description = rating_category_description;
    }

    @Override
    public String toString() {
        return "RatingDetial{" +
                "rating_count=" + rating_count +
                ", total_score=" + total_score +
                ", rating_category_name='" + rating_category_name + '\'' +
                ", rating_category_description='" + rating_category_description + '\'' +
                '}';
    }
}
