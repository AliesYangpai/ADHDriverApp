package com.adhdriver.work.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/11.
 * 类描述 活动详情
 * 版本
 */

public class ActInfo implements Serializable{


    private int id;
    private int posterImgId;
    private String posterUrl;
    private String actContent;


    public ActInfo() {
    }

    public int getPosterImgId() {
        return posterImgId;
    }

    public void setPosterImgId(int posterImgId) {
        this.posterImgId = posterImgId;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getActContent() {
        return actContent;
    }

    public void setActContent(String actContent) {
        this.actContent = actContent;
    }

    @Override
    public String toString() {
        return "ActInfo{" +
                "posterImgId=" + posterImgId +
                ", posterUrl='" + posterUrl + '\'' +
                ", actContent='" + actContent + '\'' +
                '}';
    }
}
