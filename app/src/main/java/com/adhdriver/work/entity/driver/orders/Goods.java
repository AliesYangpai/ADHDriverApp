package com.adhdriver.work.entity.driver.orders;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/5/11.
 * 类描述  货物相关信息
 * 版本
 */

public class Goods  implements Serializable {



    private String description;
    private String type;//TL 整车 LTL 零担
    private String quantity;//计价数量。
    private String weight;
    private String length;
    private String width;
    private String height;
    private String goods_volume;//货物体积

    private String load_photo_left;
    private String load_photo_front;
    private String load_photo_back;



    /**
     * 集包详情
     */
    public HashMap<String, PackageDetail> details;
    private String package_count;//包数量





    public Goods() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
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

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getPackage_count() {
        return package_count;
    }

    public void setPackage_count(String package_count) {
        this.package_count = package_count;
    }

    public HashMap<String, PackageDetail> getDetails() {
        return details;
    }

    public void setDetails(HashMap<String, PackageDetail> details) {
        this.details = details;
    }

    public String getLoad_photo_left() {
        return load_photo_left;
    }

    public void setLoad_photo_left(String load_photo_left) {
        this.load_photo_left = load_photo_left;
    }

    public String getLoad_photo_front() {
        return load_photo_front;
    }

    public void setLoad_photo_front(String load_photo_front) {
        this.load_photo_front = load_photo_front;
    }

    public String getLoad_photo_back() {
        return load_photo_back;
    }

    public void setLoad_photo_back(String load_photo_back) {
        this.load_photo_back = load_photo_back;
    }

    public String getGoods_volume() {
        return goods_volume;
    }

    public void setGoods_volume(String goods_volume) {
        this.goods_volume = goods_volume;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", quantity='" + quantity + '\'' +
                ", weight='" + weight + '\'' +
                ", length='" + length + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                ", goods_volume='" + goods_volume + '\'' +
                ", load_photo_left='" + load_photo_left + '\'' +
                ", load_photo_front='" + load_photo_front + '\'' +
                ", load_photo_back='" + load_photo_back + '\'' +
                ", details=" + details +
                ", package_count='" + package_count + '\'' +
                '}';
    }
}

























//public class Goods  implements Serializable {
//
//
//
//    private String description;
//    private String type;//TL 整车 LTL 零担
//    private String quantity;//计价数量。
//    private String weight;
//    private String length;
//    private String width;
//    private String height;
//
//
//
//
//    private String load_photo_left;
//    private String load_photo_front;
//    private String load_photo_back;
//
//
//    /**
//     * 集包详情
//     */
//    public HashMap<String, PackageDetail> details;
//
//
//
//    public Goods() {
//    }
//
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(String quantity) {
//        this.quantity = quantity;
//    }
//
//    public String getWeight() {
//        return weight;
//    }
//
//    public void setWeight(String weight) {
//        this.weight = weight;
//    }
//
//    public String getLength() {
//        return length;
//    }
//
//    public void setLength(String length) {
//        this.length = length;
//    }
//
//    public String getWidth() {
//        return width;
//    }
//
//    public void setWidth(String width) {
//        this.width = width;
//    }
//
//    public String getHeight() {
//        return height;
//    }
//
//    public void setHeight(String height) {
//        this.height = height;
//    }
//
//    public String getLoad_photo_left() {
//        return load_photo_left;
//    }
//
//    public void setLoad_photo_left(String load_photo_left) {
//        this.load_photo_left = load_photo_left;
//    }
//
//    public String getLoad_photo_front() {
//        return load_photo_front;
//    }
//
//    public void setLoad_photo_front(String load_photo_front) {
//        this.load_photo_front = load_photo_front;
//    }
//
//    public String getLoad_photo_back() {
//        return load_photo_back;
//    }
//
//    public void setLoad_photo_back(String load_photo_back) {
//        this.load_photo_back = load_photo_back;
//    }
//
//    public HashMap<String, PackageDetail> getDetails() {
//        return details;
//    }
//
//    public void setDetails(HashMap<String, PackageDetail> details) {
//        this.details = details;
//    }
//
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    @Override
//    public String toString() {
//        return "Goods{" +
//                "description='" + description + '\'' +
//                ", quantity='" + quantity + '\'' +
//                ", weight='" + weight + '\'' +
//                ", length='" + length + '\'' +
//                ", width='" + width + '\'' +
//                ", height='" + height + '\'' +
//                ", load_photo_left='" + load_photo_left + '\'' +
//                ", load_photo_front='" + load_photo_front + '\'' +
//                ", load_photo_back='" + load_photo_back + '\'' +
//                ", type='" + type + '\'' +
//                ", details=" + details +
//                '}';
//    }
//}
