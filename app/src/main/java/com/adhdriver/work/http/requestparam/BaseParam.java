package com.adhdriver.work.http.requestparam;

import com.adhdriver.work.presenter.common.PresenterUserAgreement;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Alie on 2017/11/14.
 * 类描述
 * 版本
 */

public class BaseParam {




    public JsonObject getJsonObject() {
        return new JsonObject();
    }


    /**
     * 获取jsonElement
     *  用于转化json实体类对象
     * @param object
     * @return
     */
    public JsonElement getJsonElement(Object object) {

        return new Gson().toJsonTree(object);
    }


    /**
     *  获取ORG 的jsonObject
     * @param param
     * @return
     * @throws JSONException
     */
    public JSONObject getORGJsonObJect(String param) throws JSONException {

        return new JSONObject(param);
    }


}
