package com.adhdriver.work.verification;



import org.feezu.liuli.timeselector.Utils.TextUtil;

import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2017/11/24.
 * 类描述  用于非空验证的类
 * 版本
 */

public class VertifyNotNull {


    /**
     * String 类型非空验证
     *
     * @param str
     * @return
     */
    public boolean isNotNullString(String str) {

        return !TextUtil.isEmpty(str);

    }


    /**
     * 为空
     * @param str
     * @return
     */
    public boolean isNullString(String str) {

        return TextUtil.isEmpty(str);
    }


    /**
     * 对象非空验证
     * @param object
     * @return
     */

    public boolean isNotNullObj(Object object) {

        return null != object;

    }


    /**
     * 对象为空
     * @param object
     * @return
     */
    public boolean isNullObj(Object object) {


        return null == object;

    }




    /**
     * 集合非空
     * @param
     * @return
     */
    public boolean isNotNullList(List list) {

        return null != list;
    }


    /**
     *  集合非空且有数据
     * @param list
     * @return
     */
    public boolean isNotNullListSize(List list) {

        return (null != list)&& (list.size() > 0);
    }




}
