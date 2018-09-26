package com.adhdriver.work.entity.driver.orders;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/19.
 * 类描述
 * 版本
 */

public class Vehicle implements Serializable {


    private String vehicle_id;
    private String name;
    private String avatar;
    private String description;
    private String starting_price;
    private String starting_mileage;
    private String price;
    private String deadweight;
    private String length;
    private String width;
    private String heigh;
    private String vehicle_stere;
    private String category_name;//箱车、高栏、平板

    public Vehicle() {
    }

    public String getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(String vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStarting_price() {
        return starting_price;
    }

    public void setStarting_price(String starting_price) {
        this.starting_price = starting_price;
    }

    public String getStarting_mileage() {
        return starting_mileage;
    }

    public void setStarting_mileage(String starting_mileage) {
        this.starting_mileage = starting_mileage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDeadweight() {
        return deadweight;
    }

    public void setDeadweight(String deadweight) {
        this.deadweight = deadweight;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeigh() {
        return heigh;
    }

    public void setHeigh(String heigh) {
        this.heigh = heigh;
    }

    public String getVehicle_stere() {
        return vehicle_stere;
    }

    public void setVehicle_stere(String vehicle_stere) {
        this.vehicle_stere = vehicle_stere;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicle_id='" + vehicle_id + '\'' +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", description='" + description + '\'' +
                ", starting_price='" + starting_price + '\'' +
                ", starting_mileage='" + starting_mileage + '\'' +
                ", price='" + price + '\'' +
                ", deadweight='" + deadweight + '\'' +
                ", length='" + length + '\'' +
                ", width='" + width + '\'' +
                ", heigh='" + heigh + '\'' +
                ", vehicle_stere='" + vehicle_stere + '\'' +
                ", category_name='" + category_name + '\'' +
                '}';
    }
}
