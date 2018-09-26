package com.adhdriver.work.logic;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstTag;
import com.adhdriver.work.entity.driver.thirdauth.AuthState;
import com.adhdriver.work.verification.VertifyNotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/29.
 * 类描述   第三方绑定的逻辑类
 * 版本
 */

public class LogicThirdAuth {


    private VertifyNotNull vertifyNotNull;


    public LogicThirdAuth() {

        this.vertifyNotNull = new VertifyNotNull();
    }


    /**
     * 只绑定的qq
     * @param list
     * @return
     */
    public boolean isOnlyQQBind(List<AuthState> list) {

        boolean result = false;


        List<String> listTemp = new ArrayList<>();

        for (AuthState authState : list) {
            String auth_type = authState.getAuth_type();
            listTemp.add(auth_type);
        }

        if(vertifyNotNull.isNotNullListSize(listTemp)) {

            if(listTemp.contains(ConstTag.AuthTag.QQ) && !listTemp.contains(ConstTag.AuthTag.WX)) {
                result = true;
            }
        }
        return result;
    }



    /**
     * qq已经被绑定
     * @param list
     * @return
     */
    public boolean isQQHasBind(List<AuthState> list) {

        boolean result = false;


        List<String> listTemp = new ArrayList<>();

        for (AuthState authState : list) {
            String auth_type = authState.getAuth_type();
            listTemp.add(auth_type);
        }

        if(vertifyNotNull.isNotNullListSize(listTemp)) {

            if(listTemp.contains(ConstTag.AuthTag.QQ)) {
                result = true;
            }
        }
        return result;
    }








    /**
     * wx已经被绑定
     * @param list
     * @return
     */
    public boolean isWxHasBind(List<AuthState> list) {

        boolean result = false;


        List<String> listTemp = new ArrayList<>();

        for (AuthState authState : list) {
            String auth_type = authState.getAuth_type();
            listTemp.add(auth_type);
        }

        if(vertifyNotNull.isNotNullListSize(listTemp)) {

            if(listTemp.contains(ConstTag.AuthTag.WX)) {
                result = true;
            }
        }
        return result;
    }




    /**
     * 只绑定了 wx
     * @param list
     * @return
     */
    public boolean isOnlyWxBind(List<AuthState> list) {

        boolean result = false;

        List<String> listTemp = new ArrayList<>();

        for (AuthState authState : list) {
            String auth_type = authState.getAuth_type();
            listTemp.add(auth_type);
        }

        if(vertifyNotNull.isNotNullListSize(listTemp)) {
            if(listTemp.contains(ConstTag.AuthTag.WX) && !listTemp.contains(ConstTag.AuthTag.QQ)) {
                result = true;
            }
        }

        return result;

    }


    /**
     * 都绑定了
     * @param list
     * @return
     */
    public boolean isAllBind(List<AuthState> list) {

        boolean result = false;

        List<String> listTemp = new ArrayList<>();
        for (AuthState authState : list) {
            String auth_type = authState.getAuth_type();
            listTemp.add(auth_type);
        }

        if(vertifyNotNull.isNotNullListSize(listTemp)) {
            if(listTemp.contains(ConstTag.AuthTag.WX) && listTemp.contains(ConstTag.AuthTag.QQ)) {
                result = true;
            }
        }

        return result;

    }


    /**
     * 都没有绑定了
     * @param list
     * @return
     */
    public boolean isAllNotBind(List<AuthState> list) {

        boolean result = false;

        List<String> listTemp = new ArrayList<>();

        if(!vertifyNotNull.isNotNullListSize(list)) {

            result = true;

        }

        return result;

    }
}
