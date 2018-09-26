package com.adhdriver.work.callback.doublebutton;

/**
 * Created by Administrator on 2017/4/25.
 * 类描述 DoubleDialog的点击时间，包含 cancel 和sure
 * 版本
 */

public interface DoubleDialogCallBack {

    /**
     * cancel
     * @param tag 当前调用Dialog所属的tag
     */
    public void dialogClickCancel(int tag);


    /**
     * sure
     * @param tag 当前调用Dialog所属的tag
     */
    public void dialogClickSure(int tag);

}
