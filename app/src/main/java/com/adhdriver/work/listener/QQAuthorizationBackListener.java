package com.adhdriver.work.listener;

import android.util.Log;

import com.adhdriver.work.callback.auth.QQAuthorityCallBack;
import com.adhdriver.work.entity.driver.thirdauth.QQAuthBack;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Created by Administrator on 2017/10/31.
 * 类描述  QQ授权登陆类
 * 版本
 */

public class QQAuthorizationBackListener implements IUiListener {



    private QQAuthorityCallBack qqAuthorityCallBack;

    public void setQqAuthorityCallBack(QQAuthorityCallBack qqAuthorityCallBack) {
        this.qqAuthorityCallBack = qqAuthorityCallBack;
    }

    /**
     * 获取用户信息接口
     * @param o
     */





    @Override
    public void onComplete(Object o) {




        QQAuthBack qqAuthBack = getQQAuthBack(o);


        Log.i("qq_autho", qqAuthBack.toString());

        if(null != qqAuthBack && null != qqAuthorityCallBack) {

            qqAuthorityCallBack.qqAuthoritySuccessAndNext(qqAuthBack);

        }

    }

    @Override
    public void onError(UiError uiError) {
        Log.i("qq_autho", uiError.toString());


        if(null != qqAuthorityCallBack) {

            qqAuthorityCallBack.qqAuthorityFail();

        }
    }

    @Override
    public void onCancel() {


        if(null != qqAuthorityCallBack) {

            qqAuthorityCallBack.qqAuthorityCancel();

        }


    }


    /**
     * 获取并解析QQ授权返回
     * @param o
     * @return
     */
    private QQAuthBack getQQAuthBack(Object o) {

        QQAuthBack qqAuthBack = null;
        if(null != o) {

            JSONObject jsonObject = (JSONObject) o;

            Type type = new TypeToken<QQAuthBack>() {
            }.getType();

             qqAuthBack = new Gson().fromJson(jsonObject.toString(), type);

        }

        return qqAuthBack;

    }
}
