package com.adhdriver.work.http;

import com.adhdriver.work.entity.driver.pay.aliAbout.AliAuthInfo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/2.
 * 类描述   实体类对象解析
 * 版本
 */

public class ParseSerilizable {


    public ParseSerilizable() {

    }


    /**
     * 解析成对象
     *
     * @param back
     * @param cls
     * @param <T>
     * @return
     */
    public <T> T getParseToObj(String back, Class<T> cls) {
        T t = null;


        try {
            t = new Gson().fromJson(back, cls);
        } catch (Exception e) {
            return t;
        }
        return t;
    }


    /**
     * 解析成集合
     *
     * @param back
     * @param cls
     * @param <T>
     * @return
     */
    public <T> List<T> getParseToList(String back, Class<T> cls) {


        JsonObject asJsonObject = null;
        JsonArray jsonArray = null;
        List<T> list = null;
        Gson gson = null;
        try {
            asJsonObject = new JsonParser().parse(back).getAsJsonObject();
            String items = "items";
            jsonArray = asJsonObject.getAsJsonArray(items);

            gson = new Gson();
            list = new ArrayList<T>();
            for (JsonElement elem : jsonArray) {
                list.add(gson.fromJson(elem, cls));
            }

        } catch (Exception e) {
            return list;
        }
        return list;
    }


    /**
     * 解析成集合
     *
     * @param back
     * @param cls
     * @param <T>
     * @return
     */
    public <T> List<T> getParseToNoItemsList(String back, Class<T> cls) {

        JsonArray asJsonArray = null;

        Gson gson = null;
        List<T> list = null;

        try {

            asJsonArray = new JsonParser().parse(back).getAsJsonArray();
            gson = new Gson();
            list = new ArrayList<T>();

            for (JsonElement elem : asJsonArray) {
                list.add(gson.fromJson(elem, cls));
            }


        }catch (Exception e) {

            return list;

        }
        return list;

    }


    /**
     * 这个是 org的jsonobject
     *
     * @param back
     * @param key
     * @return
     */
    public String getParseString(String back, String key) {

        String json = "";
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(back);
            json = jsonObject.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }


    /**
     * 获取特别解析获取AliInfo
     * @param back
     * @return
     */
    public AliAuthInfo specialGetAliAuthInfoBack(String back) {


        AliAuthInfo aliAuthInfo = null;

        JsonObject asJsonObject = new JsonParser().parse(back).getAsJsonObject();

        String auth_login_info = "auth_login_info";


        if (null != asJsonObject) {


            JsonObject asJsonObjectChild1 = asJsonObject.getAsJsonObject(auth_login_info);


            if (null != asJsonObjectChild1) {

                Type type = new TypeToken<AliAuthInfo>() {
                }.getType();

                aliAuthInfo = new Gson().fromJson(asJsonObjectChild1, type);
            }
        }


        return aliAuthInfo;
    }




//    /**
//     * 解析成对象
//     * @param back
//     * @param cls
//     * @param <T>
//     * @return
//     */
//    public <T>T getParseToObj(String back,Class<T> cls) {
//        T t = new Gson().fromJson(back, cls);
//        return t;
//    }
//
//
//    /**
//     * 解析成集合
//     * @param back
//     * @param cls
//     * @param <T>
//     * @return
//     */
//    public <T>List<T> getParseToList(String back, Class<T> cls) {
//
//        JsonObject asJsonObject = new JsonParser().parse(back).getAsJsonObject();
//
//        String items = "items";
//        JsonArray jsonArray = asJsonObject.getAsJsonArray(items);
//
//        Gson gson = new Gson();
//        List<T> list = new ArrayList<T>();
//        for(JsonElement elem : jsonArray){
//            list.add(gson.fromJson(elem, cls));
//        }
//        return list;
//
//
//    }
//
//
//    /**
//     * 解析成集合
//     * @param back
//     * @param cls
//     * @param <T>
//     * @return
//     */
//    public <T>List<T> getParseToNoItemsList(String back, Class<T> cls) {
//
//        JsonArray asJsonArray = new JsonParser().parse(back).getAsJsonArray();
//
//        Gson gson = new Gson();
//        List<T> list = new ArrayList<T>();
//        for(JsonElement elem : asJsonArray){
//            list.add(gson.fromJson(elem, cls));
//        }
//        return list;
//
//    }
//
//
//    /**
//     * 这个是 org的jsonobject
//     * @param back
//     * @param key
//     * @return
//     */
//    public String getParseString(String back,String key) {
//
//        String json  = "";
//        JSONObject jsonObject = null;
//        try {
//            jsonObject = new JSONObject(back);
//            json = jsonObject.getString(key);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return json;
//    }


}
