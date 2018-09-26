package com.adhdriver.work.entity.driver.thirdauth;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/11/28.
 * 类描述  app内绑定 的实体类
 * 版本
 */

public class ValidAuthInApp implements Serializable{





    private String  auth_type;  //授权typen
    private AuthParam parameters; //下一步授权绑定后参数




    public ValidAuthInApp() {
    }


    public String getAuth_type() {
        return auth_type;
    }

    public void setAuth_type(String auth_type) {
        this.auth_type = auth_type;
    }


    public AuthParam getParameters() {
        return parameters;
    }

    public void setParameters(AuthParam parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "ValidAuthInApp{" +
                "auth_type='" + auth_type + '\'' +
                ", parameters=" + parameters +
                '}';
    }
}
