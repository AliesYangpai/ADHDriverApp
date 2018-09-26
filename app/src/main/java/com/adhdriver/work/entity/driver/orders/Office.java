package com.adhdriver.work.entity.driver.orders;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/19.
 * 类描述   营业部信息
 * 版本
 */

public class Office implements Serializable{


//          "office_id": 0,
//                  "name": "string",
//                  "is_primary": true,
//                  "is_tools": true,
//                  "resource_office": true,
//                  "parent_id": 0,


    private String office_id; //营业部id
    private String name;  //营业部名称
    private String is_primary; //是否为城市运营中心。
    private String is_tools;//是否为工具型营业部 ,
    private String resource_office;  //父级营业部编号。 ,
    private String parent_id;
    private String primary_phone; //主要联系电话。 ,


    private Province province;
    private City city;
    private County county;

    private Coordinate coordinate; //经纬度


    public Office() {
    }

    public String getOffice_id() {
        return office_id;
    }

    public void setOffice_id(String office_id) {
        this.office_id = office_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIs_primary() {
        return is_primary;
    }

    public void setIs_primary(String is_primary) {
        this.is_primary = is_primary;
    }

    public String getIs_tools() {
        return is_tools;
    }

    public void setIs_tools(String is_tools) {
        this.is_tools = is_tools;
    }

    public String getResource_office() {
        return resource_office;
    }

    public void setResource_office(String resource_office) {
        this.resource_office = resource_office;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getPrimary_phone() {
        return primary_phone;
    }

    public void setPrimary_phone(String primary_phone) {
        this.primary_phone = primary_phone;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }


    @Override
    public String toString() {
        return "Office{" +
                "office_id='" + office_id + '\'' +
                ", name='" + name + '\'' +
                ", is_primary='" + is_primary + '\'' +
                ", is_tools='" + is_tools + '\'' +
                ", resource_office='" + resource_office + '\'' +
                ", parent_id='" + parent_id + '\'' +
                ", primary_phone='" + primary_phone + '\'' +
                ", province=" + province +
                ", city=" + city +
                ", county=" + county +
                ", coordinate=" + coordinate +
                '}';
    }
}
