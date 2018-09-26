package com.adhdriver.work.callback.edit;

/**
 * Created by Administrator on 2017/5/12.
 * 类描述  编辑窗口点击回调函数
 * 版本
 */

public interface DialogClickEditCallBack {


    /**
     * cancel
     * @param tag 当前调用Dialog所属的tag
     */
    public void dialogEditClickCancel(int tag);



    /**
     * sure
     * @param tag 当前调用Dialog所属的tag
     */
    public void dialogEditClickSure(int tag, String text);



}
