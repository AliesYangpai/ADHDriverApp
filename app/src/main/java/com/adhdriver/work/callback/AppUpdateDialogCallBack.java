package com.adhdriver.work.callback;

import com.adhdriver.work.entity.AppUpdate;

/**
 * Created by Administrator on 2017/6/26.
 * 类描述  版本更新的回调函数
 * 版本
 */

public interface AppUpdateDialogCallBack {

//    /**
//     * cancel
//     *
//     */
//    public void appUpdateDialogClickCancel();


    /**
     * sure
     * 回传的对象
     */
     void appUpdateDialogClickSure(AppUpdate appUpdate);


}
