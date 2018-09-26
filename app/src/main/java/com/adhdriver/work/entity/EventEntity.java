package com.adhdriver.work.entity;

import com.amap.api.services.core.LatLonPoint;

/**
 * Created by Administrator on 2017/5/17.
 * 类描述   用于eventBus对象传递
 * 版本
 */

public class EventEntity {

    private String notifyMsg;

    private int notifyTag; //外层tag

    private int childNotifyTag; //内层tag

    private LatLonPoint latLonPoint;

    public EventEntity() {
    }

    public String getNotifyMsg() {
        return notifyMsg;
    }

    public void setNotifyMsg(String notifyMsg) {
        this.notifyMsg = notifyMsg;
    }

    public int getNotifyTag() {
        return notifyTag;
    }

    public void setNotifyTag(int notifyTag) {
        this.notifyTag = notifyTag;
    }

    public LatLonPoint getLatLonPoint() {
        return latLonPoint;
    }

    public void setLatLonPoint(LatLonPoint latLonPoint) {
        this.latLonPoint = latLonPoint;
    }


    public int getChildNotifyTag() {
        return childNotifyTag;
    }

    public void setChildNotifyTag(int childNotifyTag) {
        this.childNotifyTag = childNotifyTag;
    }

    @Override
    public String toString() {
        return "EventEntity{" +
                "notifyMsg='" + notifyMsg + '\'' +
                ", notifyTag=" + notifyTag +
                ", childNotifyTag=" + childNotifyTag +
                ", latLonPoint=" + latLonPoint +
                '}';
    }
}
