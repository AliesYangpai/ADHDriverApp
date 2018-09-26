package com.adhdriver.work.entity;

/**
 * Created by Administrator on 2017/6/26.
 * 类描述  版本更新的实体类
 * 版本
 */

public class AppUpdate {

    private String required_os_type; //需要的操作系统类型。 = ['iOS', 'Android', 'Windows'],

    private String required_os_version;//需要的操作系统版本。 ,

    private String name;//更新名称。 ,

    private String description; //说明。 ,

    private String version_type; //版本类型。 = ['Alpha', 'Beta', 'Release']

    private String version_number; //版本号。 ,

    private String upgrade_type;  //更新类型。 = ['Optional', 'Recommended', 'Required'],

    private String download_url;   //下载地址。 ,

    private String create_time;    //时间


    public AppUpdate() {
    }


    public String getRequired_os_type() {
        return required_os_type;
    }

    public void setRequired_os_type(String required_os_type) {
        this.required_os_type = required_os_type;
    }

    public String getRequired_os_version() {
        return required_os_version;
    }

    public void setRequired_os_version(String required_os_version) {
        this.required_os_version = required_os_version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion_type() {
        return version_type;
    }

    public void setVersion_type(String version_type) {
        this.version_type = version_type;
    }

    public String getVersion_number() {
        return version_number;
    }

    public void setVersion_number(String version_number) {
        this.version_number = version_number;
    }

    public String getUpgrade_type() {
        return upgrade_type;
    }

    public void setUpgrade_type(String upgrade_type) {
        this.upgrade_type = upgrade_type;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
