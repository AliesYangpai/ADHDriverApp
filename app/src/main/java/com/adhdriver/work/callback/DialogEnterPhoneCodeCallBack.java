package com.adhdriver.work.callback;

/**
 * Created by Administrator on 2017/5/12.
 * 类描述  编辑窗口点击回调函数
 * 版本
 */

public interface DialogEnterPhoneCodeCallBack {


    /**
     * cancel
     * @param
     */
     void dialogEnterPhoneCodeCancel();



    /**
     * sure
     * @param  当前调用Dialog所属的tag
     */
     void dialogEnterPhoneCodeSure(String text);



}
